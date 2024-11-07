/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alg3.minhaconsulta.model;
import java.util.ArrayList;

import com.alg3.minhaconsulta.dao.ConsultaDAO;
import com.alg3.minhaconsulta.dao.DespesaDAO;
import com.alg3.minhaconsulta.dao.ExceptionDAO;

/**
 *
 * @author andresampaio
 */
public class Despesa {
    private int id;
    private String descricao;
    private String tipo;
    private double valor;
    private String dataRegistro;
    
    //Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getDescricao(){
        return descricao;
    }
    
    public String getTipo(){
        return tipo;
    }
    
    public double getValor(){
        return valor;
    }
    
    public String getDataRegistro(){
        return dataRegistro;
    }
    
     public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
     
    public void setTipo(String tipo){
        this.tipo = tipo;
    }
    
    public void setValor(double valor){
        this.valor = valor;
    }
    
    public void setDataRegistro(String dataRegistro){
        this.dataRegistro = dataRegistro;
    }
    
     public void cadastrarDespesa(Despesa despesa) throws ExceptionDAO {
        new DespesaDAO().cadastrarDespesa(despesa);
    }

       


 public ArrayList<Despesa> listarDespesas(String descricao) throws ExceptionDAO {
        return new DespesaDAO().listarDespesas(descricao);
    }

     
     
}
