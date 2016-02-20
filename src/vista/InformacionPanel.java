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
import javax.swing.JPanel;

/**
 *
 * @author Monica
 */
public class InformacionPanel extends JPanel{
    //tamaño del Panel
    Dimension size;
    
    public InformacionPanel(Dimension size, Color color){
        setPreferredSize(size);
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setBackground(color);
        this.size = size; 
      
        
    }
    
    
}
