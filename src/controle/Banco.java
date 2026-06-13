package controle;

import modelo.frete.IFrete;
import modelo.pagamento.FormaPagamento;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Classe utilitária para acesso ao banco de dados.
 */
public class Banco {

    private static Connection conexao;

    /**
     * Deve ser chamado uma única vez ao iniciar o sistema.
     */
    public static void inicializar() throws Exception {//lida com exceções
        criarBancoSeNecessario();
        criarTabelasSeNecessario();
        inserirDadosIniciais();
    }

    private static void criarBancoSeNecessario() throws Exception {

        Class.forName("org.postgresql.Driver");

        Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "123456");

        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery(
                "SELECT 1 FROM pg_database WHERE datname = 'poo'");

        if (!rs.next()) {
            stmt.executeUpdate("CREATE DATABASE poo");
            System.out.println("Banco poo criado com sucesso.");
        }

        rs.close();
        stmt.close();
        conn.close();
    }

    public static Connection conectar() throws Exception {

        if (conexao == null || conexao.isClosed()) {

            Class.forName("org.postgresql.Driver");

            String url = "jdbc:postgresql://localhost:5432/poo";
            String usuario = "postgres";
            String senha = "123456";

            conexao = DriverManager.getConnection(url, usuario, senha);
        }

        return conexao;
    }

    public static void desconectar() throws Exception {

        if (conexao != null && !conexao.isClosed()) {
            conexao.close();
        }
    }

    private static void criarTabelasSeNecessario() throws Exception {

        Connection conn = conectar();
        Statement stmt = conn.createStatement();

        stmt.execute("""
                CREATE TABLE IF NOT EXISTS produto (
                    id SERIAL PRIMARY KEY,
                    nome VARCHAR(120) NOT NULL,
                    preco NUMERIC(10,2) NOT NULL
                )
                """);

        stmt.execute("""
                CREATE TABLE IF NOT EXISTS forma_pagamento (
                    id SERIAL PRIMARY KEY,
                    nome VARCHAR(60) NOT NULL UNIQUE,
                    tipo VARCHAR(30) NOT NULL UNIQUE,
                    taxa_percentual NUMERIC(5,2) NOT NULL DEFAULT 0,
                    valor_adicional NUMERIC(10,2) NOT NULL DEFAULT 0
                )
                """);

        stmt.execute("""
                CREATE TABLE IF NOT EXISTS frete (
                    id SERIAL PRIMARY KEY,
                    nome VARCHAR(60) NOT NULL UNIQUE
                )
                """);

        stmt.execute("""
                CREATE TABLE IF NOT EXISTS venda (
                    id SERIAL PRIMARY KEY,
                    cliente VARCHAR(120) NOT NULL,
                    valor_total NUMERIC(10,2) NOT NULL DEFAULT 0,
                    forma_pagamento_id INTEGER REFERENCES forma_pagamento(id),
                    frete_id INTEGER REFERENCES frete(id),
                    quantidade_parcelas INTEGER
                )
                """);

        stmt.execute("""
                CREATE TABLE IF NOT EXISTS item_venda (
                    id SERIAL PRIMARY KEY,
                    venda_id INTEGER NOT NULL REFERENCES venda(id) ON DELETE CASCADE,
                    produto_id INTEGER NOT NULL REFERENCES produto(id),
                    quantidade INTEGER NOT NULL,
                    valor NUMERIC(10,2) NOT NULL
                )
                """);

        stmt.close();
    }

    private static void inserirDadosIniciais() throws Exception {

        Connection conn = conectar();
        Statement stmt = conn.createStatement();

        stmt.execute("""
                INSERT INTO forma_pagamento
                (nome, tipo, taxa_percentual, valor_adicional)
                VALUES
                ('pix', 'PIX', -10.00, 0.00)
                ON CONFLICT (nome) DO NOTHING
                """);

        stmt.execute("""
                INSERT INTO forma_pagamento
                (nome, tipo, taxa_percentual, valor_adicional)
                VALUES
                ('Boleto', 'BOLETO', 0.00, 2.50)
                ON CONFLICT (nome) DO NOTHING
                """);

        stmt.execute("""
                INSERT INTO forma_pagamento
                (nome, tipo, taxa_percentual, valor_adicional)
                VALUES
                ('Cartao de Credito', 'CARTAO_CREDITO', 0.00, 0.00)
                ON CONFLICT (nome) DO NOTHING
                """);
        stmt.execute("""
                INSERT INTO forma_pagamento
                (nome, tipo, taxa_percentual, valor_adicional)
                VALUES
                ('Cartao da Loja', 'CARTAO_LOJA', 0.00, 0.00)
                ON CONFLICT (nome) DO NOTHING
                """);

        stmt.execute("""
                INSERT INTO frete(nome)
                VALUES ('Correios')
                ON CONFLICT (nome) DO NOTHING
                """);

        stmt.execute("""
                INSERT INTO frete(nome)
                VALUES ('Jadlog')
                ON CONFLICT (nome) DO NOTHING
                """);
        stmt.execute("""
                INSERT INTO frete(nome)
                VALUES ('Rede Sul')
                ON CONFLICT (nome) DO NOTHING
                """);

        stmt.close();
    }

    public static void salvarFrete(IFrete frete) throws Exception {

        Connection conexao = conectar();

        Statement stmt = conexao.createStatement();

        String sql = "INSERT INTO frete(nome) VALUES ('"
                + frete.getNome()
                + "')";

        stmt.executeUpdate(sql);

        stmt.close();
    }

    public static void salvarFormaPagamento(FormaPagamento pagamento) throws Exception {
        Connection conexao = conectar();
        Statement stmt = conexao.createStatement();
        String tipoPagamento = pagamento.getNome().toUpperCase(); //para permitir a inserção de outros tipos de pagamento
        String sql = "INSERT INTO forma_pagamento (nome, tipo, taxa_percentual, valor_adicional) VALUES (" +
                "'" + pagamento.getNome() + "',"+
                "'"+ tipoPagamento +"'," +
                 pagamento.getTaxa() + ", "+
                 pagamento.getValorAdicional() +")";
        stmt.executeUpdate(sql);
        stmt.close();
    }
    public static Object[] buscarFrete() throws Exception {//tem que retornar alguma coisa ou dá erro. PRECISA DE RETURN
        // precisa throws exception. Método estático
       Connection con = conectar();
       Statement stmt = con.createStatement();
       String sql = "SELECT * FROM frete"; //instrução de seleção juntando tds as colunas
        ResultSet rs = stmt.executeQuery(sql);//leva da aplicação para o banco
        Object [] retorno = new Object[10];//retorna 10 linhas
        int indice = 0;
        while (rs.next()){
            Object[] linha = new Object[2];//pega linha para id e descrição
            linha [0] = rs.getObject(1);
            linha [1] = rs.getObject(2);
            retorno[indice++]= linha;
        }
        return retorno;
    }

    public static Object[] buscarFormaPagamento() throws Exception {
        // precisa throws exception. Método estático
        Connection con = conectar();
        Statement stmt = con.createStatement();
        String sql = "SELECT * FROM forma_pagamento"; //instrução de seleção juntando tds as colunas
        ResultSet rs = stmt.executeQuery(sql);//leva da aplicação para o banco
        Object [] retorno = new Object[10];//retorna 10 linhas
        int indice = 0;
        while (rs.next()){
            Object[] linha = new Object[2];//pega linha para id e descrição
            linha [0] = rs.getObject(1);
            linha [1] = rs.getObject(2);
            retorno[indice++]= linha;
        }
        return retorno;
    }
}