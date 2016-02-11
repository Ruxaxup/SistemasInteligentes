/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vista;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import modelo.ElementoDiscreto;

/**
 *
 * @author Ruxaxup
 */
public class EscenarioPanel extends JPanel{
    Dimension size;
    ElementoDiscreto [][] elementoDiscreto;

    public EscenarioPanel(Dimension size){
        this.size = size;
        elementoDiscreto = new ElementoDiscreto[size.width][size.height];
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
    
    
}
