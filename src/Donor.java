import java.util.ArrayList;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A user who donates to providers.
 * @author Julius Leone
 * @version 4.19.2023
 */
public class Donor extends User{
    private ArrayList<Donation> donations = new ArrayList<Donation>();

    public Donor(String username, String password, double longitude, double latitude){
        super(username, password, longitude, latitude);
    }


    public ArrayList<Donation> getDonations() {
        return this.donations;
    }
    
    public void addDonation(LocalDate date, ItemType itemType, long donationAmount, int quantityOfItems){
        donations.add(new Donation(date, itemType, donationAmount, quantityOfItems));
    }

    public void deleteDonation(Donation donation){
        donations.remove(donation);
    }
    
    /**
     * Returns the FoodPantry ButtonPermissions
     * 
     * @author William Carr
     * @return The ArrayList of ButtonPermission enums
     */
    public Set<ButtonPermission> getButtonPermissions() {
        HashSet<ButtonPermission> perms = new HashSet<>();
        perms.add(ButtonPermission.EDIT);
        // perms.add(ButtonPermission.DONATE); //TODO remove this, testing
        return perms;
    }

}
