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
     * Garza, Jaiba azul, camaron
     */
    public static enum Tipo{
        AGUA, GARZA, JAIBA, CAMARON
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
            case AGUA:
                break;
            case GARZA:
                break;
            case JAIBA:
                break;
            case CAMARON:
                break;
            
            default:
                throw new AssertionError();
        }
    }
}
