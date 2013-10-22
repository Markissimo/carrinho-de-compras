package br.calebe.exemplos.ex01;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {
    
    private static int counterPedido=0;
    private int pedido;
    private List<Produto> produtos;

    public Carrinho() {
        produtos = new ArrayList<>();
        pedido=counterPedido;
        counterPedido++;
        
    }

    public void add(Produto produto) {
        produtos.add(produto);
    }

    public Produto menorProduto() throws CarrinhoVazioExpected {
        if (produtos.isEmpty()) {
            throw new CarrinhoVazioExpected();
        }
        Produto menor = produtos.get(0);
        for (Produto produto : produtos) {
            if (produto.getPreco() < menor.getPreco()) {
                menor = produto;
            }
        }
        return menor;
    }
    
    public Produto maiorProduto() throws CarrinhoVazioExpected {
        if (produtos.isEmpty()) {
            throw new CarrinhoVazioExpected();
        }
        Produto maior = produtos.get(0);
        for (Produto produto : produtos) {
            if (produto.getPreco() > maior.getPreco()) {
                maior = produto;
            }
        }
        return maior;
    }
    
    public List<Produto> getListaProdutos(){
        return produtos;
    }
    
    public double totalCarrinho(){
        if(produtos.isEmpty()) return 0.0;
        double total = 0.0;
        for(Produto produto : produtos){
            total += produto.getPreco();
        }
        return total;
    }
    
    public int getPedido(){
        return this.pedido;
    }
    public static void resetCounterPedido(){
        counterPedido = 0;
    }
    public void removeProduto(Produto prodVelho)throws CarrinhoVazioExpected{
        if(produtos.isEmpty()) throw new CarrinhoVazioExpected();
        produtos.remove(prodVelho);
    }
    
}
