package Modelo;

import Controlador.ControleJogo;
import static Visual.Jogo.usuario;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

/**
 *
 * @author klismam
 */
public class ModeloJogo {

    public static final int ERRO_BANCO = 0;
    public static final int SUCESSO = 1;

    public int upadatePontuacao(ControleJogo cj) {
        Connection c = SementeMysql.getConexaoMySQL();
        try {
            Statement s = c.createStatement();
            if (cj.getPontuacaoTotal() > usuario.getMelhorPontuacao()) {
                String sql = "update usuario set melhorPontuacao = '" + cj.getPontuacaoTotal()
                        + "' where id_usuario = '" + usuario.getId_Usuario() + "'";
                s.executeUpdate(sql);
                usuario.setMelhorPontuacao(cj.getPontuacaoTotal());
                System.out.println(sql);
            } else {
                String sql = "update usuario set maiorPontucao = '" + cj.getPontuacaoTotal()
                        + "' where id_usuario = '" + usuario.getId_Usuario() + "'";
                s.executeUpdate(sql);
                System.out.println(sql);
            }
            usuario.setMaiorPontuacao(cj.getPontuacaoTotal());
            return SUCESSO;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return ERRO_BANCO;
        }
    }

    public int buscaPalavra(ControleJogo cj) {
        Connection c = SementeMysql.getConexaoMySQL();
        try {
            Random r = new Random();
            int num = r.nextInt(54);
            if (num == 0) {
                num = 1;
            }
            Statement s = c.createStatement();
            String sql = "select dica,palavra,dificuldade from palavras where id_dica = '" + num + "'";
            System.out.println(sql);
            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
                cj.setDica(rs.getString("dica"));
                cj.setPalavraCorretaDoBanco(rs.getString("palavra"));
                cj.setDificuldade(rs.getString("dificuldade"));
                return SUCESSO;
            } else {
                return ERRO_BANCO;
            }
        } catch (SQLException e) {
            e.getMessage();
            return ERRO_BANCO;
        }

    }
}
