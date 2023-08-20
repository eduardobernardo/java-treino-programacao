package br.com.bank.service;


import br.com.bank.gateway.Bacen;
import br.com.bank.model.Banco;

public class BacenStub extends Bacen {

  @Override
  public long cadastrarBanco(Banco banco) {
		return 1;
	}

}
