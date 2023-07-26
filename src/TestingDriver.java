import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.Scanner;

/**
 * Acts as a driver for tests on the program
 * 
 * @author William Carr
 * @version 4.24.23
 */
public class TestingDriver {
    
    public static void main(String[] args) {

        // Scanner sc = new Scanner(System.in);

        // UserBase.foodPantrys.put("anotheruser", new FoodPantry("anotheruser", "used", 0.0, 0.0, "Testing FoodPantry", "test"));
        
        // for(int i=0; i < 100; i++) System.out.println("\n");

        // UserBase.serializeFoodPantrys();
        
        // Searcher defSearcher = new Searcher((provider, string) -> provider.getName().toLowerCase().contains(string.toLowerCase()));

        // System.out.println(defSearcher.searchProviders(UserBase.providers, "e"));
        // UserBase.providers.get(0).setName("Fusha");
        
        // UserBase.providers.get("soups").addToInventory("FOOD", "Soup Can", 500);
        // UserBase.serializeFoodPantrys();
        // HashMap<Item, Integer> inventory = UserBase.providers.get("theShop").getInventory();
        // Item milk = inventory.keySet().stream().filter(item -> item.getItemName().equalsIgnoreCase("milk")).findFirst().get();
        // System.out.println(inventory.get(milk));

        // FoodPantry soups = (FoodPantry)UserBase.users.get("soups");
        // ArrayList<Donation> donations = ((Donor)UserBase.users.get("donor")).getDonations();
        // Donation dono1 = donations.get(0);
        // Donation dono2 = donations.get(1);

        
        // System.out.println(dono1 == donations.get(0));
        // System.out.println(dono2 == donations.get(1));

        // System.out.printf("%d %s(s) to %s", 1, "hi", "hello");
        // String s = sc.nextLine();
        // Searcher.getDefault("contains")
        //         .searchProviders(new ArrayList<Provider>(UserBase.providers.values()), "so")
        //         .stream()
        //         .forEach(provider -> System.out.println(provider.getUsername()));

        System.out.println(UserBase.users);
        
    }

}
