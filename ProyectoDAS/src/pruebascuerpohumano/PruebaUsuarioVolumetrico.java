/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebascuerpohumano;

import SimpleOpenNI.SimpleOpenNI;
import processing.core.PApplet;

/**
 *
 * @author Andrea
 */
public class PruebaUsuarioVolumetrico extends PApplet {

    SimpleOpenNI context;
    VisualRepresentation visualRepresentation;
    Scene scene;

    @Override
    public void setup() {
        // create a window the size of the scene
        //size(640, 480, OldP3D.NOSTALGIA);
        size(640, 480, P3D);

        // instantiate a new context
        frameRate(24);

        context = new SimpleOpenNI(this);

        scene = new Scene(this);

        scene.activateUsersManager(context);
        
        visualRepresentation = new VisualRepresentation(this, context);

    }
    
    @Override
    public void draw() {
        background(200);
        //pushMatrix();
        // update the camera
        context.update();

        /*background(51);
         lights();*/

        // draw depth image
        //image(context.depthImage(), 0, 0);

        pushStyle();
        strokeWeight(10);
        stroke(0, 0, 255);
        //eje horizontal
        line(width / 2 - 200, height / 2, width / 2 + 200, height / 2);
        //eje vertical
        stroke(0, 255, 0);
        line(width / 2, height / 2 - 200, width / 2, height / 2 + 200);
        //eje Z
        stroke(255, 200, 0);
        line(width / 2, height / 2, 200, width / 2, height / 2, -200);
        popStyle();
        
        if(scene.hasUsers()){
            visualRepresentation.update();
            scene.paint();
        }else{
            // draw scene Image
            image(context.sceneImage(), 0, 0);
        }

    }
}
