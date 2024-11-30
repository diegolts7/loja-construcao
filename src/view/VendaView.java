package view;

import controller.ArraysUtils;
import model.Item;
import model.Venda;

import javax.swing.*;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class VendaView {
    public static String imprimirVenda(Venda venda){
        Item[] myItens = venda.getItens();
        StringBuilder resumoVenda = new StringBuilder();

        if (!ArraysUtils.isEmpty(myItens)) {
            resumoVenda.append("ITENS DA VENDA\n\n");
            Arrays.stream(myItens)
                            .filter(item -> item != null)
                                    .forEach(item -> {
                                        resumoVenda.append(String.format(
                                                "Código do item: %d\n%s\nQuantidade do produto: %.2f\nSubtotal do item: %.2f R$\n\n",
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
}
