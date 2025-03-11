import dao.ClienteMapDAO;
import dao.IClienteDAO;
import domain.Cliente;

import javax.swing.*;
import java.util.Collection;
import java.util.Set;

public class  App {


    private static IClienteDAO iClienteDAO;

    public static void main(String[] args) {
        iClienteDAO = new ClienteMapDAO();
        Cliente Test = new Cliente("Peter Parker", "Rua Brasil", 1, "Minas Gerais", "Belo Horizonte", 313482, 521);
        iClienteDAO.cadastrar(Test);
        String opcao = JOptionPane.showInputDialog(null, "Digite [1] para cadastro, [2] para consulta, [3] para Exclusão [4] para buscar Todos ou [5] para sair", JOptionPane.INFORMATION_MESSAGE);

        while (!isOpcaoValida(opcao)) {
            if ("".equals(opcao)) {
                sair();
            }

//        javax.swing.SwingUtilities.invokeLater(() -> {
//                    Tela1 tela1 = new Tela1();
//                    tela1.mostrar();
//                });


            opcao = JOptionPane.showInputDialog(null, "Opção invalida Digite [1] para cadastro, [2] para consulta, [3] para Exclusão [4] para buscar Todos ou [5] para sair", JOptionPane.INFORMATION_MESSAGE);
        }

        while (isOpcaoValida(opcao)) {
            if (isOpcaoSair(opcao)) {
                sair();
            } else if (isCadastro(opcao)) {
                String dados = JOptionPane.showInputDialog(null, "Digite os dados do cliente separados por virgula, conforme exemplo: Nome, Endereço, CPF, Estado, Cidade, Telefone e Numero casa ");
                {
                    cadastrar(dados);
                }

            } else if (isConsulta(opcao)) {
                Integer dados = Integer.valueOf(JOptionPane.showInputDialog(null, "Digite o CPF do usuario "));
                {
                    consultar(dados);
                }

            } else if (isDeletar(opcao)) {
                Integer CPF = Integer.valueOf(JOptionPane.showInputDialog(null, "Digite o CPF do cliente para a deleção"));
                consultar(CPF);
                if (CPF != null) {
                    deletar(CPF);
                    JOptionPane.showMessageDialog(null, "Cliente deletado: ", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Cliente nao encontrado", "Mensagem", JOptionPane.INFORMATION_MESSAGE);

                }
            } else if (isBuscarTodos(opcao)) {
                PegarTodos();


            }

            opcao = JOptionPane.showInputDialog(null, "Digite [1] para cadastro, [2] para consulta, [3] para Exclusão [4] para Buscar Todos ou [5] para sair", JOptionPane.INFORMATION_MESSAGE);

        }

    }

    private static void PegarTodos() {
        Collection<Cliente> clientes = iClienteDAO.buscarTodos();

        for (Cliente cliente : clientes) {
            JOptionPane.showMessageDialog(null, "Cliente: " + cliente.getNome() + " Encontrado com o CPF de ", cliente.getCpf() + "Mensagem", JOptionPane.INFORMATION_MESSAGE);


        }
    }


    private static void deletar(Integer cpf) {

        if (validacao(cpf)) {
            iClienteDAO.excluir(cpf);

        } else
            JOptionPane.showMessageDialog(null, "Cliente nao encontrado", "Mensagem", JOptionPane.INFORMATION_MESSAGE);

    }

    private static void cadastrar(String dados) {
        String[] dadosSeparados = dados.split(",");


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

        Boolean isCadastrado = iClienteDAO.cadastrar(cliente);
        if (isCadastrado) {
            JOptionPane.showMessageDialog(null, "Cliente Cadastrado com sucesso", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Cliente Ja esta cadastrado ", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void consultar(Object dados) {
        if (dados instanceof String) {
            JOptionPane.showMessageDialog(null, "Apenas NUMEROS", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
        } else if (dados instanceof Integer) {

            Cliente cliente = iClienteDAO.consultar((Integer) dados);
            if (cliente != null)
                JOptionPane.showMessageDialog(null, "Cliente encontrado: " + cliente.getNome(), "Mensagem", JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, "Cliente Não encontrado: ", "Mensagem", JOptionPane.INFORMATION_MESSAGE);

        }
    }

    private static boolean validacao(Object dados) {
        if (dados instanceof String) {
            JOptionPane.showMessageDialog(null, "Apenas NUMEROS", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
        } else if (dados instanceof Integer) {

            Cliente cliente = iClienteDAO.consultar((Integer) dados);
            if (cliente != null) {
                JOptionPane.showMessageDialog(null, "Cliente encontrado: " + cliente.getNome(), "Mensagem", JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else
                JOptionPane.showMessageDialog(null, "Cliente Não encontrado: ", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
        }
        return false;



    }

    private static boolean isCadastro(String opcao) {
        return "1".equals(opcao);
    }

    private static boolean isOpcaoSair(String opcao) {
        return "5".equals(opcao);
    }

    private static boolean isConsulta(String opcao) {
        return "2".equals(opcao);
    }

    private static boolean isDeletar(String opcao) {
        return "3".equals(opcao);
    }

    private static boolean isBuscarTodos(String opcao) {
        return "4".equals(opcao);
    }


    private static void sair() {
        JOptionPane.showMessageDialog(null, "Até Logo!!", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);

    }

    private static boolean isOpcaoValida(String opcao) {

        Set<String> OPCOES_VALIDAS = Set.of("1", "2", "3", "4", "5");

        if (OPCOES_VALIDAS.contains(opcao)) {
            return true;
        }
        return false;
    }
}