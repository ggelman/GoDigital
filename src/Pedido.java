import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Classe para gerenciar pedidos
public class Pedido {
    private int idPedido;
    private Date data;
    private String canal;
    private String status;
    private int idCliente;
    private List<ItemPedido> itens;

    public Pedido(Date data, String canal, String status, int idCliente) {
        this.data = data;
        this.canal = canal;
        this.status = status;
        this.idCliente = idCliente;
        this.itens = new ArrayList<>();
    }

    public int getIdPedido() { return idPedido; }

    // Adiciona um item ao pedido
    public void adicionarItem(ItemPedido item) {
        itens.add(item);
    }

    // Importa pedido (placeholder para iFood/WhatsApp)
    public void importar() throws SQLException {
        String sql = "INSERT INTO Pedido (data, canal, status, idCliente) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setDate(1, new java.sql.Date(data.getTime()));
            stmt.setString(2, canal);
            stmt.setString(3, status);
            stmt.setInt(4, idCliente);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                this.idPedido = rs.getInt(1);
            }
        }
        // Salva itens do pedido
        for (ItemPedido item : itens) {
            item.salvar(idPedido);
        }
    }

    // Atualiza status do pedido
    public void atualizarStatus(String novoStatus) throws SQLException {
        this.status = novoStatus;
        String sql = "UPDATE Pedido SET status = ? WHERE idPedido = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setInt(2, idPedido);
            stmt.executeUpdate();
        }
    }
}