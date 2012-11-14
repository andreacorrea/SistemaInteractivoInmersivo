package pruebascuerpohumano;

import processing.core.PApplet;
import processing.core.PVector;

/*
 * Esta clase representa el torso del cuerpo humano
 */
public class Torso extends BodyMember {

    private PVector _leftUpperJointPos;
    private PVector _leftLowerJointPos;
    private PVector _rightUpperJointPos;
    private PVector _rightLowerJointPos;
    private PVector _upperMiddlePoint;
    private PVector _lowerMiddlePoint;
    private PVector _leftMiddlePoint;
    private PVector _rightMiddlePoint;

    public Torso(PApplet p, String name, int memberColor) {
        //variables de interfaz    
        _parent = p;
        _name = name;
        _memberColor = memberColor;
    }

    /*public boolean isPointInsideLimb(PVector point){
     throw new UnsupportedOperationException("Not yet implemented");
     //return false;
     }*/
    public void update(PVector leftUpperJointPos, PVector rightUpperJointPos, PVector leftLowerJointPos, PVector rightLowerJointPos) {
        _leftUpperJointPos = leftUpperJointPos;
        _rightUpperJointPos = rightUpperJointPos;
        _leftLowerJointPos = leftLowerJointPos;
        _rightLowerJointPos = rightLowerJointPos;

        calculateMiddlePoints();
        calculatePosition();
        calculateRotationZ();
        calculateRotationY();
        calculateMemberDimensions();

        /*_parent.println("Name of Torso: " + _name);
        _parent.println("UpperMiddlePoint: " + _upperMiddlePoint);
        _parent.println("LowerMiddlePoint: " + _lowerMiddlePoint);
        _parent.println("Position: " + _position);
        _parent.println("RotationZ radians: " + _rotationZ);
        _parent.println("RotationZ degrees: " + _parent.degrees(_rotationZ));
        _parent.println("RotationY radians: " + _rotationY);
        _parent.println("RotationY degrees: " + _parent.degrees(_rotationY));
        _parent.println("MemberHeight: " + _memberHeight);*/
        //drawBodyMember();
    }

    public void calculateMiddlePoints() {
        _leftLowerJointPos.z = _leftUpperJointPos.z;
        _rightLowerJointPos.z = _rightUpperJointPos.z;
        _upperMiddlePoint = calculateMiddlePoint(_leftUpperJointPos, _rightUpperJointPos);
        _lowerMiddlePoint = calculateMiddlePoint(_leftLowerJointPos, _rightLowerJointPos);
        
        _leftMiddlePoint = calculateMiddlePoint(_leftUpperJointPos, _leftLowerJointPos);
        
        _rightMiddlePoint = calculateMiddlePoint(_rightUpperJointPos, _rightLowerJointPos);
        /*_parent.println("_leftMiddlePoint; " + _leftMiddlePoint);
        _parent.println("_rightMiddlePoint: " + _rightMiddlePoint);*/

    }

    @Override
    public void calculatePosition() {
        PVector middlePoint1 = calculateMiddlePoint(_leftUpperJointPos, _rightLowerJointPos);
        PVector middlePoint2 = calculateMiddlePoint(_rightUpperJointPos, _leftLowerJointPos);

        //_position.set((middlePoint1.x + middlePoint2.x) / 2, (middlePoint1.y + middlePoint2.y) / 2, -(middlePoint1.z + middlePoint2.z) / 2);
        _position.set((middlePoint1.x + middlePoint2.x) / 2, (middlePoint1.y + middlePoint2.y) / 2, 0);
    }

    @Override
    public void calculateRotationZ() {
        PVector aux = new PVector(_upperMiddlePoint.x - _lowerMiddlePoint.x, -(_upperMiddlePoint.y - _lowerMiddlePoint.y), 0);
        //_parent.println("aux = " + aux);
        _rotationZ = _parent.atan2(aux.x, aux.y);
        /*_parent.println("rotationZ ini = " + rotationZ);
         _parent.println("rotationZ ini degrees = " + degrees(rotationZ));*/
        if (_rotationZ < 0) {
            _rotationZ = _parent.TWO_PI + _rotationZ;
        }
        /*_parent.println(" aux.y= " + aux.y + " / aux.x = " + aux.x);
         _parent.println("rotationZ = " + rotationZ);
         _parent.println("rotationZ degrees = " + degrees(rotationZ));*/
    }

    public void calculateRotationY() {
        PVector nearVector;
        PVector farVector;
        if (_parent.abs(_leftMiddlePoint.z) <= _parent.abs(_rightMiddlePoint.z)) {
            nearVector = _leftMiddlePoint;
            farVector = _rightMiddlePoint;
        } else {
            nearVector = _rightMiddlePoint;
            farVector = _leftMiddlePoint;
        }
        PVector aux = new PVector(farVector.x - nearVector.x, 0, farVector.z - nearVector.z);
        //_parent.println("aux rotationY = " + aux);
        _rotationY = _parent.atan2(aux.z, aux.x);
        //_parent.println("rotationY radianes= " + _rotationY);
        //_parent.println("rotationY grados= " + _parent.degrees(_rotationY));
        /*println("rotationZ ini = " + rotationZ);
         println("rotationZ ini degrees = " + degrees(rotationZ));*/
        if (_rotationY < 0) {
            _rotationY = _parent.TWO_PI + _rotationZ;
        }
        /*println(" aux.y= " + aux.y + " / aux.x = " + aux.x);
         println("rotationZ = " + rotationZ);
         println("rotationZ degrees = " + degrees(rotationZ));*/
    }

    @Override
    public void calculateMemberDimensions() {
        _memberHeight = _upperMiddlePoint.dist(_lowerMiddlePoint);
        //_memberWidth = _leftMiddlePoint.dist(_rightMiddlePoint);
        _memberWidth = (_leftUpperJointPos.dist(_rightUpperJointPos) + _leftLowerJointPos.dist(_rightLowerJointPos)) / 2;
        /*_parent.println("\t\t\t\t\t\t\t_leftMiddlePoint: " + _leftMiddlePoint);
         _parent.println("\t\t\t\t\t\t\t_rightMiddlePoint: " + _rightMiddlePoint);*/
        /*_parent.println("\t\t\t\t\t\t\t_leftUpperJointPos: " + _leftUpperJointPos);
        _parent.println("\t\t\t\t\t\t\t_rightUpperJointPos: " + _rightUpperJointPos);
        _parent.println("\t\t\t\t\t\t\t_leftLowerJointPos: " + _leftLowerJointPos);
        _parent.println("\t\t\t\t\t\t\t_rightLowerJointPos: " + _rightLowerJointPos);*/
        //_parent.println("_memberWidth: " + _memberWidth);
    }

    public PVector getRightUpperJointPos() {
        return _rightUpperJointPos;
    }

    public PVector getUpperMiddlePoint() {
        return _upperMiddlePoint;
    }

    public PVector getLowerMiddlePoint() {
        return _lowerMiddlePoint;
    }

    public PVector getRightMiddlePoint() {
        return _rightMiddlePoint;
    }
}