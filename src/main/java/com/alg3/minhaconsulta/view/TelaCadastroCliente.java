/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.alg3.minhaconsulta.view;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

import com.alg3.minhaconsulta.controller.PacienteController;

/**
 *
 * @author André Micael Sampaio Pinto <andre at alg3.org>
 */
public class TelaCadastroCliente extends javax.swing.JFrame {

    /**
     * Creates new form TelaCadastroCliente
     */
    
    MaskFormatter mfdata;
    MaskFormatter mfcpf;
    
    public List<String> convenios = new ArrayList<>();
    public List<String> generos = new ArrayList<>();
    public TelaCadastroCliente() {   
        
          
         try {
            mfdata = new MaskFormatter("##/##/####");
        } catch (ParseException ex) {
            System.out.println("Ocorreu um erro na criação da máscara");
        }
         
           try {
            mfcpf = new MaskFormatter("###.###.###-##");
        } catch (ParseException ex) {
            System.out.println("Ocorreu um erro na criação da máscara");
        }
           
        
     
        initComponents();

        InputConvenio.removeAll();
        convenios.add("Unimed");
        convenios.add("Sulamerica");
        convenios.add("Bradesco");
        
        for (String convenio : convenios) {
            InputConvenio.addItem(convenio);
        }
        
        InputSexo.removeAll();
        generos.add("Masculino");
        generos.add("Feminino");
        generos.add("Outro");
        
        for (String genero: generos) {
            InputSexo.addItem(genero);
        }
        
        
          submitCancelar.addActionListener((java.awt.event.ActionEvent evt) -> {
            // Código para fechar a tela de cadastro
            dispose();
              
        });
          
        submitCadastrarPaciente.addActionListener((java.awt.event.ActionEvent evt) -> {
            String nome = InputNomePaciente.getText();
            String endereco = InputEnderecoPaciente.getText();
            String nascimento = InputNascimento.getText();
            String cpf = InputCPF.getText();
            String convenio = (String) InputConvenio.getSelectedItem();
            String genero = (String) InputSexo.getSelectedItem();
            String telefone = InputTelefone.getText(); // Adicionei o campo telefone
            boolean sucesso;
        
            String generoFinal = String.valueOf(genero.charAt(0));
        
            try {
                PacienteController pacienteController = new PacienteController();
                try {
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date nascimentoDate = dateFormat.parse(nascimento);
                    String nascimentoStr = dateFormat.format(nascimentoDate);
                    sucesso = pacienteController.cadastrarPaciente(nome, nascimentoStr, endereco, telefone, cpf, convenio, generoFinal);
        
                    if (sucesso) {
                        System.out.println("Paciente cadastrado com sucesso");
                        JOptionPane.showMessageDialog(null, "Paciente cadastrado com sucesso");
                        System.out.println("Dados enviados para o controller: " + nome + ", " + endereco);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Ocorreu um erro ao cadastrar o paciente");
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
    // @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TitleCadastrarPaciente = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        LabelNome = new javax.swing.JLabel();
        LabelEndereco = new javax.swing.JLabel();
        InputNomePaciente = new javax.swing.JTextField();
        LabelNascimento = new javax.swing.JLabel();
        InputNascimento =  new javax.swing.JFormattedTextField(mfdata);
        LabelCPF = new javax.swing.JLabel();
        InputCPF =  new javax.swing.JFormattedTextField(mfcpf);
        LabelConvenio = new javax.swing.JLabel();
        InputEnderecoPaciente = new javax.swing.JTextField();
        InputConvenio = new javax.swing.JComboBox<>();
        submitCadastrarPaciente = new javax.swing.JButton();
        submitCancelar = new javax.swing.JButton();
        InputSexo = new javax.swing.JComboBox<>();
        LabelTel = new javax.swing.JLabel();
        InputTelefone = new javax.swing.JTextField();
        LabelGenero = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastrar Cliente");
        setBackground(new java.awt.Color(204, 255, 255));
        setResizable(false);

        TitleCadastrarPaciente.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        TitleCadastrarPaciente.setText("Cadastrar Paciente");

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));

        LabelNome.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LabelNome.setText("Nome:");

        LabelEndereco.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LabelEndereco.setText("Endereço:");

        InputNomePaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputNomePacienteActionPerformed(evt);
            }
        });

        LabelNascimento.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LabelNascimento.setText("Data Nasc.:");

        InputNascimento.setToolTipText("");
        InputNascimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputNascimentoActionPerformed(evt);
            }
        });

        LabelCPF.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LabelCPF.setText("CPF:");

        InputCPF.setToolTipText("");
        InputCPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputCPFActionPerformed(evt);
            }
        });

        LabelConvenio.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LabelConvenio.setText("Convênio:");

        InputEnderecoPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputEnderecoPacienteActionPerformed(evt);
            }
        });

        submitCadastrarPaciente.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        submitCadastrarPaciente.setText("OK");
        submitCadastrarPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitCadastrarPacienteActionPerformed(evt);
            }
        });

        submitCancelar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        submitCancelar.setText("Cancelar");
        submitCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitCancelarActionPerformed(evt);
            }
        });

        LabelTel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LabelTel.setText("Tel.:");

        LabelGenero.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LabelGenero.setText("Gênero:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(LabelCPF)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(InputCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(LabelGenero)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(InputSexo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(LabelNome, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(InputNomePaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(LabelNascimento)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(InputNascimento))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(LabelEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(InputEnderecoPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(LabelConvenio, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(InputConvenio, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(LabelTel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(InputTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addComponent(submitCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(submitCadastrarPaciente)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(LabelNascimento)
                        .addComponent(InputNascimento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(LabelNome)
                            .addComponent(InputNomePaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LabelCPF)
                            .addComponent(InputCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(InputSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LabelGenero))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LabelEndereco)
                    .addComponent(InputEnderecoPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelConvenio)
                    .addComponent(InputConvenio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(InputTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelTel))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(submitCadastrarPaciente)
                    .addComponent(submitCancelar))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(TitleCadastrarPaciente))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(TitleCadastrarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

   
    private void InputNomePacienteActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void InputNascimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputNascimentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputNascimentoActionPerformed

    private void InputCPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputCPFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputCPFActionPerformed

    private void InputEnderecoPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputEnderecoPacienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputEnderecoPacienteActionPerformed

    private void submitCadastrarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitCadastrarPacienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_submitCadastrarPacienteActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCadastroCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField InputCPF;
    private javax.swing.JComboBox<String> InputConvenio;
    private javax.swing.JTextField InputEnderecoPaciente;
    private javax.swing.JFormattedTextField InputNascimento;
    private javax.swing.JTextField InputNomePaciente;
    private javax.swing.JComboBox<String> InputSexo;
    private javax.swing.JTextField InputTelefone;
    private javax.swing.JLabel LabelCPF;
    private javax.swing.JLabel LabelConvenio;
    private javax.swing.JLabel LabelEndereco;
    private javax.swing.JLabel LabelGenero;
    private javax.swing.JLabel LabelNascimento;
    private javax.swing.JLabel LabelNome;
    private javax.swing.JLabel LabelTel;
    private javax.swing.JLabel TitleCadastrarPaciente;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton submitCadastrarPaciente;
    private javax.swing.JButton submitCancelar;
    // End of variables declaration//GEN-END:variables
}
