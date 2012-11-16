package pruebascuerpohumano;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import processing.core.PApplet;
import processing.core.PVector;

abstract class GeometricFigure implements ObservableGeometricFigure {

    protected PApplet parent;
    protected String name;
    protected int color;
    protected float mass;
    protected PVector pos;
    protected PVector vel;
    protected PVector posRef=new PVector(0,0,0);
    protected float deltaPosMax = 10;
    protected Map<String, Object>observers;
    
    public GeometricFigure(String name, int color, PApplet parent){
        this.name   = name;
        this.color  = color;
        this.parent = parent;
        observers = new HashMap<String, Object>();
    }
    
    public GeometricFigure(String name, int color, PVector pos, 
                           float m, PVector vel, PApplet parent) {
        this.parent = parent;
        this.name   = name;
        this.color  = color;
        this.pos     = pos;
        this.posRef = new PVector(pos.x,pos.y, pos.z);
        this.mass    = m;
        this.vel     = vel;
        observers = new HashMap<String, Object>();
    }

    abstract void paint();
    abstract void update(float friction);
    abstract void checkBoundaryCollision();
    abstract void checkCollision(Ball b);
    abstract void checkCollision(RectangularPrism p);
    
    void checkCollision(GeometricFigure fig) {
        if (fig instanceof Ball){
            checkCollision((Ball)fig);
        } else {
            checkCollision((RectangularPrism)fig);
        }
    }
    
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
        return name;
    }

    /**
     * @param name the _name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the _color
     */
    public int getColor() {
        return color;
    }

    /**
     * @param color the _color to set
     */
    public void setColor(int color) {
        this.color = color;
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

    public void setVel(PVector vel) {
        this.vel = vel;
    }
    
    public void setVelX(float vx){
        this.vel.x = vx;
    }
    
    public void setVelY(float vy){
        this.vel.y = vy;
    }

    @Override
    public void addObserver(Object obj) {
        observers.put(((GeometricFigure)obj).getName(), obj);
    }

    @Override
    public void removeObserver(Object obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
 
    protected void checkChangeState() {
        checkBoundaryCollision();
        if(posRef.dist(pos) >= deltaPosMax){
            posRef.set(pos);
            notifyAllObservers();
        }
    }

    private void notifyAllObservers() {
        GeometricFigure currentObserver;
        Iterator observer = observers.values().iterator();

        while (observer.hasNext()) {
            currentObserver = ((GeometricFigure) observer.next());
            currentObserver.inform(this);
        }
    }

    public void inform(GeometricFigure gf){
        checkCollision(gf);
    }
    
}