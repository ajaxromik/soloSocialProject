import java.io.Serializable;
/**
 * An item with a type and name.
 * 
 * @author Julius A. Leone
 * @version 2023.4.6
 */
public class Item implements Serializable{
    private static final long serialVersionUID = -4990988421L;
    private ItemType itemType;
    private String itemName;

    /**
     * Constructs an Item
     * @param itemType The string equivalent of the ItemType
     * @param itemName The name of the item
     */
    public Item(String itemType, String itemName){
        this.itemType = ItemType.valueOf(itemType);
        this.itemName = itemName;
    }


    /**
     * Returns the ItemType
     * @return the ItemType
     */
    public ItemType getItemType() {
        return this.itemType;
    }

    /**
     * Returns the item's name
     * @return the item's name
     */
    public String getItemName() {
        return this.itemName;
    }

    /**
     * Returns the String version of the item
     * @return the String version of the item
     */
    @Override
    public String toString(){
        return "item name:" + itemName + "\nitem type:" + itemType;
    }
}
