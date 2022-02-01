package br.com.ads.dao;

import br.com.ads.jdbc.ConnectionFactory;
import br.com.ads.model.ItemVenda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ItemVendaDAO {

    private Connection conn;

    public ItemVendaDAO() {
        this.conn = new ConnectionFactory().getConnection();
    }

    // Cadastrar Item
    public void cadastrarItem(ItemVenda obj) {

        try {

            String sql = "insert into tb_itensvendas(venda_id, produto_id,qtd,subtotal)values(?,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, obj.getVenda().getId());
            ps.setInt(2, obj.getProduto().getId());
            ps.setInt(3, obj.getQtd());

            ps.setDouble(4, obj.getSubTotal());

            ps.execute();
            ps.close();

            JOptionPane.showMessageDialog(null, "Item Registrada com Sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro!" + e);
        }

    }

}
