package br.com.bank.model;


public class Conta {

	private double saldo;
	private String cpf;

	public Conta(String cpf) {
		this.cpf = cpf;
	}

	// Adicionar uma funcao que adiciona saldo na conta
	public void addSaldo(double saldoAdicionar){
		if(saldoAdicionar >= 0.0){
			this.saldo += saldoAdicionar;
		}else{
			throw new IllegalArgumentException();
		}
	}

	public double getSaldo() {
		return saldo;
	}

	public String getCpf() {
		return cpf;
	}
}
