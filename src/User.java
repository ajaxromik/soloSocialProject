import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A class representing a user account of the application.
 * @author Julius A. Leone, William Carr
 * @version 4.6.2023
 */
abstract public class User implements Serializable{

    
    private static final long serialVersionUID = -43412345566322L;

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
               "\nlatitude: "+latitude;
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