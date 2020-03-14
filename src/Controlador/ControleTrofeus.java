package Controlador;

import static Visual.TelaJogo.usuario;
import Visual.TelaTrofeus;
import javax.swing.JFrame;

/**
 *
 * @author klismam
 */
public class ControleTrofeus {

    public ControleTrofeus() {
        TelaTrofeus telaTrofeus = new TelaTrofeus();
        telaTrofeus.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        telaTrofeus.setTitle("Seus Trofeus " + usuario.getNome_usuario());
        telaTrofeus.setLocationRelativeTo(null);
        telaTrofeus.setResizable(false);
        telaTrofeus.setVisible(true);
    }

    public static void main(String[] args) {
        new ControleTrofeus();
    }

}
