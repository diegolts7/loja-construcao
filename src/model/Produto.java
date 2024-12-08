package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Produto {
    private final String  codigo;
    private String descricao;
    //Visibilidade de pacote (package) ou default
    double preco;
    double qtdEstoque;
    private Categoria categoria;

    public Produto(String codigo,String descricao, double preco, double quantidade ,Categoria categoria) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.preco = preco;
        this.qtdEstoque = quantidade < 0 ? 0 : quantidade;
        this.categoria = categoria;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public double getQtdEstoque() {
        return qtdEstoque;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setQtdEstoque(double qtdEstoque) {
        this.qtdEstoque += qtdEstoque;
    }

    public String retornarProduto(){
        return "Produto: {codigo: " + this.codigo + ", " + "descrição: " + this.descricao + ", " + "preço: " + this.preco + " R$" + ", " + "qtd_estoque: " + this.qtdEstoque + ", " + "categoria: " + this.categoria + "}";
    }

    @Override
    public String toString() {
        return  descricao +
                "," + codigo +
                "," + preco +
                "," + qtdEstoque +
                "," + categoria;
    }
}