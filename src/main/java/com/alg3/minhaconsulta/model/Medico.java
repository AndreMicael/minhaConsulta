/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alg3.minhaconsulta.model;
import com.alg3.minhaconsulta.dao.ExceptionDAO;
import com.alg3.minhaconsulta.dao.MedicoDAO;
import java.util.ArrayList;

/**
 *
 * @author André Micael Sampaio Pinto
 */
public class Medico extends Pessoa {

    private int id;
    private String crm;
    private String especialidade;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCrm() {
        return crm;
    }
    public void setCrm(String crm) {
        this.crm = crm;
    }
    public String getEspecialidade() {
        return especialidade;
    }
    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }


     public void cadastrarMedico(Medico medico) throws ExceptionDAO {
        new MedicoDAO().cadastrarMedico(medico);
    }



      public ArrayList<Medico> listarMedicos(String nome) throws ExceptionDAO {
        MedicoDAO medicoDAO = new MedicoDAO();
        return medicoDAO.listarMedicos(nome);

    }

    
    
}

