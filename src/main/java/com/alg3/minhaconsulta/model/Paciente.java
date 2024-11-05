/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alg3.minhaconsulta.model;
import com.alg3.minhaconsulta.dao.ExceptionDAO;
import com.alg3.minhaconsulta.dao.PacienteDAO;
import java.util.ArrayList;



/**
 *
 * @author Andre Micael Sampaio Pinto
 */
public class Paciente extends Pessoa{

  
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

    public ArrayList<Paciente> listarPacientes(String nome) throws ExceptionDAO {
        return new PacienteDAO().listarPacientes(nome);
    }
    
}

/*
CREATE TABLE PACIENTE (
    paciente_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    data_nascimento DATE,
    endereco VARCHAR(255),
    telefone VARCHAR(15),
    convenio VARCHAR(50),
    cpf VARCHAR(11) UNIQUE 
);
*/