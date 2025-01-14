package model.venda;

import model.item.Item;
import model.produto.Produto;
import model.service.codigoGenerete.CodigoGenerator;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Venda {

    private final String codigo;
    private Map<String, Item> itens;
    private LocalDateTime dataHora;

    public Venda(CodigoGenerator adapter){
        this.codigo = adapter.gerarCodigo();
        itens = new HashMap<String, Item>();
        dataHora = LocalDateTime.now();
    }

    public double getTotal(){
        double total = 0;
        for (Item item : this.itens.values()){
            total += item.getSubtotal();
        }
        return total;
    }

    public void adicionarItem(Item item){
            this.itens.put(item.getCodigo(), item);
    }
    public Item itemExist(String codigoProduto){
        Item item = null;

        for (Map.Entry<String, Item> entry : this.itens.entrySet()){
            if (entry.getValue().getProduto().getCodigo().equals(codigoProduto)){
                item = entry.getValue();
            }
        }
        return item;

    }

    public boolean removerItem(String codigoItem){
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

    public String getCodigo() {
        return codigo;
    }
}
