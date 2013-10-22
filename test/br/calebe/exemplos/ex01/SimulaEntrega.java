/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.calebe.exemplos.ex01;

/**
 *
 * @author 31007465
 */
public class SimulaEntrega {
    private IEntrega engrega;
    private SituacaoEntrega situacao;
    
    public void setEntrega(IEntrega engrega){
        this.engrega = engrega;
    }
    
    public void setSituacaoEntrega(SituacaoEntrega situacao){
        this.situacao = situacao;
    }
    
     public SituacaoEntrega verificaSituacao(int pedido) {
        if (engrega == null) {
            throw new RuntimeException("Sem Método de Entrega definido");
        }
        return engrega.getSituacaoEntrega(pedido);
    }
}
