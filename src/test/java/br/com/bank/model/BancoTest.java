package br.com.bank.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class BancoTest {
  private Banco banco;
  private Conta conta;
  private Conta conta01;
  private Conta conta02;
  private Conta conta03;
  private Conta conta04;
  private Conta conta05;

  @BeforeEach
  public void setUp(){
    banco = new Banco("Inter");
    conta = new Conta("054");
    conta01 = new Conta("01");
    conta01.addSaldo(0.00);
    conta02 = new Conta("02");
    conta02.addSaldo(100.00);
    conta03 = new Conta("03");
    conta03.addSaldo(50000.00);
    conta04 = new Conta("04");
    conta04.addSaldo(75000.00);
    conta05 = new Conta("05");
    conta05.addSaldo(9999.99);
  }

  @Test
  @DisplayName("Deve adicionar uma conta ao Banco sem retornar erro")
  public void adicionarConta(){
    // instancia um objeto Spy
    Banco bancoSpy = Mockito.spy(banco);
    assertDoesNotThrow(() -> bancoSpy.adicionarConta(conta));
  }

  @Test
  @DisplayName("Deve retornar IllegalArgumentException quando for adicionada uma conta nula")
  public void adicionarConta_throws_error(){
    // instancia um objeto Spy
    Banco bancoSpy = Mockito.spy(banco);
    assertThrows(IllegalArgumentException.class, () -> bancoSpy.adicionarConta(null));
  }
  
  @Test
  @DisplayName("Deve retornar a conta com cpf 054 adicionada ao Banco")
  public void pesquisar_conta_cliente(){
    // instancia um objeto Spy
    Banco bancoSpy = Mockito.spy(banco);
    bancoSpy.adicionarConta(conta);
    assertEquals(Conta.class, bancoSpy.pesquisarContaDoCliente("054").getClass());
  }

  @Test
  @DisplayName("Deve retornar erro quando pesquisar um cpf que não tem cadastro")
  public void pesquisar_conta_cliente_throws_error(){
    // instancia um objeto Spy
    Banco bancoSpy = Mockito.spy(banco);
    bancoSpy.adicionarConta(conta);
    assertThrows(IllegalArgumentException.class, () -> bancoSpy.pesquisarContaDoCliente("022").getCpf());
  }

  @Test
  @DisplayName("Deve retornar apenas 2 contas com alta renda (acima de 10000)")
  public void listarContasAltaRenda(){
    // instancia um objeto Spy
    Banco bancoSpy = Mockito.spy(banco);
    // Adiciona as contas ao Banco
    bancoSpy.adicionarConta(conta);
    bancoSpy.adicionarConta(conta01);
    bancoSpy.adicionarConta(conta02);
    bancoSpy.adicionarConta(conta03);
    bancoSpy.adicionarConta(conta04);
    bancoSpy.adicionarConta(conta05);
    assertEquals(2, bancoSpy.listarContasAltaRenda().size());
  }

  @Test
  @DisplayName("Deve retornar falso se for esperado 3 contas de alta renda (acima de 10000) onde existem apenas 2")
  public void listarContasAltaRenda_false(){
    // instancia um objeto Spy
    Banco bancoSpy = Mockito.spy(banco);
    bancoSpy.adicionarConta(conta);
    bancoSpy.adicionarConta(conta01);
    bancoSpy.adicionarConta(conta02);
    bancoSpy.adicionarConta(conta03);
    bancoSpy.adicionarConta(conta04);
    bancoSpy.adicionarConta(conta05);
    assertFalse(() -> bancoSpy.listarContasAltaRenda().size() == 3);
  }
}
