package modelo.frete;

public class Correios implements IFrete {
    private String nome  = "Correios";

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
