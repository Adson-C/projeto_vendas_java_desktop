package br.com.ads.dao;

import br.com.ads.jdbc.ConnectionFactory;
import br.com.ads.model.Clientes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class ClientesDAO {
    
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
    
    public void alterarCleinte() {
        
    }
      
     public void excluirCleinte() {
        
    }
    
}
