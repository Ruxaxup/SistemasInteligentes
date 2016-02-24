package utils;


import java.awt.Dimension;
import java.awt.Point;
import modelo.ElementoDiscreto;

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
    
    
}
