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
        context.setMirror(true);
        // setup the callback helper class
        usersManager = new UsersManager(pApplet, context);
        
        // enable depthMap generation 
        CheckKinect.checkDepthCam(pApplet, context);

        // enable scene analyser
        CheckKinect.checkScene(pApplet, context);
        
        // enable skeleton generation for all joints, direct all callback to the helper class
        context.enableUser(SimpleOpenNI.SKEL_PROFILE_ALL, usersManager);
        
        
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

    public void paint() {
        
        if(hasElements()){
            if(hasUsers()){
                usersManager.paintUsers();
            }
            if(hasGeometricFigures()){
                //geometricFiguresManager.paintGeometricFigures();
            }
            
        }
        
    }
    
    public boolean hasUsers(){
        return usersManager != null && usersManager.getUsersSize()>0;
    }
    
    public boolean hasGeometricFigures(){
        return geometricFiguresManager != null && geometricFiguresManager.getGeometricFiguresSize()>0;
    }
    
    public boolean hasElements(){
        boolean hasElements = false;
        if(usersManager != null && usersManager.getUsersSize()>0){
            hasElements = true;
        }else if(geometricFiguresManager != null && geometricFiguresManager.getGeometricFiguresSize()>0){
            hasElements = true;
        }
        return hasElements;
    }

}
