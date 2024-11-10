package com.alg3.minhaconsulta.controller;

import com.alg3.minhaconsulta.dao.ProntuarioDAO;
import com.alg3.minhaconsulta.model.Prontuario;
import com.alg3.minhaconsulta.dao.ExceptionDAO;
import java.util.ArrayList;

public class ProntuarioController {

    public boolean cadastrarProntuario(int pacienteId, int medicoId, String dataRegistro, String observacoes, String exames, String historicoMedico) {
        if (pacienteId > 0  && medicoId > 0 && dataRegistro != null && observacoes != null && exames != null && historicoMedico != null) {
            Prontuario prontuario = new Prontuario();
            prontuario.setPacienteId(pacienteId);         
            prontuario.setMedicoId(medicoId);
            prontuario.setDataRegistro(dataRegistro);
            prontuario.setObservacoes(observacoes);
            prontuario.setExames(exames);
            prontuario.setHistoricoMedico(historicoMedico);

            ProntuarioDAO dao = new ProntuarioDAO();
            try {
                dao.cadastrarProntuario(prontuario);
                return true;
            } catch (ExceptionDAO ex) {
                ex.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public boolean editarProntuario(int id, int pacienteId, int consultaId, int medicoId, String dataRegistro, String observacoes, String exames, String historicoMedico) {
        if (id > 0 && pacienteId > 0 && consultaId > 0 && medicoId > 0 && dataRegistro != null && observacoes != null && exames != null && historicoMedico != null) {
            Prontuario prontuario = new Prontuario();
            prontuario.setId(id);
            prontuario.setPacienteId(pacienteId);
            prontuario.setConsultaId(consultaId);
            prontuario.setMedicoId(medicoId);
            prontuario.setDataRegistro(dataRegistro);
            prontuario.setObservacoes(observacoes);
            prontuario.setExames(exames);
            prontuario.setHistoricoMedico(historicoMedico);

            ProntuarioDAO dao = new ProntuarioDAO();
            try {
                dao.editarProntuario(prontuario);
                return true;
            } catch (ExceptionDAO ex) {
                ex.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public ArrayList<Prontuario> listarProntuarios(String observacoes) throws ExceptionDAO {
        return new ProntuarioDAO().listarProntuarios(observacoes);
    }

    public ArrayList<Prontuario> listarProntuariosId(int id) throws ExceptionDAO {
        return new ProntuarioDAO().listarProntuariosId(id);
    }

    public boolean deletarProntuario(int id) throws ExceptionDAO {
        if (id > 0) {
            try {
                Prontuario prontuario = new Prontuario();
                prontuario.setId(id);
                prontuario.deletarProntuario(prontuario);
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