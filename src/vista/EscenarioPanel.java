/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vista;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import modelo.ElementoDiscreto;
import utils.GeneraEcosistema;

/**
 *
 * @author Ruxaxup
 */
public class EscenarioPanel extends JPanel{
    //tamaño de la matriz
    Dimension size;
    //tamaño del elemento graficamente (pixeles)
    Dimension g_elemDiscreto_size;
    //Matriz de elementos discretos
    ElementoDiscreto [][] elementoDiscreto;

    public EscenarioPanel(Dimension size){
        setPreferredSize(size);
        setBackground(Color.black);
        this.size = size;        
        g_elemDiscreto_size = new Dimension(10,10);
        elementoDiscreto = new ElementoDiscreto[size.width][size.height];
        elementoDiscreto = GeneraEcosistema.test(size);
    }
    
    private void printGrid(int index, Graphics2D g2D){
        g2D.setColor(Color.LIGHT_GRAY);
        g2D.drawLine(index * g_elemDiscreto_size.width, 0,
                         index * g_elemDiscreto_size.width, size.height);
        g2D.drawLine(0, index * g_elemDiscreto_size.width,
                     size.width, index * g_elemDiscreto_size.width);
    }
    
    @Override
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        super.paint(g);
        //Para cada elemento de la matriz, se pinta segun sea su tipo
        
        for (int i = 0; i < elementoDiscreto.length; i++) {                        
            for (int j = 0; j < elementoDiscreto.length; j++) {
                pintaElementoDiscreto(elementoDiscreto[i][j], g2D, i, j);                
            }            
        }
        for (int i = 0; i < elementoDiscreto.length; i++) {
            printGrid(i, g2D);
        }
        g.dispose();
    }
    
    void pintaElementoDiscreto(ElementoDiscreto ed, Graphics2D g, int x, int y){
        //segun sea el tipo, asignamos un color
        Color c = Color.black;
        switch(ed.getTipo()){            
            case AGUA:
                c = Color.BLUE.brighter();
                break;
            case GARZA:
                c = Color.WHITE;
                break;
            case JAIBA:
                c = Color.RED;
                break;
            case CAMARON:
                c = Color.ORANGE;
                break;
            
            default:
                throw new AssertionError(ed.getTipo().name());
        }
        g.setColor(c);
        g.fillRect(x * g_elemDiscreto_size.width, y * g_elemDiscreto_size.height,
                   g_elemDiscreto_size.width, g_elemDiscreto_size.height);
    }
    
    
}
