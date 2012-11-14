/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebascuerpohumano;

import ixagon.renderer.*;
import SimpleOpenNI.*;
import java.util.Iterator;
import java.util.Map;
import processing.core.*;

public class PruebaCuerpoHumano extends PApplet {

    SimpleOpenNI context;
// this object deals with the user callbacks
    UsersManager usersManager;
    User currentUser;
    float rotX;  // by default rotate the hole scene 180deg around the x-axis, 
    // the data from openni comes upside down
    float rotY;
    Torso torso1;
    float offset = 50;
    PVector torso1_LeftUpper;
    PVector torso1_RightUpper;
    PVector torso1_LeftLower;
    PVector torso1_RightLower;

    @Override
    public void setup() {
        // create a window the size of the scene
        //size(640, 480, OldP3D.NOSTALGIA);
        size(640, 480, P3D);

        // instantiate a new context
        frameRate(24);

        setupTorsos();

        context = new SimpleOpenNI(this);

        // enable depthMap generation 
        if (context.enableDepth() == false) {
            println("Can't open the depthMap, maybe the camera is not connected!");
            exit();
            return;
        }

        // enable scene analyser
        if (context.enableScene() == false) {
            println("Can't setup scene");
            exit();
            return;
        }

        // disable mirror
        context.setMirror(true);

        // setup the callback helper class
        usersManager = new UsersManager(this, context);

        // enable skeleton generation for all joints, direct all callback to the helper class
        context.enableUser(SimpleOpenNI.SKEL_PROFILE_ALL, usersManager);

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

        if (usersManager.getUsers().size() > 0) {

            // draw the skeleton of user1
            Iterator user = usersManager.getUsers().values().iterator();

            while (user.hasNext()) {
                currentUser = ((User) user.next());
                //currentUser.getBody().drawSkeletonLines();
                //currentUser.getBody().drawJoints();
                currentUser.getBody().update();
                currentUser.getBody().circleForAHead();
            }
        } else {

            // draw scene Image
            image(context.sceneImage(), 0, 0);
        }
        //rpuebaTorsos();
    }

    public void setupTorsos() {
        //Torso1
        torso1_LeftUpper = new PVector(width / 2 - offset, height / 2 - offset, 0);
        torso1_RightUpper = new PVector(width / 2 + offset, height / 2 - offset, -300);
        torso1_LeftLower = new PVector(width / 2 - offset, height / 2 + offset, 0);
        torso1_RightLower = new PVector(width / 2 + offset, height / 2 + offset, 0);

        torso1 = new Torso(this, "torso1", color(255, 0, 0));
    }

    public void pruebaTorsos() {
        point(torso1_LeftUpper.x, torso1_LeftUpper.y, 0);
        point(torso1_RightUpper.x, torso1_RightUpper.y, torso1_RightUpper.z);
        point(torso1_LeftLower.x, torso1_LeftLower.y, 0);
        point(torso1_RightLower.x, torso1_RightLower.y, 0);
        torso1.update(torso1_LeftUpper, torso1_RightUpper, torso1_LeftLower, torso1_RightLower);
        torso1.drawBodyMember();
    }

    @Override
    public void keyPressed() {

        switch (keyCode) {
            case LEFT:
                rotY += 0.1f;
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