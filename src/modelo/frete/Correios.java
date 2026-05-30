package modelo.frete;

public class Correios implements IFrete {


    public String getNome() {
        return "Correios";
    }

    public double calcularFrete(double valorVenda) {
        if (valorVenda > 300) {
            return 0;
        } else if (valorVenda > 200){
            return 5;
        }
            return 10;
        }
}
