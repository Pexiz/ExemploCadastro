package domain.Telas;

import dao.ClienteMapDAO;
import dao.IClienteDAO;
import domain.Cliente;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tela1 extends JFrame {
    private JPanel MainPanel;
    private JFormattedTextField buttonBuscarUsuarioPeloCpf;
    private JButton mostrarTodosButton;
    private JButton cadastrarButton;
    private JButton excluirButton;
    private JButton SairButton;
    private JButton pesquisarButton;
    private Cliente cliente;

    public Tela1() {
        Cliente Test = new Cliente("Peter Parker", "Rua Brasil", 1, "Minas Gerais", "Belo Horizonte", 313482, 521);
        getClienteDAO().cadastrar(Test);

        setContentPane(MainPanel);
        setTitle("Menu Principal");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setVisible(true);

        SairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            actionSair();

            }
        });

        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                actionCadastrarUsuario();
            }
        });

        mostrarTodosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                IClienteDAO clienteDAO = getClienteDAO();
                StringBuilder todosClientes = new StringBuilder();

                for (Cliente cliente : clienteDAO.buscarTodos()) {
                    todosClientes.append("Nome: ").append(cliente.getNome())
                            .append(", CPF: ").append(cliente.getCpf())
                            .append("\n");
                }

                if (todosClientes.length() > 0) {
                    JOptionPane.showMessageDialog(null, todosClientes.toString(), "Todos os Clientes", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Nenhum cliente encontrado.", "Todos os Clientes", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });

        excluirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionDeletar();
            }
        });
        pesquisarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionBuscarCPFUnico();

                    }


        });
    }

    private void actionDeletar() {

        try {
//          JFormattedTextField formattedTextField = new JFormattedTextField(new javax.swing.text.MaskFormatter("###.###.###-##"));
            JFormattedTextField formattedTextField = new JFormattedTextField();

            formattedTextField.setColumns(11); // Tamanho do campo

            int option = JOptionPane.showConfirmDialog(
                    null,
                    formattedTextField,
                    "Digite o CPF do Cliente a Excluir",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE
            );

            if (option == JOptionPane.OK_OPTION) {
//                String cpfString = formattedTextField.getText().replaceAll("[^0-9]", ""); // Isso tira os traços
                String cpfString = formattedTextField.getText();
                Integer cpf = Integer.valueOf(cpfString);

                IClienteDAO clienteDAO = getClienteDAO();
                Cliente clienteRemovido = clienteDAO.excluir(cpf);

                if (clienteRemovido != null) {
                    JOptionPane.showMessageDialog(null, "Cliente removido com sucesso: " + clienteRemovido.getNome(), "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Nenhum cliente encontrado com o CPF: " + cpf, "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "CPF inválido! Por favor, digite apenas números.", "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void actionCadastrarUsuario()
    {
            TelaCadastro telaCadastro = new TelaCadastro();
    }

    public void actionBuscarCPFUnico()
    {

        JFormattedTextField formattedTextField = new JFormattedTextField();
        IClienteDAO clienteDAO = getClienteDAO();
        formattedTextField.setColumns(15);

        JPanel panel = new JPanel();
        panel.add(new JLabel("Digite o CPF a ser buscado: "));
        panel.add(formattedTextField);
        JOptionPane.showMessageDialog(
                null,
                panel,
                "Entrada de CPF",
                JOptionPane.PLAIN_MESSAGE);

        String cpfString = formattedTextField.getText();

        try {
            Integer cpf = Integer.valueOf(cpfString);
            cliente = getClienteDAO().consultar(cpf);

            if (cliente != null) {
                clienteDAO.consultar(cpf);
                JOptionPane.showMessageDialog(null, "Usuário encontrado: " + cliente.getNome(), "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Não foi possível encontrar o cliente com o CPF: " + cpf, "Não encontrado", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException o) {
            JOptionPane.showMessageDialog(null, "Digite um CPF válido! Apenas números são permitidos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }





    }

    public void actionSair()
    {
        JOptionPane.showMessageDialog(null, "Até Logo!!", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    private IClienteDAO getClienteDAO() {
        return ClienteMapDAO.getInstance();
    }

}