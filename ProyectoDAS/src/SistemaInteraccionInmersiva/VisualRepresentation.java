package SistemaInteraccionInmersiva;

import UsersManagement.AdapterSimpleOpenNI;
import processing.core.*;

public class VisualRepresentation {

    private PApplet pApplet;
    private AdapterSimpleOpenNI context;
    private int resolution = 0;
    private float zmin = 0, zmax = 0;
    private float zoom = 0;
    private float rotX = 0;
    private float rotY = 0;
    private boolean realColor = false;
    private int defaultColor = 255;
    private int[] depthMap;
    private PVector[] realWorldMap;
    private PImage rgbImage;
    private int[] resDepth;
    private PVector[] resMap3D;
    private PImage resRGB = new PImage();
    private boolean[] constrainedImg;

    public VisualRepresentation(PApplet pApplet, AdapterSimpleOpenNI context) {
        this.pApplet = pApplet;
        resolution = 2;
        zmin = 0;
        zmax = 2000;
        zoom = 0.5f;
        rotX = pApplet.PI;
        rotY = 0;
        realColor = true;

        this.context = context;

        context.setMirror(true);

        // enable depthMap generation 
        CheckKinect.checkDepthCam(pApplet, context);

        // enable RGB generation
        CheckKinect.checkRGBCam(pApplet, context);
    }

    public void update() {

        depthMap = context.depthMap();
        realWorldMap = context.depthMapRealWorld();
        rgbImage = context.rgbImage();

        resDepth = resizeDepth(depthMap, resolution);
        resMap3D = resizeMap3D(realWorldMap, resolution);
        resRGB = resizeRGB(rgbImage, resolution);

        constrainedImg = constrainImg(resDepth, resMap3D, zmin, zmax);
        //pApplet.pushMatrix();

        positionVisualRepresentation();

        // Lights with real colors doesn't look very nice
        if (!realColor) {
            pApplet.directionalLight(255, 255, 255, 0, -0.2f, 1);
        }

        // Paint the kinect points


        if (realColor) {
            drawAsBands(resRGB, resMap3D, constrainedImg);
        } else {
            defaultColor = pApplet.color(50, 50, 255);
            drawAsBands(defaultColor, resRGB, resMap3D, constrainedImg);
        }

        //pApplet.popMatrix();


    }

    private void positionVisualRepresentation() {
        // Position the scene
        //pApplet.background(10);
        pApplet.translate(pApplet.width / 2, pApplet.height / 2, 0);
        pApplet.rotateX(rotX);
        pApplet.rotateY(rotY);
        pApplet.scale(zoom);
        pApplet.translate(0, 0, -1500);

    }

    private int[] resizeDepth(int[] depthMap, int resolution) {
        int xSizeOrig = context.depthWidth();
        int ySizeOrig = context.depthHeight();
        int xSize = xSizeOrig / resolution;
        int ySize = ySizeOrig / resolution;
        int[] resDepthImg = new int[xSize * ySize];

        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {
                resDepthImg[x + y * xSize] = depthMap[x * resolution + y * resolution * xSizeOrig];
            }
        }
        return resDepthImg;
    }

    private PVector[] resizeMap3D(PVector[] map3D, int resolution) {
        int xSizeOrig = context.depthWidth();
        int ySizeOrig = context.depthHeight();
        int xSize = xSizeOrig / resolution;
        int ySize = ySizeOrig / resolution;
        PVector[] resMap3D = new PVector[xSize * ySize];

        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {
                resMap3D[x + y * xSize] = map3D[x * resolution + y * resolution * xSizeOrig].get();
            }
        }
        return resMap3D;
    }

    private PImage resizeRGB(PImage rgbImg, int resolution) {
        int xSizeOrig = context.depthWidth();
        int ySizeOrig = context.depthHeight();
        int xSize = xSizeOrig / resolution;
        int ySize = ySizeOrig / resolution;
        PImage resRGB = pApplet.createImage(xSize, ySize, pApplet.RGB);

        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {
                resRGB.pixels[x + y * xSize] = rgbImg.pixels[x * resolution + y * resolution * xSizeOrig];
            }
        }
        return resRGB;
    }

    private boolean[] constrainImg(int[] depthImg, PVector[] map3D, float zMin, float zMax) {
        boolean[] consImg = new boolean[depthImg.length];

        for (int i = 0; i < consImg.length; i++) {
            PVector p = map3D[i];
            consImg[i] = (depthImg[i] > 0)
                    && (p.z > zMin) && (p.z < zMax);
        }
        return consImg;
    }

    private void drawAsBands(PImage rgbImg, PVector[] map3D, boolean[] consImg) {
        pApplet.noStroke();

        int xSize = rgbImg.width;
        int ySize = rgbImg.height;
        float maxSep = 90;         // Maximum separation allowed between two consecutive points
        boolean started = false;   // Controls when a band has been started

        for (int y = 0; y < ySize - 1; y++) {
            for (int x = 0; x < xSize; x++) {
                int index = x + y * xSize;
                if (consImg[index]) {
                    // Upper point
                    pApplet.fill(rgbImg.pixels[index]);
                    PVector p = map3D[index];
                    if (!started) {
                        pApplet.beginShape(pApplet.TRIANGLE_STRIP);
                        pApplet.vertex(p.x, p.y, p.z);
                        started = true;
                    } else if (p.dist(map3D[index - 1]) < maxSep) {
                        pApplet.vertex(p.x, p.y, p.z);
                    } else {
                        pApplet.endShape();
                        started = false;
                        x--;  // Is a good point, use it in the next loop as starting point for a new band
                        continue;
                    }
                    // Lower point        
                    if (consImg[index + xSize]) {
                        PVector lp = map3D[index + xSize];
                        if (p.dist(lp) < maxSep) {
                            pApplet.fill(rgbImg.pixels[index + xSize]);
                            pApplet.vertex(lp.x, lp.y, lp.z);
                        } else {
                            pApplet.vertex(p.x, p.y, p.z);
                        }
                    } else {
                        pApplet.vertex(p.x, p.y, p.z);
                    }
                    // Finish the band if is the last point in the row
                    if (x == xSize - 1) {
                        pApplet.endShape();
                        started = false;
                    }
                } else if (started) {
                    // Upper point is not valid, let's see if we can use the lower point
                    // for the last point in the band
                    if (consImg[index + xSize]) {
                        PVector lp = map3D[index + xSize];
                        if (lp.dist(map3D[index - 1]) < maxSep) {
                            pApplet.fill(rgbImg.pixels[index + xSize]);
                            pApplet.vertex(lp.x, lp.y, lp.z);
                        }
                    }
                    pApplet.endShape();
                    started = false;
                }
            }
        }
    }

    void drawAsBands(int col, PImage rgbImg, PVector[] map3D, boolean[] consImg) {
        pApplet.noStroke();
        pApplet.fill(col);

        int xSize = rgbImg.width;
        int ySize = rgbImg.height;
        float maxSep = 90;         // Maximum separation allowed between two consecutive points
        boolean started = false;   // Controls when a band has been started

        for (int y = 0; y < ySize - 1; y++) {
            for (int x = 0; x < xSize; x++) {
                int index = x + y * xSize;
                try {
                    if (consImg[index]) {
                        // Upper point
                        PVector p = map3D[index];
                        if (!started) {
                            pApplet.beginShape(pApplet.TRIANGLE_STRIP);
                            pApplet.vertex(p.x, p.y, p.z);
                            started = true;
                        } else if (p.dist(map3D[index - 1]) < maxSep) {
                            pApplet.vertex(p.x, p.y, p.z);
                        } else {
                            pApplet.endShape();
                            started = false;
                            x--;  // Is a good point, use it in the next loop as starting point for a new band
                            continue;
                        }
                        try {
                            // Lower point        
                            if (consImg[index + xSize]) {
                                PVector lp = map3D[index + xSize];
                                if (p.dist(lp) < maxSep) {
                                    pApplet.vertex(lp.x, lp.y, lp.z);
                                } else {
                                    pApplet.vertex(p.x, p.y, p.z);
                                }
                            } else {
                                pApplet.vertex(p.x, p.y, p.z);
                            }
                        } catch (ArrayIndexOutOfBoundsException ex) {
                            pApplet.println(ex.toString());
                        }
                        // Finish the band if is the last point in the row
                        if (x == xSize - 1) {
                            pApplet.endShape();
                            started = false;
                        }
                    } else if (started) {
                        // Upper point is not valid, let's see if we can use the lower point
                        // as the last point in the band
                        try {
                            if (consImg[index + xSize]) {
                                PVector lp = map3D[index + xSize];
                                if (lp.dist(map3D[index - 1]) < maxSep) {
                                    pApplet.vertex(lp.x, lp.y, lp.z);
                                }
                            }
                        } catch (ArrayIndexOutOfBoundsException ex) {
                            pApplet.println(ex.toString());
                        }
                        pApplet.endShape();
                        started = false;
                    }
                } catch (ArrayIndexOutOfBoundsException ex) {
                    pApplet.println(ex.toString());
                }
            }
        }
    }

    public void setDefaultColor(int defaultColor) {
        this.defaultColor = defaultColor;
    }
}
