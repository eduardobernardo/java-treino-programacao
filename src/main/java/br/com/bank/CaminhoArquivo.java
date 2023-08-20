package br.com.bank;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CaminhoArquivo {


    private Path diretorio;

    private Path arquivo;

    private CaminhoArquivo(Path diretorio, Path arquivo) {
        super();
        this.diretorio = diretorio;
        this.arquivo = arquivo;
    }

    private static String mountDiretorio(Integer id){
        String caminho = "/tmp/";

        if(id > 0){
            // Define um BigDecimal passando o ID
            BigDecimal idArquivo = new BigDecimal(id);
            // Define um BigDecimal passando o divisor exigido pela lógica do teste
            BigDecimal divisor = new BigDecimal(1000);
            
            // Faz o cálculo do resultado da divisão, arredondando para cima
            // utilizando o método RoundingMode.UP.
            BigDecimal modulo = idArquivo.divide(divisor, RoundingMode.UP);
    
            // Monta a String do Diretório e do Arquivo
            caminho += modulo;
        }else{
            caminho += "1";
        }
        return caminho;
    }

    public Path getDiretorio() {
        return diretorio;
    }

    public Path getArquivo() {
        return arquivo;
    }

    public static CaminhoArquivo getInstance(Integer id) {
        //A lógica estava bugada pois os teste esperavam
        // um caminho /tmp/diretório/arquivo, onde diretório 
        // é setado de acordo com a lógica 
        // (se o id for menor que 1000, então 1, se não, incrementa 1
        // a cada 1000)

        if(id == null){
            throw new IDNaoInformadoException();
        }
        
        // Preferi fazer uma função privada para desenvolver a lógica separada
        String caminhoDiretorio = mountDiretorio(id);
        String arquivo = caminhoDiretorio + "/" + id;

        //Retorna os caminhos do Diretório e do Arquivo
        return new CaminhoArquivo(Paths.get(caminhoDiretorio), Paths.get(arquivo));
    }

}
