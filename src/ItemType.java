import java.util.ArrayList;

/**
 * Class for the different types of products that we can have
 * @author Alexa Gonzalez
 */
public enum ItemType {
    FOOD,
    WATER,
    HYGIENE_PRODUCTS;

    /**
     * A list to return an app-usable list of the names of itemtypes.
     * Must be updated with the addition of new ItemTypes. Cannot return an empty or null list.
     * @return A list of all the ItemTypes
     * @author William Carr
     */
    public static ArrayList<String> getItemTypes() {
        ArrayList<String> types = new ArrayList<String>();
        types.add("Food");
        types.add("Water");
        types.add("Hygiene Products");
        return types;
    }
}
