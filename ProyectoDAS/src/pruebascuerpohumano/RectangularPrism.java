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
            /*
            //Calculate angle, sine, and cosine
            float angle = _parent.atan2(dy,dx);
            float sine = _parent.sin(angle);
            float cosine = _parent.cos(angle);
            
            //rotate b0's position
            float x0 = 0;
            float y0 = 0;
            
            //rotate b1's position
            float x1 = dx * cosine + dy * sine;
            float y1 = dy * cosine - dx * sine;
            
            //rotate b0's velocity
            float vx0 = vel.x * cosine + vel.y * sine;
            float vy0 = vel.y * cosine - vel.x * sine;
            
            //rotate b1's velocity
            float vx1 = b.getVel().x * cosine + b.getVel().y * sine;
            float vy1 = b.getVel().y * cosine - b.getVel().x * sine;
            
            //collision reaction
            float vxTotal = vx0 - vx1;
            vx0 = ((getMass() - b.getMass()) * vx0 + 2 * b.getMass() * vx1) / (getMass() + b.getMass());
            vx1 = vxTotal  + vx0;
            x0 += vx0;
            x1 += vx1;
            
            //rotate positions back
            float x0f = x0 * cosine - y0 * sine;
            float y0f = y0 * cosine + x0 * sine;
            float x1f = x1 * cosine - y1 * sine;
            float y1f = y1 * cosine + x1 * sine;
            
            //adjust positions to actual screen positions
            b.setPosX(pos.x + x1f);
            b.setPosY(pos.y + y1f);
            pos.x += x0f;
            pos.y += y0f;
            
            //rotate velocities back
            vel.x = vx0 * cosine - vy0 * sine;
            vel.y = vy0 * cosine + vx0 * sine;
            b.setVelX( vx1 * cosine - vy1 * sine );
            b.setVelY( vy1 * cosine + vx1 * sine );*/
        } else {
            //this._color = _parent.color(255,255,255);
            //isCollided = false;
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
