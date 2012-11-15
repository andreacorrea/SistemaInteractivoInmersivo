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
public class  CheckKinect {
    public static void checkDepthCam(PApplet pApplet, SimpleOpenNI context){
        // enable depthMap generation 
        if (context.enableDepth() == false) {
            pApplet.println("Can't open the depthMap, maybe the camera is not connected!");
            pApplet.exit();
            return;
        }
    }
    
    public static void checkRGBCam(PApplet pApplet, SimpleOpenNI context){
        // enable RGB generation
        if (context.enableRGB() == false) {
            pApplet.println("Can't setup RGB");
            pApplet.exit();
            return;
        }
    }

    public static void checkScene(PApplet pApplet, SimpleOpenNI context) {
        // enable scene analyser
        if (context.enableScene() == false) {
            pApplet.println("Can't setup scene");
            pApplet.exit();
            return;
        }
    }
}