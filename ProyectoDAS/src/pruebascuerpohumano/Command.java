package pruebascuerpohumano;

public interface Command {

    public void execute();
    
    public void setReceiver(GeometricFigure receiver);
    
    public void setReceived(GeometricFigure received);
}
