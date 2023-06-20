import java.util.ArrayList;

/**
 * Acts as a driver for tests on the program
 * 
 * @author William Carr
 * @version 4.24.23
 */
public class TestingDriver {
    
    public static void main(String[] args) {
        // UserBase.foodPantrys.put("anotheruser", new FoodPantry("anotheruser", "used", 0.0, 0.0, "Testing FoodPantry", "test"));
        
        // for(int i=0; i < 100; i++) System.out.println("\n");

        // UserBase.serializeFoodPantrys();
        
        Searcher defSearcher = new Searcher((provider, string) -> provider.getName().toLowerCase().contains(string.toLowerCase()));

        System.out.println(defSearcher.searchProviders(UserBase.providers, "e"));//TODO this is how we want to do it generally, but could store a second Searcher method of doing it where it doesn't contain the phrase
        // UserBase.providers.get(0).setName("Fusha");
        // UserBase.serializeFoodPantrys();
        // System.out.println(UserBase.providers);

        
    }

}
