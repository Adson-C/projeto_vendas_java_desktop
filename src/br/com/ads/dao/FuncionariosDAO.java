package br.com.ads.dao;

import br.com.ads.jdbc.ConnectionFactory;
import br.com.ads.model.Funcionarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

    public void alterarFuncionario(Funcionarios obj) {

        try {
            // sql de para salvar novo cliente
            String sql = "update tb_funcionarios set nome=?, rg=?, cpf=?, email=?, senha=?, cargo=?, nivel_acesso=?, telefone=?, celular=?, "
                    + "cep=?, endereco=?, numero=?, complemento=?, bairro=?, cidade=?, estado=? where id =?";

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

            ps.setInt(17, obj.getId());

            ps.execute();
            ps.close();

            JOptionPane.showMessageDialog(null, "Funcionário Alterado com Sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);

        }

    }

    public void excluirFuncionario(Funcionarios obj) {

        try {
            // sql de para salvar novo cliente
            String sql = "delete from tb_funcionarios where id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, obj.getId());

            ps.execute();
            ps.close();

            //JOptionPane.showMessageDialog(null, "Cliente Excluido com Sucesso! ");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);

        }

    }

    public List<Funcionarios> listarFuncionarios() {

        try {

            List<Funcionarios> lista = new ArrayList<>();

            String sql = "select * from tb_funcionarios";

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Funcionarios obj = new Funcionarios();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));

                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivelAcesso(rs.getString("nivel_acesso"));

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

    public List<Funcionarios> listaFuncionarioPorNome(String nome) {

        try {

            List<Funcionarios> lista = new ArrayList<>();

            String sql = "select * from tb_funcionarios where nome like ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, nome); // buscar por nome

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Funcionarios obj = new Funcionarios();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivelAcesso(rs.getString("nivel_acesso"));
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

    public Funcionarios consultarPorNome(String nome) {
        try {

            String sql = "select * from tb_funcionarios where nome=?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, nome);

            ResultSet rs = ps.executeQuery();

            Funcionarios obj = new Funcionarios();

            if (rs.next()) {

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivelAcesso(rs.getString("nivel_acesso"));

                
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
            JOptionPane.showMessageDialog(null, "Funcionários não encontrado!");
            return null;
        }
    }

}
