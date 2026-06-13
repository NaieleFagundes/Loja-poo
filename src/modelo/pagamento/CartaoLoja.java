package modelo.pagamento;

public class CartaoLoja extends FormaPagamento{
    private int numParcelas;

    public CartaoLoja (int numParcelas){
        super ("Cartão da Loja");
        this.numParcelas = numParcelas;
    }

    public int getNumParcelas() {
        return numParcelas;
    }

    public void setNumParcelas(int numParcelas) {
        this.numParcelas = numParcelas;
    }

    public double processarPagamento (double valorVenda){//desconto dependendo do valor do produto
        //ao usar o cartão da loja
        if (valorVenda >= 300){
            return valorVenda - (valorVenda * 0.20);
        } else if (valorVenda >=200){
            return valorVenda - (valorVenda * 0.10);
        } else if (valorVenda >= 100){
            return valorVenda - (valorVenda * 0.05);
        }
        return valorVenda;
    }


    @Override
    public double getTaxa() {
        return 0;//sem taxa de acréscimo por usar o cartão da loja
    }
}
