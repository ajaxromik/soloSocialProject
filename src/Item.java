/**
 * An item with a type and name.
 * 
 * @author Julius A. Leone
 * @version 2023.4.6
 */
public class Item {
    private ItemType itemType;
    private String itemName;

    public Item(String itemType, String itemName){
        this.itemType = ItemType.valueOf(itemType);
        this.itemName = itemName;
    }


    public ItemType getItemType() {
        return this.itemType;
    }

    public String getItemName() {
        return this.itemName;
    }

    @Override
    public String toString(){
        return "item name:" + itemName + "\nitem type:" + itemType;
    }
}
