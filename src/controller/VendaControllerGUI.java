package controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import model.*;
import controller.VendaController;
import view.VendaView;

public class VendaControllerGUI {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private HistoricoVendas historicoVendas;
    private HistoricoProdutos historicoProdutos;
    private Venda venda;

    public VendaControllerGUI(HistoricoVendas historicoVendas, HistoricoProdutos historicoProdutos) {
        this.historicoVendas = historicoVendas;
        this.historicoProdutos = historicoProdutos;
        this.venda = new Venda();

        // Configurar a janela principal
        frame = new JFrame("Registrar Venda - Construções LTDA");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Tabela para exibir os itens da venda
        tableModel = new DefaultTableModel(new Object[]{"Código", "Nome", "Quantidade", "Preço"}, 0);
        table = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(table);

        // Painel para os botões e formulários
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));

        // Campos de entrada
        JTextField nomeProdutoField = new JTextField();
        JTextField codigoProdutoField = new JTextField();
        JTextField quantidadeField = new JTextField();

        // Botões
        JButton buscarProdutoButton = new JButton("Buscar Produto");
        JButton adicionarItemButton = new JButton("Adicionar Item");
        JButton removerItemButton = new JButton("Remover Item");
        JButton finalizarVendaButton = new JButton("Finalizar Venda");

        // Adicionar componentes ao painel
        panel.add(new JLabel("Nome do Produto:"));
        panel.add(nomeProdutoField);
        panel.add(buscarProdutoButton);

        panel.add(new JLabel("Código do Produto:"));
        panel.add(codigoProdutoField);
        panel.add(adicionarItemButton);

        panel.add(new JLabel("Quantidade:"));
        panel.add(quantidadeField);

        // Adicionar botões e tabela à janela
        frame.add(tableScroll, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.NORTH);
        frame.add(finalizarVendaButton, BorderLayout.SOUTH);

        // Ações dos botões
        buscarProdutoButton.addActionListener(e -> {
            String nomeProduto = nomeProdutoField.getText();
            Produto[] produtos = historicoProdutos.pesquisarProdutos(nomeProduto);
            if (produtos.length > 0) {
                StringBuilder sb = new StringBuilder("Produtos encontrados:\n");
                for (Produto p : produtos) {
                    sb.append("Código: ").append(p.getCodigo()).append(", Nome: ").append(p.getDescricao()).append("\n");
                }
                JOptionPane.showMessageDialog(frame, sb.toString());
            } else {
                JOptionPane.showMessageDialog(frame, "Nenhum produto encontrado.");
            }
        });

        adicionarItemButton.addActionListener(e -> {
            String codigo = codigoProdutoField.getText();
            String quantidadeStr = quantidadeField.getText();

            try {
                double quantidade = Double.parseDouble(quantidadeStr);
                Produto produto = historicoProdutos.getProdutoByCodigo(codigo);

                if (produto != null && quantidade > 0 && quantidade <= produto.getQtdEstoque()) {
                    produto.setQtdEstoque(produto.getQtdEstoque() - quantidade);
                    Item item = new Item(produto, quantidade);
                    venda.adicionarItem(item);

                    // Atualizar tabela
                    tableModel.addRow(new Object[]{produto.getCodigo(), produto.getDescricao(), quantidade, produto.getPreco()});
                } else {
                    JOptionPane.showMessageDialog(frame, "Quantidade inválida ou produto não encontrado.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Quantidade inválida.");
            }
        });

        removerItemButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                String codigo = (String) tableModel.getValueAt(selectedRow, 0);
                venda.removerItem(Integer.parseInt(codigo));
                tableModel.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(frame, "Nenhum item selecionado para remoção.");
            }
        });

        finalizarVendaButton.addActionListener(e -> {
            if (!ArraysUtils.isEmpty(venda.getItens())) {
                historicoVendas.adicionarVenda(venda);
                String comprovante = VendaView.imprimirVenda(venda);
                JOptionPane.showMessageDialog(frame, "Venda finalizada!\n" + comprovante);

                // Limpar a venda
                venda = new Venda();
                tableModel.setRowCount(0);
            } else {
                JOptionPane.showMessageDialog(frame, "Nenhum item na venda.");
            }
        });

        // Exibir a janela
        frame.setVisible(true);
    }

}
