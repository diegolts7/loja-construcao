package view;

import javax.swing.*;
import java.awt.*;
import controller.ProdutoController;
import controller.VendaController;
import controller.VendaControllerGUI;
import model.HistoricoProdutos;
import model.HistoricoVendas;
import view.ProdutoView;

public class Interface {

    public static void main(String[] args) {
        // Configurações principais da janela
        JFrame frame = new JFrame("Construções LTDA");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(6, 1, 10, 10));

        // Instâncias dos históricos
        HistoricoVendas historicoVendas = new HistoricoVendas();
        HistoricoProdutos historicoProdutos = new HistoricoProdutos();
        historicoProdutos.lerProdutos();

        // Criar botões para cada funcionalidade
        JButton vendaButton = new JButton("Fazer Venda");
        JButton cadastrarProdutoButton = new JButton("Cadastrar Produto");
        JButton historicoVendasButton = new JButton("Histórico de Vendas");
        JButton historicoProdutosButton = new JButton("Histórico de Produtos");
        JButton pedidoButton = new JButton("Fazer Pedido ao Fornecedor");
        JButton sairButton = new JButton("Sair");

        // Adicionar ações aos botões
        vendaButton.addActionListener(e ->
                new VendaControllerGUI(historicoVendas, historicoProdutos)  
        );

        cadastrarProdutoButton.addActionListener(e ->
                ProdutoController.registrarProduto(historicoProdutos)
        );

        historicoVendasButton.addActionListener(e ->
                historicoVendas.imprimirHistoricoVendas()
        );

        historicoProdutosButton.addActionListener(e ->
                ProdutoView.imprimirProdutos(historicoProdutos.getProdutos())
        );

        pedidoButton.addActionListener(e ->
                ProdutoController.fazerPedido(historicoProdutos)
        );

        sairButton.addActionListener(e ->
                System.exit(0)
        );

        // Adicionar os botões ao frame
        frame.add(vendaButton);
        frame.add(cadastrarProdutoButton);
        frame.add(historicoVendasButton);
        frame.add(historicoProdutosButton);
        frame.add(pedidoButton);
        frame.add(sairButton);

        // Exibir a janela
        frame.setVisible(true);
    }
}
