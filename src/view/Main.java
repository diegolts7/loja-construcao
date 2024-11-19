package view;

import model.Categoria;
import model.Item;
import model.Produto;
import model.Venda;

public class Main {
    public static void main(String[] args) {
        Venda venda = new Venda();
        venda.adicionarItem(
                new Item(1,
                    new Produto(1, "Arroz",
                        6.20, Categoria.ALIMENTICIO),
                3));
        System.out.println(venda.getTotal());
    }
}