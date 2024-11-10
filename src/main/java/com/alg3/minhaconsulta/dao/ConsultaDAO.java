package com.alg3.minhaconsulta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.alg3.minhaconsulta.model.Consulta;
 

import java.util.ArrayList;

public class ConsultaDAO {

    public void cadastrarConsulta(Consulta consulta) throws ExceptionDAO {
        String sql = "INSERT INTO consulta (paciente_id, medico_id, data_consulta, valor, status, observacoes) VALUES(?,?,?,?,?,?)";

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
                throw new ExceptionDAO("Erro ao fechar a conexão. Erro " + ex);}}
            

            }

            public void deletarConsulta(Consulta consulta) throws ExceptionDAO {
                String sql = "DELETE FROM consulta WHERE consulta_id = ?";

                PreparedStatement pStatement = null;
                Connection connection = null;

                try {
                    connection = new ConnectionDAO().getConnection();
                    pStatement = connection.prepareStatement(sql);
                    pStatement.setInt(1, consulta.getId());
                    pStatement.execute();

                    System.out.println("Consulta deletada com sucesso no banco de dados.");
                } catch (SQLException ex) {
                    throw new ExceptionDAO("Erro ao deletar consulta. Erro " + ex);
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

            public ArrayList<Consulta> listarConsultas(String nome) throws ExceptionDAO {
        String sql = "SELECT c.*, m.nome AS medico_nome, p.nome AS paciente_nome FROM consulta c "
                   + "JOIN medico m ON c.medico_id = m.medico_id "
                   + "JOIN paciente p ON c.paciente_id = p.paciente_id "
                   + "WHERE p.nome LIKE ? ORDER BY c.data_consulta";

        Connection connection = null;
        PreparedStatement pStatement = null;
        ArrayList<Consulta> listaConsultas = new ArrayList<>();

        try {
            connection = new ConnectionDAO().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, "%" + nome + "%");
            ResultSet rs = pStatement.executeQuery();

            while (rs.next()) {
                Consulta consulta = new Consulta();
                consulta.setId(rs.getInt("consulta_id"));
                consulta.setData(rs.getString("data_consulta"));
                consulta.setPacienteId(rs.getInt("paciente_id"));
                consulta.setMedicoId(rs.getInt("medico_id"));
                consulta.setValor(rs.getDouble("valor"));
                consulta.setStatus(rs.getString("status"));
                consulta.setObservacoes(rs.getString("observacoes"));
                consulta.setMedicoNome(rs.getString("medico_nome"));
                consulta.setPacienteNome(rs.getString("paciente_nome"));

                listaConsultas.add(consulta);
            }

        } catch (SQLException ex) {
            throw new ExceptionDAO("Erro ao listar consultas. Erro " + ex);
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

        return listaConsultas;
    }

    public ArrayList<Consulta> listarConsultasData(String data) throws ExceptionDAO {
        String sql = "SELECT c.*, m.nome AS medico_nome, p.nome AS paciente_nome FROM consulta c "
                   + "JOIN medico m ON c.medico_id = m.medico_id "
                   + "JOIN paciente p ON c.paciente_id = p.paciente_id "
                   + "WHERE c.data_consulta LIKE ? ORDER BY c.data_consulta DESC";
    
        Connection connection = null;
        PreparedStatement pStatement = null;
        ArrayList<Consulta> listaConsultas = new ArrayList<>();
    
        try {
            connection = new ConnectionDAO().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, "%" + data + "%");
            ResultSet rs = pStatement.executeQuery();
    
            while (rs.next()) {
                Consulta consulta = new Consulta();
                consulta.setId(rs.getInt("consulta_id"));
                consulta.setData(rs.getString("data_consulta"));
                consulta.setPacienteId(rs.getInt("paciente_id"));
                consulta.setMedicoId(rs.getInt("medico_id"));
                consulta.setValor(rs.getDouble("valor"));
                consulta.setStatus(rs.getString("status"));
                consulta.setObservacoes(rs.getString("observacoes"));
                consulta.setMedicoNome(rs.getString("medico_nome"));
                consulta.setPacienteNome(rs.getString("paciente_nome"));
    
                listaConsultas.add(consulta);
            }
    
        } catch (SQLException ex) {
            throw new ExceptionDAO("Erro ao listar consultas. Erro " + ex);
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
    
        return listaConsultas;
    }
}