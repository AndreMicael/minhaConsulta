/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.alg3.minhaconsulta.view.Janelas;

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
public class TelaCadastroMedico extends javax.swing.JFrame {

    /**
     * Creates new form TelaCadastroCliente
     */
    MaskFormatter mfdata;
    MaskFormatter mftelefone;
    MaskFormatter mfcrm;

    public List<String> especialidade = new ArrayList<>();
    public List<String> generos = new ArrayList<>();

    public TelaCadastroMedico() {

        try {
            FlatLightLaf.setup();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(TelaCadastroMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        try {
            mfdata = new MaskFormatter("##/##/####");
            mfdata.setPlaceholderCharacter('_');
            mfdata.setValidCharacters("0123456789");

       

            mfcrm = new MaskFormatter("UU ######"); 
            mfcrm.setPlaceholderCharacter('_');
     
        
          
            
            mftelefone = new MaskFormatter("(##) #####-####");
            mftelefone.setPlaceholderCharacter('_');
            mftelefone.setValidCharacters("0123456789");

        } catch (ParseException ex) {
            System.err.println("Erro ao criar máscara de data: " + ex.getMessage());
        }
       

        initComponents();

         
        try {
            InputNascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(mfdata));
            InputTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(mftelefone));
            InputCrm.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(mfcrm));
        } catch (Exception ex) {
            System.err.println("Erro ao aplicar máscara ao campo de data: " + ex.getMessage());
        }

        especialidade.add("Cardiologista");
        especialidade.add("Pediatria");
        especialidade.add("Urologista");
        especialidade.add("Ortopedia");
        especialidade.add("Oftalmologia");
        especialidade.add("Dermatologia");
        especialidade.add("Ginecologia");
        especialidade.add("Neurologia");
        especialidade.add("Endocrinologia");
        especialidade.add("Psiquiatria");
        especialidade.add("Oncologia");
        especialidade.add("Gastroenterologia");
        especialidade.add("Nefrologia");
        especialidade.add("Reumatologia");
        especialidade.add("Hematologia");
        especialidade.add("Otorrinolaringologia");
        especialidade.add("Pneumologia");
        especialidade.add("Cirurgia Plástica");
        especialidade.add("Anestesiologia");
        especialidade.add("Infectologia");
        especialidade.add("Medicina do Esporte");
        especialidade.add("Medicina Intensiva");
        especialidade.add("Medicina de Família e Comunidade");

        

        generos.add("Masculino");
        generos.add("Feminino");
        generos.add("Outro");

        for (String genero : generos) {
            InputGenero.addItem(genero);
        }

        for (String especialidade : especialidade) {
            InputEspecialidade.addItem(especialidade);
        }

        submitCancelar.addActionListener((java.awt.event.ActionEvent evt) -> {
     
            dispose();

        });

        submitCadastrarMedico.addActionListener((java.awt.event.ActionEvent evt) -> {
            String nome = InputNomeMedico.getText().trim();
            String endereco = InputEnderecoMedico.getText().trim();
            String nascimento = InputNascimento.getText().trim();
            String crm = InputCrm.getText().trim();
            String especialidade = (String) InputEspecialidade.getSelectedItem();
            String genero = (String) InputGenero.getSelectedItem();
            String telefone = InputTelefone.getText().trim(); // Adicionei o campo telefone
        
            // Verificação para campos obrigatórios
            if (nome.isEmpty() || endereco.isEmpty() || nascimento.contains("_") || crm.contains("_") || telefone.contains("_")) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios corretamente.");
                return;
            }
        
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
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TitleCadastrarPaciente = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        LabelNome = new javax.swing.JLabel();
        LabelEndereco = new javax.swing.JLabel();
        InputNomeMedico = new javax.swing.JTextField();
        LabelNascimento = new javax.swing.JLabel();
        InputNascimento =  new javax.swing.JFormattedTextField(mfdata);
        LabelCPF = new javax.swing.JLabel();
        InputEnderecoMedico = new javax.swing.JTextField();
        LabelEspecialidade = new javax.swing.JLabel();
        InputEspecialidade = new javax.swing.JComboBox<>();
        InputCrm = new javax.swing.JFormattedTextField(mfcrm);
        InputGenero = new javax.swing.JComboBox<>();
        LabelSexo = new javax.swing.JLabel();
        submitCadastrarMedico = new javax.swing.JButton();
        submitCancelar = new javax.swing.JButton();
        LabelTel = new javax.swing.JLabel();
        InputTelefone = new javax.swing.JFormattedTextField(mftelefone);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 255));
        setBounds(new java.awt.Rectangle(10, 10, 10, 10));
        setMaximumSize(new java.awt.Dimension(512, 269));
        setMinimumSize(new java.awt.Dimension(512, 269));
        setResizable(false);

        TitleCadastrarPaciente.setFont(new java.awt.Font("Inter SemiBold", 1, 18)); // NOI18N
        TitleCadastrarPaciente.setForeground(new java.awt.Color(0, 51, 153));
        TitleCadastrarPaciente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TitleCadastrarPaciente.setText("Cadastrar Médico");

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(446, 233));

        LabelNome.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        LabelNome.setText("Nome:");

        LabelEndereco.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        LabelEndereco.setText("Endereço:");

        InputNomeMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputNomeMedicoActionPerformed(evt);
            }
        });

        LabelNascimento.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        LabelNascimento.setText("Data Nasc.:");

        InputNascimento.setToolTipText("");
        InputNascimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputNascimentoActionPerformed(evt);
            }
        });

        LabelCPF.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        LabelCPF.setText("CRM:");

        InputEnderecoMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputEnderecoMedicoActionPerformed(evt);
            }
        });

        LabelEspecialidade.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        LabelEspecialidade.setText("Especialidade: ");

        LabelSexo.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        LabelSexo.setText("Gênero:");

        submitCadastrarMedico.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        submitCadastrarMedico.setForeground(new java.awt.Color(0, 51, 153));
        submitCadastrarMedico.setText("OK");
        submitCadastrarMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitCadastrarMedicoActionPerformed(evt);
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

        LabelTel.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        LabelTel.setText("Celular:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(LabelEndereco)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(InputEnderecoMedico))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(LabelCPF)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(InputCrm, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(LabelSexo)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(InputGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(LabelTel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(InputTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(59, 59, 59))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(LabelEspecialidade)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(InputEspecialidade, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(253, 253, 253))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(LabelNome)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(InputNomeMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LabelNascimento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(InputNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(157, 157, 157)
                .addComponent(submitCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(submitCadastrarMedico)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(InputNomeMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelNome)
                    .addComponent(LabelNascimento)
                    .addComponent(InputNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelCPF)
                    .addComponent(InputCrm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelSexo)
                    .addComponent(InputGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(InputEnderecoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelEndereco))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelEspecialidade)
                    .addComponent(InputEspecialidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(InputTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelTel))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(submitCadastrarMedico)
                    .addComponent(submitCancelar))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TitleCadastrarPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(TitleCadastrarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        getAccessibleContext().setAccessibleName("Cadastrar Médico");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void InputEnderecoMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputEnderecoMedicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputEnderecoMedicoActionPerformed

    private void InputNascimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputNascimentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputNascimentoActionPerformed

    private void InputNomeMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputNomeMedicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputNomeMedicoActionPerformed

    private void submitCadastrarMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitCadastrarMedicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_submitCadastrarMedicoActionPerformed

    private void submitCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitCancelarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_submitCancelarActionPerformed

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
            java.util.logging.Logger.getLogger(TelaCadastroMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCadastroMedico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField InputCrm;
    private javax.swing.JTextField InputEnderecoMedico;
    private javax.swing.JComboBox<String> InputEspecialidade;
    private javax.swing.JComboBox<String> InputGenero;
    private javax.swing.JFormattedTextField InputNascimento;
    private javax.swing.JTextField InputNomeMedico;
    private javax.swing.JFormattedTextField InputTelefone;
    private javax.swing.JLabel LabelCPF;
    private javax.swing.JLabel LabelEndereco;
    private javax.swing.JLabel LabelEspecialidade;
    private javax.swing.JLabel LabelNascimento;
    private javax.swing.JLabel LabelNome;
    private javax.swing.JLabel LabelSexo;
    private javax.swing.JLabel LabelTel;
    private javax.swing.JLabel TitleCadastrarPaciente;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton submitCadastrarMedico;
    private javax.swing.JButton submitCancelar;
    // End of variables declaration//GEN-END:variables
}
