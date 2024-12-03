package model;

import controller.CSVUtils;

import java.util.ArrayList;
import java.util.Arrays;

public class HistoricoFornecedor {
    private ArrayList<Fornecedor> fornecedores;

    public void adicionarFornecedor(Fornecedor fornecedor){
        this.fornecedores.add(fornecedor);
    }

    public boolean salvarFornecedores(){
        try {
            //CSVUtils.salvarEmCsv("fornecedor.csv", "cnpj,nome,telefone,categoria");
            return true;
        } catch (RuntimeException e) {
            System.out.println("Erro ao salvar produtos, tente novamente.");
            return false;
        }
    }
}
