package model.fornecedor;

import model.produto.Categoria;

public class Fornecedor {
    private final String cnpj;
    private String telefone;
    private String nome;
    private Categoria fornece_para;

    public Fornecedor(String telefone, String cnpj, String nome, Categoria fornece_para) {
        this.telefone = telefone;
        this.cnpj = cnpj;
        this.nome = nome;
        this.fornece_para = fornece_para;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Categoria getFornece_para() {
        return fornece_para;
    }

    public void setFornece_para(Categoria fornece_para) {
        this.fornece_para = fornece_para;
    }
}
