package com.alg3.minhaconsulta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.alg3.minhaconsulta.model.Paciente;

public class PacienteDAO {

    public void cadastrarPaciente(Paciente paciente) throws ExceptionDAO {
        String sql = "INSERT INTO paciente (nome, data_nascimento, endereco, telefone, convenio, cpf, genero) VALUES(?,?,?,?,?,?,?)";

        PreparedStatement pStatement = null;
        Connection connection = null;

        try {
            connection = new ConnectionDAO().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, paciente.getNome());
            pStatement.setString(2, paciente.getData_nascimento());
            pStatement.setString(3, paciente.getEndereco());
            pStatement.setString(4, paciente.getTelefone());
            pStatement.setString(5, paciente.getConvenio());
            pStatement.setString(6, paciente.getCpf());
            pStatement.setString(7, paciente.getGenero());

            System.out.println("Dados recebidos no DAO: " + paciente.getNome() + ", " + paciente.getEndereco());

            pStatement.executeUpdate();
            System.out.println("Paciente cadastrado com sucesso no banco de dados.");
        } catch (SQLException ex) {
            throw new ExceptionDAO("Erro ao cadastrar paciente. Erro " + ex);
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