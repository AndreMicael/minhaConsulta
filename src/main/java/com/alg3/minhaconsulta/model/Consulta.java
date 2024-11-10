package com.alg3.minhaconsulta.model;

import com.alg3.minhaconsulta.dao.ConsultaDAO;
import com.alg3.minhaconsulta.dao.ExceptionDAO;
 

import java.util.ArrayList;

public class Consulta {
    private Integer id;
    private String data;
    private int pacienteId;
    private int medicoId;
    private double valor;
    private String status;
    private String observacoes;
    private String medicoNome;
    private String pacienteNome;

    // Construtor
    public Consulta() {
    }

    public Consulta(Integer id, String data, int pacienteId, int medicoId, double valor, String status, String observacoes) {
        this.id = id;
        this.data = data;
        this.pacienteId = pacienteId;
        this.medicoId = medicoId;
        this.valor = valor;
        this.status = status;
        this.observacoes = observacoes;
    }

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(int pacienteId) {
        this.pacienteId = pacienteId;
    }

    public int getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(int medicoId) {
        this.medicoId = medicoId;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getDataConsulta() {
        return data;
    }

    public String getMedicoNome() {
        return medicoNome;
    }

    public void setMedicoNome(String medicoNome) {
        this.medicoNome = medicoNome;
    }

    public String getPacienteNome() {
        return pacienteNome;
    }

    public void setPacienteNome(String pacienteNome) {
        this.pacienteNome = pacienteNome;
    }

    public void cadastrarConsulta(Consulta consulta) throws ExceptionDAO {
        new ConsultaDAO().cadastrarConsulta(consulta);
    }

    public ArrayList<Consulta> listarConsultas(String nome) throws ExceptionDAO {
        return new ConsultaDAO().listarConsultas(nome);
    }
     public void deletarConsulta(Consulta consulta) throws ExceptionDAO {
        new ConsultaDAO().deletarConsulta(consulta);
    }
}