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

        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
               
                TelaPrincipal telaPrincipal = new TelaPrincipal();
                telaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
                telaPrincipal.setVisible(true); 
            }
        });
    }

}
