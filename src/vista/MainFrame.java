/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Ruxaxup
 */
public class MainFrame extends JFrame implements ActionListener{
    JButton bStart;
    EscenarioPanel escenarioP;
    InformacionPanel informacionCam;
    InformacionPanel informacionJaiba;
    InformacionPanel informacionAnguila;
    
    public MainFrame(){
        super("Elementos discretos");
        init();        
    }

    private void init() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setPreferredSize(screenSize);
        //this.setPreferredSize(new Dimension(800,500));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
        addComponents();        
        pack();
    }
    
    /*public void runPainter(){
        escenarioP.runPainter(bStart);
    }*/
    
    private void initComponents() {
        setLayout(new GridBagLayout());       
        
        //Escenario de elementos discretos
        escenarioP = new EscenarioPanel(new Dimension(700, 700), new Dimension(7,7));
        
        //Botones
        bStart = new JButton("Start");
        bStart.addActionListener(this);
        
        //Cuadros de información
        informacionCam = new InformacionPanel (new Dimension(200,70), "Camarones");
        informacionJaiba = new InformacionPanel (new Dimension(200,70), "Jaibas");
        informacionAnguila = new InformacionPanel (new Dimension(200,70), "Anguilas");
    }
    
    private void addComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;        
        gbc.gridx = 0;
        gbc.gridy = 0;        
        gbc.gridheight = 3;
        add(escenarioP,gbc);        
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.weighty = 1.0;        
        add(informacionCam,gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(informacionJaiba,gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(informacionAnguila,gbc);
        
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weighty = 0.0;
        gbc.gridwidth = 2;
        add(bStart, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == bStart){
            Thread t = new Thread(() -> {
                escenarioP.runPainter(bStart, informacionCam, informacionJaiba, informacionAnguila);                
            });
            t.start();
            bStart.setEnabled(false);
        }
    }

    
}
