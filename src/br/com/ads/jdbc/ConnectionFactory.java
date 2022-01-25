package br.com.ads.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectionFactory {
    
    public Connection getConnection() {
        
        try {
            
            return DriverManager.getConnection("");
            
        } catch (Exception erro) {
            throw new RuntimeException(erro);
        }
        
    }

}
