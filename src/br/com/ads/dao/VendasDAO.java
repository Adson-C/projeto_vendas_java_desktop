
package br.com.ads.dao;

import br.com.ads.jdbc.ConnectionFactory;
import br.com.ads.model.Clientes;
import br.com.ads.model.Vendas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
    
    // Filtrar por Datas
    public List<Vendas> listaVendasPorPeriodo(LocalDate data_inicio,LocalDate data_fim) {

        try {

            List<Vendas> lista = new ArrayList<>();

            String sql = "select v.id, date_format(v.data_venda,'%d/%m/%Y')as data_formatada, c.nome, v.total_venda, v.observacoes from tb_vendas as v "
                            + "inner join tb_clientes as c on(v.cliente_id = c.id) where v.data_venda between ? and ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, data_inicio.toString());
            ps.setString(2, data_fim.toString());
            
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Vendas obj = new Vendas();
                Clientes c = new Clientes();

                obj.setId(rs.getInt("v.id"));
                obj.setData_venda(rs.getString("data_formatada"));
                c.setNome(rs.getString("c.nome"));
                obj.setTotal_venda(rs.getDouble("v.total_venda"));
                obj.setObs(rs.getString("v.observacoes"));

                obj.setCliente(c);

                lista.add(obj);
            }

            return lista;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro: " + e);
            return null;
        }
    }
    
}
