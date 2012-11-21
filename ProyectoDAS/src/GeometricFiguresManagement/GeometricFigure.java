package GeometricFiguresManagement;

import Interaction.Command;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import processing.core.PApplet;
import processing.core.PVector;

public abstract class GeometricFigure implements ObservableGeometricFigure, Cloneable {

    protected PApplet parent;
    protected String name;
    protected int color;
    protected float mass;
    protected PVector pos = new PVector();
    protected PVector vel = new PVector();
    protected PVector posRef = new PVector(0, 0, 0);
    protected float deltaPosMax = 1;
    protected Map<String, Object> observers;
    protected boolean belongsUser = false;
    
    
    

    public GeometricFigure(String name, int color, PApplet parent, float mass) {
        this.parent = parent;
        this.name = name;
        this.color = color;
        this.mass = mass;
        this.observers = new HashMap<String, Object>();
        this.belongsUser = true;
    }

    public GeometricFigure(String name, int color, PVector pos,
            float m, PVector vel, PApplet parent) {
        this.parent = parent;
        this.name = name;
        this.color = color;
        this.pos = pos;
        this.posRef = new PVector(pos.x, pos.y, pos.z);
        this.mass = m;
        this.vel = vel;
        this.observers = new HashMap<String, Object>();
        this.belongsUser = false;
    }

    public abstract void paint();

    public abstract void update(float friction);

    public abstract void checkBoundaryCollision();

    public abstract boolean checkCollision(Ball b);

    public abstract boolean checkCollision(RectangularPrism p);
    
    public abstract void bounce(GeometricFigure gf);

    public abstract GeometricFigure cloneFig();

    public void calculateVel(PVector finalPos) {
        PVector auxFinalPos = new PVector();
        auxFinalPos.set(finalPos);
        auxFinalPos.sub(this.pos);
        this.vel = auxFinalPos;
    }

    public boolean checkCollision(GeometricFigure fig) {
        if (fig instanceof Ball) {
            return checkCollision((Ball) fig);
                
        } else {
            return checkCollision((RectangularPrism) fig);
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
        calculateVel(pos);
        this.pos = pos;
    }

    public void setPosX(float x) {
        this.pos.x = x;
    }

    public void setPosY(float y) {
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

    public void setVelX(float vx) {
        this.vel.x = vx;
    }

    public void setVelY(float vy) {
        this.vel.y = vy;
    }

    @Override
    public void addObserver(Object obj) {
        getObservers().put(((GeometricFigure) obj).getName(), obj);
    }

    @Override
    public void removeObserver(Object obj) {
        observers.remove(((GeometricFigure)obj).getName());
    }

    public void checkChangeState(Command command) {
        checkBoundaryCollision();
        if(posRef.dist(pos) >= deltaPosMax){
            posRef.set(pos);
            notifyAllObservers(command);
        }
    }

    
    @Override
    public void notifyAllObservers(Command command) {
        GeometricFigure currentObserver;
        Iterator observer = getObservers().values().iterator();

        while (observer.hasNext()) {
            currentObserver = ((GeometricFigure) observer.next());
            currentObserver.inform(this, command);
        }
    }

    public void inform(GeometricFigure gf, Command command) {
        if(checkCollision(gf) && command != null){
            command.setReceiver(this);
            command.setReceived(gf);
            command.execute();
        }
        
    }

    /**
     * @return the posRef
     */
    public PVector getPosRef() {
        return posRef;
    }

    /**
     * @param posRef the posRef to set
     */
    public void setPosRef(PVector posRef) {
        this.posRef = posRef;
    }

    /**
     * @return the observers
     */
    public Map<String, Object> getObservers() {
        return observers;
    }

    /**
     * @param observers the observers to set
     */
    public void setObservers(Map<String, Object> observers) {
        this.observers = observers;
    }

    public boolean getBelongsUser() {
        return belongsUser;
    }

    public abstract void changeColor(GeometricFigure received);
}