package modelo.frete;

public class Transportadora implements IFrete {

    public String getNome() {
        return "Jadlog";
    }

    public double calcularFrete(double valorVenda) {
        return 13;// valor fixo
    }
}
