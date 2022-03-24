package com.edu.fatec.upload.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.fatec.upload.entidades.Arquivo;

public interface RepositorioArquivo extends JpaRepository<Arquivo, Long> {
}