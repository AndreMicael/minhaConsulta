/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.alg3.minhaconsulta.view.Janelas;

import java.util.ArrayList;

import com.alg3.minhaconsulta.controller.MedicoController;
import com.alg3.minhaconsulta.dao.ExceptionDAO;
import com.alg3.minhaconsulta.model.Medico;


import javax.swing.text.MaskFormatter;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.JOptionPane;

/**
 *
 * @author André Micael Sampaio Pinto <andre at alg3.org>
 */
public class TelaEditarMedico extends javax.swing.JFrame {

    /**
     * Creates new form TelaCadastroDespesa
     */
    MaskFormatter mfdata;
    MaskFormatter mfcpf;

    private void inicializarComboBoxes() {
        // Inicializar ComboBox de Gênero
        jComboBoxGenero.removeAllItems();
        jComboBoxGenero.addItem("Selecione");
        jComboBoxGenero.addItem("Masculino");
        jComboBoxGenero.addItem("Feminino");
        jComboBoxGenero.addItem("Outro");
    
        // Inicializar ComboBox de Especialidade
        jComboBoxEspecialidade.removeAllItems();
        jComboBoxEspecialidade.addItem("Selecione");
        jComboBoxEspecialidade.addItem("Clínico Geral");
        jComboBoxEspecialidade.addItem("Cardiologista");
        jComboBoxEspecialidade.addItem("Dermatologista");
        jComboBoxEspecialidade.addItem("Ginecologista");
        jComboBoxEspecialidade.addItem("Ortopedista");
        jComboBoxEspecialidade.addItem("Pediatra");
        jComboBoxEspecialidade.addItem("Psiquiatra");
        // Adicione outras especialidades conforme necessário
    }

    public TelaEditarMedico() {

        try {
            FlatLightLaf.setup();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(TelaEditarMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

   

    initComponents();
    listarMedicos();
    inicializarComboBoxes();

        submitCancelar.addActionListener((java.awt.event.ActionEvent evt) -> {
            // Código para fechar a tela de cadastro
            dispose();
        });

      

        submitEditarMedico.addActionListener((java.awt.event.ActionEvent evt) -> {
            String selectedItem = (String) jComboBoxEditar.getSelectedItem();
            String genero = (String) jComboBoxGenero.getSelectedItem();
            String especialidade = (String) jComboBoxEspecialidade.getSelectedItem();
        
            // Validar seleções obrigatórias
            if (genero.equals("Selecione") || especialidade.equals("Selecione")) {
                JOptionPane.showMessageDialog(null, "Por favor, selecione o gênero e a especialidade do médico.", 
                    "Campos obrigatórios", JOptionPane.WARNING_MESSAGE);
                return;
            }
        
            if (selectedItem != null && !selectedItem.isEmpty()) {
                try {
                    int id = Integer.parseInt(selectedItem.split(" - ")[0].trim());
                    String nome = InputNomeMedico.getText().trim();
                    String data = InputNascimento.getText().trim();
                    String endereco = InputEnderecoMedico.getText().trim();
                    String telefone = InputTelefone.getText().trim();
                    String crm = InputCRM.getText().trim();
        
                    // Verificação para campos obrigatórios
                    if (nome.isEmpty() || data.contains("_") || endereco.isEmpty() || telefone.contains("_") || crm.contains("_")) {
                        JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios corretamente.");
                        return;
                    }
        
                    boolean sucesso;
        
                    try {
                        MedicoController medicoController = new MedicoController();
                        sucesso = medicoController.editarMedico(nome, data, endereco, telefone, crm, especialidade, genero, id);
                        if (sucesso) {
                            System.out.println("Médico editado com sucesso");
                            JOptionPane.showMessageDialog(null, "Médico editado com sucesso. ID: " + id);
                            System.out.println("Dados enviados para o controller: " + nome + ", " + endereco);
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao editar médico.");
                        }
                    } catch (ExceptionDAO ex) {
                        JOptionPane.showMessageDialog(null, "Ocorreu um erro ao editar médico. Erro: " + ex);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Formato de ID inválido: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum médico selecionado.");
            }
        });

        jComboBoxEditar.addActionListener((java.awt.event.ActionEvent evt) -> {
            String selectedItem = (String) jComboBoxEditar.getSelectedItem();
            if (selectedItem != null && !selectedItem.equals("Selecione o Medico")) {
                try {
                    int id = Integer.parseInt(selectedItem.split(" - ")[0].trim());
                    MedicoController medicoController = new MedicoController();
                    ArrayList<Medico> medicos = medicoController.listarMedicosId(id);
                    Medico medico = medicos.isEmpty() ? null : medicos.get(0);
                    preencherCampos(medico);
                } catch (NumberFormatException | ExceptionDAO ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao buscar medicos: " + ex.getMessage());
                }
            } else {
                preencherCampos(null); 
            }
        });
    }

    public void listarMedicos() {
        try {
            MedicoController medicoController = new MedicoController();
            ArrayList<Medico> medicos = medicoController.listarMedicos("");
            
            jComboBoxEditar.removeAllItems();
            jComboBoxEditar.addItem("Selecione o médico"); // Adiciona item inicial
            
            for (Medico medico : medicos) {
                String item = medico.getId() + " - " + medico.getNome() + " - " + medico.getEspecialidade();
                jComboBoxEditar.addItem(item);
            }
        } catch (ExceptionDAO ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao listar os medicos. Erro: " + ex);
        }
    }

    public void preencherCampos(Medico medico) {
    if (medico != null) {
        InputNomeMedico.setText(medico.getNome());
        InputNascimento.setText(medico.getData_nascimento());
        InputEnderecoMedico.setText(medico.getEndereco());
        InputTelefone.setText(medico.getTelefone());
        InputCRM.setText(medico.getCrm());
        
        // Debug do gênero
        String genero = medico.getGenero();
        System.out.println("Gênero recebido do médico: '" + genero + "'");
        
        // Selecionar gênero com comparação mais flexível
        if (genero != null && !genero.isEmpty()) {
            genero = genero.trim().toLowerCase();
            for (int i = 0; i < jComboBoxGenero.getItemCount(); i++) {
                String itemGenero = jComboBoxGenero.getItemAt(i).trim().toLowerCase();
                if (itemGenero.contains(genero) || genero.contains(itemGenero)) {
                    jComboBoxGenero.setSelectedIndex(i);
                    break;
                }
            }
        }
        
        // Selecionar especialidade
        String especialidade = medico.getEspecialidade();
        if (especialidade != null && !especialidade.isEmpty()) {
            for (int i = 0; i < jComboBoxEspecialidade.getItemCount(); i++) {
                if (jComboBoxEspecialidade.getItemAt(i).equalsIgnoreCase(especialidade)) {
                    jComboBoxEspecialidade.setSelectedIndex(i);
                    break;
                }
            }
        }
    } else {
        // Limpar campos
        InputNomeMedico.setText("");
        InputNascimento.setText("");
        InputEnderecoMedico.setText("");
        InputTelefone.setText("");
        InputCRM.setText("");
        jComboBoxEspecialidade.setSelectedIndex(0);
        jComboBoxGenero.setSelectedIndex(0);
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

        jComboBox1 = new javax.swing.JComboBox<>();
        TitleEditarDespesa = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        submitEditarMedico = new javax.swing.JButton();
        submitCancelar = new javax.swing.JButton();
        jComboBoxEditar = new javax.swing.JComboBox<>();
        LabelNome = new javax.swing.JLabel();
        InputNomeMedico = new javax.swing.JTextField();
        LabelNascimento = new javax.swing.JLabel();
        InputNascimento = new javax.swing.JFormattedTextField();
        LabelGenero = new javax.swing.JLabel();
        InputCRM =  new javax.swing.JFormattedTextField(mfcpf);
        LabelCPF = new javax.swing.JLabel();
        LabelEndereco = new javax.swing.JLabel();
        InputEnderecoMedico = new javax.swing.JTextField();
        jComboBoxEspecialidade = new javax.swing.JComboBox<>();
        LabelEspecialidade = new javax.swing.JLabel();
        LabelTel = new javax.swing.JLabel();
        InputTelefone = new javax.swing.JTextField();
        jComboBoxGenero = new javax.swing.JComboBox<>();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 255));
        setMinimumSize(new java.awt.Dimension(512, 300));
        setResizable(false);

        TitleEditarDespesa.setFont(new java.awt.Font("Inter SemiBold", 0, 12)); // NOI18N
        TitleEditarDespesa.setForeground(new java.awt.Color(0, 51, 153));
        TitleEditarDespesa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/doutora.png"))); // NOI18N
        TitleEditarDespesa.setText("Editar");
        TitleEditarDespesa.setMaximumSize(new java.awt.Dimension(178, 64));
        TitleEditarDespesa.setMinimumSize(new java.awt.Dimension(178, 64));

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        submitEditarMedico.setFont(new java.awt.Font("Inter", 1, 12)); // NOI18N
        submitEditarMedico.setForeground(new java.awt.Color(0, 51, 153));
        submitEditarMedico.setText("OK");
        submitEditarMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitEditarMedicoActionPerformed(evt);
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

        jComboBoxEditar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        LabelNome.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        LabelNome.setText("Nome:");

        InputNomeMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputNomeMedicoActionPerformed(evt);
            }
        });

        LabelNascimento.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        LabelNascimento.setText("Data Nasc.:");

        InputNascimento.setToolTipText("");
        InputNascimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputNascimentoActionPerformed(evt);
            }
        });

        LabelGenero.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        LabelGenero.setText("Gênero:");

        InputCRM.setToolTipText("");
        InputCRM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputCRMActionPerformed(evt);
            }
        });

        LabelCPF.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        LabelCPF.setText("CRM:");

        LabelEndereco.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        LabelEndereco.setText("Endereço:");

        InputEnderecoMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputEnderecoMedicoActionPerformed(evt);
            }
        });

        LabelEspecialidade.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        LabelEspecialidade.setText("Convênio:");

        LabelTel.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        LabelTel.setText("Tel.:");

        jComboBoxGenero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxGeneroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBoxEditar, 0, 488, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(194, 194, 194)
                .addComponent(submitCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(submitEditarMedico)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jComboBoxGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(LabelNome)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(InputNomeMedico)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(LabelNascimento)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(InputNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(LabelEspecialidade, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jComboBoxEspecialidade, 0, 204, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(LabelTel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(InputTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(LabelCPF)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(InputCRM)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(LabelGenero)
                            .addGap(164, 164, 164))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(LabelEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(InputEnderecoMedico)))
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jComboBoxEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jComboBoxGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(submitCancelar)
                    .addComponent(submitEditarMedico))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(33, 33, 33)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LabelNascimento)
                            .addComponent(InputNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(InputNomeMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(LabelNome))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(LabelCPF)
                                .addComponent(InputCRM, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(LabelGenero))))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(LabelEndereco)
                        .addComponent(InputEnderecoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(InputTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LabelEspecialidade)
                            .addComponent(jComboBoxEspecialidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LabelTel)))
                    .addContainerGap(48, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(TitleEditarDespesa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 404, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(TitleEditarDespesa, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleName("Nova Consulta");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void submitCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitCancelarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_submitCancelarActionPerformed

    private void submitEditarMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitEditarMedicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_submitEditarMedicoActionPerformed

    private void InputNomeMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputNomeMedicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputNomeMedicoActionPerformed

    private void InputCRMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputCRMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputCRMActionPerformed

    private void InputEnderecoMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputEnderecoMedicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputEnderecoMedicoActionPerformed

    private void InputNascimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputNascimentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputNascimentoActionPerformed

    private void jComboBoxGeneroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxGeneroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxGeneroActionPerformed

 
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
            java.util.logging.Logger.getLogger(TelaEditarMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaEditarMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaEditarMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaEditarMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaEditarMedico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField InputCRM;
    private javax.swing.JTextField InputEnderecoMedico;
    private javax.swing.JFormattedTextField InputNascimento;
    private javax.swing.JTextField InputNomeMedico;
    private javax.swing.JTextField InputTelefone;
    private javax.swing.JLabel LabelCPF;
    private javax.swing.JLabel LabelEndereco;
    private javax.swing.JLabel LabelEspecialidade;
    private javax.swing.JLabel LabelGenero;
    private javax.swing.JLabel LabelNascimento;
    private javax.swing.JLabel LabelNome;
    private javax.swing.JLabel LabelTel;
    private javax.swing.JLabel TitleEditarDespesa;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBoxEditar;
    private javax.swing.JComboBox<String> jComboBoxEspecialidade;
    private javax.swing.JComboBox<String> jComboBoxGenero;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton submitCancelar;
    private javax.swing.JButton submitEditarMedico;
    // End of variables declaration//GEN-END:variables
}
