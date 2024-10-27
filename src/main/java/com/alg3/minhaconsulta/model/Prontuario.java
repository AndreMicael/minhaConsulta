/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minhaConsulta.model;

/**
 *
 * @author André Micael Sampaio Pinto
 */
public class Prontuario {

    private int prontuario_id;
    private int paciente_id;
    private int consulta_id;
    private String data_registro;
    private String observacoes;
    private String exames;
    private String historico_medico;
    
    public int getProntuario_id() {
        return prontuario_id;
    }
    public void setProntuario_id(int prontuario_id) {
        this.prontuario_id = prontuario_id;
    }
    public int getPaciente_id() {
        return paciente_id;
    }
    public void setPaciente_id(int paciente_id) {
        this.paciente_id = paciente_id;
    }
    public int getConsulta_id() {
        return consulta_id;
    }
    public void setConsulta_id(int consulta_id) {
        this.consulta_id = consulta_id;
    }
    public String getData_registro() {
        return data_registro;
    }
    public void setData_registro(String data_registro) {
        this.data_registro = data_registro;
    }
    public String getObservacoes() {
        return observacoes;
    }
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
    public String getExames() {
        return exames;
    }
    public void setExames(String exames) {
        this.exames = exames;
    }
    public String getHistorico_medico() {
        return historico_medico;
    }
    public void setHistorico_medico(String historico_medico) {
        this.historico_medico = historico_medico;
    }


    
}


// CREATE TABLE PRONTUARIO (
//     prontuario_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
//     paciente_id INT,
//     consulta_id INT,
//     data_registro DATE,
//     observacoes VARCHAR(255),
//     exames VARCHAR(255),
//     historico_medico VARCHAR(255),
//     CONSTRAINT FK_PRONTUARIO_PACIENTE FOREIGN KEY (paciente_id) REFERENCES PACIENTE(paciente_id) ON DELETE RESTRICT,
//     CONSTRAINT FK_PRONTUARIO_CONSULTA FOREIGN KEY (consulta_id) REFERENCES CONSULTA(consulta_id) ON DELETE RESTRICT
// );

