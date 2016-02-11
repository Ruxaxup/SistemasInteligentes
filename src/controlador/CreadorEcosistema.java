/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import java.awt.Dimension;
import java.util.Random;
import modelo.ElementoDiscreto;
import modelo.ElementoDiscreto.Tipo;

/**
 *
 * @author Ruxaxup
 */
public class CreadorEcosistema {
    //Porcentaje de cada elemento discreto
    public static double carnivoros;
    public static double herbivoros;
    public static double plantas;
    public static double suelo;
    
    private static long noPlantas;
    private static long noCarnivoros;
    private static long noHerbivoros;
    private static long noSuelo;
    
    static{
        carnivoros = 0;
        herbivoros = 0;
        plantas = 0.5;
        suelo = 0.5;
    }
    
    private static Tipo generateRandomType(){
        Random r = new Random();
        //Maximo numero de tipos (falta desglozar)
        int max = 4;
        int min = 0;
        //Bandera por si el porcentaje es cero
        boolean exists = false;
        
        //Genera un aleatorio que si tenga probabilidad de existir segun las
        //variables de porcentajes
        Tipo tipo = Tipo.PLANTA;
        while(!exists){
            tipo = Tipo.values()[r.nextInt(ElementoDiscreto.Tipo.values().length)];
            switch(tipo){
                //Planta
                case PLANTA:
                    //Probabilidad del ED es mayor a cero
                    if(plantas > 0.0){
                        //Aun se pueden producir
                        if(noPlantas > 0){
                            exists = true;                         
                            noPlantas--;
                        }
                            
                    }
                    break;
                //Suelo
                case SUELO:
                    if(suelo > 0.0){
                        if(noSuelo > 0){
                            exists = true;                         
                            noSuelo--;
                        }
                    }
                    break;
                //Carnivoro
                case TIRANOSAURIO:
                case CARNOTAURO:
                case VELOCIRAPTOR:
                    if(carnivoros > 0.0){
                        if(noCarnivoros > 0){
                            exists = true;                         
                            noCarnivoros--;
                        }
                    }
                    break;
                //Herbivoro
                case DIPLODOCUS:
                case TRICERATOPS:
                case GALLIMIMUS:
                    if(herbivoros > 0.0){
                        if(noHerbivoros > 0){
                            exists = true;                         
                            noHerbivoros--;
                        }
                    }
                    break;
            }
        }
        return tipo;
    }
    
    public static ElementoDiscreto[][] getEcosistema(Dimension size){        
        
        long totalElementos = size.height * size.width;
        //Contador de elementos segun el tipo (falta desglozar carn y herb)
        noPlantas = (long) (totalElementos * plantas);
        noCarnivoros = (long) (totalElementos * carnivoros);
        noHerbivoros = (long) (totalElementos * herbivoros);
        noSuelo = (long) (totalElementos * suelo);
        
        //Inicializacion de la matriz
        ElementoDiscreto [][] elementos = new ElementoDiscreto[size.width][size.height];
        
        //Para cada elemento de la matriz, se genera un aleatorio entre todos
        //los tipos de de elementos y se agregan a la matriz
        for (int i = 0; i < elementos.length; i++) {
            for (int j = 0; j < elementos.length; j++) {
                elementos[i][j] = new ElementoDiscreto(generateRandomType());
            }            
        }
        
        return elementos;
    }
}
