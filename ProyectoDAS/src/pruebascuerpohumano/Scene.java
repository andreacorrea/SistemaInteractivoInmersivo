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
        this.context.setMirror(true);
        // setup the callback helper class
        usersManager = new UsersManager(pApplet, this.context, this);
        
        // enable depthMap generation 
        CheckKinect.checkDepthCam(pApplet, this.context);

        // enable scene analyser
        CheckKinect.checkScene(pApplet, this.context);
        
        // enable skeleton generation for all joints, direct all callback to the helper class
        this.context.enableUser(SimpleOpenNI.SKEL_PROFILE_ALL, usersManager);
        
        
    }
    
    public void activateGeometricFiguresManager(){
        geometricFiguresManager = new GeometricFiguresManager(this);
    }

    public UsersManager getUsersManager() {
        return usersManager;
    }

    public GeometricFiguresManager getGeometricFiguresManager() {
        return geometricFiguresManager;
    }

    public void paint() {
        pApplet.background(pApplet.color(255,0,0));
        if(hasElements()){
            if(hasUsers()){
                usersManager.updateAndPaintUsers();
            }
            if(hasGeometricFigures()){
                geometricFiguresManager.updateAndPaintGeometricFigures();
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

    void addGeometricFigure(GeometricFigure gf) {
        geometricFiguresManager.addObserverGFMap(gf);
        if(hasUsers()){
            usersManager.addObserverGFUsers(gf);
        }
    }

    void addUser(User user) {
        if(hasGeometricFigures()){
            geometricFiguresManager.addObserverUserMap(user);
        }
    }

}
