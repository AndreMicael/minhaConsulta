/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.alg3.minhaconsulta.view;




/**
 *
 * @author André Micael Sampaio Pinto <andre at alg3.org>
 */
public class TelaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form TelaPrincipal
     */
    public TelaPrincipal() {
        
     
        
        
    
       
        initComponents();
        
         addActionListeners(); // Chama o método que adiciona os ActionListeners
    }

    private void addActionListeners() {
        // Adiciona ActionListener ao item "Novo Paciente"
        
      
       CadastroPaciente.addActionListener((java.awt.event.ActionEvent evt) -> {
           // Código para abrir a tela de cadastro do paciente
           TelaCadastroCliente telaCadastro = new TelaCadastroCliente();
            telaCadastro.setVisible(true);
            telaCadastro.toFront();
        });

        NovoMedico.addActionListener((java.awt.event.ActionEvent evt) -> {
            // Código para abrir a tela de cadastro do médico
            TelaCadastroMedico telaCadastro = new TelaCadastroMedico();
            telaCadastro.setVisible(true);
            telaCadastro.toFront();
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

        BackgroundPanel = new javax.swing.JPanel();
        Tela1Jpanel = new javax.swing.JPanel();
        VerConsultasButton = new javax.swing.JToggleButton();
        TelaConsultasPanel = new javax.swing.JPanel();
        VoltarBtn = new javax.swing.JButton();
        MenuPrincipal = new javax.swing.JMenuBar();
        MenuCadastros = new javax.swing.JMenu();
        VerPacientes = new javax.swing.JMenuItem();
        VerConvenios = new javax.swing.JMenuItem();
        VerEspecialidades = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        CadastroPaciente = new javax.swing.JMenuItem();
        NovoMedico = new javax.swing.JMenuItem();
        MenuConsultas = new javax.swing.JMenu();
        NovaConsulta = new javax.swing.JMenuItem();
        ConsultasHoje = new javax.swing.JMenuItem();
        VerTodasConsultas = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        BuscarConsultas = new javax.swing.JMenu();
        PorDataConsulta = new javax.swing.JMenuItem();
        PorStatusConsulta = new javax.swing.JMenuItem();
        MenuBuscarMedico = new javax.swing.JMenu();
        PorEspecialidade = new javax.swing.JMenuItem();
        PorNome = new javax.swing.JMenuItem();
        PorCRM = new javax.swing.JMenuItem();
        FecharMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Minha Consulta");
        setMaximumSize(new java.awt.Dimension(712, 423));
        setMinimumSize(new java.awt.Dimension(712, 423));
        setPreferredSize(new java.awt.Dimension(712, 423));
        setResizable(false);

        BackgroundPanel.setBackground(new java.awt.Color(153, 153, 153));
        BackgroundPanel.setForeground(new java.awt.Color(255, 153, 153));
        BackgroundPanel.setToolTipText("");
        BackgroundPanel.setMaximumSize(new java.awt.Dimension(712, 423));
        BackgroundPanel.setMinimumSize(new java.awt.Dimension(712, 423));
        BackgroundPanel.setPreferredSize(new java.awt.Dimension(600, 400));
        BackgroundPanel.setRequestFocusEnabled(false);
        BackgroundPanel.setLayout(new java.awt.CardLayout());

        Tela1Jpanel.setBackground(new java.awt.Color(153, 255, 153));
        Tela1Jpanel.setMaximumSize(new java.awt.Dimension(712, 423));
        Tela1Jpanel.setMinimumSize(new java.awt.Dimension(712, 423));

        VerConsultasButton.setText("Consultas");
        VerConsultasButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VerConsultasButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Tela1JpanelLayout = new javax.swing.GroupLayout(Tela1Jpanel);
        Tela1Jpanel.setLayout(Tela1JpanelLayout);
        Tela1JpanelLayout.setHorizontalGroup(
            Tela1JpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Tela1JpanelLayout.createSequentialGroup()
                .addContainerGap(302, Short.MAX_VALUE)
                .addComponent(VerConsultasButton, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(299, 299, 299))
        );
        Tela1JpanelLayout.setVerticalGroup(
            Tela1JpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Tela1JpanelLayout.createSequentialGroup()
                .addContainerGap(202, Short.MAX_VALUE)
                .addComponent(VerConsultasButton)
                .addGap(198, 198, 198))
        );

        BackgroundPanel.add(Tela1Jpanel, "card2");

        TelaConsultasPanel.setBackground(new java.awt.Color(255, 153, 153));
        TelaConsultasPanel.setMaximumSize(new java.awt.Dimension(712, 423));
        TelaConsultasPanel.setMinimumSize(new java.awt.Dimension(712, 423));

        VoltarBtn.setText("Voltar");
        VoltarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VoltarBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout TelaConsultasPanelLayout = new javax.swing.GroupLayout(TelaConsultasPanel);
        TelaConsultasPanel.setLayout(TelaConsultasPanelLayout);
        TelaConsultasPanelLayout.setHorizontalGroup(
            TelaConsultasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TelaConsultasPanelLayout.createSequentialGroup()
                .addGap(305, 305, 305)
                .addComponent(VoltarBtn)
                .addContainerGap(335, Short.MAX_VALUE))
        );
        TelaConsultasPanelLayout.setVerticalGroup(
            TelaConsultasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TelaConsultasPanelLayout.createSequentialGroup()
                .addContainerGap(213, Short.MAX_VALUE)
                .addComponent(VoltarBtn)
                .addGap(187, 187, 187))
        );

        BackgroundPanel.add(TelaConsultasPanel, "card3");

        MenuCadastros.setText("Cadastros");

        VerPacientes.setText("Ver Pacientes");
        MenuCadastros.add(VerPacientes);

        VerConvenios.setText("Ver Convênios");
        MenuCadastros.add(VerConvenios);

        VerEspecialidades.setText("Ver Especialidades");
        MenuCadastros.add(VerEspecialidades);
        MenuCadastros.add(jSeparator1);

        CadastroPaciente.setText("Novo Paciente");
        MenuCadastros.add(CadastroPaciente);

        NovoMedico.setText("Novo Médico");
        MenuCadastros.add(NovoMedico);

        MenuPrincipal.add(MenuCadastros);

        MenuConsultas.setText("Consultas");

        NovaConsulta.setText("Nova Consulta");
        MenuConsultas.add(NovaConsulta);

        ConsultasHoje.setText("Consultas de Hoje");
        MenuConsultas.add(ConsultasHoje);

        VerTodasConsultas.setText("Todas as Consultas");
        VerTodasConsultas.setToolTipText("");
        MenuConsultas.add(VerTodasConsultas);
        MenuConsultas.add(jSeparator2);

        BuscarConsultas.setText("Buscar Consultas");

        PorDataConsulta.setText("Por Data");
        BuscarConsultas.add(PorDataConsulta);

        PorStatusConsulta.setText("Por Status");
        BuscarConsultas.add(PorStatusConsulta);

        MenuConsultas.add(BuscarConsultas);

        MenuPrincipal.add(MenuConsultas);

        MenuBuscarMedico.setText("Buscar Medico");

        PorEspecialidade.setText("Por Especialidade");
        MenuBuscarMedico.add(PorEspecialidade);

        PorNome.setText("Por Nome");
        MenuBuscarMedico.add(PorNome);

        PorCRM.setText("Por CRM");
        MenuBuscarMedico.add(PorCRM);

        MenuPrincipal.add(MenuBuscarMedico);

        FecharMenu.setText("Fechar");
        MenuPrincipal.add(FecharMenu);

        setJMenuBar(MenuPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BackgroundPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 712, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BackgroundPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void VerConsultasButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VerConsultasButtonActionPerformed
        Tela1Jpanel.setVisible(false);
        TelaConsultasPanel.setVisible(true);
    }//GEN-LAST:event_VerConsultasButtonActionPerformed

    private void VoltarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VoltarBtnActionPerformed
         Tela1Jpanel.setVisible(true);
        TelaConsultasPanel.setVisible(false);
    }//GEN-LAST:event_VoltarBtnActionPerformed

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
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BackgroundPanel;
    private javax.swing.JMenu BuscarConsultas;
    private javax.swing.JMenuItem CadastroPaciente;
    private javax.swing.JMenuItem ConsultasHoje;
    private javax.swing.JMenu FecharMenu;
    private javax.swing.JMenu MenuBuscarMedico;
    private javax.swing.JMenu MenuCadastros;
    private javax.swing.JMenu MenuConsultas;
    private javax.swing.JMenuBar MenuPrincipal;
    private javax.swing.JMenuItem NovaConsulta;
    private javax.swing.JMenuItem NovoMedico;
    private javax.swing.JMenuItem PorCRM;
    private javax.swing.JMenuItem PorDataConsulta;
    private javax.swing.JMenuItem PorEspecialidade;
    private javax.swing.JMenuItem PorNome;
    private javax.swing.JMenuItem PorStatusConsulta;
    private javax.swing.JPanel Tela1Jpanel;
    private javax.swing.JPanel TelaConsultasPanel;
    private javax.swing.JToggleButton VerConsultasButton;
    private javax.swing.JMenuItem VerConvenios;
    private javax.swing.JMenuItem VerEspecialidades;
    private javax.swing.JMenuItem VerPacientes;
    private javax.swing.JMenuItem VerTodasConsultas;
    private javax.swing.JButton VoltarBtn;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    // End of variables declaration//GEN-END:variables
}
