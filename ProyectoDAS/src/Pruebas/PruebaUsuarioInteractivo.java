package Pruebas;

import GeometricFiguresManagement.AdapterSimpleOpenNI;
import GeometricFiguresManagement.RectangularPrism;
import GeometricFiguresManagement.Ball;
import Interaction.BounceCommand;
import Interaction.ChangeColorCommand;
import Interaction.Command;
import SistemaInteraccionInmersiva.Scene;
import UsersManagement.BuildingSkeletonVolumeStrategy;
import UsersManagement.CubesSkeletonVolume;
import UsersManagement.SpheresSkeletonVolume;
import processing.core.PApplet;
import processing.core.PVector;

public class PruebaUsuarioInteractivo extends PApplet{
    Scene scene;
    AdapterSimpleOpenNI context;
    
    @Override
    public void setup(){
        //size(640, 480, P3D);
        size(1000, 800, P3D);
        frameRate(15);
        context = new AdapterSimpleOpenNI(this);
        
        scene = Scene.getInstance(this);
        scene.activateGeometricFiguresManager();
        scene.activateUsersManager(context);
        
        float speedX= 3;
        float positionX;
        float radio=15;
        for(int i=0; i< 2; i++){
            //speed.mult(i+1);
            positionX = width/5*i;
            speedX += 0.7;
            
            if((int)random(2) == 1){
            
                Ball ball= new Ball("ball-" + i, 200, new PVector(positionX, height/2, 0), radio+(radio*i), new PVector(speedX, 0,0), this);
                scene.getGeometricFiguresManager().addGeometricFigure(ball);
            }else{
                RectangularPrism rectPrism= new RectangularPrism("rect-" + i, 200, new PVector(positionX, height/2, 0), new PVector(radio+(radio*i), radio+(radio*i), radio+(radio*i)), new PVector(speedX, 0,0), this);
                scene.getGeometricFiguresManager().addGeometricFigure(rectPrism);
            }
        }
    }
    
    @Override
    public void draw(){
        background(color(255, 0, 0));
        // update the camera
        context.update();
        scene.paint();
        // draw scene Image
        image(context.sceneImage(), width-100, height-50, 100, 50);
        //image(context.sceneImage(), 0, 0);
    }
    
    @Override
    public void keyPressed() {
        switch (key) {
            case '1':
                changeStrategyToSpheres();
                break;

            case '2':
                changeStrategyToCubes();
                break;
                
            case '3':
                changeCommandToBounce();
                break;    
                
            case '4':
                changeCommandToChangeColor();
                break;    
        }

    }

    public void changeStrategyToSpheres() {
        BuildingSkeletonVolumeStrategy buildingSkeletonVolumeStrategy = new SpheresSkeletonVolume();
        scene.getUsersManager().setbuildingSkeletonVolumeStrategy(buildingSkeletonVolumeStrategy);
    }

    public void changeStrategyToCubes() {
        BuildingSkeletonVolumeStrategy buildingSkeletonVolumeStrategy = new CubesSkeletonVolume();
        scene.getUsersManager().setbuildingSkeletonVolumeStrategy(buildingSkeletonVolumeStrategy);

    }

    private void changeCommandToBounce() {
        Command command = new BounceCommand();
        scene.setCommand(command);
    }

    private void changeCommandToChangeColor() {
        Command command = new ChangeColorCommand();
        scene.setCommand(command);
    }
}
