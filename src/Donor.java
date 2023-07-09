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
    
    public void addDonation(LocalDate date, Provider receivingProvider, ItemType itemType, String itemName, int quantityOfItems){
        donations.add(new Donation(date, receivingProvider, itemType, itemName, quantityOfItems));
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
     * User toString method plus the donations.
     */
    public String toString() {
        return super.toString() +
               "donations: "+donations+"\n";
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