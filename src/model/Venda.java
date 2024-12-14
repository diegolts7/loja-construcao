package model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Venda {

    private Map<Integer, Item> itens;
    private LocalDateTime dataHora;

    public Venda(){
        itens = new HashMap<Integer, Item>();
        dataHora = LocalDateTime.now();
    }

    public double getTotal(){
        double total = 0;
        for (Item item : this.itens.values()){
            total += item.getSubtotal();
        }
        return total;
    }

    public boolean adicionarItem(Item item){
        this.itens.put(item.getCodigo(), item);
        return true;
    }

    public boolean removerItem(int codigoItem){
        if (this.itens.get(codigoItem) != null){
            this.itens.remove(codigoItem);
            return true;
        }
        return false;
    }

    public Collection<Item> getItens(){
        return this.itens.values();
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }
}
