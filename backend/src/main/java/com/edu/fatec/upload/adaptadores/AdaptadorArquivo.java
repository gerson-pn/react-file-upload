package com.edu.fatec.upload.adaptadores;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdaptadorArquivo extends RepresentationModel<AdaptadorArquivo> {
	private Long id;
	private String nome;
	private String tamanho;
	private String tipo;
}