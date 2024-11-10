/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.alg3.minhaconsulta.view.Janelas;


import java.text.ParseException; 
import com.alg3.minhaconsulta.controller.MedicoController;
import com.alg3.minhaconsulta.controller.PacienteController;
import com.alg3.minhaconsulta.controller.ProntuarioController;
import com.alg3.minhaconsulta.dao.ExceptionDAO;
 
import com.alg3.minhaconsulta.model.Medico;
import com.alg3.minhaconsulta.model.Paciente;
 
import javax.swing.text.MaskFormatter;


import com.formdev.flatlaf.FlatLightLaf;
import java.awt.HeadlessException;
import java.util.ArrayList;
 
import javax.swing.JOptionPane;

/**
 *
 * @author André Micael Sampaio Pinto <andre at alg3.org>
 */
public class TelaCadastroProntuario extends javax.swing.JFrame {
   

    MaskFormatter mfdata;
//
    public TelaCadastroProntuario() {

        try {
            FlatLightLaf.setup();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(TelaCadastroProntuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
     

        try {
            mfdata = new MaskFormatter("##/##/####");
            mfdata.setPlaceholderCharacter('_');
            mfdata.setValidCharacters("0123456789");
        } catch (ParseException ex) {
            System.err.println("Erro ao criar máscara de data: " + ex.getMessage());
        }

        initComponents();
        listarPacientes();
        listarMedicos();
      
       
        try {
            InputDataRegistro.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(mfdata));
        } catch (Exception ex) {
            System.err.println("Erro ao aplicar máscara ao campo de data: " + ex.getMessage());
        }
       
       
        submitCadastrarProntuario.addActionListener((java.awt.event.ActionEvent evt) -> {
            if (inputSelectMedico.getSelectedIndex() == 0 || 
                inputSelectPaciente.getSelectedIndex() == 0  
            ){
                JOptionPane.showMessageDialog(null, "Selecione um médico e paciente válidos");
                return;
            }
        
            int pacienteId = Integer.parseInt(((String) inputSelectPaciente.getSelectedItem()).split(" - ")[0].trim());      
            int medicoId = Integer.parseInt(((String) inputSelectMedico.getSelectedItem()).split(" - ")[0].trim());
 
           
            String dataConsulta = InputDataRegistro.getText().trim();
            String observacoes = inputObservacoes.getText();
            String exames = inputExames.getText();
            String historico = inputHistorico.getText();
        
            boolean sucesso;
        
            if (dataConsulta.contains("_") || observacoes.isEmpty() || exames.isEmpty() || historico.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios corretamente.");
                return;
            } 
            
            try {
                ProntuarioController prontuarioController = new ProntuarioController();
                sucesso = prontuarioController.cadastrarProntuario(
                    pacienteId, 
                    medicoId, 
                    dataConsulta, 
                    observacoes, 
                    exames, 
                    historico
                );
                
                if (sucesso) {
                    System.out.println("Prontuário cadastrado com sucesso");
                    JOptionPane.showMessageDialog(null, "Prontuário cadastrado com sucesso");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro ao cadastrar prontuário");
                }
            } catch (HeadlessException ex) {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao cadastrar prontuário. Erro: " + ex);
            }
        });

        }
    
        private void listarPacientes() {
            try {
                PacienteController pacienteController = new PacienteController();
                ArrayList<Paciente> pacientes = pacienteController.listarPacientes("");
                inputSelectPaciente.removeAllItems();
                inputSelectPaciente.addItem("Selecione um paciente");
                for (Paciente paciente : pacientes) {
                    String item = paciente.getId() + " - " + paciente.getNome();
                    inputSelectPaciente.addItem(item);
                }
            } catch (ExceptionDAO ex) {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao listar os pacientes. Erro: " + ex);
            }
        }

    private void listarMedicos() {
        try {
            MedicoController medicoController = new MedicoController();
            ArrayList<Medico> medicos = medicoController.listarMedicos("");
            inputSelectMedico.removeAllItems();
            inputSelectMedico.addItem("Selecione um médico"); 
            for (Medico medico : medicos) {
                String item = medico.getId() + " - " + medico.getNome();
                inputSelectMedico.addItem(item);
            }
        } catch (ExceptionDAO ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao listar os médicos. Erro: " + ex);
        }
    }

    




    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        InputSexo = new javax.swing.JComboBox<>();
        TitleCadastrarPaciente = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        submitCadastrarProntuario = new javax.swing.JButton();
        submitCancelar = new javax.swing.JButton();
        LabelPaciente = new javax.swing.JLabel();
        InputDataRegistro =  new javax.swing.JFormattedTextField(mfdata);
        LabelDataConsulta = new javax.swing.JLabel();
        LabelMedico = new javax.swing.JLabel();
        LabelObs = new javax.swing.JLabel();
        inputSelectMedico = new javax.swing.JComboBox<>();
        inputSelectPaciente = new javax.swing.JComboBox<>();
        inputObservacoes = new javax.swing.JTextField();
        LabelExame = new javax.swing.JLabel();
        inputExames = new javax.swing.JTextField();
        inputHistorico = new javax.swing.JTextField();
        LabelExame1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 255));
        setMinimumSize(new java.awt.Dimension(512, 300));
        setResizable(false);

        TitleCadastrarPaciente.setBackground(new java.awt.Color(255, 255, 255));
        TitleCadastrarPaciente.setFont(new java.awt.Font("Inter SemiBold", 0, 18)); // NOI18N
        TitleCadastrarPaciente.setForeground(new java.awt.Color(0, 51, 153));
        TitleCadastrarPaciente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TitleCadastrarPaciente.setText("Novo Prontuario");
        TitleCadastrarPaciente.setMaximumSize(new java.awt.Dimension(178, 64));
        TitleCadastrarPaciente.setMinimumSize(new java.awt.Dimension(178, 64));

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        submitCadastrarProntuario.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        submitCadastrarProntuario.setForeground(new java.awt.Color(0, 51, 153));
        submitCadastrarProntuario.setText("OK");
        submitCadastrarProntuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitCadastrarProntuarioActionPerformed(evt);
            }
        });

        submitCancelar.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        submitCancelar.setForeground(new java.awt.Color(0, 51, 153));
        submitCancelar.setText("Cancelar");
        submitCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitCancelarActionPerformed(evt);
            }
        });

        LabelPaciente.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        LabelPaciente.setText("Paciente:");

        InputDataRegistro.setToolTipText("");
        InputDataRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputDataRegistroActionPerformed(evt);
            }
        });

        LabelDataConsulta.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        LabelDataConsulta.setText("Data Registro:");

        LabelMedico.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        LabelMedico.setText("Médico:");

        LabelObs.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        LabelObs.setText("Obs.:");

        LabelExame.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        LabelExame.setText("Exame:");

        LabelExame1.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        LabelExame1.setText("Histórico Medico:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(LabelExame1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(inputHistorico))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(LabelMedico)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(inputSelectMedico, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(LabelPaciente)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(inputSelectPaciente, 0, 151, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(LabelDataConsulta)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(InputDataRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(LabelExame)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(inputExames))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(LabelObs)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(inputObservacoes)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(153, 153, 153)
                .addComponent(submitCancelar)
                .addGap(7, 7, 7)
                .addComponent(submitCadastrarProntuario)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelPaciente)
                    .addComponent(LabelDataConsulta)
                    .addComponent(InputDataRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputSelectPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelObs)
                    .addComponent(inputObservacoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LabelMedico)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(inputSelectMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LabelExame)
                        .addComponent(inputExames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelExame1)
                    .addComponent(inputHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(submitCadastrarProntuario)
                    .addComponent(submitCancelar))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TitleCadastrarPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(TitleCadastrarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleName("Nova Consulta");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void submitCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitCancelarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_submitCancelarActionPerformed

    private void submitCadastrarProntuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitCadastrarProntuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_submitCadastrarProntuarioActionPerformed

    private void InputDataRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputDataRegistroActionPerformed
        
    }//GEN-LAST:event_InputDataRegistroActionPerformed

  

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("FlatLightLaf".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroProntuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroProntuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroProntuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroProntuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCadastroProntuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField InputDataRegistro;
    private javax.swing.JComboBox<String> InputSexo;
    private javax.swing.JLabel LabelDataConsulta;
    private javax.swing.JLabel LabelExame;
    private javax.swing.JLabel LabelExame1;
    private javax.swing.JLabel LabelMedico;
    private javax.swing.JLabel LabelObs;
    private javax.swing.JLabel LabelPaciente;
    private javax.swing.JLabel TitleCadastrarPaciente;
    private javax.swing.JTextField inputExames;
    private javax.swing.JTextField inputHistorico;
    private javax.swing.JTextField inputObservacoes;
    private javax.swing.JComboBox<String> inputSelectMedico;
    private javax.swing.JComboBox<String> inputSelectPaciente;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton submitCadastrarProntuario;
    private javax.swing.JButton submitCancelar;
    // End of variables declaration//GEN-END:variables
}
