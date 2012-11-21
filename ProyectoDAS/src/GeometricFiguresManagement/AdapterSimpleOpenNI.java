/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GeometricFiguresManagement;

import SimpleOpenNI.SimpleOpenNI;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

/**
 *
 * @author Andrea
 */
public class AdapterSimpleOpenNI {

    private SimpleOpenNI adaptee;
    public static int SKEL_HEAD ;
    public static int SKEL_NECK ;
    public static int SKEL_LEFT_SHOULDER;
    public static int SKEL_LEFT_ELBOW;
    public static int SKEL_LEFT_HAND ;
    public static int SKEL_RIGHT_SHOULDER;
    public static int SKEL_RIGHT_ELBOW;
    public static int SKEL_RIGHT_HAND ;
    public static int SKEL_LEFT_HIP ;
    public static int SKEL_LEFT_KNEE;
    public static int SKEL_LEFT_FOOT;
    public static int SKEL_RIGHT_HIP;
    public static int SKEL_RIGHT_KNEE;
    public static int SKEL_RIGHT_FOOT;

    public AdapterSimpleOpenNI(PApplet pApplet) {
        adaptee = new SimpleOpenNI(pApplet);
    
        SKEL_HEAD = SimpleOpenNI.SKEL_HEAD;
        SKEL_NECK = SimpleOpenNI.SKEL_NECK;
        SKEL_LEFT_SHOULDER = SimpleOpenNI.SKEL_LEFT_SHOULDER;
        SKEL_LEFT_ELBOW = SimpleOpenNI.SKEL_LEFT_ELBOW;
        SKEL_LEFT_HAND = SimpleOpenNI.SKEL_LEFT_HAND;
        SKEL_RIGHT_SHOULDER = SimpleOpenNI.SKEL_RIGHT_SHOULDER;
        SKEL_RIGHT_ELBOW = SimpleOpenNI.SKEL_RIGHT_ELBOW;
        SKEL_RIGHT_HAND = SimpleOpenNI.SKEL_RIGHT_HAND;
        SKEL_LEFT_HIP = SimpleOpenNI.SKEL_LEFT_HIP;
        SKEL_LEFT_KNEE = SimpleOpenNI.SKEL_LEFT_KNEE;
        SKEL_LEFT_FOOT = SimpleOpenNI.SKEL_LEFT_FOOT;
        SKEL_RIGHT_HIP = SimpleOpenNI.SKEL_RIGHT_HIP;
        SKEL_RIGHT_KNEE = SimpleOpenNI.SKEL_RIGHT_KNEE;
        SKEL_RIGHT_FOOT = SimpleOpenNI.SKEL_RIGHT_FOOT;
    }
public void update(){
        adaptee.update();
    }
    
    
    
    public void drawLimb(int userId, int joint1, int joint2){
        adaptee.drawLimb(userId, joint1 , joint2);
    }
    
    public void getJointPositionSkeleton(int userId, int joint, PVector pos){
        adaptee.getJointPositionSkeleton(userId, joint, pos);
    }
    
    public void convertRealWorldToProjective(PVector jointPos, PVector jointPos_Proj){
        adaptee.convertRealWorldToProjective(jointPos, jointPos_Proj);
    }
    
    public boolean enableDepth(){
        return adaptee.enableDepth();
    }
    
    public boolean enableRGB(){
        return adaptee.enableRGB();
    }
    
    public boolean enableScene(){
        return adaptee.enableScene();
    }
    
    public PImage depthImage(){
        return adaptee.depthImage();
    }
    
    public PImage sceneImage(){
        return adaptee.sceneImage();
    }
    
    public void setMirror(boolean setMirror){
        adaptee.setMirror(setMirror);
    }
    
    public void enableUser(int SkeletonOption, Object classCallback){
        adaptee.enableUser(SkeletonOption, classCallback);
    }
    
    public void startPoseDetection(String pose, int userId){
        adaptee.startPoseDetection(pose, userId);
    }
    
    public void stopPoseDetection(int userId){
        adaptee.stopPoseDetection(userId);
    }
    
    public void requestCalibrationSkeleton(int userId, boolean request){
        adaptee.requestCalibrationSkeleton(userId, true);
    }
    
    public void startTrackingSkeleton(int userId){
        adaptee.startTrackingSkeleton(userId);
    }
    
    public int[] depthMap(){
        return adaptee.depthMap();
    }
    
    public PVector [] depthMapRealWorld(){
        return adaptee.depthMapRealWorld();
    }
    
    public PImage rgbImage(){
        return adaptee.rgbImage();
    }
    
    public int depthWidth(){
        return adaptee.depthWidth();
    }
    
    public int depthHeight(){
        return adaptee.depthHeight();
    }

}
