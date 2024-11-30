package model;

import controller.ArraysUtils;
import controller.CSVUtils;
import view.VendaView;

import java.util.Arrays;
import java.util.Objects;

public class HistoricoVendas {
    private Venda[] vendas;
    private int quantidadeVendas;

    public HistoricoVendas(){
        this.vendas = new Venda[3];
        this.quantidadeVendas = 0;
    }

    public boolean adicionarVenda(Venda venda){
        if(this.quantidadeVendas == this.vendas.length){
            this.vendas = Arrays.copyOf(this.vendas, this.vendas.length+3);
        }
        this.vendas[this.quantidadeVendas++] = venda;
        return true;
    }

    public void imprimirHistoricoVendas(){
        if (!ArraysUtils.isEmpty(vendas)){
            System.out.println("HISTORICO VENDAS:\n");
            Arrays.stream(this.vendas)
                            .filter(Objects::nonNull)
                                    .forEach(venda -> System.out.println(VendaView.imprimirVenda(venda)));

            }
    }

    }

