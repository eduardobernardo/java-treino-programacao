package br.com.bank.service;

import br.com.bank.gateway.Bacen;
import br.com.bank.model.Banco;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.quality.Strictness;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class SistemaBancarioTest {

   @InjectMocks
   private SistemaBancario sistemaBancario;
   @Mock
   private Bacen bacen;

   @Test
   public void registrarBanco_sucesso(){
    Banco banco = new Banco("Inter");
    when(bacen.cadastrarBanco(banco)).thenReturn(123456L);

    SistemaBancario sistemaBancario = new SistemaBancario(bacen);
    long registro = sistemaBancario.registrarBanco(banco);

    assertEquals(123456L, registro);
   }

   @Test
   public void registrarBanco_error(){
    Banco banco = new Banco("Inter");
    when(bacen.cadastrarBanco(banco)).thenThrow(new RuntimeException("BancoNaoCadastradoException"));
    SistemaBancario sistemaBancario = new SistemaBancario(bacen);

    assertThrows(RuntimeException.class, () -> sistemaBancario.registrarBanco(banco));
   }

}