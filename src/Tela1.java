import javax.swing.*;

public class Tela1 {
    private JButton opçõesButton;
    private JButton sairButton;
    private JFrame frame;


    public Tela1() {
        frame = new JFrame("Minha Tela");
        getOpçõesButton();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(null);}



    public void setOpçõesButton(JButton opçõesButton) {
        JOptionPane.showMessageDialog(null, "Cliente Cadastrado com sucesso", "Mensagem", JOptionPane.INFORMATION_MESSAGE);

    }


    public JButton getOpçõesButton() {
        JOptionPane.showMessageDialog(null, "Cliente Cadastrado com sucesso", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
        return opçõesButton;
    }

    public void mostrar() {
        frame.setVisible(true);

    }

}


