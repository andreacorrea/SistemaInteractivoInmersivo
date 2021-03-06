package GeometricFiguresManagement;

import Interaction.Command;
import java.util.ConcurrentModificationException;
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
    protected int minSpeed;

    public GeometricFigure(String name, int color, PApplet parent, float mass) {
        this.parent = parent;
        this.name = name;
        this.color = color;
        this.mass = mass;
        this.observers = new HashMap<String, Object>();
        this.belongsUser = true;
        this.minSpeed = 1;
    }

    public GeometricFigure(String name, int color, PVector pos, float m, PVector vel, PApplet parent) {
        this.parent = parent;
        this.name = name;
        this.color = color;
        this.pos = pos;
        this.posRef = new PVector(pos.x, pos.y, pos.z);
        this.mass = m;
        this.vel = vel;
        this.observers = new HashMap<String, Object>();
        this.belongsUser = false;
        this.minSpeed = 1;
    }

    public abstract void paint();

    public abstract void checkBoundaryCollision();

    public abstract boolean checkLowerBoundary();

    public abstract boolean checkRightBoundary();

    public abstract boolean checkLeftBoundary();

    public abstract boolean checkUpperBoundary();

    public abstract void bounceLowerBoundary();

    public abstract void bounceRightBoundary();

    public abstract void bounceLeftBoundary();

    public abstract void bounceUpperBoundary();

    public abstract boolean checkCollision(Ball b);

    public abstract boolean checkCollision(RectangularPrism p);

    public abstract void bounce(GeometricFigure gf);

    public abstract GeometricFigure cloneFig();

    public abstract void changeColor(GeometricFigure received);
    
    public void update(float friction, float gravity){
        if (vel.x > minSpeed) {
            vel.x *= friction;
        }
        vel.y += gravity;
        pos.x += vel.x;
        pos.y += vel.y;
    }

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

    @Override
    public void addObserver(Object obj) {
        getObservers().put(((GeometricFigure) obj).getName(), obj);
    }

    @Override
    public void removeObserver(Object obj) {
        observers.remove(((GeometricFigure) obj).getName());
    }

    public boolean checkChangeState(Command command) {
        checkBoundaryCollision();
        if (posRef.dist(pos) >= deltaPosMax) {
            posRef.set(pos);
            notifyAllObservers(command);
        }
        return posRef.dist(pos) >= deltaPosMax; 
    }

    @Override
    public void notifyAllObservers(Command command) {
        GeometricFigure currentObserver;
        Iterator observer = getObservers().values().iterator();

        while (observer.hasNext()) {
            try {
                currentObserver = ((GeometricFigure) observer.next());
                currentObserver.inform(this, command);
            } catch (ConcurrentModificationException e) {
                System.out.println(e.toString());
                return;
            }
        }
    }

    public void inform(GeometricFigure gf, Command command) {
        if (checkCollision(gf) && command != null) {
            command.setReceived(gf);
            command.execute(this);
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getMass() {
        return mass;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }

    public PVector getPos() {
        return pos;
    }

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

    public PVector getPosRef() {
        return posRef;
    }

    public void setPosRef(PVector posRef) {
        this.posRef = posRef;
    }

    public Map<String, Object> getObservers() {
        return observers;
    }

    public void setObservers(Map<String, Object> observers) {
        this.observers = observers;
    }

    public boolean getBelongsUser() {
        return belongsUser;
    }

    public int getMinSpeed() {
        return minSpeed;
    }

    public void setMinSpeed(int minSpeed) {
        this.minSpeed = minSpeed;
    }

}