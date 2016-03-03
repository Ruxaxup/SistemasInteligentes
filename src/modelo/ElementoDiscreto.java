/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import interfaces.IElementoDiscreto;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import static modelo.ElementoDiscreto.Tipo.AGUA;
import static modelo.ElementoDiscreto.Tipo.CAMARON;
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
        AGUA, GARZA, JAIBA, CAMARON, ANGUILA
    }
    
    private Tipo tipo;
    private boolean rulesExecuted;
    //Se guardaran 4 posiciones anteriores
    private List<Point> coordenadaAnterior;
    private long ciclos;
    
//JAIBA
    private int direccion;
    boolean hungry;
    int comidos;
    int contDir;

    
    
    public ElementoDiscreto(Tipo tipo){
        this.tipo = tipo;
        hungry = true;
        direccion = -1;
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
            case 4:
                return new Point (i-1,j-1);
            case 0:
                return new Point (i,j-1);
            case 5:
                return new Point (i+1,j-1);
            case 3:
                return new Point (i+1,j);
            case 6:
                return new Point (i+1,j+1);
            case 1:
                return new Point (i,j+1);
            case 7:
                return new Point (i-1,j+1);
            case 2:
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
    public void addLastPosition(Point p){
        if(!coordenadaAnterior.contains(p) && coordenadaAnterior.size() < 15){
            coordenadaAnterior.add(p);
        }            
    }
    public void clearCoordenadaAnterior(){
        coordenadaAnterior.clear();
        coordenadaAnterior = new ArrayList<>();
    }
    
    public boolean moverCamaron(ElementoDiscreto[][] vecindario, int i, int j){
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
            if(contDir == 5){
                direccion = -1;
                contDir = 0;
            }
            if(direccion != -1){
                vecino = direccion;
                contDir++;
            }
            if(visitados.contains(vecino)){
                continue;
            }
            try{
                posVecino = getVecino(vecindario, i, j, vecino); 
                direccion = vecino;
                if(coordenadaAnterior.contains(posVecino)){
                    visitados.add(new Integer(vecino));
                    continue;
                }
                vecinoED = vecindario[posVecino.x][posVecino.y];
                if(vecinoED.getTipo() == AGUA){
                    //Intercambio
                    aux = vecindario[i][j];
                    vecindario[i][j] = vecinoED;
                    vecindario[posVecino.x][posVecino.y] = aux;
                    posible = true;
                }else{
                    visitados.add(new Integer(vecino));
                    contDir = 0;
                    //resetear contador de direccion
                }                
            }catch(ArrayIndexOutOfBoundsException e){
                posible = false;
                visitados.add(new Integer(vecino));
                //resetear contador de direccion
                contDir = 0;
            }
        }   
        return posible;
    }
    
    public boolean moverJaiba(ElementoDiscreto[][] vecindario, int i, int j){
        boolean posible = false;
        Random rand = new Random();
        //int randomNum = rand.nextInt((max - min) + 1) + min;
        Point posVecino;
        
        ElementoDiscreto vecinoED, aux;
        Set<Integer> visitados = new TreeSet<>();   

        int min = 0;
        int max = 3;
        
        while(!posible && visitados.size() < 4){
            int vecino = rand.nextInt((max - min) + 1) + min;   
            if(contDir == 10){
                direccion = -1;
                contDir = 0;
            }
            if(direccion != -1){
                vecino = direccion;
                contDir++;
            }
            if(visitados.contains(vecino)){
                continue;
            }
            try{
                posVecino = getVecino(vecindario, i, j, vecino); 
                direccion = vecino;
                if(coordenadaAnterior.contains(posVecino)){
                    visitados.add(new Integer(vecino));
                    continue;
                }
                vecinoED = vecindario[posVecino.x][posVecino.y];
                
                if(vecinoED.getTipo() == AGUA){
                    //Intercambio
                    aux = vecindario[i][j];
                    vecindario[i][j] = vecinoED;
                    vecindario[posVecino.x][posVecino.y] = aux;
                    posible = true;
                }else{
                    visitados.add(new Integer(vecino));
                    contDir = 0;
                    //resetear contador de direccion
                }                
            }catch(ArrayIndexOutOfBoundsException e){
                posible = false;
                visitados.add(new Integer(vecino));
                //resetear contador de direccion
                contDir = 0;
            }
        }       
        
        return posible;
    }
    
    public boolean comerCamaron(ElementoDiscreto[][] vecindario, int i, int j){
        Point posVecinoCom;
        ElementoDiscreto vecinoEDCom;
        boolean posible = false;
        for(int k=0;k<7;k++){
            try{            
                posVecinoCom = getVecino(vecindario, i, j, k); 
                vecinoEDCom = vecindario[posVecinoCom.x][posVecinoCom.y];
            
                if(vecinoEDCom.getTipo() == CAMARON){
                    vecinoEDCom.setTipo(AGUA);
                    posible = true;
                    break;
                } 
            }catch(ArrayIndexOutOfBoundsException e){

            }
        }
        return posible;
    }
    
    private void mataJaiba(){
        setTipo(AGUA);
        ciclos = 0;
    }
    
    @Override
    public void reglasInteraccion(ElementoDiscreto[][] vecindario, int i, int j) {
        switch (tipo) {
            case AGUA:
                break;
            case GARZA:
                break;
            case JAIBA:
                if(ciclos >= 480){
                    //muere
                    mataJaiba();
                }else if(ciclos >= 16){
                    hungry = true;
                }
                if(hungry){                    
                    if(moverJaiba(vecindario, i, j)){
                        addLastPosition(new Point(i,j));
                    }                
                    if(comerCamaron(vecindario, i, j)){
                        hungry = false;
                        ciclos = 0;
                    }
                }
                ciclos++;
                break;
            case CAMARON:
                if(moverCamaron(vecindario, i, j)){
                    vecindario[i][j].addLastPosition(new Point(i,j));
                }
                ciclos++;
                break;
            case ANGUILA:
                ciclos++;
                break;
            
            default:
                throw new AssertionError();
        }
        rulesExecuted = true;        
    }
}
