package br.calebe.exemplos.ex01;

import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CarrinhoTest {

    private Carrinho carrinho;
    private IPagamento pagamento;
    private SimulaPagamento simuladorPagamento;
    private IEntrega entrega;
    private SimulaEntrega simuladorEntrega;

    @Before
    public void criandoCarrinho() {
        carrinho = new Carrinho();
        pagamento = createNiceMock(IPagamento.class);
        simuladorPagamento = new SimulaPagamento();
        
        entrega = createNiceMock(IEntrega.class);
        simuladorEntrega = new SimulaEntrega();
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
    
    @Test
    public void pagaCarrinhoAprovado() {
        carrinho.add(new Produto("Teste",1000.0));
        carrinho.add(new Produto("Teste2",2000.0));
        expect(pagamento.FazPagamento(3000.0)).andReturn(StatusPagamento.APROVADO);
        replay(pagamento);
        simuladorPagamento.setPagamento(pagamento);

        simuladorPagamento.setStatusPagamento(StatusPagamento.APROVADO);
        Assert.assertEquals(StatusPagamento.APROVADO, simuladorPagamento.paga(carrinho.totalCarrinho()));
    }
    
    @Test
    public void pagaCarrinhoNegado() {
        carrinho.add(new Produto("Teste",1000.0));
        carrinho.add(new Produto("Teste2",2000.0));
        expect(pagamento.FazPagamento(2000.0)).andReturn(StatusPagamento.NEGADO);
        replay(pagamento);
        simuladorPagamento.setPagamento(pagamento);

        Assert.assertEquals(StatusPagamento.NEGADO, simuladorPagamento.paga(carrinho.totalCarrinho()-1000));
    }
    
    @Test
    public void retornaEntregaPedido(){
        Carrinho.resetCounterPedido();
        carrinho = new Carrinho();
        Carrinho carrinho2 = new Carrinho();
        Carrinho carrinho3 = new Carrinho();
        Carrinho carrinho4 = new Carrinho();
        Carrinho carrinho5 = new Carrinho();
        
        expect(entrega.getSituacaoEntrega(0)).andReturn(SituacaoEntrega.AGUARDANDO_PAGAMENTO);
        expect(entrega.getSituacaoEntrega(1)).andReturn(SituacaoEntrega.EMPACOTANDO);
        expect(entrega.getSituacaoEntrega(2)).andReturn(SituacaoEntrega.ENTREGUE);
        expect(entrega.getSituacaoEntrega(3)).andReturn(SituacaoEntrega.ENVIADO);
        expect(entrega.getSituacaoEntrega(4)).andReturn(SituacaoEntrega.PAGO);
        replay(entrega);
        simuladorEntrega.setEntrega(entrega);

        Assert.assertEquals(SituacaoEntrega.AGUARDANDO_PAGAMENTO, simuladorEntrega.verificaSituacao(carrinho.getPedido()));
        Assert.assertEquals(SituacaoEntrega.EMPACOTANDO, simuladorEntrega.verificaSituacao(carrinho2.getPedido()));
        Assert.assertEquals(SituacaoEntrega.ENTREGUE, simuladorEntrega.verificaSituacao(carrinho3.getPedido()));
        Assert.assertEquals(SituacaoEntrega.ENVIADO, simuladorEntrega.verificaSituacao(carrinho4.getPedido()));
        Assert.assertEquals(SituacaoEntrega.PAGO, simuladorEntrega.verificaSituacao(carrinho5.getPedido()));
        
    }
}
