/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.alg3.minhaconsulta.view.Janelas;


import java.text.ParseException;
import com.alg3.minhaconsulta.controller.ConsultaController;
import com.alg3.minhaconsulta.controller.MedicoController;
import com.alg3.minhaconsulta.controller.PacienteController;
import com.alg3.minhaconsulta.controller.ProntuarioController;
import com.alg3.minhaconsulta.dao.ExceptionDAO;
import com.alg3.minhaconsulta.model.Medico;
import com.alg3.minhaconsulta.model.Paciente;
import com.alg3.minhaconsulta.model.Prontuario;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import java.awt.Component;
import java.awt.Color;
import javax.swing.text.MaskFormatter;


import com.formdev.flatlaf.FlatLightLaf;
import java.awt.HeadlessException;
import java.util.ArrayList;

 
import javax.swing.JOptionPane;

/**
 *
 * @author André Micael Sampaio Pinto <andre at alg3.org>
 */
public class TelaEditarProntuario extends javax.swing.JFrame {
   

    MaskFormatter mfdata;
//
    public TelaEditarProntuario() {

        try {
            FlatLightLaf.setup();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(TelaEditarProntuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
        listarProntuarios();
        
      
       
        try {
            InputDataRegistro.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(mfdata));
        } catch (Exception ex) {
            System.err.println("Erro ao aplicar máscara ao campo de data: " + ex.getMessage());
        }
       
       
        submitCancelar.addActionListener((java.awt.event.ActionEvent evt) -> {
            // Código para fechar a tela de cadastro
            dispose();

        });

   
        submitEditarProntuario.addActionListener((java.awt.event.ActionEvent evt) -> {
            if (inputSelectMedico.getSelectedIndex() == 0 || inputSelectPaciente.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "Selecione um médico ou paciente válido");
                return;
            }
        
            int pacienteId = Integer.parseInt(((String) inputSelectPaciente.getSelectedItem()).split(" - ")[0].trim()); //transformar em int       
            int medicoId = Integer.parseInt(((String) inputSelectMedico.getSelectedItem()).split(" - ")[0].trim()); // transformar em int
            String dataConsulta = InputDataRegistro.getText().trim();
            String observacoes = InputTipoConsulta.getText().trim(); // Conecta com a coluna observacoes do banco de dados
            String valorStr = InputExames.getText().replace(",", ".").trim(); // Substitui vírgula por ponto
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
                    System.out.println("Consulta editada com sucesso");
                    JOptionPane.showMessageDialog(null, "Consulta editada com sucesso");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro ao editar consulta");
                }
            } catch (HeadlessException ex) {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao editar consulta. Erro: " + ex);
            }
        });
        jComboBoxEditarProntuario.addActionListener((java.awt.event.ActionEvent evt) -> {
            if (jComboBoxEditarProntuario.getSelectedIndex() > 0) {
                String selectedItem = (String) jComboBoxEditarProntuario.getSelectedItem();
                int prontuarioId = Integer.parseInt(selectedItem.split(" - ")[0].trim());
                try {
                    ProntuarioController prontuarioController = new ProntuarioController();
                    Prontuario prontuario = prontuarioController.listarProntuariosId(prontuarioId).get(0);
                    preencherCampos(prontuario);
                } catch (ExceptionDAO ex) {
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro ao buscar o prontuário. Erro: " + ex);
                }
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

    public void listarProntuarios() {
    try {
    ProntuarioController prontuarioController = new ProntuarioController();
    MedicoController medicoController = new MedicoController();
    PacienteController pacienteController = new PacienteController();
    ArrayList<Prontuario> prontuarios = prontuarioController.listarProntuarios("");

      DefaultComboBoxModel < String > model = new DefaultComboBoxModel < > ();
      jComboBoxEditarProntuario.setModel(model);

      if (prontuarios.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Não há mais prontuários.");
      } else {
        model.addElement("Selecione o prontuário");
        for (Prontuario prontuario: prontuarios) {
            Medico medico = medicoController.listarMedicosId(prontuario.getMedicoId()).get(0);
            Paciente paciente = pacienteController.listarPacientesId(prontuario.getPacienteId()).get(0);    
                
                String item = String.format("%d - Paciente: %s - Médico: %s", 
                    prontuario.getId(),
                    paciente != null ? paciente.getNome() : "Não encontrado",
                    medico != null ? medico.getNome() : "Não encontrado"
                );
                model.addElement(item);
           }

           jComboBoxEditarProntuario.setRenderer(new DefaultListCellRenderer() {
          @Override
          public Component getListCellRendererComponent(JList < ? > list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value != null && value.equals("Selecione o prontuário")) {
              c.setForeground(Color.GRAY);
              setEnabled(false);
            }
            return c;
          }
        });

        if (model.getSize() > 1) {
            jComboBoxEditarProntuario.setSelectedIndex(1);
        }
      }
    } catch (ExceptionDAO ex) {
      JOptionPane.showMessageDialog(null, "Ocorreu um erro ao listar os prontuários. Erro: " + ex);
    }
  }

  public void preencherCampos(Prontuario prontuario) {
    try {
      MedicoController medicoController = new MedicoController();
      PacienteController pacienteController = new PacienteController();
      Medico medico = medicoController.listarMedicosId(prontuario.getMedicoId()).get(0);
      Paciente paciente = pacienteController.listarPacientesId(prontuario.getPacienteId()).get(0);

      inputSelectPaciente.setSelectedItem(prontuario.getPacienteId() + " - " + paciente.getNome());
      inputSelectMedico.setSelectedItem(prontuario.getMedicoId() + " - " + medico.getNome());
      InputDataRegistro.setText(prontuario.getDataRegistro());
      InputTipoConsulta.setText(prontuario.getObservacoes());
      InputExames.setText(String.valueOf(prontuario.getExames()));
      InputHistoricoMedico.setText(prontuario.getHistoricoMedico());
    } catch (ExceptionDAO ex) {
      JOptionPane.showMessageDialog(null, "Ocorreu um erro ao preencher os campos. Erro: " + ex);
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
        jComboBoxEditar = new javax.swing.JComboBox<>();
        TitleCadastrarPaciente = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        submitEditarProntuario = new javax.swing.JButton();
        submitCancelar = new javax.swing.JButton();
        LabelPaciente = new javax.swing.JLabel();
        InputDataRegistro =  new javax.swing.JFormattedTextField(mfdata);
        LabelDataRegistro = new javax.swing.JLabel();
        LabelMedico = new javax.swing.JLabel();
        LabelTipoConsulta = new javax.swing.JLabel();
        InputTipoConsulta = new javax.swing.JTextField();
        LabelExames = new javax.swing.JLabel();
        InputExames = new javax.swing.JTextField();
        inputSelectMedico = new javax.swing.JComboBox<>();
        inputSelectPaciente = new javax.swing.JComboBox<>();
        jComboBoxEditarProntuario = new javax.swing.JComboBox<>();
        InputHistoricoMedico = new javax.swing.JTextField();
        LabelHistorico = new javax.swing.JLabel();

        jComboBoxEditar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 255));
        setMinimumSize(new java.awt.Dimension(512, 300));
        setResizable(false);

        TitleCadastrarPaciente.setFont(new java.awt.Font("Inter SemiBold", 0, 12)); // NOI18N
        TitleCadastrarPaciente.setForeground(new java.awt.Color(0, 51, 153));
        TitleCadastrarPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/doutora.png"))); // NOI18N
        TitleCadastrarPaciente.setText("Editar Prontuario");
        TitleCadastrarPaciente.setMaximumSize(new java.awt.Dimension(178, 64));
        TitleCadastrarPaciente.setMinimumSize(new java.awt.Dimension(178, 64));

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));

        submitEditarProntuario.setFont(new java.awt.Font("Inter", 1, 12)); // NOI18N
        submitEditarProntuario.setForeground(new java.awt.Color(0, 51, 153));
        submitEditarProntuario.setText("OK");
        submitEditarProntuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitEditarProntuarioActionPerformed(evt);
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

        InputDataRegistro.setToolTipText("");
        InputDataRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputDataRegistroActionPerformed(evt);
            }
        });

        LabelDataRegistro.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        LabelDataRegistro.setText("Data Consulta:");

        LabelMedico.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        LabelMedico.setText("Médico:");

        LabelTipoConsulta.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        LabelTipoConsulta.setText("Obs.:");

        InputTipoConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputTipoConsultaActionPerformed(evt);
            }
        });

        LabelExames.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        LabelExames.setText("Exames");

        InputExames.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputExamesActionPerformed(evt);
            }
        });

        jComboBoxEditarProntuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        InputHistoricoMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputHistoricoMedicoActionPerformed(evt);
            }
        });

        LabelHistorico.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        LabelHistorico.setText("Hist.");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 150, Short.MAX_VALUE)
                        .addComponent(submitCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(submitEditarProntuario)
                        .addGap(0, 177, Short.MAX_VALUE))
                    .addComponent(jComboBoxEditarProntuario, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(LabelPaciente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inputSelectPaciente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LabelDataRegistro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(InputDataRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(LabelMedico)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(inputSelectMedico, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(LabelTipoConsulta)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(InputTipoConsulta)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(LabelExames)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(InputExames))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(LabelHistorico)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(InputHistoricoMedico)
                                .addGap(5, 5, 5)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jComboBoxEditarProntuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelPaciente)
                    .addComponent(LabelDataRegistro)
                    .addComponent(InputDataRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputSelectPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(InputTipoConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelTipoConsulta)
                    .addComponent(InputExames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelExames))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputSelectMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelMedico)
                    .addComponent(InputHistoricoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelHistorico))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(submitEditarProntuario)
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

    private void submitEditarProntuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitEditarProntuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_submitEditarProntuarioActionPerformed

    private void InputDataRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputDataRegistroActionPerformed
        
    }//GEN-LAST:event_InputDataRegistroActionPerformed

    private void InputTipoConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputTipoConsultaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputTipoConsultaActionPerformed

    private void InputExamesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputExamesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputExamesActionPerformed

    private void InputHistoricoMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputHistoricoMedicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputHistoricoMedicoActionPerformed

  

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
            java.util.logging.Logger.getLogger(TelaEditarProntuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaEditarProntuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaEditarProntuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaEditarProntuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaEditarProntuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField InputDataRegistro;
    private javax.swing.JTextField InputExames;
    private javax.swing.JTextField InputHistoricoMedico;
    private javax.swing.JComboBox<String> InputSexo;
    private javax.swing.JTextField InputTipoConsulta;
    private javax.swing.JLabel LabelDataRegistro;
    private javax.swing.JLabel LabelExames;
    private javax.swing.JLabel LabelHistorico;
    private javax.swing.JLabel LabelMedico;
    private javax.swing.JLabel LabelPaciente;
    private javax.swing.JLabel LabelTipoConsulta;
    private javax.swing.JLabel TitleCadastrarPaciente;
    private javax.swing.JComboBox<String> inputSelectMedico;
    private javax.swing.JComboBox<String> inputSelectPaciente;
    private javax.swing.JComboBox<String> jComboBoxEditar;
    private javax.swing.JComboBox<String> jComboBoxEditarProntuario;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton submitCancelar;
    private javax.swing.JButton submitEditarProntuario;
    // End of variables declaration//GEN-END:variables
}
