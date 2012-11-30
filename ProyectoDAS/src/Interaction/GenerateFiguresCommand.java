package Interaction;

import GeometricFiguresManagement.Ball;
import GeometricFiguresManagement.GeometricFigure;
import GeometricFiguresManagement.GeometricFiguresManager;
import GeometricFiguresManagement.RectangularPrism;
import Pruebas.PruebaEvaluacionArquitectura;
import SistemaInteraccionInmersiva.Scene;
import processing.core.PApplet;
import processing.core.PVector;

public class GenerateFiguresCommand implements Command {
    Scene scene;
    GeometricFigure received;
    
    public GenerateFiguresCommand(PApplet p){
        scene = Scene.getInstance(p);
    }
    
    @Override
    public void execute(GeometricFigure receiver) {
        //Se eliminan las figuras que representan las opciones
        GeometricFiguresManager gfm = GeometricFiguresManager.getInstance(scene.getParent());
        gfm.clear();
        if(receiver.getName().equals("Opcion1")){
            pruebaGeometriFigures(5, 35, 1);
        }else if(receiver.getName().equals("Opcion2")){
            pruebaGeometriFigures(10, 35, 1);
        }else if(receiver.getName().equals("Opcion3")){
            pruebaGeometriFigures(15, 35, 1);
        }
        scene.setCollisionCommand(new BounceCommand());
        scene.getGeometricFiguresManager().setGravity(0.1f);
        scene.getGeometricFiguresManager().setCollisionLowerBoundaryCommand(new RemoveCommand(scene.getParent()));
        scene.getGeometricFiguresManager().setUpperLimit(200);
        ((PruebaEvaluacionArquitectura)scene.getParent()).setInicio(true);
    }

    @Override
    public void setReceived(GeometricFigure received) {
        this.received = received;
    }
    
    public void pruebaGeometriFigures(int num, float radio, float speed) {
        float positionX;
        float positionY;
        int color = scene.getParent().color(0, 0, 255);
        for (int i = 0; i < num; i++) {
            //speed.mult(i+1);
            positionX = scene.getParent().width / num * i;
            positionY = 0;

            if ((int) scene.getParent().random(2) == 1) {

                Ball ball = new Ball("ball-" + i, color, new PVector(positionX, positionY, 0), radio, new PVector(0, speed, 0), scene.getParent());
                ball.setMass(20);
                scene.getGeometricFiguresManager().addGeometricFigure(ball);
            } else {
                RectangularPrism rectPrism = new RectangularPrism("rect-" + i, color, new PVector(positionX, positionY, 0), new PVector(radio, radio, radio), new PVector(speed, speed, 0), scene.getParent());
                rectPrism.setMass(20);
                scene.getGeometricFiguresManager().addGeometricFigure(rectPrism);
            }
        }
    }
}
