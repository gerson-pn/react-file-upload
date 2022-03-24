package com.edu.fatec.upload.modelos.construtores;

import com.edu.fatec.upload.adaptadores.AdaptadorArquivo;

public class ConstrutorAdaptadorArquivo implements Construtor<AdaptadorArquivo> {
	private AdaptadorArquivo adaptador;

	public ConstrutorAdaptadorArquivo() {
		adaptador = new AdaptadorArquivo();
	}

	public void setId(Long id) {
		this.adaptador.setId(id);
	}

	public void setNome(String nome) {
		this.adaptador.setNome(nome);
	}

	public void setTamanho(String tamanho) {
		this.adaptador.setTamanho(tamanho);
	}

	public void setTipo(String tipo) {
		this.adaptador.setTipo(tipo);
	}

	@Override
	public void reiniciarObjeto() {
		adaptador = new AdaptadorArquivo();
	}

	@Override
	public AdaptadorArquivo obterObjeto() {
		return this.adaptador;
	}

}