package GeometricFiguresManagement;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import processing.core.PApplet;
import processing.core.PVector;

public class Ball extends GeometricFigure {

    private float radius;
    //private boolean isCollided = false;

    public Ball(PApplet parent, String pname, int pcolor, float mass) {
        super(pname, pcolor, parent, mass);
    }

    public Ball(String name, int pcolor, PVector pos, float r, PVector vel, PApplet parent) {
        super(name, pcolor, pos, r * 0.1f, vel, parent);
        radius = r;
    }

    @Override
    public void paint() {
        parent.noStroke();
        parent.pushMatrix();
        parent.translate(pos.x, pos.y, pos.z);
        /*if(isCollided){
         parent.fill(0,255,0);
         }else{
         parent.fill(255,255,255);
         }*/
        parent.fill(color);
        parent.sphere(radius);
        parent.popMatrix();
        //isCollided=false;
    }  

    @Override
    public void checkBoundaryCollision() {
        if (checkRightBoundary()) {
            if(GeometricFiguresManager.getInstance(parent).getCollisionRightBoundaryCommand()!= null){
                GeometricFiguresManager.getInstance(parent).getCollisionRightBoundaryCommand().execute(this);
            }else{
                bounceRightBoundary();
            }
        } else if (checkLeftBoundary()) {
            if(GeometricFiguresManager.getInstance(parent).getCollisionLeftBoundaryCommand()!= null){
                GeometricFiguresManager.getInstance(parent).getCollisionLeftBoundaryCommand().execute(this);
            }else{
                bounceLeftBoundary();
            }
        } else if (checkLowerBoundary()) {
            if(GeometricFiguresManager.getInstance(parent).getCollisionLowerBoundaryCommand()!= null){
                GeometricFiguresManager.getInstance(parent).getCollisionLowerBoundaryCommand().execute(this);
            }else{
                bounceLowerBoundary();
            }
        } else if (checkUpperBoundary()) {
            if(GeometricFiguresManager.getInstance(parent).getCollisionUpperBoundaryCommand()!= null){
                GeometricFiguresManager.getInstance(parent).getCollisionUpperBoundaryCommand().execute(this);
            }else{
                bounceUpperBoundary();
            }
        }
    }

    @Override
    public boolean checkCollision(Ball b) {
        float dx = b.getPos().x - pos.x;
        float dy = b.getPos().y - pos.y;
        float dist = parent.sqrt(dx * dx + dy * dy);
        float minDist = radius + b.getRadius();
        return dist < minDist;
    }

    @Override
    public boolean checkCollision(RectangularPrism p) {
        @SuppressWarnings("static-access")
        float dx = parent.abs(p.getPos().x - pos.x);
        float dy = parent.abs(p.getPos().y - pos.y);
        float dxmin = radius + p.getDimensions().x / 2;
        float dymin = radius + p.getDimensions().y / 2;
        return dx < dxmin && dy < dymin;
    }

    @Override
    public GeometricFigure cloneFig() {
        PVector cvel = new PVector(vel.x, vel.y, vel.z);
        PVector cpos = new PVector(pos.x, pos.y, pos.z);
        PVector cPosRef = new PVector(getPosRef().x, getPosRef().y, getPosRef().y);
        Map<String, Object> cObservers = new HashMap<String, Object>();
        cObservers.putAll(getObservers());

        GeometricFigure clonedFig = null;
        try {
            clonedFig = (GeometricFigure) super.clone();
            clonedFig.setVel(cvel);
            clonedFig.setPos(cpos);
            clonedFig.setPosRef(cPosRef);
            clonedFig.setObservers(cObservers);
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Ball.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clonedFig;
    }

    @Override
    public void bounce(GeometricFigure gf) {
        if (gf instanceof Ball) {
            bounceBall((Ball) gf);
        } else {
            bounceRectangularPrism((RectangularPrism) gf);
        }
    }

    public void bounceBall(Ball b) {
        float dx = b.getPos().x - pos.x;
        float dy = b.getPos().y - pos.y;

        //isCollided = true;
        //Calculate angle, sine, and cosine
        float angle = parent.atan2(dy, dx);
        float sine = parent.sin(angle);
        float cosine = parent.cos(angle);

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
        vx1 = vxTotal + vx0;
        x0 += vx0;
        x1 += vx1;

        //guarantee minimun distance
        /*float auxDx = _parent.abs(x1 - x0);
         if( auxDx <= minDist ){
         float incX = (minDist - auxDx)/2;
         if(vx0 < 1){
         x0 -= incX;
         } else {
         x0 += incX;
         }
         if(vx1 < 1){
         x1 -= incX;
         } else {
         x1 += incX;
         }
         }*/

        //rotate positions back
        float x0f = x0 * cosine - y0 * sine;
        float y0f = y0 * cosine + x0 * sine;
        float x1f = x1 * cosine - y1 * sine;
        float y1f = y1 * cosine + x1 * sine;

        if (!b.getBelongsUser()) {
            //adjust positions to actual screen positions
            b.setPosX(pos.x + x1f);
            b.setPosY(pos.y + y1f);
            pos.x += x0f;
            pos.y += y0f;

            //rotate velocities back
            vel.x = vx0 * cosine - vy0 * sine;
            vel.y = vy0 * cosine + vx0 * sine;
            b.setVelX(vx1 * cosine - vy1 * sine);
            b.setVelY(vy1 * cosine + vx1 * sine);
        }
    }

    public void bounceRectangularPrism(RectangularPrism p) {
        //this._color = _parent.color(0,255,0);
        float dx = parent.abs(p.getPos().x - pos.x);
        float dy = parent.abs(p.getPos().y - pos.y);

        //isCollided = true;
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

    @Override
    public void changeColor(GeometricFigure received) {
        {
            if (!this.belongsUser) {
                if (color == 0) {
                    color = parent.color(255, 0, 255);
                } else {
                    color = 0;
                }

            }
            if (!received.getBelongsUser()) {
                if (received.getColor() == 0) {
                    received.setColor(parent.color(255, 0, 255));
                } else {
                    received.setColor(0);
                }
            }
        }
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float r) {
        this.radius = r;
    }

    @Override
    public boolean checkLowerBoundary() {
        return pos.y > parent.height - radius;
    }

    @Override
    public boolean checkRightBoundary() {
        return pos.x > parent.width - radius;
    }

    @Override
    public boolean checkLeftBoundary() {
        return pos.x < radius;
    }

    @Override
    public boolean checkUpperBoundary() {
        return pos.y < radius-GeometricFiguresManager.getInstance(parent).getUpperLimit();
    }

    @Override
    public void bounceLowerBoundary() {
        pos.y = parent.height - radius;
        vel.y *= -1;
    }

    @Override
    public void bounceRightBoundary() {
        pos.x = parent.width - radius;
        vel.x *= -1;
    }

    @Override
    public void bounceLeftBoundary() {
        pos.x = radius;
        vel.x *= -1;
    }

    @Override
    public void bounceUpperBoundary() {
        pos.y = radius-GeometricFiguresManager.getInstance(parent).getUpperLimit();
        vel.y *= -1;
    }
}
