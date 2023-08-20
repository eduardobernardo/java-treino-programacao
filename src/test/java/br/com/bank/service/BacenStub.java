package br.com.bank.service;


import br.com.bank.gateway.Bacen;
import br.com.bank.model.Banco;

public class BacenStub extends Bacen {

  private Boolean throwError;

  public BacenStub(boolean throwError){
    this.throwError = throwError;
  }

  @Override
  public long cadastrarBanco(Banco banco) {
    if(this.throwError){
      throw new RuntimeException("BancoNaoCadastradoException");
    }
		return 123456L;
	}

}
