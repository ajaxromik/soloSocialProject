import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.print.attribute.HashPrintJobAttributeSet; //TODO why is this here?

/**
 * An account for a food pantry.
 * @author Alexa Gonzalez, Julius A. Leone
 * @version 4.6.2023
 */
public class FoodPantry extends User{
    private static final Logger logger = Logger.getLogger("FoodPantry");

    private HashMap<Item, Integer> inventory = new HashMap<Item, Integer>();
    private String name;

    /**
     * creates a food pantry
     * @param username the username for the food pantry account.
     * @param password the password for the food pantry account
     * @param longitude the longitude of the food pantry.
     * @param latitude the latitude of the food pantry.
     * @param name the name of the food pantry.
     * @author Julius A. Leone
     */
    public FoodPantry(String username, String password, double longitude, double latitude, String name){
        super(username, password, longitude, latitude);
        this.name = name;
    }


    /**
     * checks if an item is in the inventory and they have at least one of it based off the name.
     * @param itemName the name item.
     * @return true if the inventory has an item false if not.
     * @author Julius A. Leone
     */
    public boolean inInventory(String itemName){
        for (Item item : inventory.keySet()) {
            if(item.getItemName().equalsIgnoreCase(itemName) && inventory.get(item) != 0){
                //if the item is in the inventory and has stock.
                return true;
            }
        }
        //if the loop successfully completes then the item was not found.
        return false;
    }

    /**
     * checks if an item is in the inventory and they have at least one of it based off the type.
     * @param itemType the type of item.
     * @return true if the inventory has an item false if not.
     * @author Julius A. Leone
     */
    public boolean inInventory(ItemType itemType){
        for (Item item : inventory.keySet()) {
            if(item.getItemType().equals(itemType) && inventory.get(item) != 0){
                //if the item is in the inventory and has stock.
                return true;
            }
        }
        //if the loop successfully completes then the item was not found.
        return false;
    }

    /**
     * adds an item to the inventory. If the inventory already contains the item the quantity goes up by one.
     * @param itemType the type of the item.
     * @param itemName the name of the item.
     * @param quantity the amount of the item.
     * @author Julius A. Leone
     */
    public void addToInventory(String itemType, String itemName, int quantity){
        Item itemToAdd = new Item(itemType, itemName);

        inventory.putIfAbsent(itemToAdd, quantity);
    }

    /**
     * removes an item from the inventory.
     * @param itemType the type of the item being removed.
     * @param itemName the name of the item being removed.
     * @autho Julius A. Leone
     */
    public void removeFromInventory(Item itemToRemove){
        if(inventory.remove(itemToRemove) == null){
            logger.log(Level.WARNING, "Item not in inventory.");
        }
        
    }

    /**
     * Sets the quantity of an item.
     * @param itemType the type of the item
     * @param itemName the name of the item
     * @param quantity the new desired quanity of the item
     * @author Julius A. Leone
     */
    public void setItemQuantity(Item itemToChange, int quantity){
        if(inventory.get(itemToChange) == null){
            logger.log(Level.WARNING, "Item not in inventory.");
        }
        else{
            inventory.put(itemToChange, quantity);
        }
    }

    /**
     * gets the details of the food pantry.
     * @return the details of the food pantry.
     * @author Julius A. Leone
     */
    public String getDetails(){
        return "";
    }

    /**
     * Returns the FoodPantry ButtonPermissions
     * 
     * @author William Carr
     * @return The ArrayList of ButtonPermission enums
     */
    public Set<ButtonPermission> getButtonPermissions() {
        HashSet<ButtonPermission> perms = new HashSet<>();
        perms.add(ButtonPermission.EDIT);
        // perms.add(ButtonPermission.DONATE); //TODO remove this, testing
        return perms;
    }





}
