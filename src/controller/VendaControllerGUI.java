package controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import model.item.Item;
import model.produto.HistoricoProdutos;
import model.produto.Produto;
import model.service.codigoGenerete.GenereteWithDateAndRandom;
import model.venda.HistoricoVendas;
import model.venda.Venda;
import view.VendaView;

public class VendaControllerGUI {
    private JTable table;
    private DefaultTableModel tableModel;
    private HistoricoVendas historicoVendas;
    private HistoricoProdutos historicoProdutos;
    private Venda venda;

    public VendaControllerGUI(HistoricoVendas historicoVendas, HistoricoProdutos historicoProdutos) {
        this.historicoVendas = historicoVendas;
        this.historicoProdutos = historicoProdutos;
        this.venda = new Venda(new GenereteWithDateAndRandom());
    }

    public JPanel createVendaPanel(CardLayout cardLayout, JPanel mainPanel) {
        JPanel vendaPanel = new JPanel(new BorderLayout());
        vendaPanel.setBackground(new Color(245, 245, 245)); // Cor de fundo suave

        // Tabela para exibir os itens da venda
        tableModel = new DefaultTableModel(new Object[]{"Código", "Nome", "Quantidade", "Preço", "Subtotal"}, 0);
        table = new JTable(tableModel);
        table.setRowHeight(40);  // Tamanho maior das linhas da tabela
        table.setFont(new Font("Arial", Font.PLAIN, 16));  // Fonte maior para os dados
        table.setSelectionBackground(new Color(200, 230, 255)); // Cor de fundo para seleção
        JScrollPane tableScroll = new JScrollPane(table);

        // Painel para os botões e formulários
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(192, 188, 182));  // Cor pastel de fundo
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Campo de quantidade
        JTextField quantidadeField = new JTextField();

        // JComboBox para selecionar produto
        JComboBox<Produto> produtoComboBox = new JComboBox<>();
        // Preencher o JComboBox com os produtos disponíveis
        for (Produto p : historicoProdutos.getProdutos()) {
            produtoComboBox.addItem(p);  // Adiciona o produto completo ao combo
        }

        // Botões
        JButton adicionarItemButton = new JButton("Adicionar Item");
        JButton finalizarVendaButton = new JButton("Finalizar Venda");
        JButton voltarButton = new JButton("Voltar ao Menu");

        // Estilizando os botões
        Color buttonColor = new Color(0, 153, 204);  // Azul para os botões
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);  // Fonte dos botões
        JButton[] buttons = {adicionarItemButton, finalizarVendaButton, voltarButton};
        for (JButton button : buttons) {
            button.setBackground(buttonColor);
            button.setForeground(Color.WHITE);
            button.setFont(buttonFont);
            button.setPreferredSize(new Dimension(200, 50));  // Tamanho fixo para todos os botões
            button.setFocusPainted(false);  // Remover o foco dos botões
        }

        // Adicionar componentes ao painel
        gbc.gridx = 0;
        gbc.weightx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Selecione o Produto:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        panel.add(produtoComboBox, gbc);

        gbc.gridx = 2;
        gbc.weightx = 0;
        panel.add(new JLabel("Quantidade:"), gbc);

        gbc.gridx = 3;
        gbc.weightx = 1.0;
        panel.add(quantidadeField, gbc);

        gbc.gridx = 4;
        gbc.weightx = 0;
        panel.add(adicionarItemButton, gbc);

        // Adicionar botões e tabela à janela
        vendaPanel.add(tableScroll, BorderLayout.CENTER);
        vendaPanel.add(panel, BorderLayout.NORTH);
        vendaPanel.add(finalizarVendaButton, BorderLayout.SOUTH);

        // Ações dos botões
        adicionarItemButton.addActionListener(e -> {
            Produto produtoSelecionado = (Produto) produtoComboBox.getSelectedItem(); // Pega o produto completo selecionado
            String quantidadeStr = quantidadeField.getText();

            try {
                double quantidade = Double.parseDouble(quantidadeStr);
                if (produtoSelecionado != null && quantidade > 0 && quantidade <= produtoSelecionado.getQtdEstoque()) {
                    produtoSelecionado.setQtdEstoque(-quantidade);
                    Item item = new Item(new GenereteWithDateAndRandom(),produtoSelecionado, quantidade);
                    venda.adicionarItem(item);

                    // Atualizar tabela
                    tableModel.addRow(new Object[]{produtoSelecionado.getCodigo(), produtoSelecionado.getDescricao(), quantidade, produtoSelecionado.getPreco(), item.getSubtotal()});
                } else {
                    JOptionPane.showMessageDialog(vendaPanel, "Quantidade inválida ou produto não encontrado.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vendaPanel, "Quantidade inválida.");
            }
        });

        finalizarVendaButton.addActionListener(e -> {
            if (!venda.getItens().isEmpty()) {
                historicoVendas.adicionarVenda(venda);
                String comprovante = VendaView.imprimirVenda(venda);
                JOptionPane.showMessageDialog(vendaPanel, "Venda finalizada!\n" + comprovante);

                // Limpar a venda
                venda = new Venda(new GenereteWithDateAndRandom());
                tableModel.setRowCount(0);
            } else {
                JOptionPane.showMessageDialog(vendaPanel, "Nenhum item na venda.");
            }
        });

        voltarButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        vendaPanel.add(voltarButton, BorderLayout.SOUTH);

        return vendaPanel;
    }
}
