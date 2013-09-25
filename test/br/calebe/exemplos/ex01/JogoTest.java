/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.calebe.exemplos.ex01;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 31007465
 */
public class JogoTest {
    
    public JogoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void comparaJogosIguais(){
        Jogo gta = new Jogo("GTA V",200.00);
        Jogo gtaTeste = new Jogo("GTA V",200.00);
        Assert.assertEquals(gta,gtaTeste);
    }
    
    @Test
    public void comparaProdutoJogoIguais(){
        Jogo gta = new Jogo("GTA V",200.00);
        Produto gtaTeste = new Produto("GTA V",200.00);
        Assert.assertEquals(gta,gtaTeste);
    }
    
    @Test
    public void comparaJogosDiferentes(){
        Jogo gta = new Jogo("GTA V",200.00);
        Jogo cod = new Jogo("COD: Ghosts",200.00);
        Assert.assertFalse(gta.equals(cod));
    }
}