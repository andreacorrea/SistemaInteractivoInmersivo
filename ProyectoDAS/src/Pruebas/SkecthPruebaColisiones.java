/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pruebas;
import GeometricFiguresManagement.GeometricFigure;
import GeometricFiguresManagement.RectangularPrism;
import GeometricFiguresManagement.Ball;
import processing.core.*;

/**
 *
 * @author samuel
 */
public class SkecthPruebaColisiones extends PApplet {
    
    protected int framerate = 30;
    protected float friction = 1f;
    protected float strengthCoef = 0.8f;
    public boolean checar = false;
    
    Ball ballPrototype = new Ball("b3", color(255,255,255), new PVector(400,400,0), 50f, new PVector(2,1,0), this);
    RectangularPrism cubePrototype = new RectangularPrism("c1", color(255,255,255), new PVector(600,600,0), new PVector(70,70,70), new PVector(10,10,0), this);
    
    GeometricFigure[] figs = {
        //new RectangularPrism("c1", color(255,255,255), new PVector(600,600,0), new PVector(90,90,90), new PVector(20,20,0), this),
        new Ball("b1", color(255,255,255), new PVector(600,100,0), 40f, new PVector(1,2,0), this),
        ballPrototype.cloneFig(),
        ballPrototype.cloneFig(),
        cubePrototype.cloneFig(),
        cubePrototype.cloneFig(),
    };
    
    PVector[] vHistPos = {
        new PVector(0,0,0),
        new PVector(0,0,0)
    };
        
    @Override
    public void setup(){
        frameRate(framerate);
        size(1100,700,P3D);  
        smooth();
        lights();
        noStroke();
        figs[0].setMass(1000f);
        figs[1].setPos(new PVector(0,600,0));
        figs[3].setPos(new PVector(900,0,0));
    }

    @Override
    public void draw(){
        background(51);
        stroke(204);
        figs[0].setPosX(mouseX);
        figs[0].setPosY(mouseY);
        for(int i=0; i<figs.length; i++){
            if(i == 0){
                vHistPos[0].x = vHistPos[1].x;
                vHistPos[0].y = vHistPos[1].y;
                vHistPos[1].x = mouseX;
                vHistPos[1].y = mouseY;
                figs[0].setVelX( (vHistPos[1].x - vHistPos[0].x) * strengthCoef );
                figs[0].setVelY( (vHistPos[1].y - vHistPos[0].y) * strengthCoef );
            } else {
                figs[i].update(friction);
            }
            figs[i].paint();
            figs[i].checkBoundaryCollision();
            for(int j=0; j<figs.length; j++){
                if(j != i){
                    figs[i].checkCollision(figs[j]);
                }
            }
        }
    }
    
}