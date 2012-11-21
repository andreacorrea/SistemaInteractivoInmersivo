package Pruebas;

import GeometricFiguresManagement.AdapterSimpleOpenNI;
import SimpleOpenNI.SimpleOpenNI;
import SistemaInteraccionInmersiva.VisualRepresentation;
import java.util.Iterator;
import processing.core.*;

public class PruebaVisualRepresentation extends PApplet {

    AdapterSimpleOpenNI context;
    VisualRepresentation visualRepresentation;

    @Override
    public void setup() {
        // create a window the size of the scene
        size(640, 480, P3D);

        // instantiate a new context
        frameRate(24);
        
        context = new AdapterSimpleOpenNI(this);
        
        visualRepresentation = new VisualRepresentation(this, context);

    }

    @Override
    public void draw() {
        background(250);
        // update the camera
        context.update();
        visualRepresentation.update();
    }   
    
}
