/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.alg3.minhaconsulta;

import javax.swing.SwingUtilities;

 
import com.alg3.minhaconsulta.view.TelaPrincipal; // Importa a classe TelaPrincipal

 

import javax.swing.JFrame; // Importa JFrame

 

/**
 *
 * @author André Micael Sampaio Pinto <andre at alg3.org>
 */
public class MinhaConsulta {

    public static void main(String[] args) {
    
        


        // Usa SwingUtilities para garantir que a interface gráfica seja criada na thread de eventos do Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override 
            public void run() {
                // Cria uma instância da tela principal
                TelaPrincipal telaPrincipal = new TelaPrincipal();
                telaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Configura a operação de fechamento
                telaPrincipal.setVisible(true); // Torna a tela visível
            }
        });
    }


    

}
