/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import interfaces.IElementoDiscreto;
import java.awt.Point;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import static modelo.ElementoDiscreto.Tipo.AGUA;

/**
 *
 * @author Ruxaxup
 */
public class ElementoDiscreto implements IElementoDiscreto{
    
    /**
     * Tipos de elementos discretos en el ecosistema
     * 
     * Garza, Jaiba azul, camaron
     */
    public static enum Tipo{
        AGUA, GARZA, JAIBA, CAMARON
    }
    
    private Tipo tipo;
    
    public ElementoDiscreto(Tipo tipo){
        this.tipo = tipo;
    }
    
    public void setTipo(Tipo tipo){
        this.tipo = tipo;
    }
    
    public Tipo getTipo(){
        return tipo;
    }
    
    public Point getVecino(ElementoDiscreto[][] vecindario, int i, int j, int posicion){
        
        switch (posicion){
            case 0:
                return new Point (i-1,j-1);
            case 1:
                return new Point (i,j-1);
            case 2:
                return new Point (i+1,j-1);
            case 3:
                return new Point (i-1,j);
            case 4:
                return new Point (i+1,j+1);
            case 5:
                return new Point (i,j+1);
            case 6:
                return new Point (i-1,j+1);
            case 7:
                return new Point (i-1,j);
        }
        return null;
    }
     
    public void mover(ElementoDiscreto[][] vecindario, int i, int j){
        Random rand = new Random();
        //int randomNum = rand.nextInt((max - min) + 1) + min;
        Point posVecino;
        ElementoDiscreto vecinoED;
        Set<Integer> visitados = new TreeSet<>();
        int min = 0;
        int max = 7;
        boolean posible = false;
        while(!posible && visitados.size() < 8){
            int vecino = rand.nextInt((max - min) + 1) + min;
            if(visitados.contains(vecino)){
                continue;
            }
            try{
                posVecino = getVecino(vecindario, i, j, vecino); 
                vecinoED = vecindario[posVecino.x][posVecino.y];
                if(vecinoED.getTipo() == AGUA){
                    vecindario[posVecino.x][posVecino.y].setTipo(Tipo.CAMARON);
                    vecindario[i][j].setTipo(AGUA);
                    posible = true;
                }else{
                    visitados.add(new Integer(vecino));
                }                
            }catch(ArrayIndexOutOfBoundsException e){
                posible = false;
                visitados.add(new Integer(vecino));
            }
        }        
    }
    
    @Override
    public void reglasInteraccion(ElementoDiscreto[][] vecindario, int i, int j) {
        switch (tipo) {
            case AGUA:
                break;
            case GARZA:
                break;
            case JAIBA:
                break;
            case CAMARON:
                mover(vecindario, i, j);
                break;
            
            default:
                throw new AssertionError();
        }
    }
}
