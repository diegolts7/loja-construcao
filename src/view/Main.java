package view;

import controller.CSVUtils;
import controller.ProdutoController;
import controller.VendaController;
import model.*;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    private static Scanner myScanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean parada = false;
        HistoricoVendas historicoVendas = new HistoricoVendas();

        HistoricoProdutos historicoProdutos = new HistoricoProdutos();
        historicoProdutos.lerProdutos();
        /*historicoProdutos.cadastrarProduto(new Produto(ProdutoController.gerarCodigo(),"arroz",5.67, 20,Categoria.ALIMENTICIO), false);
        historicoProdutos.cadastrarProduto(new Produto(ProdutoController.gerarCodigo(),"arroz parborizado",5.20, 30,Categoria.ALIMENTICIO), false);
*/


        do {
            System.out.println("\n\n#################################################################\nBEM VINDO AO MERCADINHO SANTA LUZIA, SUA COMPRA É NOSSA ALEGRIA!!! \uD83D\uDE0A\n#################################################################\n\n1. Fazer venda\n2. Cadastrar produto\n3. Historico de vendas\n4. Historico de produtos\n0. Sair\n");
            int escolha = myScanner.nextInt();
            switch (escolha){
                case 1 : VendaController.registrarVenda(historicoVendas, historicoProdutos); break;
                case 2 :
                    ProdutoController.registrarProduto(historicoProdutos); break;
                case 3 : historicoVendas.imprimirHistoricoVendas(); break;
                case 4 : ProdutoView.imprimirProdutos(historicoProdutos.getProdutos()); break;
                case 0 : parada = true; break;
                default: continue;
            }
        }while(!parada);
    }

}