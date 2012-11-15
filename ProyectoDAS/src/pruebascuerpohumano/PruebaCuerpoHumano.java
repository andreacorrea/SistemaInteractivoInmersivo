/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebascuerpohumano;

//import ixagon.renderer.*;
import SimpleOpenNI.*;
import java.util.Iterator;
import java.util.Map;
import processing.core.*;

public class PruebaCuerpoHumano extends PApplet {

    Scene scene;

    User currentUser;
    float rotX;  // by default rotate the hole scene 180deg around the x-axis, 
    // the data from openni comes upside down
    float rotY;
    
    SimpleOpenNI context;
    
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

    }

    @Override
    public void draw() {
        background(200);
        rotateX(rotX);
        rotateY(rotY);
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

        if (scene.getUsersManager().getUsersSize()> 0) {

            // draw the skeleton of user1
            Iterator user = scene.getUsersManager().getUsers().values().iterator();

            while (user.hasNext()) {
                currentUser = ((User) user.next());
                //currentUser.getBody().drawSkeletonLines();
                //currentUser.getBody().drawJoints();
                currentUser.getBody().update();
                //currentUser.getBody().circleForAHead();
            }
        } else {

            // draw scene Image
            image(context.sceneImage(), 0, 0);
        }
        
    }



    @Override
    public void keyPressed() {

        switch (keyCode) {
            case LEFT:
                int a =0;
                //rotY += 0.1f;
                break;
            case RIGHT:
                // zoom out
                rotY -= 0.1f;
                break;
            case UP:
                rotX += 0.1f;
                break;
            case DOWN:
                // zoom out
                rotX -= 0.1f;
                break;  
            
            case '0':
                exit();
        }
    }
}