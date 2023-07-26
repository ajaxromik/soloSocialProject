import java.util.HashMap;
/**
 * Users that have inventories that can be searched through.
 * @author Julius A. Leone, William Carr
 */
public interface Provider {
    /**
     * Returns the username of the provider
     * @return the username
     */
    abstract public String getUsername();
    /**
     * Returns the details listed for the provider
     * @return the details
     */
    abstract public String getDetails();
    /**
     * Returns the name of the provider's location
     * @return the name
     */
    abstract public String getName();
    /**
     * Returns the inventory
     * @return the inventory
     */
    abstract public HashMap<Item, Integer> getInventory();
    /**
     * Sets the name
     * @param name The name of the provider
     */
    abstract public void setName(String name);
    /**
     * Sets the details of the provider
     * @param details the details listed
     */
    abstract public void setDetails(String details);
    /**
     * Checks if a provider has a certain item
     * @param itemName The name of the item
     * @return true if the item is in the inventory
     */
    abstract public boolean inInventory(String itemName);
    /**
     * Adds the item to the inventory
     * @param itemType ItemType of the item
     * @param itemName Name of the item
     * @param quantity number of items
     */
    abstract public void addToInventory(String itemType, String itemName, int quantity);
    /**
     * Removes an item from the inventory
     * @param item The item to remove
     */
    abstract public void removeFromInventory(Item item);
    /**
     * Sets the quantity of a certain item
     * @param item The item to change the quantity of
     * @param quantity The amount to set it to
     */
    abstract public void setItemQuantity(Item item, int quantity);
    /**
     * Returns the longitude
     * @return the longitude
     */
    abstract public double getLongitude();
    /**
     * Returns the latitude
     * @return the latitude
     */
    abstract public double getLatitude();

}
