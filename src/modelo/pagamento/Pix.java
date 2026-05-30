package modelo.pagamento;

public class Pix extends FormaPagamento {


    public Pix (){
        super ("PIX");
    }


    public double getTaxa(){
        return -10; //10% de desconto
    }
}
