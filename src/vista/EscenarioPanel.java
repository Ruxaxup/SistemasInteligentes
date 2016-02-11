/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vista;

import controlador.CreadorEcosistema;
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
        setPreferredSize(new Dimension(200,200));
        setBackground(Color.black);
        this.size = size;
        //Tamaño de los elementos sera de 2x2 pixeles
        g_elemDiscreto_size = new Dimension(8, 8);        
        CreadorEcosistema.plantas = 0.3;
        CreadorEcosistema.suelo = 0.0;
        CreadorEcosistema.carnivoros = 0.2;
        CreadorEcosistema.herbivoros = 0.5;
        elementoDiscreto = CreadorEcosistema.getEcosistema(size);
        
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
        g.dispose();
    }
    
    void pintaElementoDiscreto(ElementoDiscreto ed, Graphics2D g, int x, int y){
        //segun sea el tipo, asignamos un color
        Color c = Color.black;
        switch(ed.getTipo()){
            case PLANTA:
                c = Color.GREEN;
                break;
            case SUELO:
                c = Color.GRAY;
                break;
            case TIRANOSAURIO:
                c = Color.RED;
                break;
            case VELOCIRAPTOR:
                c = Color.ORANGE;
                break;
            case CARNOTAURO:
                c = Color.YELLOW;
                break;
            case GALLIMIMUS:
                c = Color.PINK;
                break;
            case DIPLODOCUS:
                c = Color.BLUE;
                break;
            case TRICERATOPS:
                c = Color.MAGENTA;
                break;
        }
        g.setColor(c);
        int width = g_elemDiscreto_size.width;
        int height = g_elemDiscreto_size.height;
        g.fillRect(x * width, y * height, width, height);
    }
    
    
}
