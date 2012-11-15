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
public class RectangularPrism extends GeometricFigure {
    private float rotationZ;
    private float rotationY;
    private PVector dimensions;
    private boolean isCollided=false;
    
    public RectangularPrism(PApplet p, String name, int color) {
        super(name, color, p);
    }
    
    public RectangularPrism(String pname, int pcolor, PVector ppos, PVector pdim, PVector pvel, PApplet pparent){
        super(pname, pcolor, ppos, ( (pdim.x*0.1f+pdim.y*0.1f+pdim.z*0.1f)/3 ), pvel, pparent);
        dimensions = pdim;
    }

    @Override
    void paint() {
        _parent.pushMatrix();
        _parent.fill(_color);
        _parent.translate(pos.x, pos.y, pos.z);
        _parent.rotateZ(rotationZ);
        _parent.rotateY(rotationY);
        if(isCollided){
            _parent.fill(0,255,0);
        }else{
            _parent.fill(255,255,255);
        }
        _parent.box(getDimensions().x, getDimensions().y, getDimensions().z);
        _parent.popMatrix();
        isCollided=false;
    }

    @Override
    void update(float friction) {
        vel.x *= friction;
        vel.y *= friction;
        pos.x += vel.x;
        pos.y += vel.y;
    }

    @Override
    void checkBoundaryCollision() {
        if (pos.x > _parent.width-getDimensions().x) {
            pos.x  = _parent.width-getDimensions().x;
            vel.x *= -1;
        } 
        else if (pos.x < getDimensions().x) {
            pos.x  = getDimensions().x;
            vel.x *= -1;
        } 
        else if (pos.y > _parent.height-getDimensions().y) {
            pos.y  = _parent.height-getDimensions().y;
            vel.y *= -1;
        } 
        else if (pos.y < getDimensions().y) {
            pos.y  = getDimensions().y;
            vel.y *= -1;
        }
    }
    
    void checkCollision(Ball b){
        float dx = _parent.abs( b.getPos().x - pos.x );
        float dy = _parent.abs( b.getPos().y - pos.y );
        float dxmin = b.getRadius() + dimensions.x/2;
        float dymin = b.getRadius() + dimensions.y/2;
        
        if(dx < dxmin && dy < dymin){
            //this._color = _parent.color(0,255,0);
            isCollided = true;

            float angle = _parent.atan2(dy,dx);
            float vx0 = vel.x;
            float vy0 = vel.y;
            float vx1 = b.getVel().x;
            float vy1 = b.getVel().y;
            
            if(angle >= -_parent.PI/4 && angle <= _parent.PI/4) {
                //collision reaction
                float vxTotal = vx0 - vx1;
                vx0 = ((getMass() - b.getMass()) * vx0 + 2 * b.getMass() * vx1) / (getMass() + b.getMass());
                vx1 = vxTotal  + vx0;
                pos.x += vx0;
                b.setPosX( b.getPos().x + vx1 );
            } else {
                float vyTotal = vy0 - vy1;
                vy0 = ((getMass() - b.getMass()) * vy0 + 2 * b.getMass() * vy1) / (getMass() + b.getMass());
                vy1 = vyTotal  + vy0;
                pos.y += vy0;
                b.setPosY( b.getPos().y + vy1 );            
            }  
            
        } 
    }
    
    void checkCollision(RectangularPrism p){
    
    }
    
    public void setDimensionX(float y) {
        this.dimensions.y = y;
    }

    public void setDimensionY(float x) {
        this.dimensions.x = x;
    }

    public void setDimensionZ(float z) {
        this.dimensions.z = z;
    }

    public void setRotationZ(float rotationZ) {
        this.rotationZ = rotationZ;
    }

    public void setRotationY(float rotationY) {
        this.rotationY = rotationY;
    }

    /**
     * @return the dimensions
     */
    public PVector getDimensions() {
        return dimensions;
    }

    /**
     * @param dimensions the dimensions to set
     */
    public void setDimensions(PVector dimensions) {
        this.dimensions = dimensions;
    }

}
