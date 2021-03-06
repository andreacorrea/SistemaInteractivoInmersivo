package UsersManagement.VolumeGeneration;

import GeometricFiguresManagement.GeometricFigure;
import Interaction.Command;
import SistemaInteraccionInmersiva.CalculateVectors;
import UsersManagement.AdapterSimpleOpenNI;
import UsersManagement.User;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import processing.core.*;

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
    private float defaultMass = 50;
    
    BuildingSkeletonVolumeStrategy buildingSkeletonVolumeStrategy;
    

    public Body(PApplet p, User user, int bodyColor) {
        this.parent = p;
        this.user = user;
        this.context = user.getContext();
        this.bodyColor = bodyColor;
        this.bodyMembers = new HashMap<String, GeometricFigure>();
        //this.buildingSkeletonVolumeStrategy = new CubesSkeletonVolume();
        this.buildingSkeletonVolumeStrategy = new Spheres5SkeletonVolume();
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
        context.drawLimb(user.getUserId(), AdapterSimpleOpenNI.SKEL_HEAD, AdapterSimpleOpenNI.SKEL_NECK);

        context.drawLimb(user.getUserId(), AdapterSimpleOpenNI.SKEL_NECK, AdapterSimpleOpenNI.SKEL_LEFT_SHOULDER);
        context.drawLimb(user.getUserId(), AdapterSimpleOpenNI.SKEL_LEFT_SHOULDER, AdapterSimpleOpenNI.SKEL_LEFT_ELBOW);
        context.drawLimb(user.getUserId(), AdapterSimpleOpenNI.SKEL_LEFT_ELBOW, AdapterSimpleOpenNI.SKEL_LEFT_HAND);

        context.drawLimb(user.getUserId(), AdapterSimpleOpenNI.SKEL_NECK, AdapterSimpleOpenNI.SKEL_RIGHT_SHOULDER);
        context.drawLimb(user.getUserId(), AdapterSimpleOpenNI.SKEL_RIGHT_SHOULDER, AdapterSimpleOpenNI.SKEL_RIGHT_ELBOW);
        context.drawLimb(user.getUserId(), AdapterSimpleOpenNI.SKEL_RIGHT_ELBOW, AdapterSimpleOpenNI.SKEL_RIGHT_HAND);

        context.drawLimb(user.getUserId(), AdapterSimpleOpenNI.SKEL_LEFT_SHOULDER, AdapterSimpleOpenNI.SKEL_TORSO);
        context.drawLimb(user.getUserId(), AdapterSimpleOpenNI.SKEL_RIGHT_SHOULDER, AdapterSimpleOpenNI.SKEL_TORSO);

        context.drawLimb(user.getUserId(), AdapterSimpleOpenNI.SKEL_TORSO, AdapterSimpleOpenNI.SKEL_LEFT_HIP);
        context.drawLimb(user.getUserId(), AdapterSimpleOpenNI.SKEL_LEFT_HIP, AdapterSimpleOpenNI.SKEL_LEFT_KNEE);
        context.drawLimb(user.getUserId(), AdapterSimpleOpenNI.SKEL_LEFT_KNEE, AdapterSimpleOpenNI.SKEL_LEFT_FOOT);

        context.drawLimb(user.getUserId(), AdapterSimpleOpenNI.SKEL_TORSO, AdapterSimpleOpenNI.SKEL_RIGHT_HIP);
        context.drawLimb(user.getUserId(), AdapterSimpleOpenNI.SKEL_RIGHT_HIP, AdapterSimpleOpenNI.SKEL_RIGHT_KNEE);
        context.drawLimb(user.getUserId(), AdapterSimpleOpenNI.SKEL_RIGHT_KNEE, AdapterSimpleOpenNI.SKEL_RIGHT_FOOT);

    }

    public void drawJoints() {
        parent.pushStyle();
        parent.strokeWeight(10);
        PVector currentJoint = new PVector();
        currentJoint = CalculateVectors.getJointPos(AdapterSimpleOpenNI.SKEL_HEAD, context, user);
        parent.point(currentJoint.x, currentJoint.y, 0);
        currentJoint = CalculateVectors.getJointPos(AdapterSimpleOpenNI.SKEL_NECK, context, user);
        parent.point(currentJoint.x, currentJoint.y, 0);
        currentJoint = CalculateVectors.getJointPos(AdapterSimpleOpenNI.SKEL_LEFT_SHOULDER, context, user);
        parent.point(currentJoint.x, currentJoint.y, 0);
        currentJoint = CalculateVectors.getJointPos(AdapterSimpleOpenNI.SKEL_LEFT_ELBOW, context, user);
        parent.point(currentJoint.x, currentJoint.y, 0);
        currentJoint = CalculateVectors.getJointPos(AdapterSimpleOpenNI.SKEL_LEFT_HAND, context, user);
        parent.point(currentJoint.x, currentJoint.y, 0);
        currentJoint = CalculateVectors.getJointPos(AdapterSimpleOpenNI.SKEL_RIGHT_SHOULDER, context, user);
        parent.point(currentJoint.x, currentJoint.y, 0);
        currentJoint = CalculateVectors.getJointPos(AdapterSimpleOpenNI.SKEL_RIGHT_ELBOW, context, user);
        parent.point(currentJoint.x, currentJoint.y, 0);
        currentJoint = CalculateVectors.getJointPos(AdapterSimpleOpenNI.SKEL_RIGHT_HAND, context, user);
        parent.point(currentJoint.x, currentJoint.y, 0);
        currentJoint = CalculateVectors.getJointPos(AdapterSimpleOpenNI.SKEL_TORSO, context, user);
        parent.point(currentJoint.x, currentJoint.y, 0);
        currentJoint = CalculateVectors.getJointPos(AdapterSimpleOpenNI.SKEL_LEFT_HIP, context, user);
        parent.point(currentJoint.x, currentJoint.y, 0);
        currentJoint = CalculateVectors.getJointPos(AdapterSimpleOpenNI.SKEL_LEFT_KNEE, context, user);
        parent.point(currentJoint.x, currentJoint.y, 0);
        currentJoint = CalculateVectors.getJointPos(AdapterSimpleOpenNI.SKEL_LEFT_FOOT, context, user);
        parent.point(currentJoint.x, currentJoint.y, 0);
        currentJoint = CalculateVectors.getJointPos(AdapterSimpleOpenNI.SKEL_RIGHT_HIP, context, user);
        parent.point(currentJoint.x, currentJoint.y, 0);
        currentJoint = CalculateVectors.getJointPos(AdapterSimpleOpenNI.SKEL_RIGHT_KNEE, context, user);
        parent.point(currentJoint.x, currentJoint.y, 0);
        currentJoint = CalculateVectors.getJointPos(AdapterSimpleOpenNI.SKEL_RIGHT_FOOT, context, user);
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

    public void setBuildingSkeletonVolumeStrategy(BuildingSkeletonVolumeStrategy buildingSkeletonVolumeStrategy) {
        //Eliminar todos los elementos actuales del cuerpo
        bodyMembers.clear();
        //Establecer nueva estrategia
        this.buildingSkeletonVolumeStrategy = buildingSkeletonVolumeStrategy;
        createSkeleton();
    }

    
    
    
    
    
}