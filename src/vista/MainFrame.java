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
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
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
    //GUI menus e interacciones
    MenuBar menuBar;
    Menu mFile;
    Menu mConfig;
    JButton bStart;
    JButton bPause;
    //GUI para elementos discretos
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
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
        addComponents();  
        createMenu();
        pack();
    }
        
    private void initComponents() {
        setLayout(new GridBagLayout());       
        
        //Escenario de elementos discretos
        escenarioP = new EscenarioPanel(new Dimension(700, 700), new Dimension(7,7));
        
        //Botones
        bStart = new JButton("Start");
        bStart.addActionListener(this);
        bPause = new JButton("Pause");
        bPause.addActionListener(this);
        bPause.setEnabled(false);
        
        //Cuadros de información
        informacionCam = new InformacionPanel (new Dimension(200,70), "Camarones", Color.ORANGE);
        informacionJaiba = new InformacionPanel (new Dimension(200,70), "Jaibas", Color.RED);
        informacionAnguila = new InformacionPanel (new Dimension(200,70), "Anguilas", Color.MAGENTA);
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
        gbc.weighty = 1.0;
        gbc.gridwidth = 1;
        add(bStart, gbc);
        
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weighty = 1.0;
        gbc.gridwidth = 1;
        add(bPause, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == bStart){
            Thread t = new Thread(() -> {
                escenarioP.runPainter(bStart, informacionCam, informacionJaiba, informacionAnguila);                
            });
            t.start();
            bStart.setEnabled(false);
            bPause.setEnabled(true);
        }else if(e.getSource() == bPause){
            escenarioP.pauseExecution();
            bPause.setEnabled(false);
        }
    }

    private void createMenu() {
        MenuItem exit = new MenuItem("Exit");
        exit.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
        MenuItem setTime = new MenuItem("Velocidad ejecución");
        setTime.addActionListener((ActionEvent e) -> {
            new DConfiguraciones(this,escenarioP).setVisible(true);
        });
        menuBar = new MenuBar();
        mFile = new Menu("File");
        mConfig = new Menu("Config");
        
        mFile.add(exit);
        mConfig.add(setTime);
        menuBar.add(mFile);
        menuBar.add(mConfig);
        setMenuBar(menuBar);
    }    
}
