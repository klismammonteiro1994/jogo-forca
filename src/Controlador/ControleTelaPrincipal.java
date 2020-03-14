package Controlador;

import Visual.TelaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author klismam
 */
public class ControleTelaPrincipal {

    public ControleTelaPrincipal() {

        TelaPrincipal tp = new TelaPrincipal();
        tp.setTitle("Jogo Da Forca 2.0");
        tp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tp.setLocationRelativeTo(null);
        tp.setVisible(true);

        tp.getBtnLogin().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                tp.dispose();
                botaoLogin();
            }
        });

        tp.getBtnRanking().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                botaoRanking();
            }
        });

        tp.getBtnSobre().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                botaoSobre();
            }
        });
        
    }
    
    public void botaoLogin(){
        TelaPrincipal tp = new TelaPrincipal();
        tp.dispose();
        ControleTelaLogin ctl = new ControleTelaLogin();
    }
    
    public void botaoRanking(){
        ControleRanking cr = new ControleRanking();
    }
    
    public void botaoSobre(){
        JOptionPane.showMessageDialog(null, "Jogo da Forca Versão 2.0.."
                + "\nDesenvolvido por Klismam Monteiro, Daniel Valente e Gabriel Negidio"
                + "\nAgradeço a ajuda dos professores Maxwel Dias , Diemison Melo e Demys Brito"
                + "\nTodos os Direitos Reservados");
        
    }

    public static void main(String[] args) {
        new ControleTelaPrincipal();
    }
}
