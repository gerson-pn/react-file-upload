package com.edu.fatec.upload.modelos.construtores;


public interface Construtor<T> {
	public void reiniciarObjeto();
	public T obterObjeto();
}