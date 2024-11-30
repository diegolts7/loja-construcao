package model;

public class Item {
    private static int auxCodigo;
    private final int codigo;
    private Produto produto;
    private double quantidade;

    public Item(Produto produto, double quantidade) {
        this.codigo = auxCodigo++;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public int getCodigo() {
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
        this.quantidade = quantidade;
    }

    public double getSubtotal(){
        return produto.preco*quantidade;
    }

}
