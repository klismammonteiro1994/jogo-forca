package Controlador;

import Modelo.ModeloLogin;
import static Modelo.ModeloLogin.ERRO_BANCO;
import static Modelo.ModeloLogin.ERRO_SENHA;
import static Modelo.ModeloLogin.SUCESSO;
import Modelo.Usuario;
import Visual.TelaLogin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author klismam
 */
public class ControleTelaLogin {

    TelaLogin tl = new TelaLogin();
    ModeloLogin ml = new ModeloLogin();
    Usuario u = new Usuario();

    public ControleTelaLogin() {

        tl.setTitle("Cadastro ou Login");
        tl.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tl.setLocationRelativeTo(null);
        tl.setVisible(true);

        tl.getBtnCadastrar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                u.setNome_usuario(tl.getTfNomeUsuario().getText());
                u.setLogin(tl.getTfLoginCadastro().getText());
                u.setSenha(tl.getTfSenhaCadastro().getText());
                u.setConfirmaSenha(tl.getTfConfirmaSenha().getText());
                
                char[] str = new char[10];
             //   str = {"1","2","3","4","5","6","7","8","9","0"};

                if (u.getNome_usuario().equalsIgnoreCase("") || u.getLogin().equalsIgnoreCase("")
                        || u.getSenha().equalsIgnoreCase("") || u.getConfirmaSenha().equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(null, "Nenhum Campo Pode Ficar Em Branco !!");
                } else if (tl.getTfNomeUsuario().getText().length() < 6) {
                    JOptionPane.showMessageDialog(null, "O Campo Nome de Usuario deve ter no Minimo 6 Caracteres !!");
                } else if (tl.getTfNomeUsuario().getText().contains("")) { // nao funciona
                    JOptionPane.showMessageDialog(null, "o nome de usuario nao deve conter apenas numeros !!");
                } else if (tl.getTfLoginCadastro().getText().length() < 4) {
                    JOptionPane.showMessageDialog(null, "O Campo Login deve ter no Minimo 4 Caracteres !!");
                } else if (tl.getTfSenhaCadastro().getText().length() < 6) {
                    JOptionPane.showMessageDialog(null, "o Campo Senha deve ter no Minimo 6 Caracteres !!");
                } else {
                    metodoCadastrar();
                }

            }
        });

        tl.getBtnLogar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                u.setLogin(tl.getTfLogin().getText());
                u.setSenha(tl.getTfSenhaLogin().getText());

                if (u.getLogin().equalsIgnoreCase("") || u.getSenha().equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(null, "O campo Senha e Login Nao Pode Ficar Em Branco !!");
                    tl.getTfLogin().setText("");
                    tl.getTfSenhaLogin().setText("");
                } else {
                    metodoLogin();
                }

            }
        });

        tl.getBtnVoltar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                tl.dispose();
                ControleTelaPrincipal ctp = new ControleTelaPrincipal();
            }
        });
    }

    public void metodoCadastrar() {

        int ret = ml.usuarioCadastro(u);

        if (ERRO_BANCO == ret) {
            JOptionPane.showMessageDialog(null, "Erro no Banco !");
        } else if (ERRO_SENHA == ret) {
            JOptionPane.showMessageDialog(null, "Senha Digitada Não Corresponde !");
            tl.getTfSenhaCadastro().setText("");
            tl.getTfConfirmaSenha().setText("");
        } else if (SUCESSO == ret) {
            JOptionPane.showMessageDialog(null, "Usuario Cadastrado com Sucesso !!");
            tl.getTfNomeUsuario().setText("");
            tl.getTfLoginCadastro().setText("");
            tl.getTfSenhaCadastro().setText("");
            tl.getTfConfirmaSenha().setText("");
        }
    }

    public void metodoLogin() {

        int ret = ml.usuarioLogin(u);

        if (ERRO_BANCO == ret) {
            JOptionPane.showMessageDialog(null, "Erro no Banco !");
        } else if (ERRO_SENHA == ret) {
            tl.getTfLogin().setText("");
            tl.getTfSenhaLogin().setText("");
            JOptionPane.showMessageDialog(null, "Login ou Senha Errados !!");
        } else if (SUCESSO == ret) {
            tl.dispose();
            ControleTelaJogo tj = new ControleTelaJogo();
        }

    }

    public static void main(String[] args) {

        new ControleTelaLogin();

    }
}
