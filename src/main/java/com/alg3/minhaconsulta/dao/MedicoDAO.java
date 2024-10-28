/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alg3.minhaconsulta.dao;

import com.alg3.minhaconsulta.model.Medico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;



/**
 *
 * @author André Micael Sampaio Pinto <andre at alg3.org>
 */
public class MedicoDAO {
    
    public void cadastrarMedico(Medico medico) throws ExceptionDAO {
        String sql = "INSERT INTO medico (nome, data_nascimento, endereco, telefone, especialidade, crm, genero) VALUES(?,?,?,?,?,?,?)";
        PreparedStatement pStatement = null;
        Connection connection = null;
        
            try {
            connection = new ConnectionDAO().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, medico.getNome());
            pStatement.setString(2, medico.getData_nascimento());
            pStatement.setString(3, medico.getEndereco());
            pStatement.setString(4, medico.getTelefone());
            pStatement.setString(5, medico.getEspecialidade());
            pStatement.setString(6, medico.getCrm());
            pStatement.setString(7, medico.getGenero());

            System.out.println("Dados recebidos no DAO: " + medico.getNome() + ", " + medico.getEndereco());

            pStatement.executeUpdate();
            System.out.println("Médico cadastrado com sucesso no banco de dados.");
        } catch (SQLException ex) {
            throw new ExceptionDAO("Erro ao cadastrar Médico. Erro " + ex);
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
    

