package model.venda;

import java.util.*;

public class HistoricoVendas {
    private Map<String, Venda> vendas;
    private int quantidadeVendas;

    public HistoricoVendas(){
        this.vendas = new HashMap<String, Venda>();
        this.quantidadeVendas = 0;
    }

    public void adicionarVenda(Venda venda){
        this.vendas.put(venda.getCodigo(), venda);
    }

    public Collection<Venda> getHistoricoVendas(){
        return this.vendas.values();
    }

    }

