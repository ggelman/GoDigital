# GoDigital Code - Padaria Santa Marcelina
Sistema de Gestão Empresarial para Padaria Santa Marcelina <br>
Fase 3 - FIAP 2025 <br>
**Grupo:** Giulia (RM: 12345), Lucas (RM: 67890) <br>
Data: 12 de maio de 2025 <br>
#### Sobre o Projeto
O GoDigital Code é um sistema de gestão empresarial desenvolvido para a Padaria Santa Marcelina, uma microempresa com três unidades em São Paulo (Alto da Boa Vista, Campo Belo, Moema). O objetivo é promover a transformação digital, otimizando processos manuais, integrando canais de vendas (iFood, WhatsApp) e melhorando a experiência do cliente. O sistema é projetado para usuários com pouca experiência tecnológica, oferecendo simplicidade, baixo custo e funcionalidades de alto impacto.

#### Funcionalidades

- **Cadastro de Produtos:** Registro de produtos com alertas de reposição.
- **Integração de Pedidos:** Sincronização de pedidos via iFood e WhatsApp (placeholder).
- **Registro de Vendas:** Controle de vendas presenciais e delivery.
- **Programa de Fidelidade:** Sistema de pontos para clientes frequentes.
- **Relatórios:** Geração de relatórios de vendas e estoque.
- **IA Preditiva:** Planejada para prever demanda diária (futuro).

#### Diferenciais

Interface intuitiva para microempresas.
Integração personalizada com iFood/WhatsApp.
Programa de fidelidade para retenção.
IA preditiva acessível.

### Estrutura do Repositório
/src <br>
  /DatabaseConnection.java  ***# Conexão com MySQL*** <br>
  /Produto.java             ***# Gerencia produtos e estoque*** <br>
  /Cliente.java             ***# Gerencia clientes*** <br>
  /Pedido.java              ***# Gerencia pedidos*** <br>
  /ItemPedido.java          ***# Itens de um pedido*** <br>
  /Venda.java               ***# Gerencia vendas*** <br>
  /Fidelidade.java          ***# Programa de fidelidade*** <br>
  /Usuario.java             ***# Gerencia usuários*** <br>
  /Relatorio.java           ***# Gera relatórios*** <br>
  /Main.java                ***# Demonstração das funcionalidades*** <br>

### Requisitos

Java: JDK 8 ou superior. <br>
MySQL: 8.0 ou superior. <br>
Driver JDBC: mysql-connector-java-8.0.27.jar (ou similar). <br>
IDE: IntelliJ, Eclipse ou similar (opcional). <br>


### Configuração

Banco de Dados: <br>
Instale o MySQL e crie o banco padaria_santa_marcelina:CREATE DATABASE padaria_santa_marcelina; <br> <br>

Execute os scripts SQL abaixo para criar as tabelas: <br> <br>
CREATE TABLE Produto ( <br>
    idProduto INT AUTO_INCREMENT PRIMARY KEY, <br>
    nome VARCHAR(100) NOT NULL, <br>
    preco DECIMAL(10,2) NOT NULL, <br>
    qtdMinima INT NOT NULL, <br>
    descricao VARCHAR(255), <br>
    qtdAtual INT NOT NULL <br>
); <br> <br>

CREATE TABLE Cliente ( <br>
    idCliente INT AUTO_INCREMENT PRIMARY KEY, <br>
    nome VARCHAR(100) NOT NULL, <br>
    telefone VARCHAR(15), <br>
    email VARCHAR(100) <br>
); <br> <br>

CREATE TABLE Pedido ( <br>
    idPedido INT AUTO_INCREMENT PRIMARY KEY, <br>
    data DATE NOT NULL, <br>
    canal VARCHAR(50) NOT NULL, <br>
    status VARCHAR(50) NOT NULL, <br>
    idCliente INT, <br>
    FOREIGN KEY (idCliente) REFERENCES Cliente(idCliente) <br>
); <br> <br>

CREATE TABLE Venda ( <br>
    idVenda INT AUTO_INCREMENT PRIMARY KEY, <br>
    data DATE NOT NULL, <br>
    valorTotal DECIMAL(10,2) NOT NULL, <br>
    metodoPagamento VARCHAR(50) NOT NULL, <br>
    idPedido INT, <br>
    FOREIGN KEY (idPedido) REFERENCES Pedido(idPedido) <br>
); <br> <br>

CREATE TABLE Fidelidade ( <br>
    idFidelidade INT AUTO_INCREMENT PRIMARY KEY, <br>
    idCliente INT NOT NULL, <br>
    pontos INT NOT NULL, <br>
    dataAtualizacao DATE NOT NULL, <br>
    FOREIGN KEY (idCliente) REFERENCES Cliente(idCliente) <br>
); <br> <br>

CREATE TABLE Item_Pedido ( <br>
    idPedido INT, <br>
    idProduto INT, <br>
    quantidade INT NOT NULL, <br>
    PRIMARY KEY (idPedido, idProduto), <br>
    FOREIGN KEY (idPedido) REFERENCES Pedido(idPedido), <br>
    FOREIGN KEY (idProduto) REFERENCES Produto(idProduto) <br>
); <br> <br>

Use uma ferramenta como DBeaver ou MySQL Workbench para executar os scripts.

#### Driver JDBC:

Baixe o driver MySQL Connector/J: MySQL Connector. <br>
Adicione o .jar ao classpath do projeto: <br>
IntelliJ: File > Project Structure > Libraries > + > Java > selecione o .jar. <br>
Eclipse: Right-click project > Build Path > Add External Archives > selecione o .jar. <br>

#### Configuração da Conexão:

Edite DatabaseConnection.java com as credenciais do seu MySQL:private static final String URL = "jdbc:mysql://localhost:3306/padaria_santa_marcelina"; <br>
private static final String USER = "root"; <br>
private static final String PASSWORD = "sua_senha"; <br>

#### Projeto Java:

Clone este repositório:git clone https://github.com/ggelman/godigital.git <br>

Importe o projeto na sua IDE: <br>
IntelliJ: File > Open > selecione a pasta do projeto. <br>
Eclipse: File > Import > General > Existing Projects into Workspace. <br>

#### Execução

Compilar e Executar: <br>

Abra Main.java na IDE. <br>
Execute o método main: <br>
IntelliJ: Clique no ícone verde de "Run" ao lado de main. <br>
Eclipse: Right-click Main.java > Run As > Java Application. <br> <br>

O programa demonstra: <br>
Cadastro de cliente e produto. <br>
Criação de pedido com itens. <br>
Registro de venda e comprovante. <br>
Adição de pontos de fidelidade. <br>
Geração de relatórios de vendas e estoque. <br>
Atualização de estoque com alerta. <br>

#### Saída Esperada:

Console exibirá comprovantes, relatórios e alertas de estoque. <br>
Exemplo:Comprovante Venda #1 <br>
Data: 2025-05-12 <br>
Total: R$15.00 <br>
Pagamento: Pix <br>
Relatório de Vendas: <br>
Venda #1 | Data: 2025-05-12 | Total: R$15.00 | Pagamento: Pix <br>
Relatório de Estoque: <br>
Produto: Pão Francês | Estoque: 45 | Mínimo: 20 
