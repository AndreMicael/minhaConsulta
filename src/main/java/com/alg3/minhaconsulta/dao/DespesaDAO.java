package com.alg3.minhaconsulta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.alg3.minhaconsulta.model.Despesa;

public class DespesaDAO {

    public void cadastrarDespesa(Despesa despesa) throws ExceptionDAO {
        String sql = "INSERT INTO despesa (descricao, tipo, valor, data_registro) VALUES(?,?,?,?)";

        PreparedStatement pStatement = null;
        Connection connection = null;

        try {
            connection = new ConnectionDAO().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, despesa.getDescricao());
            pStatement.setString(2, despesa.getTipo());
            pStatement.setDouble(3, despesa.getValor());
            pStatement.setString(4, despesa.getDataRegistro());
          

            System.out.println("Dados recebidos no DAO: " + despesa.getDescricao() + ", " + despesa.getValor());

            pStatement.executeUpdate();
            System.out.println("Despesa cadastrado com sucesso no banco de dados.");
        } catch (SQLException ex) {
            throw new ExceptionDAO("Erro ao cadastrar despesa. Erro " + ex);
        } finally {
            try {
                if (pStatement != null) {
                    pStatement.close();
                }
            } catch (SQLException ex) {
                throw new ExceptionDAO("Erro ao fechar o Statement. Erro " + ex);
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                throw new ExceptionDAO("Erro ao fechar a conexão. Erro " + ex);
            }
        }
    }
}