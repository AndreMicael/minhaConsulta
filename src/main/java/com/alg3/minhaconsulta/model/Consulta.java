/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alg3.minhaconsulta.model;

import com.alg3.minhaconsulta.dao.ConsultaDAO;
import com.alg3.minhaconsulta.dao.ExceptionDAO;

/**
 *
 * @author André Micael Sampaio Pinto
 */
public class Consulta {
   
    private Integer consultaId;
    private Integer pacienteId;
    private Integer medicoId;
    private String dataConsulta;
    private double valor;
    private String status;
    private String observacoes;

    // Getters e Setters
    public Integer getConsultaId() {
        return consultaId;
    }

    public void setConsultaId(Integer consultaId) {
        this.consultaId = consultaId;
    }
    
       public double getValor() {
        return valor;
    }
    
     public void setValor(double valor) {
        this.valor = valor;
    }

    public Integer getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Integer pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Integer getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(Integer medicoId) {
        this.medicoId = medicoId;
    }


    public String getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(String dataConsulta) {
        this.dataConsulta = dataConsulta;
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

     public void cadastrarConsulta(Consulta consulta) throws ExceptionDAO {
        new ConsultaDAO().cadastrarConsulta(consulta);
    }
        
    
}


