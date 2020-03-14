package Controlador;

import Modelo.Usuario;
import Visual.TelaJogo;
import static Visual.TelaJogo.usuario;
import Visual.TelaTrofeus;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author klismam
 */
public class ControleTelaJogo {

    TelaJogo tj = new TelaJogo();
    Usuario u = new Usuario();

    public ControleTelaJogo() {
        tj.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        tj.setTitle("Bem-Vindo " + tj.getUsuario().getLogin() 
                + " Melhor Pontuação " + tj.getUsuario().getMelhorPontuacao());
        tj.setResizable(false);
        tj.setLocationRelativeTo(null);
        tj.setVisible(true);

        tj.getBtnJogar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                tj.dispose();
                ControleJogo cj = new ControleJogo();
                cj.palavra();
            }
        });

        tj.getBtnRanking().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                ControleRanking cr = new ControleRanking();
            }
        });

        tj.getBtnTrofeus().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                ControleTrofeus ct = new ControleTrofeus();
            }
        });

        tj.getBtnSobre().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JOptionPane.showMessageDialog(null, "Jogo da Forca Versão 2.0.."
                        + "\nDesenvolvido por Klismam Monteiro, Daniel Valente e Gabriel Negidio"
                        + "\nAgradeço a ajuda dos professores Maxwel Dias , Diemison Melo e Demys Brito"
                        + "\nTodos os Direitos Reservados");
            }
        });

        tj.getBtnSair().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                metodoSair();
            }
        });
    }

    public void metodoSair() {
        int opcao
                = JOptionPane.showOptionDialog(null,
                        "Realmente Deseja Fazer Logout ?",
                        "Pergunta",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null,
                        new String[]{"Sim", "Não"}, "SIM");
        if (opcao == 0) {
            tj.dispose();
            ControleTelaLogin tl = new ControleTelaLogin();

        }
    }

    public static void main(String[] args) {

        new ControleTelaJogo();
    }
}
