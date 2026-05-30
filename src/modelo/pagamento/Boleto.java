package modelo.pagamento;

public class Boleto extends FormaPagamento {


    public Boleto(double taxaFixa) {
        super ("Boleto");
    }

    public double getValorAdicional (){
        return 2.5;//retorna valor fixo, sobreescrendo o método da classe mãe e SÓ para essa classe
    }

    public double getTaxa (){
        return 0;
    }
}
