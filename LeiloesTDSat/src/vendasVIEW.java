import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class VendasVIEW extends javax.swing.JFrame {
    
    private javax.swing.JTable listaProdutosVendidos;

    public VendasVIEW() {
        initComponents();
        listarProdutosVendidos();
    }

    private void listarProdutosVendidos() {
        ProdutosDAO produtosdao = new ProdutosDAO();

        DefaultTableModel model = (DefaultTableModel) listaProdutosVendidos.getModel();
        model.setNumRows(0);

        ArrayList<ProdutosDTO> produtosVendidos = produtosdao.listarProdutosVendidos();

        for (int i = 0; i < produtosVendidos.size(); i++) {
            model.addRow(new Object[]{
                produtosVendidos.get(i).getId(),
                produtosVendidos.get(i).getNome(),
                produtosVendidos.get(i).getValor(),
                produtosVendidos.get(i).getStatus()
            });
        }
    }

// Não consegui criar o forms, me perdi :/
}

