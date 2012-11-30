package GeometricFiguresManagement;

import Interaction.Command;

public interface ObservableGeometricFigure {
    
    public abstract void addObserver(Object obj);
    public abstract void removeObserver(Object obj);
    public abstract void notifyAllObservers(Command command);
}
