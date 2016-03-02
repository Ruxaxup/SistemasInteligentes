/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.Color;
import java.awt.Dimension;
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
    
    public InformacionPanel(Dimension size, String title){
        setPreferredSize(size);
        this.size = size; 
        this.title = title;
        initComponents();
        addComponents();        
    }
    
    private void initComponents(){        
        vivos = new JLabel("Vivos: ");
        muertos = new JLabel("Muertos: ");
    }
    
    private void addComponents() {
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setBorder(new TitledBorder(title));
        add(vivos);
        add(muertos);
    }
    
    public void setMuertos(long muertos){
        this.muertos.setText("Muertos: "+muertos);
    }
    
    public void setVivos(long vivos){
        this.vivos.setText("Vivos: "+vivos);
    }
    
}
