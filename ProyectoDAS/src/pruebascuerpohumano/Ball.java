/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebascuerpohumano;

import processing.core.PApplet;
import processing.core.PVector;

/**
 *
 * @author samuel
 */
public class Ball extends GeometricFigure{
    
    private float radius;
    private boolean isCollided = false;
    
    public Ball(String pname, int pcolor, PApplet pparent){
        super(pname, pcolor, pparent);
    }
    
    public Ball(String pname, int pcolor, PVector ppos, float pr, PVector pvel, PApplet pparent){
        super(pname, pcolor, ppos, pr*0.1f, pvel, pparent);
        radius = pr;
    }
    
    @Override
    public void paint(){
        _parent.pushMatrix();
        _parent.stroke(0,0,0);
        _parent.translate(pos.x, pos.y, pos.z);
        if(isCollided){
            _parent.fill(0,255,0);
        }else{
            _parent.fill(255,255,255);
        }
        _parent.sphere(radius);
        _parent.popMatrix();
        isCollided=false;
    }
    
    @Override
    public void update(float friction){
        vel.x *= friction;
        vel.y *= friction;
        pos.x += vel.x;
        pos.y += vel.y;
    }
    
    @Override
    void checkBoundaryCollision() {
        if (pos.x > _parent.width-radius) {
            pos.x  = _parent.width-radius;
            vel.x *= -1;
        } 
        else if (pos.x < radius) {
            pos.x  = radius;
            vel.x *= -1;
        } 
        else if (pos.y > _parent.height-radius) {
            pos.y  = _parent.height-radius;
            vel.y *= -1;
        } 
        else if (pos.y < radius) {
            pos.y  = radius;
            vel.y *= -1;
        }
    }
    
    @Override
    void checkCollision(Ball b){
        float dx = b.getPos().x - pos.x;
        float dy = b.getPos().y - pos.y;
        float dist = _parent.sqrt(dx*dx + dy*dy);
        
        if(dist < radius + b.getRadius()){
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
            b.setVelY( vy1 * cosine + vx1 * sine );
        }
    }
    
    void checkCollision(RectangularPrism p){
        float dx = _parent.abs( p.getPos().x - pos.x );
        float dy = _parent.abs( p.getPos().y - pos.y );
        float dxmin = radius + p.getDimensions().x/2;
        float dymin = radius + p.getDimensions().y/2;
        
        if(dx < dxmin && dy < dymin){
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
            float vx1 = p.getVel().x * cosine + p.getVel().y * sine;
            float vy1 = p.getVel().y * cosine - p.getVel().x * sine;
            
            //collision reaction
            float vxTotal = vx0 - vx1;
            vx0 = ((getMass() - p.getMass()) * vx0 + 2 * p.getMass() * vx1) / (getMass() + p.getMass());
            vx1 = vxTotal  + vx0;
            x0 += vx0;
            x1 += vx1;
            
            //rotate positions back
            float x0f = x0 * cosine - y0 * sine;
            float y0f = y0 * cosine + x0 * sine;
            float x1f = x1 * cosine - y1 * sine;
            float y1f = y1 * cosine + x1 * sine;
            
            //adjust positions to actual screen positions
            p.setPosX(pos.x + x1f);
            p.setPosY(pos.y + y1f);
            pos.x += x0f;
            pos.y += y0f;
            
            //rotate velocities back
            vel.x = vx0 * cosine - vy0 * sine;
            vel.y = vy0 * cosine + vx0 * sine;
            p.setVelX( vx1 * cosine - vy1 * sine );
            p.setVelY( vy1 * cosine + vx1 * sine );
            */
            /*vel.x *= -1;
            vel.y *= -1;
            p.setVelX( p.getVel().x * (-1) );
            p.setVelY( p.getVel().y * (-1) );*/
        } 
    }

    /**
     * @return the r
     */
    public float getRadius() {
        return radius;
    }

    /**
     * @param r the r to set
     */
    public void setRadius(float r) {
        this.radius = r;
    }

}
