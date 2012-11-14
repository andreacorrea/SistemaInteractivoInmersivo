/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebascuerpohumano;

import processing.core.PApplet;

/**
 *
 * @author Andrea
 */
public class RectangularPrism extends GeometricFigure {
    private float _memberHeight = 20.0f;
    private float _memberWidth = 0;
    private float _memberDepth = 20;
    
    public RectangularPrism(PApplet p, String name, int color) {
        super(p, name, color);
    }

    public void setMemberHeight(float _memberHeight) {
        this._memberHeight = _memberHeight;
    }

    public void setMemberWidth(float _memberWidth) {
        this._memberWidth = _memberWidth;
    }

    public void setMemberDepth(float _memberDepth) {
        this._memberDepth = _memberDepth;
    }
    
    

}
