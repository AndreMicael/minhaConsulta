/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alg3.minhaconsulta.dao;

import com.alg3.minhaconsulta.model.Medico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

  public ArrayList < Medico > listarMedicos(String nome) throws ExceptionDAO {
    String sql = "SELECT * FROM medico WHERE nome LIKE ? ORDER BY medico_id";

    Connection connection = null;
    PreparedStatement pStatement = null;
    ArrayList < Medico > listarMedicos = new ArrayList < > ();

    try {
      connection = new ConnectionDAO().getConnection();
      pStatement = connection.prepareStatement(sql);
      pStatement.setString(1, "%" + nome + "%");
      ResultSet rs = pStatement.executeQuery();

      while (rs.next()) {
        Medico medico = new Medico();
        medico.setId(rs.getInt("medico_id"));
        medico.setNome(rs.getString("nome"));
        medico.setData_nascimento(rs.getString("data_nascimento"));
        medico.setEndereco(rs.getString("endereco"));
        medico.setTelefone(rs.getString("telefone"));
        medico.setEspecialidade(rs.getString("especialidade"));
        medico.setCrm(rs.getString("crm"));
        medico.setGenero(rs.getString("genero"));
        listarMedicos.add(medico);

      }

    } catch (SQLException ex) {
      throw new ExceptionDAO("Erro ao listar medicos. Erro " + ex);
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

    return listarMedicos;
  }

  public ArrayList < Medico > listarMedicosId(int id) throws ExceptionDAO {
    ArrayList < Medico > medicos = new ArrayList < > ();
    Connection conn = null;
    PreparedStatement pstm = null;
    ResultSet rs = null;

    try {
      conn = new ConnectionDAO().getConnection();
      String sql = "SELECT * FROM medico WHERE medico_id = ?";
      pstm = conn.prepareStatement(sql);
      pstm.setInt(1, id);
      rs = pstm.executeQuery();

      while (rs.next()) {
        Medico medico = new Medico();
        medico.setId(rs.getInt("medico_id"));
        medico.setNome(rs.getString("nome"));
        medico.setData_nascimento(rs.getString("data_nascimento"));
        medico.setEndereco(rs.getString("endereco"));
        medico.setTelefone(rs.getString("telefone"));
        medico.setCrm(rs.getString("crm"));
        medico.setEspecialidade(rs.getString("especialidade"));
        medico.setGenero(rs.getString("genero"));
        medicos.add(medico);
      }
    } catch (SQLException e) {
      throw new ExceptionDAO("Erro ao consultar médico: " + e.getMessage());
    } finally {
      try {
        if (rs != null) rs.close();
        if (pstm != null) pstm.close();
        if (conn != null) conn.close();
      } catch (SQLException e) {
        throw new ExceptionDAO("Erro ao fechar conexão: " + e.getMessage());
      }
    }
    return medicos;
  }

  public boolean editarMedico(Medico medico) throws ExceptionDAO {
    String sql = "UPDATE medico SET nome = ?, data_nascimento = ?, endereco = ?, " +
      "telefone = ?, crm = ?, especialidade = ?, genero = ? WHERE medico_id = ?";
    Connection connection = null;
    PreparedStatement pStatement = null;

    try {
      connection = new ConnectionDAO().getConnection();
      pStatement = connection.prepareStatement(sql);

      // Lógica para converter o gênero
      String genero;
      if (medico.getGenero().equalsIgnoreCase("Feminino")) {
        genero = "F";
      } else if (medico.getGenero().equalsIgnoreCase("Masculino")) {
        genero = "M";
      } else {
        genero = "X";
      }

      pStatement.setString(1, medico.getNome());
      pStatement.setString(2, medico.getData_nascimento());
      pStatement.setString(3, medico.getEndereco());
      pStatement.setString(4, medico.getTelefone());
      pStatement.setString(5, medico.getCrm());
      pStatement.setString(6, medico.getEspecialidade());
      pStatement.setString(7, genero);
      pStatement.setInt(8, medico.getId());

      int resultado = pStatement.executeUpdate();
      return resultado > 0;

    } catch (SQLException e) {
      throw new ExceptionDAO("Erro ao editar médico: " + e);
    } finally {
      try {
        if (pStatement != null) {
          pStatement.close();
        }
        if (connection != null) {
          connection.close();
        }
      } catch (SQLException e) {
        throw new ExceptionDAO("Erro ao fechar conexão: " + e);
      }
    }
  }

  public void deletarMedico(Medico medico) throws ExceptionDAO {
    String sql = "DELETE FROM medico WHERE medico_id = ?";

    PreparedStatement pStatement = null;
    Connection connection = null;

    try {
      connection = new ConnectionDAO().getConnection();
      pStatement = connection.prepareStatement(sql);
      pStatement.setInt(1, medico.getId());
      pStatement.execute();

      System.out.println("Médico deletado com sucesso no banco de dados.");
    } catch (SQLException ex) {
      throw new ExceptionDAO("Erro ao deletar médico. Erro " + ex);
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