/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.alg3.minhaconsulta.view.Janelas;

import java.util.ArrayList;

import com.alg3.minhaconsulta.controller.PacienteController;
import com.alg3.minhaconsulta.dao.ExceptionDAO;
import com.alg3.minhaconsulta.model.Paciente;

import javax.swing.text.MaskFormatter;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;

/**
 *
 * @author André Micael Sampaio Pinto <andre at alg3.org>
 */
public class TelaEditarConsulta extends javax.swing.JFrame {

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
    
        // Inicializar ComboBox de Convênio
        jComboBoxConvenio.removeAllItems();
        jComboBoxConvenio.addItem("Selecione");
        jComboBoxConvenio.addItem("Particular");
        jComboBoxConvenio.addItem("Unimed");
        jComboBoxConvenio.addItem("Amil");
        jComboBoxConvenio.addItem("SulAmérica");
        jComboBoxConvenio.addItem("Bradesco Saúde");
    }

    public TelaEditarConsulta() {

        try {
            FlatLightLaf.setup();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(TelaEditarConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

   

    initComponents();
    listarPacientes();
    inicializarComboBoxes();

        submitCancelar.addActionListener((java.awt.event.ActionEvent evt) -> {
            // Código para fechar a tela de cadastro
            dispose();
        });

      

        submitEditarCliente.addActionListener((java.awt.event.ActionEvent evt) -> {
            String selectedItem = (String) jComboBoxEditar.getSelectedItem();
            String genero = (String) jComboBoxGenero.getSelectedItem();
            String convenio = (String) jComboBoxConvenio.getSelectedItem();
        
            // Validar seleções obrigatórias
            if (genero.equals("Selecione") || convenio.equals("Selecione")) {
                JOptionPane.showMessageDialog(null, "Por favor, selecione o gênero e o convênio do paciente.", 
                    "Campos obrigatórios", JOptionPane.WARNING_MESSAGE);
                return;
            }
        
            if (selectedItem != null && !selectedItem.isEmpty()) {
                try {
                    int id = Integer.parseInt(selectedItem.split(" - ")[0].trim());
                    String nome = InputNomePaciente.getText().trim();
                    String data = InputDataConsulta.getText().trim();
                    String endereco = InputEnderecoPaciente.getText().trim();
                    String telefone = InputTelefone.getText().trim();
                    String cpf = InputCPF.getText().trim();
        
                    // Verificação para campos obrigatórios
                    if (nome.isEmpty() || data.contains("_") || endereco.isEmpty() || telefone.contains("_") || cpf.contains("_")) {
                        JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios corretamente.");
                        return;
                    }
        
                    boolean sucesso;
        
                    try {
                        PacienteController pacienteController = new PacienteController();
                        sucesso = pacienteController.editarPaciente(nome, data, endereco, telefone, cpf, convenio, genero, id);
                        if (sucesso) {
                            System.out.println("Paciente editado com sucesso");
                            JOptionPane.showMessageDialog(null, "Paciente editado com sucesso. ID: " + id);
                            System.out.println("Dados enviados para o controller: " + nome + ", " + endereco);
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao editar paciente.");
                        }
                    } catch (HeadlessException ex) {
                        JOptionPane.showMessageDialog(null, "Ocorreu um erro ao editar paciente. Erro: " + ex);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Formato de ID inválido: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum paciente selecionado.");
            }
        });

        jComboBoxEditar.addActionListener((java.awt.event.ActionEvent evt) -> {
            String selectedItem = (String) jComboBoxEditar.getSelectedItem();
            if (selectedItem != null && !selectedItem.equals("Selecione o paciente")) {
                try {
                    int id = Integer.parseInt(selectedItem.split(" - ")[0].trim());
                    PacienteController pacienteController = new PacienteController();
                    ArrayList<Paciente> pacientes = pacienteController.listarPacientesId(id);
                    Paciente paciente = pacientes.isEmpty() ? null : pacientes.get(0);
                    preencherCampos(paciente);
                } catch (NumberFormatException | ExceptionDAO ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao buscar paciente: " + ex.getMessage());
                }
            } else {
                preencherCampos(null); // Limpa os campos quando "Selecione o paciente" está selecionado
            }
        });
    }

    public void listarPacientes() {
        try {
            PacienteController pacienteController = new PacienteController();
            ArrayList<Paciente> pacientes = pacienteController.listarPacientes("");
            
            jComboBoxEditar.removeAllItems();
            jComboBoxEditar.addItem("Selecione o paciente"); // Adiciona item inicial
            
            for (Paciente paciente : pacientes) {
                String item = paciente.getId() + " - " + paciente.getNome() + " - " + paciente.getCpf();
                jComboBoxEditar.addItem(item);
            }
        } catch (ExceptionDAO ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao listar os pacientes. Erro: " + ex);
        }
    }

    public void preencherCampos(Paciente paciente) {
        if (paciente != null) {
            InputNomePaciente.setText(paciente.getNome());
            InputDataConsulta.setText(paciente.getData_nascimento());
            InputEnderecoPaciente.setText(paciente.getEndereco());
            InputTelefone.setText(paciente.getTelefone());
            InputCPF.setText(paciente.getCpf());
            
            // Debug do gênero
            String genero = paciente.getGenero();
            System.out.println("Gênero recebido do paciente: '" + genero + "'");
            System.out.println("Tamanho da string do gênero: " + genero.length());
            System.out.println("Bytes do gênero: ");
            for (byte b : genero.getBytes()) {
                System.out.print(b + " ");
            }
            System.out.println();
            
            // Selecionar gênero com comparação mais flexível
            if (genero != null && !genero.isEmpty()) {
                genero = genero.trim().toLowerCase(); // Remove espaços e converte para minúsculo
                for (int i = 0; i < jComboBoxGenero.getItemCount(); i++) {
                    String itemGenero = jComboBoxGenero.getItemAt(i).trim().toLowerCase();
                    System.out.println("Comparando '" + genero + "' com '" + itemGenero + "'");
                    
                    if (itemGenero.contains(genero) || genero.contains(itemGenero)) {
                        jComboBoxGenero.setSelectedIndex(i);
                        System.out.println("Match encontrado! Selecionando índice " + i);
                        break;
                    }
                }
            }
            
            // Selecionar convênio
            String convenio = paciente.getConvenio();
            if (convenio != null && !convenio.isEmpty()) {
                for (int i = 0; i < jComboBoxConvenio.getItemCount(); i++) {
                    if (jComboBoxConvenio.getItemAt(i).equalsIgnoreCase(convenio)) {
                        jComboBoxConvenio.setSelectedIndex(i);
                        break;
                    }
                }
            }
        } else {
            // Limpar campos
            InputNomePaciente.setText("");
            InputDataConsulta.setText("");
            InputEnderecoPaciente.setText("");
            InputTelefone.setText("");
            InputCPF.setText("");
            jComboBoxConvenio.setSelectedIndex(0);
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
        submitEditarCliente = new javax.swing.JButton();
        submitCancelar = new javax.swing.JButton();
        jComboBoxEditar = new javax.swing.JComboBox<>();
        LabelNome = new javax.swing.JLabel();
        InputNomePaciente = new javax.swing.JTextField();
        LabelNascimento = new javax.swing.JLabel();
        InputDataConsulta = new javax.swing.JFormattedTextField();
        LabelGenero = new javax.swing.JLabel();
        InputCPF =  new javax.swing.JFormattedTextField(mfcpf);
        LabelCPF = new javax.swing.JLabel();
        LabelEndereco = new javax.swing.JLabel();
        InputEnderecoPaciente = new javax.swing.JTextField();
        jComboBoxConvenio = new javax.swing.JComboBox<>();
        LabelConvenio = new javax.swing.JLabel();
        LabelTel = new javax.swing.JLabel();
        InputTelefone = new javax.swing.JTextField();
        jComboBoxGenero = new javax.swing.JComboBox<>();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 255));
        setMinimumSize(new java.awt.Dimension(512, 300));
        setResizable(false);

        TitleEditarDespesa.setFont(new java.awt.Font("Inter SemiBold", 0, 18)); // NOI18N
        TitleEditarDespesa.setForeground(new java.awt.Color(0, 51, 153));
        TitleEditarDespesa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TitleEditarDespesa.setText("Editar Consulta");
        TitleEditarDespesa.setMaximumSize(new java.awt.Dimension(178, 64));
        TitleEditarDespesa.setMinimumSize(new java.awt.Dimension(178, 64));

        jPanel1.setBackground(new java.awt.Color(0, 255, 204));

        submitEditarCliente.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        submitEditarCliente.setForeground(new java.awt.Color(0, 51, 153));
        submitEditarCliente.setText("OK");
        submitEditarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitEditarClienteActionPerformed(evt);
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

        jComboBoxEditar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBoxEditar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        LabelNome.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        LabelNome.setText("Paciente:");

        InputNomePaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputNomePacienteActionPerformed(evt);
            }
        });

        LabelNascimento.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        LabelNascimento.setText("Data Consulta");

        InputDataConsulta.setToolTipText("");
        InputDataConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputDataConsultaActionPerformed(evt);
            }
        });

        LabelGenero.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        LabelGenero.setText("Gênero:");

        InputCPF.setToolTipText("");
        InputCPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputCPFActionPerformed(evt);
            }
        });

        LabelCPF.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        LabelCPF.setText("CPF:");

        LabelEndereco.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        LabelEndereco.setText("Endereço:");

        InputEnderecoPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputEnderecoPacienteActionPerformed(evt);
            }
        });

        LabelConvenio.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        LabelConvenio.setText("Convênio:");

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
                .addGap(157, 157, 157)
                .addComponent(submitCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(submitEditarCliente)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jComboBoxEditar, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(LabelConvenio, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxConvenio, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LabelTel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(InputTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(LabelNome)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(InputNomePaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LabelNascimento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(InputDataConsulta))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(LabelCPF)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(InputCPF)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LabelGenero)
                        .addGap(3, 3, 3)
                        .addComponent(jComboBoxGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(LabelEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(InputEnderecoPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(215, 215, 215))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jComboBoxEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(InputNomePaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelNome)
                    .addComponent(LabelNascimento)
                    .addComponent(InputDataConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelCPF)
                    .addComponent(InputCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelGenero)
                    .addComponent(jComboBoxGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LabelEndereco)
                    .addComponent(InputEnderecoPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(InputTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(LabelConvenio)
                        .addComponent(jComboBoxConvenio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LabelTel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(submitCancelar)
                    .addComponent(submitEditarCliente))
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TitleEditarDespesa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(TitleEditarDespesa, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleName("Nova Consulta");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void submitCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitCancelarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_submitCancelarActionPerformed

    private void submitEditarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitEditarClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_submitEditarClienteActionPerformed

    private void InputNomePacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputNomePacienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputNomePacienteActionPerformed

    private void InputCPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputCPFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputCPFActionPerformed

    private void InputEnderecoPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputEnderecoPacienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputEnderecoPacienteActionPerformed

    private void InputDataConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputDataConsultaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputDataConsultaActionPerformed

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
            java.util.logging.Logger.getLogger(TelaEditarConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaEditarConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaEditarConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaEditarConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new TelaEditarConsulta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField InputCPF;
    private javax.swing.JFormattedTextField InputDataConsulta;
    private javax.swing.JTextField InputEnderecoPaciente;
    private javax.swing.JTextField InputNomePaciente;
    private javax.swing.JTextField InputTelefone;
    private javax.swing.JLabel LabelCPF;
    private javax.swing.JLabel LabelConvenio;
    private javax.swing.JLabel LabelEndereco;
    private javax.swing.JLabel LabelGenero;
    private javax.swing.JLabel LabelNascimento;
    private javax.swing.JLabel LabelNome;
    private javax.swing.JLabel LabelTel;
    private javax.swing.JLabel TitleEditarDespesa;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBoxConvenio;
    private javax.swing.JComboBox<String> jComboBoxEditar;
    private javax.swing.JComboBox<String> jComboBoxGenero;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton submitCancelar;
    private javax.swing.JButton submitEditarCliente;
    // End of variables declaration//GEN-END:variables
}
