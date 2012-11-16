package pruebascuerpohumano;

import SimpleOpenNI.SimpleOpenNI;
import java.util.HashMap;
import java.util.Map;

public class GeometricFiguresManager {
    private Map<Integer, GeometricFigure> geometricFigures;
    
    public GeometricFiguresManager() {
        geometricFigures = new HashMap<Integer, GeometricFigure>();
    }
    
    public void addGeometricFigure(GeometricFigure gf){
        geometricFigures.put(geometricFigures.size(), gf);
    }
    
    public GeometricFigure getGeometricFigure(int i){
        return geometricFigures.get(i);
    }
    
    public GeometricFigure removeGeometricFigure(int i){
        return geometricFigures.remove(i);
    }
    
    public int getGeometricFiguresSize(){
        return geometricFigures.size();
    }
}
