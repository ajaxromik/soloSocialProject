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
    private static final long serialVersionUID = -12343125445334L;
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
     * Returns whether a user can donate. By default returns false, but may be overrode by subclasses.
     * @return This returns true for all donors.
     */
    public boolean canDonate() {
        return true;
    }
    
    /**
     * Returns the FoodPantry ButtonPermissions
     * 
     * @author William Carr
     * @return The ArrayList of ButtonPermission enums
     */
    public Set<ButtonPermission> getButtonPermissions() {
        HashSet<ButtonPermission> perms = new HashSet<>(super.getButtonPermissions());
        perms.add(ButtonPermission.DONATE);
        return perms;
    }

}