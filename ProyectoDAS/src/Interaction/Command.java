package Interaction;

import GeometricFiguresManagement.GeometricFigure;

public interface Command {

    public void execute();
    
    public void setReceiver(GeometricFigure receiver);
    
    public void setReceived(GeometricFigure received);
}