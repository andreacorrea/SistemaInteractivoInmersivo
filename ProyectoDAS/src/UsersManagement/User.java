package UsersManagement;

import UsersManagement.Body;
import SimpleOpenNI.*;
import GeometricFiguresManagement.AdapterSimpleOpenNI;
import processing.core.*;

/*
 *
 * Esta clase representa a un usuario en el mundo virtual
 */
public class User {
    private AdapterSimpleOpenNI context;
    private int userId;
    private Body body;
    private int userColor;

    public User(PApplet p, int userId, AdapterSimpleOpenNI context, int userColor) {
        this.userId = userId;
        this.context = context;
        this.userColor = userColor;
        this.body = new Body(p, this, userColor);
    }
    
    public AdapterSimpleOpenNI getContext() {
        return context;
    }
    
    public int getUserId() {
        return userId;
    }
    
    public Body getBody() {
        return body;
    }
    
}
