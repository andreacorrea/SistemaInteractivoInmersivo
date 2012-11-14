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
    private float rotationZ;
    private float rotationY;
    
    public RectangularPrism(PApplet p, String name, int color) {
        super(name, color, p);
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

    public void setRotationZ(float rotationZ) {
        this.rotationZ = rotationZ;
    }

    public void setRotationY(float rotationY) {
        this.rotationY = rotationY;
    }

    @Override
    void paint() {
        _parent.pushMatrix();
        _parent.fill(_color);
        _parent.translate(pos.x, pos.y, pos.z);
        _parent.rotateZ(rotationZ);
        _parent.rotateY(rotationY);
        _parent.box(_memberWidth, _memberHeight, _memberDepth);
        _parent.popMatrix();
    }

    @Override
    void update(float friction) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    void checkBoundaryCollision() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    void checkCollision(Ball b) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    

}
