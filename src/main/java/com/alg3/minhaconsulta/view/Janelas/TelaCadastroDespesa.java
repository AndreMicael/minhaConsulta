/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.alg3.minhaconsulta.view.Janelas;


import java.text.ParseException;
import com.alg3.minhaconsulta.controller.DespesaController;
import com.alg3.minhaconsulta.dao.ExceptionDAO;


import javax.swing.text.MaskFormatter;


import com.formdev.flatlaf.FlatLightLaf;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;

/**
 *
 * @author André Micael Sampaio Pinto <andre at alg3.org>
 */
public class TelaCadastroDespesa extends javax.swing.JFrame {

    /**
     * Creates new form TelaCadastroDespesa
     */
    MaskFormatter mfdata;
 //
   // public List<String> convenios = new ArrayList<>();
  //public List<String> generos = new ArrayList<>();
//
    public TelaCadastroDespesa() {

        try {
            FlatLightLaf.setup();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(TelaCadastroDespesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        try {
            mfdata = new MaskFormatter("##/##/####");
            mfdata.setPlaceholderCharacter('_');
            mfdata.setValidCharacters("0123456789");
        } catch (ParseException ex) {
            System.err.println("Erro ao criar máscara de data: " + ex.getMessage());
        }

        initComponents();

         try {
               InputData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(mfdata));
            } catch (Exception ex) {
                System.err.println("Erro ao aplicar máscara ao campo de data: " + ex.getMessage());
            }


       
        submitCancelar.addActionListener((java.awt.event.ActionEvent evt) -> {
            // Código para fechar a tela de cadastro
            dispose();

        });

        
        
        jComboBoxTipo.addItem("Selecione o tipo da despesa");
        jComboBoxTipo.addItem("Entrada");
        jComboBoxTipo.addItem("Saída");
         
        submitCadastrarDespesa.addActionListener((java.awt.event.ActionEvent evt) -> {
            String descricao = InputDescricao.getText().trim();
            String tipo = jComboBoxTipo.getSelectedItem() != null ? jComboBoxTipo.getSelectedItem().toString() : "";
            if (tipo.equals("Selecione o tipo da despesa")) {
            JOptionPane.showMessageDialog(null, "Selecione o tipo da despesa.");
            return;
            }
            String data = InputData.getText().trim();
            String valorStr = InputValorConsulta.getText().replace(",", ".").trim();
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
                sucesso = despesaController.cadastrarDespesa(descricao, tipo, valor, data);
            } catch (ExceptionDAO ex) {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao cadastrar despesa. Erro: " + ex);
                sucesso = false;
            }
            if (sucesso) {
                System.out.println("Despesa cadastrada com sucesso");
                JOptionPane.showMessageDialog(null, "Despesa cadastrada com sucesso");
                System.out.println("Dados enviados para o controller: " + descricao + ", " + tipo);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao cadastrar despesa.");
            }
            } catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao cadastrar despesa. Erro: " + ex);
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
        submitCadastrarDespesa = new javax.swing.JButton();
        submitCancelar = new javax.swing.JButton();
        LabelDescricao = new javax.swing.JLabel();
        InputDescricao = new javax.swing.JTextField();
        LabelTipo = new javax.swing.JLabel();
        Data = new javax.swing.JLabel();
        InputData = new javax.swing.JFormattedTextField();
        LabelValor = new javax.swing.JLabel();
        InputValorConsulta = new javax.swing.JTextField();
        jComboBoxTipo = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 255));
        setMinimumSize(new java.awt.Dimension(512, 300));
        setResizable(false);

        TitleCadastrarPaciente.setFont(new java.awt.Font("Inter SemiBold", 0, 18)); // NOI18N
        TitleCadastrarPaciente.setForeground(new java.awt.Color(0, 51, 153));
        TitleCadastrarPaciente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TitleCadastrarPaciente.setText("Nova despesa");
        TitleCadastrarPaciente.setMaximumSize(new java.awt.Dimension(178, 64));
        TitleCadastrarPaciente.setMinimumSize(new java.awt.Dimension(178, 64));

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        submitCadastrarDespesa.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        submitCadastrarDespesa.setForeground(new java.awt.Color(0, 51, 153));
        submitCadastrarDespesa.setText("OK");
        submitCadastrarDespesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitCadastrarDespesaActionPerformed(evt);
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

        LabelDescricao.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        LabelDescricao.setText("Descrição:");

        InputDescricao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputDescricaoActionPerformed(evt);
            }
        });

        LabelTipo.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        LabelTipo.setText("Tipo:");

        Data.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        Data.setText("Data:");

        InputData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputDataActionPerformed(evt);
            }
        });

        LabelValor.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
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
                        .addComponent(Data)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(InputData)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LabelValor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(InputValorConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(LabelDescricao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(InputDescricao, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(LabelTipo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(142, 142, 142)
                .addComponent(submitCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(submitCadastrarDespesa)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(InputDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelDescricao)
                    .addComponent(LabelTipo)
                    .addComponent(jComboBoxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(InputData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Data)
                    .addComponent(InputValorConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelValor))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(submitCadastrarDespesa)
                    .addComponent(submitCancelar))
                .addContainerGap(16, Short.MAX_VALUE))
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
                .addGap(14, 14, 14)
                .addComponent(TitleCadastrarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleName("Nova Consulta");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void submitCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitCancelarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_submitCancelarActionPerformed

    private void submitCadastrarDespesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitCadastrarDespesaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_submitCadastrarDespesaActionPerformed

    private void InputDescricaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputDescricaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputDescricaoActionPerformed

    private void InputDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputDataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputDataActionPerformed

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
            java.util.logging.Logger.getLogger(TelaCadastroDespesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroDespesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroDespesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroDespesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCadastroDespesa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Data;
    private javax.swing.JFormattedTextField InputData;
    private javax.swing.JTextField InputDescricao;
    private javax.swing.JTextField InputValorConsulta;
    private javax.swing.JLabel LabelDescricao;
    private javax.swing.JLabel LabelTipo;
    private javax.swing.JLabel LabelValor;
    private javax.swing.JLabel TitleCadastrarPaciente;
    private javax.swing.JComboBox<String> jComboBoxTipo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton submitCadastrarDespesa;
    private javax.swing.JButton submitCancelar;
    // End of variables declaration//GEN-END:variables
}
