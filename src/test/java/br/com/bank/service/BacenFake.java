package br.com.bank.service;


import java.util.HashMap;
import java.util.Map;

import br.com.bank.gateway.Bacen;
import br.com.bank.model.Banco;

public class BacenFake extends Bacen {

  private Map<Long,Banco> bancos = new HashMap<Long,Banco>();

  @Override
  public long cadastrarBanco(Banco banco) {
		long id = bancos.size() + 1;
    bancos.put(id, banco);
    return id;
	}

}
