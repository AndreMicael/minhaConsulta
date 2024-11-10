package com.alg3.minhaconsulta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.alg3.minhaconsulta.model.Prontuario;
import java.util.ArrayList;

public class ProntuarioDAO {
  public void cadastrarProntuario(Prontuario prontuario) throws ExceptionDAO {
    String sql = "INSERT INTO prontuario (paciente_id, medico_id, data_registro, observacoes, exames, historico_medico) VALUES(?,?,?,?,?,?)";

    PreparedStatement pStatement = null;
    Connection connection = null;

    try {
      connection = new ConnectionDAO().getConnection();
      pStatement = connection.prepareStatement(sql);
      pStatement.setInt(1, prontuario.getPacienteId());
      pStatement.setInt(2, prontuario.getMedicoId());
      pStatement.setString(3, prontuario.getDataRegistro());
      pStatement.setString(4, prontuario.getObservacoes());
      pStatement.setString(5, prontuario.getExames());
      pStatement.setString(6, prontuario.getHistoricoMedico());

      pStatement.executeUpdate();
      System.out.println("Prontuario cadastrado com sucesso no banco de dados.");
    } catch (SQLException ex) {
      throw new ExceptionDAO("Erro ao cadastrar prontuario. Erro " + ex);
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

  public void deletarProntuario(Prontuario prontuario) throws ExceptionDAO {
    String sql = "DELETE FROM prontuario WHERE prontuario_id = ?";

    PreparedStatement pStatement = null;
    Connection connection = null;

    try {
      connection = new ConnectionDAO().getConnection();
      pStatement = connection.prepareStatement(sql);
      pStatement.setInt(1, prontuario.getId());
      pStatement.execute();

      System.out.println("Prontuario deletado com sucesso no banco de dados.");
    } catch (SQLException ex) {
      throw new ExceptionDAO("Erro ao deletar prontuario. Erro " + ex);
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

  public void editarProntuario(Prontuario prontuario) throws ExceptionDAO {
    String sql = "UPDATE prontuario SET paciente_id = ?, medico_id = ?, data_registro = ?, observacoes = ?, exames = ?, historico_medico = ? WHERE prontuario_id = ?";

    PreparedStatement pStatement = null;
    Connection connection = null;

    try {
      connection = new ConnectionDAO().getConnection();
      pStatement = connection.prepareStatement(sql);
      pStatement.setInt(1, prontuario.getPacienteId());
      pStatement.setInt(2, prontuario.getMedicoId());
      pStatement.setString(3, prontuario.getDataRegistro());
      pStatement.setString(4, prontuario.getObservacoes());
      pStatement.setString(5, prontuario.getExames());
      pStatement.setString(6, prontuario.getHistoricoMedico());
      pStatement.setInt(7, prontuario.getId());

      pStatement.executeUpdate();
      System.out.println("Prontuario editado com sucesso no banco de dados.");
    } catch (SQLException ex) {
      throw new ExceptionDAO("Erro ao editar prontuario. Erro " + ex);
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

  public ArrayList < Prontuario > listarProntuarios(String observacoes) throws ExceptionDAO {
    String sql = "SELECT * FROM prontuario WHERE observacoes LIKE ? ORDER BY prontuario_id";

    Connection connection = null;
    PreparedStatement pStatement = null;
    ArrayList < Prontuario > listaProntuarios = new ArrayList < > ();

    try {
      connection = new ConnectionDAO().getConnection();
      pStatement = connection.prepareStatement(sql);
      pStatement.setString(1, "%" + observacoes + "%");
      ResultSet rs = pStatement.executeQuery();

      while (rs.next()) {
        Prontuario prontuario = new Prontuario();
        prontuario.setId(rs.getInt("prontuario_id"));
        prontuario.setPacienteId(rs.getInt("paciente_id"));
        prontuario.setMedicoId(rs.getInt("medico_id"));
        prontuario.setDataRegistro(rs.getString("data_registro"));
        prontuario.setObservacoes(rs.getString("observacoes"));
        prontuario.setExames(rs.getString("exames"));
        prontuario.setHistoricoMedico(rs.getString("historico_medico"));
        listaProntuarios.add(prontuario);
      }

    } catch (SQLException ex) {
      throw new ExceptionDAO("Erro ao listar prontuarios. Erro " + ex);
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

    return listaProntuarios;
  }

  public ArrayList < Prontuario > listarProntuariosId(int prontuarioId) throws ExceptionDAO {
    String sql = "SELECT * FROM prontuario WHERE prontuario_id = ?";

    Connection connection = null;
    PreparedStatement pStatement = null;
    ArrayList < Prontuario > listaProntuarios = new ArrayList < > ();

    try {
      connection = new ConnectionDAO().getConnection();
      pStatement = connection.prepareStatement(sql);
      pStatement.setInt(1, prontuarioId);
      ResultSet rs = pStatement.executeQuery();

      while (rs.next()) {
        Prontuario prontuario = new Prontuario();
        prontuario.setId(rs.getInt("prontuario_id"));
        prontuario.setPacienteId(rs.getInt("paciente_id"));
        prontuario.setMedicoId(rs.getInt("medico_id"));
        prontuario.setDataRegistro(rs.getString("data_registro"));
        prontuario.setObservacoes(rs.getString("observacoes"));
        prontuario.setExames(rs.getString("exames"));
        prontuario.setHistoricoMedico(rs.getString("historico_medico"));
        listaProntuarios.add(prontuario);
      }

    } catch (SQLException ex) {
      throw new ExceptionDAO("Erro ao listar prontuarios. Erro " + ex);
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

    return listaProntuarios;
  }
}