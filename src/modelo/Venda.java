package modelo;

import modelo.frete.IFrete;
import modelo.pagamento.FormaPagamento;

public class Venda {

    private double valorTotal;
    private String cliente;
    private FormaPagamento formaPagamento;
    private IFrete frete;

    public Venda (String nomeCliente){
        this.cliente = nomeCliente;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public IFrete getFrete() {
        return frete;
    }

    public void setFrete(IFrete frete) {
        this.frete = frete;
    }
}
