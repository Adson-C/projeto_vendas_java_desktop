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
}
