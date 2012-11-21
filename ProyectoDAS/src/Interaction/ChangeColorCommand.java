package Interaction;

import GeometricFiguresManagement.GeometricFigure;

public class ChangeColorCommand implements Command{
    
    private GeometricFigure receiver;
    private GeometricFigure received;
    
    @Override
    public void execute() {
        receiver.bounce(received);
        receiver.changeColor(received);
    }

    @Override
    public void setReceiver(GeometricFigure receiver) {
        this.receiver = receiver;
    }

    @Override
    public void setReceived(GeometricFigure received) {
        this.received = received;
    }
    
}
