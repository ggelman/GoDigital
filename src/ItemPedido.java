import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Classe para gerenciar itens de um pedido
public class ItemPedido {
    private int idProduto;
    private int quantidade;

    public ItemPedido(int idProduto, int quantidade) {
        this.idProduto = idProduto;
        this.quantidade = quantidade;
    }

    // Salva item no banco, associado a um pedido
    public void salvar(int idPedido) throws SQLException {
        String sql = "INSERT INTO Item_Pedido (idPedido, idProduto, quantidade) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPedido);
            stmt.setInt(2, idProduto);
            stmt.setInt(3, quantidade);
            stmt.executeUpdate();
        }
    }
}