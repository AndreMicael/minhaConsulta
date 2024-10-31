package com.alg3.minhaconsulta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.alg3.minhaconsulta.model.Consulta;


public class ConsultaDAO {

    public void cadastrarConsulta(Consulta consulta) throws ExceptionDAO {
        String sql = "INSERT INTO consulta (paciente_id, medico_id, data_consulta, valor,status,observacoes) VALUES(?,?,?,?,?,?)";

        PreparedStatement pStatement = null;
        Connection connection = null;

        try {
            connection = new ConnectionDAO().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, consulta.getPacienteId());
            pStatement.setInt(2, consulta.getMedicoId());
            pStatement.setString(3, consulta.getDataConsulta());
            pStatement.setDouble(4, consulta.getValor());
            pStatement.setString(5, consulta.getStatus());
             pStatement.setString(6, consulta.getObservacoes());
          

            System.out.println("Dados recebidos no DAO: " + consulta.getObservacoes() + ", " + consulta.getStatus());

            pStatement.executeUpdate();
            System.out.println("Consulta cadastrada com sucesso no banco de dados.");
        } catch (SQLException ex) {
            throw new ExceptionDAO("Erro ao cadastrar consulta. Erro " + ex);
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