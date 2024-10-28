/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alg3.minhaconsulta.controller;

import com.alg3.minhaconsulta.dao.ExceptionDAO;
import com.alg3.minhaconsulta.dao.MedicoDAO;
import com.alg3.minhaconsulta.model.Medico;

/**
 *
 * @author André Micael Sampaio Pinto <andre at alg3.org>
 */
public class MedicoController {
    
    public boolean cadastrarMedico(String nome, String data_nascimento, String endereco, String telefone, String crm, String especialidade, String genero) {
        if (nome != null && nome.length() > 0 && data_nascimento != null && endereco != null && endereco.length() > 0 && telefone != null && telefone.length() > 0 && crm != null && crm.length() > 0 && especialidade != null && especialidade.length() > 0) {
            Medico medico = new Medico();
            medico.setNome(nome);
            medico.setData_nascimento(data_nascimento);
            medico.setEndereco(endereco);
            medico.setTelefone(telefone);
            medico.setCrm(crm);
            medico.setEspecialidade(especialidade);
            medico.setGenero(genero);

            System.out.println("Dados recebidos no controller: " + nome + ", " + endereco);

            MedicoDAO dao = new MedicoDAO();
            try {
                dao.cadastrarMedico(medico);
                return true;
            } catch (ExceptionDAO ex) {
                ex.printStackTrace();
                return false;
            }
        }
        return false;
    }
    
}
