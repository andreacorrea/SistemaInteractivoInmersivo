package pruebascuerpohumano;

import SimpleOpenNI.SimpleOpenNI;
import processing.core.PApplet;

public class Scene {
    
    private PApplet pApplet;
    private SimpleOpenNI context;
    private static Scene instance = null;   
    
    // this object deals with the user callbacks
    private UsersManager usersManager;
    private GeometricFiguresManager geometricFiguresManager;
    
    private Command command = new BounceCommand();
    
    private Scene(PApplet pApplet){
        this.pApplet = pApplet;
    }
    
    public static Scene getInstance(PApplet p){
        if(instance == null){
            instance = new Scene(p);
        } else {
            instance.pApplet = p;
        }
        return instance;
    }
    
    public void activateUsersManager(SimpleOpenNI context){
        this.context = context;
        this.context.setMirror(true);
        // setup the callback helper class
        usersManager = UsersManager.getInstance(pApplet, this.context, this);
        usersManager.setCommand(command);
        // enable depthMap generation 
        CheckKinect.checkDepthCam(pApplet, this.context);

        // enable scene analyser
        CheckKinect.checkScene(pApplet, this.context);
        
        // enable skeleton generation for all joints, direct all callback to the helper class
        this.context.enableUser(SimpleOpenNI.SKEL_PROFILE_ALL, usersManager);
        
        
    }
    
    public void activateGeometricFiguresManager(){
        geometricFiguresManager = GeometricFiguresManager.getInstance(this);
        geometricFiguresManager.setCommand(command);
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

    public void setCommand(Command command) {
        this.command = command;
        if(hasElements()){
            if(hasUsers()){
                this.usersManager.setCommand(command);
            }
            if(hasGeometricFigures()){
                this.geometricFiguresManager.setCommand(command);
            }
        }
        
        
    }

}
