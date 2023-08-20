package br.com.bank.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Banco {

    private String nome;

    public Banco(String nome) {
        this.nome = nome;
    }

    private List<Conta> contas = new ArrayList<>();

    public void adicionarConta(Conta conta) {
        if(conta == null){
            throw new IllegalArgumentException();
        }
        contas.add(conta);
    }
    public Conta pesquisarContaDoCliente(String cpf){
        //Aproveitar o metodo de filtrar contas para retornar a pesquisa
        List<Conta> contas = filtrarContas( conta -> conta.getCpf().equals(cpf));
        if(contas.size() > 0){
            return contas.get(0);
        }else{
            throw new IllegalArgumentException();
        }
    }

    public List<Conta> listarContasAltaRenda() {
        return filtrarContas(c -> c.getSaldo() >= 10000);
    }

    private List<Conta> filtrarContas(Predicate<Conta> filtro) {
        return contas.stream().filter(filtro).collect(Collectors.toList());
    }
}
