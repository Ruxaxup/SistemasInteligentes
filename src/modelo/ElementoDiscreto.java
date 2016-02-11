/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import interfaces.IElementoDiscreto;

/**
 *
 * @author Ruxaxup
 */
public class ElementoDiscreto implements IElementoDiscreto{
    
    /**
     * Tipos de elementos discretos en el ecosistema
     * 
     * Carnivoros: Tirsanosaurio, Carnotauro, Velociraptor
     * Herviboros: Diplodocus, Triceratops, Gallimimus
     * Plantas
     */
    public static enum Tipo{
        PLANTA, SUELO, TIRANOSAURIO, CARNOTAURO, VELOCIRAPTOR, DIPLODOCUS,
        TRICERATOPS, GALLIMIMUS
    }
    
    private Tipo tipo;
    
    public ElementoDiscreto(Tipo tipo){
        this.tipo = tipo;
    }
    
    public Tipo getTipo(){
        return tipo;
    }
    
    @Override
    public void reglasInteraccion() {
        switch (tipo) {
            case PLANTA:
                
                break;
            case SUELO:
                
                break;
            case TIRANOSAURIO:
                
                break;
            case CARNOTAURO:
                
                break;
            case VELOCIRAPTOR:
                
                break;
            case DIPLODOCUS:
                
                break;
            case TRICERATOPS:
                
                break;
            case GALLIMIMUS:
                
                break;
            default:
                throw new AssertionError();
        }
    }
}
