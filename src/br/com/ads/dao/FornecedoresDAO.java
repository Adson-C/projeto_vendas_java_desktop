package br.com.ads.dao;

import br.com.ads.jdbc.ConnectionFactory;
import br.com.ads.model.Fornecedores;
import br.com.ads.utils.WebServiceCep;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class FornecedoresDAO {

    private Connection conn;

    public FornecedoresDAO() {
        this.conn = new ConnectionFactory().getConnection();
    }

    public void cadastrarFornecedores(Fornecedores obj) {

        try {
            // sql de para salvar novo cliente
            String sql = "insert into tb_fornecedores(nome,cnpj,email,telefone,celular,cep,endereco,numero,complemento,bairro,cidade,estado) "
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, obj.getNome());
            ps.setString(2, obj.getCnpj());
            ps.setString(3, obj.getEmail());
            ps.setString(4, obj.getTelefone());
            ps.setString(5, obj.getCelular());
            ps.setString(6, obj.getCep());
            ps.setString(7, obj.getEndereco());
            ps.setInt(8, obj.getNumero());
            ps.setString(9, obj.getComplemento());
            ps.setString(10, obj.getBairro());
            ps.setString(11, obj.getCidade());
            ps.setString(12, obj.getUf());

            ps.execute();
            ps.close();

            JOptionPane.showMessageDialog(null, "Fornecedor Cadastrado com Sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);

        }
    }

    public void excluirFornecedores(Fornecedores obj) {

        try {
            // sql de para salvar novo cliente
            String sql = "delete from tb_fornecedores where id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, obj.getId());

            ps.execute();
            ps.close();

            //JOptionPane.showMessageDialog(null, "Cliente Excluido com Sucesso! ");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);

        }

    }

    public void alterarFornecedores(Fornecedores obj) {

        try {
            // sql de para salvar novo cliente
            String sql = "update tb_fornecedores set nome=?, cnpj=?, email=?, telefone=?, celular=?, "
                    + "cep=?, endereco=?, numero=?, complemento=?, bairro=?, cidade=?, estado=? where id =?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, obj.getNome());
            ps.setString(2, obj.getCnpj());
            ps.setString(3, obj.getEmail());
            ps.setString(4, obj.getTelefone());
            ps.setString(5, obj.getCelular());
            ps.setString(6, obj.getCep());
            ps.setString(7, obj.getEndereco());
            ps.setInt(8, obj.getNumero());
            ps.setString(9, obj.getComplemento());
            ps.setString(10, obj.getBairro());
            ps.setString(11, obj.getCidade());
            ps.setString(12, obj.getUf());

            ps.setInt(13, obj.getId());

            ps.execute();
            ps.close();

            JOptionPane.showMessageDialog(null, "Fornecedor Alterado com Sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);

        }

    }

    public List<Fornecedores> listarFornecedores() {

        try {

            List<Fornecedores> lista = new ArrayList<>();

            String sql = "select * from tb_fornecedores";

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Fornecedores obj = new Fornecedores();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCnpj(rs.getString("cnpj"));
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
            JOptionPane.showMessageDialog(null, "erro: " + e);
            return null;
        }
    }
    
      public  List<Fornecedores>buscaFornecedoresPorNome(String nome) {
         
         
         try {
             
             List<Fornecedores> lista = new ArrayList<>();
             
             String sql = "select * from tb_fornecedores where nome like ?";
             
             PreparedStatement ps = conn.prepareStatement(sql);
             
             ps.setString(1, nome); // buscar por nome
             
             ResultSet rs = ps.executeQuery();
             
             while(rs.next()) {
                 Fornecedores obj = new Fornecedores();
                 
                 obj.setId(rs.getInt("id"));
                 obj.setNome(rs.getString("nome"));
                 obj.setCnpj(rs.getString("cnpj"));
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

      public Fornecedores consultarPorNome(String nome) {
         try {
             
             String sql = "select * from tb_fornecedores where nome=?";
             
             PreparedStatement ps = conn.prepareStatement(sql);
             
             ps.setString(1, nome);
             
             ResultSet rs = ps.executeQuery();
             
             Fornecedores obj = new Fornecedores();
             
             if(rs.next()) {
                 
                 obj.setId(rs.getInt("id"));
                 obj.setNome(rs.getString("nome"));
                 obj.setCnpj(rs.getString("cnpj"));
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
             JOptionPane.showMessageDialog(null, "Fornecedor não encontrado!");
             return null;
         }
     }
     public Fornecedores buscarCep(String cep) {
         // buscar cep
       WebServiceCep webServiceCep = WebServiceCep.searchCep(cep);  
         
      Fornecedores obj = new Fornecedores();
      
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
