package SistemaInteraccionInmersiva;

import GeometricFiguresManagement.GeometricFigure;
import GeometricFiguresManagement.GeometricFiguresManager;
import Interaction.BounceCommand;
import Interaction.Command;
import UsersManagement.AdapterSimpleOpenNI;
import UsersManagement.User;
import UsersManagement.UsersManager;
import java.util.Map;
import processing.core.PApplet;

public class Scene {
    
    private PApplet parent;
    private AdapterSimpleOpenNI context;
    private static Scene instance = null;   
    
    // this object deals with the user callbacks
    private UsersManager usersManager;
    private GeometricFiguresManager geometricFiguresManager;
    
    
    private Command collisionCommand = new BounceCommand();
    
    private Scene(PApplet parent){
        this.parent = parent;
    }
    
    public static Scene getInstance(PApplet p){
        if(instance == null){
            instance = new Scene(p);
        } else {
            instance.parent = p;
        }
        return instance;
    }
    
    public void activateUsersManager(AdapterSimpleOpenNI context){
        this.context = context;
        this.context.setMirror(true);
        // setup the callback helper class
        usersManager = UsersManager.getInstance(parent, this.context);
        usersManager.setCollisionCommand(collisionCommand);
        // enable depthMap generation 
        CheckKinect.checkDepthCam(parent, this.context);

        // enable scene analyser
        CheckKinect.checkScene(parent, this.context);
        
        // enable skeleton generation for all joints, direct all callback to the helper class
        this.context.enableUser(AdapterSimpleOpenNI.SKEL_PROFILE_ALL, usersManager);
        
        
    }
    
    public void activateGeometricFiguresManager(){
        geometricFiguresManager = GeometricFiguresManager.getInstance(this.parent);
        geometricFiguresManager.setCollisionCommand(collisionCommand);
    }

    public UsersManager getUsersManager() {
        return usersManager;
    }

    public GeometricFiguresManager getGeometricFiguresManager() {
        return geometricFiguresManager;
    }

    public void paint() {
        parent.lights();
        parent.background(parent.color(230));
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

    public void addGeometricFigure(GeometricFigure gf) {
        geometricFiguresManager.addObserverGFMap(gf);
        if(hasUsers()){
            usersManager.addObserverGFUsers(gf);
        }
    }

    public void addUser(User user) {
        if(hasGeometricFigures()){
            geometricFiguresManager.addObserverUserMap(user);
        }
    }
    
    public void removeUser(User user) {
        if(hasGeometricFigures()){
            geometricFiguresManager.removeObserverUserMap(user);
        }
    }
    
    public void removeGeometricFigure(GeometricFigure gf){

        if(hasElements()){
            if(hasUsers()){
                usersManager.removeObserverGFUsers(gf);
            }
            if(hasGeometricFigures()){
                geometricFiguresManager.removeObserverGFMap(gf);
            }
        }
    }

    public void setCollisionCommand(Command collisionCommand) {
        this.collisionCommand = collisionCommand;
        if(hasElements()){
            if(hasUsers()){
                this.usersManager.setCollisionCommand(collisionCommand);
            }
            if(hasGeometricFigures()){
                this.geometricFiguresManager.setCollisionCommand(collisionCommand);
            }
        }
    }

    public PApplet getParent() {
        return parent;
    }

}
