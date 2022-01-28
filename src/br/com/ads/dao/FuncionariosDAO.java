
package br.com.ads.dao;

import br.com.ads.jdbc.ConnectionFactory;
import br.com.ads.model.Funcionarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class FuncionariosDAO {
    
     private Connection conn;
    
    public FuncionariosDAO() {
        this.conn = new ConnectionFactory().getConnection();
    }
    
     public void cadastrarFuncionarios(Funcionarios obj) {
        
        try {
            // sql de para salvar novo cliente
            String sql = "insert into tb_funcionarios(nome,rg,cpf,email,senha,cargo,nivel_acesso,telefone,celular,cep,endereco,numero,complemento,bairro,cidade,estado) "
                                         + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            
            PreparedStatement ps = conn.prepareStatement(sql);
           ps.setString(1, obj.getNome());
            ps.setString(2, obj.getRg());
            ps.setString(3, obj.getCpf());
            ps.setString(4, obj.getEmail());
            ps.setString(5, obj.getSenha());
            ps.setString(6, obj.getCargo());
            ps.setString(7, obj.getNivelAcesso());
            ps.setString(8, obj.getTelefone());
            ps.setString(9, obj.getCelular());
            ps.setString(10, obj.getCep());
            ps.setString(11, obj.getEndereco());
            ps.setInt(12, obj.getNumero());
            ps.setString(13, obj.getComplemento());
            ps.setString(14, obj.getBairro());
            ps.setString(15, obj.getCidade());
            ps.setString(16, obj.getUf());
            
            ps.execute();
            ps.close();
            
            JOptionPane.showMessageDialog(null, "Funcionário Cadastrado com Sucesso!");
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);
            
        }
    }

}
