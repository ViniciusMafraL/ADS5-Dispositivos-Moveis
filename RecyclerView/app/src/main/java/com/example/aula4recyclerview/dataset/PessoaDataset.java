package com.example.aula4recyclerview.dataset;

import com.example.aula4recyclerview.models.Pessoa;

import java.util.ArrayList;

public class PessoaDataset {
    private static ArrayList<Pessoa> pessoas ;

    public static ArrayList<Pessoa> getItens(){
        pessoas = new ArrayList<>();
        pessoas.add(new Pessoa(1,"Rafael Moreno","222222"));
        pessoas.add(new Pessoa(2,"José da Silva","3333333"));
        pessoas.add(new Pessoa(3,"Patrícia Soares","4444444"));

        return pessoas;
    }
    public static void addPessoa(Pessoa pessoa){
        pessoas.add(pessoa);
    }
}
