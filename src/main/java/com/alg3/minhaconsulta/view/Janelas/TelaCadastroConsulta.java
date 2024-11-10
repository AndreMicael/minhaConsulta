/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.alg3.minhaconsulta.view.Janelas;


import java.text.ParseException;
import com.alg3.minhaconsulta.controller.ConsultaController;
import com.alg3.minhaconsulta.controller.MedicoController;
import com.alg3.minhaconsulta.controller.PacienteController;
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
public class TelaCadastroConsulta extends javax.swing.JFrame {
   

    MaskFormatter mfdata;
//
    public TelaCadastroConsulta() {

        try {
            FlatLightLaf.setup();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(TelaCadastroConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
            InputDataConsulta.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(mfdata));
        } catch (Exception ex) {
            System.err.println("Erro ao aplicar máscara ao campo de data: " + ex.getMessage());
        }
       
       
        submitCancelar.addActionListener((java.awt.event.ActionEvent evt) -> {
            // Código para fechar a tela de cadastro
            dispose();

        });

   
        submitCadastrarConsulta.addActionListener((java.awt.event.ActionEvent evt) -> {
            if (inputSelectMedico.getSelectedIndex() == 0 || inputSelectPaciente.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "Selecione um médico ou paciente válido");
                return;
            }
        
            int pacienteId = Integer.parseInt(((String) inputSelectPaciente.getSelectedItem()).split(" - ")[0].trim()); //transformar em int       
            int medicoId = Integer.parseInt(((String) inputSelectMedico.getSelectedItem()).split(" - ")[0].trim()); // transformar em int
            String dataConsulta = InputDataConsulta.getText().trim();
            String observacoes = InputTipoConsulta.getText().trim(); // Conecta com a coluna observacoes do banco de dados
            String valorStr = InputValorConsulta.getText().replace(",", ".").trim(); // Substitui vírgula por ponto
            boolean sucesso;
        
            // Verificação para campos obrigatórios
            if (dataConsulta.contains("_") || valorStr.isEmpty() || observacoes.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios corretamente.");
                return;
            }
        
            double valor;
            try {
                valor = Double.parseDouble(valorStr); //transformar em double
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Valor inválido.");
                return;
            }
        
            try {
                ConsultaController consultaController = new ConsultaController();
                sucesso = consultaController.cadastrarConsulta(pacienteId, medicoId, dataConsulta, valor, observacoes);
                if (sucesso) {
                    System.out.println("Consulta cadastrada com sucesso");
                    JOptionPane.showMessageDialog(null, "Consulta cadastrada com sucesso");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro ao cadastrar consulta");
                }
            } catch (HeadlessException ex) {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao cadastrar consulta. Erro: " + ex);
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
        submitCadastrarConsulta = new javax.swing.JButton();
        submitCancelar = new javax.swing.JButton();
        LabelPaciente = new javax.swing.JLabel();
        InputDataConsulta =  new javax.swing.JFormattedTextField(mfdata);
        LabelDataConsulta = new javax.swing.JLabel();
        LabelMedico = new javax.swing.JLabel();
        LabelTipoConsulta = new javax.swing.JLabel();
        InputTipoConsulta = new javax.swing.JTextField();
        LabelValor = new javax.swing.JLabel();
        InputValorConsulta = new javax.swing.JTextField();
        inputSelectMedico = new javax.swing.JComboBox<>();
        inputSelectPaciente = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 255));
        setMinimumSize(new java.awt.Dimension(512, 300));
        setResizable(false);

        TitleCadastrarPaciente.setFont(new java.awt.Font("Inter SemiBold", 0, 12)); // NOI18N
        TitleCadastrarPaciente.setForeground(new java.awt.Color(0, 51, 153));
        TitleCadastrarPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/doutora.png"))); // NOI18N
        TitleCadastrarPaciente.setText("Nova Consulta");
        TitleCadastrarPaciente.setMaximumSize(new java.awt.Dimension(178, 64));
        TitleCadastrarPaciente.setMinimumSize(new java.awt.Dimension(178, 64));

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        submitCadastrarConsulta.setFont(new java.awt.Font("Inter", 1, 12)); // NOI18N
        submitCadastrarConsulta.setForeground(new java.awt.Color(0, 51, 153));
        submitCadastrarConsulta.setText("OK");
        submitCadastrarConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitCadastrarConsultaActionPerformed(evt);
            }
        });

        submitCancelar.setFont(new java.awt.Font("Inter", 1, 12)); // NOI18N
        submitCancelar.setForeground(new java.awt.Color(0, 51, 153));
        submitCancelar.setText("Cancelar");
        submitCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitCancelarActionPerformed(evt);
            }
        });

        LabelPaciente.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        LabelPaciente.setText("Paciente:");

        InputDataConsulta.setToolTipText("");
        InputDataConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputDataConsultaActionPerformed(evt);
            }
        });

        LabelDataConsulta.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        LabelDataConsulta.setText("Data Consulta:");

        LabelMedico.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        LabelMedico.setText("Médico:");

        LabelTipoConsulta.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        LabelTipoConsulta.setText("Consulta:");

        InputTipoConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputTipoConsultaActionPerformed(evt);
            }
        });

        LabelValor.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        LabelValor.setText("Valor:");

        InputValorConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputValorConsultaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(LabelTipoConsulta)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(InputTipoConsulta))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(LabelPaciente)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(inputSelectPaciente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 150, Short.MAX_VALUE)
                                .addComponent(submitCancelar)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(LabelValor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(InputValorConsulta))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(submitCadastrarConsulta))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(LabelDataConsulta)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(InputDataConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(LabelMedico)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inputSelectMedico, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelPaciente)
                    .addComponent(LabelDataConsulta)
                    .addComponent(InputDataConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputSelectPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(InputTipoConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelTipoConsulta)
                    .addComponent(InputValorConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelValor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LabelMedico)
                    .addComponent(inputSelectMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(submitCadastrarConsulta)
                    .addComponent(submitCancelar))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(TitleCadastrarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(TitleCadastrarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleName("Nova Consulta");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void submitCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitCancelarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_submitCancelarActionPerformed

    private void submitCadastrarConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitCadastrarConsultaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_submitCadastrarConsultaActionPerformed

    private void InputDataConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputDataConsultaActionPerformed
        
    }//GEN-LAST:event_InputDataConsultaActionPerformed

    private void InputTipoConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputTipoConsultaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputTipoConsultaActionPerformed

    private void InputValorConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputValorConsultaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputValorConsultaActionPerformed

  

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
            java.util.logging.Logger.getLogger(TelaCadastroConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCadastroConsulta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField InputDataConsulta;
    private javax.swing.JComboBox<String> InputSexo;
    private javax.swing.JTextField InputTipoConsulta;
    private javax.swing.JTextField InputValorConsulta;
    private javax.swing.JLabel LabelDataConsulta;
    private javax.swing.JLabel LabelMedico;
    private javax.swing.JLabel LabelPaciente;
    private javax.swing.JLabel LabelTipoConsulta;
    private javax.swing.JLabel LabelValor;
    private javax.swing.JLabel TitleCadastrarPaciente;
    private javax.swing.JComboBox<String> inputSelectMedico;
    private javax.swing.JComboBox<String> inputSelectPaciente;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton submitCadastrarConsulta;
    private javax.swing.JButton submitCancelar;
    // End of variables declaration//GEN-END:variables
}
