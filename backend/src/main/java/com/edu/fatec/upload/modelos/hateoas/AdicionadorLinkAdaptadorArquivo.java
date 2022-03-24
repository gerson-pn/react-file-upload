package com.edu.fatec.upload.modelos.hateoas;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.edu.fatec.upload.adaptadores.AdaptadorArquivo;
import com.edu.fatec.upload.controles.ArquivoControle;

@Component
public class AdicionadorLinkAdaptadorArquivo implements AdicionadorLink<AdaptadorArquivo> {
	@Override
	public void adicionarLink(List<AdaptadorArquivo> lista) {
		for (AdaptadorArquivo arquivo : lista) {
			long id = arquivo.getId();
			Link linkProprio = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(ArquivoControle.class)
							.obterArquivo(id))
					.withSelfRel();
			arquivo.add(linkProprio);
		}
	}

	@Override
	public void adicionarLink(AdaptadorArquivo arquivo) {
		Link linkLista = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(ArquivoControle.class)
						.obterArquivos())
				.withRel("Lista de Arquivos");
		arquivo.add(linkLista);
	}
}