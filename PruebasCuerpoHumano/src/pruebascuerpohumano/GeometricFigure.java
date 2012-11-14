/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectodas;

import processing.core.PApplet;
import processing.core.PVector;

/**
 *
 * @author Samuel
 */
abstract class GeometricFigure {

    protected PApplet _parent;
    protected String _name;
    protected int _color;
    protected float posX, posY, mass, velX , velY;
    
    GeometricFigure(String name, int color, float x, float y, 
                    float m, float vx, float vy, PApplet p) {
        _parent= p;
        _name = name;
        _color = color;
        posX = x;
        posY = y;
        mass = m;
        velX = vx;
        velY = vy;
    }

    void drawBodyMember() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    abstract void paint();
    abstract void update(float friction);
    abstract void checkBoundaryCollision();
    abstract void checkCollision(Ball b);
    
    public PVector calculateMiddlePoint(PVector p1, PVector p2) {
        PVector auxP1 = p1.get();
        PVector auxP2 = p2.get();
        auxP1.sub(auxP2);
        auxP1.div(2);
        auxP2.add(auxP1);
        return auxP2;
    }  

    /**
     * @return the posX
     */
    public float getPosX() {
        return posX;
    }

    /**
     * @param posX the posX to set
     */
    public void setPosX(float posX) {
        this.posX = posX;
    }

    /**
     * @return the posY
     */
    public float getPosY() {
        return posY;
    }

    /**
     * @param posY the posY to set
     */
    public void setPosY(float posY) {
        this.posY = posY;
    }

    /**
     * @return the mass
     */
    public float getMass() {
        return mass;
    }

    /**
     * @param mass the mass to set
     */
    public void setMass(float mass) {
        this.mass = mass;
    }

    /**
     * @return the velX
     */
    public float getVelX() {
        return velX;
    }

    /**
     * @param velX the velX to set
     */
    public void setVelX(float velX) {
        this.velX = velX;
    }

    /**
     * @return the velY
     */
    public float getVelY() {
        return velY;
    }

    /**
     * @param velY the velY to set
     */
    public void setVelY(float velY) {
        this.velY = velY;
    }
    
}