import java.util.function.BiPredicate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A searching class for searching a list of providers given a criteria.
 * 
 * @author Julius A.Leone
 * @version 4.16.2023
 */
public class Searcher {
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
     * Searches a list of providers and returns a list only including providers that match search parameters.
     * @param providers a list containing providers
     * @param searchString the string we are looking for
     * @return
     */
    public List<Provider> searchProviders(List<Provider> providers, String searchString){
        return providers.parallelStream()
            .filter(provider -> filter.test(provider, searchString))
            .collect(Collectors.toList());
    }
}
