package Interaction;

import GeometricFiguresManagement.GeometricFigure;
import SistemaInteraccionInmersiva.Scene;
import processing.core.PApplet;

public class RemoveCommand implements Command{
    Scene scene;
    GeometricFigure received;
    
    public RemoveCommand(PApplet p){
        scene = Scene.getInstance(p);
    }
    
    @Override
    public void execute(GeometricFigure received) {
        scene.getGeometricFiguresManager().removeGeometricFigure(received);
    }

    @Override
    public void setReceived(GeometricFigure received) {
        //this.received=received;
    }
    
}
