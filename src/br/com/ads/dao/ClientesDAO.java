package br.com.ads.dao;

import br.com.ads.jdbc.ConnectionFactory;
import br.com.ads.model.Clientes;
import br.com.ads.utils.WebServiceCep;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.text.html.HTML;


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
    
     public  List<Clientes>buscaClientePorNome(String nome) {
         
         
         try {
             
             List<Clientes> lista = new ArrayList<>();
             
             String sql = "select * from tb_clientes where nome like ?";
             
             PreparedStatement ps = conn.prepareStatement(sql);
             
             ps.setString(1, nome); // buscar por nome
             
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
     
     // Buscar Por Nome
     public Clientes consultarPorNome(String nome) {
         try {
             
             String sql = "select * from tb_clientes where nome=?";
             
             PreparedStatement ps = conn.prepareStatement(sql);
             
             ps.setString(1, nome);
             
             ResultSet rs = ps.executeQuery();
             
             Clientes obj = new Clientes();
             
             if(rs.next()) {
                 
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
                 
             }
             
             return obj;
             
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
             return null;
         }
     }
     
     // Buscar Por CPF
     public Clientes buscarPorCpf(String cpf) {
         try {
             
             String sql = "select * from tb_clientes where cpf=?";
             
             PreparedStatement ps = conn.prepareStatement(sql);
             
             ps.setString(1, cpf);
             
             ResultSet rs = ps.executeQuery();
             
             Clientes obj = new Clientes();
             
             if(rs.next()) {
                 
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
                 
             }
             
             return obj;
             
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
             return null;
         }
     }
     
     public Clientes buscarCep(String cep) {
         // buscar cep
       WebServiceCep webServiceCep = WebServiceCep.searchCep(cep);  
         
      Clientes obj = new Clientes();
      
      if (webServiceCep.wasSuccessful()) {
          obj.setEndereco(webServiceCep.getLogradouroFull());
          obj.setCidade(webServiceCep.getCidade());
          obj.setBairro(webServiceCep.getBairro());
          obj.setUf(webServiceCep.getUf());
          return obj;
      }else{
         JOptionPane.showMessageDialog(null, "Erro Numero: " + webServiceCep.getResulCode());
         JOptionPane.showMessageDialog(null, "Descrição do erro: " + webServiceCep.getResultText());
          return null;
      }    
     }
}
