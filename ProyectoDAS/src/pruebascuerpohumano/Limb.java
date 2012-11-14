package pruebascuerpohumano;

import processing.core.*;

/*
 * Esta clase representa una extremidad del cuerpo humano, menos el torso
 */
public class Limb extends BodyMember {

    private PVector _upperJointPos;
    private PVector _lowerJointPos;
    private Body _body;

    public Limb(PApplet p, String name, int memberColor, Body body) {
        //variables de instancia
        _body = body;
        _parent = p;
        _name = name;
        _memberColor = memberColor;
        
    }

    /*public boolean isPointInsideLimb(PVector point){
     throw new UnsupportedOperationException("Not yet implemented");
     //return false;
     }*/
    public void update(PVector upperJointPos, PVector lowerJointPos) {
        _upperJointPos = upperJointPos;
        _lowerJointPos = lowerJointPos;
        _parent.println("upperJointPos= " + upperJointPos);
        _parent.println("lowerJointPos= " + lowerJointPos);
        //PENDIENTE IMPLEMENTACION
        calculatePosition();
        calculateRotationZ();
        calculateRotationY();
        //calculateRotationX();
        calculateMemberDimensions();
        
        //drawBodyMember();
    }

    @Override
    public void calculatePosition() {
        _position.set(calculateMiddlePoint(_lowerJointPos, _upperJointPos));
        _position.z=-(_position.z-_body.getPositionOffset());
    }

    @Override
    public void calculateRotationZ() {
        PVector aux = new PVector(_upperJointPos.x - _lowerJointPos.x, -(_upperJointPos.y - _lowerJointPos.y), 0);
        //_parent.println("aux = " + aux);
        //rotationZ = atan2(aux.y, aux.x);
        //_rotationZ = _parent.atan2(aux.x, aux.y);
        _rotationZ = _parent.atan2(aux.y, -aux.x);
        
        if (_rotationZ < 0) {
            _rotationZ = _parent.TWO_PI + _rotationZ;
        }
    }
    
    public void calculateRotationY() {
        PVector aux = new PVector(_upperJointPos.x - _lowerJointPos.x, 0, _upperJointPos.z - _lowerJointPos.z);
        //_parent.println("aux rotationY = " + aux);
        _rotationY = _parent.atan2(aux.z, aux.x);
        /*_parent.println("rotationY radianes= " + _rotationY);
        _parent.println("rotationY grados= " + _parent.degrees(_rotationY));*/
        
        if (_rotationY < 0) {
            _rotationY = _parent.TWO_PI + _rotationY;
        }
        if(_rotationZ > _parent.radians(90) && _rotationZ < _parent.radians(270)){
            //_rotationY+=_parent.radians(90);
            _rotationY=-_rotationY;
        }
        
        
        /*_parent.println("rotationY radianes FINAL= " + _rotationY);
        _parent.println("rotationY grados FINAL= " + _parent.degrees(_rotationY));*/        
    }

    @Override
    public void calculateMemberDimensions() {
        _memberWidth = _upperJointPos.dist(_lowerJointPos);
    }
}
