package com.alg3.minhaconsulta.controller;

import com.alg3.minhaconsulta.dao.PacienteDAO;
import com.alg3.minhaconsulta.model.Paciente;
import com.alg3.minhaconsulta.dao.ExceptionDAO;
import java.util.ArrayList;

public class PacienteController {

  public boolean cadastrarPaciente(String nome, String data_nascimento, String endereco, String telefone, String cpf, String convenio, String genero) {
    if (nome != null && nome.length() > 0 && data_nascimento != null && endereco != null && endereco.length() > 0 && telefone != null && telefone.length() > 0 && cpf != null && cpf.length() > 0 && convenio != null && convenio.length() > 0) {
      Paciente paciente = new Paciente();
      paciente.setNome(nome);
      paciente.setData_nascimento(data_nascimento);
      paciente.setEndereco(endereco);
      paciente.setTelefone(telefone);
      paciente.setCpf(cpf);
      paciente.setConvenio(convenio);
      paciente.setGenero(genero);

      System.out.println("Dados recebidos no controller: " + nome + ", " + endereco);

      PacienteDAO dao = new PacienteDAO();
      try {
        dao.cadastrarPaciente(paciente);
        return true;
      } catch (ExceptionDAO ex) {
        ex.printStackTrace();
        return false;
      }
    }
    return false;
  }

  public boolean editarPaciente(String nome, String data_nascimento, String endereco, String telefone, String cpf, String convenio, String genero, int id) {
    if (nome != null && nome.length() > 0 && data_nascimento != null && endereco != null && endereco.length() > 0 && telefone != null && telefone.length() > 0 && cpf != null && cpf.length() > 0 && convenio != null && convenio.length() > 0) {
      Paciente paciente = new Paciente();
      paciente.setNome(nome);
      paciente.setData_nascimento(data_nascimento);
      paciente.setEndereco(endereco);
      paciente.setTelefone(telefone);
      paciente.setCpf(cpf);
      paciente.setConvenio(convenio);
      paciente.setGenero(genero);
      paciente.setId(id);

      System.out.println("Dados recebidos no controller: " + nome + ", " + endereco);

      PacienteDAO dao = new PacienteDAO();
      try {
        dao.editarPaciente(paciente);
        return true;
      } catch (ExceptionDAO ex) {
        ex.printStackTrace();
        return false;
      }
    }
    return false;
  }

  public ArrayList < Paciente > listarPacientes(String nome) throws ExceptionDAO {
    return new Paciente().listarPacientes(nome);
  }

  public ArrayList < Paciente > listarPacientesId(int id) throws ExceptionDAO {
    return new PacienteDAO().listarPacientesId(id);
  }

  public boolean deletarPaciente(int id) throws ExceptionDAO {
    if (id > 0) {
      try {

        Paciente paciente = new Paciente();
        paciente.setId(id);
        paciente.deletarPaciente(paciente);
        return true;
      } catch (ExceptionDAO ex) {
        ex.printStackTrace();
        return false;
      }
    } else {
      return false;
    }
  }
}