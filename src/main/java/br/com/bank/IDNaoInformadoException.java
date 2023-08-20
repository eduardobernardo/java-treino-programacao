package br.com.bank;

public class IDNaoInformadoException extends NullPointerException {
  public IDNaoInformadoException(){
    super("ID não informado!");
  }
}
