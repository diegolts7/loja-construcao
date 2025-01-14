package controller;

import model.item.Item;
import model.produto.HistoricoProdutos;
import model.produto.Produto;
import model.service.codigoGenerete.GenereteWithDateAndRandom;
import model.venda.HistoricoVendas;
import model.venda.Venda;
import view.ProdutoView;
import view.VendaView;

import java.util.Collection;
import java.util.Scanner;

public abstract   class VendaController {
    private static Scanner myScanner = new Scanner(System.in);
    public static void registrarVenda(HistoricoVendas historicoVendas, HistoricoProdutos historicoProdutos) {
        boolean parada = false;
        Venda venda = new Venda(new GenereteWithDateAndRandom());
        do {

            System.out.println("1. Adicionar item na venda\n2. Remover item da venda\nQualquer outro numero - " +
                    "Finalizar venda");
            System.out.print("Escolha uma opção: ");
            int opcaoVenda = myScanner.nextInt();
            myScanner.nextLine();


            switch (opcaoVenda) {
                case 1:
                    System.out.println("Digite o nome do produto que deseja adicionar");
                    String nome = myScanner.nextLine();
                    Produto produto;

                    Collection<Produto> produtosFiltro = historicoProdutos.pesquisarProdutos(nome);

                    if (!produtosFiltro.isEmpty()){
                        ProdutoView.imprimirProdutos(produtosFiltro);
                        System.out.println("Digite o codigo do produto que quer adicionar: ");
                        String codigo = myScanner.nextLine();
                        produto = historicoProdutos.getProdutoByCodigo(codigo);

                        while (produto == null){
                            System.out.println("Codigo de produto invalido, digite um codigo valido: ");
                            codigo = myScanner.nextLine();
                            produto = historicoProdutos.getProdutoByCodigo(codigo);
                        }
                    }else {
                        System.out.println("Nenhum produto encontrado!\n");
                        continue;
                    }

                    System.out.println("Informe a quantidade: ");
                    double quantidade = myScanner.nextDouble();

                    while (quantidade < 1 || quantidade > produto.getQtdEstoque()){
                        System.out.println("Informe uma quantidade valida: ");
                        quantidade = myScanner.nextDouble();
                    }

                    myScanner.nextLine();


                    try{
                        Item itemExistente = venda.itemExist(produto.getCodigo());

                        if (itemExistente == null){
                            Item item = new Item(new GenereteWithDateAndRandom(),produto, quantidade);
                            venda.adicionarItem(item);
                        }else {
                            itemExistente.setQuantidade(1);
                        }

                        produto.setQtdEstoque(-quantidade);
                        historicoProdutos.salvarProdutos();
                        System.out.println(VendaView.imprimirVenda(venda));
                    } catch (RuntimeException e) {
                        produto.setQtdEstoque(+quantidade);
                        System.out.println("Erro ao adicionar item, tente novamente");
                    }
                    break;

                case 2:
                    System.out.println("Informe o código do item a ser removido: ");
                    String codigo = myScanner.nextLine();
                    myScanner.nextLine();
                   if (!venda.removerItem(codigo)){
                       System.out.println("Erro ao remover item\n");
                   }else {
                       System.out.println("Item removido com sucesso\n");
                   }
                    break;

                default:
                    parada = true;
                    System.out.println("Mostrar comprovante da venda? (y/n):");
                    char letra = myScanner.next().charAt(0);
                    if (Character.toLowerCase(letra) == 'y'){
                        VendaView.mostrarComprovante("Comprovante Venda", VendaView.imprimirVenda(venda));
                    }
            }
        } while (!parada);

        if (!venda.getItens().isEmpty()){
            historicoVendas.adicionarVenda(venda);
        }
    }



}
