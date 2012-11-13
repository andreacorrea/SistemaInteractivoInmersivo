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
    private Map<String, BodyMember> _bodyMembers;
    private int _bodyColor;
    private float positionOffset;

    

    public Body(PApplet p, User user, int bodyColor) {
        _parent = p;
        _user = user;
        _context = user.getContext();
        _bodyColor = bodyColor;
        _bodyMembers = new HashMap<String, BodyMember>();

        createSkeleton();
    }

    public void createSkeleton() {
        //_bodyMembers.put("HEAD", new Limb(_parent, "HEAD", _bodyColor));
        _bodyMembers.put("TORSO", new Torso(_parent, "TORSO", _bodyColor));
        _bodyMembers.put("LEFT_FOREARM", new Limb(_parent, "LEFT_FOREARM", _bodyColor, this));
        _bodyMembers.put("LEFT_ARM", new Limb(_parent, "LEFT_ARM", _bodyColor, this));
        _bodyMembers.put("RIGHT_FOREARM", new Limb(_parent, "RIGHT_FOREARM", _bodyColor, this));
        _bodyMembers.put("RIGHT_ARM", new Limb(_parent, "RIGHT_ARM", _bodyColor, this));
        _bodyMembers.put("LEFT_THIGH", new Limb(_parent, "LEFT_THIGH", _bodyColor, this));
        _bodyMembers.put("LEFT_LEG", new Limb(_parent, "LEFT_LEG", _bodyColor, this));
        _bodyMembers.put("RIGHT_THIGH", new Limb(_parent, "RIGHT_THIGH", _bodyColor, this));
        _bodyMembers.put("RIGHT_LEG", new Limb(_parent, "RIGHT_LEG", _bodyColor, this));
        

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

        /*BodyMember member = (BodyMember) _bodyMembers.get("TORSO");
         member.drawBodyMember();
         member = (BodyMember) _bodyMembers.get("LEFT_FOREARM");
         member.drawBodyMember();*/

        BodyMember currentMember;
        Iterator member = _bodyMembers.values().iterator();

        while (member.hasNext()) {
            currentMember = ((BodyMember) member.next());
            currentMember.drawBodyMember();
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
        //updateHeadMember();
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
        Torso torso = (Torso) _bodyMembers.get("TORSO");
        positionOffset = getJointPos(SimpleOpenNI.SKEL_LEFT_SHOULDER).z;
        torso.update(getJointPos(SimpleOpenNI.SKEL_LEFT_SHOULDER), getJointPos(SimpleOpenNI.SKEL_RIGHT_SHOULDER), getJointPos(SimpleOpenNI.SKEL_LEFT_HIP), getJointPos(SimpleOpenNI.SKEL_RIGHT_HIP));
        
    }

    private void updateHeadMember() {
        Limb aux = (Limb) _bodyMembers.get("HEAD");
        aux.update(getJointPos(SimpleOpenNI.SKEL_HEAD), getJointPos(SimpleOpenNI.SKEL_NECK));
    }

    private void updateLeftForearmMember() {
        Limb aux = (Limb) _bodyMembers.get("LEFT_FOREARM");
        aux.update(getJointPos(SimpleOpenNI.SKEL_LEFT_ELBOW), getJointPos(SimpleOpenNI.SKEL_LEFT_SHOULDER));
    }

    private void updateLeftArmMember() {
        Limb aux = (Limb) _bodyMembers.get("LEFT_ARM");
        aux.update(getJointPos(SimpleOpenNI.SKEL_LEFT_HAND), getJointPos(SimpleOpenNI.SKEL_LEFT_ELBOW));
    }

    private void updateRightForearmMember() {
        Limb aux = (Limb) _bodyMembers.get("RIGHT_FOREARM");
        aux.update(getJointPos(SimpleOpenNI.SKEL_RIGHT_ELBOW), getJointPos(SimpleOpenNI.SKEL_RIGHT_SHOULDER));
    }

    private void updateRightArmMember() {
        Limb aux = (Limb) _bodyMembers.get("RIGHT_ARM");
        aux.update(getJointPos(SimpleOpenNI.SKEL_RIGHT_HAND), getJointPos(SimpleOpenNI.SKEL_RIGHT_ELBOW));

    }

    private void updateLeftThighMember() {
        Limb aux = (Limb) _bodyMembers.get("LEFT_THIGH");
        aux.update(getJointPos(SimpleOpenNI.SKEL_LEFT_KNEE), getJointPos(SimpleOpenNI.SKEL_LEFT_HIP));

    }

    private void updateLeftLegMember() {
        Limb aux = (Limb) _bodyMembers.get("LEFT_LEG");
        aux.update(getJointPos(SimpleOpenNI.SKEL_LEFT_FOOT), getJointPos(SimpleOpenNI.SKEL_LEFT_KNEE));

    }

    private void updateRightThighMember() {
        Limb aux = (Limb) _bodyMembers.get("RIGHT_THIGH");
        aux.update(getJointPos(SimpleOpenNI.SKEL_RIGHT_KNEE), getJointPos(SimpleOpenNI.SKEL_RIGHT_HIP));
    }

    private void updateRightLegMember() {
        Limb aux = (Limb) _bodyMembers.get("RIGHT_LEG");
        aux.update(getJointPos(SimpleOpenNI.SKEL_RIGHT_FOOT), getJointPos(SimpleOpenNI.SKEL_RIGHT_KNEE));
    }

    /**
     *
     * @param point
     */
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
        _parent.sphere(10);
        _parent.popMatrix();
    }
    
    public float getPositionOffset() {
        return positionOffset;
    }
}