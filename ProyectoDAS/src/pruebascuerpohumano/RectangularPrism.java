/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebascuerpohumano;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import processing.core.PApplet;
import processing.core.PVector;

/**
 *
 * @author Andrea
 */
public class RectangularPrism extends GeometricFigure {

    private float rotationZ;
    private float rotationY;
    private PVector dimensions = new PVector();
    private boolean isCollided = false;

    public RectangularPrism(PApplet p, String name, int color, float mass) {
        super(name, color, p, mass);
    }

    public RectangularPrism(String pname, int pcolor, PVector ppos, PVector pdim, PVector pvel, PApplet pparent) {
        super(pname, pcolor, ppos, ((pdim.x * 0.1f + pdim.y * 0.1f + pdim.z * 0.1f) / 3), pvel, pparent);
        dimensions = pdim;
    }

    @Override
    void paint() {
        //checkChangeState();
        parent.pushMatrix();
        parent.stroke(0, 0, 0);
        parent.fill(color);
        parent.translate(pos.x, pos.y, pos.z);
        parent.rotateZ(rotationZ);
        parent.rotateY(rotationY);
        if (isCollided) {
            parent.fill(0, 255, 0);
        } else {
            parent.fill(255, 255, 255);
        }
        parent.box(getDimensions().x, getDimensions().y, getDimensions().z);
        parent.popMatrix();
        isCollided = false;
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
        if (pos.x > parent.width - getDimensions().x) {
            pos.x = parent.width - getDimensions().x;
            vel.x *= -1;
        } else if (pos.x < getDimensions().x) {
            pos.x = getDimensions().x;
            vel.x *= -1;
        } else if (pos.y > parent.height - getDimensions().y) {
            pos.y = parent.height - getDimensions().y;
            vel.y *= -1;
        } else if (pos.y < getDimensions().y) {
            pos.y = getDimensions().y;
            vel.y *= -1;
        }
    }

    @Override
    void checkCollision(Ball b){
        float dx = parent.abs( b.getPos().x - pos.x );
        float dy = parent.abs( b.getPos().y - pos.y );
        float dxmin = b.getRadius() + dimensions.x / 2;
        float dymin = b.getRadius() + dimensions.y / 2;
        
        if(dx < dxmin && dy < dymin){
            //this._color = _parent.color(0,255,0);
            isCollided = true;
            float angle = parent.atan2(dy, dx);

            if (!b.getBelongsUser()) {
                //collision reaction
                if (angle >= -parent.PI / 4 && angle <= parent.PI / 4) {
                    float vxTotal = vel.x - b.getVel().x;
                    vel.x = ((getMass() - b.getMass()) * vel.x + 2 * b.getMass() * b.getVel().x) / (getMass() + b.getMass());
                    b.setVelX(vxTotal + vel.x);
                    pos.x += vel.x;
                    b.setPosX(b.getPos().x + b.getVel().x);
                } else {
                    float vyTotal = vel.y - b.getVel().y;
                    vel.y = ((getMass() - b.getMass()) * vel.y + 2 * b.getMass() * b.getVel().y) / (getMass() + b.getMass());
                    b.setVelY(vyTotal + vel.y);
                    pos.y += vel.y;
                    b.setPosY(b.getPos().y + b.getVel().y);
                }
            }
        }
    }

	@Override
    void checkCollision(RectangularPrism p) {
        float dx = parent.abs(p.getPos().x - pos.x);
        float dy = parent.abs(p.getPos().y - pos.y);
        float dxmin = p.getDimensions().x / 2 + dimensions.x / 2;
        float dymin = p.getDimensions().y / 2 + dimensions.y / 2;

        if (dx < dxmin && dy < dymin) {
            //this._color = _parent.color(0,255,0);
            isCollided = true;
            float angle = parent.atan2(dy, dx);

            if (!p.getBelongsUser()) {
                //collision reaction
                if (angle >= -parent.PI / 4 && angle <= parent.PI / 4) {
                    float vxTotal = vel.x - p.getVel().x;
                    vel.x = ((getMass() - p.getMass()) * vel.x + 2 * p.getMass() * p.getVel().x) / (getMass() + p.getMass());
                    p.setVelX(vxTotal + vel.x);
                    pos.x += vel.x;
                    p.setPosX(p.getPos().x + p.getVel().x);
                } else {
                    float vyTotal = vel.y - p.getVel().y;
                    vel.y = ((getMass() - p.getMass()) * vel.y + 2 * p.getMass() * p.getVel().y) / (getMass() + p.getMass());
                    p.setVelY(vyTotal + vel.y);
                    pos.y += vel.y;
                    p.setPosY(p.getPos().y + p.getVel().y);
                }

            }
        }
        }
    
    @Override
    public GeometricFigure cloneFig(){
        PVector cvel = new PVector(vel.x, vel.y, vel.z);
        PVector cpos = new PVector(pos.x, pos.y, pos.z);
        PVector cPosRef = new PVector(getPosRef().x, getPosRef().y, getPosRef().y);
        PVector cDimensions = new PVector(getDimensions().x, getDimensions().y, getDimensions().z);
        Map<String,Object> cObservers = new HashMap<String,Object>();
        cObservers.putAll(getObservers());
                
        GeometricFigure clonedFig = null;
        try {
            clonedFig = (GeometricFigure)super.clone();
            clonedFig.setVel(cvel);
            clonedFig.setPos(cpos);
            clonedFig.setPosRef(cPosRef);
            ((RectangularPrism)clonedFig).setDimensions(cDimensions);
            clonedFig.setObservers(cObservers);
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Ball.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clonedFig;
    }

    public void setDimensionX(float x) {
        this.dimensions.x = x;
    }

    public void setDimensionY(float y) {
        this.dimensions.y = y;
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
