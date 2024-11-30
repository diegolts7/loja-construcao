package view;

import model.Produto;

import java.util.Arrays;

public class ProdutoView {
    public static void imprimirProdutos(Produto[] produtos){
        Arrays.stream(produtos)
                        .filter(produto -> produto != null)
                                .forEach(produto -> {
                                    System.out.println(produto.retornarProduto());
                                });


    }

}
