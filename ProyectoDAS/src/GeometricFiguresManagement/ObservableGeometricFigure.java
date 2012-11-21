/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GeometricFiguresManagement;

import Interaction.Command;

/**
 *
 * @author Andrea
 */
public interface ObservableGeometricFigure {
    
    public abstract void addObserver(Object obj);
    public abstract void removeObserver(Object obj);
    public abstract void notifyAllObservers(Command command);
}
