package modelo;

public class ItemVenda {
    private Venda venda;
    private Produto produto;
    private int quantidade;
    private double valor;

    public ItemVenda (Venda venda, Produto produto, int quantidade) {
        this.venda = venda;
        this.produto = produto;
        this.quantidade = quantidade;
        this.valor = produto.getPreco(); //estratégia para manter estoque
    }

    /**
     * Retorna o valor total do item da venda, multiplicando a quantidade vezes o valor
     * do item
     * @return
     */
    public double getValorTotalItem (){
        return this.quantidade * this.valor;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }


}
