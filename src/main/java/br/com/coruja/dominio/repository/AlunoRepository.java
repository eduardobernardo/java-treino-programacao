package br.com.coruja.dominio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.coruja.dominio.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
  
}
