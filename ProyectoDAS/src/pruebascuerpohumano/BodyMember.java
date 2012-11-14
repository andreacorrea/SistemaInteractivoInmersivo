package pruebascuerpohumano;

import processing.core.*;

/*
 * Esta clase representa cualquier extremidad del cuerpo humano
 */
public abstract class BodyMember {

    protected PApplet _parent; // The parent PApplet that we will render ourselves onto
    protected String _name = "";
    protected PVector _position = new PVector();
    protected float _memberHeight = 20.0f;
    protected float _memberWidth = 0;
    protected float _memberDepth = 20;
    protected float _rotationZ = 0.0f; //angulo en radianes, el eje x negativo es 0 grados y en sentido del reloj es positivo
    protected float _rotationY=0.0f; //angulo en radianes, el eje x positivo es 0 grados y en contra sentido del reloj es positivo
    protected PVector _speed = new PVector();
    protected PVector _acceleration = new PVector();
    protected int _memberColor;

    //To implement
    public abstract void calculatePosition();

    public abstract void calculateRotationZ();

    public abstract void calculateMemberDimensions();
    //public abstract boolean isPointInsideLimb(PVector point);
    //public abstract void update();

    public void drawBodyMember() {
        
      _parent.pushMatrix();
        _parent.fill(_memberColor);
        /*_parent.println("Nombre: " + _name);
        _parent.println("Position: " + _position);*/
        
        _parent.translate(_position.x, _position.y, _position.z);
        /*_parent.println("RotationZ radians: " + _rotationZ);
        _parent.println("RotationZ degrees: " + _parent.degrees(_rotationZ));*/
        /*_parent.println("RotationY radians: " + _rotationY);
        _parent.println("RotationY degrees: " + _parent.degrees(_rotationY));*/
        
        _parent.rotateZ(_rotationZ);
        _parent.rotateY(_rotationY);
        
        //_parent.println(_name + ": RotationY radians: " + _rotationY);
        _parent.println(_name + ": RotationZ degrees: " + _parent.degrees(_rotationZ));
        _parent.println(_name + ": RotationY degrees: " + _parent.degrees(_rotationY));
        //_parent.rotateY(_rotationY);
        //_parent.rotateY(radians(45));
        /*_parent.println("RotationZ radians: " + _rotationZ);
        _parent.println("RotationZ degrees: " + _parent.degrees(_rotationZ));*/
        
          
        //_parent.rotateX(_rotationX);
        //_parent.println("MemberWidth: " + _memberWidth);
        //_parent.println("MemberHeight: " + _memberHeight);
        _parent.box(_memberWidth, _memberHeight, _memberDepth);
        _parent.popMatrix();
    }

    public PVector calculateMiddlePoint(PVector p1, PVector p2) {
        PVector auxP1 = p1.get();
        PVector auxP2 = p2.get();
        auxP1.sub(auxP2);
        auxP1.div(2);
        auxP2.add(auxP1);
        return auxP2;
    }

    public PVector getPosition() {
        return _position;
    }

    public float getMemberHeight() {
        return _memberHeight;
    }

    public float getMemberWidth() {
        return _memberWidth;
    }

    public float getMemberDepth() {
        return _memberDepth;
    }

    public float getRotationZ() {
        return _rotationZ;
    }
}
