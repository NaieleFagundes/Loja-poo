package visao;

import controle.Banco;
import modelo.frete.Correios;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    public Main(){
        setTitle(":: TechStore ::");
        //define tamanho
        setSize(640,480);
        //abre janela no centro
        //posiciona a janela em relação à dimensão atual do tipo tela. Abre no centro.
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        desenharComponentes();
    }

    public void desenharComponentes (){
        JLabel jlNome = new JLabel("Nome do cliente: ");
        JTextField jtfNome = new JTextField(50);
        JButton btnCadastrar = new JButton("Cadastrar");
        //adiciona a tela
        JPanel painel = new JPanel();
        painel.add(jlNome);
        painel.add(jtfNome);
        //getContentPane().add(jtfNome);

        painel.add(btnCadastrar);// adiciona o botão. Sempre antes do getContentPane
        //há hierarquia
        getContentPane().add(painel);// adc campo de texto
        btnCadastrar.addActionList (new ActionListener (){}){
            public void actionPerformed (ActionEvent e){
                Correios correios = new Correios();
                Banco.salvarFrete(c);
                JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
            }
        }
    }

    static void main(String[] args) throws Exception {
        new Main().setVisible(true);
        Banco.conectar();
    }
}
