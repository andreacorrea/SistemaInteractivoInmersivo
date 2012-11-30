package Pruebas;

import GeometricFiguresManagement.GeometricFiguresManager;
import GeometricFiguresManagement.RectangularPrism;
import Interaction.GenerateFiguresCommand;
import SistemaInteraccionInmersiva.Scene;
import UsersManagement.AdapterSimpleOpenNI;
import processing.core.PApplet;
import processing.core.PVector;

public class PruebaEvaluacionArquitectura extends PApplet {

    public Scene scene;
    public AdapterSimpleOpenNI context;
    
    RectangularPrism rp1;
    RectangularPrism rp2;
    RectangularPrism rp3;
    
    boolean perdio=false;
    boolean inicio = false;

    @Override
    public void setup() {
        //size(400, 400, P3D);
        frameRate(15);
        //size(1000, 700, P3D);
        size(640, 700, P3D);
        
        scene = Scene.getInstance(this);
        scene.activateGeometricFiguresManager();
        GeometricFiguresManager.getInstance(this).setGravity(0);
        rp1 = new RectangularPrism("Opcion1", color(0,255,0), new PVector(0,240,0), new PVector(60,60,20), new PVector(0,0,0), this);
        rp2 = new RectangularPrism("Opcion2", color(0,255,0), new PVector(0,330,0), new PVector(60,60,20), new PVector(0,0,0), this);
        rp3 = new RectangularPrism("Opcion3", color(0,255,0), new PVector(0,410,0), new PVector(60,60,20), new PVector(0,0,0), this);
        scene.getGeometricFiguresManager().addGeometricFigure(rp1);
        scene.getGeometricFiguresManager().addGeometricFigure(rp2);
        scene.getGeometricFiguresManager().addGeometricFigure(rp3);
        
        scene.setCollisionCommand(new GenerateFiguresCommand(this));
        context = new AdapterSimpleOpenNI(this);
        
        scene.activateUsersManager(context);
    }

    @Override
    public void draw() {
        
        context.update();
        scene.paint();
        image(context.sceneImage(), width-150, height-80, 150, 80);
        if(inicio && GeometricFiguresManager.getInstance(this).getGeometricFigures().isEmpty()){
            perdio = true;
            System.out.println("perdiste =(");
            exit();
        }
    }

    public void setInicio(boolean inicio) {
        this.inicio = inicio;
    }

}