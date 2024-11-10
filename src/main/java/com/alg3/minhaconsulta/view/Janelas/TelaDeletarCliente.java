/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.alg3.minhaconsulta.view.Janelas;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import java.awt.Component;
import java.awt.Color;
import java.util.ArrayList;

import com.alg3.minhaconsulta.controller.PacienteController;
import com.alg3.minhaconsulta.dao.ExceptionDAO;
import com.alg3.minhaconsulta.model.Paciente;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.JOptionPane;


public class TelaDeletarCliente extends javax.swing.JFrame {

    public TelaDeletarCliente() {

        try {
            FlatLightLaf.setup();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(TelaDeletarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        initComponents();
        listarPacientes();

        submitCancelar.addActionListener((java.awt.event.ActionEvent evt) -> {
            // Código para fechar a tela de cadastro
            dispose();
        });

        submitExcluirCliente.addActionListener((java.awt.event.ActionEvent evt) -> {
            String selectedItem = (String) jComboBoxExcluir.getSelectedItem();

            if (selectedItem != null && !selectedItem.isEmpty() && !selectedItem.equals("Selecione o paciente")) {
                try {
                    int id = Integer.parseInt(selectedItem.split(" - ")[0].trim());
                    PacienteController pacienteController = new PacienteController();
                    boolean sucesso = pacienteController.deletarPaciente(id);

                    if (sucesso) {
                        System.out.println("Paciente excluído com sucesso");
                        JOptionPane.showMessageDialog(null, "Paciente excluído com sucesso. ID: " + id);
                        jComboBoxExcluir.removeItem(selectedItem); // Remove o paciente da lista imediatamente
                        listarPacientes(); // Atualiza a lista de pacientes

                        // Seleciona um item válido após a exclusão
                        if (jComboBoxExcluir.getItemCount() > 1) {
                            jComboBoxExcluir.setSelectedIndex(1);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Ocorreu um erro ao excluir paciente.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Formato de ID inválido: " + ex.getMessage());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro ao excluir paciente. Erro: " + ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum paciente selecionado.");
            }
        });

        jComboBoxExcluir.addActionListener((java.awt.event.ActionEvent evt) -> {
            String selectedItem = (String) jComboBoxExcluir.getSelectedItem();
            if (selectedItem != null && !selectedItem.isEmpty() && !selectedItem.equals("Selecione o paciente")) {
                try {
                    int id = Integer.parseInt(selectedItem.split(" - ")[0].trim());
                    PacienteController pacienteController = new PacienteController();
                    ArrayList<Paciente> pacientes = pacienteController.listarPacientesId(id);
                    if (pacientes != null && !pacientes.isEmpty()) {
                        System.out.println("Paciente encontrado: " + pacientes);
                    } else {
                        JOptionPane.showMessageDialog(null, "Paciente não encontrado.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Formato de ID inválido: " + ex.getMessage());
                } catch (ExceptionDAO ex) {
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro ao buscar paciente. Erro: " + ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum paciente selecionado.");
            }
        });
    }

public void listarPacientes() {
    try {
        PacienteController pacienteController = new PacienteController();
        ArrayList<Paciente> pacientes = pacienteController.listarPacientes("");

        // Criar um modelo personalizado que permite desabilitar itens
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>() {
            @Override
            public void setSelectedItem(Object anObject) {
                if (!getElementAt(0).equals(anObject)) {
                    super.setSelectedItem(anObject);
                }
            }
        };
        
        jComboBoxExcluir.setModel(model);

        if (pacientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há mais pacientes.");
        } else {
            model.addElement("Selecione o paciente"); // Adiciona item inicial
            for (Paciente paciente : pacientes) {
                String item = paciente.getId() + " - " + paciente.getNome() + " - " + paciente.getCpf();
                model.addElement(item);
            }
            
            // Adicionar renderer personalizado para desabilitar o primeiro item
            jComboBoxExcluir.setRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, 
                        int index, boolean isSelected, boolean cellHasFocus) {
                    Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (value != null && value.equals("Selecione o paciente")) {
                        c.setForeground(Color.GRAY);
                        setEnabled(false);
                    }
                    return c;
                }
            });
            
            // Selecionar o segundo item se existir
            if (model.getSize() > 1) {
                jComboBoxExcluir.setSelectedIndex(1);
            }
        }
    } catch (ExceptionDAO ex) {
        JOptionPane.showMessageDialog(null, "Ocorreu um erro ao listar os pacientes. Erro: " + ex);
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
        submitExcluirCliente = new javax.swing.JButton();
        submitCancelar = new javax.swing.JButton();
        jComboBoxExcluir = new javax.swing.JComboBox<>();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 255));
        setMinimumSize(new java.awt.Dimension(512, 300));
        setResizable(false);

        TitleEditarDespesa.setFont(new java.awt.Font("Inter SemiBold", 0, 18)); // NOI18N
        TitleEditarDespesa.setForeground(new java.awt.Color(0, 51, 153));
        TitleEditarDespesa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TitleEditarDespesa.setText("Deletar Paciente");
        TitleEditarDespesa.setMaximumSize(new java.awt.Dimension(178, 64));
        TitleEditarDespesa.setMinimumSize(new java.awt.Dimension(178, 64));

        jPanel1.setBackground(new java.awt.Color(255, 153, 153));

        submitExcluirCliente.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        submitExcluirCliente.setForeground(new java.awt.Color(0, 51, 153));
        submitExcluirCliente.setText("OK");
        submitExcluirCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitExcluirClienteActionPerformed(evt);
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

        jComboBoxExcluir.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        jComboBoxExcluir.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBoxExcluir, 0, 488, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(161, 161, 161)
                .addComponent(submitCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(submitExcluirCliente)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jComboBoxExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(submitCancelar)
                    .addComponent(submitExcluirCliente))
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TitleEditarDespesa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(TitleEditarDespesa, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void submitExcluirClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitExcluirClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_submitExcluirClienteActionPerformed

 
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
            java.util.logging.Logger.getLogger(TelaDeletarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaDeletarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaDeletarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaDeletarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new TelaDeletarCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel TitleEditarDespesa;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBoxExcluir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton submitCancelar;
    private javax.swing.JButton submitExcluirCliente;
    // End of variables declaration//GEN-END:variables
}
