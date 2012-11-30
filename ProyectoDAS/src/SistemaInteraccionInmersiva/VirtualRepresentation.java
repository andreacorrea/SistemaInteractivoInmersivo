package SistemaInteraccionInmersiva;

import UsersManagement.AdapterSimpleOpenNI;
import processing.core.*;

public class VirtualRepresentation {

    private PApplet parent;
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
    private float maxSep = 90;         // Maxima separacion entre dos puntos consecutivos

    public VirtualRepresentation(PApplet pApplet, AdapterSimpleOpenNI context) {
        this.parent = pApplet;
        resolution = 2;
        zmin = 0;
        zmax = 2000;
        zoom = 0.5f;
        rotX = pApplet.PI;
        rotY = 0;
        realColor = true;

        this.context = context;

        context.setMirror(true);

        // habilita la generacion del depthMap
        CheckKinect.checkDepthCam(pApplet, context);

        // habilita la generacion RGB
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

        
        if (!realColor) {
            parent.directionalLight(255, 255, 255, 0, -0.2f, 1);
        }

        


        if (realColor) {
            drawAsBands(resRGB, resMap3D, constrainedImg);
        } else {
            defaultColor = parent.color(50, 50, 255);
            drawAsBands(defaultColor, resRGB, resMap3D, constrainedImg);
        }

        //pApplet.popMatrix();


    }

    private void positionVisualRepresentation() {
        // Posiciona la escena
        //pApplet.background(10);
        parent.translate(parent.width / 2, parent.height / 2, 0);
        parent.rotateX(rotX);
        parent.rotateY(rotY);
        parent.scale(zoom);
        parent.translate(0, 0, -1500);

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
        PImage resRGB = parent.createImage(xSize, ySize, parent.RGB);

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
        parent.noStroke();

        int xSize = rgbImg.width;
        int ySize = rgbImg.height;
        
        boolean started = false;

        for (int y = 0; y < ySize - 1; y++) {
            for (int x = 0; x < xSize; x++) {
                int index = x + y * xSize;
                if (consImg[index]) {
                    // Punto superior
                    parent.fill(rgbImg.pixels[index]);
                    PVector p = map3D[index];
                    if (!started) {
                        parent.beginShape(parent.TRIANGLE_STRIP);
                        parent.vertex(p.x, p.y, p.z);
                        started = true;
                    } else if (p.dist(map3D[index - 1]) < maxSep) {
                        parent.vertex(p.x, p.y, p.z);
                    } else {
                        parent.endShape();
                        started = false;
                        x--;  // Es un punto válido, se debe usar en el siguiente ciclo como punto de inicio para un nuevo triángulo
                        continue;
                    }
                    // Punto inferior      
                    if (consImg[index + xSize]) {
                        PVector lp = map3D[index + xSize];
                        if (p.dist(lp) < maxSep) {
                            parent.fill(rgbImg.pixels[index + xSize]);
                            parent.vertex(lp.x, lp.y, lp.z);
                        } else {
                            parent.vertex(p.x, p.y, p.z);
                        }
                    } else {
                        parent.vertex(p.x, p.y, p.z);
                    }
                    // Cierra el triángulo si es el último punto en la fila
                    if (x == xSize - 1) {
                        parent.endShape();
                        started = false;
                    }
                } else if (started) {
                    // El punto superior no es válido, se determina si se puede usar el punto 
                    //inferior como el ultimo punto del triangulo
                    if (consImg[index + xSize]) {
                        PVector lp = map3D[index + xSize];
                        if (lp.dist(map3D[index - 1]) < maxSep) {
                            parent.fill(rgbImg.pixels[index + xSize]);
                            parent.vertex(lp.x, lp.y, lp.z);
                        }
                    }
                    parent.endShape();
                    started = false;
                }
            }
        }
    }

    void drawAsBands(int col, PImage rgbImg, PVector[] map3D, boolean[] consImg) {
        parent.noStroke();
        parent.fill(col);

        int xSize = rgbImg.width;
        int ySize = rgbImg.height;
        boolean started = false;   // Controls when a band has been started

        for (int y = 0; y < ySize - 1; y++) {
            for (int x = 0; x < xSize; x++) {
                int index = x + y * xSize;
                try {
                    if (consImg[index]) {
                        // Punto superior
                        PVector p = map3D[index];
                        if (!started) {
                            parent.beginShape(parent.TRIANGLE_STRIP);
                            parent.vertex(p.x, p.y, p.z);
                            started = true;
                        } else if (p.dist(map3D[index - 1]) < maxSep) {
                            parent.vertex(p.x, p.y, p.z);
                        } else {
                            parent.endShape();
                            started = false;
                            x--;  // Es un punto válido, se debe usar en el siguiente ciclo como punto de inicio para un nuevo triángulo
                            continue;
                        }
                        try {
                            // Punto inferior      
                            if (consImg[index + xSize]) {
                                PVector lp = map3D[index + xSize];
                                if (p.dist(lp) < maxSep) {
                                    parent.vertex(lp.x, lp.y, lp.z);
                                } else {
                                    parent.vertex(p.x, p.y, p.z);
                                }
                            } else {
                                parent.vertex(p.x, p.y, p.z);
                            }
                        } catch (ArrayIndexOutOfBoundsException ex) {
                            parent.println(ex.toString());
                        }
                        // Cierra el triángulo si es el último punto en la fila
                        if (x == xSize - 1) {
                            parent.endShape();
                            started = false;
                        }
                    } else if (started) {
                        // El punto superior no es válido, se determina si se puede usar el punto 
                        //inferior como el ultimo punto del triangulo
                        try {
                            if (consImg[index + xSize]) {
                                PVector lp = map3D[index + xSize];
                                if (lp.dist(map3D[index - 1]) < maxSep) {
                                    parent.vertex(lp.x, lp.y, lp.z);
                                }
                            }
                        } catch (ArrayIndexOutOfBoundsException ex) {
                            parent.println(ex.toString());
                        }
                        parent.endShape();
                        started = false;
                    }
                } catch (ArrayIndexOutOfBoundsException ex) {
                    parent.println(ex.toString());
                }
            }
        }
    }

    public void setDefaultColor(int defaultColor) {
        this.defaultColor = defaultColor;
    }
}
