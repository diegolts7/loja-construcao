package view;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class InterfaceVenda {

    public static void main(String[] args) {
        // Criação do frame principal
        JFrame frame = new JFrame("Tela de vendas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Tela cheia
        frame.setLayout(new BorderLayout());

        // Painel principal
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Painel superior com título
        JLabel title = new JLabel("Ponto de Vendas", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setForeground(Color.WHITE);
        title.setOpaque(true);
        title.setBackground(new Color(0, 102, 204));
        title.setPreferredSize(new Dimension(0, 80));
        mainPanel.add(title, BorderLayout.NORTH);

        // Painel central com dados e carrinho
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Painel Dados do Cliente
        JPanel clientPanel = new JPanel(new GridBagLayout());
        clientPanel.setBorder(new TitledBorder("Dados do Cliente"));
        GridBagConstraints clientGbc = new GridBagConstraints();
        clientGbc.insets = new Insets(5, 5, 5, 5);
        clientGbc.fill = GridBagConstraints.HORIZONTAL;

        clientGbc.gridx = 0; clientGbc.gridy = 0;
        clientPanel.add(new JLabel("CPF:"), clientGbc);
        clientGbc.gridx = 1; clientPanel.add(new JTextField(15), clientGbc);

        clientGbc.gridx = 0; clientGbc.gridy = 1;
        clientPanel.add(new JLabel("Nome:"), clientGbc);
        clientGbc.gridx = 1; clientPanel.add(new JTextField(15), clientGbc);

        clientGbc.gridx = 0; clientGbc.gridy = 2;
        clientPanel.add(new JLabel("Data:"), clientGbc);
        clientGbc.gridx = 1; clientPanel.add(new JTextField(15), clientGbc);

        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1; gbc.weighty = 1; // Proporção vertical ajustada
        gbc.fill = GridBagConstraints.BOTH;
        centerPanel.add(clientPanel, gbc);

        // Painel Dados do Produto
        JPanel productPanel = new JPanel(new GridBagLayout());
        productPanel.setBorder(new TitledBorder("Dados do Produto"));
        GridBagConstraints productGbc = new GridBagConstraints();
        productGbc.insets = new Insets(5, 5, 5, 5);
        productGbc.fill = GridBagConstraints.HORIZONTAL;

        productGbc.gridx = 0; productGbc.gridy = 0;
        productPanel.add(new JLabel("Código:"), productGbc);
        productGbc.gridx = 1; productPanel.add(new JTextField(15), productGbc);

        productGbc.gridx = 0; productGbc.gridy = 1;
        productPanel.add(new JLabel("Produto:"), productGbc);
        productGbc.gridx = 1; productPanel.add(new JTextField(15), productGbc);

        productGbc.gridx = 0; productGbc.gridy = 2;
        productPanel.add(new JLabel("Preço:"), productGbc);
        productGbc.gridx = 1; productPanel.add(new JTextField(15), productGbc);

        productGbc.gridx = 0; productGbc.gridy = 3;
        productPanel.add(new JLabel("Qtd:"), productGbc);
        productGbc.gridx = 1; productPanel.add(new JTextField(15), productGbc);

        // Botão Adicionar Item
        JButton addButton = new JButton("Adicionar Item");
        addButton.setPreferredSize(new Dimension(150, 40));
        productGbc.gridx = 0; productGbc.gridy = 4; productGbc.gridwidth = 2;
        productGbc.anchor = GridBagConstraints.CENTER;
        productPanel.add(addButton, productGbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 1; gbc.weighty = 1; // Proporção vertical ajustada
        gbc.fill = GridBagConstraints.BOTH;
        centerPanel.add(productPanel, gbc);

        // Painel Carrinho de Compras
        JPanel cartPanel = new JPanel(new BorderLayout());
        cartPanel.setBorder(new TitledBorder("Carrinho de compras"));

        String[] columnNames = {"Código", "Produto", "Qtd", "Preço", "Subtotal"};
        Object[][] data = {
                {"6", "Bolacha", "10", "6.0", "60.0"},
                {"4", "Caneca Star Wars", "5", "23.0", "115.0"}
        };
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);

        scrollPane.setPreferredSize(new Dimension(0, 0));
        cartPanel.add(scrollPane, BorderLayout.CENTER);

        gbc.gridx = 1; gbc.gridy = 0; gbc.gridheight = 2; gbc.weightx = 2; gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        centerPanel.add(cartPanel, gbc);

        // Painel Total da Venda
        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalPanel.setBorder(new TitledBorder("Total da Venda"));
        totalPanel.add(new JLabel("TOTAL DA VENDA:"));
        JTextField totalField = new JTextField("175.0", 10);
        totalField.setHorizontalAlignment(JTextField.CENTER);
        totalField.setFont(new Font("Arial", Font.BOLD, 30));
        totalField.setEditable(false);
        totalPanel.add(totalField);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2; gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(totalPanel, gbc);

        // Painel Botões
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton paymentButton = new JButton("PAGAMENTO");
        JButton cancelButton = new JButton("CANCELAR VENDA");
        paymentButton.setPreferredSize(new Dimension(150, 40));
        cancelButton.setPreferredSize(new Dimension(150, 40));
        buttonPanel.add(paymentButton);
        buttonPanel.add(cancelButton);

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Adiciona painel principal ao frame
        frame.add(mainPanel);
        frame.setVisible(true);
    }
}
