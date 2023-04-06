import java.util.HashMap;

import javax.print.attribute.HashPrintJobAttributeSet;

/**
 * An account for a food pantry.
 * @author Alexa Gonzalez, Julius A. Leone
 * @version 4.6.2023
 */
public class FoodPantry extends User{
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
     */
    public boolean inInventory(String itemName){
        for (Item item : inventory.keySet()) {
            if(item.getItemName().equals(itemName) && inventory.get(item) != 0){
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
     */
    public void addToInventory(String itemType, String itemName, int quantity){
        Item itemToAdd = new Item(itemType, itemName);

        inventory.putIfAbsent(itemToAdd, quantity);
    }

    public void removeFromInventory(String itemType, String itemName){
        Item itemToRemove = new Item(itemType, itemName);
        if(inventory.remove(itemToRemove) == null){
            //TO DO tell user nothing was removed
        }
        
    }

    public void setItemQuantity(String itemType, String itemName, int quantity){
        Item itemToChange = new Item(itemType, itemName);
        if(inventory.get(itemToChange) == null){
            //TODO tell user no such item exists.
        }
        else{
            inventory.put(itemToChange, quantity);
        }
    }







}
