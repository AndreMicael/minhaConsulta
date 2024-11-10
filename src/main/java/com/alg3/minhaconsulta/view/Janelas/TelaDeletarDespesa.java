/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.alg3.minhaconsulta.view.Janelas;
import java.text.ParseException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import java.awt.Component;
import java.awt.Color;
import java.util.ArrayList;

import com.alg3.minhaconsulta.controller.DespesaController;
 
import com.alg3.minhaconsulta.dao.ExceptionDAO;
import com.alg3.minhaconsulta.model.Despesa;
 

import javax.swing.text.MaskFormatter;


import com.formdev.flatlaf.FlatLightLaf;
import java.awt.HeadlessException;
 
import javax.swing.JOptionPane;

/**
 *
 * @author André Micael Sampaio Pinto <andre at alg3.org>
 */
public class TelaDeletarDespesa extends javax.swing.JFrame {

    /**
     * Creates new form TelaCadastroDespesa
     */
    MaskFormatter mfdata;
    private javax.swing.JFormattedTextField InputData;

   
        public TelaDeletarDespesa(String tipoDespesa) {
    
            try {
                FlatLightLaf.setup();
            } catch (Exception ex) {
                java.util.logging.Logger.getLogger(TelaDeletarDespesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }

            try {
                mfdata = new MaskFormatter("##/##/####");
                mfdata.setPlaceholderCharacter('_');
                mfdata.setValidCharacters("0123456789");
            } catch (ParseException ex) {
                System.err.println("Erro ao criar máscara de data: " + ex.getMessage());
            }
    
            initComponents();
            listarDespesas(tipoDespesa);

            try {
                InputData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(mfdata));
            } catch (Exception ex) {
                System.err.println("Erro ao aplicar máscara ao campo de data: " + ex.getMessage());
            }

    
            submitCancelar.addActionListener((java.awt.event.ActionEvent evt) -> {
                // Código para fechar a tela de cadastro
                dispose();
            });
    
            submitExcluirDespesa.addActionListener((java.awt.event.ActionEvent evt) -> {
            String selectedItem = (String) jComboBoxExcluir.getSelectedItem();

            if (selectedItem != null && !selectedItem.isEmpty() && !selectedItem.equals("Selecione a despesa")) {
                try {
                    int id = Integer.parseInt(selectedItem.split(" - ")[0].trim());
                    DespesaController despesaController = new DespesaController();
                    boolean sucesso = despesaController.deletarDespesa(id);

                    if (sucesso) {
                        System.out.println("Despesa excluída com sucesso");
                        JOptionPane.showMessageDialog(null, "Despesa excluída com sucesso. ID: " + id);
                        jComboBoxExcluir.removeItem(selectedItem);  
                        listarDespesas(tipoDespesa);  

                        // Seleciona um item válido após a exclusão
                        if (jComboBoxExcluir.getItemCount() > 1) {
                            jComboBoxExcluir.setSelectedIndex(1);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Ocorreu um erro ao excluir despesa.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Formato de ID inválido: " + ex.getMessage());
                } catch (ExceptionDAO | HeadlessException ex) {
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro ao excluir despesa. Erro: " + ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Nenhuma despesa selecionada.");
            }
        });

    
            jComboBoxExcluir.addActionListener((java.awt.event.ActionEvent evt) -> {
            String selectedItem = (String) jComboBoxExcluir.getSelectedItem();
            if (selectedItem != null && !selectedItem.isEmpty() && !selectedItem.equals("Selecione a despesa")) {
                try {
                    int id = Integer.parseInt(selectedItem.split(" - ")[0].trim());
                    DespesaController despesaController = new DespesaController();
                    ArrayList<Despesa> despesas = despesaController.listarDespesasId(id);
                    if (despesas != null && !despesas.isEmpty()) {
                        System.out.println("Despesa encontrada: " + despesas);
                    } else {
                        JOptionPane.showMessageDialog(null, "Despesa não encontrada.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Formato de ID inválido: " + ex.getMessage());
                } catch (ExceptionDAO ex) {
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro ao buscar despesa. Erro: " + ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Nenhuma despesa selecionado.");
            }
        });
    }
    
       
    
    public void listarDespesas(String tipoDespesa) {
        try {
            DespesaController despesaController = new DespesaController();
            ArrayList<Despesa> despesas = despesaController.listarDespesasTipo(tipoDespesa);
    
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>() {
                @Override
                public void setSelectedItem(Object anObject) {
                    if (!getElementAt(0).equals(anObject)) {
                        super.setSelectedItem(anObject);
                    }
                }
            };
    
            jComboBoxExcluir.setModel(model);
    
            if (despesas.isEmpty()) {
                // JOptionPane.showMessageDialog(null, "Não há mais despesas.");
            } else {
                model.addElement("Selecione a despesa");  
                for (Despesa despesa : despesas) {
                    String item = despesa.getId() + " - " + despesa.getDescricao() + " - " + "R$ " + despesa.getValor();
                    model.addElement(item);
                }
    
                // Adicionar renderer personalizado para desabilitar o primeiro item
                jComboBoxExcluir.setRenderer(new DefaultListCellRenderer() {
                    @Override
                    public Component getListCellRendererComponent(JList<?> list, Object value, 
                            int index, boolean isSelected, boolean cellHasFocus) {
                        Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                        if (value != null && value.equals("Selecione a despesa")) {
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
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao listar as despesas. Erro: " + ex);
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

        TitleDeletarDespesa = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        submitExcluirDespesa = new javax.swing.JButton();
        submitCancelar = new javax.swing.JButton();
        jComboBoxExcluir = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 255));
        setMinimumSize(new java.awt.Dimension(512, 300));
        setResizable(false);

        TitleDeletarDespesa.setFont(new java.awt.Font("Inter SemiBold", 0, 18)); // NOI18N
        TitleDeletarDespesa.setForeground(new java.awt.Color(0, 51, 153));
        TitleDeletarDespesa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TitleDeletarDespesa.setText("Deletar Despesa");
        TitleDeletarDespesa.setMaximumSize(new java.awt.Dimension(178, 64));
        TitleDeletarDespesa.setMinimumSize(new java.awt.Dimension(178, 64));

        jPanel1.setBackground(new java.awt.Color(255, 153, 153));

        submitExcluirDespesa.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        submitExcluirDespesa.setForeground(new java.awt.Color(0, 51, 153));
        submitExcluirDespesa.setText("OK");
        submitExcluirDespesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitExcluirDespesaActionPerformed(evt);
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
                .addGap(160, 160, 160)
                .addComponent(submitCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(submitExcluirDespesa)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jComboBoxExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(submitExcluirDespesa)
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
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TitleDeletarDespesa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(TitleDeletarDespesa, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleName("Nova Consulta");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void submitCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitCancelarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_submitCancelarActionPerformed

    private void submitExcluirDespesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitExcluirDespesaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_submitExcluirDespesaActionPerformed

    

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
            java.util.logging.Logger.getLogger(TelaDeletarDespesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaDeletarDespesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaDeletarDespesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaDeletarDespesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaDeletarDespesa("someTipoDespesa").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel TitleDeletarDespesa;
    private javax.swing.JComboBox<String> jComboBoxExcluir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton submitCancelar;
    private javax.swing.JButton submitExcluirDespesa;
    // End of variables declaration//GEN-END:variables
}
