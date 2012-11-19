/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebascuerpohumano;

import SimpleOpenNI.SimpleOpenNI;
import java.util.HashMap;
import java.util.Map;
import processing.core.PApplet;
import processing.core.PVector;

public class CubesSkeletonVolume implements BuildingSkeletonVolumeStrategy{

    @Override
    public void updateVolume(Body body) {
        if(body.getBodyMembers().size()==0){
            createSkeleton(body);
        }else{
            updateMembers(body);
        }
    }

    @Override
    public void createSkeleton(Body body) {
        body.getBodyMembers().put("HEAD", new Ball(body.getParent(), "HEAD", body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("TORSO", new RectangularPrism(body.getParent(), "TORSO", body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("LEFT_FOREARM", new RectangularPrism(body.getParent(), "LEFT_FOREARM", body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("LEFT_ARM", new RectangularPrism(body.getParent(), "LEFT_ARM", body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("RIGHT_FOREARM", new RectangularPrism(body.getParent(), "RIGHT_FOREARM", body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("RIGHT_ARM", new RectangularPrism(body.getParent(), "RIGHT_ARM", body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("LEFT_THIGH", new RectangularPrism(body.getParent(), "LEFT_THIGH", body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("LEFT_LEG", new RectangularPrism(body.getParent(), "LEFT_LEG", body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("RIGHT_THIGH", new RectangularPrism(body.getParent(), "RIGHT_THIGH", body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("RIGHT_LEG", new RectangularPrism(body.getParent(), "RIGHT_LEG", body.getBodyColor(), body.getDefaultMass()));
    }
    
    private void updateMembers(Body body) {
        updateHeadMember((HashMap)body.getBodyMembers(), body.getContext(), body.getUser());
        updateTorsoMember(body, (HashMap)body.getBodyMembers(), body.getContext(), body.getUser());
        updateLimbMember(body, "LEFT_FOREARM", SimpleOpenNI.SKEL_LEFT_ELBOW, SimpleOpenNI.SKEL_LEFT_SHOULDER);
        updateLimbMember(body, "LEFT_ARM", SimpleOpenNI.SKEL_LEFT_HAND, SimpleOpenNI.SKEL_LEFT_ELBOW);
        updateLimbMember(body, "RIGHT_FOREARM", SimpleOpenNI.SKEL_RIGHT_ELBOW, SimpleOpenNI.SKEL_RIGHT_SHOULDER);
        updateLimbMember(body, "RIGHT_ARM", SimpleOpenNI.SKEL_RIGHT_HAND, SimpleOpenNI.SKEL_RIGHT_ELBOW);
        updateLimbMember(body, "LEFT_THIGH", SimpleOpenNI.SKEL_LEFT_KNEE, SimpleOpenNI.SKEL_LEFT_HIP);
        updateLimbMember(body, "LEFT_LEG", SimpleOpenNI.SKEL_LEFT_FOOT, SimpleOpenNI.SKEL_LEFT_KNEE);
        updateLimbMember(body, "RIGHT_THIGH", SimpleOpenNI.SKEL_RIGHT_KNEE, SimpleOpenNI.SKEL_RIGHT_HIP);
        updateLimbMember(body, "RIGHT_LEG", SimpleOpenNI.SKEL_RIGHT_FOOT, SimpleOpenNI.SKEL_RIGHT_KNEE);

    }

    private void updateHeadMember(HashMap <String, GeometricFigure>bodyMembers, SimpleOpenNI context, User user) {
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

    private void updateTorsoMember(Body body, HashMap <String, GeometricFigure>bodyMembers, SimpleOpenNI context, User user) {
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
        rotationZ = body.getParent().atan2(aux.x, aux.y);
        /*_parent.println("rotationZ ini = " + rotationZ);
         _parent.println("rotationZ ini degrees = " + degrees(rotationZ));*/
        if (rotationZ < 0) {
            rotationZ = body.getParent().TWO_PI + rotationZ;
        }

        //Calculate rotationY
        float rotationY;
        PVector nearVector;
        PVector farVector;
        if (body.getParent().abs(leftMiddlePoint.z) <= body.getParent().abs(rightMiddlePoint.z)) {
            nearVector = leftMiddlePoint;
            farVector = rightMiddlePoint;
        } else {
            nearVector = rightMiddlePoint;
            farVector = leftMiddlePoint;
        }
        PVector aux2 = new PVector(farVector.x - nearVector.x, 0, farVector.z - nearVector.z);
        rotationY = body.getParent().atan2(aux2.z, aux2.x);
        if (rotationY < 0) {
            rotationY = body.getParent().TWO_PI + rotationZ;
        }

        //Calculate Member Dimensions
        float memberHeight = upperMiddlePoint.dist(lowerMiddlePoint);
        float memberWidth = (leftUpperJointPos.dist(rightUpperJointPos) + leftLowerJointPos.dist(rightLowerJointPos)) / 2;




        updateTorsoGeometricFigure(torso, position, rotationZ, rotationY, memberWidth, memberHeight);
        /*defaultDimensionsTorso.x = memberWidth;
         defaultDimensionsTorso.y = memberHeight;*/

        //torso.setDimensions(defaultDimensionsTorso);

    }

    private void updateLimbMember(Body body, String nameLimb, int upperPoint, int lowerPoint) {
        RectangularPrism limb = (RectangularPrism) body.getBodyMembers().get(nameLimb);
        PVector upperJointPos = CalculateVectors.getJointPos(upperPoint, body.getContext(), body.getUser());
        PVector lowerJointPos = CalculateVectors.getJointPos(lowerPoint, body.getContext(), body.getUser());
        //Calculate position
        PVector position = new PVector();
        position.set(limb.calculateMiddlePoint(lowerJointPos, upperJointPos));
        position.z = -(position.z - body.getPositionOffset());

        //Calculate rotationZ
        float rotationZ;
        PVector aux = new PVector(upperJointPos.x - lowerJointPos.x, -(upperJointPos.y - lowerJointPos.y), 0);
        rotationZ = body.getParent().atan2(aux.y, -aux.x);
        if (rotationZ < 0) {
            rotationZ = body.getParent().TWO_PI + rotationZ;
        }

        //Calculate rotationY
        float rotationY;
        PVector aux2 = new PVector(upperJointPos.x - lowerJointPos.x, 0, upperJointPos.z - lowerJointPos.z);

        rotationY = body.getParent().atan2(aux2.z, aux2.x);

        if (rotationY < 0) {
            rotationY = body.getParent().TWO_PI + rotationY;
        }
        if (rotationZ > body.getParent().radians(90) && rotationZ < body.getParent().radians(270)) {
            //_rotationY+=_parent.radians(90);
            rotationY = -rotationY;
        }

        //Calculate Height
        float memberWidth = upperJointPos.dist(lowerJointPos);

       updateLimbGeometricFigure(body, limb, position, rotationZ, rotationY, memberWidth);

    }

    private void updateTorsoGeometricFigure(RectangularPrism torso, PVector position, float rotationZ, float rotationY, float memberWidth, float memberHeight) {
        torso.setPos(position);
        torso.setRotationZ(rotationZ);
        torso.setRotationY(rotationY);
        torso.setDimensionX(memberWidth);
        torso.setDimensionY(memberHeight);
        torso.setDimensionZ(rotationY);

    }

    private void updateLimbGeometricFigure(Body body, RectangularPrism limb, PVector position, float rotationZ, float rotationY, float memberWidth) {
         //update features
        limb.setPos(position);
        limb.setRotationZ(rotationZ);
        limb.setRotationY(rotationY);
        limb.setDimensionX(memberWidth);
        limb.setDimensionY(body.getDefaultHeight());
        limb.setDimensionZ(body.getDefaultDepth());
    }

}

