package domain.Telas;

import dao.ClienteMapDAO;
import dao.IClienteDAO;
import domain.Cliente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCadastro extends JFrame {
    private JPanel PainelCadastro;
    private JLabel Texto1;
    private JLabel Texto2;
    private JTextField areaDeEscrita;
    private JButton cadastrarButton;

    public TelaCadastro() {
        IClienteDAO clienteDAO = ClienteMapDAO.getInstance();
        JPanel painel = new JPanel();
        setContentPane(PainelCadastro);

        setTitle("Cadastrar Usuarios");
        setSize(450, 300);
        setLocationRelativeTo(null);
        setVisible(true);


        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

               String teste = areaDeEscrita.getText();

                String[] dadosSeparados = teste.split(",");

        if (dadosSeparados.length < 7) {
            JOptionPane.showMessageDialog(null, "Dados insuficientes. Verifique e tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String nome = dadosSeparados[0].isEmpty() ? null : dadosSeparados[0];
        String endereco = dadosSeparados[1].isEmpty() ? null : (dadosSeparados[1]);
        Integer cpf = dadosSeparados[2].isEmpty() ? null : Integer.valueOf(dadosSeparados[2]);
        String estado = dadosSeparados[3].isEmpty() ? null : dadosSeparados[3];
        String cidade = dadosSeparados[4].isEmpty() ? null : dadosSeparados[4];
        Integer telefone = dadosSeparados[5].isEmpty() ? null : Integer.valueOf(dadosSeparados[5]);
        Integer numeroCasa = dadosSeparados[6].isEmpty() ? null : Integer.parseInt(dadosSeparados[6]);

        Cliente cliente = new Cliente(nome, endereco, cpf, estado, cidade, telefone, numeroCasa);

        Boolean isCadastrado = clienteDAO.cadastrar(cliente);
        if (isCadastrado) {
            JOptionPane.showMessageDialog(null, "Cliente Cadastrado com sucesso", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Cliente Ja esta cadastrado ", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
        }

            }
        });

    }
}
