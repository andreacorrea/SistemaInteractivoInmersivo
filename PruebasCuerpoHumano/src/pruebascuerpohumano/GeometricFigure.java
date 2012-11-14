/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebascuerpohumano;

import processing.core.PApplet;
import processing.core.PVector;

/**
 *
 * @author Andrea
 */
class GeometricFigure {

    private PApplet _parent;
    private String _nombre;
    private int _color;
    
    GeometricFigure(PApplet p, String nombre, int color) {
        _parent= p;
        _nombre = nombre;
        _color = color;
    }

    void drawBodyMember() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    void update(PVector jointPos, PVector jointPos0, PVector jointPos1, PVector jointPos2) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
    public PVector calculateMiddlePoint(PVector p1, PVector p2) {
        PVector auxP1 = p1.get();
        PVector auxP2 = p2.get();
        auxP1.sub(auxP2);
        auxP1.div(2);
        auxP2.add(auxP1);
        return auxP2;
    }
    
}
