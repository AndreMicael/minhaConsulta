/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.alg3.minhaconsulta.view.Janelas;


import java.text.ParseException;
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
public class TelaEditarDespesa extends javax.swing.JFrame {

    /**
     * Creates new form TelaCadastroDespesa
     */
    MaskFormatter mfdata;
    private javax.swing.JFormattedTextField InputData;

   
        public TelaEditarDespesa(String tipoDespesa) {
    
            try {
                FlatLightLaf.setup();
            } catch (Exception ex) {
                java.util.logging.Logger.getLogger(TelaEditarDespesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    
            submitEditarDespesa.addActionListener((java.awt.event.ActionEvent evt) -> {
                String selectedItem = (String) jComboBoxEditar.getSelectedItem();
                if (selectedItem != null && !selectedItem.isEmpty()) {
                    try {
                        // Assuming the ID is the first part of the string before the first space
                        int id = Integer.parseInt(selectedItem.split(" - ")[0].trim());
                        String descricao = InputEditarDescricao.getText().trim();
                        String tipo = InputEditarTipoDespesa.getText().trim();
                        String data = InputEditarData.getText().trim();
                        String valorStr = InputEditarDespesa.getText().replace(",", ".").trim(); // Substitui vírgula por ponto
                        boolean sucesso;
            
                        // Verificação para campos obrigatórios
                        if (descricao.isEmpty() || data.contains("_") || valorStr.isEmpty()) {
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
                            DespesaController despesaController = new DespesaController();
                            try {
                                sucesso = despesaController.editarDespesa(id, descricao, tipo, valor, data);
                            } catch (ExceptionDAO ex) {
                                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao editar despesa. Erro: " + ex);
                                sucesso = false;
                            }
                            if (sucesso) {
                                System.out.println("Despesa editada com sucesso");
                                JOptionPane.showMessageDialog(null, "Despesa editada com sucesso. ID: " + id);
                                System.out.println("Dados enviados para o controller: " + descricao + ", " + tipo);
                                dispose();
                            } else {
                                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao editar despesa.");
                            }
                        } catch (HeadlessException ex) {
                            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao editar despesa. Erro: " + ex);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Formato de ID inválido: " + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Nenhuma despesa selecionada.");
                }
            });
    
            jComboBoxEditar.addActionListener((java.awt.event.ActionEvent evt) -> {
                String selectedItem = (String) jComboBoxEditar.getSelectedItem();
                if (selectedItem != null && !selectedItem.equals("Selecione a despesa")) {
                    try {
                        int id = Integer.parseInt(selectedItem.split(" - ")[0].trim());
                        DespesaController despesaController = new DespesaController();
                        ArrayList<Despesa> despesas = despesaController.listarDespesasId(id);
                        Despesa despesa = despesas.isEmpty() ? null : despesas.get(0);
                        preencherCampos(despesa);
                    } catch (NumberFormatException | ExceptionDAO ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao buscar despesa: " + ex.getMessage());
                    }
                } else {
                    preencherCampos(null); // Limpa os campos quando "Selecione a despesa" está selecionado
                }
            });
        }
    
        public void preencherCampos(Despesa despesa) {
            if (despesa != null) {
                InputEditarDescricao.setText(despesa.getDescricao());
                InputEditarTipoDespesa.setText(despesa.getTipo());
                InputEditarData.setText(despesa.getDataRegistro());
                InputEditarDespesa.setText(String.valueOf(despesa.getValor()));
            } else {
                // Limpar campos
                InputEditarDescricao.setText("");
                InputEditarTipoDespesa.setText("");
                InputEditarData.setText("");
                InputEditarDespesa.setText("");
            }
        }
    
        public void listarDespesas(String tipoDespesa) {
            try {
                DespesaController despesaController = new DespesaController();
                ArrayList<Despesa> despesas = despesaController.listarDespesasTipo(tipoDespesa);
                jComboBoxEditar.removeAllItems();
                jComboBoxEditar.addItem("Selecione a despesa"); // Adiciona item inicial
                for (Despesa despesa : despesas) {
                    String item = despesa.getId() + " - " + despesa.getDescricao() + " - R$ " + String.format("%.2f", despesa.getValor());
                    jComboBoxEditar.addItem(item);
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

        TitleEditarDespesa = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        submitEditarDespesa = new javax.swing.JButton();
        submitCancelar = new javax.swing.JButton();
        LabelEditarDescricao = new javax.swing.JLabel();
        InputEditarDescricao = new javax.swing.JTextField();
        InputEditarTipoDespesa =  new javax.swing.JFormattedTextField(mfdata);
        LabelTipo = new javax.swing.JLabel();
        EditarData = new javax.swing.JLabel();
        InputEditarData = new javax.swing.JTextField();
        LabelValor = new javax.swing.JLabel();
        InputEditarDespesa = new javax.swing.JTextField();
        jComboBoxEditar = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 255));
        setMinimumSize(new java.awt.Dimension(512, 300));
        setResizable(false);

        TitleEditarDespesa.setFont(new java.awt.Font("Inter SemiBold", 0, 18)); // NOI18N
        TitleEditarDespesa.setForeground(new java.awt.Color(0, 51, 153));
        TitleEditarDespesa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TitleEditarDespesa.setText("Editar Despesa");
        TitleEditarDespesa.setMaximumSize(new java.awt.Dimension(178, 64));
        TitleEditarDespesa.setMinimumSize(new java.awt.Dimension(178, 64));

        jPanel1.setBackground(new java.awt.Color(0, 255, 204));

        submitEditarDespesa.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        submitEditarDespesa.setForeground(new java.awt.Color(0, 51, 153));
        submitEditarDespesa.setText("OK");
        submitEditarDespesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitEditarDespesaActionPerformed(evt);
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

        LabelEditarDescricao.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        LabelEditarDescricao.setText("Descrição:");

        InputEditarDescricao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputEditarDescricaoActionPerformed(evt);
            }
        });

        InputEditarTipoDespesa.setToolTipText("");
        InputEditarTipoDespesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputEditarTipoDespesaActionPerformed(evt);
            }
        });

        LabelTipo.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        LabelTipo.setText("Tipo:");

        EditarData.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        EditarData.setText("Data:");

        InputEditarData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputEditarDataActionPerformed(evt);
            }
        });

        LabelValor.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        LabelValor.setText("Valor:");

        InputEditarDespesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputEditarDespesaActionPerformed(evt);
            }
        });

        jComboBoxEditar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxEditar, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(EditarData)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(InputEditarData))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(LabelEditarDescricao)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(InputEditarDescricao, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(LabelTipo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(InputEditarTipoDespesa, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(LabelValor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(InputEditarDespesa, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 4, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(submitCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(submitEditarDespesa)
                        .addGap(158, 158, 158)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jComboBoxEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(InputEditarDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelEditarDescricao)
                    .addComponent(LabelTipo)
                    .addComponent(InputEditarTipoDespesa, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(InputEditarData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EditarData)
                    .addComponent(InputEditarDespesa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelValor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(submitEditarDespesa)
                    .addComponent(submitCancelar))
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TitleEditarDespesa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(TitleEditarDespesa, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleName("Nova Consulta");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void submitCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitCancelarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_submitCancelarActionPerformed

    private void submitEditarDespesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitEditarDespesaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_submitEditarDespesaActionPerformed

    private void InputEditarDescricaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputEditarDescricaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputEditarDescricaoActionPerformed

    private void InputEditarTipoDespesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputEditarTipoDespesaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputEditarTipoDespesaActionPerformed

    private void InputEditarDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputEditarDataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputEditarDataActionPerformed

    private void InputEditarDespesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputEditarDespesaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputEditarDespesaActionPerformed

    

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
            java.util.logging.Logger.getLogger(TelaEditarDespesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaEditarDespesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaEditarDespesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaEditarDespesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                new TelaEditarDespesa("someTipoDespesa").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel EditarData;
    private javax.swing.JTextField InputEditarData;
    private javax.swing.JTextField InputEditarDescricao;
    private javax.swing.JTextField InputEditarDespesa;
    private javax.swing.JFormattedTextField InputEditarTipoDespesa;
    private javax.swing.JLabel LabelEditarDescricao;
    private javax.swing.JLabel LabelTipo;
    private javax.swing.JLabel LabelValor;
    private javax.swing.JLabel TitleEditarDespesa;
    private javax.swing.JComboBox<String> jComboBoxEditar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton submitCancelar;
    private javax.swing.JButton submitEditarDespesa;
    // End of variables declaration//GEN-END:variables
}
