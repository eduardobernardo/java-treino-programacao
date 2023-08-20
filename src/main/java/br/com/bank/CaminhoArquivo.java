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
        String directory = "/tmp/";

        if(id > 0){
            // Define um BigDecimal passando o ID
            BigDecimal a = new BigDecimal(id);
            // Define um BigDecimal passando o divisor exigido pela lógica do teste
            BigDecimal divisor = new BigDecimal(1000);
            
            // Faz o cálculo do resultado da divisão, arredondando para cima
            // utilizando o método RoundingMode.UP.
            BigDecimal result = a.divide(divisor, RoundingMode.UP);
    
            // Monta a String do Diretório e do Arquivo
            directory += result;
        }else{
            directory += "1";
        }
        return directory;
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
        String directory = mountDiretorio(id);
        String file = directory + "/" + id;

        //Retorna os caminhos do Diretório e do Arquivo
        return new CaminhoArquivo(Paths.get(directory), Paths.get(file));
    }

}
