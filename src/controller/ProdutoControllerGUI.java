package controller;

import javax.swing.*;
import model.Categoria;
import model.HistoricoProdutos;
import model.Produto;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProdutoControllerGUI {

    public static void registrarProduto(HistoricoProdutos historicoProdutos){
        // Criar a janela de cadastro de produto
        JFrame cadastroFrame = new JFrame("Cadastrar Produto");
        cadastroFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Abrir a janela em tela cheia
        cadastroFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Layout do painel
        JPanel cadastroPanel = new JPanel();
        cadastroPanel.setLayout(new GridLayout(5, 2));

        // Campos para entrada de dados
        JLabel descricaoLabel = new JLabel("Descrição:");
        JTextField descricaoField = new JTextField();
        descricaoField.setPreferredSize(new Dimension(50, 15)); // Caixa de texto menor

        JLabel precoLabel = new JLabel("Preço:");
        JTextField precoField = new JTextField();
        precoField.setPreferredSize(new Dimension(50, 15)); // Caixa de texto menor

        JLabel qtdEstoqueLabel = new JLabel("Quantidade em Estoque:");
        JTextField qtdEstoqueField = new JTextField();
        qtdEstoqueField.setPreferredSize(new Dimension(50, 15)); // Caixa de texto menor

        JLabel categoriaLabel = new JLabel("Categoria:");
        JComboBox<Categoria> categoriaComboBox = new JComboBox<>(Categoria.values());

        // Estilo dos rótulos
        descricaoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        precoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        qtdEstoqueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        categoriaLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Botão para cadastrar o produto
        JButton cadastrarButton = new JButton("Cadastrar Produto");

        // Estilizando o botão
        Color buttonColor = new Color(0, 153, 204);  // Azul para o botão
        Font buttonFont = new Font("Arial", Font.PLAIN, 20);  // Fonte do botão
        cadastrarButton.setBackground(buttonColor);
        cadastrarButton.setForeground(Color.WHITE);
        cadastrarButton.setFont(buttonFont);
        cadastrarButton.setPreferredSize(new Dimension(50, 15));  // Tamanho reduzido para o botão
        cadastrarButton.setFocusPainted(false);  // Remover o foco do botão

        // Adicionar os componentes ao painel
        cadastroPanel.add(descricaoLabel);
        cadastroPanel.add(descricaoField);
        cadastroPanel.add(precoLabel);
        cadastroPanel.add(precoField);
        cadastroPanel.add(qtdEstoqueLabel);
        cadastroPanel.add(qtdEstoqueField);
        cadastroPanel.add(categoriaLabel);
        cadastroPanel.add(categoriaComboBox);
        cadastroPanel.add(new JLabel()); // Para preencher o grid
        cadastroPanel.add(cadastrarButton);

        // Adicionar painel à janela
        cadastroFrame.add(cadastroPanel);
        cadastroFrame.setVisible(true);

        // Ação do botão cadastrar
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String descricao = descricaoField.getText().trim();
                if (descricao.isEmpty()) {
                    JOptionPane.showMessageDialog(cadastroFrame, "Descrição inválida!");
                    return;
                }

                double preco = 0;
                try {
                    preco = Double.parseDouble(precoField.getText().trim());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(cadastroFrame, "Preço inválido!");
                    return;
                }

                double qtdEstoque = 0;
                try {
                    qtdEstoque = Double.parseDouble(qtdEstoqueField.getText().trim());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(cadastroFrame, "Quantidade inválida!");
                    return;
                }

                Categoria categoria = (Categoria) categoriaComboBox.getSelectedItem();
                Produto produto = new Produto(gerarCodigo(), descricao, preco, qtdEstoque, categoria);

                historicoProdutos.cadastrarProduto(produto, false);
                JOptionPane.showMessageDialog(cadastroFrame, "Produto cadastrado com sucesso!");
                cadastroFrame.dispose();
            }
        });
    }

    public static String gerarCodigo() {
        return String.valueOf(System.currentTimeMillis());
    }
}
