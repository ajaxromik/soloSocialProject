import java.util.ArrayList;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * A class that handles a DonationFeed to produce a desired output.
 */
public class DonationFeedView {

    private DonationFeed donationFeed;
    private VBox donationsVBox;
    
    /**
     * Creates a view for the specified DonationFeed 
     * @param donationFeed The donation feed to provide a view for.
     */
    public DonationFeedView(DonationFeed donationFeed, VBox ) {
        this.donationFeed = donationFeed;
    }

    

    /**
     * Returns the donations VBox
     * @return The donations VBox
     */
    public VBox getDonationFeedVBox() {
        return donationsVBox;
    }

    /**
     * Resets the VBox's children based on the current feed contents.
     */
    private void updateVBox() {
        
    }

}
