package com.alg3.minhaconsulta.controller;

import com.alg3.minhaconsulta.dao.DespesaDAO;
import com.alg3.minhaconsulta.model.Despesa;
import com.alg3.minhaconsulta.dao.ExceptionDAO;

public class DespesaController {

    public boolean cadastrarDespesa(String descricao, String tipo, double valor, String dataRegistro) {
        if (descricao == null || descricao.length() <= 0 || dataRegistro == null || dataRegistro.length() <= 0 || valor <= 0 || tipo == null || tipo.length() <= 0) {
            Despesa despesa = new Despesa();
            despesa.setDescricao(descricao);
            despesa.setTipo(tipo);
            despesa.setValor(valor);
            despesa.setDataRegistro(dataRegistro);
            
            System.out.println("Dados recebidos no controller: " + descricao + ", " + tipo);

            DespesaDAO dao = new DespesaDAO();
            try {
                dao.cadastrarDespesa(despesa);
                return true;
            } catch (ExceptionDAO ex) {
                ex.printStackTrace();
                return false;
            }
        }
        return false;
    }
}