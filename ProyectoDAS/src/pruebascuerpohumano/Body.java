package pruebascuerpohumano;

import processing.core.*;
import SimpleOpenNI.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/*
 * Esta clase representa al cuerpo de un usuario
 */
public class Body {

    private PApplet _parent;
    private User _user;
    private SimpleOpenNI _context;
    private Map<String, GeometricFigure> _bodyMembers;
    private int _bodyColor;
    private float positionOffset;

    public Body(PApplet p, User user, int bodyColor) {
        _parent = p;
        _user = user;
        _context = user.getContext();
        _bodyColor = bodyColor;
        _bodyMembers = new HashMap<String, GeometricFigure>();

        createSkeleton();
    }

    public void createSkeleton() {
        _bodyMembers.put("HEAD", new Ball("HEAD", _bodyColor, 0, 0, 0, 0, 20, _parent));
        _bodyMembers.put("TORSO", new RectangularPrism(_parent, "TORSO", _bodyColor));
        _bodyMembers.put("LEFT_FOREARM", new RectangularPrism(_parent, "LEFT_FOREARM", _bodyColor));
        _bodyMembers.put("LEFT_ARM", new RectangularPrism(_parent, "LEFT_ARM", _bodyColor));
        _bodyMembers.put("RIGHT_FOREARM", new RectangularPrism(_parent, "RIGHT_FOREARM", _bodyColor));
        _bodyMembers.put("RIGHT_ARM", new RectangularPrism(_parent, "RIGHT_ARM", _bodyColor));
        _bodyMembers.put("LEFT_THIGH", new RectangularPrism(_parent, "LEFT_THIGH", _bodyColor));
        _bodyMembers.put("LEFT_LEG", new RectangularPrism(_parent, "LEFT_LEG", _bodyColor));
        _bodyMembers.put("RIGHT_THIGH", new RectangularPrism(_parent, "RIGHT_THIGH", _bodyColor));
        _bodyMembers.put("RIGHT_LEG", new RectangularPrism(_parent, "RIGHT_LEG", _bodyColor));

    }

    public void update() {
        updateMembers();
        drawSkeletonMembers();
    }

    public void drawSkeletonLines() {
        _context.drawLimb(_user.getUserId(), SimpleOpenNI.SKEL_HEAD, SimpleOpenNI.SKEL_NECK);

        _context.drawLimb(_user.getUserId(), SimpleOpenNI.SKEL_NECK, SimpleOpenNI.SKEL_LEFT_SHOULDER);
        _context.drawLimb(_user.getUserId(), SimpleOpenNI.SKEL_LEFT_SHOULDER, SimpleOpenNI.SKEL_LEFT_ELBOW);
        _context.drawLimb(_user.getUserId(), SimpleOpenNI.SKEL_LEFT_ELBOW, SimpleOpenNI.SKEL_LEFT_HAND);

        _context.drawLimb(_user.getUserId(), SimpleOpenNI.SKEL_NECK, SimpleOpenNI.SKEL_RIGHT_SHOULDER);
        _context.drawLimb(_user.getUserId(), SimpleOpenNI.SKEL_RIGHT_SHOULDER, SimpleOpenNI.SKEL_RIGHT_ELBOW);
        _context.drawLimb(_user.getUserId(), SimpleOpenNI.SKEL_RIGHT_ELBOW, SimpleOpenNI.SKEL_RIGHT_HAND);

        _context.drawLimb(_user.getUserId(), SimpleOpenNI.SKEL_LEFT_SHOULDER, SimpleOpenNI.SKEL_TORSO);
        _context.drawLimb(_user.getUserId(), SimpleOpenNI.SKEL_RIGHT_SHOULDER, SimpleOpenNI.SKEL_TORSO);

        _context.drawLimb(_user.getUserId(), SimpleOpenNI.SKEL_TORSO, SimpleOpenNI.SKEL_LEFT_HIP);
        _context.drawLimb(_user.getUserId(), SimpleOpenNI.SKEL_LEFT_HIP, SimpleOpenNI.SKEL_LEFT_KNEE);
        _context.drawLimb(_user.getUserId(), SimpleOpenNI.SKEL_LEFT_KNEE, SimpleOpenNI.SKEL_LEFT_FOOT);

        _context.drawLimb(_user.getUserId(), SimpleOpenNI.SKEL_TORSO, SimpleOpenNI.SKEL_RIGHT_HIP);
        _context.drawLimb(_user.getUserId(), SimpleOpenNI.SKEL_RIGHT_HIP, SimpleOpenNI.SKEL_RIGHT_KNEE);
        _context.drawLimb(_user.getUserId(), SimpleOpenNI.SKEL_RIGHT_KNEE, SimpleOpenNI.SKEL_RIGHT_FOOT);

    }

    public void drawJoints() {
        _parent.pushStyle();
        _parent.strokeWeight(10);
        PVector currentJoint = new PVector();
        currentJoint = getJointPos(SimpleOpenNI.SKEL_HEAD);
        _parent.point(currentJoint.x, currentJoint.y, 0);
        currentJoint = getJointPos(SimpleOpenNI.SKEL_NECK);
        _parent.point(currentJoint.x, currentJoint.y, 0);
        currentJoint = getJointPos(SimpleOpenNI.SKEL_LEFT_SHOULDER);
        _parent.point(currentJoint.x, currentJoint.y, 0);
        currentJoint = getJointPos(SimpleOpenNI.SKEL_LEFT_ELBOW);
        _parent.point(currentJoint.x, currentJoint.y, 0);
        currentJoint = getJointPos(SimpleOpenNI.SKEL_LEFT_HAND);
        _parent.point(currentJoint.x, currentJoint.y, 0);
        currentJoint = getJointPos(SimpleOpenNI.SKEL_RIGHT_SHOULDER);
        _parent.point(currentJoint.x, currentJoint.y, 0);
        currentJoint = getJointPos(SimpleOpenNI.SKEL_RIGHT_ELBOW);
        _parent.point(currentJoint.x, currentJoint.y, 0);
        currentJoint = getJointPos(SimpleOpenNI.SKEL_RIGHT_HAND);
        _parent.point(currentJoint.x, currentJoint.y, 0);
        currentJoint = getJointPos(SimpleOpenNI.SKEL_TORSO);
        _parent.point(currentJoint.x, currentJoint.y, 0);
        currentJoint = getJointPos(SimpleOpenNI.SKEL_LEFT_HIP);
        _parent.point(currentJoint.x, currentJoint.y, 0);
        currentJoint = getJointPos(SimpleOpenNI.SKEL_LEFT_KNEE);
        _parent.point(currentJoint.x, currentJoint.y, 0);
        currentJoint = getJointPos(SimpleOpenNI.SKEL_LEFT_FOOT);
        _parent.point(currentJoint.x, currentJoint.y, 0);
        currentJoint = getJointPos(SimpleOpenNI.SKEL_RIGHT_HIP);
        _parent.point(currentJoint.x, currentJoint.y, 0);
        currentJoint = getJointPos(SimpleOpenNI.SKEL_RIGHT_KNEE);
        _parent.point(currentJoint.x, currentJoint.y, 0);
        currentJoint = getJointPos(SimpleOpenNI.SKEL_RIGHT_FOOT);
        _parent.point(currentJoint.x, currentJoint.y, 0);
        _parent.popStyle();
    }

    public void drawSkeletonMembers() {

        GeometricFigure currentMember;
        Iterator member = _bodyMembers.values().iterator();

        while (member.hasNext()) {
            currentMember = ((GeometricFigure) member.next());
            currentMember.paint();
        }
    }

    // Devuelve un PVector que contiene las coordenadas en RealWorld del jointPos solicitado
    public PVector getJointPos(int jointId) {
        PVector jointPos = new PVector();
        _context.getJointPositionSkeleton(_user.getUserId(), jointId, jointPos);
        PVector jointPosRW = new PVector();
        _context.convertRealWorldToProjective(jointPos, jointPosRW);
        return jointPosRW;

    }

    private void updateMembers() {
        updateHeadMember();
        updateTorsoMember();
        updateLeftForearmMember();
        updateLeftArmMember();
        updateRightForearmMember();
        updateRightArmMember();
        updateLeftThighMember();
        updateLeftLegMember();
        updateRightThighMember();
        updateRightLegMember();

    }

    private void updateTorsoMember() {
        RectangularPrism torso = (RectangularPrism) _bodyMembers.get("TORSO");
        this.positionOffset = getJointPos(SimpleOpenNI.SKEL_LEFT_SHOULDER).z;
        PVector leftUpperJointPos = getJointPos(SimpleOpenNI.SKEL_LEFT_SHOULDER);
        PVector rightUpperJointPos = getJointPos(SimpleOpenNI.SKEL_RIGHT_SHOULDER);
        PVector leftLowerJointPos = getJointPos(SimpleOpenNI.SKEL_LEFT_HIP);
        PVector rightLowerJointPos = getJointPos(SimpleOpenNI.SKEL_RIGHT_HIP);

        //Calculate Middle Points
        leftLowerJointPos.z = leftUpperJointPos.z;
        rightLowerJointPos.z = rightUpperJointPos.z;
        PVector upperMiddlePoint = torso.calculateMiddlePoint(leftUpperJointPos, rightUpperJointPos);
        PVector lowerMiddlePoint = torso.calculateMiddlePoint(leftLowerJointPos, rightLowerJointPos);

        PVector leftMiddlePoint = torso.calculateMiddlePoint(leftUpperJointPos, leftLowerJointPos);

        PVector rightMiddlePoint = torso.calculateMiddlePoint(rightUpperJointPos, rightLowerJointPos);

        //Calculate Position
        PVector position = new PVector();
        PVector middlePoint1 = torso.calculateMiddlePoint(leftUpperJointPos, rightLowerJointPos);
        PVector middlePoint2 = torso.calculateMiddlePoint(rightUpperJointPos, leftLowerJointPos);

        position.set((middlePoint1.x + middlePoint2.x) / 2, (middlePoint1.y + middlePoint2.y) / 2, 0);

        //Calculate rotationZ

        float rotationZ;

        PVector aux = new PVector(upperMiddlePoint.x - lowerMiddlePoint.x, -(upperMiddlePoint.y - lowerMiddlePoint.y), 0);
        //_parent.println("aux = " + aux);
        rotationZ = _parent.atan2(aux.x, aux.y);
        /*_parent.println("rotationZ ini = " + rotationZ);
         _parent.println("rotationZ ini degrees = " + degrees(rotationZ));*/
        if (rotationZ < 0) {
            rotationZ = _parent.TWO_PI + rotationZ;
        }

        //Calculate rotationY
        float rotationY;
        PVector nearVector;
        PVector farVector;
        if (_parent.abs(leftMiddlePoint.z) <= _parent.abs(rightMiddlePoint.z)) {
            nearVector = leftMiddlePoint;
            farVector = rightMiddlePoint;
        } else {
            nearVector = rightMiddlePoint;
            farVector = leftMiddlePoint;
        }
        PVector aux2 = new PVector(farVector.x - nearVector.x, 0, farVector.z - nearVector.z);
        rotationY = _parent.atan2(aux2.z, aux2.x);
        if (rotationY < 0) {
            rotationY = _parent.TWO_PI + rotationZ;
        }

        //Calculate Member Dimensions
        float memberHeight = upperMiddlePoint.dist(lowerMiddlePoint);
        float memberWidth = (leftUpperJointPos.dist(rightUpperJointPos) + leftLowerJointPos.dist(rightLowerJointPos)) / 2;
        
        updatePrism(torso, position, rotationZ, rotationY, memberWidth);
        torso.setMemberHeight(memberHeight);

    }
    
    private void updateHeadMember() {
        Ball head = (Ball) _bodyMembers.get("HEAD");
        PVector headPosition = getJointPos(SimpleOpenNI.SKEL_HEAD);
        // a 200 pixel diameter head
        float headsize = 150;

        // create a distance scalar related to the depth (z dimension)
        float distanceScalar = (525 / headPosition.z);
        
        head.setPosX(headPosition.x);
        head.setPosY(headPosition.y);
        head.setRadius(distanceScalar * headsize);
        
    }
    
    private void updateLimb(RectangularPrism limb, PVector upperJointPos, PVector lowerJointPos){
        //Calculate position
        PVector position = new PVector();
        position.set(limb.calculateMiddlePoint(lowerJointPos, upperJointPos));
        position.z=-(position.z-this.positionOffset);
        
        //Calculate rotationZ
        float rotationZ;
        PVector aux = new PVector(upperJointPos.x - lowerJointPos.x, -(upperJointPos.y - lowerJointPos.y), 0);
        rotationZ = _parent.atan2(aux.y, -aux.x);
        if (rotationZ < 0) {
            rotationZ = _parent.TWO_PI + rotationZ;
        }
        
        //Calculate rotationY
        float rotationY;
        PVector aux2 = new PVector(upperJointPos.x - lowerJointPos.x, 0, upperJointPos.z - lowerJointPos.z);
        
        rotationY = _parent.atan2(aux2.z, aux2.x);
        
        if (rotationY < 0) {
            rotationY = _parent.TWO_PI + rotationY;
        }
        if(rotationZ > _parent.radians(90) && rotationZ < _parent.radians(270)){
            //_rotationY+=_parent.radians(90);
            rotationY=-rotationY;
        }
        
        //Calculate Height
        float memberWidth = upperJointPos.dist(lowerJointPos);
        
        updatePrism(limb, position, rotationZ, rotationY, memberWidth);
        
    }

    private void updateLeftForearmMember() {
        RectangularPrism limb = (RectangularPrism) _bodyMembers.get("LEFT_FOREARM");
        updateLimb(limb, getJointPos(SimpleOpenNI.SKEL_LEFT_ELBOW), getJointPos(SimpleOpenNI.SKEL_LEFT_SHOULDER));
    }

    private void updateLeftArmMember() {
        RectangularPrism limb = (RectangularPrism) _bodyMembers.get("LEFT_ARM");
        updateLimb(limb, getJointPos(SimpleOpenNI.SKEL_LEFT_HAND), getJointPos(SimpleOpenNI.SKEL_LEFT_ELBOW));
    }

    private void updateRightForearmMember() {
        RectangularPrism limb = (RectangularPrism) _bodyMembers.get("RIGHT_FOREARM");
        updateLimb(limb, getJointPos(SimpleOpenNI.SKEL_RIGHT_ELBOW), getJointPos(SimpleOpenNI.SKEL_RIGHT_SHOULDER));
    }

    private void updateRightArmMember() {
        RectangularPrism limb = (RectangularPrism) _bodyMembers.get("RIGHT_ARM");
        updateLimb(limb, getJointPos(SimpleOpenNI.SKEL_RIGHT_HAND), getJointPos(SimpleOpenNI.SKEL_RIGHT_ELBOW));

    }

    private void updateLeftThighMember() {
        RectangularPrism limb = (RectangularPrism) _bodyMembers.get("LEFT_THIGH");
        updateLimb(limb, getJointPos(SimpleOpenNI.SKEL_LEFT_KNEE), getJointPos(SimpleOpenNI.SKEL_LEFT_HIP));

    }

    private void updateLeftLegMember() {
        RectangularPrism limb = (RectangularPrism) _bodyMembers.get("LEFT_LEG");
        updateLimb(limb, getJointPos(SimpleOpenNI.SKEL_LEFT_FOOT), getJointPos(SimpleOpenNI.SKEL_LEFT_KNEE));

    }

    private void updateRightThighMember() {
        RectangularPrism limb = (RectangularPrism) _bodyMembers.get("RIGHT_THIGH");
        updateLimb(limb, getJointPos(SimpleOpenNI.SKEL_RIGHT_KNEE), getJointPos(SimpleOpenNI.SKEL_RIGHT_HIP));
    }

    private void updateRightLegMember() {
        RectangularPrism limb = (RectangularPrism) _bodyMembers.get("RIGHT_LEG");
        updateLimb(limb, getJointPos(SimpleOpenNI.SKEL_RIGHT_FOOT), getJointPos(SimpleOpenNI.SKEL_RIGHT_KNEE));
    }

    public Limb isPointInsideBody(PVector point) {
        return null;
    }

    public void circleForAHead() {
        // get 3D position of a joint
        PVector jointPos = new PVector();
        _context.getJointPositionSkeleton(_user.getUserId(), SimpleOpenNI.SKEL_HEAD, jointPos);

        // convert real world point to projective space
        PVector jointPos_Proj = new PVector();
        _context.convertRealWorldToProjective(jointPos, jointPos_Proj);

        // a 200 pixel diameter head
        float headsize = 200;

        // create a distance scalar related to the depth (z dimension)
        float distanceScalar = (525 / jointPos_Proj.z);

        // set the fill colour to make the circle green
        _parent.fill(0, 255, 0);

        // draw the circle at the position of the head with the head size scaled by the distance scalar
        _parent.ellipse(jointPos_Proj.x, jointPos_Proj.y, distanceScalar * headsize, distanceScalar * headsize);
        _parent.pushMatrix();
        _parent.translate(jointPos_Proj.x, jointPos_Proj.y, 0);
        //_parent.sphere(10);
        _parent.popMatrix();
    }

    public float getPositionOffset() {
        return positionOffset;
    }

    private void updatePrism(RectangularPrism torso, PVector position, float rotationZ, float rotationY, float memberWidth) {
        //torso.setPosition(position);
        torso.setPosX(position.x);
        torso.setPosY(position.y);
        torso.setPosZ(position.z);
        torso.setRotationZ(rotationZ);
        torso.setRotationY(rotationY);
        torso.setMemberWidth(memberWidth);
    }
    

}