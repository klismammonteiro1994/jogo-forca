package Modelo;

import static Visual.TelaLogin.usuario;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author klismam
 */
public class ModeloLogin {
    
    public static final int ERRO_BANCO=-1;
    public static final int ERRO_SENHA=-2;
    public static final int SUCESSO=0;
    
    public int usuarioCadastro(Usuario u) {
        
        if (u.getSenha().equals(u.getConfirmaSenha())) {
            try {
                Connection c = SementeMysql.getConexaoMySQL();
                Statement stm = c.createStatement();
                String sql = "INSERT INTO usuario (id_usuario, nome_usuario, login, senha) VALUES "
                        + "(NULL,'" + u.getNome_usuario() + "', '" + u.getLogin() + "' ,'" + u.getSenha() + "')";
                System.out.println(sql);
                stm.executeUpdate(sql);
                c.close();
                return SUCESSO;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return ERRO_BANCO;
            }
        } else {
            return ERRO_SENHA;
        }
        
    }

    public int usuarioLogin(Usuario u) {
        
        Connection c = SementeMysql.getConexaoMySQL();

        try {
            Statement s = c.createStatement();
            String sql = "SELECT * FROM usuario where login='" + u.getLogin() + "' AND senha='" + u.getSenha() + "'";
            System.out.println(sql);
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                usuario = new Usuario(rs.getInt("pontuacao"), rs.getInt("id_usuario"), rs.getInt("maiorPontucao"),
                rs.getInt("trofeu"),rs.getInt("melhorPontuacao") , rs.getString("nome_usuario"), rs.getString("login"), rs.getString("senha"));
                
                return SUCESSO;
            } else {
                return ERRO_SENHA;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ERRO_BANCO;
        }

    }
}
