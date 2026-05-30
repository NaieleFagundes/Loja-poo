package modelo.pagamento;


public abstract class FormaPagamento {
    private String nome;// do pagamento

    public FormaPagamento (String nome){
        this.nome = nome;
    }

    public double processarPagamento(double valorVenda){
        return 0;
        //implementar depois
    }

    public double getValorAdicional (){
        return 0;
    }

    public abstract double getTaxa();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
