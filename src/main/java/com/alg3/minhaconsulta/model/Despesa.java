package com.alg3.minhaconsulta.model;

import java.util.ArrayList;
import com.alg3.minhaconsulta.dao.DespesaDAO;
import com.alg3.minhaconsulta.dao.ExceptionDAO;

public class Despesa {
    private int id;
    private String descricao;
    private double valor;
    private String dataRegistro;
    private String tipo;

    // Construtor
    public Despesa() {
    }

    public Despesa(int id, String descricao, double valor, String dataRegistro, String tipo) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.dataRegistro = dataRegistro;
        this.tipo = tipo;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(String dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void cadastrarDespesa(Despesa despesa) throws ExceptionDAO {
        new DespesaDAO().cadastrarDespesa(despesa);
    }

    public void editarDespesa(Despesa despesa) throws ExceptionDAO {
        new DespesaDAO().editarDespesa(despesa);
    }

    public ArrayList<Despesa> listarDespesas(String descricao) throws ExceptionDAO {
        return new DespesaDAO().listarDespesas(descricao);
    }
}