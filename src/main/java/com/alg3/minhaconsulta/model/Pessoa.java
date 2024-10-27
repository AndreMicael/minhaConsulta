package minhaConsulta.model;

import java.util.Date;

public class Pessoa {

    private String nome;
    private Date data_nascimento;
    private String endereco;
    private String telefone;
    private String cpf;

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Date getData_nascimento() {
        return data_nascimento;
    }
    public void setData_nascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
}


