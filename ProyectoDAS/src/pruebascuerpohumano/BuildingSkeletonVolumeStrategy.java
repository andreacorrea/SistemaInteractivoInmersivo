package pruebascuerpohumano;

import SimpleOpenNI.SimpleOpenNI;
import java.util.HashMap;
import java.util.Map;
import processing.core.PApplet;

public interface BuildingSkeletonVolumeStrategy {
    public void updateVolume(Body body);
    public void createSkeleton(Body body);
}
