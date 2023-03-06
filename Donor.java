/**
 * 
 */
public class Donor {
    private ProductType donation;

    /**
     * Constructor for the Donor class
     * @param donation  
     * @author Alexa Gonzalez 
     */
    public Donor(ProductType donation) {
        this.donation = donation;
    }
    
    /**
     * 
     * @return
     * @author Alexa Gonzalez
     */
    public ProductType getDonation() {
        return donation;
    }

    /**
     *  
     * @param donation
     * @author Alexa Gonzalez
     */
    public void setDonation(ProductType donation) {
        this.donation = donation;
    }

    /**
     * 
     * @param provider
     * @author
     */
    public void donate(Provider provider){

    }
   

}
