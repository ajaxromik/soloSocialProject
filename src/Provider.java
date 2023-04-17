/**
 * Users that have inventories that can be searched through.
 * @author Julius A. Leone
 */
public interface Provider {
    abstract public boolean inInventory(ItemType itemType);
    abstract public void addToInventory(String itemType, String itemName, int quantity);
    abstract public void removeFromInventory(String itemType, String itemName);
    abstract public void setItemQuantity(String itemType, String itemName, int quantity);
    abstract public String getDetails();
}
