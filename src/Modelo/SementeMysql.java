package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SementeMysql {

    private static Connection connection;

    public SementeMysql() {
    }

    public static java.sql.Connection getConexaoMySQL() {
        connection = null;
        try {
            String driverName = "com.mysql.jdbc.Driver";
            Class.forName(driverName);
            String servidor = "localhost";
            String bancodedados = "JogoForca";
            String url = "jdbc:mysql://" + servidor + "/" + bancodedados;
            String usuario = "root";
            String senha = "";

            connection = DriverManager.getConnection(url, usuario, senha);
            if (connection != null) {
                System.out.println("STATUS - Conectado com sucesso!");
            } else {
                System.out.println("STATUS - NÃ£o foi possivel realizar conexÃo");
            }
            return connection;
        } catch (ClassNotFoundException e) {
            System.out.println("O driver expecificado nao foi encontrado.");
            return null;
        } catch (SQLException e) {
            System.out.println("Nao foi possivel conectar ao Banco de Dados.");
            System.exit(0);
            return null;
        }
    }
    

    public static boolean FecharConexao() {
        try {
            connection.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static java.sql.Connection ReiniciarConexao() {
        FecharConexao();
        return SementeMysql.getConexaoMySQL();
    }
}
