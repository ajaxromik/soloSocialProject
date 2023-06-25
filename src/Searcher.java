import java.util.function.BiPredicate;
import java.util.List;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * A searching class for searching a list of providers given a criteria.
 * 
 * @author Julius A.Leone, William Carr
 * @version 4.16.2023
 */
public class Searcher {

    private static HashMap<String, Searcher> defaultFilters = new HashMap<String,Searcher>(); // holds the default filters

    static {
        //filter for name that contains a keyword
        defaultFilters.put("contains",new Searcher((provider, string) -> provider.getName().toLowerCase().contains(string.toLowerCase())));
        //filter for name that does not have a keyword
        defaultFilters.put("without",new Searcher((provider, string) -> !provider.getName().toLowerCase().contains(string.toLowerCase())));
        //if inventory has an item that contains keyword
        defaultFilters.put("has", new Searcher(
            (provider, string) -> (provider.getInventory().isEmpty() && string.equals("")) || //if search bar is empty and so is the inventory, don't change anything
            provider.getInventory()
                    .keySet()
                    .stream()
                    .anyMatch(item -> item.getItemName().toLowerCase().contains(string.toLowerCase()))
        ));
        //if inventory doesn't have an item that contains keyword
        defaultFilters.put("lacks", new Searcher(
            (provider, string) -> provider.getInventory()
                                          .keySet()
                                          .stream()
                                          .anyMatch(item -> !(item.getItemName().toLowerCase().contains(string.toLowerCase())))
        ));
    }

    private BiPredicate<Provider, String> filter;

    /**
     * Creates a Searcher 
     * @param filter the filter used for searching the provider collection.
     * @author Julius A. Leone
     */
    public Searcher(BiPredicate<Provider, String> filter){
        this.filter = filter;
    }

    /**
     * Grabs the current search filter
     * @return the searcher's filter
     */
    public BiPredicate<Provider,String> getFilter() {
        return this.filter;
    }

    /**
     * Changes the current search filter
     * @param filter the searcher's filter
     */
    public void setFilter(BiPredicate<Provider,String> filter) {
        this.filter = filter;
    }

    /**
     * Returns a default Searcher
     * @param filter The name of the default Searcher(i.e. "contains"). Must use the right name or returns null.
     * @return A default method of searching
     * @author William Carr
     */
    public static Searcher getDefault(String filter) {
        return defaultFilters.get(filter);
    }

    /**
     * Searches a list of providers and returns a list only including providers that match search parameters.
     * @param providers a list containing providers
     * @param searchString the string we are looking for
     * @return The list of providers that fit the Searcher's filter
     */
    public List<Provider> searchProviders(List<Provider> providers, String searchString){
        return providers.parallelStream()
            .filter(provider -> filter.test(provider, searchString))
            .collect(Collectors.toList());
    }
}
