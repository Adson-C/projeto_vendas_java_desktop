package br.com.ads.dao;

import br.com.ads.jdbc.ConnectionFactory;
import br.com.ads.model.ItemVenda;
import br.com.ads.model.Produtos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro!" + e);
        }

    }

    // Lista Itens Vendidos de uma Venda por ID
    public List<ItemVenda> listaItensPorVendas(int venda_id) {

        try {

            List<ItemVenda> lista = new ArrayList<>();

            String sql = "select i.id, p.descricao, i.qtd, p.preco, i.subtotal from tb_itensvendas as i "
                            + " inner join tb_produtos as p on(i.produto_id = p.id) where i.venda_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, venda_id);
            
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                
                ItemVenda iTem = new ItemVenda();
                Produtos prod = new Produtos();
                
                iTem.setId(rs.getInt("i.id"));
                prod.setDescricao(rs.getString("p.descricao"));
                iTem.setQtd(rs.getInt("i.qtd"));
                prod.setPreco(rs.getDouble("p.preco"));
                iTem.setSubTotal(rs.getDouble("i.subtotal"));
                
                iTem.setProduto(prod);

                lista.add(iTem);
            }

            return lista;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro: " + e);
            return null;
        }
    }
    
}
