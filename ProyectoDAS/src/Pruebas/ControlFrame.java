package Pruebas;

import SistemaInteraccionInmersiva.Scene;
import controlP5.ControlEvent;
import controlP5.ControlP5;
import controlP5.Textarea;
import processing.core.PApplet;


public class ControlFrame extends PApplet {

    ControlP5 cp5;
    PApplet parent;
    int w, h;
    int colorBackground = 0;
    Textarea myTextarea;
    
    boolean firstTime = true;

    public void setup() {
        size(w, h);
        frameRate(25);
        cp5 = new ControlP5(this);
        cp5.addSlider("abc").setRange(0, 255).setPosition(10, 10);
        cp5.addSlider("def").plugTo(parent, "def").setRange(0, 255).setPosition(10, 30);
        myTextarea = cp5.addTextarea("txt")
                .setPosition(100, 150)
                .setSize(200, 200)
                .setFont(createFont("arial", 12))
                .setLineHeight(14)
                .setColor(color(128))
                .setColorBackground(color(255, 100))
                .setColorForeground(color(255, 100));
        ;
        myTextarea.setText("Lorem Ipsum is simply dummy text of the printing and typesetting"
                + " industry. Lorem Ipsum has been the industry's standard dummy text"
                + " ever since the 1500s, when an unknown printer took a galley of type"
                + " and scrambled it to make a type specimen book. It has survived not"
                + " only five centuries, but also the leap into electronic typesetting,"
                + " remaining essentially unchanged. It was popularised in the 1960s"
                + " with the release of Letraset sheets containing Lorem Ipsum passages,"
                + " and more recently with desktop publishing software like Aldus"
                + " PageMaker including versions of Lorem Ipsum.");

        cp5.addButton("escanear")
                .setValue(0)
                .setPosition(100, 6)
                .setSize(200, 19);
        
        cp5.addButton("Agregar5Figuras")
                .setValue(5)
                .setPosition(100, 80)
                .setSize(200, 19);

        // and add another 2 buttons
        cp5.addButton("Agregar10Figuras")
                .setValue(10)
                .setPosition(100, 100)
                .setSize(200, 19);

        cp5.addButton("Agregar15Figuras")
                .setPosition(100, 120)
                .setSize(200, 19)
                .setValue(15);

    }

    public void draw() {
        background(colorBackground);
    }

    private ControlFrame() {
        
    }

    public ControlFrame(PApplet theParent, int theWidth, int theHeight) {
        parent = theParent;
        w = theWidth;
        h = theHeight;
    }

    public ControlP5 control() {
        return cp5;
    }
    

}