package br.com.ads.dao;

import br.com.ads.jdbc.ConnectionFactory;
import br.com.ads.model.Produtos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

}
