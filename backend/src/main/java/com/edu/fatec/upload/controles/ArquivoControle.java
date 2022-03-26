package com.edu.fatec.upload.controles;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.edu.fatec.upload.adaptadores.AdaptadorArquivo;
import com.edu.fatec.upload.entidades.Arquivo;
import com.edu.fatec.upload.modelos.hateoas.AdicionadorLinkAdaptadorArquivo;
import com.edu.fatec.upload.modelos.populadores.PopuladorAdaptadorArquivo;
import com.edu.fatec.upload.servicos.ArmazemArquivo;

@CrossOrigin
@RestController
public class ArquivoControle {
	@Autowired
	private AdicionadorLinkAdaptadorArquivo adicionadorLink;
	@Autowired
	private ArmazemArquivo armazem;

	@PostMapping("/arquivo/enviar")
	public ResponseEntity<String> receberArquivo(@RequestParam("file") MultipartFile arquivoEnviado)
			throws IOException {
		Arquivo arquivo = new Arquivo();
		arquivo.setBytes(arquivoEnviado.getBytes());
		arquivo.setNome(arquivoEnviado.getOriginalFilename());
		Long tamanho = arquivoEnviado.getSize();
		arquivo.setTamanho(tamanho.toString());
		arquivo.setTipo(arquivoEnviado.getContentType());
		armazem.armazenarArquivo(arquivo);
		return new ResponseEntity<String>("arquivo recebido", HttpStatus.ACCEPTED);
	}

	@GetMapping("/arquivos")
	public ResponseEntity<List<AdaptadorArquivo>> obterArquivos() {
		List<Arquivo> arquivos = armazem.obterArquivos();
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
	public ResponseEntity<Resource> obterArquivo(@PathVariable long id) {
		Resource recurso = armazem.obterArquivoComoRecurso(id);
		if (recurso == null) {
			ResponseEntity<Resource> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return resposta;
		} else {
			Arquivo arquivo = armazem.obterArquivo(id);
			MediaType tipoArquivo = MediaType.parseMediaType(arquivo.getTipo());
			ResponseEntity<Resource> resposta = ResponseEntity.ok().contentType(tipoArquivo).body(recurso);
			return resposta;
		}
	}

	@DeleteMapping("/arquivo/excluir")
	public ResponseEntity<String> excluirArquivo(@RequestBody Arquivo arquivo) {
		Arquivo alvo = armazem.obterArquivo(arquivo.getId());
		if (!(alvo == null)) {
			armazem.excluirArquivo(alvo);
			ResponseEntity<String> resposta = new ResponseEntity<String>(HttpStatus.ACCEPTED);
			return resposta;
		} else {
			ResponseEntity<String> resposta = new ResponseEntity<String>("Arquivo não encontrado para exclusão",
					HttpStatus.BAD_REQUEST);
			return resposta;
		}

	}
}