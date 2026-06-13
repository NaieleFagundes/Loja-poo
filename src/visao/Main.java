package visao;

import controle.Banco;
import modelo.frete.Correios;
import modelo.pagamento.Boleto;
import modelo.pagamento.CartaoLoja;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    public Main() throws Exception {
        setTitle(":: TechStore ::");
        //define o tamanho
        setSize(640, 480);
        // abre a janela no centro
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        desenharComponentes();
    }

    public void desenharComponentes() throws Exception {
        JLabel jlNome = new JLabel("Forma de Pagamento:");
        JTextField jtfNome = new JTextField(50);
        JButton btnCadastrar = new JButton("Cadastrar");

        JTable tabela = new JTable();// criação de tabela
        String [] colunas = new String[] {"id", "descrição"};//objeto
        // inicializado no abre e fecha chaves. Representa nome das colunas
        //da tabela
        Object[] linhas = Banco.buscarFrete();//mostra na tabela os fretes, não os pagamentos**
        DefaultTableModel modelo = new DefaultTableModel(colunas,0);//colunas e linhas
        // adiciona a tela
        for (int i = 0; i < linhas.length; i++) {
            modelo.addRow((Object[]) linhas[i]);
        }

        tabela.setModel(modelo);
        JScrollPane scroll = new JScrollPane(tabela);//barra de rolagem para tabela

        JPanel painel = new JPanel();

        painel.add(jlNome);
        painel.add(jtfNome);
        painel.add(btnCadastrar);
        painel.add(scroll);

        getContentPane().add(painel);
        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CartaoLoja c = new CartaoLoja(2);
                c.setNome(jtfNome.getText());
                try {
                    Banco.salvarFormaPagamento(c);
                    jtfNome.setText("");//para limpar e inserir outras formas de pagamento
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                JOptionPane.showMessageDialog(
                        null, "Salvo com sucesso!");

            }
        });
    }

    public static void main(String[] args) throws Exception {
        new Main().setVisible(true);
        Banco.conectar();
    }
}