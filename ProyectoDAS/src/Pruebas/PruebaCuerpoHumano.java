/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pruebas;

//import ixagon.renderer.*;
import UsersManagement.AdapterSimpleOpenNI;
import Interaction.BounceCommand;
import Interaction.ChangeColorCommand;
import Interaction.Command;
import SistemaInteraccionInmersiva.Scene;
import UsersManagement.VolumeGeneration.BuildingSkeletonVolumeStrategy;
import UsersManagement.User;
import processing.core.*;

public class PruebaCuerpoHumano extends PApplet {

    Scene scene;
    User currentUser;
    /*float rotX;  // by default rotate the hole scene 180deg around the x-axis, 
     // the data from openni comes upside down
     float rotY;*/
    AdapterSimpleOpenNI context;

    @Override
    public void setup() {
        // create a window the size of the scene
        //size(640, 480, OldP3D.NOSTALGIA);
        size(640, 480, P3D);

        // instantiate a new context
        frameRate(24);

        context = new AdapterSimpleOpenNI(this);

        scene = Scene.getInstance(this);

        scene.activateUsersManager(context);

    }

    @Override
    public void draw() {
        //background(200);
        /*rotateX(rotX);
         rotateY(rotY);*/
        // update the camera
        context.update();

        /*background(51);
         lights();*/

        // draw depth image
        //image(context.depthImage(), 0, 0);

        /*pushStyle();
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
         popStyle();*/

        if (scene.hasElements()) {
            scene.paint();
        } else {
            // draw scene Image
            image(context.sceneImage(), 0, 0);
        }

    }
}