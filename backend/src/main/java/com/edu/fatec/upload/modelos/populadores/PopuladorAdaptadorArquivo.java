package com.edu.fatec.upload.modelos.populadores;

import java.util.ArrayList;
import java.util.List;

import com.edu.fatec.upload.adaptadores.AdaptadorArquivo;
import com.edu.fatec.upload.entidades.Arquivo;
import com.edu.fatec.upload.modelos.construtores.ConstrutorAdaptadorArquivo;

public class PopuladorAdaptadorArquivo{
	private List<Arquivo> arquivos;

	public PopuladorAdaptadorArquivo(List<Arquivo> arquivos) {
		this.arquivos = arquivos;
	}
	
	public List<AdaptadorArquivo> adaptadores(){
		List<AdaptadorArquivo> adaptadores = new ArrayList<AdaptadorArquivo>();
		ConstrutorAdaptadorArquivo construtor = new ConstrutorAdaptadorArquivo();
		for (Arquivo arquivo : arquivos) {
			construtor.reiniciarObjeto();
			construtor.setId(arquivo.getId());
			construtor.setNome(arquivo.getNome());
			construtor.setTamanho(arquivo.getTamanho());
			construtor.setTipo(arquivo.getTipo());
			adaptadores.add(construtor.obterObjeto());
		}
		return adaptadores;
	}
}