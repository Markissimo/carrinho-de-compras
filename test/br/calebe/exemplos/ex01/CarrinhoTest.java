package br.calebe.exemplos.ex01;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CarrinhoTest {

    private Carrinho carrinho;

    @Before
    public void criandoCarrinho() {
        carrinho = new Carrinho();
    }

    @Test(expected = CarrinhoVazioExpected.class)
    public void colocandoZeroProduto() throws CarrinhoVazioExpected {
        Produto menor;
        menor = carrinho.menorProduto();
        Assert.assertEquals(null, menor);
    }

    @Test
    public void colocandoUmProduto() throws CarrinhoVazioExpected {
        Produto livro = new Produto("Java em 24 horas", 50.00);
        carrinho.add(livro);
        Produto menor;
        menor = carrinho.menorProduto();
        Assert.assertEquals(livro, menor);
    }

    @Test
    public void colocandoMaisProdutos() throws CarrinhoVazioExpected {
        Produto livro = new Produto("Java em 24 horas", 50.00);
        carrinho.add(livro);
        Produto deitel = new Produto("Java: como programar", 150.00);
        carrinho.add(deitel);
        Produto menor;
        menor = carrinho.menorProduto();
        Assert.assertEquals(livro, menor);
    }
    
    @Test
    public void identidadeDeProdutos() throws CarrinhoVazioExpected {
        Produto original = new Produto("Java em 24 horas", 50.00);
        carrinho.add(original);
        Produto copia = new Produto("Java em 24 horas", 50.00);
        original = carrinho.menorProduto();
        Assert.assertEquals(original, copia);
    }
    
    @Test
    public void comparaMaior() throws CarrinhoVazioExpected{
        Produto produto = new Produto("Produto",50.00);
        Produto produtoCaro = new Produto("ProdutoCaro",75.00);
        carrinho.add(produto);
        carrinho.add(produtoCaro);
        Assert.assertEquals(produtoCaro, carrinho.maiorProduto());
        
    }
    
    @Test
    public void comparaSoma(){
        Produto produto = new Produto("Produto Simples",50.00);
        Produto produtoComposto = new Produto("Produto Composto",100.00);
        carrinho.add(produto);
        carrinho.add(produtoComposto);
        double soma = produto.getPreco() + produtoComposto.getPreco();
        Assert.assertEquals(soma, carrinho.totalCarrinho(),0.01);
    }
            
    @Test
    public void confereRemocao() throws CarrinhoVazioExpected{
        Produto produto = new Produto("Produto Normal",50.00);
        Produto produtoASerRemovido = new Produto("Produto Removido",100.00);
        carrinho.add(produto);
        carrinho.add(produtoASerRemovido);
        carrinho.removeProduto(produtoASerRemovido);
        boolean isRemoved = true; 
        for(Produto p : carrinho.getListaProdutos()){
            if(p.equals(produtoASerRemovido)){
               isRemoved = false;
            }
        }
        Assert.assertTrue(isRemoved);
    }
    
    @Test
    public void confereListaCarrinho(){
        Produto produto = new Produto("Produto 1",20.00);
        Produto produto2 = new Produto("Produto 2",30.00);
        carrinho.add(produto);
        carrinho.add(produto2);
        List<Produto> lsListaRetornada = carrinho.getListaProdutos();
        boolean isOk = true;
        for(Produto p : lsListaRetornada){    
            if(!(p.equals(produto)||p.equals(produto2))){
                isOk = false;
            }
        }  
        Assert.assertTrue(isOk);
    }
    
    @Test
    public void adicionaJogo() throws CarrinhoVazioExpected{
        Jogo jogo = new Jogo("GTA V", 200.00);
        carrinho.add(jogo);
        Jogo teste = (Jogo)carrinho.menorProduto();
        Assert.assertEquals(jogo, teste);
    }
    
}
