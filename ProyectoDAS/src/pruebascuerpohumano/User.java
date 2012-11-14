package pruebascuerpohumano;

import SimpleOpenNI.*;
import processing.core.*;

/*
 *
 * Esta clase representa a un usuario en el mundo virtual
 */
public class User {
    private SimpleOpenNI _context;
    private int _userId;
    private Body _body;
    private int _userColor;

    public User(PApplet p, int userId, SimpleOpenNI context, int userColor) {
        _userId = userId;
        _context = context;
        _body = new Body(p, this, userColor);
    }
    
    public SimpleOpenNI getContext() {
        return _context;
    }
    
    public int getUserId() {
        return _userId;
    }
    
    public Body getBody() {
        return _body;
    }
    
}
