/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alg3.minhaconsulta.model;

/**
 *
 * @author André Micael Sampaio Pinto
 */
public class Medico extends Pessoa {

    private int medicoId;
    private String crm;
    private int especialidadeId;
    
    public int getMedicoId() {
        return medicoId;
    }
    public void setMedicoId(int medicoId) {
        this.medicoId = medicoId;
    }
    public String getCrm() {
        return crm;
    }
    public void setCrm(String crm) {
        this.crm = crm;
    }
    public int getEspecialidadeId() {
        return especialidadeId;
    }
    public void setEspecialidadeId(int especialidadeId) {
        this.especialidadeId = especialidadeId;
    }

    
    
}

// CREATE TABLE MEDICO (
//     medico_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
//     nome VARCHAR(255),
//     crm VARCHAR(20) UNIQUE,  -- Definindo CRM como único
//     especialidade_id INT,
//     telefone VARCHAR(15),
//     CONSTRAINT FK_MEDICO_ESPECIALIDADE FOREIGN KEY (especialidade_id) REFERENCES ESPECIALIDADE(especialidade_id) ON DELETE RESTRICT
// );
