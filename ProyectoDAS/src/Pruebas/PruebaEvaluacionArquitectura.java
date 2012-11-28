package Pruebas;

import GeometricFiguresManagement.RectangularPrism;
import Interaction.GenerateFiguresCommand;
import SistemaInteraccionInmersiva.Scene;
import UsersManagement.AdapterSimpleOpenNI;
import processing.core.PApplet;
import processing.core.PVector;

public class PruebaEvaluacionArquitectura extends PApplet {

    ControlFrame cf;
    int def;
    public Scene scene;
    public AdapterSimpleOpenNI context;
    
    RectangularPrism rp1;
    RectangularPrism rp2;
    RectangularPrism rp3;

    @Override
    public void setup() {
        //size(400, 400, P3D);
        frameRate(15);
        size(1000, 700, P3D);
        //cf = addControlFrame("extra", 400, 400);
        scene = Scene.getInstance(this);
        scene.activateGeometricFiguresManager();
        rp1 = new RectangularPrism("Opcion1", color(0,255,0), new PVector(0,40,0), new PVector(60,60,20), new PVector(0,0,0), this);
        rp2 = new RectangularPrism("Opcion2", color(0,255,0), new PVector(0,130,0), new PVector(60,60,20), new PVector(0,0,0), this);
        rp3 = new RectangularPrism("Opcion3", color(0,255,0), new PVector(0,210,0), new PVector(60,60,20), new PVector(0,0,0), this);
        scene.getGeometricFiguresManager().addGeometricFigure(rp1);
        scene.getGeometricFiguresManager().addGeometricFigure(rp2);
        scene.getGeometricFiguresManager().addGeometricFigure(rp3);
        
        scene.setCommand(new GenerateFiguresCommand(this));
        context = new AdapterSimpleOpenNI(this);
        
        scene.activateUsersManager(context);
    }

    @Override
    public void draw() {
        background(def);
        
        context.update();
        scene.paint();
        image(context.sceneImage(), width-100, height-80, 100, 80);
        
    }

    @Override
    public void keyPressed() {
        
        switch (keyCode) {
            case LEFT:
                cf.myTextarea.setText("Cambie el texto desde la pruebaArquitectura");
                break;
            
        }
    }
}