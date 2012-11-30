package Pruebas;

import SistemaInteraccionInmersiva.VirtualRepresentation;
import UsersManagement.AdapterSimpleOpenNI;
import processing.core.*;

public class PruebaVisualRepresentation extends PApplet {

    AdapterSimpleOpenNI context;
    VirtualRepresentation visualRepresentation;

    @Override
    public void setup() {
        // create a window the size of the scene
        size(640, 480, P3D);

        // instantiate a new context
        frameRate(24);
        
        context = new AdapterSimpleOpenNI(this);
        
        visualRepresentation = new VirtualRepresentation(this, context);
        visualRepresentation.setRealColor(true);

    }

    @Override
    public void draw() {
        background(250);
        // update the camera
        context.update();
        visualRepresentation.update();
    }   
    
}
