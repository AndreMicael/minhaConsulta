/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alg3.minhaconsulta.controller;

import java.util.Date;

import com.alg3.minhaconsulta.model.Paciente;


/**
 *
 * @author André Micael Sampaio Pinto <andre at alg3.org>
 *    private String nome;
    private Date data_nascimento;
    private String endereco;
    private String telefone;
    private String cpf;
     private int pacienteId;
    private String convenio;
    private String cpf;
 * 
 */
public class PacienteController {
    
    public boolean cadastrarPaciente(String nome, Date data_nascimento, String endereco, String telefone, String cpf, String convenio){
       
        if (nome != null && nome.length() > 0 && data_nascimento != null && endereco != null && endereco.length() > 0 && telefone != null && telefone.length() > 0 && cpf != null && cpf.length() > 0 && convenio != null && convenio.length() > 0){
            Paciente paciente = new Paciente();
            paciente.setNome(nome);
            paciente.setData_nascimento(data_nascimento);
            paciente.setEndereco(endereco);
            paciente.setTelefone(telefone);
            paciente.setCpf(cpf);
            paciente.setConvenio(convenio);
            return true;
            
        }
        return false;
    }
    
    
}
