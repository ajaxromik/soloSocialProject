import java.util.ArrayList;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A user who donates to providers.
 * @author Julius Leone, William Carr
 * @version 7.19.2023
 */
public class Donor extends User{
    private static final long serialVersionUID = -12343125445334L;
    private ArrayList<Donation> donations = new ArrayList<Donation>();

    /**
     * Creates a donor
     * @param username username of the donor
     * @param password password of the donor
     * @param longitude longitude of the donor
     * @param latitude latitude of the donor
     */
    public Donor(String username, String password, double longitude, double latitude){
        super(username, password, longitude, latitude);
    }

    /**
     * Returns the donations
     * @return the donations
     */
    public ArrayList<Donation> getDonations() {
        return this.donations;
    }
    
    /**
     * Adds a donation using all the necessary parameters
     * @param date date of the donation
     * @param receivingProvider receivingProvider of the donation
     * @param itemType itemType of the donation
     * @param itemName itemName of the donation
     * @param quantityOfItems quantityOfItems of the donation
     */
    public void addDonation(LocalDate date, Provider receivingProvider, ItemType itemType, String itemName, int quantityOfItems){
        donations.add(new Donation(date, receivingProvider, itemType, itemName, quantityOfItems));
    }

    /**
     * Adds a pre-existing donation. MUST NOT BE NULL.
     * @param donation The donation to accredit to this donor.
     */
    public void addDonation(Donation donation) {
        donations.add(donation);
    }

    /**
     * Deletes a donation from the list for an unforeseen but possible future where this is needed.
     * @param donation The donation to remove
     */
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
        perms.add(ButtonPermission.VIEWDONATIONS);
        return perms;
    }

}