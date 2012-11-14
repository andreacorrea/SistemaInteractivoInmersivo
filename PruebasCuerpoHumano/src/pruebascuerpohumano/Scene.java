package pruebascuerpohumano;

import SimpleOpenNI.SimpleOpenNI;
import processing.core.PApplet;

public class Scene {
    
    PApplet pApplet;
    SimpleOpenNI context;
    
    
    // this object deals with the user callbacks
    UsersManager usersManager;
    GeometricFiguresManager geometricFiguresManager;
    
    public Scene(PApplet pApplet){
        this.pApplet = pApplet;
    }
    
    public void activateUsersManager(SimpleOpenNI context){
        this.context = context;
        
        // enable depthMap generation 
        if (context.enableDepth() == false) {
            pApplet.println("Can't open the depthMap, maybe the camera is not connected!");
            pApplet.exit();
            return;
        }

        // enable scene analyser
        if (context.enableScene() == false) {
            pApplet.println("Can't setup scene");
            pApplet.exit();
            return;
        }
        
        // enable skeleton generation for all joints, direct all callback to the helper class
        context.enableUser(SimpleOpenNI.SKEL_PROFILE_ALL, usersManager);
        
        // setup the callback helper class
        usersManager = new UsersManager(pApplet, context);
        
    }
    
    public void activateGeometricFiguresManager(SimpleOpenNI context){
        geometricFiguresManager = new GeometricFiguresManager();
    }

    public UsersManager getUsersManager() {
        return usersManager;
    }

    public GeometricFiguresManager getGeometricFiguresManager() {
        return geometricFiguresManager;
    }

}
