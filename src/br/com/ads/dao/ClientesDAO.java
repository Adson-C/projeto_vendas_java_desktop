package br.com.ads.dao;

import br.com.ads.jdbc.ConnectionFactory;
import br.com.ads.model.Clientes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class ClientesDAO extends Clientes{
    
    private Connection conn;
    
    public ClientesDAO() {
        this.conn = new ConnectionFactory().getConnection();
    }

    public void cadastrarCleinte(Clientes obj) {
        
        try {
            // sql de para salvar novo cliente
            String sql = "insert into tb_clientes (nome,rg,cpf,email,telefone,celular,cep,endereco,numero,complemento,bairro,cidade,estado) "
                                         + "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            
            PreparedStatement ps = conn.prepareStatement(sql);
           ps.setString(1, obj.getNome());
            ps.setString(2, obj.getRg());
            ps.setString(3, obj.getCpf());
            ps.setString(4, obj.getEmail());
            ps.setString(5, obj.getTelefone());
            ps.setString(6, obj.getCelular());
            ps.setString(7, obj.getCep());
            ps.setString(8, obj.getEndereco());
            ps.setInt(9, obj.getNumero());
            ps.setString(10, obj.getComplemento());
            ps.setString(11, obj.getBairro());
            ps.setString(12, obj.getCidade());
            ps.setString(13, obj.getUf());
            
            ps.execute();
            ps.close();
            
            JOptionPane.showMessageDialog(null, "Cliente Cadastrado com Sucesso!");
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);
            
        }
    }
    
    public void alterarCleinte(Clientes obj) {
        
        try {
            // sql de para salvar novo cliente
            String sql = "update tb_clientes set nome=?, rg=?, cpf=?, email=?, telefone=?, celular=?, "
                           + "cep=?, endereco=?, numero=?, complemento=?, bairro=?, cidade=?, estado=? where id =?";
            
            PreparedStatement ps = conn.prepareStatement(sql);
           ps.setString(1, obj.getNome());
            ps.setString(2, obj.getRg());
            ps.setString(3, obj.getCpf());
            ps.setString(4, obj.getEmail());
            ps.setString(5, obj.getTelefone());
            ps.setString(6, obj.getCelular());
            ps.setString(7, obj.getCep());
            ps.setString(8, obj.getEndereco());
            ps.setInt(9, obj.getNumero());
            ps.setString(10, obj.getComplemento());
            ps.setString(11, obj.getBairro());
            ps.setString(12, obj.getCidade());
            ps.setString(13, obj.getUf());
            
            ps.setInt(14, obj.getId());
            
            ps.execute();
            ps.close();
            
            JOptionPane.showMessageDialog(null, "Cliente Alterado com Sucesso!");
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);
            
        }
        
    }
      
     public void excluirCleinte(Clientes obj) {
       
                try {
            // sql de para salvar novo cliente
            String sql = "delete from tb_clientes where id = ?";
            
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, obj.getId());
            
            ps.execute();
            ps.close();
            
         //JOptionPane.showMessageDialog(null, "Cliente Excluido com Sucesso! ");
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);
            
        }
    
        
    }
     
     // Listar Todos Clientes
     public  List<Clientes>listarClientes() {
         
         try {
             
             List<Clientes> lista = new ArrayList<>();
             
             String sql = "select * from tb_clientes";
             
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();
             
             while(rs.next()) {
                 Clientes obj = new Clientes();
                 
                 obj.setId(rs.getInt("id"));
                 obj.setNome(rs.getString("nome"));
                 obj.setRg(rs.getString("rg"));
                 obj.setCpf(rs.getString("cpf"));
                 obj.setEmail(rs.getString("email"));
                 obj.setTelefone(rs.getString("telefone"));
                 obj.setCelular(rs.getString("celular"));
                 obj.setCep(rs.getString("cep"));
                 obj.setEndereco(rs.getString("endereco"));
                 obj.setNumero(rs.getInt("numero"));
                 obj.setComplemento(rs.getString("complemento"));
                 obj.setBairro(rs.getString("bairro"));
                 obj.setCidade(rs.getString("cidade"));
                 obj.setUf(rs.getString("estado"));
                 
                 lista.add(obj);
             }
             
             return lista;
             
         } catch (SQLException e) {
             JOptionPane.showMessageDialog(null,"erro: " + e);
             return null;
         }
         
         
         
     }
    
}
