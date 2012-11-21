/*package Pruebas;

import controlP5.*;
import java.awt.Frame;
import processing.core.PApplet;

public class PruebaEvaluacionArquitectura extends PApplet {

    ControlFrame cf;
    int def;

    @Override
    public void setup() {
        size(400, 400);

        cf = addControlFrame("extra", 400, 400);

    }

    @Override
    public void draw() {
        background(def);
    }

    ControlFrame addControlFrame(String theName, int theWidth, int theHeight) {
        Frame f = new Frame(theName);
        ControlFrame p = new ControlFrame(this, theWidth, theHeight);
        f.add(p);
        p.init();
        f.setTitle(theName);
        f.setSize(p.w, p.h);
        f.setLocation(100, 100);
        f.setResizable(false);
        f.setVisible(true);
        return p;
    }

    @Override
    public void keyPressed() {
        
        switch (keyCode) {
            case LEFT:
                cf.myTextarea.setText("Cambie el texto desde la pruebaArquitectura");
                break;
            
        }
    }
}*/