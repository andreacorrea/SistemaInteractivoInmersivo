
package pruebascuerpohumano;

import SimpleOpenNI.SimpleOpenNI;
import processing.core.PApplet;
import processing.core.PVector;

public class PruebaUsuarioInteractivo extends PApplet{
    Scene scene;
    SimpleOpenNI context;
    
    @Override
    public void setup(){
        size(640, 480, P3D);
        frameRate(15);
        context = new SimpleOpenNI(this);
        
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
            
                Ball ball= new Ball("ball-" + i, 0, new PVector(positionX, height/2, 0), radio+(radio*i), new PVector(speedX, 0,0), this);
                scene.getGeometricFiguresManager().addGeometricFigure(ball);
            }else{
                RectangularPrism rectPrism= new RectangularPrism("rect-" + i, 0, new PVector(positionX, height/2, 0), new PVector(radio+(radio*i), radio+(radio*i), radio+(radio*i)), new PVector(speedX, 0,0), this);
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
}
