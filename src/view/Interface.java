package view;

import javax.swing.*;
import java.awt.*;
import controller.ProdutoController;
import controller.VendaControllerGUI;
import model.HistoricoProdutos;
import model.HistoricoVendas;

public class Interface {

    public static void main(String[] args) {
        // Configurações principais da janela
        JFrame frame = new JFrame("PV Miudezas - Construções LTDA");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Layout principal para trocar entre os "painéis" (páginas)
        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        // Instâncias dos históricos
        HistoricoVendas historicoVendas = new HistoricoVendas();
        HistoricoProdutos historicoProdutos = new HistoricoProdutos();
        historicoProdutos.lerProdutos();

        // Painel inicial (menu principal)
        JPanel menuPanel = new JPanel(new BorderLayout());

        // Título no topo
        JLabel titleLabel = new JLabel("PV Miudezas - Construções LTDA", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 102, 204));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        // Painel para botões centralizados
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(new Color(255, 239, 213));  // Cor pastel de fundo
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);

        // Botões do menu principal
        JButton vendaButton = new JButton("Fazer Venda");
        JButton cadastrarProdutoButton = new JButton("Cadastrar Produto");
        JButton historicoVendasButton = new JButton("Histórico de Vendas");
        JButton historicoProdutosButton = new JButton("Histórico de Produtos");
        JButton pedidoButton = new JButton("Fazer Pedido ao Fornecedor");
        JButton sairButton = new JButton("Sair");

        // Estilizar os botões
        Color buttonColor = new Color(0, 153, 204);  // Azul mais bonito para os botões
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);  // Fonte dos botões

        // Função para estilizar os botões de forma consistente
        JButton[] buttons = {vendaButton, cadastrarProdutoButton, historicoVendasButton,
                historicoProdutosButton, pedidoButton, sairButton};
        for (JButton button : buttons) {
            button.setBackground(buttonColor);
            button.setForeground(Color.WHITE);
            button.setFont(buttonFont);
            button.setPreferredSize(new Dimension(250, 50));  // Tamanho fixo para todos os botões
            button.setFocusPainted(false);  // Remove o contorno quando o botão está focado
        }

        // Adicionar os botões ao painel central
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(vendaButton, gbc);
        gbc.gridy++;
        buttonPanel.add(cadastrarProdutoButton, gbc);
        gbc.gridy++;
        buttonPanel.add(historicoVendasButton, gbc);
        gbc.gridy++;
        buttonPanel.add(historicoProdutosButton, gbc);
        gbc.gridy++;
        buttonPanel.add(pedidoButton, gbc);
        gbc.gridy++;
        buttonPanel.add(sairButton, gbc);

        // Adicionar título e painel central ao menu
        menuPanel.add(titleLabel, BorderLayout.NORTH);
        menuPanel.add(buttonPanel, BorderLayout.CENTER);

        // Painel de venda
        VendaControllerGUI vendasControllerGUI = new VendaControllerGUI(historicoVendas, historicoProdutos);

        // Painel para histórico de vendas
        JPanel historicoVendasPanel = new JPanel(new BorderLayout());
        JTextArea vendasTextArea = new JTextArea();
        vendasTextArea.setEditable(false);
        historicoVendasPanel.add(new JScrollPane(vendasTextArea), BorderLayout.CENTER);

        // Painel para histórico de produtos
        JPanel historicoProdutosPanel = new JPanel(new BorderLayout());
        JTextArea produtosTextArea = new JTextArea();
        produtosTextArea.setEditable(false);
        historicoProdutosPanel.add(new JScrollPane(produtosTextArea), BorderLayout.CENTER);

        // Adicionar todos os painéis ao CardLayout
        mainPanel.add(menuPanel, "Menu");
        mainPanel.add(vendasControllerGUI.createVendaPanel(cardLayout, mainPanel), "Venda");
        mainPanel.add(historicoVendasPanel, "HistoricoVendas");
        mainPanel.add(historicoProdutosPanel, "HistoricoProdutos");

        frame.add(mainPanel);

        // Ações dos botões para alternar entre as páginas
        vendaButton.addActionListener(e -> cardLayout.show(mainPanel, "Venda"));
        cadastrarProdutoButton.addActionListener(e -> ProdutoController.registrarProduto(historicoProdutos));
        historicoVendasButton.addActionListener(e -> cardLayout.show(mainPanel, "HistoricoVendas"));
        historicoProdutosButton.addActionListener(e -> cardLayout.show(mainPanel, "HistoricoProdutos"));
        pedidoButton.addActionListener(e -> ProdutoController.fazerPedido(historicoProdutos));
        sairButton.addActionListener(e -> System.exit(0));

        // Exibir a janela inicial
        frame.setVisible(true);
    }
}
