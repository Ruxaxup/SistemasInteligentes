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
    InformacionPanel informacionReina;
    InformacionPanel informacionObreras;
    InformacionPanel informacionZanganos;
    InformacionPanel informacionLarvas;
    
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
    
    public void runPainter(){
        escenarioP.runPainter();
    }
    
    private void initComponents() {
        setLayout(new GridBagLayout());
           
        
        //Escenario de elementos discretos
        escenarioP = new EscenarioPanel(new Dimension(700, 700), new Dimension(7,7));
        
        //Botones
        bStart = new JButton("Start");
        bStart.addActionListener(this);
        
        //Cuadros de información
        informacionReina = new InformacionPanel (new Dimension(70,70), Color.red);
        informacionObreras = new InformacionPanel (new Dimension(70,70), Color.blue);
        informacionZanganos = new InformacionPanel (new Dimension(70,70), Color.green);
        informacionLarvas = new InformacionPanel (new Dimension(70,70), Color.cyan);
    }
    
    private void addComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(escenarioP);
        add(bStart);
        //add(BorderLayout.PAGE_START, new Label("Colmena"));
        //add(BorderLayout.EAST, informacionP);
        //add(BorderLayout.WEST, new JButton());
        //add(BorderLayout.CENTER, escenarioP);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == bStart){
            Thread t = new Thread(() -> {
                escenarioP.runPainter();                
            });
            t.start();
        }
    }

    
}
