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
        setPreferredSize(new Dimension(840,450));
        setBackground(Color.black);
        this.size = size;        
    }
    
    /*@Override
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        super.paint(g);
        //Para cada elemento de la matriz, se pinta segun sea su tipo
        for (int i = 0; i < elementoDiscreto.length; i++) {
            for (int j = 0; j < elementoDiscreto.length; j++) {
                pintaElementoDiscreto(elementoDiscreto[i][j], g2D, i, j);                
            }            
        }
        g.dispose();
    }*/
    
    void pintaElementoDiscreto(ElementoDiscreto ed, Graphics2D g, int x, int y){
        //segun sea el tipo, asignamos un color
        Color c = Color.black;
        switch(ed.getTipo()){            
            case LARVA_OBRERA:
                break;
            case LARVA_ZANGANO:
                break;
            case LARVA_REINA:
                break;
            case ABEJA_ZANGANO:
                break;
            case ABEJA_REINA:
                break;
            case ABEJA_OBRERA:
                break;
            case ABEJA_NODRIZA:
                break;
            case ABEJA_ALMACENERA:
                break;
            case ABEJA_CERERA:
                break;
            case ABEJA_CENTINELA:
                break;
            case ABEJA_LIBADORA:
                break;
            default:
                throw new AssertionError(ed.getTipo().name());
        }
    }
    
    
}
