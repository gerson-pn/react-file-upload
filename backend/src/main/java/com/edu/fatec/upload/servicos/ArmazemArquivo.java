package com.edu.fatec.upload.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.edu.fatec.upload.entidades.Arquivo;
import com.edu.fatec.upload.repositorios.RepositorioArquivo;

@Service
public class ArmazemArquivo {
	@Autowired
	private RepositorioArquivo repositorio;
	
	public void armazenarArquivo(Arquivo arquivo) {
		this.repositorio.save(arquivo);
	}
	
	public List<Arquivo> obterArquivos(){
		return this.repositorio.findAll();
	}
	
	public Resource obterArquivoComoRecurso(Long id) {
		Arquivo arquivo = this.repositorio.getById(id);
		Resource recurso = new ByteArrayResource(arquivo.getBytes());
		return recurso;
	}
	
	public Arquivo obterArquivo(Long id) {
		Arquivo arquivo = this.repositorio.getById(id);
		return arquivo;
	}
	
	public void excluirArquivo(Arquivo arquivo) {
		this.repositorio.delete(arquivo);
	}
}