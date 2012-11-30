/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pruebas;
import GeometricFiguresManagement.Ball;
import GeometricFiguresManagement.GeometricFigure;
import GeometricFiguresManagement.RectangularPrism;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import processing.core.*;

public class SkecthPruebaColisiones extends PApplet {
    
    protected int framerate = 30;
    protected float friction = 0.93f;
    protected float gravity = 0.1f;
    protected float strengthCoef = 0.8f;
    public boolean checar = false;
    
    FileWriter fstream;
    BufferedWriter out;
    long tiempoInicio;
    
    Ball ballPrototype = new Ball("b3", color(255,255,255), new PVector(400,400,0), 35f, new PVector(2,1,0), this);
    RectangularPrism cubePrototype = new RectangularPrism("c1", color(255,255,255), new PVector(600,600,0), new PVector(70,70,70), new PVector(10,10,0), this);
    GeometricFigure[] figs = new GeometricFigure[100];
    /*GeometricFigure[] figs = {
        new Ball("b1", color(255,255,255), new PVector(600,100,0), 40f, new PVector(1,2,0), this),
        ballPrototype.cloneFig(),
        ballPrototype.cloneFig(),
        cubePrototype.cloneFig(),
        cubePrototype.cloneFig(),
    };*/
    
    PVector[] vHistPos = {
        new PVector(0,0,0),
        new PVector(0,0,0)
    };
        
    @Override
    public void setup(){
        for (int i=0; i<100; i++){
            figs[i]=ballPrototype.cloneFig();
            figs[i].setPos(new PVector(i+i*1/100, i+i*1/100, 0));
            figs[i].setName("ball_" + i);
        }
        frameRate(framerate);
        size(1100,700,P3D);  
        smooth();
        lights();
        noStroke();
        figs[0].setMass(1000f);
        figs[1].setPos(new PVector(0,600,0));
        figs[3].setPos(new PVector(900,0,0));
        try {
            fstream = new FileWriter("Prueba_14.txt");
        } catch (IOException ex) {
            Logger.getLogger(SkecthPruebaColisiones.class.getName()).log(Level.SEVERE, null, ex);
        }
        out = new BufferedWriter(fstream);
        tiempoInicio = System.currentTimeMillis();
    }

    @Override
    public void draw(){
        background(51);
        stroke(204);
        figs[0].setPosX(mouseX);
        figs[0].setPosY(mouseY);
        for(int i=0; i<figs.length; i++){
            /*if(i == 0){
                vHistPos[0].x = vHistPos[1].x;
                vHistPos[0].y = vHistPos[1].y;
                vHistPos[1].x = mouseX;
                vHistPos[1].y = mouseY;
                figs[0].setVelX( (vHistPos[1].x - vHistPos[0].x) * strengthCoef );
                figs[0].setVelY( (vHistPos[1].y - vHistPos[0].y) * strengthCoef );
            } else {*/
                figs[i].update(friction, gravity);
            //}
                //if(figs[i].checkChangeState(new BounceCommand())){
                    
                //}
            figs[i].paint();
            figs[i].checkBoundaryCollision();
            try {
                out.write("" + (System.currentTimeMillis() - tiempoInicio) + ": " + figs[i].getName() + " cambia de posición " + figs[i].getPos()+"\n");
                System.out.println("" + (System.currentTimeMillis() - tiempoInicio) + ": " + figs[i].getName() + " cambia de posición " + figs[i].getPos());

            } catch (IOException ex) {
                Logger.getLogger(SkecthPruebaColisiones.class.getName()).log(Level.SEVERE, null, ex);
            }
            for(int j=0; j<figs.length; j++){
                if(j != i){
                    if( figs[i].checkCollision(figs[j]) ){
                        try {
                            long a = System.currentTimeMillis();
                            long totalTiempo = System.currentTimeMillis() - tiempoInicio;
                            out.write("" + (totalTiempo) + ": " + figs[i].getName() + " detectó colisión con " + figs[j].getName() + " " + figs[j].getPos()+"\n");
                            System.out.println("" + (System.currentTimeMillis() - tiempoInicio) + ": " + figs[i].getName() + " detectó colisión con " + figs[j].getName() + " " + figs[j].getPos());
                        } catch (IOException ex) {
                            Logger.getLogger(SkecthPruebaColisiones.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        try {
                            out.write("" + (System.currentTimeMillis() - tiempoInicio) + ": " + figs[i].getName() + " rebota a " + figs[j].getName() + " " + figs[j].getPos()+"\n");
                            System.out.println("" + (System.currentTimeMillis() - tiempoInicio) + ": " + figs[i].getName() + " rebota a " + figs[j].getName() + " " + figs[j].getPos());
                        } catch (IOException ex) {
                            Logger.getLogger(SkecthPruebaColisiones.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        figs[i].bounce(figs[j]);
                    }
                }
            }
        }
    }
    
}