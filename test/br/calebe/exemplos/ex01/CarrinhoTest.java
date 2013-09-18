package br.calebe.exemplos.ex01;

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
    public void confereSoma() throws CarrinhoVazioExpected{
        Produto produto = new Produto("Produto",50.00);
        Produto produtoCaro = new Produto("ProdutoCaro",75.00);
        carrinho.add(produto);
        carrinho.add(produtoCaro);
        Assert.assertEquals(produtoCaro, carrinho.maiorProduto());
        
    }
    
    @Test
    public void confereRemocao()throws CarrinhoVazioExpected{
        //TODO
    }
    
}
