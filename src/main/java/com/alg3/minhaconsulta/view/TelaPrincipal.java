/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.alg3.minhaconsulta.view;

import com.alg3.minhaconsulta.controller.ConsultaController;
import com.alg3.minhaconsulta.controller.DespesaController;
import com.alg3.minhaconsulta.controller.MedicoController;
import com.alg3.minhaconsulta.controller.PacienteController;
import com.alg3.minhaconsulta.dao.ConnectionDAO;
import com.alg3.minhaconsulta.dao.ExceptionDAO;
import com.alg3.minhaconsulta.model.Consulta;
import com.alg3.minhaconsulta.model.Despesa;
import com.alg3.minhaconsulta.model.Medico;
import com.alg3.minhaconsulta.model.Paciente;
import com.alg3.minhaconsulta.view.Cadastros.TelaCadastroConsulta;
import com.alg3.minhaconsulta.view.Cadastros.TelaCadastroMedico;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

import com.alg3.minhaconsulta.view.Cadastros.TelaCadastroCliente;
import com.alg3.minhaconsulta.view.Cadastros.TelaCadastroDespesa;
import com.alg3.minhaconsulta.view.Cadastros.TelaEditarCliente;
import com.alg3.minhaconsulta.view.Cadastros.TelaEditarDespesa;
import com.alg3.minhaconsulta.view.Cadastros.TelaEditarMedico;
import com.formdev.flatlaf.FlatLightLaf;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableCellRenderer;
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
        }else if (painelParaExibir == TelaBalancos) {
            consultarEntrada(null);
            consultarSaida(null);
            totalBalancos();
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
        TelaCadastroDespesa telaCadastroDespesa = new TelaCadastroDespesa();
        TelaEditarDespesa telaEditarDespesa = new TelaEditarDespesa();
        TelaEditarCliente telaEditarCliente = new TelaEditarCliente();
        TelaEditarMedico telaEditarMedico = new TelaEditarMedico();

        // Abre as janelas
        CadastroPaciente.addActionListener((java.awt.event.ActionEvent evt) -> exibirTelas(telaCadastro));
        NovoMedico.addActionListener((java.awt.event.ActionEvent evt) -> exibirTelas(telaCadastroMedico));
        NovaConsulta.addActionListener((java.awt.event.ActionEvent evt) -> exibirTelas(telaCadastroConsulta));
        jButtonEditarEntrada.addActionListener((java.awt.event.ActionEvent evt) -> exibirTelas(telaEditarDespesa)); 
        jButtonEditarPaciente.addActionListener((java.awt.event.ActionEvent evt) -> exibirTelas(telaEditarCliente)); 
        NovaDepesa.addActionListener((java.awt.event.ActionEvent evt) -> exibirTelas(telaCadastroDespesa));   
        jButtonEditarMedico.addActionListener((java.awt.event.ActionEvent evt) -> exibirTelas(telaEditarMedico));

        // Alterna entre os paineis
        VerTodasConsultas.addActionListener((java.awt.event.ActionEvent evt) -> exibirPainel(TelaConsultasPanel));
        VerPacientes.addActionListener((java.awt.event.ActionEvent evt) -> exibirPainel(TelaPacientesPanel));
        
        VerMedicos.addActionListener((java.awt.event.ActionEvent evt) -> exibirPainel(TelaBuscaMedicoPanel));
        BalancoFinanceiro.addActionListener((java.awt.event.ActionEvent evt) -> exibirPainel(TelaBalancos));

        
        
        TextFieldNomePaciente.addActionListener(this::consultarPaciente); //Busca ao teclar enter
        ButtonConsultaPaciente.addActionListener(this::consultarPaciente); // Busca ao apertar botão

        TextFieldNomeMedico.addActionListener(this::consultarMedico); //Busca ao teclar enter
        ButtonConsultaMedico.addActionListener(this::consultarMedico); // Busca ao apertar botão

        TextFieldNomeConsulta.addActionListener(evt -> consultarConsulta(evt)); //Busca ao teclar enter
        ButtonConsultaConsultas.addActionListener(evt -> consultarConsulta(evt)); // Busca ao apertar botão
        
        TextFieldBuscaEntrada.addActionListener(evt -> consultarEntrada(evt)); // Busca ao teclar enter
        ButtonBuscaEntrada.addActionListener(evt -> consultarEntrada(evt)); // Busca ao apertar botão
        
        TextFieldBuscaSaida.addActionListener(evt -> consultarSaida(evt)); // Busca ao teclar enter
        ButtonBuscaSaida.addActionListener(evt -> consultarSaida(evt)); // Busca ao apertar botão
        
        
       
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
        } catch (ExceptionDAO ex) {
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
        } catch (ExceptionDAO ex) {
            JOptionPane.showMessageDialog(this, "Erro ao consultar medicos: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void consultarEntrada(java.awt.event.ActionEvent evt) {
        String nome = TextFieldBuscaEntrada.getText();
        DefaultTableModel tableModel = (DefaultTableModel) jTable1DespesaEntrada.getModel();
        tableModel.setRowCount(0);
        jTable1DespesaEntrada.getColumnModel().getColumn(0).setPreferredWidth(160);
        jTable1DespesaEntrada.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer());
        // Colocar R$ antes do valor e deixar com 2 casas decimais
        jTable1DespesaEntrada.getColumnModel().getColumn(1).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            protected void setValue(Object value) {
                if (value instanceof Double) {
                    value = String.format("R$ %.2f", value);
                }
                super.setValue(value);
            }
        });
        
        DespesaController despesaController = new DespesaController();
    
        try {
            ArrayList<Despesa> despesas = despesaController.listarDespesas(nome);
            for (Despesa despesa : despesas) {
                if ("Entrada".equalsIgnoreCase(despesa.getTipo())) {
                    Object[] rowData = {
                        despesa.getDescricao(),
                        despesa.getValor(),
                        despesa.getDataRegistro()
                    };
                    tableModel.addRow(rowData);
                }
            }
        } catch (ExceptionDAO ex) {
            JOptionPane.showMessageDialog(this, "Erro ao consultar entradas: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
   
    private void totalBalancos() {
        double totalEntrada = 0;
        double totalSaida = 0;
        double balanco = 0;
        for (int i = 0; i < jTable1DespesaEntrada.getRowCount(); i++) {
            totalEntrada += (double) jTable1DespesaEntrada.getValueAt(i, 1);
        }
        
         for (int i = 0; i < jTable1DespesaSaida.getRowCount(); i++) {
            totalSaida += (double) jTable1DespesaSaida.getValueAt(i, 1);
        }
         
        balanco = totalEntrada - totalSaida;
        
        TextFieldEntradasTotal.setText(String.format("R$ %.2f", totalEntrada));
        TextFieldSaidasTotal.setText(String.format("R$ %.2f", totalSaida));
        if(balanco < 0){
            TextFieldBalanco.setText(String.format("R$ %.2f", balanco));
            TextFieldBalanco.setForeground(new java.awt.Color(255, 0, 0));
        }else{
            TextFieldBalanco.setText(String.format("R$ %.2f", balanco));
            TextFieldBalanco.setForeground(new java.awt.Color(0, 153, 0));
        }
        
    }
    
    private void consultarSaida(java.awt.event.ActionEvent evt) {
        String nome = TextFieldBuscaSaida.getText();
        DefaultTableModel tableModel = (DefaultTableModel) jTable1DespesaSaida.getModel();
        tableModel.setRowCount(0);
        jTable1DespesaSaida.getColumnModel().getColumn(0).setPreferredWidth(160);
        // alinhar texto a esquerda
        jTable1DespesaSaida.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer());
         jTable1DespesaSaida.getColumnModel().getColumn(1).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            protected void setValue(Object value) {
                if (value instanceof Double) {
                    value = String.format("R$ %.2f", value);
                }
                super.setValue(value);
            }
        });
        DespesaController despesaController = new DespesaController();
    
        try {
            ArrayList<Despesa> despesas = despesaController.listarDespesas(nome);
            for (Despesa despesa : despesas) {
                if ("Saída".equalsIgnoreCase(despesa.getTipo())) {
                    Object[] rowData = {
                        despesa.getDescricao(),
                        despesa.getValor(),
                        despesa.getDataRegistro()
                    };
                    tableModel.addRow(rowData);
                }
            }
        } catch (ExceptionDAO ex) {
            JOptionPane.showMessageDialog(this, "Erro ao consultar entradas: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
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
                    consulta.getMedicoNome(), // Mostra o nome do médico
                    consulta.getPacienteNome() // Mostra o nome do paciente
                };
                tableModel.addRow(rowData);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao consultar consultas: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public ArrayList<Consulta> listarConsultas(String nome) throws ExceptionDAO {
        String sql = "SELECT c.*, m.nome AS medico_nome, p.nome AS paciente_nome FROM consulta c "
                   + "JOIN medico m ON c.medico_id = m.medico_id "
                   + "JOIN paciente p ON c.paciente_id = p.paciente_id "
                   + "WHERE p.nome LIKE ? ORDER BY c.data_consulta";

        Connection connection = null;
        PreparedStatement pStatement = null;
        ArrayList<Consulta> listaConsultas = new ArrayList<>();

        try {
            connection = new ConnectionDAO().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, "%" + nome + "%");
            ResultSet rs = pStatement.executeQuery();

            while (rs.next()) {
                Consulta consulta = new Consulta();
                consulta.setId(rs.getInt("consulta_id"));
                consulta.setData(rs.getString("data_consulta"));
                consulta.setPacienteId(rs.getInt("paciente_id"));
                consulta.setMedicoId(rs.getInt("medico_id"));
                consulta.setValor(rs.getDouble("valor"));
                consulta.setStatus(rs.getString("status"));
                consulta.setObservacoes(rs.getString("observacoes"));

                listaConsultas.add(consulta);
            }

        } catch (SQLException ex) {
            throw new ExceptionDAO("Erro ao listar consultas. Erro " + ex);
        } finally {
            try {
                if (pStatement != null) {
                    pStatement.close();
                }
            } catch (SQLException ex) {
                throw new ExceptionDAO("Erro ao fechar o Statement. Erro " + ex);
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                throw new ExceptionDAO("Erro ao fechar a conexão. Erro " + ex);
            }
        }

        return listaConsultas;
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
        jButtonEditarPaciente = new javax.swing.JButton();
        TelaBuscaMedicoPanel = new javax.swing.JPanel();
        VoltarBtn4 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1ConsultaMedico = new javax.swing.JTable();
        ButtonConsultaMedico = new javax.swing.JButton();
        TextFieldNomeMedico = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        LabelTodosMedicos = new javax.swing.JLabel();
        jButtonEditarMedico = new javax.swing.JButton();
        TelaBalancos = new javax.swing.JPanel();
        VoltarBtn5 = new javax.swing.JButton();
        LabelTodosPacientes1 = new javax.swing.JLabel();
        TextFieldBuscaEntrada = new javax.swing.JTextField();
        ButtonBuscaEntrada = new javax.swing.JButton();
        TextFieldBuscaSaida = new javax.swing.JTextField();
        ButtonBuscaSaida = new javax.swing.JButton();
        LabelTodosPacientes2 = new javax.swing.JLabel();
        TextFieldBalanco = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable1DespesaEntrada = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        TextFieldEntradasTotal = new javax.swing.JTextField();
        jButtonEditarEntrada = new javax.swing.JButton();
        JButtonRemoverEntrada = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable1DespesaSaida = new javax.swing.JTable();
        TextFieldSaidasTotal = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        JButtonRemoverSaida = new javax.swing.JButton();
        jButtonEditarSaida = new javax.swing.JButton();
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

        jButtonEditarPaciente.setText("Editar");

        javax.swing.GroupLayout TelaPacientesPanelLayout = new javax.swing.GroupLayout(TelaPacientesPanel);
        TelaPacientesPanel.setLayout(TelaPacientesPanelLayout);
        TelaPacientesPanelLayout.setHorizontalGroup(
            TelaPacientesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TelaPacientesPanelLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(TelaPacientesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(VoltarBtn1)
                    .addGroup(TelaPacientesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButtonEditarPaciente)
                        .addGroup(TelaPacientesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addComponent(LabelTodosPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(TelaPacientesPanelLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TextFieldNomePaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ButtonConsultaPaciente)))))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonEditarPaciente)
                .addGap(44, 44, 44)
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

        jButtonEditarMedico.setText("Editar");

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
                .addGroup(TelaBuscaMedicoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonEditarMedico)
                    .addGroup(TelaBuscaMedicoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane2)
                        .addComponent(LabelTodosMedicos, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(TelaBuscaMedicoPanelLayout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(TextFieldNomeMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(ButtonConsultaMedico))))
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonEditarMedico)
                .addGap(32, 32, 32)
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

        LabelTodosPacientes1.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        LabelTodosPacientes1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelTodosPacientes1.setText("Saídas");

        TextFieldBuscaEntrada.setToolTipText("Informe o nome do paciente...");

        ButtonBuscaEntrada.setText("Buscar");

        TextFieldBuscaSaida.setToolTipText("Informe o nome do paciente...");

        ButtonBuscaSaida.setText("Buscar");

        LabelTodosPacientes2.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        LabelTodosPacientes2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelTodosPacientes2.setText("Entradas");

        TextFieldBalanco.setEditable(false);
        TextFieldBalanco.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TextFieldBalanco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldBalancoActionPerformed(evt);
            }
        });

        jPanel1.setMaximumSize(new java.awt.Dimension(331, 230));
        jPanel1.setMinimumSize(new java.awt.Dimension(331, 230));

        jTable1DespesaEntrada.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        jTable1DespesaEntrada.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Descrição", "Valor", "Data"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1DespesaEntrada.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1DespesaEntrada.setMaximumSize(new java.awt.Dimension(225, 80));
        jTable1DespesaEntrada.setMinimumSize(new java.awt.Dimension(225, 80));
        jTable1DespesaEntrada.setShowGrid(true);
        jScrollPane6.setViewportView(jTable1DespesaEntrada);

        jLabel5.setText("Total");

        TextFieldEntradasTotal.setEditable(false);
        TextFieldEntradasTotal.setMaximumSize(new java.awt.Dimension(64, 22));
        TextFieldEntradasTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldEntradasTotalActionPerformed(evt);
            }
        });

        jButtonEditarEntrada.setText("Editar");

        JButtonRemoverEntrada.setText("Remover");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonEditarEntrada)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(JButtonRemoverEntrada)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextFieldEntradasTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TextFieldEntradasTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonEditarEntrada)
                            .addComponent(JButtonRemoverEntrada))
                        .addContainerGap())))
        );

        jPanel2.setMaximumSize(new java.awt.Dimension(331, 230));
        jPanel2.setMinimumSize(new java.awt.Dimension(331, 230));
        jPanel2.setPreferredSize(new java.awt.Dimension(331, 230));

        jTable1DespesaSaida.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        jTable1DespesaSaida.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Descrição", "Valor", "Data"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1DespesaSaida.setMaximumSize(new java.awt.Dimension(225, 80));
        jTable1DespesaSaida.setMinimumSize(new java.awt.Dimension(225, 80));
        jTable1DespesaSaida.setShowGrid(true);
        jScrollPane5.setViewportView(jTable1DespesaSaida);

        TextFieldSaidasTotal.setEditable(false);
        TextFieldSaidasTotal.setMaximumSize(new java.awt.Dimension(64, 22));
        TextFieldSaidasTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldSaidasTotalActionPerformed(evt);
            }
        });

        jLabel6.setText("Total");

        JButtonRemoverSaida.setText("Remover");

        jButtonEditarSaida.setText("Editar");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButtonEditarSaida)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(JButtonRemoverSaida)
                        .addGap(21, 21, 21)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextFieldSaidasTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonEditarSaida)
                        .addComponent(JButtonRemoverSaida))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TextFieldSaidasTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout TelaBalancosLayout = new javax.swing.GroupLayout(TelaBalancos);
        TelaBalancos.setLayout(TelaBalancosLayout);
        TelaBalancosLayout.setHorizontalGroup(
            TelaBalancosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TelaBalancosLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(TelaBalancosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(TelaBalancosLayout.createSequentialGroup()
                        .addComponent(TextFieldBalanco, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(186, 186, 186)
                        .addComponent(VoltarBtn5)
                        .addGap(16, 16, 16))
                    .addGroup(TelaBalancosLayout.createSequentialGroup()
                        .addGroup(TelaBalancosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(TelaBalancosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TelaBalancosLayout.createSequentialGroup()
                                    .addComponent(TextFieldBuscaEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(ButtonBuscaEntrada)
                                    .addGap(62, 62, 62))
                                .addComponent(LabelTodosPacientes2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(TelaBalancosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(TelaBalancosLayout.createSequentialGroup()
                                .addComponent(TextFieldBuscaSaida, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ButtonBuscaSaida))
                            .addComponent(LabelTodosPacientes1, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17))))
        );
        TelaBalancosLayout.setVerticalGroup(
            TelaBalancosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TelaBalancosLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(TelaBalancosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelTodosPacientes2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelTodosPacientes1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(TelaBalancosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextFieldBuscaEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonBuscaEntrada)
                    .addComponent(TextFieldBuscaSaida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonBuscaSaida))
                .addGap(18, 18, 18)
                .addGroup(TelaBalancosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(TelaBalancosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(VoltarBtn5)
                    .addComponent(TextFieldBalanco, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
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

    private void VoltarBtn5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_VoltarBtn5ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_VoltarBtn5ItemStateChanged

    private void TextFieldEntradasTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldEntradasTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldEntradasTotalActionPerformed

    private void TextFieldSaidasTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldSaidasTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldSaidasTotalActionPerformed

    private void TextFieldBalancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldBalancoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldBalancoActionPerformed

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
    private javax.swing.JButton ButtonBuscaEntrada;
    private javax.swing.JButton ButtonBuscaSaida;
    private javax.swing.JButton ButtonConsultaConsultas;
    private javax.swing.JButton ButtonConsultaMedico;
    private javax.swing.JButton ButtonConsultaPaciente;
    private javax.swing.JMenuItem CadastroPaciente;
    private javax.swing.JButton JButtonRemoverEntrada;
    private javax.swing.JButton JButtonRemoverSaida;
    private javax.swing.JLabel LabelTodasConsultas;
    private javax.swing.JLabel LabelTodosMedicos;
    private javax.swing.JLabel LabelTodosPacientes;
    private javax.swing.JLabel LabelTodosPacientes1;
    private javax.swing.JLabel LabelTodosPacientes2;
    private javax.swing.JMenu MenuBuscarMedico;
    private javax.swing.JMenu MenuCadastros;
    private javax.swing.JMenu MenuConsultas;
    private javax.swing.JMenuBar MenuPrincipal;
    private javax.swing.JMenuItem NovaConsulta;
    private javax.swing.JMenuItem NovaDepesa;
    private javax.swing.JMenuItem NovoMedico;
    private javax.swing.JPanel ProximasConsultasPanel;
    private javax.swing.JPanel Tela1Jpanel;
    private javax.swing.JPanel TelaBalancos;
    private javax.swing.JPanel TelaBuscaMedicoPanel;
    private javax.swing.JPanel TelaConsultasPanel;
    private javax.swing.JPanel TelaPacientesPanel;
    private javax.swing.JTextField TextFieldBalanco;
    private javax.swing.JTextField TextFieldBuscaEntrada;
    private javax.swing.JTextField TextFieldBuscaSaida;
    private javax.swing.JTextField TextFieldEntradasTotal;
    private javax.swing.JTextField TextFieldNomeConsulta;
    private javax.swing.JTextField TextFieldNomeMedico;
    private javax.swing.JTextField TextFieldNomePaciente;
    private javax.swing.JTextField TextFieldSaidasTotal;
    private javax.swing.JMenuItem VerMedicos;
    private javax.swing.JMenuItem VerPacientes;
    private javax.swing.JMenuItem VerTodasConsultas;
    private javax.swing.JButton VoltarBtn;
    private javax.swing.JButton VoltarBtn1;
    private javax.swing.JButton VoltarBtn4;
    private javax.swing.JButton VoltarBtn5;
    private javax.swing.JButton jButtonEditarEntrada;
    private javax.swing.JButton jButtonEditarMedico;
    private javax.swing.JButton jButtonEditarPaciente;
    private javax.swing.JButton jButtonEditarSaida;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelImage;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTable jTable1ConsultaMedico;
    private javax.swing.JTable jTable1ConsultaPaciente;
    private javax.swing.JTable jTable1Consultas;
    private javax.swing.JTable jTable1DespesaEntrada;
    private javax.swing.JTable jTable1DespesaSaida;
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