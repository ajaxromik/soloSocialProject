/**
 * 
 * @author
 */
public interface Provider {
    public void addInventory(String productName, ProductType productType);
    public void removeInventory(String productName, ProductType productType);

}
