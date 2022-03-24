package com.edu.fatec.upload.controles;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.edu.fatec.upload.adaptadores.AdaptadorArquivo;
import com.edu.fatec.upload.entidades.Arquivo;
import com.edu.fatec.upload.modelos.hateoas.AdicionadorLinkAdaptadorArquivo;
import com.edu.fatec.upload.modelos.populadores.PopuladorAdaptadorArquivo;
import com.edu.fatec.upload.repositorios.RepositorioArquivo;

@CrossOrigin
@RestController
public class ArquivoControle {
	@Autowired
	private RepositorioArquivo repositorio;
	@Autowired
	private AdicionadorLinkAdaptadorArquivo adicionadorLink;

	@PostMapping("/arquivo/enviar")
	public ResponseEntity<String> receberArquivo(@RequestParam("file") MultipartFile arquivoEnviado)
			throws IOException {
		Arquivo arquivo = new Arquivo();
		arquivo.setBytes(arquivoEnviado.getBytes());
		arquivo.setNome(arquivoEnviado.getOriginalFilename());
		Long tamanho = arquivoEnviado.getSize();
		arquivo.setTamanho(tamanho.toString());
		arquivo.setTipo(arquivoEnviado.getContentType());
		repositorio.save(arquivo);
		return new ResponseEntity<String>("arquivo recebido", HttpStatus.ACCEPTED);
	}

	@GetMapping("/arquivos")
	public ResponseEntity<List<AdaptadorArquivo>> obterArquivos() {
		List<Arquivo> arquivos = repositorio.findAll();
		PopuladorAdaptadorArquivo populador = new PopuladorAdaptadorArquivo(arquivos);
		List<AdaptadorArquivo> adaptadores = populador.adaptadores();

		if (arquivos.isEmpty()) {
			ResponseEntity<List<AdaptadorArquivo>> resposta = new ResponseEntity<>(adaptadores, HttpStatus.NOT_FOUND);
			return resposta;
		} else {
			adicionadorLink.adicionarLink(adaptadores);
			ResponseEntity<List<AdaptadorArquivo>> resposta = new ResponseEntity<>(adaptadores, HttpStatus.FOUND);
			return resposta;
		}
	}

	@GetMapping("/arquivo/{id}")
	public ResponseEntity<Arquivo> obterArquivo(@PathVariable long id) {
		Arquivo arquivo = repositorio.getById(id);
		if (arquivo == null) {
			ResponseEntity<Arquivo> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return resposta;
		} else {
			ResponseEntity<Arquivo> resposta = new ResponseEntity<Arquivo>(arquivo, HttpStatus.FOUND);
			return resposta;
		}
	}
}