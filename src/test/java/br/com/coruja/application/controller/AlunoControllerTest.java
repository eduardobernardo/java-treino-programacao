package br.com.coruja.application.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.com.coruja.dominio.model.Aluno;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AlunoControllerTest {

  @Autowired
  protected WebTestClient web;

  @BeforeEach
  public void setUp() {
      web = web.mutate().responseTimeout(Duration.ofMillis(10000)).build();
  }

  @Test
  @Description("Deve salvar um novo aluno (mock) no banco")
  public void deve_salvar_aluno(){
    Aluno novoAluno = mock(Aluno.class);
    web.post().uri("/alunos").bodyValue(novoAluno).accept(MediaType.ALL).exchange().expectStatus().isOk().expectBody(Aluno.class)
                .value(c -> assertEquals(Aluno.class, c.getClass()));
  }
  
}
