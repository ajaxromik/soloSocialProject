import java.util.HashMap;

/**
 * Class for the different types of products that we can have
 * @author Alexa Gonzalez
 */
public enum ItemType {
    FOOD,
    WATER,
    HYGIENE_PRODUCTS;

    public static final String DEFAULT_ITEM_TYPE = "Food";

    /**
     * A map to return an app-usable list of the names of itemtypes.
     * Must be updated with the addition of new ItemTypes. Cannot return an empty or null list.
     * @return A map of all the ItemTypes
     * @author William Carr
     */
    public static HashMap<String, ItemType> getItemTypes() {
        HashMap<String, ItemType> types = new HashMap<String, ItemType>();
        types.put("Food", FOOD);
        types.put("Water", WATER);
        types.put("Hygiene Products", HYGIENE_PRODUCTS);
        return types;
    }
}
