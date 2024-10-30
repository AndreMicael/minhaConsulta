/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.alg3.minhaconsulta.view;

import com.alg3.minhaconsulta.view.Cadastros.TelaCadastroConsulta;
import com.alg3.minhaconsulta.view.Cadastros.TelaCadastroMedico;
import com.alg3.minhaconsulta.view.Cadastros.TelaCadastroCliente;
import com.alg3.minhaconsulta.view.Exibicoes.TelaExibirEntradas;
import com.alg3.minhaconsulta.view.Exibicoes.TelaExibirSaidas;
import com.formdev.flatlaf.FlatLightLaf;

/**
 *
 * @author André Micael Sampaio Pinto <andre at alg3.org>
 */
public class TelaPrincipal extends javax.swing.JFrame {

    public void Fechar() {
        addActionListeners();

    }

    /**
     * Creates new form TelaPrincipal
     */
    public TelaPrincipal() {

        try {
            FlatLightLaf.setup();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(TelaCadastroMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

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

        NovaConsulta.addActionListener((java.awt.event.ActionEvent evt) -> {
            // Código para abrir a tela de cadastro do médico
            TelaCadastroConsulta telaCadastroConsulta = new TelaCadastroConsulta();
            telaCadastroConsulta.setVisible(true);
            telaCadastroConsulta.toFront();
        });

        EntradasFinanceiro.addActionListener((java.awt.event.ActionEvent evt) -> {
            // Código para abrir a tela de cadastro do médico
            TelaExibirEntradas telaExibirEntradas = new TelaExibirEntradas();
            telaExibirEntradas.setVisible(true);
            telaExibirEntradas.toFront();
        });

        SaidasFinanceiro.addActionListener((java.awt.event.ActionEvent evt) -> {
            // Código para abrir a tela de cadastro do médico
            TelaExibirSaidas telaExibirSaidas = new TelaExibirSaidas();
            telaExibirSaidas.setVisible(true);
            telaExibirSaidas.toFront();
        });

        //Alternar entre os panels
        VerTodasConsultas.addActionListener((java.awt.event.ActionEvent evt) -> {
            TelaConsultasPanel.setVisible(true);
            Tela1Jpanel.setVisible(false);
            TelaPacientesPanel.setVisible(false);
            TelaConveniosPanel.setVisible(false);
            TelaBuscaMedicoPanel.setVisible(false);
            TelaBalancos.setVisible(false);
        });

        VerPacientes.addActionListener((java.awt.event.ActionEvent evt) -> {
            TelaPacientesPanel.setVisible(true);
            TelaConsultasPanel.setVisible(false);
            Tela1Jpanel.setVisible(false);
            TelaConveniosPanel.setVisible(false);
            TelaBuscaMedicoPanel.setVisible(false);
            TelaBalancos.setVisible(false);
        });

        VerConvenios.addActionListener((java.awt.event.ActionEvent evt) -> {
            TelaConveniosPanel.setVisible(true);
            TelaConsultasPanel.setVisible(false);
            Tela1Jpanel.setVisible(false);
            TelaPacientesPanel.setVisible(false);
            TelaBuscaMedicoPanel.setVisible(false);
            TelaBalancos.setVisible(false);
        });

        VerEspecialidades.addActionListener((java.awt.event.ActionEvent evt) -> {
            TelaEspecialidadesPanel.setVisible(true);
            TelaConveniosPanel.setVisible(false);
            TelaConsultasPanel.setVisible(false);
            Tela1Jpanel.setVisible(false);
            TelaPacientesPanel.setVisible(false);
            TelaBuscaMedicoPanel.setVisible(false);
            TelaBalancos.setVisible(false);
        });

        VerMedicos.addActionListener((java.awt.event.ActionEvent evt) -> {
            TelaBuscaMedicoPanel.setVisible(true);
            TelaEspecialidadesPanel.setVisible(false);
            TelaConveniosPanel.setVisible(false);
            TelaConsultasPanel.setVisible(false);
            Tela1Jpanel.setVisible(false);
            TelaPacientesPanel.setVisible(false);
            TelaBalancos.setVisible(false);

        });

        BalancoFinanceiro.addActionListener((java.awt.event.ActionEvent evt) -> {
            TelaBalancos.setVisible(true);
            TelaBuscaMedicoPanel.setVisible(false);
            TelaEspecialidadesPanel.setVisible(false);
            TelaConveniosPanel.setVisible(false);
            TelaConsultasPanel.setVisible(false);
            Tela1Jpanel.setVisible(false);
            TelaPacientesPanel.setVisible(false);

        });

    }

    public void voltarButton() {
        Tela1Jpanel.setVisible(true);
        TelaBalancos.setVisible(false);
        TelaBuscaMedicoPanel.setVisible(false);
        TelaEspecialidadesPanel.setVisible(false);
        TelaConveniosPanel.setVisible(false);
        TelaConsultasPanel.setVisible(false);
        TelaPacientesPanel.setVisible(false);

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
        jPanelImage = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        ProximasConsultasPanel = new javax.swing.JPanel();
        TelaConsultasPanel = new javax.swing.JPanel();
        VoltarBtn = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        TelaPacientesPanel = new javax.swing.JPanel();
        VoltarBtn1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        TelaConveniosPanel = new javax.swing.JPanel();
        VoltarBtn2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jTextField3 = new javax.swing.JTextField();
        TelaEspecialidadesPanel = new javax.swing.JPanel();
        VoltarBtn3 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jTextField4 = new javax.swing.JTextField();
        TelaBuscaMedicoPanel = new javax.swing.JPanel();
        VoltarBtn4 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jTextField5 = new javax.swing.JTextField();
        TelaBalancos = new javax.swing.JPanel();
        VoltarBtn5 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jTextField6 = new javax.swing.JTextField();
        MenuPrincipal = new javax.swing.JMenuBar();
        MenuCadastros = new javax.swing.JMenu();
        VerPacientes = new javax.swing.JMenuItem();
        VerMedicos = new javax.swing.JMenuItem();
        VerConvenios = new javax.swing.JMenuItem();
        VerEspecialidades = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        CadastroPaciente = new javax.swing.JMenuItem();
        NovoMedico = new javax.swing.JMenuItem();
        MenuConsultas = new javax.swing.JMenu();
        NovaConsulta = new javax.swing.JMenuItem();
        VerTodasConsultas = new javax.swing.JMenuItem();
        MenuBuscarMedico = new javax.swing.JMenu();
        EntradasFinanceiro = new javax.swing.JMenuItem();
        SaidasFinanceiro = new javax.swing.JMenuItem();
        BalancoFinanceiro = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Minha Consulta");
        setMinimumSize(new java.awt.Dimension(712, 423));
        setResizable(false);

        BackgroundPanel.setBackground(new java.awt.Color(153, 153, 153));
        BackgroundPanel.setForeground(new java.awt.Color(255, 153, 153));
        BackgroundPanel.setToolTipText("");
        BackgroundPanel.setMaximumSize(new java.awt.Dimension(712, 423));
        BackgroundPanel.setMinimumSize(new java.awt.Dimension(712, 423));
        BackgroundPanel.setPreferredSize(new java.awt.Dimension(600, 400));
        BackgroundPanel.setRequestFocusEnabled(false);
        BackgroundPanel.setLayout(new java.awt.CardLayout());

        Tela1Jpanel.setBackground(new java.awt.Color(153, 204, 255));
        Tela1Jpanel.setMaximumSize(new java.awt.Dimension(712, 423));
        Tela1Jpanel.setMinimumSize(new java.awt.Dimension(712, 423));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/ImgPrincipal.png"))); // NOI18N
        jLabel2.setText("jLabel2");

        javax.swing.GroupLayout jPanelImageLayout = new javax.swing.GroupLayout(jPanelImage);
        jPanelImage.setLayout(jPanelImageLayout);
        jPanelImageLayout.setHorizontalGroup(
            jPanelImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 678, Short.MAX_VALUE)
        );
        jPanelImageLayout.setVerticalGroup(
            jPanelImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, Short.MAX_VALUE)
        );

        ProximasConsultasPanel.setBackground(new java.awt.Color(204, 204, 255));
        ProximasConsultasPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Próximas Consultas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Inter SemiBold", 0, 14), new java.awt.Color(0, 102, 153))); // NOI18N
        ProximasConsultasPanel.setToolTipText("Próximas Consultas");

        javax.swing.GroupLayout ProximasConsultasPanelLayout = new javax.swing.GroupLayout(ProximasConsultasPanel);
        ProximasConsultasPanel.setLayout(ProximasConsultasPanelLayout);
        ProximasConsultasPanelLayout.setHorizontalGroup(
            ProximasConsultasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 215, Short.MAX_VALUE)
        );
        ProximasConsultasPanelLayout.setVerticalGroup(
            ProximasConsultasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 241, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout Tela1JpanelLayout = new javax.swing.GroupLayout(Tela1Jpanel);
        Tela1Jpanel.setLayout(Tela1JpanelLayout);
        Tela1JpanelLayout.setHorizontalGroup(
            Tela1JpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Tela1JpanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(Tela1JpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ProximasConsultasPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        Tela1JpanelLayout.setVerticalGroup(
            Tela1JpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Tela1JpanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanelImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(ProximasConsultasPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
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

        jTextField1.setText("Todas as Consultas");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(267, 267, 267)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(294, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout TelaConsultasPanelLayout = new javax.swing.GroupLayout(TelaConsultasPanel);
        TelaConsultasPanel.setLayout(TelaConsultasPanelLayout);
        TelaConsultasPanelLayout.setHorizontalGroup(
            TelaConsultasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TelaConsultasPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TelaConsultasPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(VoltarBtn)
                .addGap(50, 50, 50))
        );
        TelaConsultasPanelLayout.setVerticalGroup(
            TelaConsultasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TelaConsultasPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 280, Short.MAX_VALUE)
                .addComponent(VoltarBtn)
                .addGap(58, 58, 58))
        );

        BackgroundPanel.add(TelaConsultasPanel, "card3");

        TelaPacientesPanel.setBackground(new java.awt.Color(51, 255, 204));
        TelaPacientesPanel.setMaximumSize(new java.awt.Dimension(712, 423));
        TelaPacientesPanel.setMinimumSize(new java.awt.Dimension(712, 423));

        VoltarBtn1.setText("Voltar");
        VoltarBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VoltarBtn1ActionPerformed(evt);
            }
        });

        jTextField2.setText("Todas os pacientes");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(267, 267, 267)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(294, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout TelaPacientesPanelLayout = new javax.swing.GroupLayout(TelaPacientesPanel);
        TelaPacientesPanel.setLayout(TelaPacientesPanelLayout);
        TelaPacientesPanelLayout.setHorizontalGroup(
            TelaPacientesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TelaPacientesPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TelaPacientesPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(VoltarBtn1)
                .addGap(50, 50, 50))
        );
        TelaPacientesPanelLayout.setVerticalGroup(
            TelaPacientesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TelaPacientesPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 280, Short.MAX_VALUE)
                .addComponent(VoltarBtn1)
                .addGap(58, 58, 58))
        );

        BackgroundPanel.add(TelaPacientesPanel, "card3");

        TelaConveniosPanel.setBackground(new java.awt.Color(255, 255, 204));
        TelaConveniosPanel.setMaximumSize(new java.awt.Dimension(712, 423));
        TelaConveniosPanel.setMinimumSize(new java.awt.Dimension(712, 423));

        VoltarBtn2.setText("Voltar");
        VoltarBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VoltarBtn2ActionPerformed(evt);
            }
        });

        jTextField3.setText("Todas os Convenios");
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(267, 267, 267)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(294, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout TelaConveniosPanelLayout = new javax.swing.GroupLayout(TelaConveniosPanel);
        TelaConveniosPanel.setLayout(TelaConveniosPanelLayout);
        TelaConveniosPanelLayout.setHorizontalGroup(
            TelaConveniosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TelaConveniosPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TelaConveniosPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(VoltarBtn2)
                .addGap(50, 50, 50))
        );
        TelaConveniosPanelLayout.setVerticalGroup(
            TelaConveniosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TelaConveniosPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 280, Short.MAX_VALUE)
                .addComponent(VoltarBtn2)
                .addGap(58, 58, 58))
        );

        BackgroundPanel.add(TelaConveniosPanel, "card3");

        TelaEspecialidadesPanel.setBackground(new java.awt.Color(255, 51, 51));
        TelaEspecialidadesPanel.setMaximumSize(new java.awt.Dimension(712, 423));
        TelaEspecialidadesPanel.setMinimumSize(new java.awt.Dimension(712, 423));

        VoltarBtn3.setText("Voltar");
        VoltarBtn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VoltarBtn3ActionPerformed(evt);
            }
        });

        jTextField4.setText("Todas as Especialidades");
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(267, 267, 267)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(294, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout TelaEspecialidadesPanelLayout = new javax.swing.GroupLayout(TelaEspecialidadesPanel);
        TelaEspecialidadesPanel.setLayout(TelaEspecialidadesPanelLayout);
        TelaEspecialidadesPanelLayout.setHorizontalGroup(
            TelaEspecialidadesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TelaEspecialidadesPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TelaEspecialidadesPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(VoltarBtn3)
                .addGap(50, 50, 50))
        );
        TelaEspecialidadesPanelLayout.setVerticalGroup(
            TelaEspecialidadesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TelaEspecialidadesPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 280, Short.MAX_VALUE)
                .addComponent(VoltarBtn3)
                .addGap(58, 58, 58))
        );

        BackgroundPanel.add(TelaEspecialidadesPanel, "card3");

        TelaBuscaMedicoPanel.setBackground(new java.awt.Color(255, 51, 51));
        TelaBuscaMedicoPanel.setMaximumSize(new java.awt.Dimension(712, 423));
        TelaBuscaMedicoPanel.setMinimumSize(new java.awt.Dimension(712, 423));

        VoltarBtn4.setText("Voltar");
        VoltarBtn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VoltarBtn4ActionPerformed(evt);
            }
        });

        jTextField5.setText("Ver Medicos");
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(267, 267, 267)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(294, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout TelaBuscaMedicoPanelLayout = new javax.swing.GroupLayout(TelaBuscaMedicoPanel);
        TelaBuscaMedicoPanel.setLayout(TelaBuscaMedicoPanelLayout);
        TelaBuscaMedicoPanelLayout.setHorizontalGroup(
            TelaBuscaMedicoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TelaBuscaMedicoPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TelaBuscaMedicoPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(VoltarBtn4)
                .addGap(50, 50, 50))
        );
        TelaBuscaMedicoPanelLayout.setVerticalGroup(
            TelaBuscaMedicoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TelaBuscaMedicoPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 280, Short.MAX_VALUE)
                .addComponent(VoltarBtn4)
                .addGap(58, 58, 58))
        );

        BackgroundPanel.add(TelaBuscaMedicoPanel, "card3");

        TelaBalancos.setBackground(new java.awt.Color(0, 255, 204));
        TelaBalancos.setMaximumSize(new java.awt.Dimension(712, 423));
        TelaBalancos.setMinimumSize(new java.awt.Dimension(712, 423));

        VoltarBtn5.setText("Voltar");
        VoltarBtn5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                VoltarBtn5ItemStateChanged(evt);
            }
        });
        VoltarBtn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VoltarBtn5ActionPerformed(evt);
            }
        });

        jTextField6.setText("Balanços");
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(267, 267, 267)
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(294, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout TelaBalancosLayout = new javax.swing.GroupLayout(TelaBalancos);
        TelaBalancos.setLayout(TelaBalancosLayout);
        TelaBalancosLayout.setHorizontalGroup(
            TelaBalancosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TelaBalancosLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TelaBalancosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(VoltarBtn5)
                .addGap(50, 50, 50))
        );
        TelaBalancosLayout.setVerticalGroup(
            TelaBalancosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TelaBalancosLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 280, Short.MAX_VALUE)
                .addComponent(VoltarBtn5)
                .addGap(58, 58, 58))
        );

        BackgroundPanel.add(TelaBalancos, "card3");

        MenuCadastros.setBackground(new java.awt.Color(204, 204, 255));
        MenuCadastros.setForeground(new java.awt.Color(51, 51, 51));
        MenuCadastros.setText("Cadastros");
        MenuCadastros.setFocusable(false);
        MenuCadastros.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N

        VerPacientes.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        VerPacientes.setText("Ver Pacientes");
        MenuCadastros.add(VerPacientes);

        VerMedicos.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        VerMedicos.setText("Ver Médicos");
        VerMedicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VerMedicosActionPerformed(evt);
            }
        });
        MenuCadastros.add(VerMedicos);

        VerConvenios.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        VerConvenios.setText("Ver Convênios");
        MenuCadastros.add(VerConvenios);

        VerEspecialidades.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        VerEspecialidades.setText("Ver Especialidades");
        MenuCadastros.add(VerEspecialidades);
        MenuCadastros.add(jSeparator1);

        CadastroPaciente.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        CadastroPaciente.setText("Novo Paciente");
        MenuCadastros.add(CadastroPaciente);

        NovoMedico.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        NovoMedico.setText("Novo Médico");
        MenuCadastros.add(NovoMedico);

        MenuPrincipal.add(MenuCadastros);

        MenuConsultas.setBackground(new java.awt.Color(204, 204, 255));
        MenuConsultas.setForeground(new java.awt.Color(51, 51, 51));
        MenuConsultas.setText("Consultas");
        MenuConsultas.setFocusable(false);
        MenuConsultas.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N

        NovaConsulta.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        NovaConsulta.setText("Nova Consulta");
        MenuConsultas.add(NovaConsulta);

        VerTodasConsultas.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        VerTodasConsultas.setText("Todas as Consultas");
        VerTodasConsultas.setToolTipText("");
        MenuConsultas.add(VerTodasConsultas);

        MenuPrincipal.add(MenuConsultas);

        MenuBuscarMedico.setBackground(new java.awt.Color(204, 204, 255));
        MenuBuscarMedico.setForeground(new java.awt.Color(51, 51, 51));
        MenuBuscarMedico.setText("Financeiro");
        MenuBuscarMedico.setFocusable(false);
        MenuBuscarMedico.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        MenuBuscarMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuBuscarMedicoActionPerformed(evt);
            }
        });

        EntradasFinanceiro.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        EntradasFinanceiro.setText("Entradas");
        MenuBuscarMedico.add(EntradasFinanceiro);

        SaidasFinanceiro.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        SaidasFinanceiro.setText("Saidas");
        MenuBuscarMedico.add(SaidasFinanceiro);

        BalancoFinanceiro.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        BalancoFinanceiro.setText("Balanço");
        MenuBuscarMedico.add(BalancoFinanceiro);

        MenuPrincipal.add(MenuBuscarMedico);

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

    private void VoltarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VoltarBtnActionPerformed
        Tela1Jpanel.setVisible(true);
        TelaConsultasPanel.setVisible(false);
        TelaPacientesPanel.setVisible(false);
    }//GEN-LAST:event_VoltarBtnActionPerformed

    private void VoltarBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VoltarBtn1ActionPerformed
        voltarButton();
    }//GEN-LAST:event_VoltarBtn1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        voltarButton();
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void VoltarBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VoltarBtn2ActionPerformed
        voltarButton();
    }//GEN-LAST:event_VoltarBtn2ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void VoltarBtn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VoltarBtn3ActionPerformed
        voltarButton();
    }//GEN-LAST:event_VoltarBtn3ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void VoltarBtn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VoltarBtn4ActionPerformed
        voltarButton();
    }//GEN-LAST:event_VoltarBtn4ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void MenuBuscarMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuBuscarMedicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MenuBuscarMedicoActionPerformed

    private void VerMedicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VerMedicosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_VerMedicosActionPerformed

    private void VoltarBtn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VoltarBtn5ActionPerformed
        voltarButton();
    }//GEN-LAST:event_VoltarBtn5ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void VoltarBtn5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_VoltarBtn5ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_VoltarBtn5ItemStateChanged

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
    private javax.swing.JMenuItem BalancoFinanceiro;
    private javax.swing.JMenuItem CadastroPaciente;
    private javax.swing.JMenuItem EntradasFinanceiro;
    private javax.swing.JMenu MenuBuscarMedico;
    private javax.swing.JMenu MenuCadastros;
    private javax.swing.JMenu MenuConsultas;
    private javax.swing.JMenuBar MenuPrincipal;
    private javax.swing.JMenuItem NovaConsulta;
    private javax.swing.JMenuItem NovoMedico;
    private javax.swing.JPanel ProximasConsultasPanel;
    private javax.swing.JMenuItem SaidasFinanceiro;
    private javax.swing.JPanel Tela1Jpanel;
    private javax.swing.JPanel TelaBalancos;
    private javax.swing.JPanel TelaBuscaMedicoPanel;
    private javax.swing.JPanel TelaConsultasPanel;
    private javax.swing.JPanel TelaConveniosPanel;
    private javax.swing.JPanel TelaEspecialidadesPanel;
    private javax.swing.JPanel TelaPacientesPanel;
    private javax.swing.JMenuItem VerConvenios;
    private javax.swing.JMenuItem VerEspecialidades;
    private javax.swing.JMenuItem VerMedicos;
    private javax.swing.JMenuItem VerPacientes;
    private javax.swing.JMenuItem VerTodasConsultas;
    private javax.swing.JButton VoltarBtn;
    private javax.swing.JButton VoltarBtn1;
    private javax.swing.JButton VoltarBtn2;
    private javax.swing.JButton VoltarBtn3;
    private javax.swing.JButton VoltarBtn4;
    private javax.swing.JButton VoltarBtn5;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanelImage;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables
}
