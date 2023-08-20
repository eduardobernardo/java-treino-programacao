package br.com.bank.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import br.com.bank.ContaNaoEncontradaException;

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
        // O metodo filtrarContas retorna uma lista, mas queremos apenas 1 conta
        // List<Conta> contas = filtrarContas( conta -> conta.getCpf().equals(cpf));

        // O metodo Stream Filter, quando usado com o metodo findFirst
        // Faz a mesma funcao do break em um for loop
        // A vantagem e que o stream deixa o codigo mais declarativo sobre
        // as intencoes do algoritmo.
        Optional<Conta> contaCliente = contas.stream().filter(conta -> conta.getCpf().equals(cpf)).findFirst();

        // Se encontrou alguma conta, retorna
        if(contaCliente.isPresent()){
            return contaCliente.get();
        }else{
            // Retorna o erro de conta nao encontrada
            throw new ContaNaoEncontradaException();
        }
    }

    public List<Conta> listarContasAltaRenda() {
        return filtrarContas(c -> c.getSaldo() >= 10000);
    }

    private List<Conta> filtrarContas(Predicate<Conta> filtro) {
        return contas.stream().filter(filtro).collect(Collectors.toList());
    }
}
