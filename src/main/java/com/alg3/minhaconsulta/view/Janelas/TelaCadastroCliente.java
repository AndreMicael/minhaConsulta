/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.alg3.minhaconsulta.view.Janelas;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
 

import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

import com.alg3.minhaconsulta.controller.PacienteController;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.HeadlessException;
 

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
  MaskFormatter mftelefone;

  public List < String > convenios = new ArrayList < > ();
  public List < String > generos = new ArrayList < > ();

  public TelaCadastroCliente() {

    try {
      FlatLightLaf.setup();
    } catch (Exception ex) {
      java.util.logging.Logger.getLogger(TelaCadastroMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }

    try {
      mfdata = new MaskFormatter("##/##/####");
      mfdata.setPlaceholderCharacter('_');
      mfdata.setValidCharacters("0123456789");

      mfcpf = new MaskFormatter("###.###.###-##");
      mfcpf.setPlaceholderCharacter('_');
      mfcpf.setValidCharacters("0123456789");

      mftelefone = new MaskFormatter("(##) #####-####");
      mftelefone.setPlaceholderCharacter('_');
      mftelefone.setValidCharacters("0123456789");

    } catch (ParseException ex) {
      System.err.println("Erro ao criar máscara de data: " + ex.getMessage());
    }

    initComponents();

    try {
      InputNascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(mfdata));
      InputCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(mfcpf));
      InputTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(mftelefone));
    } catch (Exception ex) {
      System.err.println("Erro ao aplicar máscara ao campo de data: " + ex.getMessage());
    }

    InputConvenio.removeAll();
    convenios.add("Unimed");
    convenios.add("Sulamerica");
    convenios.add("Bradesco");

    for (String convenio: convenios) {
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
      String nome = InputNomePaciente.getText().trim();
      String endereco = InputEnderecoPaciente.getText().trim();
      String nascimento = InputNascimento.getText().trim();
      String cpf = InputCPF.getText().trim();
      String convenio = (String) InputConvenio.getSelectedItem();
      String genero = (String) InputSexo.getSelectedItem();
      String telefone = InputTelefone.getText().trim(); // Adicionei o campo telefone

      // Verificação para campos obrigatórios
      if (nome.isEmpty() || endereco.isEmpty() || nascimento.contains("_") || cpf.contains("_") || telefone.contains("_")) {
        JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios corretamente.");
        return;
      }

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
      } catch (HeadlessException ex) {
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
        InputTelefone = new javax.swing.JFormattedTextField(mftelefone);
        LabelGenero = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 255));
        setMaximumSize(new java.awt.Dimension(512, 300));
        setMinimumSize(new java.awt.Dimension(512, 300));
        setPreferredSize(new java.awt.Dimension(512, 300));
        setResizable(false);

        TitleCadastrarPaciente.setFont(new java.awt.Font("Inter SemiBold", 0, 18)); // NOI18N
        TitleCadastrarPaciente.setForeground(new java.awt.Color(0, 51, 153));
        TitleCadastrarPaciente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TitleCadastrarPaciente.setText("Cadastrar Paciente");
        TitleCadastrarPaciente.setMaximumSize(new java.awt.Dimension(178, 64));
        TitleCadastrarPaciente.setMinimumSize(new java.awt.Dimension(178, 64));

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        LabelNome.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        LabelNome.setForeground(new java.awt.Color(0, 0, 153));
        LabelNome.setText("Nome:");

        LabelEndereco.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        LabelEndereco.setForeground(new java.awt.Color(0, 0, 153));
        LabelEndereco.setText("Endereço:");

        InputNomePaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputNomePacienteActionPerformed(evt);
            }
        });

        LabelNascimento.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        LabelNascimento.setForeground(new java.awt.Color(0, 0, 153));
        LabelNascimento.setText("Data Nasc.:");

        InputNascimento.setToolTipText("");
        InputNascimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputNascimentoActionPerformed(evt);
            }
        });

        LabelCPF.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        LabelCPF.setForeground(new java.awt.Color(0, 0, 153));
        LabelCPF.setText("CPF:");

        InputCPF.setToolTipText("");
        InputCPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputCPFActionPerformed(evt);
            }
        });

        LabelConvenio.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        LabelConvenio.setForeground(new java.awt.Color(0, 0, 153));
        LabelConvenio.setText("Convênio:");

        InputEnderecoPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputEnderecoPacienteActionPerformed(evt);
            }
        });

        submitCadastrarPaciente.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        submitCadastrarPaciente.setForeground(new java.awt.Color(0, 51, 153));
        submitCadastrarPaciente.setText("OK");
        submitCadastrarPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitCadastrarPacienteActionPerformed(evt);
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
        LabelTel.setForeground(new java.awt.Color(0, 0, 153));
        LabelTel.setText("Celular:");

        LabelGenero.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        LabelGenero.setForeground(new java.awt.Color(0, 0, 153));
        LabelGenero.setText("Gênero:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(LabelNome)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(InputNomePaciente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LabelNascimento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(InputNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(LabelConvenio, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(InputConvenio, 0, 179, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LabelTel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(InputTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(LabelCPF)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(InputCPF)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LabelGenero)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(InputSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(LabelEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(InputEnderecoPaciente)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(submitCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(submitCadastrarPaciente)
                .addGap(160, 160, 160))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(LabelNascimento)
                        .addComponent(InputNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(InputNomePaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LabelNome))
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(InputTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(LabelConvenio)
                        .addComponent(InputConvenio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LabelTel)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(submitCadastrarPaciente)
                    .addComponent(submitCancelar))
                .addContainerGap(23, Short.MAX_VALUE))
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
                .addContainerGap()
                .addComponent(TitleCadastrarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleDescription("Cadastrar Paciente");

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
      for (javax.swing.UIManager.LookAndFeelInfo info: javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("FlatLightLaf".equals(info.getName())) {
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
    private javax.swing.JFormattedTextField InputTelefone;
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