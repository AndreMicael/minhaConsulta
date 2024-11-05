/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.alg3.minhaconsulta.view;

import com.alg3.minhaconsulta.controller.ConsultaController;
import com.alg3.minhaconsulta.controller.MedicoController;
import com.alg3.minhaconsulta.controller.PacienteController;
import com.alg3.minhaconsulta.model.Consulta;
import com.alg3.minhaconsulta.model.Medico;
import com.alg3.minhaconsulta.model.Paciente;
import com.alg3.minhaconsulta.view.Cadastros.TelaCadastroConsulta;
import com.alg3.minhaconsulta.view.Cadastros.TelaCadastroMedico;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

import com.alg3.minhaconsulta.view.Cadastros.TelaCadastroCliente;
import com.alg3.minhaconsulta.view.Cadastros.TelaCadastroDespesa;
import com.alg3.minhaconsulta.view.Exibicoes.TelaExibirEntradas;
import com.alg3.minhaconsulta.view.Exibicoes.TelaExibirSaidas;
import com.formdev.flatlaf.FlatLightLaf;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author André Micael Sampaio Pinto <andre at alg3.org>
 */
public class TelaPrincipal extends javax.swing.JFrame {

  
    public TelaPrincipal() {

        try {
            FlatLightLaf.setup();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(TelaCadastroMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        initComponents(); // Inicializa os componentes da tela
        addActionListeners(); // Adiciona os listeners aos botões
       
    }

    //Função para alternar entre os painéis da tela principal
    private void exibirPainel(JPanel painelParaExibir) {
        TelaConsultasPanel.setVisible(false);
        Tela1Jpanel.setVisible(false);
        TelaPacientesPanel.setVisible(false);
     
        TelaBuscaMedicoPanel.setVisible(false);
      
        TelaBalancos.setVisible(false);
        
        // Exibe apenas o painel desejado
        painelParaExibir.setVisible(true);

        if (painelParaExibir == TelaPacientesPanel) {
            consultarPaciente(null);
        } else if (painelParaExibir == TelaBuscaMedicoPanel) {
            consultarMedico(null);
        } else if (painelParaExibir == TelaConsultasPanel) {
            consultarConsulta(null);
        }
    }

    // Função para abrir as janelas
    private void exibirTelas(JFrame janelaParaExibir){
        janelaParaExibir.setVisible(true);
        janelaParaExibir.toFront();
    }

    // Função para executar os listeners dos botões
    private void addActionListeners() {    
        
        // Instancia as telas
        TelaCadastroCliente telaCadastro = new TelaCadastroCliente();
        TelaCadastroMedico telaCadastroMedico = new TelaCadastroMedico();
        TelaCadastroConsulta telaCadastroConsulta = new TelaCadastroConsulta();
        TelaExibirEntradas telaExibirEntradas = new TelaExibirEntradas();
        TelaExibirSaidas telaExibirSaidas = new TelaExibirSaidas();
        TelaCadastroDespesa telaCadastroDespesa = new TelaCadastroDespesa();

        // Abre as janelas
        CadastroPaciente.addActionListener((java.awt.event.ActionEvent evt) -> exibirTelas(telaCadastro));
        NovoMedico.addActionListener((java.awt.event.ActionEvent evt) -> exibirTelas(telaCadastroMedico));
        NovaConsulta.addActionListener((java.awt.event.ActionEvent evt) -> exibirTelas(telaCadastroConsulta));
        EntradasFinanceiro.addActionListener((java.awt.event.ActionEvent evt) -> exibirTelas(telaExibirEntradas));
        SaidasFinanceiro.addActionListener((java.awt.event.ActionEvent evt) -> exibirTelas(telaExibirSaidas));   
        NovaDepesa.addActionListener((java.awt.event.ActionEvent evt) -> exibirTelas(telaCadastroDespesa));   

        // Alterna entre os paineis
        VerTodasConsultas.addActionListener((java.awt.event.ActionEvent evt) -> exibirPainel(TelaConsultasPanel));
        VerPacientes.addActionListener((java.awt.event.ActionEvent evt) -> exibirPainel(TelaPacientesPanel));
        
        VerMedicos.addActionListener((java.awt.event.ActionEvent evt) -> exibirPainel(TelaBuscaMedicoPanel));
        BalancoFinanceiro.addActionListener((java.awt.event.ActionEvent evt) -> exibirPainel(TelaBalancos));

        
        TextFieldNomePaciente.addActionListener(this::consultarPaciente); //Busca ao teclar enter
        ButtonConsultaPaciente.addActionListener(this::consultarPaciente); // Busca ao apertar botão

        TextFieldNomeMedico.addActionListener(this::consultarMedico); //Busca ao teclar enter
        ButtonConsultaMedico.addActionListener(this::consultarMedico); // Busca ao apertar botão

        TextFieldNomeConsulta.addActionListener(this::consultarConsulta); //Busca ao teclar enter
        ButtonConsultaConsultas.addActionListener(this::consultarConsulta); // Busca ao apertar botão

    }
    
  
    
    private void consultarPaciente(java.awt.event.ActionEvent evt) {
        String nome = TextFieldNomePaciente.getText();
        DefaultTableModel tableModel = (DefaultTableModel) jTable1ConsultaPaciente.getModel();
        tableModel.setRowCount(0);
        PacienteController pacienteController = new PacienteController();

        try {
            ArrayList<Paciente> pacientes = pacienteController.listarPacientes(nome);
            for (Paciente paciente : pacientes) {
                Object[] rowData = {
                    paciente.getId(),
                    paciente.getNome(),
                    paciente.getData_nascimento(),
                    paciente.getCpf(),
                    paciente.getGenero(),
                    paciente.getConvenio(),
                    paciente.getEndereco(),
                    paciente.getTelefone()
                };
                tableModel.addRow(rowData);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao consultar pacientes: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void consultarMedico(java.awt.event.ActionEvent evt) {
        String nome = TextFieldNomeMedico.getText();
        DefaultTableModel tableModel = (DefaultTableModel) jTable1ConsultaMedico.getModel();
        tableModel.setRowCount(0);
        MedicoController medicoController = new MedicoController();

        try {
            ArrayList<Medico> medicos = medicoController.listarMedicos(nome);
            for (Medico medico : medicos) {
                Object[] rowData = {
                    medico.getId(),
                    medico.getNome(),
                    medico.getData_nascimento(),
                    medico.getCrm(),
                    medico.getGenero(),
                    medico.getEspecialidade(),
                    medico.getEndereco(),
                    medico.getTelefone()
                };
                tableModel.addRow(rowData);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao consultar medicos: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void consultarConsulta(java.awt.event.ActionEvent evt) {
        String nome = TextFieldNomeConsulta.getText();
        DefaultTableModel tableModel = (DefaultTableModel) jTable1Consultas.getModel();
        tableModel.setRowCount(0);
        ConsultaController consultaController = new ConsultaController();
    
        try {
            ArrayList<Consulta> consultas = consultaController.listarConsultas(nome);
            for (Consulta consulta : consultas) {
                Object[] rowData = {
                    consulta.getId(),
                    consulta.getData(),
                    consulta.getValor(),
                    consulta.getMedico().getNome(), // Mostra o nome do médico
                    consulta.getPaciente().getNome(), // Mostra o nome do paciente
                   
                };
                tableModel.addRow(rowData);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao consultar consultas: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Função para voltar para a tela principal
    public void voltarButton() {
        Tela1Jpanel.setVisible(true);
        TelaBalancos.setVisible(false);
        TelaBuscaMedicoPanel.setVisible(false);
        
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
        jLabel4 = new javax.swing.JLabel();
        LabelTodasConsultas = new javax.swing.JLabel();
        TextFieldNomeConsulta = new javax.swing.JTextField();
        ButtonConsultaConsultas = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1Consultas = new javax.swing.JTable();
        TelaPacientesPanel = new javax.swing.JPanel();
        VoltarBtn1 = new javax.swing.JButton();
        LabelTodosPacientes = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        TextFieldNomePaciente = new javax.swing.JTextField();
        ButtonConsultaPaciente = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1ConsultaPaciente = new javax.swing.JTable();
        TelaBuscaMedicoPanel = new javax.swing.JPanel();
        VoltarBtn4 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1ConsultaMedico = new javax.swing.JTable();
        ButtonConsultaMedico = new javax.swing.JButton();
        TextFieldNomeMedico = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        LabelTodosMedicos = new javax.swing.JLabel();
        TelaBalancos = new javax.swing.JPanel();
        VoltarBtn5 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jTextField6 = new javax.swing.JTextField();
        MenuPrincipal = new javax.swing.JMenuBar();
        MenuCadastros = new javax.swing.JMenu();
        VerPacientes = new javax.swing.JMenuItem();
        VerMedicos = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        CadastroPaciente = new javax.swing.JMenuItem();
        NovoMedico = new javax.swing.JMenuItem();
        MenuConsultas = new javax.swing.JMenu();
        NovaConsulta = new javax.swing.JMenuItem();
        VerTodasConsultas = new javax.swing.JMenuItem();
        MenuBuscarMedico = new javax.swing.JMenu();
        NovaDepesa = new javax.swing.JMenuItem();
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

        jLabel4.setText("Buscar Consulta:");

        LabelTodasConsultas.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        LabelTodasConsultas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon-paciente.png"))); // NOI18N
        LabelTodasConsultas.setText("Todas as Consultas");

        TextFieldNomeConsulta.setToolTipText("Informe do paciente da consulta");

        ButtonConsultaConsultas.setText("Buscar");

        jTable1Consultas.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        jTable1Consultas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Código", "Data", "Valor", "Medico", "Paciente"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1Consultas.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1Consultas.setShowGrid(true);
        jScrollPane3.setViewportView(jTable1Consultas);

        javax.swing.GroupLayout TelaConsultasPanelLayout = new javax.swing.GroupLayout(TelaConsultasPanel);
        TelaConsultasPanel.setLayout(TelaConsultasPanelLayout);
        TelaConsultasPanelLayout.setHorizontalGroup(
            TelaConsultasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TelaConsultasPanelLayout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addGroup(TelaConsultasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(TelaConsultasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane3)
                        .addComponent(LabelTodasConsultas, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(TelaConsultasPanelLayout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(TextFieldNomeConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(ButtonConsultaConsultas)))
                    .addComponent(VoltarBtn))
                .addGap(50, 50, 50))
        );
        TelaConsultasPanelLayout.setVerticalGroup(
            TelaConsultasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TelaConsultasPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(LabelTodasConsultas, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(TelaConsultasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(TextFieldNomeConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonConsultaConsultas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(VoltarBtn)
                .addContainerGap(47, Short.MAX_VALUE))
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

        LabelTodosPacientes.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        LabelTodosPacientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon-paciente.png"))); // NOI18N
        LabelTodosPacientes.setText("Todos os Pacientes");

        jLabel1.setText("Buscar Paciente:");

        TextFieldNomePaciente.setToolTipText("Informe o nome do paciente...");

        ButtonConsultaPaciente.setText("Buscar");

        jTable1ConsultaPaciente.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        jTable1ConsultaPaciente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Nome", "Data Nasc.", "CPF", "Gênero", "Convênio", "Endereço", "Telefone"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1ConsultaPaciente.setShowGrid(true);
        jScrollPane1.setViewportView(jTable1ConsultaPaciente);

        javax.swing.GroupLayout TelaPacientesPanelLayout = new javax.swing.GroupLayout(TelaPacientesPanel);
        TelaPacientesPanel.setLayout(TelaPacientesPanelLayout);
        TelaPacientesPanelLayout.setHorizontalGroup(
            TelaPacientesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TelaPacientesPanelLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(TelaPacientesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(VoltarBtn1)
                    .addGroup(TelaPacientesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1)
                        .addComponent(LabelTodosPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(TelaPacientesPanelLayout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(TextFieldNomePaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(ButtonConsultaPaciente))))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        TelaPacientesPanelLayout.setVerticalGroup(
            TelaPacientesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TelaPacientesPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(LabelTodosPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(TelaPacientesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(TextFieldNomePaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonConsultaPaciente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(VoltarBtn1)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        BackgroundPanel.add(TelaPacientesPanel, "card3");

        TelaBuscaMedicoPanel.setBackground(new java.awt.Color(255, 51, 51));
        TelaBuscaMedicoPanel.setMaximumSize(new java.awt.Dimension(712, 423));
        TelaBuscaMedicoPanel.setMinimumSize(new java.awt.Dimension(712, 423));

        VoltarBtn4.setText("Voltar");
        VoltarBtn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VoltarBtn4ActionPerformed(evt);
            }
        });

        jTable1ConsultaMedico.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        jTable1ConsultaMedico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Nome", "Data Nasc.", "CRM", "Gênero", "Especialidade", "Endereço", "Telefone"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1ConsultaMedico.setShowGrid(true);
        jScrollPane2.setViewportView(jTable1ConsultaMedico);

        ButtonConsultaMedico.setText("Buscar");

        TextFieldNomeMedico.setToolTipText("Informe o nome do paciente...");

        jLabel3.setText("Buscar Médico:");

        LabelTodosMedicos.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        LabelTodosMedicos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon-doctor.png"))); // NOI18N
        LabelTodosMedicos.setText("Todos os Médicos");

        javax.swing.GroupLayout TelaBuscaMedicoPanelLayout = new javax.swing.GroupLayout(TelaBuscaMedicoPanel);
        TelaBuscaMedicoPanel.setLayout(TelaBuscaMedicoPanelLayout);
        TelaBuscaMedicoPanelLayout.setHorizontalGroup(
            TelaBuscaMedicoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TelaBuscaMedicoPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(VoltarBtn4)
                .addGap(144, 144, 144))
            .addGroup(TelaBuscaMedicoPanelLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(TelaBuscaMedicoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(LabelTodosMedicos, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(TelaBuscaMedicoPanelLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextFieldNomeMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ButtonConsultaMedico)))
                .addContainerGap(80, Short.MAX_VALUE))
        );
        TelaBuscaMedicoPanelLayout.setVerticalGroup(
            TelaBuscaMedicoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TelaBuscaMedicoPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(LabelTodosMedicos, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(TelaBuscaMedicoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(TextFieldNomeMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonConsultaMedico))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(VoltarBtn4)
                .addContainerGap(44, Short.MAX_VALUE))
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

        NovaDepesa.setText("Nova Despesa");
        MenuBuscarMedico.add(NovaDepesa);

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

    private void VoltarBtn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VoltarBtn4ActionPerformed
        voltarButton();
    }//GEN-LAST:event_VoltarBtn4ActionPerformed

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
    private javax.swing.JButton ButtonConsultaConsultas;
    private javax.swing.JButton ButtonConsultaMedico;
    private javax.swing.JButton ButtonConsultaPaciente;
    private javax.swing.JMenuItem CadastroPaciente;
    private javax.swing.JMenuItem EntradasFinanceiro;
    private javax.swing.JLabel LabelTodasConsultas;
    private javax.swing.JLabel LabelTodosMedicos;
    private javax.swing.JLabel LabelTodosPacientes;
    private javax.swing.JMenu MenuBuscarMedico;
    private javax.swing.JMenu MenuCadastros;
    private javax.swing.JMenu MenuConsultas;
    private javax.swing.JMenuBar MenuPrincipal;
    private javax.swing.JMenuItem NovaConsulta;
    private javax.swing.JMenuItem NovaDepesa;
    private javax.swing.JMenuItem NovoMedico;
    private javax.swing.JPanel ProximasConsultasPanel;
    private javax.swing.JMenuItem SaidasFinanceiro;
    private javax.swing.JPanel Tela1Jpanel;
    private javax.swing.JPanel TelaBalancos;
    private javax.swing.JPanel TelaBuscaMedicoPanel;
    private javax.swing.JPanel TelaConsultasPanel;
    private javax.swing.JPanel TelaPacientesPanel;
    private javax.swing.JTextField TextFieldNomeConsulta;
    private javax.swing.JTextField TextFieldNomeMedico;
    private javax.swing.JTextField TextFieldNomePaciente;
    private javax.swing.JMenuItem VerMedicos;
    private javax.swing.JMenuItem VerPacientes;
    private javax.swing.JMenuItem VerTodasConsultas;
    private javax.swing.JButton VoltarBtn;
    private javax.swing.JButton VoltarBtn1;
    private javax.swing.JButton VoltarBtn4;
    private javax.swing.JButton VoltarBtn5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanelImage;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTable jTable1ConsultaMedico;
    private javax.swing.JTable jTable1ConsultaPaciente;
    private javax.swing.JTable jTable1Consultas;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables
}



// Código antigo para exibir os paineis
//
//
// VerTodasConsultas.addActionListener((java.awt.event.ActionEvent evt) -> {
//     TelaConsultasPanel.setVisible(true);
//     Tela1Jpanel.setVisible(false);
//     TelaPacientesPanel.setVisible(false);
//     TelaConveniosPanel.setVisible(false);
//     TelaBuscaMedicoPanel.setVisible(false);
//     TelaBalancos.setVisible(false);
// });

// VerPacientes.addActionListener((java.awt.event.ActionEvent evt) -> {
//     TelaPacientesPanel.setVisible(true);
//     TelaConsultasPanel.setVisible(false);
//     Tela1Jpanel.setVisible(false);
//     TelaConveniosPanel.setVisible(false);
//     TelaBuscaMedicoPanel.setVisible(false);
//     TelaBalancos.setVisible(false);
// });

// VerConvenios.addActionListener((java.awt.event.ActionEvent evt) -> {
//     TelaConveniosPanel.setVisible(true);
//     TelaConsultasPanel.setVisible(false);
//     Tela1Jpanel.setVisible(false);
//     TelaPacientesPanel.setVisible(false);
//     TelaBuscaMedicoPanel.setVisible(false);
//     TelaBalancos.setVisible(false);
// });

// VerEspecialidades.addActionListener((java.awt.event.ActionEvent evt) -> {
//     TelaEspecialidadesPanel.setVisible(true);
//     TelaConveniosPanel.setVisible(false);
//     TelaConsultasPanel.setVisible(false);
//     Tela1Jpanel.setVisible(false);
//     TelaPacientesPanel.setVisible(false);
//     TelaBuscaMedicoPanel.setVisible(false);
//     TelaBalancos.setVisible(false);
// });

// VerMedicos.addActionListener((java.awt.event.ActionEvent evt) -> {
//     TelaBuscaMedicoPanel.setVisible(true);
//     TelaEspecialidadesPanel.setVisible(false);
//     TelaConveniosPanel.setVisible(false);
//     TelaConsultasPanel.setVisible(false);
//     Tela1Jpanel.setVisible(false);
//     TelaPacientesPanel.setVisible(false);
//     TelaBalancos.setVisible(false);

// });

// BalancoFinanceiro.addActionListener((java.awt.event.ActionEvent evt) -> {
//     TelaBalancos.setVisible(true);
//     TelaBuscaMedicoPanel.setVisible(false);
//     TelaEspecialidadesPanel.setVisible(false);
//     TelaConveniosPanel.setVisible(false);
//     TelaConsultasPanel.setVisible(false);
//     Tela1Jpanel.setVisible(false);
//     TelaPacientesPanel.setVisible(false);

// });




  //  Código antigo para abrir as telas

        // CadastroPaciente.addActionListener((java.awt.event.ActionEvent evt) -> {
        //     
        //     TelaCadastroCliente telaCadastroCliente = new TelaCadastroCliente();
        //     telaCadastro.setVisible(true);
        //     telaCadastro.toFront();
        // });

        // NovoMedico.addActionListener((java.awt.event.ActionEvent evt) -> {
        //      
        //     TelaCadastroMedico telaCadastroMedico = new TelaCadastroMedico();
        //     telaCadastroMedico.setVisible(true);
        //     telaCadastroMedico.toFront();
        // });

        // NovaConsulta.addActionListener((java.awt.event.ActionEvent evt) -> {
        //      
        //     TelaCadastroConsulta telaCadastroConsulta = new TelaCadastroConsulta();
        //     telaCadastroConsulta.setVisible(true);
        //     telaCadastroConsulta.toFront();
        // });

        // EntradasFinanceiro.addActionListener((java.awt.event.ActionEvent evt) -> {
        //  
        //     TelaExibirEntradas telaExibirEntradas = new TelaExibirEntradas();
        //     telaExibirEntradas.setVisible(true);
        //     telaExibirEntradas.toFront();
        // });

        // SaidasFinanceiro.addActionListener((java.awt.event.ActionEvent evt) -> {
        //     TelaExibirSaidas telaExibirSaidas = new TelaExibirSaidas();
        //     telaExibirSaidas.setVisible(true);
        //     telaExibirSaidas.toFront();
        // });