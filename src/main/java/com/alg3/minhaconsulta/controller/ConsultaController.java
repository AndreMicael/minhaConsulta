package com.alg3.minhaconsulta.controller;

import com.alg3.minhaconsulta.dao.ConsultaDAO;
import com.alg3.minhaconsulta.model.Consulta;
import com.alg3.minhaconsulta.dao.ExceptionDAO;
import java.util.ArrayList;

public class ConsultaController {

    public boolean cadastrarConsulta(int pacienteId, int medicoId, String dataConsulta, double valor, String status, String observacao) {
        if (pacienteId != 0 && medicoId != 0 && dataConsulta != null && dataConsulta.length() > 0 && valor != 0.0 && status != null && status.length() > 0 && observacao != null && observacao.length() > 0) {
            Consulta consulta = new Consulta();
            consulta.setPacienteId(pacienteId);
            consulta.setMedicoId(medicoId);
            consulta.setDataConsulta(dataConsulta);
            consulta.setValor(valor);
            consulta.setStatus(status);
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

    public ArrayList<Consulta> listarConsultas(String nome) throws ExceptionDAO {
        return new Consulta().listarConsultas(nome);
    }
}