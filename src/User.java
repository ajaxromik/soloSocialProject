/**
 * A class representing a user account of the application.
 * @author Julius A. Leone
 * @version 4.6.2023
 */
abstract public class User{

    private String username;
    private String password;
    private double longitude;
    private double latitude;

    public User(String username, String password, double longitude, double latitude){
        this.username = username;
        this.password = password;
        this.longitude = longitude;
        this.latitude = latitude;
    }


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

    public double getlongitude() {
        return this.longitude;
    }

    public void setlongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getlatitude() {
        return this.latitude;
    }

    public void setlatitude(double latitude) {
        this.latitude = latitude;
    }


    /**
     * Checks an entered username and password
     * @param attemptedUsername Username being entered.
     * @param attempetedPassword Password being entered.
     * @return
     */
    public boolean checkLoginInfo(String attemptedUsername, String attempetedPassword){
        return (username.equals(attemptedUsername) && password.equals(attempetedPassword));
    }


    /**
     * Gives a users distance from a location.
     * @param latitude The longitude of the location.
     * @param longitude The latitude of the location.
     * @return
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