package com.alg3.minhaconsulta.model;

import com.alg3.minhaconsulta.dao.ConsultaDAO;
import com.alg3.minhaconsulta.dao.ExceptionDAO;
import java.util.ArrayList;

public class Consulta {
    private Integer id;
    private String data;
    private Medico medico;
    private Paciente paciente;
    private double valor;
    private String status;
    private String observacoes;

    // Construtor
    public Consulta() {
    }

    public Consulta(Integer id, String data, Medico medico, Paciente paciente, double valor, String status, String observacoes) {
        this.id = id;
        this.data = data;
        this.medico = medico;
        this.paciente = paciente;
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

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
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

    public int getPacienteId() {
        return paciente.getId();
    }

    public int getMedicoId() {
        return medico.getId();
    }

    public String getDataConsulta() {
        return data;
    }

    public void cadastrarConsulta(Consulta consulta) throws ExceptionDAO {
        new ConsultaDAO().cadastrarConsulta(consulta);
    }

    public ArrayList<Consulta> listarConsultas(String nome) throws ExceptionDAO {
        return new ConsultaDAO().listarConsultas(nome);
    }
}