import java.util.HashMap;
/**
 * Users that have inventories that can be searched through.
 * @author Julius A. Leone
 */
public interface Provider {
    abstract public String getDetails();
    abstract public String getName();
    abstract public HashMap<Item, Integer> getInventory();
    abstract public void setName(String name);
    abstract public void setDetails(String details);
    abstract public boolean inInventory(ItemType itemType);
    abstract public void addToInventory(String itemType, String itemName, int quantity);
    abstract public void removeFromInventory(Item item);
    abstract public void setItemQuantity(Item item, int quantity);
    
}
