package model.item;

import model.produto.Produto;
import model.service.codigoGenerete.CodigoGenerator;

public class Item {
    private final String codigo;
    private Produto produto;
    private double quantidade;

    public Item(CodigoGenerator adapter, Produto produto, double quantidade) {
        this.codigo = adapter.gerarCodigo();
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public String getCodigo() {
        return codigo;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade += quantidade;
    }

    public double getSubtotal(){
        return produto.getPreco() * quantidade;
    }

}
