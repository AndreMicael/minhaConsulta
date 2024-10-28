package com.alg3.minhaconsulta.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ConnectionDAO class to manage database connections.
 */
public class ConnectionDAO {
    
    public Connection getConnection() {
        Connection conn = null;
        
        try {
            // Carregar o driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver JDBC carregado com sucesso.");
        } catch (ClassNotFoundException ex) {
            System.err.println("Erro ao carregar o driver JDBC: " + ex.getMessage());
            ex.printStackTrace();
        }
        
        try {
            // Estabelecer a conexão com o banco de dados
            String url = "jdbc:mysql://localhost:3308/minha_consulta?useSSL=false";
            String user = "root";
            String password = "root";
            System.out.println("Tentando conectar ao banco de dados com URL: " + url);
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexão estabelecida com sucesso.");
        } catch (SQLException ex) {
            System.err.println("Erro ao estabelecer a conexão com o banco de dados: " + ex.getMessage());
            ex.printStackTrace();
        }
        
        if (conn == null) {
            System.err.println("Conexão é nula. Verifique os detalhes de conexão.");
        }
        
        return conn;
    }
}