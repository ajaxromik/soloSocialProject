import java.io.*;
import java.util.ArrayList;

/**
 * Handles the list of most recent donations.
 * 
 * @author William Carr
 * @version 7.14.2023
 */
public class DonationsBase {

    private static ArrayList<Donation> recentDonations;

    private static final int DONATION_LIMIT = 10;

    static {
        try { // retrieves the serialized objects if it can
            FileInputStream fileStream = new FileInputStream("ser/recentDonations.ser");
            ObjectInputStream objectStream = new ObjectInputStream(fileStream);
            recentDonations = (ArrayList<Donation>) objectStream.readObject();
            objectStream.close();
        } catch (Exception e) { // otherwise makes it into an empty list
            recentDonations = new ArrayList<Donation>();
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
     * Records the donation into the feed, but removes the last one if there is more than the limit.
     * @param donation The donation to add.
     */
    public static void recordDonation(Donation donation) {
        if(donation != null) {
            recentDonations.add(donation); // new donations are added
            
            if(recentDonations.size() > DONATION_LIMIT) 
                recentDonations.remove(DONATION_LIMIT); // remove the last one if the list just got bigger than the limit

            serializeRecentDonations(); // then save the list
        }
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
