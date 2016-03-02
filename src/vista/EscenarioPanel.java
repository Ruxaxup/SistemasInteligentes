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
import javax.swing.JButton;
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
            
    public EscenarioPanel(Dimension size, Dimension ED_size){
        setPreferredSize(size);
        //setBackground(Color.black);
        this.size = size;        
        this.ED_size = ED_size;
        ed = new ElementoDiscreto[size.width/ED_size.width][size.height/ED_size.height];
        ed = GeneraEcosistema.test(new Dimension(100, 100));
        //GeneraEcosistema.generaAgua(ed);
        GeneraEcosistema.GeneraEcosistema(ElementoDiscreto.Tipo.CAMARON, this, 1, .1);
        //GeneraEcosistema.GeneraEcosistema(ElementoDiscreto.Tipo.CAMARON, this, 2, .8);
        GeneraEcosistema.GeneraEcosistema(ElementoDiscreto.Tipo.JAIBA, this, 3, .1);
        GeneraEcosistema.GeneraEcosistema(ElementoDiscreto.Tipo.CAMARON, this, 4, .1);
        System.out.println("Dimension de la matriz: "+ed.length );
        this.addMouseListener(this);
        
        
    }
    
    public void runPainter(JButton bStart, InformacionPanel cam,
            InformacionPanel jaibas, InformacionPanel anguilas){
        int k = 0;
        int ciclos = 0;
        System.out.println("Painter");
        while(k < 1000){
            for(int i = 0; i < ed.length; i++){
                for(int j = 0; j < ed.length; j++){
                    if(!ed[i][j].areRulesExecuted())
                        ed[i][j].reglasInteraccion(ed, i, j);
                }
            }
            ciclos++;
            
            for (ElementoDiscreto[] ed1 : ed) {
                for (int j = 0; j < ed.length; j++) {
                    ed1[j].clearRulesExecuted();
                    if(ciclos == 10){
                        ed1[j].clearCoordenadaAnterior();
                        ciclos = 0;
                    }
                }
            }
            repaint();
            cuentaElementosVivos(ed, cam, jaibas, anguilas);
            k++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(EscenarioPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        bStart.setEnabled(true);
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
