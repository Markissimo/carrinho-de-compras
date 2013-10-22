/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.calebe.exemplos.ex01;

/**
 *
 * @author 31007465
 */
public class SimulaPagamento {
    private IPagamento pagamento;
    private StatusPagamento statusPagamento;
    
    public void setPagamento(IPagamento pagamento){
        this.pagamento = pagamento;
    }
    
    public void setStatusPagamento(StatusPagamento status){
        this.statusPagamento = status;
    }
    
     public StatusPagamento paga(double valor) {
        if (pagamento == null) {
            throw new RuntimeException("Sem Método de pagamento definido");
        }

        return pagamento.FazPagamento(valor);
    }
}
