package view;

import javax.swing.*;
import java.awt.*;

public class InterfaceMenu {

    public static void main(String[] args) {
        // Cria o JFrame principal
        JFrame frame = new JFrame("PV Miudezas para Construção");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        frame.setLayout(new BorderLayout());

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(1, 7, 10, 10));
        menuPanel.setBackground(new Color(0, 102, 204));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Botões do menu superior com tamanho ajustado
        JButton btnClientes = new JButton("Clientes");
        btnClientes.setPreferredSize(new Dimension(280, 50));
        btnClientes.setIcon(new ImageIcon("src/img/cliente.png"));
        JButton btnFuncionarios = new JButton("Funcionários");
        btnFuncionarios.setPreferredSize(new Dimension(280, 50));
        btnFuncionarios.setIcon(new ImageIcon("src/img/funcionario.png"));
        JButton btnFornecedores = new JButton("Fornecedores");
        btnFornecedores.setPreferredSize(new Dimension(280, 50));
        btnFornecedores.setIcon(new ImageIcon("src/img/fornecedores.png"));
        JButton btnProdutos = new JButton("Produtos");
        btnProdutos.setPreferredSize(new Dimension(280, 50));
        btnProdutos.setIcon(new ImageIcon("src/img/produtos.png"));
        JButton btnVendas = new JButton("Vendas");
        btnVendas.setPreferredSize(new Dimension(280, 50));
        btnVendas.setIcon(new ImageIcon("src/img/vendas.png"));
        JButton btnSair = new JButton("Sair");
        btnSair.setPreferredSize(new Dimension(280, 50));
        btnSair.setIcon(new ImageIcon("src/img/sair.png"));

        // Adiciona botões ao painel
        menuPanel.add(btnClientes);
        menuPanel.add(btnFuncionarios);
        menuPanel.add(btnFornecedores);
        menuPanel.add(btnProdutos);
        menuPanel.add(btnVendas);
        menuPanel.add(btnSair);

        // ====== PAINEL CENTRAL COM IMAGEM PLACEHOLDER ======
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());

        // Espaço para imagem (substitua depois)
        ImageIcon image = new ImageIcon("src/img/imagem.png");
        JLabel imageLabel = new JLabel(image, SwingConstants.CENTER);
        imageLabel.setFont(new Font("Arial", Font.BOLD, 20));
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        centerPanel.add(imageLabel, BorderLayout.CENTER);

        // ====== PAINEL DO RODAPÉ ======
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(0, 102, 204));
        JLabel footerLabel = new JLabel("© 2024 PV Miudezas para Construção", JLabel.CENTER);
        footerLabel.setForeground(Color.WHITE);
        footerPanel.add(footerLabel);

        // ====== ADICIONA PAINÉIS AO FRAME ======
        frame.add(menuPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(footerPanel, BorderLayout.SOUTH);

        // Torna visível o JFrame
        frame.setVisible(true);

        // ====== EVENTOS DOS BOTÕES ======
        btnSair.addActionListener(e -> System.exit(0));
        btnClientes.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Clientes selecionado."));
        btnFuncionarios.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Funcionários selecionado."));
        btnFornecedores.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Fornecedores selecionado."));
        btnProdutos.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Produtos selecionado."));
        btnVendas.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Vendas selecionado."));
    }
}
