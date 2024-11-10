package com.alg3.minhaconsulta.controller;

import com.alg3.minhaconsulta.dao.ConsultaDAO;
import com.alg3.minhaconsulta.model.Consulta;

import com.alg3.minhaconsulta.dao.ExceptionDAO;

import java.util.ArrayList;

public class ConsultaController {

  public boolean cadastrarConsulta(int pacienteId, int medicoId, String dataConsulta, double valor, String observacao) {
    if (pacienteId != 0 && medicoId != 0 && dataConsulta != null && dataConsulta.length() > 0 && valor != 0.0 && observacao != null && observacao.length() > 0) {
      Consulta consulta = new Consulta();
      consulta.setPacienteId(pacienteId);
      consulta.setMedicoId(medicoId);
      consulta.setData(dataConsulta);
      consulta.setValor(valor);

      consulta.setObservacoes(observacao);

      System.out.println("Dados recebidos no controller: " + observacao + ", " + dataConsulta);

      ConsultaDAO dao = new ConsultaDAO();
      try {
        dao.cadastrarConsulta(consulta);
        return true;
      } catch (ExceptionDAO ex) {
        ex.printStackTrace();
        return false;
      }
    }
    return false;
  }

  public boolean editarConsulta(int pacienteId, int medicoId, String dataConsulta, double valor, String observacao) {
    if (pacienteId != 0 && medicoId != 0 && dataConsulta != null && dataConsulta.length() > 0 && valor != 0.0 && observacao != null && observacao.length() > 0) {
      Consulta consulta = new Consulta();
      consulta.setPacienteId(pacienteId);
      consulta.setMedicoId(medicoId);
      consulta.setData(dataConsulta);
      consulta.setValor(valor);
      consulta.setObservacoes(observacao);

      System.out.println("Dados recebidos no controller: " + observacao + ", " + dataConsulta);

      ConsultaDAO dao = new ConsultaDAO();
      try {
        dao.cadastrarConsulta(consulta);
        return true;
      } catch (ExceptionDAO ex) {
        ex.printStackTrace();
        return false;
      }
    }
    return false;
  }

  public ArrayList < Consulta > listarConsultas(String nome) throws ExceptionDAO {
    return new Consulta().listarConsultas(nome);
  }

  public ArrayList < Consulta > listarConsultasData(String data) throws ExceptionDAO {
    return new ConsultaDAO().listarConsultasData(data);
  }

  public Consulta listarConsultasId(int id) throws ExceptionDAO {
    try {
      return new ConsultaDAO().listarConsultasId(id); // Alterado de listarConsulta para listarConsultasId
    } catch (ExceptionDAO e) {
      throw e;
    }
  }

  public boolean deletarConsulta(int id) throws ExceptionDAO {
    if (id > 0) {
      try {

        Consulta consulta = new Consulta();
        consulta.setId(id);
        consulta.deletarConsulta(consulta);
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