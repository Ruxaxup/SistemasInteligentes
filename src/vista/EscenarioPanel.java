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
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;
import modelo.ElementoDiscreto;
import modelo.ElementoDiscreto.Tipo;
import utils.GeneraEcosistema;
import utils.NumeroElementos;

/**
 *
 * @author Ruxaxup
 */
public class EscenarioPanel extends JPanel{
    //tamaño de la matriz
    Dimension size;
    //tamaño del elemento graficamente (pixeles)
    Dimension ED_size;
    //Matriz de elementos discretos
    public ElementoDiscreto [][] ed;
    
    //Thread
    private boolean pause;
    private int threadSleep;
    
    //Generadores
    private final int TIEMPO_CAMARON = 24;
    private final int TIEMPO_JAIBA = 384;
    private final int TIEMPO_ANGUILA = -1;
    long ciclosTotales = 0;
    
    public EscenarioPanel(Dimension size, Dimension ED_size){
        setPreferredSize(size);
        threadSleep = 1;
        this.size = size;        
        this.ED_size = ED_size;
        ed = new ElementoDiscreto[size.width/ED_size.width][size.height/ED_size.height];
        ed = GeneraEcosistema.test(new Dimension(size.width/ED_size.width,
                                                 size.height/ED_size.height));
        //GeneraEcosistema.generaAgua(ed);
        GeneraEcosistema.GeneraEcosistema(ElementoDiscreto.Tipo.CAMARON, this, 1, .1);
        GeneraEcosistema.GeneraEcosistema(ElementoDiscreto.Tipo.CAMARON, this, 2, .8);
        GeneraEcosistema.GeneraEcosistema(ElementoDiscreto.Tipo.JAIBA, this, 3, .1);
        GeneraEcosistema.GeneraEcosistema(ElementoDiscreto.Tipo.CAMARON, this, 3, .2);
        GeneraEcosistema.GeneraEcosistema(ElementoDiscreto.Tipo.ANGUILA, this, 4, .1);        
    }
    
    public void runPainter(JButton bStart, InformacionPanel cam,
            InformacionPanel jaibas, InformacionPanel anguilas, PanelTiempo pTiempo){
        
        int ciclos = 0;
        pause = false;
        //Generadores
        int generaCam = 0;
        int generaJaiba = 0;
        int generaAnguila = 0;
        while(!pause){
            for(int i = 0; i < ed.length; i++){
                for(int j = 0; j < ed.length; j++){
                    if(!ed[i][j].areRulesExecuted())
                        ed[i][j].reglasInteraccion(ed, i, j);
                }
            }
            for (ElementoDiscreto[] ed1 : ed) {
                for (int j = 0; j < ed.length; j++) {
                    ed1[j].clearRulesExecuted();
                    
                    if(ciclos == 12 && ed1[j].getTipo() != Tipo.AGUA){
                        ed1[j].clearCoordenadaAnterior();
                        ciclos = 0;
                    }
                }
            }
            repaint();
            cuentaElementosVivos(ed, cam, jaibas, anguilas);
            ciclosTotales++;
            pTiempo.setTime(ciclosTotales/96);
            ciclos++;
            generaCam++;
            generaJaiba++;
            generaAnguila++;
            //Generadores
            if(generaCam < TIEMPO_CAMARON){
                generaEspecie(Tipo.CAMARON);
                generaCam = 0;
            }
            if(generaJaiba < TIEMPO_JAIBA){
                generaEspecie(Tipo.JAIBA);
                generaJaiba = 0;
            }
            if(generaAnguila < TIEMPO_ANGUILA){
                generaEspecie(Tipo.ANGUILA);
                generaAnguila = 0;
            }
            try {
                Thread.sleep(threadSleep);
            } catch (InterruptedException ex) {
                Logger.getLogger(EscenarioPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        bStart.setEnabled(true);
    }
    
    private void printGrid(int index, Graphics2D g2D){
        g2D.setColor(Color.gray);
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
                c = new Color(0x00,0x8a,0xe6);                
                break;
            case GARZA:
                c = Color.WHITE;
                break;
            case JAIBA:
                //c = Color.RED;
                c = new Color(0xff,0x33,0);
                break;
            case CAMARON:
                c = Color.ORANGE;
                break;            
            case ANGUILA:
                c = Color.MAGENTA;
                break;
            default:
                throw new AssertionError(ed.getTipo().name());
        }
        g.setColor(c);
        g.fillRect(x * ED_size.width, y * ED_size.height,
                   ED_size.width, ED_size.height);
    }

    private void cuentaElementosVivos(ElementoDiscreto[][] ed, InformacionPanel cam,
            InformacionPanel jaibas, InformacionPanel anguilas) {
        long camaron = 0;
        long jaiba = 0;
        long anguila = 0;
        
        for (ElementoDiscreto[] ed1 : ed) {
            for (ElementoDiscreto ed11 : ed1) {
                if (ed11.getTipo()== Tipo.CAMARON)
                    camaron++;
                if (ed11.getTipo()== Tipo.JAIBA)
                    jaiba++;
                if (ed11.getTipo()== Tipo.ANGUILA)
                    anguila++;
                
            }
        }
        //Vivos
        cam.setVivos(camaron);
        jaibas.setVivos(jaiba);
        anguilas.setVivos(anguila);
        //Muertos
        cam.setMuertos(NumeroElementos.camaronesTotales - camaron);
        jaibas.setMuertos(NumeroElementos.jaibasTotales - jaiba);
        anguilas.setMuertos(NumeroElementos.anguilasTotales - anguila);
    }

    void setThreadSleep(int tiempo) {
        threadSleep = tiempo;
    }
    
    void pauseExecution(){
        pause = true;
    }
    
    //GENERADORES
    
    private void generaEspecie(Tipo tipo){
        Random rand = new Random();
        int i,j;
        int max = ed.length;
        int min = 0;
        
        i = rand.nextInt((max - min) + 1) + min;
        j = rand.nextInt((max - min) + 1) + min;
        
        Point[] vecinos = new Point[]{          
                new Point (i-1,j-1),
                new Point (i,j-1),
                new Point (i+1,j-1),
                new Point (i+1,j),
                new Point (i+1,j+1),
                new Point (i,j+1),
                new Point (i-1,j+1),
                new Point (i-1,j)
        };
        
        for (Point vecino : vecinos) {
            try{
                if (ed[vecino.x][vecino.y].getTipo() == Tipo.AGUA){
                    if(rand.nextBoolean()){
                        ed[vecino.x][vecino.y].setTipo(tipo);
                        switch(tipo){
                            case AGUA:
                                break;
                            case GARZA:
                                break;
                            case JAIBA:
                                NumeroElementos.jaibasTotales++;
                                break;
                            case CAMARON:
                                NumeroElementos.camaronesTotales++;
                                break;
                            case ANGUILA:
                                NumeroElementos.anguilasTotales++;
                                break;
                            default:
                                throw new AssertionError(tipo.name());
                            
                        }
                    }
                }
            }catch(ArrayIndexOutOfBoundsException e){
                
            }
        }
        
        
    }
    
}
