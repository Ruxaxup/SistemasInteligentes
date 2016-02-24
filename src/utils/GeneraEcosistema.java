package utils;


import java.awt.Dimension;
import java.awt.Point;
import java.util.Random;
import modelo.ElementoDiscreto;
import modelo.ElementoDiscreto.Tipo;
import vista.EscenarioPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ruxaxup
 */
public class GeneraEcosistema {
    public static Point spawnJaiba;
    public static Point spawnCamaron;
    public static Point spawnGarza;
    
    public static ElementoDiscreto[][] test(Dimension size){
        ElementoDiscreto[][] ecosistema = new ElementoDiscreto[size.width][size.height];
        for (int i = 0; i < ecosistema.length; i++) {
            for (int j = 0; j < ecosistema.length; j++) {
                ecosistema[i][j] = new ElementoDiscreto(ElementoDiscreto.Tipo.AGUA);                
            }
        }
        return ecosistema;
    }
    
    public static void GeneraEcosistema(Tipo tipo, EscenarioPanel ep ,int cuadrante, double percent){
        int minX = 0, minY = 0, maxX = 0, maxY = 0;
        int gap = (int) ((ep.ed.length/2)*.2);
        int center = (ep.ed.length/2);
        int area, cantED;
        //int random;
        switch (cuadrante){
            case 1:
                minX = minY = 0;
                maxX = maxY = center -gap;
                break;
            case 2:
                minX =  center + gap;
                minY = 0;
                maxX = ep.ed.length - 1;
                maxY = center - gap;
                break;
            case 3:
                minX = 0;
                minY = center + gap;
                maxX = center - gap;
                maxY = ep.ed.length - 1;
                break;
            case 4:
                minX = minY = center + gap;
                maxX = maxY = ep.ed.length - 1;
                break;
        }
        area = (maxX - minX)*(maxY - minY);
        cantED = (int) (area * percent);
        while(cantED > 0){
            Point p = generaED(minX, maxX, minY, maxY, tipo, ep.ed);
            ep.ed[p.x][p.y].setTipo(tipo);
            cantED--;
        }
    }
    
    public static void generaAgua(ElementoDiscreto[][] ed){
        for (ElementoDiscreto[] elementoDiscretos : ed) {
            for (ElementoDiscreto elementoDiscreto : elementoDiscretos) {
                elementoDiscreto = new ElementoDiscreto(Tipo.AGUA);
            }
        }
    }

    private static Point generaED(int minX, int maxX, int minY, int maxY, Tipo tipo, ElementoDiscreto[][] ed) {
        boolean flag = false;
        int i = 0, j = 0;
        Random rand = new Random();
        while (!flag){
            //int randomNum = rand.nextInt((max - min) + 1) + min;
            i = rand.nextInt((maxX - minX) + 1) + minX;
            j = rand.nextInt((maxY - minY) + 1) + minY;
            if (ed[i][j].getTipo() == Tipo.AGUA){
                ed[i][j].setTipo(tipo);
                flag = true;
            }
                
        }
        return new Point (i, j);
    }
    
    
}
