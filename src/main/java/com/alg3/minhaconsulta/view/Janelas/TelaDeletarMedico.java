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

import com.alg3.minhaconsulta.controller.MedicoController;
 
import com.alg3.minhaconsulta.dao.ExceptionDAO;
import com.alg3.minhaconsulta.model.Medico;
 
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;

/**
 *
 * @author André Micael Sampaio Pinto <andre at alg3.org>
 */
public class TelaDeletarMedico extends javax.swing.JFrame {

    
    

    public TelaDeletarMedico() {

        try {
            FlatLightLaf.setup();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(TelaDeletarMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

   

    initComponents();
    listarMedicos();
 

        submitCancelar.addActionListener((java.awt.event.ActionEvent evt) -> {
            dispose();
        });

      

        submitExcluirMedico.addActionListener((java.awt.event.ActionEvent evt) -> {
            String selectedItem = (String) jComboBoxExcluir.getSelectedItem();

            if (selectedItem != null && !selectedItem.isEmpty() && !selectedItem.equals("Selecione o médico")) {
                try {
                    int id = Integer.parseInt(selectedItem.split(" - ")[0].trim());
                    MedicoController medicoController = new MedicoController();
                    boolean sucesso = medicoController.deletarMedico(id);

                    if (sucesso) {
                        System.out.println("Médico excluído com sucesso");
                        JOptionPane.showMessageDialog(null, "Médico excluído com sucesso. ID: " + id);
                        jComboBoxExcluir.removeItem(selectedItem); // Remove o médico da lista imediatamente
                        listarMedicos(); // Atualiza a lista de médicos

                        // Seleciona um item válido após a exclusão
                        if (jComboBoxExcluir.getItemCount() > 1) {
                            jComboBoxExcluir.setSelectedIndex(1);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Ocorreu um erro ao excluir médico.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Formato de ID inválido: " + ex.getMessage());
                } catch (ExceptionDAO | HeadlessException ex) {
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro ao excluir médico. Erro: " + ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum médico selecionado.");
            }
        });


        jComboBoxExcluir.addActionListener((java.awt.event.ActionEvent evt) -> {
            String selectedItem = (String) jComboBoxExcluir.getSelectedItem();
            if (selectedItem != null && !selectedItem.isEmpty() && !selectedItem.equals("Selecione o médico")) {
                try {
                    int id = Integer.parseInt(selectedItem.split(" - ")[0].trim());
                    MedicoController medicoController = new MedicoController();
                    ArrayList<Medico> medicos = medicoController.listarMedicosId(id);
                    if (medicos != null && !medicos.isEmpty()) {
                        System.out.println("Medico encontrado: " + medicos);
                    } else {
                        JOptionPane.showMessageDialog(null, "Médico não encontrado.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Formato de ID inválido: " + ex.getMessage());
                } catch (ExceptionDAO ex) {
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro ao buscar médico. Erro: " + ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum médico selecionado.");
            }
        });
    }
    
public void listarMedicos() {
    try {
        MedicoController medicoController = new MedicoController();
        ArrayList<Medico> medicos = medicoController.listarMedicos("");

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

        if (medicos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há mais médicos.");
        } else {
            model.addElement("Selecione o médico"); // Adiciona item inicial
            for (Medico medico : medicos) {
                String item = medico.getId() + " - " + medico.getNome() + " - " + medico.getCrm();
                model.addElement(item);
            }
            
            // Adicionar renderer personalizado para desabilitar o primeiro item
            jComboBoxExcluir.setRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, 
                        int index, boolean isSelected, boolean cellHasFocus) {
                    Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (value != null && value.equals("Selecione o médico")) {
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

        jComboBox1 = new javax.swing.JComboBox<>();
        TitleDeletarMedico = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        submitExcluirMedico = new javax.swing.JButton();
        submitCancelar = new javax.swing.JButton();
        jComboBoxExcluir = new javax.swing.JComboBox<>();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 255));
        setMinimumSize(new java.awt.Dimension(512, 300));
        setResizable(false);

        TitleDeletarMedico.setFont(new java.awt.Font("Inter SemiBold", 0, 18)); // NOI18N
        TitleDeletarMedico.setForeground(new java.awt.Color(0, 51, 153));
        TitleDeletarMedico.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TitleDeletarMedico.setText("Deletar Médico");
        TitleDeletarMedico.setMaximumSize(new java.awt.Dimension(178, 64));
        TitleDeletarMedico.setMinimumSize(new java.awt.Dimension(178, 64));

        jPanel1.setBackground(new java.awt.Color(255, 153, 153));

        submitExcluirMedico.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        submitExcluirMedico.setForeground(new java.awt.Color(0, 51, 153));
        submitExcluirMedico.setText("OK");
        submitExcluirMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               
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
                .addGap(157, 157, 157)
                .addComponent(submitCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(submitExcluirMedico)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jComboBoxExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(submitCancelar)
                    .addComponent(submitExcluirMedico))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TitleDeletarMedico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(TitleDeletarMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    // TODO add your handling code here:

    private void submitEditarMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitEditarMedicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_submitEditarMedicoActionPerformed

 
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
            java.util.logging.Logger.getLogger(TelaDeletarMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaDeletarMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaDeletarMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaDeletarMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new TelaDeletarMedico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel TitleDeletarMedico;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBoxExcluir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton submitCancelar;
    private javax.swing.JButton submitExcluirMedico;
    // End of variables declaration//GEN-END:variables
}
