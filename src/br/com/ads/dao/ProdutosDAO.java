package br.com.ads.dao;

import br.com.ads.jdbc.ConnectionFactory;
import br.com.ads.model.Fornecedores;
import br.com.ads.model.Produtos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ProdutosDAO {

    private Connection conn;

    public ProdutosDAO() {
        this.conn = new ConnectionFactory().getConnection();
    }

    public void cadastrarProdutos(Produtos obj) {
        try {

            String sql = "insert into tb_produtos(descricao, preco,qtd_estoque,for_id)values(?,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, obj.getDescricao());
            ps.setDouble(2, obj.getPreco());
            ps.setInt(3, obj.getQtd_Estoque());

            ps.setInt(4, obj.getFornecedores().getId()); // obejto de fornecedores 

            ps.execute();
            ps.close();

            JOptionPane.showMessageDialog(null, "Produto Cadastrado com Sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro!" + e);
        }
    }

    public void alterarProdutos(Produtos obj) {
        try {

            String sql = "update tb_produtos set descricao=?, preco=?,qtd_estoque=?,for_id=? where id=?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, obj.getDescricao());
            ps.setDouble(2, obj.getPreco());
            ps.setInt(3, obj.getQtd_Estoque());

            ps.setInt(4, obj.getFornecedores().getId()); // obejto de fornecedores 
            ps.setInt(5, obj.getId());

            ps.execute();
            ps.close();

            JOptionPane.showMessageDialog(null, "Produto Alterado com Sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro!" + e);
        }
    }

    public void excluirProdutos(Produtos obj) {

        try {
            // sql de para salvar novo cliente
            String sql = "delete from tb_produtos where id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, obj.getId());

            ps.execute();
            ps.close();

            //JOptionPane.showMessageDialog(null, "Cliente Excluido com Sucesso! ");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);

        }

    }

    public List<Produtos> listarProdutos() {

        try {

            List<Produtos> lista = new ArrayList<>();

            String sql = "select p.id, p.descricao, p.preco, p.qtd_estoque, f.nome from tb_produtos "
                    + " as p inner join tb_fornecedores as f on (p.for_id = f.id);";

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Produtos obj = new Produtos();
                Fornecedores f = new Fornecedores();

                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_Estoque(rs.getInt("p.qtd_estoque"));

                f.setNome(rs.getString("f.nome"));

                obj.setFornecedores(f);

                lista.add(obj);
            }

            return lista;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro: " + e);
            return null;
        }
    }

    public List<Produtos> buscaProdutosPorNome(String nome) {

        try {

            List<Produtos> lista = new ArrayList<>();

            String sql = "select p.id, p.descricao, p.preco, p.qtd_estoque, f.nome from tb_produtos "
                    + " as p inner join tb_fornecedores as f on (p.for_id = f.id) where p.descricao like ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, nome);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Produtos obj = new Produtos();
                Fornecedores f = new Fornecedores();

                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_Estoque(rs.getInt("p.qtd_estoque"));

                f.setNome(rs.getString("f.nome"));

                obj.setFornecedores(f);

                lista.add(obj);
            }

            return lista;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro: " + e);
            return null;
        }
    }

    public Produtos consultarPorNome(String nome) {
        
        try {
            String sql = "select p.id, p.descricao, p.preco, p.qtd_estoque, f.nome from tb_produtos "
                    + " as p inner join tb_fornecedores as f on (p.for_id = f.id) where p.descricao= ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, nome);

            ResultSet rs = ps.executeQuery();
            Produtos obj = new Produtos();
            Fornecedores f = new Fornecedores();

            if (rs.next()) {                
                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_Estoque(rs.getInt("p.qtd_estoque"));

                f.setNome(rs.getString("f.nome"));

                obj.setFornecedores(f);

            }
            return obj;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro: " + e);
            return null;
        }
    }
    
    // Buscar Produto por Código
    public Produtos buscaPorCodigo(int id) {
        
        try {
            String sql = "select * from tb_produtos where id=?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            Produtos obj = new Produtos();
            Fornecedores f = new Fornecedores();

            if (rs.next()) {
                obj.setId(rs.getInt("id"));
                obj.setDescricao(rs.getString("descricao"));
                obj.setPreco(rs.getDouble("preco"));
                obj.setQtd_Estoque(rs.getInt("qtd_estoque"));

            }else {
                JOptionPane.showMessageDialog(null, "Produto não encontrado!");
            }
            return obj;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro "+ e);
            return null;
        }
    }
    
    // Dar baixa no Estoque
    public void baixaEstoque(int id, int qtd_nova) {
        
        try {
            String sql = "update tb_produtos set qtd_estoque =? where id=?";

            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, qtd_nova);
            ps.setInt(2, id);
            
            ps.execute();
            ps.close();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Erro: " + e);
            
        }
    }  

    // Retorna Estoque artual 
    
    public int retornaEsttoqueAtual(int id) {
        
        try {
            int qtd_estoque = 0;
            
            String sql = "select qtd_estoque from tb_produtos where id=?";

            PreparedStatement ps = conn.prepareStatement(sql);
           
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()) {
                
                Produtos p = new Produtos();
                
                qtd_estoque = (rs.getInt("qtd_estoque"));
            }
            return qtd_estoque;
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
            
        }
        
    }
    public void adiconarEstouqe(int id, int qtd_nova) {
        
        try {
            String sql = "update tb_produtos set qtd_estoque =? where id=?";

            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, qtd_nova);
            ps.setInt(2, id);
            
            ps.execute();
            ps.close();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Erro: " + e);
            
        }
    }  
}
