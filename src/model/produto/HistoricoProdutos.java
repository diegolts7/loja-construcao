package model.produto;

import model.service.codigoGenerete.GenereteWithCodeSave;
import model.service.lerDados.LerDados;
import model.service.salvarDados.SalvarDados;

import java.util.*;

public class HistoricoProdutos {
    private final Map<String, Produto> produtos;
    private final SalvarDados salvarDados;
    private final LerDados lerDados;

    public HistoricoProdutos(SalvarDados salvarDados, LerDados lerDados){
        this.produtos = new HashMap<String, Produto>();
        this.salvarDados = salvarDados;
        this.lerDados = lerDados;
        this.lerProdutos();
    }

    public void cadastrarProduto(Produto produto){
        this.produtos.put(produto.getCodigo(), produto);
    }

    public Collection<Produto> pesquisarProdutos(String nome){
        return this.produtos.values().stream()
                .filter(produto -> produto.getDescricao().toLowerCase().contains(nome.toLowerCase()))
                .toList();
    }

    public Produto getProdutoByCodigo(String codigo){
        return this.produtos.get(codigo);
    }

    public Collection<Produto> getProdutos() {
        return this.produtos.values();
    }

    public void salvarProdutos(){
        this.salvarDados.save("produtos.csv", "descricao,codigo,preco,qtd_estoque,categoria", this.produtos.values());
    }

    public void lerProdutos(){
            ArrayList<String> arrayProdutos =  this.lerDados.ler("produtos.csv");

            arrayProdutos.forEach(linha -> {
                String[] valores = linha.split(",");
                this.cadastrarProduto(new Produto(new GenereteWithCodeSave(valores[1]), valores[0], Double.parseDouble(valores[2]), Double.parseDouble(valores[3]), Categoria.valueOf(valores[4])));
            });
    }
}
