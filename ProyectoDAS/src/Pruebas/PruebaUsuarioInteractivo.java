package Pruebas;

import GeometricFiguresManagement.Ball;
import GeometricFiguresManagement.RectangularPrism;
import Interaction.BounceCommand;
import Interaction.ChangeColorCommand;
import Interaction.Command;
import SistemaInteraccionInmersiva.Scene;
import UsersManagement.AdapterSimpleOpenNI;
import UsersManagement.BuildingSkeletonVolumeStrategy;
import UsersManagement.Cubes5SkeletonVolume;
import UsersManagement.CubesSkeletonVolume;
import UsersManagement.Spheres5SkeletonVolume;
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
        frameRate(30);
        context = new AdapterSimpleOpenNI(this);
        
        scene = Scene.getInstance(this);
        scene.activateGeometricFiguresManager();
        scene.activateUsersManager(context);
        
        float speedX= 3;
        float positionX;
        float radio=40;
        for(int i=0; i < 3; i++){
            //speed.mult(i+1);
            positionX = width/5*i;
            speedX += 0.7;
            
            if((int)random(2) == 1){
                Ball ball= new Ball("ball-" + i, 200, new PVector(positionX, height/2, 0), radio+(10*i), new PVector(speedX, 0,0), this);
                scene.getGeometricFiguresManager().addGeometricFigure(ball);
            }else{
                RectangularPrism rectPrism= new RectangularPrism("rect-" + i, 200, new PVector(positionX, height/2, 0), new PVector(radio+(10*i), radio+(10*i), radio+(10*i)), new PVector(speedX, 0,0), this);
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
                changeStrategyTo5Spheres();
                break;
                
            case '3':
                changeStrategyToCubes();
                break;
            
            case '4':
                changeStrategyTo5Cubes();
                break;
                        
            case '5':
                changeCommandToBounce();
                break;    
                
            case '6':
                changeCommandToChangeColor();
                break;    
        }

    }

    public void changeStrategyToSpheres() {
        BuildingSkeletonVolumeStrategy buildingSkeletonVolumeStrategy = new SpheresSkeletonVolume();
        scene.getUsersManager().setbuildingSkeletonVolumeStrategy(buildingSkeletonVolumeStrategy);
    }
    
    private void changeStrategyTo5Spheres() {
        BuildingSkeletonVolumeStrategy buildingSkeletonVolumeStrategy = new Spheres5SkeletonVolume();
        scene.getUsersManager().setbuildingSkeletonVolumeStrategy(buildingSkeletonVolumeStrategy);
    }
    
    private void changeStrategyTo5Cubes() {
        BuildingSkeletonVolumeStrategy buildingSkeletonVolumeStrategy = new Cubes5SkeletonVolume();
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
