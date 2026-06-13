package modelo.frete;

public class TransportadoraRedeSul implements IFrete{

    public String getNome (){
        return "Rede Sul";
    }

    @Override
    public double calcularFrete(double valorVenda) {
        if (valorVenda <= 100){
            return 30;
        }
        else if (valorVenda > 100 && valorVenda <= 150){
            return 15;
        }
        return 0;
    }
}
