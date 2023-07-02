import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import java.util.logging.Level;

/**
 * An account for a food pantry.
 * @author Julius A. Leone
 * @version 4.6.2023
 */
public class FoodPantry extends User implements Provider{
    private static final long serialVersionUID = -43412345566321L;
    private static final Logger logger = Logger.getLogger("FoodPantry");

    private HashMap<Item, Integer> inventory = new HashMap<Item, Integer>();
    private String name;
    private String details;


    /**
     * creates a food pantry
     * @param username the username for the food pantry account.
     * @param password the password for the food pantry account
     * @param longitude the longitude of the food pantry.
     * @param latitude the latitude of the food pantry.
     * @param name the name of the food pantry.
     * @author Julius A. Leone
     * @param details details of the pantry
     */
    public FoodPantry(String username, String password, double latitude , double longitude, String name, String details){
        super(username, password, longitude, latitude);
        this.name = name;
        this.details = details;
    }


    public HashMap<Item,Integer> getInventory() {
        return this.inventory;
    }

    public void setInventory(HashMap<Item,Integer> inventory) {
        this.inventory = inventory;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails(){
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    /**
     * Returns the longitude
     * 
     * @return The longitude
     * @author William Carr
     */
    public double getLongitude() {
        return super.getLongitude();
    }

    /**
     * Returns the latitude
     * 
     * @return The latitude
     * @author William Carr
     */
    public double getLatitude() {
        return super.getLatitude();
    }

    /**
     * FoodPantry toString method.
     * 
     * @author William Carr
     * @return String showing the instance vars
     */
    @Override
    public String toString() {
        return super.toString() +
               "\ninventory: "+inventory+
               "\nname: "+name+
               "\ndetails: "+details;
    }
    
    /**
     * Uses the TextFields from the userFields Array and updates the values of the instance variables.
     * @param userFields The ArrayList from the getUserFields method. Index 1 should be longitude and index 2 should be latitude.
     */
    public void updateUserFields(ArrayList<Node> userFields) { // TODO testing; add the changes to database after this
        super.updateUserFields(userFields);
        setName(((TextField)userFields.get(3)).getText());
        setDetails(((TextField)userFields.get(4)).getText());
        UserBase.serializeUsers();
    }

    /** //TODO needs a function that takes the ArrayList and updates the user fields
     * Returns a list of Nodes. The first node is a GridPane and should be added to the EditUserFrame.
     * The second and following Nodes are the TextFields that need to be used by the edit user frame.
     * @return An ArrayList of Nodes that always has a GridPane as its first item, and will always have at least two Nodes more than that. (long & lat)
     */
    public ArrayList<Node> getUserFields() { //TODO need to add this method to subclasses

        ArrayList<Node> userFields = super.getUserFields();
        GridPane inputArea = (GridPane)userFields.get(0);
        
        // additional details
        Label nameLabel = new Label("Name:");
        inputArea.add(nameLabel, 0, 2);

        TextField nameField = new TextField();
        nameField.setText(""+this.name);
        inputArea.add(nameField, 1, 2);

        Label detailsLabel = new Label("Details:");
        inputArea.add(detailsLabel, 0, 3);

        TextField detailsField = new TextField(); //TODO make this into a bigger box
        detailsField.setText(""+this.details);
        inputArea.add(detailsField, 1, 3);

        // inventory section
        VBox inventoryBox = new VBox(15.0);
        inventoryBox.setPadding(new Insets(10));
        inventoryBox.setMaxWidth(240);
        inventoryBox.setPrefHeight(150);
        inventory.forEach((item, integer) -> { //each item of inventory //TODO make the inventory editable
            BorderPane itemPane = new BorderPane();
            itemPane.setMinWidth(240);

            Label itemName = new Label(item.getItemName());
            itemPane.setLeft(itemName);

            TextField countField = new TextField(integer.toString());
            countField.setPrefWidth(55);
            itemPane.setRight(countField); // TODO make the integer editable later
            
            BorderPane.setAlignment(itemName, Pos.CENTER_LEFT);//vertically centering the two
            BorderPane.setAlignment(countField, Pos.CENTER_RIGHT);

            inventoryBox.getChildren().add(itemPane);
        }); //TODO to finish the inventory, make a text thing to add an item and an add button
        
        // making it a part of the grid
        ScrollPane inventoryPane = new ScrollPane(inventoryBox);
        inventoryPane.setHbarPolicy(ScrollBarPolicy.NEVER);
        inventoryPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        inventoryPane.setPannable(true);
        inputArea.add(inventoryPane, 0, 4, 2, 1);
        
        userFields.add(nameField);
        userFields.add(detailsField);

        return userFields;
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

}