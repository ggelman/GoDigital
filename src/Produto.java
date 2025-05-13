import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Classe para gerenciar produtos no estoque
public class Produto {
    private int idProduto;
    private String nome;
    private double preco;
    private int qtdMinima;
    private String descricao;
    private int qtdAtual;

    public Produto(String nome, double preco, int qtdMinima, String descricao, int qtdAtual) {
        this.nome = nome;
        this.preco = preco;
        this.qtdMinima = qtdMinima;
        this.descricao = descricao;
        this.qtdAtual = qtdAtual;
    }

    public int getIdProduto() { return idProduto; }
    public String getNome() { return nome; }
    public double getPreco() { return preco; }
    public int getQtdAtual() { return qtdAtual; }

    // Cadastra um novo produto no banco
    public void cadastrar() throws SQLException {
        String sql = "INSERT INTO Produto (nome, preco, qtdMinima, descricao, qtdAtual) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, nome);
            stmt.setDouble(2, preco);
            stmt.setInt(3, qtdMinima);
            stmt.setString(4, descricao);
            stmt.setInt(5, qtdAtual);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                this.idProduto = rs.getInt(1);
            }
        }
    }

    // Verifica se o estoque está abaixo do mínimo
    public boolean emitirAlerta() {
        return qtdAtual < qtdMinima;
    }

    // Atualiza o estoque após venda ou reposição
    public void atualizarEstoque(int quantidade) throws SQLException {
        this.qtdAtual += quantidade;
        String sql = "UPDATE Produto SET qtdAtual = ? WHERE idProduto = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, qtdAtual);
            stmt.setInt(2, idProduto);
            stmt.executeUpdate();
        }
    }
}