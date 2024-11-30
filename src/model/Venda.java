package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Venda {

    private Item[] itens;
    private LocalDateTime dataHora;
    private int quantidadeItens;

    public Venda(){
        itens = new Item[3];
        dataHora = LocalDateTime.now();
        quantidadeItens = 0;
    }

    public double getTotal(){
        double total = 0;
        for(int i=0;i<quantidadeItens; i++){
            total += itens[i].getSubtotal();
        }
        return total;
    }

    public boolean adicionarItem(Item item){
        if(quantidadeItens == itens.length){
            itens = Arrays.copyOf(itens, itens.length+3);
        }
        itens[quantidadeItens++] = item;
        return true;
    }

    public boolean removerItem(int codigoItem){
        for (int i=0;i<this.quantidadeItens;i++){
            if (this.itens[i].getCodigo() == codigoItem){
                if(i < (this.itens.length -1)){
                    this.itens[i] = itens[this.quantidadeItens -1];
                    this.itens[this.quantidadeItens - 1] = null;
                }else{
                    this.itens[i] = null;
                }
                this.quantidadeItens--;
                return true;
            }
        }
        return false;
    }

    public Item[] getItens(){
        return Arrays.copyOf(itens, quantidadeItens);
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public int getQuantidadeItens() {
        return quantidadeItens;
    }

    public void setQuantidadeItens(int quantidadeItens) {
        this.quantidadeItens = quantidadeItens;
    }
}
