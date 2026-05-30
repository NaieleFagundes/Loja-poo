package controle; //importa do PostGres
import modelo.frete.IFrete;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Classe utilitaria que realiza operacoes com banco de dados
 */

public class Banco {
    private static Connection conexao;

    public static Connection conectar() throws Exception {
        if(conexao == null){
            // carregar o driver
            Class.forName("org.postgresql.Driver");//precisa do Drive JBDC para funcionar
            //na hierarquia JBDC o banco depende do drive
            String url = "jdbc:postgresql://localhost:5432/poo";
            String usuario = "postgres";//nome do usuario do banco de dados
            String senha = "123456"; //depois abre a conexão
            conexao = DriverManager.getConnection(url, usuario, senha);//conecta no banco passando os parâmetros
            //getConnection é método
            //**Obs.: ver o que está estático ou não
        }
        return conexao;//*SingleTon
    }
    public static  void  desconectar() throws Exception {
        conexao.close();
    }

    public static void salvarFrete (IFrete frete) throws Exception {//importa a interface
        //classe estática só lida com dados estáticos
        Connection conexao = conectar();//se não há conexão aberta, ele abre
        Statement stmt = conexao.createStatement();//meio de transporte.Envio e retorno da informação
        String sql = "insert into fretes values (";
        sql += "'" + frete.getNome() + "')";
        stmt.executeUpdate(sql);

    }

}
