import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Classe para gerenciar usuários do sistema
public class Usuario {
    private int idUsuario;
    private String nome;
    private String senha;
    private String tipo; // Ex.: Gerente, Atendente, Caixa

    public Usuario(String nome, String senha, String tipo) {
        this.nome = nome;
        this.senha = senha;
        this.tipo = tipo;
    }

    // Cadastra um produto (restrito a Gerente)
    public void cadastrarProduto(Produto produto) throws SQLException {
        if (!tipo.equals("Gerente")) {
            throw new SQLException("Apenas gerentes podem cadastrar produtos");
        }
        produto.cadastrar();
    }
}