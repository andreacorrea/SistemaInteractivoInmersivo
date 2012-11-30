package Pruebas;

import GeometricFiguresManagement.Ball;
import GeometricFiguresManagement.RectangularPrism;
import SistemaInteraccionInmersiva.Scene;
import processing.core.PApplet;
import processing.core.PVector;

public class PruebaGeometricFiguresManager extends PApplet {

    Scene scene;
    //long tiempoInicio = System.currentTimeMillis();
    //BufferedWriter out;

    @Override
    public void setup() {

        size(1024, 768, P3D);
        frameRate(15);

        scene = Scene.getInstance(this);
        scene.activateGeometricFiguresManager();
        //scene.getGeometricFiguresManager().setGravity(0);
        
        pruebaGeometriFigures(10, 35, 3);
        
    }

    @Override
    public void draw() {
        background(color(255, 0, 0));


        scene.paint();

    }

    public void pruebaGeometriFigures(int num, float radio, float speed) {
        float positionX;
        float positionY;
        
        for (int i = 0; i < num; i++) {
            //speed.mult(i+1);
            positionX = width / num * i;
            positionY = height / num * i;
            speed += 0.7;

            if ((int) random(2) == 1) {

                Ball ball = new Ball("ball-" + i, color(255, 255, 0), new PVector(positionX, positionY, 0), radio, new PVector(speed, 0, 0), this);
                scene.getGeometricFiguresManager().addGeometricFigure(ball);
            } else {
                RectangularPrism rectPrism = new RectangularPrism("rect-" + i, color(255, 255, 0), new PVector(positionX, positionY, 0), new PVector(radio, radio, radio), new PVector(speed, 0, 0), this);
                scene.getGeometricFiguresManager().addGeometricFigure(rectPrism);
            }
        }
    }

    /*@Override
    public void keyPressed() {
        switch (key) {
            case '1':
                scene.getGeometricFiguresManager().getGeometricFigures().clear();
                try {
                    FileWriter fstream = new FileWriter("Tiempo de respuesta_Prueba1.txt");
                    out = new BufferedWriter(fstream);
                    pruebaGeometriFigures(10, 15, out, 3);
                } catch (Exception ex) {
                }
                
                break;

            case '2':
                scene.getGeometricFiguresManager().getGeometricFigures().clear();
                try {
                    FileWriter fstream = new FileWriter("Tiempo de respuesta_Prueba2.txt");
                    out = new BufferedWriter(fstream);
                    pruebaGeometriFigures(100, 15, out, 1);
                } catch (Exception ex) {
                }
                
                break;

            case '3':
                scene.getGeometricFiguresManager().getGeometricFigures().clear();
                try {
                    FileWriter fstream = new FileWriter("Tiempo de respuesta_Prueba3.txt");
                    out = new BufferedWriter(fstream);
                    pruebaGeometriFigures(1000, 15, out, 0.3f);
                } catch (Exception ex) {
                }
                
                break;
                
                

            case '4':
                
                scene.getGeometricFiguresManager().getGeometricFigures().clear();
                try {
                    FileWriter fstream = new FileWriter("Tiempo de respuesta_Prueba4.txt");
                    out = new BufferedWriter(fstream);
                    pruebaGeometriFigures(10, 35, out, 3);
                } catch (Exception ex) {
                }
                
                break;

            case '5':
                scene.getGeometricFiguresManager().getGeometricFigures().clear();
                try {
                    FileWriter fstream = new FileWriter("Tiempo de respuesta_Prueba5.txt");
                    out = new BufferedWriter(fstream);
                    pruebaGeometriFigures(100, 35, out, 1);
                } catch (Exception ex) {
                }
                
                break;

            case '6':
                scene.getGeometricFiguresManager().getGeometricFigures().clear();
                try {
                    FileWriter fstream = new FileWriter("Tiempo de respuesta_Prueba6.txt");
                    out = new BufferedWriter(fstream);
                    pruebaGeometriFigures(1000, 35, out, 0.03f);
                } catch (Exception ex) {
                }
                
                break;
                
                

            case '7':
                scene.getGeometricFiguresManager().getGeometricFigures().clear();
                try {
                    FileWriter fstream = new FileWriter("Tiempo de respuesta_Prueba7.txt");
                    out = new BufferedWriter(fstream);
                    pruebaGeometriFigures(10, 55, out, 3);
                } catch (Exception ex) {
                }
                
                break;

            case '8':
                scene.getGeometricFiguresManager().getGeometricFigures().clear();
                try {
                    FileWriter fstream = new FileWriter("Tiempo de respuesta_Prueba8.txt");
                    out = new BufferedWriter(fstream);
                    pruebaGeometriFigures(100, 55, out, 1);
                } catch (Exception ex) {
                }
                
                break;

            case '9':
                scene.getGeometricFiguresManager().getGeometricFigures().clear();
                try {
                    FileWriter fstream = new FileWriter("Tiempo de respuesta_Prueba9.txt");
                    out = new BufferedWriter(fstream);
                    pruebaGeometriFigures(1000, 55, out, 0.3f);
                } catch (Exception ex) {
                }
                
                break;
        }

    }*/
}
