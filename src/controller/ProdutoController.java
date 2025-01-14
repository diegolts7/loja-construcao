package controller;

import model.produto.Categoria;
import model.produto.HistoricoProdutos;
import model.produto.Produto;
import model.service.codigoGenerete.GenereteWithDateAndRandom;
import view.ProdutoView;

public class ProdutoController {
    public static void registrarProduto(HistoricoProdutos historicoProdutos){
        String descricao = ProdutoView.solicitarDescricao();
        double preco = ProdutoView.solicitarPreco();
        double qtdEstoque = ProdutoView.solicitarQuantidade();
        String categoriaStr = ProdutoView.solicitarCategoria();

        Categoria categoria = IsCategoriaValida.test(categoriaStr);
        if (categoria == null) {
            throw new IllegalArgumentException("Categoria inválida");
        }

        historicoProdutos.cadastrarProduto(new Produto(new GenereteWithDateAndRandom(), descricao, preco, qtdEstoque, categoria));
    }

    public static void fazerPedido(HistoricoProdutos historicoProdutos) {
        ProdutoView.imprimirProdutos(historicoProdutos.getProdutos());
        String idItemCompra = ProdutoView.solicitarIdProduto();
        Produto produtoPedido = historicoProdutos.getProdutoByCodigo(idItemCompra);

        if (produtoPedido == null) {
            throw new IllegalArgumentException("Produto não encontrado");
        }

        double quantidade = ProdutoView.solicitarQuantidadeCompra();
        if (quantidade < 1) {
            throw new IllegalArgumentException("Quantidade inválida");
        }
        
        try {
            produtoPedido.setQtdEstoque(quantidade);
            historicoProdutos.salvarProdutos();
        } catch (RuntimeException e) {
            produtoPedido.setQtdEstoque(-quantidade);
        }
    }
}