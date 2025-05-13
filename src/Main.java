import java.sql.SQLException;
import java.util.Date;

// Classe principal para testar funcionalidades
public class Main {
    public static void main(String[] args) {
        try {
            // Cadastra um cliente
            Cliente cliente = new Cliente("João Silva", "11987654321", "joao@exemplo.com");
            cliente.cadastrar();

            // Cadastra um produto
            Produto produto = new Produto("Pão Francês", 1.50, 20, "Pão fresco", 50);
            produto.cadastrar();

            // Cria um pedido
            Pedido pedido = new Pedido(new Date(), "WhatsApp", "Pendente", cliente.getIdCliente());
            ItemPedido item = new ItemPedido(produto.getIdProduto(), 10);
            pedido.adicionarItem(item);
            pedido.importar();
            pedido.atualizarStatus("Concluído");

            // Registra uma venda
            Venda venda = new Venda(new Date(), 15.00, "Pix", pedido.getIdPedido());
            venda.registrar();
            System.out.println(venda.gerarComprovante());

            // Adiciona pontos de fidelidade
            Fidelidade fidelidade = new Fidelidade(cliente.getIdCliente(), 0);
            fidelidade.adicionarPontos(10);

            // Gera relatórios
            Relatorio relatorio = new Relatorio();
            System.out.println("Relatório de Vendas:");
            for (String linha : relatorio.gerarRelatorioVendas("2025-05-01", "2025-05-31")) {
                System.out.println(linha);
            }
            System.out.println("Relatório de Estoque:");
            for (String linha : relatorio.gerarRelatorioEstoque()) {
                System.out.println(linha);
            }

            // Atualiza estoque e verifica alerta
            produto.atualizarEstoque(-5);
            if (produto.emitirAlerta()) {
                System.out.println("Alerta: Estoque de " + produto.getNome() + " abaixo do mínimo!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}