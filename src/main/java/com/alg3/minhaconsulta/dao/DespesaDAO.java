package com.alg3.minhaconsulta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.alg3.minhaconsulta.model.Despesa;
 

public class DespesaDAO {

  public int cadastrarDespesa(Despesa despesa) throws ExceptionDAO {
    String sql = "INSERT INTO despesa (descricao, tipo, valor, data_registro) VALUES(?,?,?,?)";

    PreparedStatement pStatement = null;
    Connection connection = null;
    int generatedId = -1;

    try {
      connection = new ConnectionDAO().getConnection();
      pStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
      pStatement.setString(1, despesa.getDescricao());
      pStatement.setString(2, despesa.getTipo());
      pStatement.setDouble(3, despesa.getValor());
      pStatement.setString(4, despesa.getDataRegistro());

      pStatement.executeUpdate();
      ResultSet rs = pStatement.getGeneratedKeys();
      if (rs.next()) {
        generatedId = rs.getInt(1);
      }
      System.out.println("Despesa cadastrada com sucesso no banco de dados.");
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
    return generatedId;
  }

  public boolean editarDespesa(Despesa despesa) throws ExceptionDAO {
    String sql = "UPDATE despesa SET descricao = ?, tipo = ?, valor = ?, data_registro = ? WHERE despesa_id = ?";

    PreparedStatement pStatement = null;
    Connection connection = null;

    try {
      connection = new ConnectionDAO().getConnection();
      pStatement = connection.prepareStatement(sql);
      pStatement.setString(1, despesa.getDescricao());
      pStatement.setString(2, despesa.getTipo());
      pStatement.setDouble(3, despesa.getValor());
      pStatement.setString(4, despesa.getDataRegistro());
      pStatement.setInt(5, despesa.getId());

      System.out.println("Dados recebidos no DAO: " + despesa.getDescricao() + ", " + despesa.getValor());

      int rowsAffected = pStatement.executeUpdate();
      System.out.println("Despesa editada com sucesso no banco de dados.");
      return rowsAffected > 0;
    } catch (SQLException ex) {
      throw new ExceptionDAO("Erro ao editar despesa. Erro " + ex);
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

  public ArrayList < Despesa > listarDespesas(String descricao) throws ExceptionDAO {
    String sql = "SELECT * FROM despesa WHERE descricao LIKE ? ORDER BY despesa_id";

    Connection connection = null;
    PreparedStatement pStatement = null;
    ArrayList < Despesa > listarDespesas = new ArrayList < > ();

    try {
      connection = new ConnectionDAO().getConnection();
      pStatement = connection.prepareStatement(sql);
      pStatement.setString(1, "%" + descricao + "%");
      ResultSet rs = pStatement.executeQuery();

      while (rs.next()) {
        Despesa despesa = new Despesa();
        despesa.setId(rs.getInt("despesa_id"));
        despesa.setDescricao(rs.getString("descricao"));
        despesa.setTipo(rs.getString("tipo"));
        despesa.setValor(rs.getDouble("valor"));
        despesa.setDataRegistro(rs.getString("data_registro"));
        listarDespesas.add(despesa);
      }

    } catch (SQLException ex) {
      throw new ExceptionDAO("Erro ao listar despesas. Erro " + ex);
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

    return listarDespesas;
  }

  public ArrayList < Despesa > listarDespesasId(int id) throws ExceptionDAO {
    String sql = "SELECT * FROM despesa WHERE despesa_id = ? ORDER BY despesa_id";

    Connection connection = null;
    PreparedStatement pStatement = null;
    ArrayList < Despesa > listarDespesas = new ArrayList < > ();

    try {
      connection = new ConnectionDAO().getConnection();
      pStatement = connection.prepareStatement(sql);
      pStatement.setInt(1, id);
      ResultSet rs = pStatement.executeQuery();

      while (rs.next()) {
        Despesa despesa = new Despesa();
        despesa.setId(rs.getInt("despesa_id"));
        despesa.setDescricao(rs.getString("descricao"));
        despesa.setTipo(rs.getString("tipo"));
        despesa.setValor(rs.getDouble("valor"));
        despesa.setDataRegistro(rs.getString("data_registro"));
        listarDespesas.add(despesa);
      }

    } catch (SQLException ex) {
      throw new ExceptionDAO("Erro ao listar despesas. Erro " + ex);
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

    return listarDespesas;
  }

  public Despesa listarPorId(int id) throws ExceptionDAO {
    ArrayList<Despesa> despesas = listarDespesasId(id);
    return despesas.isEmpty() ? null : despesas.get(0);
}

  public ArrayList < Despesa > listarDespesasTipo(String tipo) throws ExceptionDAO {
    String sql = "SELECT * FROM despesa WHERE tipo = ? ORDER BY data_registro";

    Connection connection = null;
    PreparedStatement pStatement = null;
    ArrayList < Despesa > listaDespesas = new ArrayList < > ();

    try {
      connection = new ConnectionDAO().getConnection();
      pStatement = connection.prepareStatement(sql);
      pStatement.setString(1, tipo);
      ResultSet rs = pStatement.executeQuery();

      while (rs.next()) {
        Despesa despesa = new Despesa();
        despesa.setId(rs.getInt("despesa_id"));
        despesa.setDescricao(rs.getString("descricao"));
        despesa.setValor(rs.getDouble("valor"));
        despesa.setDataRegistro(rs.getString("data_registro"));
        despesa.setTipo(rs.getString("tipo"));

        listaDespesas.add(despesa);
      }

    } catch (SQLException ex) {
      throw new ExceptionDAO("Erro ao listar despesas. Erro " + ex);
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

    return listaDespesas;
  }

  public void deletarDespesa(Despesa despesa) throws ExceptionDAO {
        String sql = "DELETE FROM despesa WHERE despesa_id = ?";

        PreparedStatement pStatement = null;
        Connection connection = null;

        try {
            connection = new ConnectionDAO().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, despesa.getId());
            pStatement.execute();

            System.out.println("Despesa deletada com sucesso no banco de dados.");
        } catch (SQLException ex) {
            throw new ExceptionDAO("Erro ao deletar despesa. Erro " + ex);
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