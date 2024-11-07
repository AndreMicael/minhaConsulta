package com.alg3.minhaconsulta.controller;

import com.alg3.minhaconsulta.dao.DespesaDAO;
import com.alg3.minhaconsulta.model.Despesa;
import com.alg3.minhaconsulta.dao.ExceptionDAO;
import java.util.ArrayList;

public class DespesaController {

    public boolean cadastrarDespesa(String descricao, String tipo, double valor, String data) throws ExceptionDAO {
        Despesa despesa = new Despesa();
        despesa.setDescricao(descricao);
        despesa.setTipo(tipo);
        despesa.setValor(valor);
        despesa.setDataRegistro(data);

        DespesaDAO despesaDAO = new DespesaDAO();
        return despesaDAO.cadastrarDespesa(despesa) != -1;
    }

    public boolean editarDespesa(int id, String descricao, String tipo, double valor, String data) throws ExceptionDAO {
        Despesa despesa = new Despesa();
        despesa.setId(id);
        despesa.setDescricao(descricao);
        despesa.setTipo(tipo);
        despesa.setValor(valor);
        despesa.setDataRegistro(data);

        DespesaDAO despesaDAO = new DespesaDAO();
        return despesaDAO.editarDespesa(despesa);
    }

    public ArrayList<Despesa> listarDespesasTipo(String tipo) throws ExceptionDAO {
        return new DespesaDAO().listarDespesasTipo(tipo);
    }

    public ArrayList<Despesa> listarDespesas(String descricao) throws ExceptionDAO {
        return new DespesaDAO().listarDespesas(descricao);
    }
}