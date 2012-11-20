package pruebascuerpohumano;

import SimpleOpenNI.SimpleOpenNI;
import processing.core.PApplet;
import processing.core.PVector;

public class CalculateVectors {

    // Devuelve un PVector que contiene las coordenadas en RealWorld del jointPos solicitado
    public static PVector getJointPos(int jointId, AdapterSimpleOpenNI context, User user) {
        PVector jointPos = new PVector();
        context.getJointPositionSkeleton(user.getUserId(), jointId, jointPos);
        PVector jointPosRW = new PVector();
        context.convertRealWorldToProjective(jointPos, jointPosRW);
        return jointPosRW;
    }
}
