/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebascuerpohumano;

import processing.core.PApplet;

/**
 *
 * @author samuel
 */
public class Ball extends GeometricFigure{
    
    private float radius;
    private float posZ;
    
    public Ball(String name, int color, float x, float y,
                float vx, float vy, float r, PApplet p){
        super(name, color, x, y, r*0.1f, vx, vy, p);
        radius = r;
        posZ=0;
    }
    
    @Override
    public void paint(){
        _parent.pushMatrix();
        _parent.translate(posX, posY, 0);
        _parent.sphere(radius);
        _parent.popMatrix();
    }
    
    @Override
    public void update(float friction){
        velX *= friction;
        velY *= friction;
        posX += velX;
        posY += velY;
    }
    
    @Override
    void checkBoundaryCollision() {
        if (posX > _parent.width-radius) {
            posX  = _parent.width-radius;
            velX *= -1;
        } 
        else if (posX < radius) {
            posX  = radius;
            velX *= -1;
        } 
        else if (posY > _parent.height-radius) {
            posY  = _parent.height-radius;
            velY *= -1;
        } 
        else if (posY < radius) {
            posY  = radius;
            velY *= -1;
        }
    }
    
    @Override
    void checkCollision(Ball b){
        float dx = b.getPosX() - posX;
        float dy = b.getPosY() - posY;
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
            float vx0 = velX * cosine + velY * sine;
            float vy0 = velY * cosine - velX * sine;
            
            //rotate b1's velocity
            float vx1 = b.getVelX() * cosine + b.getVelY() * sine;
            float vy1 = b.getVelY() * cosine - b.getVelX() * sine;
            
            //collision reaction
            float vxTotal = vx0 - vx1;
            vx0 = ((mass - b.getMass()) * vx0 + 2 * b.getMass() * vx1) / (mass + b.getMass());
            vx1 = vxTotal  + vx0;
            x0 += vx0;
            x1 += vx1;
            
            //rotate positions back
            float x0f = x0 * cosine - y0 * sine;
            float y0f = y0 * cosine + x0 * sine;
            float x1f = x1 * cosine - y1 * sine;
            float y1f = y1 * cosine + x1 * sine;
            
            //adjust positions to actual screen positions
            b.setPosX(posX + x1f);
            b.setPosY(posY + y1f);
            posX += x0f;
            posY += y0f;
            
            //rotate velocities back
            velX = vx0 * cosine - vy0 * sine;
            velY = vy0 * cosine + vx0 * sine;
            b.setVelX( vx1 * cosine - vy1 * sine );
            b.setVelY( vy1 * cosine + vx1 * sine );
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
