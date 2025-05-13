import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

// Classe para gerenciar o programa de fidelidade
public class Fidelidade {
    private int idFidelidade;
    private int idCliente;
    private int pontos;
    private Date dataAtualizacao;

    public Fidelidade(int idCliente, int pontos) {
        this.idCliente = idCliente;
        this.pontos = pontos;
        this.dataAtualizacao = new Date();
    }

    // Adiciona pontos ao cliente
    public void adicionarPontos(int novosPontos) throws SQLException {
        this.pontos += novosPontos;
        this.dataAtualizacao = new Date();
        String sql = "INSERT INTO Fidelidade (idCliente, pontos, dataAtualizacao) VALUES (?, ?, ?) " +
                     "ON DUPLICATE KEY UPDATE pontos = ?, dataAtualizacao = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            stmt.setInt(2, pontos);
            stmt.setDate(3, new java.sql.Date(dataAtualizacao.getTime()));
            stmt.setInt(4, pontos);
            stmt.setDate(5, new java.sql.Date(dataAtualizacao.getTime()));
            stmt.executeUpdate();
        }
    }

    // Resgata pontos do cliente
    public boolean resgatarPontos(int pontosResgatar) throws SQLException {
        if (pontos >= pontosResgatar) {
            this.pontos -= pontosResgatar;
            this.dataAtualizacao = new Date();
            String sql = "UPDATE Fidelidade SET pontos = ?, dataAtualizacao = ? WHERE idCliente = ?";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, pontos);
                stmt.setDate(2, new java.sql.Date(dataAtualizacao.getTime()));
                stmt.setInt(3, idCliente);
                stmt.executeUpdate();
                return true;
            }
        }
        return false;
    }
}