package pruebascuerpohumano;

import SimpleOpenNI.SimpleOpenNI;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GeometricFiguresManager {

    private Map<Integer, GeometricFigure> geometricFigures;
    private Scene scene;
    protected float friction = 0.95f;
    private static GeometricFiguresManager instance = null;

    private GeometricFiguresManager(Scene scene) {
        geometricFigures = new HashMap<Integer, GeometricFigure>();
        this.scene = scene;
    }
    
    public static GeometricFiguresManager getInstance(Scene scene) {
        if(instance == null){
            instance = new GeometricFiguresManager(scene);
        } else {
            instance.scene = scene;
        }
        return instance;
    }

    public void addGeometricFigure(GeometricFigure gf) {
        geometricFigures.put(geometricFigures.size(), gf);
        scene.addGeometricFigure(gf);
    }

    public GeometricFigure getGeometricFigure(int i) {
        return geometricFigures.get(i);
    }

    public GeometricFigure removeGeometricFigure(int i) {
        return geometricFigures.remove(i);
    }

    public int getGeometricFiguresSize() {
        return geometricFigures.size();
    }

    void addObserverGFMap(GeometricFigure gf) {
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

    void addObserverUserMap(User user) {
        GeometricFigure currentGeometricFigure;
        Iterator geometricFigure = geometricFigures.values().iterator();

        while (geometricFigure.hasNext()) {
            currentGeometricFigure = ((GeometricFigure) geometricFigure.next());
            user.getBody().addObserverGFBody(currentGeometricFigure);
        }
    }

    void updateAndPaintGeometricFigures() {
        GeometricFigure currentGeometricFigure;
        Iterator geometricFigure = geometricFigures.values().iterator();

        while (geometricFigure.hasNext()) {
            currentGeometricFigure = ((GeometricFigure) geometricFigure.next());
            currentGeometricFigure.update(friction);
            currentGeometricFigure.paint();
        }
    }
}
