/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UsersManagement.VolumeGeneration;

import GeometricFiguresManagement.Ball;
import GeometricFiguresManagement.GeometricFigure;
import GeometricFiguresManagement.RectangularPrism;
import SistemaInteraccionInmersiva.CalculateVectors;
import UsersManagement.AdapterSimpleOpenNI;
import UsersManagement.User;
import java.util.HashMap;
import processing.core.PVector;

public class Spheres5SkeletonVolume implements BuildingSkeletonVolumeStrategy {

    private float defaultSize=10;
    
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
        body.getBodyMembers().put("LEFT_FOREARM_S1", new Ball(body.getParent(), "LEFT_FOREARM_S1" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("LEFT_FOREARM_S2", new Ball(body.getParent(), "LEFT_FOREARM_S2" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("LEFT_FOREARM_S3", new Ball(body.getParent(), "LEFT_FOREARM_S3" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("LEFT_FOREARM_S4", new Ball(body.getParent(), "LEFT_FOREARM_S4" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("LEFT_FOREARM_S5", new Ball(body.getParent(), "LEFT_FOREARM_S5" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("LEFT_ARM_S1", new Ball(body.getParent(), "LEFT_ARM_S1" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("LEFT_ARM_S2", new Ball(body.getParent(), "LEFT_ARM_S2" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("LEFT_ARM_S3", new Ball(body.getParent(), "LEFT_ARM_S3" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("LEFT_ARM_S4", new Ball(body.getParent(), "LEFT_ARM_S4" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("LEFT_ARM_S5", new Ball(body.getParent(), "LEFT_ARM_S5" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("RIGHT_FOREARM_S1", new Ball(body.getParent(), "RIGHT_FOREARM_S1" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("RIGHT_FOREARM_S2", new Ball(body.getParent(), "RIGHT_FOREARM_S2" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("RIGHT_FOREARM_S3", new Ball(body.getParent(), "RIGHT_FOREARM_S3" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("RIGHT_FOREARM_S4", new Ball(body.getParent(), "RIGHT_FOREARM_S4" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("RIGHT_FOREARM_S5", new Ball(body.getParent(), "RIGHT_FOREARM_S5" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("RIGHT_ARM_S1", new Ball(body.getParent(), "RIGHT_ARM_S1" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("RIGHT_ARM_S2", new Ball(body.getParent(), "RIGHT_ARM_S2" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("RIGHT_ARM_S3", new Ball(body.getParent(), "RIGHT_ARM_S3" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("RIGHT_ARM_S4", new Ball(body.getParent(), "RIGHT_ARM_S4" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("RIGHT_ARM_S5", new Ball(body.getParent(), "RIGHT_ARM_S5" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("LEFT_THIGH_S1", new Ball(body.getParent(), "LEFT_THIGH_S1" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("LEFT_THIGH_S2", new Ball(body.getParent(), "LEFT_THIGH_S2" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("LEFT_THIGH_S3", new Ball(body.getParent(), "LEFT_THIGH_S3" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("LEFT_THIGH_S4", new Ball(body.getParent(), "LEFT_THIGH_S4" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("LEFT_THIGH_S5", new Ball(body.getParent(), "LEFT_THIGH_S5" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("LEFT_LEG_S1", new Ball(body.getParent(), "LEFT_LEG_S1" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("LEFT_LEG_S2", new Ball(body.getParent(), "LEFT_LEG_S2" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("LEFT_LEG_S3", new Ball(body.getParent(), "LEFT_LEG_S3" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("LEFT_LEG_S4", new Ball(body.getParent(), "LEFT_LEG_S4" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("LEFT_LEG_S5", new Ball(body.getParent(), "LEFT_LEG_S5" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("RIGHT_THIGH_S1", new Ball(body.getParent(), "RIGHT_THIGH_S1" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("RIGHT_THIGH_S2", new Ball(body.getParent(), "RIGHT_THIGH_S2" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("RIGHT_THIGH_S3", new Ball(body.getParent(), "RIGHT_THIGH_S3" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("RIGHT_THIGH_S4", new Ball(body.getParent(), "RIGHT_THIGH_S4" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("RIGHT_THIGH_S5", new Ball(body.getParent(), "RIGHT_THIGH_S5" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("RIGHT_LEG_S1", new Ball(body.getParent(), "RIGHT_LEG_S1" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("RIGHT_LEG_S2", new Ball(body.getParent(), "RIGHT_LEG_S2" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("RIGHT_LEG_S3", new Ball(body.getParent(), "RIGHT_LEG_S3" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("RIGHT_LEG_S4", new Ball(body.getParent(), "RIGHT_LEG_S4" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
        body.getBodyMembers().put("RIGHT_LEG_S5", new Ball(body.getParent(), "RIGHT_LEG_S5" + "_" + body.getUser().getUserId(), body.getBodyColor(), body.getDefaultMass()));
    }
    
    private void updateMembers(Body body) {
        updateHeadMember((HashMap)body.getBodyMembers(), body.getContext(), body.getUser());
        updateTorsoMember(body, (HashMap)body.getBodyMembers(), body.getContext(), body.getUser());
        updateLimbMember(body, "LEFT_FOREARM_S1", "LEFT_FOREARM_S2", "LEFT_FOREARM_S3", "LEFT_FOREARM_S4", "LEFT_FOREARM_S5", AdapterSimpleOpenNI.SKEL_LEFT_ELBOW, AdapterSimpleOpenNI.SKEL_LEFT_SHOULDER);
        updateLimbMember(body, "LEFT_ARM_S1", "LEFT_ARM_S2", "LEFT_ARM_S3", "LEFT_ARM_S4", "LEFT_ARM_S5", AdapterSimpleOpenNI.SKEL_LEFT_HAND, AdapterSimpleOpenNI.SKEL_LEFT_ELBOW);
        updateLimbMember(body, "RIGHT_FOREARM_S1", "RIGHT_FOREARM_S2", "RIGHT_FOREARM_S3", "RIGHT_FOREARM_S4", "RIGHT_FOREARM_S5", AdapterSimpleOpenNI.SKEL_RIGHT_ELBOW, AdapterSimpleOpenNI.SKEL_RIGHT_SHOULDER);
        updateLimbMember(body, "RIGHT_ARM_S1", "RIGHT_ARM_S2", "RIGHT_ARM_S3", "RIGHT_ARM_S4", "RIGHT_ARM_S5", AdapterSimpleOpenNI.SKEL_RIGHT_HAND, AdapterSimpleOpenNI.SKEL_RIGHT_ELBOW);
        updateLimbMember(body, "LEFT_THIGH_S1", "LEFT_THIGH_S2", "LEFT_THIGH_S3", "LEFT_THIGH_S4", "LEFT_THIGH_S5", AdapterSimpleOpenNI.SKEL_LEFT_KNEE, AdapterSimpleOpenNI.SKEL_LEFT_HIP);
        updateLimbMember(body, "LEFT_LEG_S1", "LEFT_LEG_S2", "LEFT_LEG_S3", "LEFT_LEG_S4", "LEFT_LEG_S5", AdapterSimpleOpenNI.SKEL_LEFT_FOOT, AdapterSimpleOpenNI.SKEL_LEFT_KNEE);
        updateLimbMember(body, "RIGHT_THIGH_S1", "RIGHT_THIGH_S2", "RIGHT_THIGH_S3", "RIGHT_THIGH_S4", "RIGHT_THIGH_S5", AdapterSimpleOpenNI.SKEL_RIGHT_KNEE, AdapterSimpleOpenNI.SKEL_RIGHT_HIP);
        updateLimbMember(body, "RIGHT_LEG_S1", "RIGHT_LEG_S2", "RIGHT_LEG_S3", "RIGHT_LEG_S4", "RIGHT_LEG_S5", AdapterSimpleOpenNI.SKEL_RIGHT_FOOT, AdapterSimpleOpenNI.SKEL_RIGHT_KNEE);

        
        
    }

    private void updateHeadMember(HashMap <String, GeometricFigure>bodyMembers, AdapterSimpleOpenNI context, User user) {
        Ball head = (Ball) bodyMembers.get("HEAD");
        PVector headPosition = CalculateVectors.getJointPos(AdapterSimpleOpenNI.SKEL_HEAD, context, user);
        
        float headsize = 100;

        // crear una distancia escalar relacionada a la profundidad (dimension Z)
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
        PVector upperMiddlePoint = CalculateVectors.calculateMiddlePoint(leftUpperJointPos, rightUpperJointPos);
        PVector lowerMiddlePoint = CalculateVectors.calculateMiddlePoint(leftLowerJointPos, rightLowerJointPos);

        PVector leftMiddlePoint = CalculateVectors.calculateMiddlePoint(leftUpperJointPos, leftLowerJointPos);

        PVector rightMiddlePoint = CalculateVectors.calculateMiddlePoint(rightUpperJointPos, rightLowerJointPos);

        //Calculate Position
        PVector position = new PVector();
        PVector middlePoint1 = CalculateVectors.calculateMiddlePoint(leftUpperJointPos, rightLowerJointPos);
        PVector middlePoint2 = CalculateVectors.calculateMiddlePoint(rightUpperJointPos, leftLowerJointPos);

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
        Ball sphere1 = (Ball) body.getBodyMembers().get(nameSphere1);
        Ball sphere2 = (Ball) body.getBodyMembers().get(nameSphere2);
        Ball sphere3 = (Ball) body.getBodyMembers().get(nameSphere3);
        Ball sphere4 = (Ball) body.getBodyMembers().get(nameSphere4);
        Ball sphere5 = (Ball) body.getBodyMembers().get(nameSphere5);
        PVector upperJointPos = CalculateVectors.getJointPos(upperPoint, body.getContext(), body.getUser());
        PVector lowerJointPos = CalculateVectors.getJointPos(lowerPoint, body.getContext(), body.getUser());
        //Calculate position
        PVector positionS1 = new PVector();
        PVector positionS2 = new PVector();
        PVector positionS3 = new PVector();
        PVector positionS4 = new PVector();
        PVector positionS5 = new PVector();

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
        
        positionS1.set(aux);
        
        positionS1.z = -(positionS1.z - body.getPositionOffset());
        
        //calculo pos 2
        
        aux.set(orientacion);
        aux.normalize();
        aux.mult(distanciaJoints/6 *2);
        
        aux.add(lowerJointPos);
        
        positionS2.set(aux);
        
        positionS2.z = -(positionS2.z - body.getPositionOffset());
        
        //calculo pos 3
        aux.set(orientacion);
        aux.normalize();
        aux.mult(distanciaJoints/6 *3);
        
        aux.add(lowerJointPos);
        
        positionS3.set(aux);
        
        positionS3.z = -(positionS3.z - body.getPositionOffset());
        
        //calculo pos 4
        aux.set(orientacion);
        aux.normalize();
        aux.mult(distanciaJoints/6 *4);
        
        aux.add(lowerJointPos);
        
        positionS4.set(aux);
        
        positionS4.z = -(positionS4.z - body.getPositionOffset());
        
        //calculo pos 5
        aux.set(orientacion);
        aux.normalize();
        aux.mult(distanciaJoints/6 *5);
        
        aux.add(lowerJointPos);
        
        positionS5.set(aux);
        
        positionS5.z = -(positionS5.z - body.getPositionOffset());
        
        
        
        positionS1.z = 0;
        positionS2.z = 0;
        positionS3.z = 0;
        positionS4.z = 0;
        positionS5.z = 0;
        
        positionS1.set(CalculateVectors.getRealPositionBody(positionS1, body.getParent()));
        positionS2.set(CalculateVectors.getRealPositionBody(positionS2, body.getParent()));
        positionS3.set(CalculateVectors.getRealPositionBody(positionS3, body.getParent()));
        positionS4.set(CalculateVectors.getRealPositionBody(positionS4, body.getParent()));
        positionS5.set(CalculateVectors.getRealPositionBody(positionS5, body.getParent()));
        
        sphere1.setPos(positionS1);
        sphere1.setRadius(defaultSize);
        sphere2.setPos(positionS2);
        sphere2.setRadius(defaultSize);
        sphere3.setPos(positionS3);
        sphere3.setRadius(defaultSize);
        sphere4.setPos(positionS4);
        sphere4.setRadius(defaultSize);
        sphere5.setPos(positionS5);
        sphere5.setRadius(defaultSize);
    }

    private void updateTorsoGeometricFigure(RectangularPrism torso, PVector position, float rotationZ, float rotationY, float memberWidth, float memberHeight, User user) {
        position.set(CalculateVectors.getRealPositionBody(position, user.getBody().getParent()));
        torso.setPos(position);
        torso.setRotationZ(rotationZ);
        torso.setRotationY(rotationY);
        torso.setDimensionX(memberWidth);
        torso.setDimensionY(memberHeight);
        torso.setDimensionZ(15);
        //torso.setDimensionZ(rotationY);

    }
}
