/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Label;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Monica
 */
public class InformacionPanel extends JPanel{
    //tamaño del Panel
    Dimension size;
    private JLabel vivos;
    private JLabel muertos;
    private String title;
    private Color c;
    
    public InformacionPanel(Dimension size, String title, Color c){
        setPreferredSize(size);
        this.c = c;
        this.size = size; 
        this.title = title;
        initComponents();
        addComponents();        
    }
    
    private void initComponents(){   
        Font label = new Font("TimesRoman",Font.PLAIN,14);
        vivos = new JLabel("Vivos: ");
        muertos = new JLabel("Muertos: ");
        
        vivos.setFont(label);
        muertos.setFont(label);
    }
    
    private void addComponents() {
        Font titleFont = new Font("TimesRoman",Font.PLAIN,18);
        JPanel pLabels = new JPanel();
        
        pLabels.setLayout(new BoxLayout(pLabels,BoxLayout.Y_AXIS));
        setLayout(new FlowLayout(FlowLayout.LEFT,5,02));
        setBorder(new TitledBorder(title));
        
        ((TitledBorder)getBorder()).setTitleFont(titleFont);
        pLabels.add(vivos);
        pLabels.add(muertos);
        add(pLabels);
        add(new JPanel(){
            @Override
            public void paint(Graphics g) {
                super.paint(g); //To change body of generated methods, choose Tools | Templates.
                setBackground(c);
            }
            
        });
    }
    
    public void setMuertos(long muertos){
        this.muertos.setText("Muertos: "+muertos);
    }
    
    public void setVivos(long vivos){
        this.vivos.setText("Vivos: "+vivos);
    }
    
}
