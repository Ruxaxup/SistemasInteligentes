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
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import modelo.ElementoDiscreto;
import utils.GeneraEcosistema;

/**
 *
 * @author Ruxaxup
 */
public class EscenarioPanel extends JPanel implements MouseListener{
    //tamaño de la matriz
    Dimension size;
    //tamaño del elemento graficamente (pixeles)
    Dimension ED_size;
    //Matriz de elementos discretos
    public ElementoDiscreto [][] ed;
    public HashMap<Integer,Integer> estadistico;
    
    public EscenarioPanel(Dimension size, Dimension ED_size){
        setPreferredSize(size);
        setBackground(Color.black);
        this.size = size;        
        this.ED_size = ED_size;
        ed = new ElementoDiscreto[size.width/ED_size.width][size.height/ED_size.height];
        ed = GeneraEcosistema.test(new Dimension(100, 100));
        //GeneraEcosistema.generaAgua(ed);
        GeneraEcosistema.GeneraEcosistema(ElementoDiscreto.Tipo.CAMARON, this, 1, .8);
        //GeneraEcosistema.GeneraEcosistema(ElementoDiscreto.Tipo.JAIBA, this, 2, .4);
        //GeneraEcosistema.GeneraEcosistema(ElementoDiscreto.Tipo.GARZA, this, 3, .01);
        GeneraEcosistema.GeneraEcosistema(ElementoDiscreto.Tipo.CAMARON, this, 4, .6);
        System.out.println("Dimension de la matriz: "+ed.length );
        this.addMouseListener(this);
        
        estadistico = new HashMap<>();
        estadistico.put(new Integer(0), new Integer(0));
        estadistico.put(new Integer(1), new Integer(0));
        estadistico.put(new Integer(2), new Integer(0));
        estadistico.put(new Integer(3), new Integer(0));
        estadistico.put(new Integer(4), new Integer(0));
        estadistico.put(new Integer(5), new Integer(0));
        estadistico.put(new Integer(6), new Integer(0));
        estadistico.put(new Integer(7), new Integer(0));
        
    }
    
    public void runPainter(){
        int k = 0;
        System.out.println("Painter");
        while(k < 10000000){
            for(int i = 0; i < ed.length; i++){
                for(int j = 0; j < ed.length; j++){
                    ed[i][j].mover(ed, i, j, estadistico);
                }
            }
            //repaint();
            //System.out.println(k);
            k++;
            /*try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(EscenarioPanel.class.getName()).log(Level.SEVERE, null, ex);
            }*/
        }
        System.out.println(estadistico);
    }
    
    private void printGrid(int index, Graphics2D g2D){
        g2D.setColor(Color.LIGHT_GRAY);
        g2D.drawLine(index * ED_size.width, 0,
                         index * ED_size.width, size.height);
        g2D.drawLine(0, index * ED_size.width,
                     size.width, index * ED_size.width);
    }
    
    @Override
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        super.paint(g);
        //Para cada elemento de la matriz, se pinta segun sea su tipo
        
        for (int i = 0; i < ed.length; i++) {                        
            for (int j = 0; j < ed.length; j++) {
                pintaElementoDiscreto(ed[i][j], g2D, i, j);                
            }            
        }
        for (int i = 0; i < ed.length; i++) {
            printGrid(i, g2D);
        }
        g2D.setColor(Color.red);
        g2D.dispose();
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
        g.fillRect(x * ED_size.width, y * ED_size.height,
                   ED_size.width, ED_size.height);
    }
    
    private class Painter implements Runnable{
        JPanel p;
        public Painter (JPanel p){
            this.p = p;
        }
        @Override
        public void run() {
            while(true){
                p.repaint();
            }
        }
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println((e.getX()/ED_size.width)
                  + ", " + (e.getY()/ED_size.height));
    }

    @Override
    public void mousePressed(MouseEvent e) {
            
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
    
}
