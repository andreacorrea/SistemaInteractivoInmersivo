
package pruebascuerpohumano;

import processing.core.PApplet;
import processing.core.PVector;

public class PruebaUsuarioInteractivo extends PApplet{
    Scene scene;
    
    @Override
    public void setup(){
        size(640, 480, P3D);
        frameRate(15);
        scene = new Scene(this);
        scene.activateGeometricFiguresManager();
        
        float speedX= 3;
        float positionX;
        float radio=10;
        for(int i=0; i< 2; i++){
            //speed.mult(i+1);
            positionX = width/5*i;
            speedX *=(i+1);
            
            
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
        background(color(100));
        scene.paint();
    }
}
