package SistemaInteraccionInmersiva;

import UsersManagement.AdapterSimpleOpenNI;
import UsersManagement.User;
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
    
    public static PVector getRealPositionBody(PVector posActual, PApplet parent){
        float posX = parent.map(posActual.x, 0, 640, 0, parent.width);
        //float posY = parent.map(posActual.y, 0, 480, 0, parent.height);
        PVector resp = new PVector(posX, posActual.y, posActual.z);
        return resp;
    }
}
