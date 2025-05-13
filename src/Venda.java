import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

// Classe para gerenciar vendas
public class Venda {
    private int idVenda;
    private Date data;
    private double valorTotal;
    private String metodoPagamento;
    private int idPedido;

    public Venda(Date data, double valorTotal, String metodoPagamento, int idPedido) {
        this.data = data;
        this.valorTotal = valorTotal;
        this.metodoPagamento = metodoPagamento;
        this.idPedido = idPedido;
    }

    // Registra uma venda no banco
    public void registrar() throws SQLException {
        String sql = "INSERT INTO Venda (data, valorTotal, metodoPagamento, idPedido) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setDate(1, new java.sql.Date(data.getTime()));
            stmt.setDouble(2, valorTotal);
            stmt.setString(3, metodoPagamento);
            stmt.setInt(4, idPedido);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                this.idVenda = rs.getInt(1);
            }
        }
    }

    // Gera comprovante (placeholder)
    public String gerarComprovante() {
        return "Comprovante Venda #" + idVenda + "\nData: " + data + "\nTotal: R$" + valorTotal + "\nPagamento: " + metodoPagamento;
    }
}