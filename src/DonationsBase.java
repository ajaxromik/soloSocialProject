import java.io.*;
import java.util.ArrayList;

/**
 * Handles storing the most recent 10 donations into a database.
 * 
 * @author William Carr
 * @version 7.14.2023
 */
public class DonationsBase {

    private static ArrayList<Donation> recentDonations = new ArrayList<Donation>();

    static {
        try { // retrieves the serialized objects if it can
            FileInputStream fileStream = new FileInputStream("ser/recentDonations.ser");
            ObjectInputStream objectStream = new ObjectInputStream(fileStream);
            recentDonations = (ArrayList<Donation>) objectStream.readObject();
            objectStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the recent donations in the class's object.
     * @return The recent donations.
     */
    public static ArrayList<Donation> getRecentDonations() {
        return recentDonations;
    }

    /**
     * Sets the recent donations to a new ArrayList.
     * MUST NOT have more than 10 objects and MUST NOT be null.
     * @param recentDonations The new list that is not null and has a size <= 10.
     */
    public static void setRecentDonations(ArrayList<Donation> recentDonations) {
        DonationsBase.recentDonations = recentDonations;
    }

    /**
     * Serializes the recentDonations.
     */
    public static void serializeRecentDonations() {
        try {
            FileOutputStream fileStreamOut = new FileOutputStream("ser/recentDonations.ser");
            ObjectOutputStream objectStreamOut = new ObjectOutputStream(fileStreamOut);
            objectStreamOut.writeObject(recentDonations);
            objectStreamOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
