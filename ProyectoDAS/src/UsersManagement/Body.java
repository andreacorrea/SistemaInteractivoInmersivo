package UsersManagement;

import GeometricFiguresManagement.GeometricFigure;
import processing.core.*;
import SimpleOpenNI.*;
import SistemaInteraccionInmersiva.CalculateVectors;
import Interaction.Command;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/*
 * Esta clase representa al cuerpo de un usuario
 */
public class Body {

    private PApplet parent;
    private User user;
    private AdapterSimpleOpenNI context;
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
        buildingSkeletonVolumeStrategy.createSkeleton(this);
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
        this.buildingSkeletonVolumeStrategy.updateVolume(this);
    }

    public float getPositionOffset() {
        return positionOffset;
    }

    public void addObserverGFBody(GeometricFigure gf) {
        GeometricFigure bodyMember;

        Iterator bodyMemberIterator = bodyMembers.values().iterator();

        while (bodyMemberIterator.hasNext()) {
            bodyMember = ((GeometricFigure) bodyMemberIterator.next());
            bodyMember.addObserver(gf);
            gf.addObserver(bodyMember);
        }
    }
    
    public void removeObserverGFBody(GeometricFigure gf) {
        GeometricFigure currentGeometricFigure;

        Iterator bodyMember = bodyMembers.values().iterator();

        while (bodyMember.hasNext()) {
            currentGeometricFigure = ((GeometricFigure) bodyMember.next());
            currentGeometricFigure.removeObserver(gf);
            gf.removeObserver(currentGeometricFigure);
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
        this.positionOffset = CalculateVectors.getJointPos(AdapterSimpleOpenNI.SKEL_LEFT_SHOULDER, context, user).z;
    }

    public Map<String, GeometricFigure> getBodyMembers() {
        return bodyMembers;
    }

    public PApplet getParent() {
        return parent;
    }

    public AdapterSimpleOpenNI getContext() {
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
        //Eliminar todos los elementos actuales del cuerpo
        bodyMembers.clear();
        //Establecer nueva estrategia
        this.buildingSkeletonVolumeStrategy = buildingSkeletonVolumeStrategy;
        createSkeleton();
    }

    
    
    
    
    
}