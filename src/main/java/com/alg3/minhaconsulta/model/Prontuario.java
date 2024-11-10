package com.alg3.minhaconsulta.model;

import java.util.ArrayList;

import com.alg3.minhaconsulta.dao.ExceptionDAO; 
import com.alg3.minhaconsulta.dao.ProntuarioDAO;

public class Prontuario {

    private int id;
    private int pacienteId;
    private int consultaId;
    private int medicoId;
    private String dataRegistro;
    private String observacoes;
    private String exames;
    private String historicoMedico;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getPacienteId() {
        return pacienteId;
    }
    public void setPacienteId(int pacienteId) {
        this.pacienteId = pacienteId;
    }
    public int getConsultaId() {
        return consultaId;
    }
    public void setConsultaId(int consultaId) {
        this.consultaId = consultaId;
    }
    public int getMedicoId() {
        return medicoId;
    }
    public void setMedicoId(int medicoId) {
        this.medicoId = medicoId;
    }
    public String getDataRegistro() {
        return dataRegistro;
    }
    public void setDataRegistro(String dataRegistro) {
        this.dataRegistro = dataRegistro;
    }
    public String getObservacoes() {
        return observacoes;
    }
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
    public String getExames() {
        return exames;
    }
    public void setExames(String exames) {
        this.exames = exames;
    }
    public String getHistoricoMedico() {
        return historicoMedico;
    }
    public void setHistoricoMedico(String historicoMedico) {
        this.historicoMedico = historicoMedico;
    }

    public void cadastrarProntuario(Prontuario prontuario) throws ExceptionDAO {
        new ProntuarioDAO().cadastrarProntuario(prontuario);
    }

    public void editarProntuario(Prontuario prontuario) throws ExceptionDAO {
        new ProntuarioDAO().editarProntuario(prontuario);
    }

    public ArrayList<Prontuario> listarProntuarios(String nome) throws ExceptionDAO {
        return new ProntuarioDAO().listarProntuarios(nome);
    }

    public void deletarProntuario(Prontuario prontuario) throws ExceptionDAO {
        new ProntuarioDAO().deletarProntuario(prontuario);
    }

}
