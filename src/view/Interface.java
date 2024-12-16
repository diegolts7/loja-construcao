package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import controller.ProdutoControllerGUI;
import controller.VendaControllerGUI;
import model.HistoricoProdutos;
import model.HistoricoVendas;

public class Interface {

    public static void main(String[] args) {
        // Configurações principais da janela
        JFrame frame = new JFrame("PV Miudezas para Construção");

        // Maximizar a janela ao iniciar (tela cheia)
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Layout principal para trocar entre os "painéis" (páginas)
        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        // Instâncias dos históricos
        HistoricoVendas historicoVendas = new HistoricoVendas();
        HistoricoProdutos historicoProdutos = new HistoricoProdutos();
        historicoProdutos.lerProdutos();

        // Painel inicial (menu principal)
        JPanel menuPanel = new JPanel(new BorderLayout()) {
            // Sobrescrever o método paintComponent para adicionar a imagem de fundo
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    // Carregar a imagem de fundo
                    BufferedImage backgroundImage = ImageIO.read(new File("src/img/background.jpg")); // Coloque o caminho correto da sua imagem
                    // Redimensionar para se ajustar ao tamanho do painel
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
                    // Definir a opacidade
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f)); // 0.3f é a opacidade (ajuste conforme necessário)
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        // Carregar a logo
        ImageIcon logoIcon = new ImageIcon("src/img/logo.png"); // Caminho para a logo
        Image img = logoIcon.getImage();
        Image scaledImg = img.getScaledInstance(230, 130, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(scaledImg);

        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        logoLabel.setVerticalAlignment(SwingConstants.CENTER);

        // Painel para exibir a logo
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(new Color(255, 239, 213)); // Mesma cor de fundo para consistência
        logoPanel.add(logoLabel);

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
            button.setPreferredSize(new Dimension(350, 60));  // Aumentando o tamanho dos botões
            button.setFocusPainted(false);  // Remove o contorno quando o botão está focado
        }

        // Adicionar os botões ao painel central
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(vendaButton, gbc);
        gbc.gridx++;
        buttonPanel.add(cadastrarProdutoButton, gbc);
        gbc.gridx++;
        buttonPanel.add(historicoVendasButton, gbc);
        gbc.gridx++;
        buttonPanel.add(historicoProdutosButton, gbc);
        gbc.gridx++;
        buttonPanel.add(pedidoButton, gbc);
        gbc.gridx++;
        buttonPanel.add(sairButton, gbc);

        // Adicionar a logo e painel central ao menu
        menuPanel.add(logoPanel, BorderLayout.NORTH);  // Logo substitui o título
        menuPanel.add(buttonPanel, BorderLayout.CENTER);

        // Painel de venda
        VendaControllerGUI vendasControllerGUI = new VendaControllerGUI(historicoVendas, historicoProdutos);

        //Painel para cadastrar produto

        ProdutoControllerGUI produtoControllerGUI = new ProdutoControllerGUI(historicoProdutos);

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
        mainPanel.add(produtoControllerGUI.createPanelCadastrarProduto(), "CadastrarProduto");
        mainPanel.add(historicoVendasPanel, "HistoricoVendas");
        mainPanel.add(historicoProdutosPanel, "HistoricoProdutos");

        frame.add(mainPanel);

        // Ações dos botões para alternar entre as páginas
        vendaButton.addActionListener(e -> cardLayout.show(mainPanel, "Venda"));

        // Ação para o botão "Cadastrar Produto"
        cadastrarProdutoButton.addActionListener(e -> cardLayout.show(mainPanel, "CadastrarProduto"));
        historicoVendasButton.addActionListener(e -> cardLayout.show(mainPanel, "HistoricoVendas"));
        historicoProdutosButton.addActionListener(e -> cardLayout.show(mainPanel, "HistoricoProdutos"));
        //pedidoButton.addActionListener(e -> ProdutoController.fazerPedido(historicoProdutos));
        sairButton.addActionListener(e -> System.exit(0));

        // Exibir a janela inicial
        frame.setVisible(true);
    }
}
