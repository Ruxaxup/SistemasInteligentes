/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.HashMap;
import modelo.ElementoDiscreto;

/**
 *
 * @author Ruxaxup
 */
public interface IElementoDiscreto {
    abstract void reglasInteraccion(ElementoDiscreto[][] vecindario, int i, int j);
    abstract void reglasInteraccion(ElementoDiscreto[][] vecindario, int i, int j, HashMap<Integer,Integer> estadistico);
}
