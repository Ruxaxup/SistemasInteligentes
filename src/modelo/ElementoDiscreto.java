/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import interfaces.IElementoDiscreto;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import static modelo.ElementoDiscreto.Tipo.AGUA;

/**
 *
 * @author Ruxaxup
 */
public class ElementoDiscreto implements IElementoDiscreto{

    @Override
    public void reglasInteraccion(ElementoDiscreto[][] vecindario, int i, int j) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Tipos de elementos discretos en el ecosistema
     * 
     * Garza, Jaiba azul, camaron
     */
    public static enum Tipo{
        AGUA, GARZA, JAIBA, CAMARON
    }
    
    private Tipo tipo;
    private boolean rulesExecuted;
    //Se guardaran 4 posiciones anteriores
    private List<Integer> pastPositions;
    private List<Point> coordenadaAnterior;
    
    public ElementoDiscreto(Tipo tipo){
        this.tipo = tipo;
        pastPositions = new ArrayList<>();
        coordenadaAnterior = new ArrayList<>();
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
                return new Point (i+1,j);
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
     
    public boolean areRulesExecuted(){
        return rulesExecuted;
    }
    public void clearRulesExecuted(){
        rulesExecuted = false;
    }
    public void clearPastPositions(){
        pastPositions.clear();
    }
    public void addLastPosition(Point p){
        if(!coordenadaAnterior.contains(p)){
            coordenadaAnterior.add(p);
        }            
    }
    public void clearCoordenadaAnterior(){
        coordenadaAnterior.clear();
    }
    
    public boolean mover(ElementoDiscreto[][] vecindario, int i, int j, HashMap<Integer,Integer> estadistico){
        Random rand = new Random();
        //int randomNum = rand.nextInt((max - min) + 1) + min;
        Point posVecino;
        ElementoDiscreto vecinoED, aux;
        Set<Integer> visitados = new TreeSet<>();        
        int min = 0;
        int max = 7;
        boolean posible = false;
        
        while(!posible && visitados.size() < 8){
            int vecino = rand.nextInt((max - min) + 1) + min;
            estadistico.put(vecino,estadistico.get(vecino) + 1);
            
            if(pastPositions.contains(vecino)){
                visitados.add(vecino);
                continue;
            }
            if(visitados.contains(vecino)){
                continue;
            }
            try{
                posVecino = getVecino(vecindario, i, j, vecino); 
                if(coordenadaAnterior.contains(posVecino)){
                    visitados.add(new Integer(vecino));
                    continue;
                }
                vecinoED = vecindario[posVecino.x][posVecino.y];
                if(vecinoED.getTipo() == AGUA){
                    estadistico.put(vecino,estadistico.get(vecino) + 1);
                    //Intercambio
                    aux = vecindario[i][j];
                    vecindario[i][j] = vecinoED;
                    vecindario[posVecino.x][posVecino.y] = aux;
                    //Agrega la posicion a la lista
                    if(!pastPositions.contains(vecino) && pastPositions.size() < 4)
                        pastPositions.add(vecino);
                    posible = true;
                }else{
                    visitados.add(new Integer(vecino));
                }                
            }catch(ArrayIndexOutOfBoundsException e){
                posible = false;
                visitados.add(new Integer(vecino));
            }
        }   
        return posible;
    }
    
    @Override
    public void reglasInteraccion(ElementoDiscreto[][] vecindario, int i, int j, HashMap<Integer,Integer> estadistico) {
        switch (tipo) {
            case AGUA:
                break;
            case GARZA:
                break;
            case JAIBA:
                break;
            case CAMARON:
                if(mover(vecindario, i, j, estadistico)){
                    vecindario[i][j].addLastPosition(new Point(i,j));
                }
                rulesExecuted = true;
                break;
            
            default:
                throw new AssertionError();
        }
    }
}
