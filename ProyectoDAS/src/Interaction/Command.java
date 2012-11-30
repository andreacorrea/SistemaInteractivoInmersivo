package Interaction;

import GeometricFiguresManagement.GeometricFigure;

public interface Command {

    public void execute(GeometricFigure receiver);
    
    public void setReceived(GeometricFigure received);
}
