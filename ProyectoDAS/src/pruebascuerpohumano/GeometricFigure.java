/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebascuerpohumano;

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
    protected float mass;
    protected PVector pos;
    protected PVector vel;
    
    public GeometricFigure(String pname, int pcolor, PApplet pparent){
        _name   = pname;
        _color  = pcolor;
        _parent = pparent;
    }
    
    public GeometricFigure(String name, int color, PVector ppos, 
                    float m, PVector pvel, PApplet p) {
        _parent = p;
        _name   = name;
        _color  = color;
        pos     = ppos;
        mass    = m;
        vel     = pvel;
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
     * @return the _name
     */
    public String getName() {
        return _name;
    }

    /**
     * @param name the _name to set
     */
    public void setName(String name) {
        this._name = name;
    }

    /**
     * @return the _color
     */
    public int getColor() {
        return _color;
    }

    /**
     * @param color the _color to set
     */
    public void setColor(int color) {
        this._color = color;
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
     * @return the pos
     */
    public PVector getPos() {
        return pos;
    }

    /**
     * @param pos the pos to set
     */
    public void setPos(PVector pos) {
        this.pos = pos;
    }
    
    public void setPosX(float x){
        this.pos.x = x;
    }
    
    public void setPosY(float y){
        this.pos.y = y;
    }

    /**
     * @return the vel
     */
    public PVector getVel() {
        return vel;
    }

    /**
     * @param vel the vel to set
     */
    public void setVel(PVector vel) {
        this.vel = vel;
    }
    
    public void setVelX(float vx){
        this.vel.x = vx;
    }
    
    public void setVelY(float vy){
        this.vel.y = vy;
    }
    
}