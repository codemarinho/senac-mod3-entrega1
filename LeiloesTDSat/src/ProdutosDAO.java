import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProdutosDAO {
    private Connection conn;

    public boolean venderProduto(int idProduto) {
        PreparedStatement stmt = null;

        try {
            conn = Conexao.getConexao(); //conexão

            // SQL para atualizar o status do produto para "Vendido"
            String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idProduto);

            // Executa a atualização
            int rowsUpdated = stmt.executeUpdate();

            // Verifica se a atualização foi bem-sucedida
            if (rowsUpdated > 0) {
                System.out.println("Produto vendido com sucesso!");
                return true;
            } else {
                System.out.println("Não foi possível vender o produto. Produto não encontrado.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao vender o produto: " + e.getMessage());
            return false;
        } finally {
            // Feche o PreparedStatement
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

