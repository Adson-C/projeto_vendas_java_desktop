
package br.com.ads.dao;

import br.com.ads.jdbc.ConnectionFactory;
import br.com.ads.model.Vendas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class VendasDAO {

    private Connection conn;

    public VendasDAO() {
        this.conn = new ConnectionFactory().getConnection();
    }
    
    // Efetuar uma Venda
    public void cadastrarVendas(Vendas obj) {
        
        try {
            
            String sql = "insert into tb_vendas(cliente_id, data_venda,total_venda,observacoes)values(?,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, obj.getCliente().getId());
            ps.setString(2, obj.getData_venda());
            ps.setDouble(3, obj.getTotal_venda());

            ps.setString(4, obj.getObs());

            ps.execute();
            ps.close();


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro!" + e);
        }
     
    }
    
    // Retorna a ultima Venda
    public int retornaUltimaVenda() {
        try {
            int idVenda = 0;
            
            String sql = "select max(id) id from tb_vendas";

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()) {
                Vendas p = new Vendas();
                p.setId(rs.getInt("id"));
                
                idVenda = p.getId();
                
            }
            
            return idVenda;
            
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
