package model;

import controller.ArraysUtils;
import controller.CSVUtils;

import java.util.ArrayList;
import java.util.Arrays;

public class HistoricoProdutos {
    private Produto[] produtos;
    private int quantidadeProdutos;

    public HistoricoProdutos(){
        this.produtos = new Produto[3];
        this.quantidadeProdutos = 0;
    }

    public boolean cadastrarProduto(Produto produto, boolean isReader){

            if(this.quantidadeProdutos == this.produtos.length){
                this.produtos = Arrays.copyOf(this.produtos, this.produtos.length+3);
            }
            this.produtos[this.quantidadeProdutos] = produto;
            this.quantidadeProdutos++;
            if (!isReader){
                if (this.salvarProdutos()){
                    return true;
                }else {
                    this.quantidadeProdutos--;
                    System.out.println("Erro cadastrar produto, tente novamente.");
                    return false;
                }
            }
            return true;


    }

    public Produto[] pesquisarProdutos(String nome){
        if (!ArraysUtils.isEmpty(this.produtos)){

            return  Arrays.stream(this.produtos)
                    .filter(produto -> produto != null)
                    .filter(produto -> produto.getDescricao().toLowerCase().contains(nome.toLowerCase()))
                    .toArray(Produto[]::new);
        }
        return new Produto[0];
    }

    public Produto getProdutoByCodigo(String codigo){
        return Arrays.stream(this.produtos)
                .filter(iterator -> iterator != null)
                .filter(iterator -> iterator.getCodigo().equals(codigo))
                .findFirst()
                .orElse(null);
    }

    public Produto[] getProdutos() {
        return produtos;
    }

    public boolean salvarProdutos(){
        try {
            CSVUtils.salvarEmCsv("produtos.csv", "descricao,codigo,preco,qtd_estoque,categoria", this.produtos);
            return true;
        } catch (RuntimeException e) {
            System.out.println("Erro ao salvar produtos, tente novamente.");
            return false;
        }
    }
    public boolean lerProdutos(){
        try {
            ArrayList<String> arrayProdutos =  CSVUtils.lerCSV("produtos.csv");
            ArrayList<Produto> meusProdutos = new ArrayList<>();

            arrayProdutos.forEach(linha -> {
                String[] valores = linha.split(",");
                this.cadastrarProduto(new Produto(valores[0], valores[1], Double.parseDouble(valores[2]), Double.parseDouble(valores[3]), Categoria.valueOf(valores[4])), true);
            });
            return true;
        } catch (RuntimeException e) {
            System.out.println("Erro ao ler produtos!");
            return false;
        }
    }
}
