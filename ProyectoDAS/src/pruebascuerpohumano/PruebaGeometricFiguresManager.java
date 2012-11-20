/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebascuerpohumano;

import processing.core.PApplet;
import processing.core.PVector;

/**
 *
 * @author Andrea
 */
public class PruebaGeometricFiguresManager extends PApplet{
    Scene scene;
    
    @Override
    public void setup(){
        size(640, 480, P3D);
        frameRate(15);
        
        scene = Scene.getInstance(this);
        scene.activateGeometricFiguresManager();
        
        
        float speedX= 3;
        float positionX;
        float radio=7;
        for(int i=0; i< 10; i++){
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
        
        
        scene.paint();
        
    }
}
