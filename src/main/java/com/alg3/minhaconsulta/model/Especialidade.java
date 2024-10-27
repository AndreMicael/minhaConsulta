package minhaConsulta.model;

/**
 *
 * @author André Micael Sampaio Pinto
 */

public class Especialidade {

    private String nome;
    private String descricao;

   
    public String getNome() {
        return nome;
    } 
     public String getDescricao() {
        return descricao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }  

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
