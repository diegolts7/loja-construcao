package view;

import model.produto.Produto;

import java.util.Collection;
import java.util.Scanner;

public class ProdutoView {
    private static Scanner myScanner = new Scanner(System.in);
    public static void imprimirProdutos(Collection<Produto> produtos){
        produtos.forEach(produto -> {
            System.out.println(produto.retornarProduto());
        });
    }

    public static String solicitarDescricao() {
        System.out.println("Informe a descrição do produto: ");
        return myScanner.nextLine();
    }

    public static double solicitarPreco() {
        System.out.println("Informe o preço do produto: ");
        return myScanner.nextDouble();
    }

    public static double solicitarQuantidade() {
        System.out.println("Informe a quantidade do produto em estoque: ");
        return myScanner.nextDouble();
    }

    public static String solicitarCategoria() {
        System.out.println("Informe a categoria do produto: ");
        return myScanner.nextLine();
    }

    public static String solicitarIdProduto() {
        System.out.println("Qual o produto você deseja comprar? ");
        return myScanner.nextLine();
    }

    public static double solicitarQuantidadeCompra() {
        System.out.println("Qual quantidade desse produto deseja? ");
        return myScanner.nextDouble();
    }

}
