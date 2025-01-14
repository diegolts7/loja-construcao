package view;

import model.venda.Venda;

import java.util.Collection;

final public class HistoricoVendasView {
    public static void imprimirVendas(Collection<Venda> vendas){
        vendas.forEach((venda)-> {
            System.out.println(VendaView.imprimirVenda(venda));
        });
    }
}
