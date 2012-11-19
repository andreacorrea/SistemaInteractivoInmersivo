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

    private PApplet parent;
    private User user;
    private SimpleOpenNI context;
    private Map<String, GeometricFigure> bodyMembers;
    private int bodyColor;
    private float positionOffset;
    private float defaultHeight = 20;
    private float defaultDepth = 20;
    private float defaultMass = 1;
    
    BuildingSkeletonVolumeStrategy buildingSkeletonVolumeStrategy;
    

    public Body(PApplet p, User user, int bodyColor) {
        this.parent = p;
        this.user = user;
        this.context = user.getContext();
        this.bodyColor = bodyColor;
        this.bodyMembers = new HashMap<String, GeometricFigure>();
        //this.buildingSkeletonVolumeStrategy = new CubesSkeletonVolume();
        this.buildingSkeletonVolumeStrategy = new SpheresSkeletonVolume();
        createSkeleton();
    }

    public void createSkeleton() {
        /*bodyMembers.put("HEAD", new Ball(parent, "HEAD", bodyColor, defaultMass));
        bodyMembers.put("TORSO", new RectangularPrism(parent, "TORSO", bodyColor, defaultMass));
        bodyMembers.put("LEFT_FOREARM", new RectangularPrism(parent, "LEFT_FOREARM", bodyColor, defaultMass));
        bodyMembers.put("LEFT_ARM", new RectangularPrism(parent, "LEFT_ARM", bodyColor, defaultMass));
        bodyMembers.put("RIGHT_FOREARM", new RectangularPrism(parent, "RIGHT_FOREARM", bodyColor, defaultMass));
        bodyMembers.put("RIGHT_ARM", new RectangularPrism(parent, "RIGHT_ARM", bodyColor, defaultMass));
        bodyMembers.put("LEFT_THIGH", new RectangularPrism(parent, "LEFT_THIGH", bodyColor, defaultMass));
        bodyMembers.put("LEFT_LEG", new RectangularPrism(parent, "LEFT_LEG", bodyColor, defaultMass));
        bodyMembers.put("RIGHT_THIGH", new RectangularPrism(parent, "RIGHT_THIGH", bodyColor, defaultMass));
        bodyMembers.put("RIGHT_LEG", new RectangularPrism(parent, "RIGHT_LEG", bodyColor, defaultMass));*/
        this.buildingSkeletonVolumeStrategy.createSkeleton(this);
    }

    public void update() {
        calculatePositionOffset();
        updateMembers();
    }

    public void drawSkeletonLines() {
        context.drawLimb(user.getUserId(), SimpleOpenNI.SKEL_HEAD, SimpleOpenNI.SKEL_NECK);

        context.drawLimb(user.getUserId(), SimpleOpenNI.SKEL_NECK, SimpleOpenNI.SKEL_LEFT_SHOULDER);
        context.drawLimb(user.getUserId(), SimpleOpenNI.SKEL_LEFT_SHOULDER, SimpleOpenNI.SKEL_LEFT_ELBOW);
        context.drawLimb(user.getUserId(), SimpleOpenNI.SKEL_LEFT_ELBOW, SimpleOpenNI.SKEL_LEFT_HAND);

        context.drawLimb(user.getUserId(), SimpleOpenNI.SKEL_NECK, SimpleOpenNI.SKEL_RIGHT_SHOULDER);
        context.drawLimb(user.getUserId(), SimpleOpenNI.SKEL_RIGHT_SHOULDER, SimpleOpenNI.SKEL_RIGHT_ELBOW);
        context.drawLimb(user.getUserId(), SimpleOpenNI.SKEL_RIGHT_ELBOW, SimpleOpenNI.SKEL_RIGHT_HAND);

        context.drawLimb(user.getUserId(), SimpleOpenNI.SKEL_LEFT_SHOULDER, SimpleOpenNI.SKEL_TORSO);
        context.drawLimb(user.getUserId(), SimpleOpenNI.SKEL_RIGHT_SHOULDER, SimpleOpenNI.SKEL_TORSO);

        context.drawLimb(user.getUserId(), SimpleOpenNI.SKEL_TORSO, SimpleOpenNI.SKEL_LEFT_HIP);
        context.drawLimb(user.getUserId(), SimpleOpenNI.SKEL_LEFT_HIP, SimpleOpenNI.SKEL_LEFT_KNEE);
        context.drawLimb(user.getUserId(), SimpleOpenNI.SKEL_LEFT_KNEE, SimpleOpenNI.SKEL_LEFT_FOOT);

        context.drawLimb(user.getUserId(), SimpleOpenNI.SKEL_TORSO, SimpleOpenNI.SKEL_RIGHT_HIP);
        context.drawLimb(user.getUserId(), SimpleOpenNI.SKEL_RIGHT_HIP, SimpleOpenNI.SKEL_RIGHT_KNEE);
        context.drawLimb(user.getUserId(), SimpleOpenNI.SKEL_RIGHT_KNEE, SimpleOpenNI.SKEL_RIGHT_FOOT);

    }

    public void drawJoints() {
        parent.pushStyle();
        parent.strokeWeight(10);
        PVector currentJoint = new PVector();
        currentJoint = CalculateVectors.getJointPos(SimpleOpenNI.SKEL_HEAD, context, user);
        parent.point(currentJoint.x, currentJoint.y, 0);
        currentJoint = CalculateVectors.getJointPos(SimpleOpenNI.SKEL_NECK, context, user);
        parent.point(currentJoint.x, currentJoint.y, 0);
        currentJoint = CalculateVectors.getJointPos(SimpleOpenNI.SKEL_LEFT_SHOULDER, context, user);
        parent.point(currentJoint.x, currentJoint.y, 0);
        currentJoint = CalculateVectors.getJointPos(SimpleOpenNI.SKEL_LEFT_ELBOW, context, user);
        parent.point(currentJoint.x, currentJoint.y, 0);
        currentJoint = CalculateVectors.getJointPos(SimpleOpenNI.SKEL_LEFT_HAND, context, user);
        parent.point(currentJoint.x, currentJoint.y, 0);
        currentJoint = CalculateVectors.getJointPos(SimpleOpenNI.SKEL_RIGHT_SHOULDER, context, user);
        parent.point(currentJoint.x, currentJoint.y, 0);
        currentJoint = CalculateVectors.getJointPos(SimpleOpenNI.SKEL_RIGHT_ELBOW, context, user);
        parent.point(currentJoint.x, currentJoint.y, 0);
        currentJoint = CalculateVectors.getJointPos(SimpleOpenNI.SKEL_RIGHT_HAND, context, user);
        parent.point(currentJoint.x, currentJoint.y, 0);
        currentJoint = CalculateVectors.getJointPos(SimpleOpenNI.SKEL_TORSO, context, user);
        parent.point(currentJoint.x, currentJoint.y, 0);
        currentJoint = CalculateVectors.getJointPos(SimpleOpenNI.SKEL_LEFT_HIP, context, user);
        parent.point(currentJoint.x, currentJoint.y, 0);
        currentJoint = CalculateVectors.getJointPos(SimpleOpenNI.SKEL_LEFT_KNEE, context, user);
        parent.point(currentJoint.x, currentJoint.y, 0);
        currentJoint = CalculateVectors.getJointPos(SimpleOpenNI.SKEL_LEFT_FOOT, context, user);
        parent.point(currentJoint.x, currentJoint.y, 0);
        currentJoint = CalculateVectors.getJointPos(SimpleOpenNI.SKEL_RIGHT_HIP, context, user);
        parent.point(currentJoint.x, currentJoint.y, 0);
        currentJoint = CalculateVectors.getJointPos(SimpleOpenNI.SKEL_RIGHT_KNEE, context, user);
        parent.point(currentJoint.x, currentJoint.y, 0);
        currentJoint = CalculateVectors.getJointPos(SimpleOpenNI.SKEL_RIGHT_FOOT, context, user);
        parent.point(currentJoint.x, currentJoint.y, 0);
        parent.popStyle();
    }

    public void paintSkeletonMembers(Command command) {

        GeometricFigure currentMember;
        Iterator member = bodyMembers.values().iterator();

        while (member.hasNext()) {
            currentMember = ((GeometricFigure) member.next());
            currentMember.checkChangeState(command);
            currentMember.paint();
        }
    }

    

    private void updateMembers() {
        /*updateTorsoMember();
        updateHeadMember();
        updateLeftForearmMember();
        updateLeftArmMember();
        updateRightForearmMember();
        updateRightArmMember();
        updateLeftThighMember();
        updateLeftLegMember();
        updateRightThighMember();
        updateRightLegMember();*/
        this.buildingSkeletonVolumeStrategy.updateVolume(this);
    }

    /*private void updateHeadMember() {
        Ball head = (Ball) bodyMembers.get("HEAD");
        PVector headPosition = CalculateVectors.getJointPos(SimpleOpenNI.SKEL_HEAD, context, user);
        // a 200 pixel diameter head
        float headsize = 100;

        // create a distance scalar related to the depth (z dimension)
        float distanceScalar = (525 / headPosition.z);

        headPosition.z = 0;
        head.setPos(headPosition);
        head.setRadius(distanceScalar * headsize);

    }

    private void updateTorsoMember() {
        RectangularPrism torso = (RectangularPrism) bodyMembers.get("TORSO");
        PVector leftUpperJointPos = CalculateVectors.getJointPos(SimpleOpenNI.SKEL_LEFT_SHOULDER, context, user);
        PVector rightUpperJointPos = CalculateVectors.getJointPos(SimpleOpenNI.SKEL_RIGHT_SHOULDER, context, user);
        PVector leftLowerJointPos = CalculateVectors.getJointPos(SimpleOpenNI.SKEL_LEFT_HIP, context, user);
        PVector rightLowerJointPos = CalculateVectors.getJointPos(SimpleOpenNI.SKEL_RIGHT_HIP, context, user);

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
        rotationZ = parent.atan2(aux.x, aux.y);
        _parent.println("rotationZ ini = " + rotationZ);
         _parent.println("rotationZ ini degrees = " + degrees(rotationZ));
        if (rotationZ < 0) {
            rotationZ = parent.TWO_PI + rotationZ;
        }

        //Calculate rotationY
        float rotationY;
        PVector nearVector;
        PVector farVector;
        if (parent.abs(leftMiddlePoint.z) <= parent.abs(rightMiddlePoint.z)) {
            nearVector = leftMiddlePoint;
            farVector = rightMiddlePoint;
        } else {
            nearVector = rightMiddlePoint;
            farVector = leftMiddlePoint;
        }
        PVector aux2 = new PVector(farVector.x - nearVector.x, 0, farVector.z - nearVector.z);
        rotationY = parent.atan2(aux2.z, aux2.x);
        if (rotationY < 0) {
            rotationY = parent.TWO_PI + rotationZ;
        }

        //Calculate Member Dimensions
        float memberHeight = upperMiddlePoint.dist(lowerMiddlePoint);
        float memberWidth = (leftUpperJointPos.dist(rightUpperJointPos) + leftLowerJointPos.dist(rightLowerJointPos)) / 2;




        updateTorsoGeometricFigure(torso, position, rotationZ, rotationY, memberWidth, memberHeight);
        //defaultDimensionsTorso.x = memberWidth;
         //defaultDimensionsTorso.y = memberHeight;

        //torso.setDimensions(defaultDimensionsTorso);

    }

    private void updateLimbMember(RectangularPrism limb, PVector upperJointPos, PVector lowerJointPos) {
        //Calculate position
        PVector position = new PVector();
        position.set(limb.calculateMiddlePoint(lowerJointPos, upperJointPos));
        position.z = -(position.z - this.positionOffset);

        //Calculate rotationZ
        float rotationZ;
        PVector aux = new PVector(upperJointPos.x - lowerJointPos.x, -(upperJointPos.y - lowerJointPos.y), 0);
        rotationZ = parent.atan2(aux.y, -aux.x);
        if (rotationZ < 0) {
            rotationZ = parent.TWO_PI + rotationZ;
        }

        //Calculate rotationY
        float rotationY;
        PVector aux2 = new PVector(upperJointPos.x - lowerJointPos.x, 0, upperJointPos.z - lowerJointPos.z);

        rotationY = parent.atan2(aux2.z, aux2.x);

        if (rotationY < 0) {
            rotationY = parent.TWO_PI + rotationY;
        }
        if (rotationZ > parent.radians(90) && rotationZ < parent.radians(270)) {
            //_rotationY+=_parent.radians(90);
            rotationY = -rotationY;
        }

        //Calculate Height
        float memberWidth = upperJointPos.dist(lowerJointPos);

       updateLimbGeometricFigure(limb, position, rotationZ, rotationY, memberWidth);

    }

    private void updateTorsoGeometricFigure(RectangularPrism torso, PVector position, float rotationZ, float rotationY, float memberWidth, float memberHeight) {
        torso.setPos(position);
        torso.setRotationZ(rotationZ);
        torso.setRotationY(rotationY);
        torso.setDimensionX(memberWidth);
        torso.setDimensionY(memberHeight);
        torso.setDimensionZ(rotationY);

    }

    private void updateLimbGeometricFigure(RectangularPrism limb, PVector position, float rotationZ, float rotationY, float memberWidth) {
         //update features
        limb.setPos(position);
        limb.setRotationZ(rotationZ);
        limb.setRotationY(rotationY);
        limb.setDimensionX(memberWidth);
        limb.setDimensionY(defaultHeight);
        limb.setDimensionZ(defaultDepth);
    }

    private void updateLeftForearmMember() {
        RectangularPrism limb = (RectangularPrism) bodyMembers.get("LEFT_FOREARM");
        updateLimbMember(limb, CalculateVectors.getJointPos(SimpleOpenNI.SKEL_LEFT_ELBOW, context, user), CalculateVectors.getJointPos(SimpleOpenNI.SKEL_LEFT_SHOULDER, context, user));
    }

    private void updateLeftArmMember() {
        RectangularPrism limb = (RectangularPrism) bodyMembers.get("LEFT_ARM");
        updateLimbMember(limb, CalculateVectors.getJointPos(SimpleOpenNI.SKEL_LEFT_HAND, context, user), CalculateVectors.getJointPos(SimpleOpenNI.SKEL_LEFT_ELBOW, context, user));
    }

    private void updateRightForearmMember() {
        RectangularPrism limb = (RectangularPrism) bodyMembers.get("RIGHT_FOREARM");
        updateLimbMember(limb, CalculateVectors.getJointPos(SimpleOpenNI.SKEL_RIGHT_ELBOW, context, user), CalculateVectors.getJointPos(SimpleOpenNI.SKEL_RIGHT_SHOULDER, context, user));
    }

    private void updateRightArmMember() {
        RectangularPrism limb = (RectangularPrism) bodyMembers.get("RIGHT_ARM");
        updateLimbMember(limb, CalculateVectors.getJointPos(SimpleOpenNI.SKEL_RIGHT_HAND, context, user), CalculateVectors.getJointPos(SimpleOpenNI.SKEL_RIGHT_ELBOW, context, user));

    }

    private void updateLeftThighMember() {
        RectangularPrism limb = (RectangularPrism) bodyMembers.get("LEFT_THIGH");
        updateLimbMember(limb, CalculateVectors.getJointPos(SimpleOpenNI.SKEL_LEFT_KNEE, context, user), CalculateVectors.getJointPos(SimpleOpenNI.SKEL_LEFT_HIP, context, user));

    }

    private void updateLeftLegMember() {
        RectangularPrism limb = (RectangularPrism) bodyMembers.get("LEFT_LEG");
        updateLimbMember(limb, CalculateVectors.getJointPos(SimpleOpenNI.SKEL_LEFT_FOOT, context, user), CalculateVectors.getJointPos(SimpleOpenNI.SKEL_LEFT_KNEE, context, user));

    }

    private void updateRightThighMember() {
        RectangularPrism limb = (RectangularPrism) bodyMembers.get("RIGHT_THIGH");
        updateLimbMember(limb, CalculateVectors.getJointPos(SimpleOpenNI.SKEL_RIGHT_KNEE, context, user), CalculateVectors.getJointPos(SimpleOpenNI.SKEL_RIGHT_HIP, context, user));
    }

    private void updateRightLegMember() {
        RectangularPrism limb = (RectangularPrism) bodyMembers.get("RIGHT_LEG");
        updateLimbMember(limb, CalculateVectors.getJointPos(SimpleOpenNI.SKEL_RIGHT_FOOT, context, user), CalculateVectors.getJointPos(SimpleOpenNI.SKEL_RIGHT_KNEE, context, user));
    }*/

    public float getPositionOffset() {
        return positionOffset;
    }

    void addObserverGFBody(GeometricFigure gf) {
        GeometricFigure currentGeometricFigure;

        Iterator bodyMember = bodyMembers.values().iterator();

        while (bodyMember.hasNext()) {
            currentGeometricFigure = ((GeometricFigure) bodyMember.next());
            currentGeometricFigure.addObserver(gf);
            gf.addObserver(currentGeometricFigure);
        }
    }

    public void circleForAHead() {
        // get 3D position of a joint
        PVector jointPos = new PVector();
        context.getJointPositionSkeleton(user.getUserId(), SimpleOpenNI.SKEL_HEAD, jointPos);

        // convert real world point to projective space
        PVector jointPos_Proj = new PVector();
        context.convertRealWorldToProjective(jointPos, jointPos_Proj);

        // a 200 pixel diameter head
        float headsize = 200;

        // create a distance scalar related to the depth (z dimension)
        float distanceScalar = (525 / jointPos_Proj.z);

        // set the fill colour to make the circle green
        parent.fill(0, 255, 0);

        // draw the circle at the position of the head with the head size scaled by the distance scalar
        parent.ellipse(jointPos_Proj.x, jointPos_Proj.y, distanceScalar * headsize, distanceScalar * headsize);
        parent.pushMatrix();
        parent.translate(jointPos_Proj.x, jointPos_Proj.y, 0);
        //_parent.sphere(10);
        parent.popMatrix();
    }
    
    private void calculatePositionOffset(){
        this.positionOffset = CalculateVectors.getJointPos(SimpleOpenNI.SKEL_LEFT_SHOULDER, context, user).z;
    }

    public Map<String, GeometricFigure> getBodyMembers() {
        return bodyMembers;
    }

    public PApplet getParent() {
        return parent;
    }

    public SimpleOpenNI getContext() {
        return context;
    }

    public int getBodyColor() {
        return bodyColor;
    }

    public float getDefaultMass() {
        return defaultMass;
    }

    public User getUser() {
        return user;
    }

    public float getDefaultHeight() {
        return defaultHeight;
    }

    public float getDefaultDepth() {
        return defaultDepth;
    }

    public void setBuildingSkeletonVolumeStrategy(BuildingSkeletonVolumeStrategy buildingSkeletonVolumeStrategy) {
        this.buildingSkeletonVolumeStrategy = buildingSkeletonVolumeStrategy;
    }
    
    
    
    
}