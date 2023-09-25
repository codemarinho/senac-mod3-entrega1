import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutosDAO {
    private Connection conn;


    public ArrayList<ProdutosDTO> listarProdutosVendidos() {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<ProdutosDTO> produtosVendidos = new ArrayList<>();

        try {
            conn = Conexao.getConexao(); //faltou testar o a conexão com o banco aqui

            // SQL para selecionar produtos com status "Vendido"
            String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";

            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));
                produtosVendidos.add(produto);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar produtos vendidos: " + e.getMessage());
        } finally {
            // Feche o ResultSet e o PreparedStatement
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return produtosVendidos;
    }

}

