/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebascuerpohumano;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author Andrea
 */
public interface ObservableGeometricFigure {
    
    public abstract void addObserver(Object obj);
    public abstract void removeObserver(Object obj);
    
    
}
