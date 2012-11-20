package pruebascuerpohumano;

import SimpleOpenNI.SimpleOpenNI;
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
        
        // update the camera
        context.update();
        visualRepresentation.update();

    }

    
}
