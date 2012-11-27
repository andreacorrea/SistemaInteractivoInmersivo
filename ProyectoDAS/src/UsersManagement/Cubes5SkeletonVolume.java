/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UsersManagement;

import GeometricFiguresManagement.Ball;
import GeometricFiguresManagement.GeometricFigure;
import GeometricFiguresManagement.RectangularPrism;
import SistemaInteraccionInmersiva.CalculateVectors;
import java.util.HashMap;
import processing.core.PVector;

/**
 *
 * @author Andrea
 */
public class Cubes5SkeletonVolume implements BuildingSkeletonVolumeStrategy {
    
    private float defaultSize=20;
    
    @Override
    public void updateVolume(Body body) {
        if(body.getBodyMembers().isEmpty()){
            createSkeleton(body);
        }else{
            updateMembers(body);
        }
    }

    @Override
    public void createSkeleton(Body body) {
        body.getBodyMembers().put("HEAD", new Ball(body.getParent(), "HEAD" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("TORSO", new RectangularPrism(body.getParent(), "TORSO" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("LEFT_FOREARM_RP1", new RectangularPrism(body.getParent(), "LEFT_FOREARM_RP1" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("LEFT_FOREARM_RP2", new RectangularPrism(body.getParent(), "LEFT_FOREARM_RP2" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("LEFT_FOREARM_RP3", new RectangularPrism(body.getParent(), "LEFT_FOREARM_RP3" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("LEFT_FOREARM_RP4", new RectangularPrism(body.getParent(), "LEFT_FOREARM_RP4" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("LEFT_FOREARM_RP5", new RectangularPrism(body.getParent(), "LEFT_FOREARM_RP5" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("LEFT_ARM_RP1", new RectangularPrism(body.getParent(), "LEFT_ARM_RP1" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("LEFT_ARM_RP2", new RectangularPrism(body.getParent(), "LEFT_ARM_RP2" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("LEFT_ARM_RP3", new RectangularPrism(body.getParent(), "LEFT_ARM_RP3" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("LEFT_ARM_RP4", new RectangularPrism(body.getParent(), "LEFT_ARM_RP4" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("LEFT_ARM_RP5", new RectangularPrism(body.getParent(), "LEFT_ARM_RP5" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("RIGHT_FOREARM_RP1", new RectangularPrism(body.getParent(), "RIGHT_FOREARM_RP1" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("RIGHT_FOREARM_RP2", new RectangularPrism(body.getParent(), "RIGHT_FOREARM_RP2" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("RIGHT_FOREARM_RP3", new RectangularPrism(body.getParent(), "RIGHT_FOREARM_RP3" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("RIGHT_FOREARM_RP4", new RectangularPrism(body.getParent(), "RIGHT_FOREARM_RP4" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("RIGHT_FOREARM_RP5", new RectangularPrism(body.getParent(), "RIGHT_FOREARM_RP5" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("RIGHT_ARM_RP1", new RectangularPrism(body.getParent(), "RIGHT_ARM_RP1" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("RIGHT_ARM_RP2", new RectangularPrism(body.getParent(), "RIGHT_ARM_RP2" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("RIGHT_ARM_RP3", new RectangularPrism(body.getParent(), "RIGHT_ARM_RP3" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("RIGHT_ARM_RP4", new RectangularPrism(body.getParent(), "RIGHT_ARM_RP4" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("RIGHT_ARM_RP5", new RectangularPrism(body.getParent(), "RIGHT_ARM_RP5" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("LEFT_THIGH_RP1", new RectangularPrism(body.getParent(), "LEFT_THIGH_RP1" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("LEFT_THIGH_RP2", new RectangularPrism(body.getParent(), "LEFT_THIGH_RP2" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("LEFT_THIGH_RP3", new RectangularPrism(body.getParent(), "LEFT_THIGH_RP3" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("LEFT_THIGH_RP4", new RectangularPrism(body.getParent(), "LEFT_THIGH_RP4" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("LEFT_THIGH_RP5", new RectangularPrism(body.getParent(), "LEFT_THIGH_RP5" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("LEFT_LEG_RP1", new RectangularPrism(body.getParent(), "LEFT_LEG_RP1" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("LEFT_LEG_RP2", new RectangularPrism(body.getParent(), "LEFT_LEG_RP2" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("LEFT_LEG_RP3", new RectangularPrism(body.getParent(), "LEFT_LEG_RP3" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("LEFT_LEG_RP4", new RectangularPrism(body.getParent(), "LEFT_LEG_RP4" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("LEFT_LEG_RP5", new RectangularPrism(body.getParent(), "LEFT_LEG_RP5" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("RIGHT_THIGH_RP1", new RectangularPrism(body.getParent(), "RIGHT_THIGH_RP1" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("RIGHT_THIGH_RP2", new RectangularPrism(body.getParent(), "RIGHT_THIGH_RP2" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("RIGHT_THIGH_RP3", new RectangularPrism(body.getParent(), "RIGHT_THIGH_RP3" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("RIGHT_THIGH_RP4", new RectangularPrism(body.getParent(), "RIGHT_THIGH_RP4" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("RIGHT_THIGH_RP5", new RectangularPrism(body.getParent(), "RIGHT_THIGH_RP5" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("RIGHT_LEG_RP1", new RectangularPrism(body.getParent(), "RIGHT_LEG_RP1" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("RIGHT_LEG_RP2", new RectangularPrism(body.getParent(), "RIGHT_LEG_RP2" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("RIGHT_LEG_RP3", new RectangularPrism(body.getParent(), "RIGHT_LEG_RP3" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("RIGHT_LEG_RP4", new RectangularPrism(body.getParent(), "RIGHT_LEG_RP4" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("RIGHT_LEG_RP5", new RectangularPrism(body.getParent(), "RIGHT_LEG_RP5" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
    }
    
    private void updateMembers(Body body) {
        updateHeadMember((HashMap)body.getBodyMembers(), body.getContext(), body.getUser());
        updateTorsoMember(body, (HashMap)body.getBodyMembers(), body.getContext(), body.getUser());
        updateLimbMember(body, "LEFT_FOREARM_RP1", "LEFT_FOREARM_RP2", "LEFT_FOREARM_RP3", "LEFT_FOREARM_RP4", "LEFT_FOREARM_RP5", AdapterSimpleOpenNI.SKEL_LEFT_ELBOW, AdapterSimpleOpenNI.SKEL_LEFT_SHOULDER);
        updateLimbMember(body, "LEFT_ARM_RP1", "LEFT_ARM_RP2", "LEFT_ARM_RP3", "LEFT_ARM_RP4", "LEFT_ARM_RP5", AdapterSimpleOpenNI.SKEL_LEFT_HAND, AdapterSimpleOpenNI.SKEL_LEFT_ELBOW);
        updateLimbMember(body, "RIGHT_FOREARM_RP1", "RIGHT_FOREARM_RP2", "RIGHT_FOREARM_RP3", "RIGHT_FOREARM_RP4", "RIGHT_FOREARM_RP5", AdapterSimpleOpenNI.SKEL_RIGHT_ELBOW, AdapterSimpleOpenNI.SKEL_RIGHT_SHOULDER);
        updateLimbMember(body, "RIGHT_ARM_RP1", "RIGHT_ARM_RP2", "RIGHT_ARM_RP3", "RIGHT_ARM_RP4", "RIGHT_ARM_RP5", AdapterSimpleOpenNI.SKEL_RIGHT_HAND, AdapterSimpleOpenNI.SKEL_RIGHT_ELBOW);
        updateLimbMember(body, "LEFT_THIGH_RP1", "LEFT_THIGH_RP2", "LEFT_THIGH_RP3", "LEFT_THIGH_RP4", "LEFT_THIGH_RP5", AdapterSimpleOpenNI.SKEL_LEFT_KNEE, AdapterSimpleOpenNI.SKEL_LEFT_HIP);
        updateLimbMember(body, "LEFT_LEG_RP1", "LEFT_LEG_RP2", "LEFT_LEG_RP3", "LEFT_LEG_RP4", "LEFT_LEG_RP5", AdapterSimpleOpenNI.SKEL_LEFT_FOOT, AdapterSimpleOpenNI.SKEL_LEFT_KNEE);
        updateLimbMember(body, "RIGHT_THIGH_RP1", "RIGHT_THIGH_RP2", "RIGHT_THIGH_RP3", "RIGHT_THIGH_RP4", "RIGHT_THIGH_RP5", AdapterSimpleOpenNI.SKEL_RIGHT_KNEE, AdapterSimpleOpenNI.SKEL_RIGHT_HIP);
        updateLimbMember(body, "RIGHT_LEG_RP1", "RIGHT_LEG_RP2", "RIGHT_LEG_RP3", "RIGHT_LEG_RP4", "RIGHT_LEG_RP5", AdapterSimpleOpenNI.SKEL_RIGHT_FOOT, AdapterSimpleOpenNI.SKEL_RIGHT_KNEE);

        
        
    }

    private void updateHeadMember(HashMap <String, GeometricFigure>bodyMembers, AdapterSimpleOpenNI context, User user) {
        Ball head = (Ball) bodyMembers.get("HEAD");
        PVector headPosition = CalculateVectors.getJointPos(AdapterSimpleOpenNI.SKEL_HEAD, context, user);
        // a 200 pixel diameter head
        float headsize = 100;

        // create a distance scalar related to the depth (z dimension)
        float distanceScalar = (525 / headPosition.z);

        headPosition.z = 0;
        headPosition.set(CalculateVectors.getRealPositionBody(headPosition, user.getBody().getParent()));
        head.setPos(headPosition);
        head.setRadius(distanceScalar * headsize);

    }

    private void updateTorsoMember(Body body, HashMap <String, GeometricFigure>bodyMembers, AdapterSimpleOpenNI context, User user) {
        RectangularPrism torso = (RectangularPrism) bodyMembers.get("TORSO");
        PVector leftUpperJointPos = CalculateVectors.getJointPos(AdapterSimpleOpenNI.SKEL_LEFT_SHOULDER, context, user);
        PVector rightUpperJointPos = CalculateVectors.getJointPos(AdapterSimpleOpenNI.SKEL_RIGHT_SHOULDER, context, user);
        PVector leftLowerJointPos = CalculateVectors.getJointPos(AdapterSimpleOpenNI.SKEL_LEFT_HIP, context, user);
        PVector rightLowerJointPos = CalculateVectors.getJointPos(AdapterSimpleOpenNI.SKEL_RIGHT_HIP, context, user);

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




        updateTorsoGeometricFigure(torso, position, rotationZ, rotationY, memberWidth, memberHeight, user);
        /*defaultDimensionsTorso.x = memberWidth;
         defaultDimensionsTorso.y = memberHeight;*/

        //torso.setDimensions(defaultDimensionsTorso);

    }

    private void updateLimbMember(Body body, String nameSphere1, String nameSphere2, String nameSphere3, String nameSphere4, String nameSphere5, int upperPoint, int lowerPoint) {
        RectangularPrism prism1 = (RectangularPrism) body.getBodyMembers().get(nameSphere1);
        RectangularPrism prism2 = (RectangularPrism) body.getBodyMembers().get(nameSphere2);
        RectangularPrism prism3 = (RectangularPrism) body.getBodyMembers().get(nameSphere3);
        RectangularPrism prism4 = (RectangularPrism) body.getBodyMembers().get(nameSphere4);
        RectangularPrism prism5 = (RectangularPrism) body.getBodyMembers().get(nameSphere5);
        PVector upperJointPos = CalculateVectors.getJointPos(upperPoint, body.getContext(), body.getUser());
        PVector lowerJointPos = CalculateVectors.getJointPos(lowerPoint, body.getContext(), body.getUser());
        //Calculate position
        PVector positionRP1 = new PVector();
        PVector positionRP2 = new PVector();
        PVector positionRP3 = new PVector();
        PVector positionRP4 = new PVector();
        PVector positionRP5 = new PVector();

        PVector aux= new PVector();
        
        float distanciaJoints = upperJointPos.dist(lowerJointPos);
        
        PVector orientacion = new PVector();
        orientacion.set(upperJointPos);
        orientacion.sub(lowerJointPos);
        //calculo pos 1
        
        aux.set(orientacion);
        aux.normalize();
        aux.mult(distanciaJoints/6);
        
        aux.add(lowerJointPos);
        
        positionRP1.set(aux);
        
        positionRP1.z = -(positionRP1.z - body.getPositionOffset());
        
        //calculo pos 2
        
        aux.set(orientacion);
        aux.normalize();
        aux.mult(distanciaJoints/6 *2);
        
        aux.add(lowerJointPos);
        
        positionRP2.set(aux);
        
        positionRP2.z = -(positionRP2.z - body.getPositionOffset());
        
        //calculo pos 3
        aux.set(orientacion);
        aux.normalize();
        aux.mult(distanciaJoints/6 *3);
        
        aux.add(lowerJointPos);
        
        positionRP3.set(aux);
        
        positionRP3.z = -(positionRP3.z - body.getPositionOffset());
        
        //calculo pos 4
        aux.set(orientacion);
        aux.normalize();
        aux.mult(distanciaJoints/6 *4);
        
        aux.add(lowerJointPos);
        
        positionRP4.set(aux);
        
        positionRP4.z = -(positionRP4.z - body.getPositionOffset());
        
        //calculo pos 5
        aux.set(orientacion);
        aux.normalize();
        aux.mult(distanciaJoints/6 *5);
        
        aux.add(lowerJointPos);
        
        positionRP5.set(aux);
        
        positionRP5.z = -(positionRP5.z - body.getPositionOffset());
        
        
        
        positionRP1.z = 0;
        positionRP2.z = 0;
        positionRP3.z = 0;
        positionRP4.z = 0;
        positionRP5.z = 0;
        
        positionRP1.set(CalculateVectors.getRealPositionBody(positionRP1, body.getParent()));
        positionRP2.set(CalculateVectors.getRealPositionBody(positionRP2, body.getParent()));
        positionRP3.set(CalculateVectors.getRealPositionBody(positionRP3, body.getParent()));
        positionRP4.set(CalculateVectors.getRealPositionBody(positionRP4, body.getParent()));
        positionRP5.set(CalculateVectors.getRealPositionBody(positionRP5, body.getParent()));
        
        prism1.setPos(positionRP1);
        prism1.setDimensions(new PVector(defaultSize,defaultSize,defaultSize));
        prism2.setPos(positionRP2);
        prism2.setDimensions(new PVector(defaultSize,defaultSize,defaultSize));
        prism3.setPos(positionRP3);
        prism3.setDimensions(new PVector(defaultSize,defaultSize,defaultSize));
        prism4.setPos(positionRP4);
        prism4.setDimensions(new PVector(defaultSize,defaultSize,defaultSize));
        prism5.setPos(positionRP5);
        prism5.setDimensions(new PVector(defaultSize,defaultSize,defaultSize));
    }

    private void updateTorsoGeometricFigure(RectangularPrism torso, PVector position, float rotationZ, float rotationY, float memberWidth, float memberHeight, User user) {
        position.set(CalculateVectors.getRealPositionBody(position, user.getBody().getParent()));
        torso.setPos(position);
        torso.setRotationZ(rotationZ);
        torso.setRotationY(rotationY);
        torso.setDimensionX(memberWidth);
        torso.setDimensionY(memberHeight);
        torso.setDimensionZ(rotationY);

    }
}
