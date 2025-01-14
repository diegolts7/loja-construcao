package view;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Painel Dados do Cliente
        JPanel clientPanel = new JPanel(new GridBagLayout());
        clientPanel.setBorder(new TitledBorder("Dados do Cliente"));
        GridBagConstraints clientGbc = new GridBagConstraints();
        clientGbc.insets = new Insets(10, 10, 10, 10); // Padding maior entre os elementos
        clientGbc.fill = GridBagConstraints.HORIZONTAL;
        clientGbc.weightx = 1;

        JTextField cpfField = new JTextField(20);
        cpfField.setPreferredSize(new Dimension(0, 30)); // Aumenta a altura do input

        clientGbc.gridx = 0; clientGbc.gridy = 0;
        clientPanel.add(new JLabel("CPF:"), clientGbc);
        clientGbc.gridy = 1;
        clientPanel.add(cpfField, clientGbc);

        JTextField nameField = new JTextField(20);
        nameField.setPreferredSize(new Dimension(0, 30));

        clientGbc.gridx = 0; clientGbc.gridy = 2;
        clientPanel.add(new JLabel("Nome:"), clientGbc);
        clientGbc.gridy = 3;
        clientPanel.add(nameField, clientGbc);



        JLabel dateField = new JLabel(LocalDate.now().toString());
        dateField.setPreferredSize(new Dimension(0, 30));

        clientGbc.gridx = 0; clientGbc.gridy = 4;
        clientPanel.add(new JLabel("Data:"), clientGbc);
        clientGbc.gridy = 5;
        clientPanel.add(dateField, clientGbc);

        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1; gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        centerPanel.add(clientPanel, gbc);

// Painel Dados do Produto
        JPanel productPanel = new JPanel(new GridBagLayout());
        productPanel.setBorder(new TitledBorder("Dados do Produto"));
        GridBagConstraints productGbc = new GridBagConstraints();
        productGbc.insets = new Insets(10, 10, 10, 10);
        productGbc.fill = GridBagConstraints.HORIZONTAL;
        productGbc.weightx = 1;

        JTextField codeField = new JTextField(20);
        codeField.setPreferredSize(new Dimension(0, 30));

        productGbc.gridx = 0; productGbc.gridy = 0;
        productPanel.add(new JLabel("Código:"), productGbc);
        productGbc.gridx = 1;
        productPanel.add(codeField, productGbc);

        JTextField productField = new JTextField(20);
        productField.setPreferredSize(new Dimension(0, 30));

        productGbc.gridx = 0; productGbc.gridy = 1;
        productPanel.add(new JLabel("Produto:"), productGbc);
        productGbc.gridx = 1;
        productPanel.add(productField, productGbc);

        JTextField priceField = new JTextField(20);
        priceField.setPreferredSize(new Dimension(0, 30));

        productGbc.gridx = 0; productGbc.gridy = 2;
        productPanel.add(new JLabel("Preço:"), productGbc);
        productGbc.gridx = 1;
        productPanel.add(priceField, productGbc);

        JTextField qtdField = new JTextField(20);
        qtdField.setPreferredSize(new Dimension(0, 30));

        productGbc.gridx = 0; productGbc.gridy = 3;
        productPanel.add(new JLabel("Qtd:"), productGbc);
        productGbc.gridx = 1;
        productPanel.add(qtdField, productGbc);

// Botão Adicionar Item
        JButton addButton = new JButton("Adicionar Item");
        addButton.setPreferredSize(new Dimension(0, 40)); // Aumenta altura do botão
        productGbc.gridx = 0; productGbc.gridy = 4; productGbc.gridwidth = 1;
        productGbc.fill = GridBagConstraints.HORIZONTAL;
        productPanel.add(addButton, productGbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 1; gbc.weighty = 1;
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
        table.setRowHeight(40);
        table.setSelectionBackground(new Color(200, 230, 255));
        table.setFont(new Font("Arial", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(table);

        scrollPane.setPreferredSize(new Dimension(0, 0));
        cartPanel.add(scrollPane, BorderLayout.CENTER);

        gbc.gridx = 1; gbc.gridy = 0; gbc.gridheight = 2; gbc.weightx = 4; gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        centerPanel.add(cartPanel, gbc);

        // Painel Total da Venda
        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalPanel.setBorder(new TitledBorder("Total da Venda"));
        totalPanel.add(new JLabel("TOTAL DA VENDA:"));
        JTextField totalField = new JTextField("175.0", 10);
        totalField.setHorizontalAlignment(JTextField.CENTER);
        totalField.setFont(new Font("Arial", Font.BOLD, 50));
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
