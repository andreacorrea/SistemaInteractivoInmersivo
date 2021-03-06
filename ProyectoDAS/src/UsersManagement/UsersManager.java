package UsersManagement;

import UsersManagement.VolumeGeneration.BuildingSkeletonVolumeStrategy;
import GeometricFiguresManagement.GeometricFigure;
import Interaction.Command;
import SistemaInteraccionInmersiva.Scene;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import processing.core.*;

/*
 * Esta clase maneja las llamadas a los eventos de usuario y al conjunto de usuarios del sistema
 */
public class UsersManager {

    private AdapterSimpleOpenNI context;
    private Map<Integer, User> users;
    private Scene scene;
    private PApplet parent;
    private static UsersManager instance = null;
    private Command collisionCommand;

    private UsersManager(PApplet p, AdapterSimpleOpenNI context) {
        this.parent = p;
        this.context = context;
        this.scene = Scene.getInstance(p);
        users = new HashMap<Integer, User>();
    }

    public static UsersManager getInstance(PApplet p, AdapterSimpleOpenNI context) {
        if (instance == null) {
            instance = new UsersManager(p, context);
        } else {
            instance.parent = p;
            instance.context = context;
            instance.scene = Scene.getInstance(p);
        }
        return instance;
    }

    // when a person ('user') enters the field of view
    public void onNewUser(int userId) {
        parent.println("New User Detected - userId: " + userId);
        parent.println("Start pose detection");

        // start pose detection
        context.startPoseDetection("Psi", userId);
    }

    // when a person ('user') leaves the field of view 
    public void onLostUser(int userId) {
        parent.println("User Lost - userId: " + userId);
        removeUser(userId);
    }

    public void onExitUser(int userId) {
        parent.println("onExitUser - userId: " + userId);
        removeUser(userId);
    }

    public void onReEnterUser(int userId) {
        parent.println("onReEnterUser - userId: " + userId);
        User user = new User(parent, userId, context, parent.color(0, 255, 0));
        users.put(userId, user);
        scene.addUser(user);
    }

    // when a user begins a pose
    public void onStartPose(String pose, int userId) {
        parent.println("Start of Pose Detected  - userId: " + userId + ", pose: " + pose);
        parent.println("Stop pose detection");

        // stop pose detection
        context.stopPoseDetection(userId);

        // start attempting to calibrate the skeleton
        context.requestCalibrationSkeleton(userId, true);
    }

    public void onEndPose(String pose, int userId) {
        parent.println("onEndPose - userId: " + userId + ", pose: " + pose);
    }

    // when calibration begins
    public void onStartCalibration(int userId) {
        parent.println("Beginning Calibration - userId: " + userId);
    }

    // when calibaration ends - successfully or unsucessfully
    public void onEndCalibration(int userId, boolean successfull) {
        parent.println("Calibration of userId: " + userId + ", successfull: " + successfull);

        if (successfull) {
            parent.println("  User calibrated !!!");
            addUser(userId);
            parent.println("  User added to usersManager !!!");
            context.startTrackingSkeleton(userId);

        } else {
            parent.println("  Failed to calibrate user !!!");
            parent.println("  Start pose detection");
            context.startPoseDetection("Psi", userId);
        }
    }

    public void addUser(int userId) {
        User user = new User(parent, userId, context, parent.color(0, 255, 0));
        users.put(userId, user);
        scene.addUser(user);
    }

    public void removeUser(int userId) {
        User userRemoved = users.remove(userId);
        scene.removeUser(userRemoved);
        userRemoved.getBody().getBodyMembers().clear();
    }

    public User getUser(int userId) {
        User user;
        if (users.containsKey(userId)) {
            user = users.get(userId);
        } else {
            user = null;
        }
        return user;
    }

    public Map<Integer, User> getUsers() {
        return users;
    }

    public int getUsersSize() {
        return users.size();
    }

    public void updateAndPaintUsers() {
        //parent.camera(parent.width / 2.0f, parent.height / 2.0f, 750, parent.width / 2.0f, parent.height / 2.0f, 0, 0, 1, 0);
        //_parent.scale(0.35f);
        User currentUser;

        // draw the skeleton of user1
        Iterator user = users.values().iterator();

        while (user.hasNext()) {
            currentUser = ((User) user.next());
            //currentUser.getBody().drawSkeletonLines();
            //currentUser.getBody().drawJoints();

            currentUser.getBody().update();
            currentUser.getBody().paintSkeletonMembers(collisionCommand);


            //currentUser.getBody().circleForAHead();
        }
    }

    public void addObserverGFUsers(GeometricFigure gf) {
        User currentUser;
        Iterator user = users.values().iterator();

        while (user.hasNext()) {
            currentUser = ((User) user.next());
            currentUser.getBody().addObserverGFBody(gf);
        }
    }

    public void removeObserverGFUsers(GeometricFigure gf) {
        User currentUser;
        Iterator user = users.values().iterator();

        while (user.hasNext()) {
            currentUser = ((User) user.next());
            currentUser.getBody().removeObserverGFBody(gf);
        }
    }

    public void setCollisionCommand(Command collisioncommand) {
        this.collisionCommand = collisioncommand;
    }

    public void setbuildingSkeletonVolumeStrategy(BuildingSkeletonVolumeStrategy buildingSkeletonVolumeStrategy) {
        User currentUser;

        // draw the skeleton of user1
        Iterator user = users.values().iterator();

        while (user.hasNext()) {
            currentUser = ((User) user.next());
            scene.removeUser(currentUser);
            currentUser.getBody().setBuildingSkeletonVolumeStrategy(buildingSkeletonVolumeStrategy);
            scene.addUser(currentUser);
        }

    }
}
