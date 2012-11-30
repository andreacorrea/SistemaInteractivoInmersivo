package GeometricFiguresManagement;

import Interaction.Command;
import SistemaInteraccionInmersiva.Scene;
import UsersManagement.User;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import processing.core.PApplet;

public class GeometricFiguresManager {

    private Map<String, GeometricFigure> geometricFigures;
    private Scene scene;
    protected float friction = 0.93f;
    protected float gravity = 0.3f;
    protected float upperLimit =0;
    private static GeometricFiguresManager instance = null;
    private Command collisionCommand;
    private Command collisionLowerBoundaryCommand;
    private Command collisionUpperBoundaryCommand;
    private Command collisionLeftBoundaryCommand;
    private Command collisionRightBoundaryCommand;
    

    private GeometricFiguresManager(PApplet p) {
        geometricFigures = new HashMap<String, GeometricFigure>();
        this.scene = Scene.getInstance(p);
    }

    public static GeometricFiguresManager getInstance(PApplet p) {
        if (instance == null) {
            instance = new GeometricFiguresManager(p);
        } else {
            instance.scene = Scene.getInstance(p);
        }
        return instance;
    }

    public void addGeometricFigure(GeometricFigure gf) {
        geometricFigures.put(gf.getName(), gf);
        scene.addGeometricFigure(gf);
    }

    public GeometricFigure getGeometricFigure(String name) {
        return geometricFigures.get(name);
    }

    public GeometricFigure removeGeometricFigure(GeometricFigure gf) {
        geometricFigures.remove(gf.getName());
        scene.removeGeometricFigure(gf);
        return gf;
    }
    
    public void clear(){
        GeometricFigure currentGeometricFigure;
        Iterator geometricFigure = geometricFigures.values().iterator();

        /*while (geometricFigure.hasNext()) {
            currentGeometricFigure = ((GeometricFigure) geometricFigure.next());
            removeGeometricFigure(currentGeometricFigure);
        }*/
        while (geometricFigure.hasNext()) {
            currentGeometricFigure = ((GeometricFigure) geometricFigure.next());
            scene.removeGeometricFigure(currentGeometricFigure);
        }
        geometricFigures.clear();
    }

    public int getGeometricFiguresSize() {
        return geometricFigures.size();
    }

    public void addObserverGFMap(GeometricFigure gf) {
        GeometricFigure currentGeometricFigure;
        Iterator geometricFigure = geometricFigures.values().iterator();

        while (geometricFigure.hasNext()) {
            currentGeometricFigure = ((GeometricFigure) geometricFigure.next());
            if (!currentGeometricFigure.equals(gf)) {
                currentGeometricFigure.addObserver(gf);
                gf.addObserver(currentGeometricFigure);
            }
        }
    }

    public void removeObserverGFMap(GeometricFigure gf) {
        GeometricFigure currentGeometricFigure;
        Iterator geometricFigure = geometricFigures.values().iterator();

        while (geometricFigure.hasNext()) {
            currentGeometricFigure = ((GeometricFigure) geometricFigure.next());
            if (!currentGeometricFigure.equals(gf)) {
                currentGeometricFigure.removeObserver(gf);
                gf.removeObserver(currentGeometricFigure);
            }
        }
    }

    public void addObserverUserMap(User user) {
        GeometricFigure currentGeometricFigure;
        Iterator geometricFigure = geometricFigures.values().iterator();

        while (geometricFigure.hasNext()) {
            currentGeometricFigure = ((GeometricFigure) geometricFigure.next());
            user.getBody().addObserverGFBody(currentGeometricFigure);
        }
    }

    public void removeObserverUserMap(User user) {
        GeometricFigure currentGeometricFigure;
        Iterator geometricFigure = geometricFigures.values().iterator();

        while (geometricFigure.hasNext()) {
            currentGeometricFigure = ((GeometricFigure) geometricFigure.next());
            user.getBody().removeObserverGFBody(currentGeometricFigure);
        }
    }

    public void updateAndPaintGeometricFigures() {
        GeometricFigure currentGeometricFigure;
        Iterator geometricFigure = geometricFigures.values().iterator();

        while (geometricFigure.hasNext()) {
            try{
                currentGeometricFigure = ((GeometricFigure) geometricFigure.next());
                currentGeometricFigure.update(friction, gravity);
                currentGeometricFigure.checkChangeState(collisionCommand);
                currentGeometricFigure.paint();
            }catch(ConcurrentModificationException ex){
                System.out.println(ex.toString());
                return;
            }
        }
    }

    public void setCollisionCommand(Command collisionCommand) {
        this.collisionCommand = collisionCommand;
    }

    public Map<String, GeometricFigure> getGeometricFigures() {
        return geometricFigures;
    }

    public void setGravity(float gravity) {
        this.gravity = gravity;
    }

    public void setCollisionLowerBoundaryCommand(Command collisionLowerBoundaryCommand) {
        this.collisionLowerBoundaryCommand = collisionLowerBoundaryCommand;
    }

    public Command getCollisionLowerBoundaryCommand() {
        return collisionLowerBoundaryCommand;
    }

    public Command getCollisionUpperBoundaryCommand() {
        return collisionUpperBoundaryCommand;
    }

    public void setCollisionUpperBoundaryCommand(Command collisionUpperBoundaryCommand) {
        this.collisionUpperBoundaryCommand = collisionUpperBoundaryCommand;
    }

    public Command getCollisionLeftBoundaryCommand() {
        return collisionLeftBoundaryCommand;
    }

    public void setCollisionLeftBoundaryCommand(Command collisionLeftBoundaryCommand) {
        this.collisionLeftBoundaryCommand = collisionLeftBoundaryCommand;
    }

    public Command getCollisionRightBoundaryCommand() {
        return collisionRightBoundaryCommand;
    }

    public void setCollisionRightBoundaryCommand(Command collisionRightBoundaryCommand) {
        this.collisionRightBoundaryCommand = collisionRightBoundaryCommand;
    }

    public float getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(float upperLimit) {
        this.upperLimit = upperLimit;
    }
    
    
    
    
}
