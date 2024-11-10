package com.alg3.minhaconsulta.model;

import com.alg3.minhaconsulta.dao.ExceptionDAO;
import com.alg3.minhaconsulta.dao.PacienteDAO;
import java.util.ArrayList;

public class Paciente extends Pessoa {

    private String convenio;
    private String cpf;
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getConvenio() {
        return convenio;
    }

    public void setConvenio(String convenio) {
        this.convenio = convenio;
    }

    @Override
    public String getCpf() {
        return cpf;
    }

    @Override
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void cadastrarPaciente(Paciente paciente) throws ExceptionDAO {
        new PacienteDAO().cadastrarPaciente(paciente);
    }

    public void editarPaciente(Paciente paciente) throws ExceptionDAO {
        new PacienteDAO().editarPaciente(paciente);
    }

    public ArrayList<Paciente> listarPacientes(String nome) throws ExceptionDAO {
        return new PacienteDAO().listarPacientes(nome);
    }

    public void deletarPaciente(Paciente paciente) throws ExceptionDAO {
        new PacienteDAO().deletarPaciente(paciente);
    }
}