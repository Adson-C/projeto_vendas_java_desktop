package br.com.ads.jdbc;

import javax.swing.JOptionPane;


public class TestaConexao {
    
    public static void main(String[] args) {
        
        try {
            
            new ConnectionFactory().getConnection();
            JOptionPane.showMessageDialog(null, "Connectado com Sucesso!");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "COps Erro!" + e);
        }
    }

}
