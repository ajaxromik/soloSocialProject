import java.time.LocalDate;
/**
 * Donations to providers from users.
 * @author Julius A. Leone
 * @version 4.19.2023
 */
public class Donation implements Comparable<Donation>{
    private LocalDate date;
    private ItemType itemType;
    private long donationAmount;
    private int quantityOfItems;

    public Donation(LocalDate date, ItemType itemType, long donationAmount, int quantityOfItems){
        this.date = date;
        this.itemType = itemType;
        this.donationAmount = donationAmount;
        this.quantityOfItems = quantityOfItems;
    }


    public LocalDate getDate() {
        return this.date;
    }

    public ItemType getItemType() {
        return this.itemType;
    }

    public long getDonationAmount() {
        return this.donationAmount;
    }


    public int getQuantityOfItems() {
        return this.quantityOfItems;
    }

    @Override
    public int compareTo(Donation donation) {
        return date.compareTo(donation.getDate());
    }



}