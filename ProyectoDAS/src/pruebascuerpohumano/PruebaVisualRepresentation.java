package pruebascuerpohumano;

import SimpleOpenNI.SimpleOpenNI;
import java.util.Iterator;
import processing.core.*;

public class PruebaVisualRepresentation extends PApplet {

    SimpleOpenNI context;
    VisualRepresentation visualRepresentation;

    @Override
    public void setup() {
        // create a window the size of the scene
        size(640, 480, P3D);

        // instantiate a new context
        frameRate(24);
        
        context = new SimpleOpenNI(this);
        
        visualRepresentation = new VisualRepresentation(this);
        visualRepresentation.activateVisualRepresentation(context);

    }

    @Override
    public void draw() {
        
        // update the camera
        context.update();
        visualRepresentation.update();

    }

    
}
