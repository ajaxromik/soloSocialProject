import java.util.ArrayList;
import java.util.Scanner;

/**
 * Acts as a driver for tests on the program
 * 
 * @author William Carr
 * @version 4.24.23
 */
public class TestingDriver {
    
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // UserBase.foodPantrys.put("anotheruser", new FoodPantry("anotheruser", "used", 0.0, 0.0, "Testing FoodPantry", "test"));
        
        // for(int i=0; i < 100; i++) System.out.println("\n");

        // UserBase.serializeFoodPantrys();
        
        // Searcher defSearcher = new Searcher((provider, string) -> provider.getName().toLowerCase().contains(string.toLowerCase()));

        // System.out.println(defSearcher.searchProviders(UserBase.providers, "e"));
        // UserBase.providers.get(0).setName("Fusha");
        
        // UserBase.providers.get("soups").addToInventory("FOOD", "Soup Can", 500);
        // UserBase.serializeFoodPantrys();
        System.out.println(UserBase.users);

        // String s = sc.nextLine();
        // Searcher.getDefault("contains")
        //         .searchProviders(new ArrayList<Provider>(UserBase.providers.values()), "so")
        //         .stream()
        //         .forEach(provider -> System.out.println(provider.getUsername()));
        
    }

}
