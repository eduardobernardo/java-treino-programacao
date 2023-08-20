package br.com.bank.service;

import br.com.bank.BancoNaoCadastradoException;
import br.com.bank.gateway.Bacen;
import br.com.bank.model.Banco;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.quality.Strictness;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class SistemaBancarioTest {

   @InjectMocks
   private SistemaBancario sistemaBancario;
   private SistemaBancario sistemaBancarioFake;
   private SistemaBancario sistemaBancarioStub;
   @Mock
   private Bacen bacen;

   @BeforeEach
   public void setUp(){
      sistemaBancarioFake = new SistemaBancario(new BacenFake());
      sistemaBancarioStub = new SistemaBancario(new BacenStub());
   }

   @Test
   public void deve_retornar_id_ao_cadastrar_banco_fake(){
      long id = sistemaBancarioFake.registrarBanco(new Banco("Inter"));
      assertEquals(1, id);
   }

   @Test
   public void deve_retornar_id_ao_cadastrar_banco_stub(){
      long id = sistemaBancarioStub.registrarBanco(new Banco("Inter"));
      assertEquals(1, id);
   }

   @Test
   public void deve_retornar_id_ao_cadastrar_banco_mock(){
      Banco inter = new Banco("inter");

      when(bacen.cadastrarBanco(inter)).thenReturn(1L);
      long id = sistemaBancario.registrarBanco(inter);

      verify(bacen, times(1)).cadastrarBanco(inter);
      assertEquals(1L, id);
   }

   @Test
   public void deve_retornar_erro_BancoNaoCadastradoException(){
    Banco banco = new Banco("Inter");

    when(bacen.cadastrarBanco(banco)).thenThrow(BancoNaoCadastradoException.class);
    SistemaBancario sistemaBancario = new SistemaBancario(bacen);

    assertThrows(BancoNaoCadastradoException.class, () -> sistemaBancario.registrarBanco(banco));
   }

}