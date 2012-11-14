/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebascuerpohumano;
import processing.core.*;

/**
 *
 * @author samuel
 */
public class SkecthPruebaColisiones extends PApplet {
    
    protected int framerate = 30;
    protected float friction = 0.95f;
    protected float strengthCoef = 0.5f;
    
    GeometricFigure[] figs = {
        //String pname, int pcolor, PVector ppos, float pr, PVector pvel, PApplet pparent
        new Ball("b1", 255, new PVector(0,0,0), 40f, new PVector(0,0,0), this),
        new Ball("b2", 255, new PVector(100,100,0), 30f, new PVector(2,2,0), this),
        new Ball("b3", 255, new PVector(400,400,0), 50f, new PVector(2,1,0), this),
        new Ball("b4", 255, new PVector(600,100,0), 60f, new PVector(1,2,0), this),
        new Ball("b5", 255, new PVector(700,500,0), 80f, new PVector(1,1,0), this),
    };
    
    PVector[] vHistPos = {
        new PVector(0,0,0),
        new PVector(0,0,0)
    };
        
    @Override
    public void setup(){
        frameRate(framerate);
        size(800,600,P3D);  
        smooth();
        lights();
        noStroke();
        figs[0].setMass(1000f);
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
                    figs[i].checkCollision((Ball)figs[j]);
                }
            }
        }
    }
    
}