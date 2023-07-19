import java.lang.reflect.Array;
import java.util.ArrayList;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * A class that handles controlling the donations feed of the app. 
 * There should not be more than one donation feed in existence else the recent donations will be split between them in some way.
 * 
 * @author William Carr
 * @version 7.14.2023
 */
public class DonationFeed {
    
    //list of most recent donations; should never exceed 10
    private static ArrayList<Donation> recentDonations = DonationsBase.getRecentDonations(); 

    private static final int DONATION_LIMIT = 10;

    /**
     * Returns the donations in the feed
     * @return The donations in the feed
     */
    public static ArrayList<Donation> getRecentDonations() {
        return recentDonations;
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

            DonationsBase.serializeRecentDonations(); // then save the list
        }
    }

}
