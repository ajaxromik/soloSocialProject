import java.io.Serializable;
import java.time.LocalDate;
/**
 * Donations to providers from users.
 * @author Julius A. Leone
 * @version 4.19.2023
 */
public class Donation implements Comparable<Donation>, Serializable{
    
    private static final long serialVersionUID = -23759832054724L;

    private LocalDate date;
    private Provider receivingProvider;
    private ItemType itemType;
    private String itemName;
    private int quantityOfItems;

    /**
     * Creates a new donation and adds it to the record. 
     * @param date Date of the donation
     * @param receivingProvider The provider who receives the donation
     * @param itemType The type of item
     * @param itemName The name of the item
     * @param quantityOfItems The amount of items in this donation
     */
    public Donation(LocalDate date, Provider receivingProvider, ItemType itemType, String itemName, int quantityOfItems){
        this.date = date;
        this.receivingProvider = receivingProvider;
        this.itemType = itemType;
        this.itemName = itemName;
        this.quantityOfItems = quantityOfItems;
    }


    /**
     * Returns the date of the donation
     * @return the date of the donation
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Returns the item type
     * @return the item type
     */
    public ItemType getItemType() {
        return this.itemType;
    }

    /**
     * Returns the item name
     * @return the item name
     */
    public String getItemName() {
        return this.itemName;
    }

    /**
     * returns the quantity of items
     * @return the quantity of items
     */
    public int getQuantityOfItems() {
        return this.quantityOfItems;
    }

    /**
     * Returns the receiving provider.
     * @return The receiving provider.
     */
    public Provider getProvider() {
        return receivingProvider;
    }

    /**
     * Returns info about the donation
     * @return Info about the donation
     */
    public String toString() {
        return "\ndate: "+date+
               "\nitem: "+itemName+
               "\nquantity: "+quantityOfItems+
               "\nreceiver: "+receivingProvider.getUsername()+"\n";
    }

    /**
     * Compares one donation to another by order of date.
     */
    @Override
    public int compareTo(Donation donation) {
        return date.compareTo(donation.getDate());
    }



}