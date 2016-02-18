/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Ruxaxup
 */
public class MainFrame extends JFrame{
    EscenarioPanel escenarioP;
    
    public MainFrame(){
        super("Elementos discretos");
        init();        
    }

    private void init() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setPreferredSize(screenSize);
        //this.setPreferredSize(new Dimension(500,500));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
        addComponents();
        
        pack();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        
        //Escenario de elementos discretos
        escenarioP = new EscenarioPanel(new Dimension(10, 10));
    }
    
    private void addComponents() {
        add(BorderLayout.EAST, new JButton());
        add(BorderLayout.WEST, new JButton());
        add(BorderLayout.CENTER, escenarioP);
    }

    
}
