package Controlador;

import Visual.TelaRanking;
import javax.swing.JFrame;

/**
 *
 * @author klismam
 */
public class ControleRanking {

    public ControleRanking() {
        TelaRanking telaRanking = new TelaRanking();
        telaRanking.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        telaRanking.setTitle("Ranking Melhores Jogadores");
        telaRanking.setLocationRelativeTo(null);
        telaRanking.setResizable(false);
        telaRanking.setVisible(true);
        
    }

    public static void main(String[] args) {
        new ControleRanking();
    }

}
