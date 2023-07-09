import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.Node;

/**
 * A class representing a user account of the application.
 * @author Julius A. Leone, William Carr
 * @version 4.6.2023
 */
abstract public class User implements Serializable{

    
    private static final long serialVersionUID = -43412345566322L;

    // since there is no reason for a set, keep this public just like Integer.MAX_VALUE
    public static final double MAX_LATITUDE = 90.0;
    public static final double MIN_LATITUDE = -90.0;
    public static final double MAX_LONGITUDE = 180.0;
    public static final double MIN_LONGITUDE = -180.0;

    /**
     * Checks two doubles to see if they are within the ranges allowed for latitude and longitude.
     * @param latitude The latitude to check
     * @param longitude The longitude to check
     * @return Whether the latitude or longitude are valid.
     */
    public static boolean checkLatLon(double latitude, double longitude) {
        if(latitude >= MAX_LATITUDE ||
           latitude <= MIN_LATITUDE ||
           longitude >= MAX_LONGITUDE ||
           longitude <= MIN_LONGITUDE)
            return false;
        else
            return true;
    }

    private String username;
    private String password;
    private double longitude;
    private double latitude;

    /**
     * Sets up standard user attributes
     * @param username the login username for the user
     * @param password the login password for the user
     * @param longitude the longitudinal location of the user
     * @param latitude the laitudinal location of the user
     * @author Julius A. Leone
     */
    public User(String username, String password, double longitude, double latitude){
        this.username = username;
        this.password = password;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    // auto generated getters and setters.
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Returns whether a user can donate. By default returns false, but may be overrode by subclasses.
     * @return Whether the user is able to donate
     */
    public boolean canDonate() {
        return false;
    }
    
    /**
     * User toString method.
     * 
     * @author William Carr
     * @return String showing the data
     */
    @Override
    public String toString() {
        return "\nusername: "+username+
               "\npassword: "+password+
               "\nlongitude: "+longitude+
               "\nlatitude: "+latitude+"\n";
    }

    /**
     * Uses the TextFields from the userFields Array and updates the values of the instance variables.
     * @param userFields The ArrayList from the getUserFields method. Index 1 should be longitude and index 2 should be latitude.
     */
    public void updateUserFields(ArrayList<Node> userFields) { // TODO testing; add the changes to database after this
        setLongitude(Double.parseDouble(((TextField)userFields.get(1)).getText()));
        setLatitude(Double.parseDouble(((TextField)userFields.get(2)).getText()));
        UserBase.serializeUsers();
    }

    /** //TODO needs a function that takes the ArrayList and updates the user fields
     * Returns a list of Nodes. The first node is a GridPane and should be added to the EditUserFrame.
     * The second and following Nodes are the TextFields that need to be used by the edit user frame.
     * @return An ArrayList of Nodes that always has a GridPane as its first item, and will always have at least two Nodes more than that. (long & lat)
     */
    public ArrayList<Node> getUserFields() { //TODO need to add this method to subclasses

        GridPane inputArea = new GridPane();
        inputArea.setAlignment(Pos.CENTER);
        inputArea.setHgap(10);
        inputArea.setVgap(10);
        inputArea.setPadding(new Insets(25));
        
        // basic user details //TODO consider adding password changing
        Label longitudeLabel = new Label("Longitude:");
        inputArea.add(longitudeLabel, 0, 0);

        TextField longitudeField = new TextField(); //TODO make the create user frame lambda for changing the long & lat into a static var to use here too
        longitudeField.setText(""+this.longitude);
        longitudeField.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches("^[-]?[0-9]+\\.?[0-9]*$")) 
                longitudeField.setText(newValue.replaceAll("[^\\d.]|[.](?=.*[.]+)",""));
        });
        inputArea.add(longitudeField, 1, 0);

        Label latitudeLabel = new Label("Latitude:");
        inputArea.add(latitudeLabel, 0, 1);

        TextField latitudeField = new TextField();
        latitudeField.setText(""+this.latitude);
        latitudeField.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches("^[-]?[0-9]+\\.?[0-9]*$")) 
                latitudeField.setText(newValue.replaceAll("[^\\d.]|[.](?=.*[.]+)",""));
        });
        inputArea.add(latitudeField, 1, 1);
        
        ArrayList<Node> userFields = new ArrayList<Node>();
        userFields.add(inputArea);
        userFields.add(longitudeField);
        userFields.add(latitudeField);

        return userFields;
    }

    /**
     * Every user has specific permissions to buttons, which get get from here
     * 
     * @author William Carr
     * @return The set of ButtonPermission enums
     */
    public Set<ButtonPermission> getButtonPermissions(){
        HashSet<ButtonPermission> perms = new HashSet<>();
        perms.add(ButtonPermission.EDIT);
        return perms;
    }

    /**
     * Checks an entered username and password
     * @param attemptedUsername Username being entered.
     * @param attempetedPassword Password being entered.
     * @return if the username and password match
     */
    public boolean checkLoginInfo(String attemptedUsername, String attempetedPassword){
        return (username.equals(attemptedUsername) && password.equals(attempetedPassword));
    }


    /**
     * Gives a users distance from a location.
     * @param latitude The longitude of the location.
     * @param longitude The latitude of the location.
     * @return the distance between the location of the user and location.
     * @author Julius. A Leone
     */
    public double distanceFrom(double latitude, double longitude) {
        double earthRadius = 3958.75; // miles (or 6371.0 kilometers)
        double latitudeDif = Math.toRadians(longitude-this.longitude);
        double longitudeDif = Math.toRadians(latitude-this.latitude);
        double sinlatitudeDif = Math.sin(latitudeDif / 2);
        double sinOfLongitude = Math.sin(longitudeDif / 2);
        double a = Math.pow(sinlatitudeDif, 2) + Math.pow(sinOfLongitude, 2)
                * Math.cos(Math.toRadians(longitude)) * Math.cos(Math.toRadians(latitude));
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double dist = earthRadius * c;
    
        return dist;
    }

    
}