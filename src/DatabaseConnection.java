import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Classe para gerenciar a conexão com o banco de dados MySQL
public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/padaria_santa_marcelina";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver MySQL não encontrado", e);
        }
    }
}