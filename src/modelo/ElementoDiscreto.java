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
        LARVA_OBRERA, LARVA_ZANGANO, LARVA_REINA,
        ABEJA_ZANGANO, ABEJA_REINA,
        ABEJA_OBRERA, ABEJA_NODRIZA, ABEJA_ALMACENERA, ABEJA_CERERA, ABEJA_CENTINELA,
        ABEJA_LIBADORA
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
            case LARVA_OBRERA:
                
                break;
            case LARVA_ZANGANO:
                
                break;
            case LARVA_REINA:
                
                break;
            case ABEJA_ZANGANO:
                
                break;
            case ABEJA_REINA:
                
                break;
            case ABEJA_OBRERA:
                
                break;
            case ABEJA_NODRIZA:
                
                break;
            case ABEJA_ALMACENERA:
                
                break;
            case ABEJA_CERERA:
                
                break;
            case ABEJA_CENTINELA:
                
                break;    
            case ABEJA_LIBADORA:
                
                break;
            default:
                throw new AssertionError();
        }
    }
}
