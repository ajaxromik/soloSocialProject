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

    public Donation(LocalDate date, Provider receivingProvider, ItemType itemType, String itemName, int quantityOfItems){
        this.date = date;
        this.receivingProvider = receivingProvider;
        this.itemType = itemType;
        this.itemName = itemName;
        this.quantityOfItems = quantityOfItems;
    }


    public LocalDate getDate() {
        return this.date;
    }

    public ItemType getItemType() {
        return this.itemType;
    }

    public String getItemName() {
        return this.itemName;
    }


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
     */
    public String toString() {
        return "\ndate: "+date+
               "\nitem: "+itemName+
               "\nquantity: "+quantityOfItems+
               "\nreceiver: "+receivingProvider.getUsername()+"\n";
    }

    @Override
    public int compareTo(Donation donation) {
        return date.compareTo(donation.getDate());
    }



}