package controller;

import model.produto.Categoria;

public abstract class IsCategoriaValida {
    public static Categoria test(String categoria){
        try{
            return Categoria.valueOf(categoria.toUpperCase());
        }catch (IllegalArgumentException e){
            return null;
        }
    }
}
