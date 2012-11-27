package GeometricFiguresManagement;

import GeometricFiguresManagement.GeometricFigure;
import Interaction.Command;
import SistemaInteraccionInmersiva.Scene;
import UsersManagement.User;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import processing.core.PApplet;

public class GeometricFiguresManager {

    private Map<String, GeometricFigure> geometricFigures;
    private Scene scene;
    protected float friction = 1f;
    private static GeometricFiguresManager instance = null;
    private Command command;

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

    public GeometricFigure getGeometricFigure(int i) {
        return geometricFigures.get(i);
    }

    public GeometricFigure removeGeometricFigure(GeometricFigure gf) {
        geometricFigures.remove(gf.getName());
        scene.removeGeometricFigure(gf);
        return gf;
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
            currentGeometricFigure = ((GeometricFigure) geometricFigure.next());
            currentGeometricFigure.update(friction);
            currentGeometricFigure.checkChangeState(command);
            currentGeometricFigure.paint();
        }
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public Map<String, GeometricFigure> getGeometricFigures() {
        return geometricFigures;
    }
    
    
}
