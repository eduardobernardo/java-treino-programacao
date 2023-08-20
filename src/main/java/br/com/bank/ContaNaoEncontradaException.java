package br.com.bank;

public class ContaNaoEncontradaException extends RuntimeException {
  
  public ContaNaoEncontradaException(){
    super("Conta não encontrada!");
  }
}
