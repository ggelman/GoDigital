import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Classe para gerar relatórios
public class Relatorio {
    // Gera relatório de vendas por período
    public List<String> gerarRelatorioVendas(String dataInicio, String dataFim) throws SQLException {
        List<String> relatorio = new ArrayList<>();
        String sql = "SELECT idVenda, data, valorTotal, metodoPagamento FROM Venda WHERE data BETWEEN ? AND ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dataInicio);
            stmt.setString(2, dataFim);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String linha = "Venda #" + rs.getInt("idVenda") + " | Data: " + rs.getDate("data") +
                              " | Total: R$" + rs.getDouble("valorTotal") + " | Pagamento: " + rs.getString("metodoPagamento");
                relatorio.add(linha);
            }
        }
        return relatorio;
    }

    // Gera relatório de estoque
    public List<String> gerarRelatorioEstoque() throws SQLException {
        List<String> relatorio = new ArrayList<>();
        String sql = "SELECT nome, qtdAtual, qtdMinima FROM Produto";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String linha = "Produto: " + rs.getString("nome") + " | Estoque: " + rs.getInt("qtdAtual") +
                              " | Mínimo: " + rs.getInt("qtdMinima");
                relatorio.add(linha);
            }
        }
        return relatorio;
    }
}