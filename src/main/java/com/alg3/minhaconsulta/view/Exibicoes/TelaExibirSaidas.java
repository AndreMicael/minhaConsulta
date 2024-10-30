/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.alg3.minhaconsulta.view.Exibicoes;

import com.formdev.flatlaf.FlatLightLaf;

import com.alg3.minhaconsulta.controller.MedicoController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

import javax.swing.text.MaskFormatter;

/**
 *
 * @author André Micael Sampaio Pinto <andre at alg3.org>
 */
public class TelaExibirSaidas extends javax.swing.JFrame {

    /**
     * Creates new form TelaCadastroCliente
     */
    MaskFormatter mfdata;

    public List<String> especialidade = new ArrayList<>();
    public List<String> generos = new ArrayList<>();

    public TelaExibirSaidas() {
 
        try {
            FlatLightLaf.setup();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(TelaExibirSaidas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
 
       
        initComponents();
       
     
        

     

        /*
        submitCadastrarMedico.addActionListener((java.awt.event.ActionEvent evt) -> {
            String nome = InputNomeMedico.getText();
            String endereco = InputEnderecoMedico.getText();
            String nascimento = InputNascimento.getText();
            String crm = InputCrm.getText();
            String especialidade = (String) InputEspecialidade.getSelectedItem();
            String genero = (String) InputGenero.getSelectedItem();
            String telefone = InputTelefone.getText(); // Adicionei o campo telefone
            boolean sucesso;

            String generoFinal = String.valueOf(genero.charAt(0));

            try {
                MedicoController medicoController = new MedicoController();
                try {
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date nascimentoDate = dateFormat.parse(nascimento);
                    String nascimentoStr = dateFormat.format(nascimentoDate);
                    sucesso = medicoController.cadastrarMedico(nome, nascimentoStr, endereco, telefone, crm, especialidade, generoFinal);

                    if (sucesso) {
                        System.out.println("Médico cadastrado com sucesso");
                        JOptionPane.showMessageDialog(null, "Médico cadastrado com sucesso");
                        System.out.println("Dados enviados para o controller: " + nome + ", " + endereco);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Ocorreu um erro ao cadastrar o Médico");
                    }
                } catch (ParseException e) {
                    JOptionPane.showMessageDialog(null, "Preencha todos os dados corretamente.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao cadastrar o paciente. Erro: " + ex);
            }
        });
 */
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TitleSaidas = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 255));
        setBounds(new java.awt.Rectangle(10, 10, 10, 10));
        setMinimumSize(new java.awt.Dimension(512, 269));
        setResizable(false);

        TitleSaidas.setFont(new java.awt.Font("Inter SemiBold", 1, 12)); // NOI18N
        TitleSaidas.setForeground(new java.awt.Color(0, 51, 153));
        TitleSaidas.setText("Saidas");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(211, 211, 211)
                .addComponent(TitleSaidas)
                .addContainerGap(248, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TitleSaidas, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(237, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleName("Todas as Entradas");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(TelaExibirSaidas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaExibirSaidas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaExibirSaidas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaExibirSaidas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new TelaExibirSaidas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel TitleSaidas;
    // End of variables declaration//GEN-END:variables
}