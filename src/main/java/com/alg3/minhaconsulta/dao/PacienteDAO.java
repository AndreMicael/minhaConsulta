package com.alg3.minhaconsulta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.alg3.minhaconsulta.model.Paciente;
import java.util.ArrayList;

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

    public void deletarPaciente(Paciente paciente) throws ExceptionDAO {
        String sql = "DELETE FROM paciente WHERE paciente_id = ?";

        PreparedStatement pStatement = null;
        Connection connection = null;

        try {
            connection = new ConnectionDAO().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, paciente.getId());
            pStatement.execute();

            System.out.println("Paciente deletado com sucesso no banco de dados.");
        } catch (SQLException ex) {
            throw new ExceptionDAO("Erro ao deletar paciente. Erro " + ex);
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



  
  

  
    

    public void editarPaciente(Paciente paciente) throws ExceptionDAO {
        String sql = "UPDATE paciente SET nome = ?, data_nascimento = ?, endereco = ?, telefone = ?, convenio = ?, cpf = ?, genero = ? " +
                     "WHERE paciente_id = ?";

        PreparedStatement pStatement = null;
        Connection connection = null;

        try {
            connection = new ConnectionDAO().getConnection();
            pStatement = connection.prepareStatement(sql);

            String genero;
            if (paciente.getGenero().equalsIgnoreCase("Feminino")) {
                genero = "F";
            } else if (paciente.getGenero().equalsIgnoreCase("Masculino")) {
                genero = "M";
            } else {
                genero = "X";
            }

            pStatement.setString(1, paciente.getNome());
            pStatement.setString(2, paciente.getData_nascimento());
            pStatement.setString(3, paciente.getEndereco());
            pStatement.setString(4, paciente.getTelefone());
            pStatement.setString(5, paciente.getConvenio());
            pStatement.setString(6, paciente.getCpf());
            pStatement.setString(7, genero);
            pStatement.setInt(8, paciente.getId());

            System.out.println("Dados editados no DAO: " + paciente.getNome() + ", " + paciente.getEndereco());

            pStatement.executeUpdate();
            System.out.println("Paciente editado com sucesso no banco de dados.");
        } catch (SQLException ex) {
            throw new ExceptionDAO("Erro ao editar paciente. Erro " + ex);
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

    public ArrayList<Paciente> listarPacientes(String nome) throws ExceptionDAO {
        String sql = "SELECT * FROM paciente WHERE nome LIKE ? ORDER BY paciente_id";

        Connection connection = null;
        PreparedStatement pStatement = null;
        ArrayList<Paciente> listaPacientes = new ArrayList<>();

        try {
            connection = new ConnectionDAO().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, "%" + nome + "%");
            ResultSet rs = pStatement.executeQuery();

            while (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setId(rs.getInt("paciente_id"));
                paciente.setNome(rs.getString("nome"));
                paciente.setData_nascimento(rs.getString("data_nascimento"));
                paciente.setEndereco(rs.getString("endereco"));
                paciente.setTelefone(rs.getString("telefone"));
                paciente.setConvenio(rs.getString("convenio"));
                paciente.setCpf(rs.getString("cpf"));
                paciente.setGenero(rs.getString("genero"));
                listaPacientes.add(paciente);
            }

        } catch (SQLException ex) {
            throw new ExceptionDAO("Erro ao listar pacientes. Erro " + ex);
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

        return listaPacientes;
    }

    public ArrayList<Paciente> listarPacientesId(int pacienteId) throws ExceptionDAO {
        String sql = "SELECT * FROM paciente WHERE paciente_id = ?";

        Connection connection = null;
        PreparedStatement pStatement = null;
        ArrayList<Paciente> listaPacientes = new ArrayList<>();

        try {
            connection = new ConnectionDAO().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, pacienteId);
            ResultSet rs = pStatement.executeQuery();

            while (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setId(rs.getInt("paciente_id"));
                paciente.setNome(rs.getString("nome"));
                paciente.setData_nascimento(rs.getString("data_nascimento"));
                paciente.setEndereco(rs.getString("endereco"));
                paciente.setTelefone(rs.getString("telefone"));
                paciente.setConvenio(rs.getString("convenio"));
                paciente.setCpf(rs.getString("cpf"));
                paciente.setGenero(rs.getString("genero"));
                listaPacientes.add(paciente);
            }

        } catch (SQLException ex) {
            throw new ExceptionDAO("Erro ao listar pacientes. Erro " + ex);
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

        return listaPacientes;
    }
}