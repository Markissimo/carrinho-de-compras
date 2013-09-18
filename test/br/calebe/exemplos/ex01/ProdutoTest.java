/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.calebe.exemplos.ex01;

import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author 31007465
 */
public class ProdutoTest {
    
    private Produto prodTest;
    
    public ProdutoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        prodTest = new Produto("Produto Teste", 15.5);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void comparandoProdutoIgualComEquals(){
        Produto prod = new Produto("Produto Teste", 15.5);
        Assert.assertTrue(prod.equals(prodTest));
    }
    
    @Test
    public void comparandoProdutoDiferenteComEquals(){
        Produto prod = new Produto("Produto",10.5);
        Assert.assertFalse(prod.equals(prodTest));
    }
    
    @Test(expected = ClassCastException.class)
    public void comparandoProdutoComObjeto(){
        Object test = "teste";
        prodTest.equals(test);
        Assert.fail();
    }
    
    public void comparandoProdutoComProdutoObject(){
        Object test = new Produto("Produto Teste", 15.5);
        Assert.assertEquals(prodTest, test);
    }
}