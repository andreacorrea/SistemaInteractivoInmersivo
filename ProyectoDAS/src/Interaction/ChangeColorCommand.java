package Interaction;

import GeometricFiguresManagement.GeometricFigure;

public class ChangeColorCommand implements Command{
    
    private GeometricFigure received;
    
    @Override
    public void execute(GeometricFigure receiver) {
        receiver.bounce(received);
        receiver.changeColor(received);
    }

    @Override
    public void setReceived(GeometricFigure received) {
        this.received = received;
    }
    
}
