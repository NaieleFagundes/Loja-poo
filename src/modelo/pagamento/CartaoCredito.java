package modelo.pagamento;

public class CartaoCredito extends FormaPagamento {
    private int numParcelas;

    public CartaoCredito (int numParcelas){
        super ("Cartão de Crédito"); //smp primeiro
        this.numParcelas = numParcelas;
    }

    public int getNumParcelas() {
        return numParcelas;
    }

    public void setNumParcelas(int numParcelas) {
        this.numParcelas = numParcelas;
    }

    public double getTaxa(){ //sem parâmetro
        if (this.numParcelas > 3){
            return 5;
        } //sem else por causa de return
            return 0;
        }
}