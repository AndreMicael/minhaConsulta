/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.alg3.minhaconsulta.view.Cadastros;


import java.text.ParseException;
import com.alg3.minhaconsulta.controller.ConsultaController;


import javax.swing.text.MaskFormatter;


import com.formdev.flatlaf.FlatLightLaf;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;

/**
 *
 * @author André Micael Sampaio Pinto <andre at alg3.org>
 */
public class TelaCadastroConsulta extends javax.swing.JFrame {

    /**
     * Creates new form TelaCadastroCliente
     */
    MaskFormatter mfdata;
 //
   // public List<String> convenios = new ArrayList<>();
  //public List<String> generos = new ArrayList<>();
//
    public TelaCadastroConsulta() {

        try {
            FlatLightLaf.setup();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(TelaCadastroMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        initComponents();

        try {
            mfdata = new MaskFormatter("##/##/####");
        } catch (ParseException ex) {
            System.out.println("Ocorreu um erro na criação da máscara");
        }

       
        submitCancelar.addActionListener((java.awt.event.ActionEvent evt) -> {
            // Código para fechar a tela de cadastro
            dispose();

        });

        /*
        InputConvenio.removeAll();
        convenios.add("Unimed");
        convenios.add("Sulamerica");
        convenios.add("Bradesco");
        
        for (String convenio : convenios) {
            InputConvenio.addItem(convenio);
        }
        
        InputConsultaPaciente.removeAll();
        generos.add("Masculino");
        generos.add("Feminino");
        generos.add("Outro");
        
        for (String genero: generos) {
            InputConsultaPaciente.addItem(genero);
        }
        
        
         
          */
            submitCadastrarConsulta.addActionListener((java.awt.event.ActionEvent evt) -> {
            int pacienteId = Integer.parseInt(InputPacienteId.getText());   //transformar em int       
            int medicoId = Integer.parseInt(InputMedicoId.getText()); // transformar em int
            String dataConsulta = InputDataConsulta.getText();
            String tipo = InputTipoConsulta.getText();
            double valor = Double.parseDouble(InputValorConsulta.getText()); //transformar em double
            String status = InputStatus.getText();
            boolean sucesso;
        
     
        
            try {
                ConsultaController consultaController = new ConsultaController();
                sucesso = consultaController.cadastrarConsulta(pacienteId, medicoId, dataConsulta, valor, tipo, status);
                if (sucesso) {
                    System.out.println("Consulta cadastrada com sucesso");
                    JOptionPane.showMessageDialog(null, "Consulta cadastrada com sucesso");
                    // System.out.println("Dados enviados para o controller: " + nome + ", " + endereco);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro ao cadastrar consulta");
                }
            } catch (HeadlessException ex) {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao cadastrar consulta. Erro: " + ex);
            }
        });
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TitleCadastrarPaciente = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        submitCadastrarConsulta = new javax.swing.JButton();
        submitCancelar = new javax.swing.JButton();
        LabelPaciente = new javax.swing.JLabel();
        InputPacienteId = new javax.swing.JTextField();
        InputDataConsulta =  new javax.swing.JFormattedTextField(mfdata);
        LabelDataConsulta = new javax.swing.JLabel();
        LabelMedico = new javax.swing.JLabel();
        InputMedicoId = new javax.swing.JTextField();
        LabelTipo = new javax.swing.JLabel();
        InputTipoConsulta = new javax.swing.JTextField();
        LabelValor = new javax.swing.JLabel();
        InputValorConsulta = new javax.swing.JTextField();
        InputStatus = new javax.swing.JTextField();
        LabelStatus = new javax.swing.JLabel();

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

        InputPacienteId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputPacienteIdActionPerformed(evt);
            }
        });

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

        InputMedicoId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputMedicoIdActionPerformed(evt);
            }
        });

        LabelTipo.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        LabelTipo.setText("Tipo de Consulta:");

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

        InputStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputStatusActionPerformed(evt);
            }
        });

        LabelStatus.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        LabelStatus.setText("Status:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(LabelTipo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(InputTipoConsulta))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(LabelPaciente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(InputPacienteId))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 150, Short.MAX_VALUE)
                        .addComponent(submitCancelar)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(submitCadastrarConsulta))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(LabelDataConsulta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(InputDataConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(LabelValor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(InputValorConsulta)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(LabelMedico)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(InputMedicoId, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(LabelStatus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(InputStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(InputPacienteId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelPaciente)
                    .addComponent(LabelDataConsulta)
                    .addComponent(InputDataConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(InputTipoConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelTipo)
                    .addComponent(InputValorConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelValor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(InputMedicoId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelMedico)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(InputStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LabelStatus)))
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

    private void InputPacienteIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputPacienteIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputPacienteIdActionPerformed

    private void InputDataConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputDataConsultaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputDataConsultaActionPerformed

    private void InputMedicoIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputMedicoIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputMedicoIdActionPerformed

    private void InputTipoConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputTipoConsultaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputTipoConsultaActionPerformed

    private void InputValorConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputValorConsultaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputValorConsultaActionPerformed

    private void InputStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputStatusActionPerformed

    private void InputNomePacienteActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

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
    private javax.swing.JTextField InputMedicoId;
    private javax.swing.JTextField InputPacienteId;
    private javax.swing.JTextField InputStatus;
    private javax.swing.JTextField InputTipoConsulta;
    private javax.swing.JTextField InputValorConsulta;
    private javax.swing.JLabel LabelDataConsulta;
    private javax.swing.JLabel LabelMedico;
    private javax.swing.JLabel LabelPaciente;
    private javax.swing.JLabel LabelStatus;
    private javax.swing.JLabel LabelTipo;
    private javax.swing.JLabel LabelValor;
    private javax.swing.JLabel TitleCadastrarPaciente;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton submitCadastrarConsulta;
    private javax.swing.JButton submitCancelar;
    // End of variables declaration//GEN-END:variables
}
