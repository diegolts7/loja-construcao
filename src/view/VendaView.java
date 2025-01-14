package view;

import model.item.Item;
import model.venda.Venda;

import javax.swing.*;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Objects;
import java.util.Scanner;

public class VendaView {
    private static Scanner myScanner = new Scanner(System.in);
    public static String imprimirVenda(Venda venda){
        Collection<Item> myItens = venda.getItens();
        StringBuilder resumoVenda = new StringBuilder();

        if (!myItens.isEmpty()) {
            resumoVenda.append("ITENS DA VENDA\n\n");
            myItens.stream()
                    .filter(Objects::nonNull)
                                    .forEach(item -> {
                                        resumoVenda.append(String.format(
                                                "Código do item: %s\n%s\nQuantidade do produto: %.2f\nSubtotal do item: %.2f R$\n\n",
                                                item.getCodigo(),
                                                item.getProduto().retornarProduto(),
                                                item.getQuantidade(),
                                                item.getSubtotal()
                                        ));
                                    });


            resumoVenda.append(String.format("Total da venda: %.2f R$\n", venda.getTotal()));
            resumoVenda.append("Data da venda: ")
                    .append(venda.getDataHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")))
                    .append("\n");
        } else {
            resumoVenda.append("Você não possui itens registrados na venda");
        }

        return resumoVenda.toString();
    }
    public static void mostrarComprovante(String nome, String conteudo){
        String faixa = "************************************************************************************************";
        String conteudoComFaixa = faixa + "\n" + faixa + "\n\n" + conteudo + "\n\n" + faixa + "\n" + faixa;
        JOptionPane.showMessageDialog(null,conteudoComFaixa ,nome ,  JOptionPane.INFORMATION_MESSAGE);
    }

    public static int menuVenda(){
        System.out.println("1. Adicionar item na venda\n2. Remover item da venda\nQualquer outro numero - " +
                "Finalizar venda");
        System.out.print("Escolha uma opção: ");
        return myScanner.nextInt();
    }

    public static String nomeProduto(){
        System.out.println("Digite o nome do produto que deseja adicionar");
        return myScanner.nextLine();
    }
}
