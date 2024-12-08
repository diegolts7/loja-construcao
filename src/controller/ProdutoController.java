package controller;

import model.Categoria;
import model.HistoricoProdutos;
import model.Produto;
import view.ProdutoView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;

public class ProdutoController {
    private static Scanner myScanner = new Scanner(System.in);
    public static void registrarProduto(HistoricoProdutos historicoProdutos){
        String descricao = solicitarDescricaoValida();

        System.out.println("Informe o preço do produto: ");
        double preco = myScanner.nextDouble();

        myScanner.nextLine();

        System.out.println("Informe a quantidade do produto em estoque: ");
        double qtdEstoque = myScanner.nextDouble();

        myScanner.nextLine();

        Categoria categoria = solicitarCategoriaValida();

        Produto produto = new Produto(gerarCodigo(),descricao, preco, qtdEstoque,categoria);

        historicoProdutos.cadastrarProduto(produto, false);
    }
    private static String solicitarDescricaoValida() {
        System.out.println("Informe a descrição do produto: ");
        String descricao = myScanner.nextLine();
        while (descricao.trim().isEmpty()) {
            System.out.println("Descrição inválida! Tente novamente: ");
            descricao = myScanner.nextLine();
        }
        return descricao;
    }
    private static Categoria solicitarCategoriaValida(){
        System.out.println("Informe a categoria do produto: ");
        for (Categoria ctgr : Categoria.values()) {
            System.out.println("- " + ctgr);
        }

        String categoriaStr = myScanner.nextLine();
        Categoria categoria = IsCategoriaValida.test(categoriaStr);

        while (categoria == null){
            System.out.println("Informe uma categoria correta: ");
            for (Categoria ctgr : Categoria.values()) {
                System.out.println("- " + ctgr);
            }

            categoriaStr = myScanner.nextLine();
            categoria = IsCategoriaValida.test(categoriaStr);
        }
        return categoria;
    }
    public static String gerarCodigo(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyHHmmssSSS") ;
        return  LocalDateTime.now().format(formatter) + new Random().nextInt(100);
    }

    public static void fazerPedido(HistoricoProdutos historicoProdutos){
        ProdutoView.imprimirProdutos(historicoProdutos.getProdutos());
        System.out.println("Qual o produto você deseja comprar? ");
        String idItemCompra = myScanner.nextLine();
        Produto produtoPedido = historicoProdutos.getProdutoByCodigo(idItemCompra);

        while (produtoPedido == null){
            System.out.println("Digite um id de produto valido: ");
            idItemCompra = myScanner.nextLine();
        }

        System.out.println("Qual quantidade desse produto deseja? ");
        double quantidade = myScanner.nextDouble();
        myScanner.nextLine();

        while (quantidade < 1){
            System.out.println("Digite uma quantidade valida: ");
            quantidade = myScanner.nextDouble();
            myScanner.nextLine();
        }

        produtoPedido.setQtdEstoque(quantidade);
        if (!historicoProdutos.salvarProdutos()){
            produtoPedido.setQtdEstoque(-quantidade);
        }
    }


}