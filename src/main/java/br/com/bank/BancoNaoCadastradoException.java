package br.com.bank;

public class BancoNaoCadastradoException extends RuntimeException {
  
  public BancoNaoCadastradoException(){
    super("Banco não foi cadastrado!");
  }
}
