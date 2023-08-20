package br.com.coruja.application.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.coruja.dominio.model.Aluno;
import br.com.coruja.dominio.repository.AlunoRepository;

@RestController
@RequestMapping(value="/alunos")
public class AlunoController {
  
  @Autowired
  private AlunoRepository repository;

  @GetMapping
  public ResponseEntity<List<Aluno>> list(){
    List<Aluno> alunos = repository.findAll();
    return ResponseEntity.status(HttpStatus.OK).body(alunos);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Aluno> find(@PathVariable Long id){
    Aluno aluno = repository.findById(id).get();
    if(aluno.getId() != null){
      return ResponseEntity.status(HttpStatus.OK).body(aluno);
    }else{
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<Aluno> update(@PathVariable Long id, @RequestBody Aluno data){
    Aluno alunoInfo = repository.findById(id).get();

    if(alunoInfo.getId() != null){
      alunoInfo.setEmail(data.getEmail());
      alunoInfo.setNome(data.getNome());
      repository.save(alunoInfo);
      return ResponseEntity.status(HttpStatus.OK).body(alunoInfo);
    }else{
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
  }

  @PostMapping
  public ResponseEntity<Aluno> save(@RequestBody Aluno aluno){
    Aluno created = repository.save(aluno);
    if(created.getId() != null){
      return ResponseEntity.status(HttpStatus.OK).body(created);
    }else{
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
  }

  @DeleteMapping(value = "/{id}")
  @ResponseStatus(code = HttpStatus.OK)
  public ResponseEntity<Void> delete(@PathVariable Long id){
    Optional<Aluno> alunoInfo = repository.findById(id);

    if(alunoInfo.isPresent()){
      repository.deleteById(id);
      return ResponseEntity.status(HttpStatus.OK).build();
    }else{
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }
}
