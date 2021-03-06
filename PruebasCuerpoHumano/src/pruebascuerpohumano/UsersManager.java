package pruebascuerpohumano;

import SimpleOpenNI.SimpleOpenNI;
import java.util.HashMap;
import java.util.Map;
import processing.core.*;

/*
 * Esta clase maneja las llamadas a los eventos de usuario y al conjunto de usuarios del sistema
 */
public class UsersManager {

    private SimpleOpenNI _context;
    private Map<Integer, User> _users;
    private PApplet _parent;

    public UsersManager(PApplet p, SimpleOpenNI context) {
        _parent = p;
        _context = context;
        _users = new HashMap<Integer, User>();
    }

    // when a person ('user') enters the field of view
    public void onNewUser(int userId) {
        _parent.println("New User Detected - userId: " + userId);
        _parent.println("Start pose detection");

        // start pose detection
        _context.startPoseDetection("Psi", userId);
    }

    // when a person ('user') leaves the field of view 
    public void onLostUser(int userId) {
        _parent.println("User Lost - userId: " + userId);
        removeUser(userId);
    }

    public void onExitUser(int userId) {
        _parent.println("onExitUser - userId: " + userId);
    }

    public void onReEnterUser(int userId) {
        _parent.println("onReEnterUser - userId: " + userId);
    }

    // when a user begins a pose
    public void onStartPose(String pose, int userId) {
        _parent.println("Start of Pose Detected  - userId: " + userId + ", pose: " + pose);
        _parent.println("Stop pose detection");

        // stop pose detection
        _context.stopPoseDetection(userId);

        // start attempting to calibrate the skeleton
        _context.requestCalibrationSkeleton(userId, true);
    }

    public void onEndPose(String pose, int userId) {
        _parent.println("onEndPose - userId: " + userId + ", pose: " + pose);
    }

    // when calibration begins
    public void onStartCalibration(int userId) {
        _parent.println("Beginning Calibration - userId: " + userId);
    }

    // when calibaration ends - successfully or unsucessfully
    public void onEndCalibration(int userId, boolean successfull) {
        _parent.println("Calibration of userId: " + userId + ", successfull: " + successfull);

        if (successfull) {
            _parent.println("  User calibrated !!!");
            addUser(userId);
            _parent.println("  User added to usersManager !!!");
            _context.startTrackingSkeleton(userId);

        } else {
            _parent.println("  Failed to calibrate user !!!");
            _parent.println("  Start pose detection");
            _context.startPoseDetection("Psi", userId);
        }
    }

    public void addUser(int userId) {
        _users.put(userId, new User(_parent, userId, _context, _parent.color(0,255,0)));
    }

    public void removeUser(int userId) {
        _users.remove(userId);
    }

    public User getUser(int userId) {
        User user;
        if (_users.containsKey(userId)) {
            user = _users.get(userId);
        } else {
            user = null;
        }
        return user;
    }

    public Map<Integer, User> getUsers() {
        return _users;
    }
    
    public int getUsersSize(){
        return _users.size();
    }
    
}
