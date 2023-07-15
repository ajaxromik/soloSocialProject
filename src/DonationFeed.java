import java.lang.reflect.Array;
import java.util.ArrayList;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * A class that handles controlling the donations feed of the app.
 * 
 * @author William Carr
 * @version 7.14.2023
 */
public class DonationFeed {
    
    private VBox donationsVBox;
    private static ArrayList<Donation> newDonations = new ArrayList<>(); //TODO this might need to be static for other things to interact with it?
    private ArrayList<Donation> displayedDonations = new ArrayList<>(); // this is the list used to both save and load up the last n donations

    /**
     * Creates an object of this class with a vbox.
     * @param donationsVBox The VBox that the donations will display on.
     */
    public DonationFeed(VBox donationsVBox) {
        this.donationsVBox = donationsVBox;
    }

    /**
     * Returns the donations VBox
     * @return The donations VBox
     */
    public VBox getDonationFeed() {
        return donationsVBox;
    }

    /**
     * Returns the donations that are visible
     * @return The donations that are visible
     */
    public ArrayList<Donation> getDisplayedDonations() {
        return displayedDonations;
    }

    /**
     * Returns the donations that will be added
     * @return The donations that will be added
     */
    public static ArrayList<Donation> getNewDonations() {
        return newDonations;
    }

    /**
     * Records the donation into the feed.
     * @param donation The donation to add when the thread gets to it.
     */
    public static void recordDonation(Donation donation) {
        newDonations.add(donation);
    }

    /**
     * Gets the text to use to display a donation.
     * @param donation The donation to convert to text.
     * @return The text version of the donation.
     */
    private static String getDonationText(Donation donation) {
        return String.format("%i %s(s) to %s", donation.getQuantityOfItems(), donation.getItemName(), donation.getProvider().getName());
    }

    /**
     * Resets the VBox view based on the current feed contents.
     */
    private void setupVBox() {
        Text donationsText = new Text();
        donationsVBox.getChildren().clear();
        donationsVBox.getChildren().add(donationsText);

        String foundation = "";
        for(int i = 0; i < displayedDonations.size(); i++) {
            foundation += getDonationText(displayedDonations.get(i));
            if(i != 0)
                foundation += "\n";
        }

        donationsText.setText(foundation);
    }

    /**
     * Updates the donations VBox feed based on the newly made donations. //TODO add a call to the saving functionality
     */
    public void updateFeedOnce() {
        while(newDonations.size() != 0) { //              while there are items in newDonations
            Donation d = newDonations.get(0);
            displayedDonations.add(0, d); //        add the new donation to the top of the list
            if(displayedDonations.size() > 10)
                displayedDonations.remove(10); //   clear off the end of the list if it's too big
        }

        // now that the list has been adjusted, setup the new VBox text
        setupVBox();
    }

    /**
     * Updates the donations VBox feed indefinitely.
     */
    private void updateFeedIndefinitely() {
        try {
            while(true) {
                updateFeedOnce();
                Thread.sleep(10000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Starts the running of the feed. Keep in mind the feed must be updated once on exit.
     */
    public void startFeed() {

        // TODO add task of loading the feed from a file here
        Runnable loadUpFeed;

        // starts the automatically updating feed
        Runnable updater = new Runnable () {
            public void run() {
                updateFeedIndefinitely();
            }
        };

        Thread backgroundUpdater = new Thread(updater);
        backgroundUpdater.setDaemon(true);
        backgroundUpdater.start(); // tells the application to start the updater

    }
}
