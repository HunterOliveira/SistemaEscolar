package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexaoDao {
    public static final String url = "jdbc:mysql://localhost:3306/sistema_escolar";
    public static final String usuario = "root";
    public static final String senha = "Oliveira1.!";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, usuario, senha);
    }
}



