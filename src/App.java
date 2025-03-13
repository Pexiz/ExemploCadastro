import dao.IClienteDAO;
import domain.Cliente;
import domain.Telas.Tela1;
import javax.swing.*;

public class  App {


    private static IClienteDAO iClienteDAO;

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(() -> {
                    Tela1 tela1 = new Tela1();
                });
    }


    private static void deletar(Integer cpf) {

        if (validacao(cpf)) {
            iClienteDAO.excluir(cpf);

        } else
            JOptionPane.showMessageDialog(null, "Cliente nao encontrado", "Mensagem", JOptionPane.INFORMATION_MESSAGE);

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

}