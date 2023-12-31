import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Displays the searched results by the User's filters
 * 
 * @author William Carr
 * @version 6.20.2023
 */
public class SearchFrame extends Application{

    /**
     * Tests SearchFrame
     * 
     * @param args The arguments used to start the program from the command line
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Makes it possible to test this class.
     * Keep in mind this is purely for UI appearance testing and it has limited functionality.
     * 
     * @param mainStage The stage to add the home page onto.
     */
    @Override
    public void start(Stage mainStage) {
        buildSearchPage(mainStage);
		mainStage.show();
    }

    // ----- end of testing methods -----

    private static Map<String,BorderPane> providerPanes;

    //held separately to inject the code to return to the rest of the program
    private static Button backButton = new Button("Back");

    /**
     * Returns the backButton
     * 
     * @author William Carr
     * @return backButton field
     */
    public static Button getBackButton() {
        return backButton;
    }

    /**
     * Creates a new Search page and switches the stage to it.
     * Resets search and filters, and is necessary to update the providers whenever they are changed.
     * 
     * @param mainStage The stage to put the SearchFrame into
     */
    public static void buildSearchPage(Stage mainStage) {

        // search results
        VBox resultsBox = new VBox();
        resultsBox.setMaxWidth(970);
        
        ScrollPane searchResults = new ScrollPane(resultsBox);
        searchResults.setHbarPolicy(ScrollBarPolicy.NEVER);
        searchResults.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        searchResults.setPannable(true);
        searchResults.setMaxWidth(970);
        searchResults.setMinWidth(970);

        // search keyword
        Label searchLabel = new Label("Search:");
        TextField keywordBox = new TextField();

        //filter selection
        Label filterLabel = new Label("Search by:");
        ComboBox<String> filterBox = new ComboBox<String>();
		filterBox.setValue("Filters");
		filterBox.getItems().addAll("Name", "Not in Name", "Inventory", "Not in Inventory");
        
        // actual searching
        keywordBox.setOnKeyReleased(e -> searchUsers(resultsBox, getFilter(filterBox.getValue()), keywordBox.getText()));
        filterBox.setOnAction(e -> searchUsers(resultsBox, getFilter(filterBox.getValue()), keywordBox.getText()));

        // search and filter area
        HBox inputArea = new HBox(15);
        inputArea.setPadding(new Insets(0,0,25,0));
        inputArea.getChildren().addAll(searchLabel,keywordBox,filterLabel,filterBox);

        // separates the back button from the search area
        BorderPane topArea = new BorderPane();
        topArea.setLeft(inputArea);
        topArea.setRight(backButton);

        // stage scene change
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(15));
        borderPane.setTop(topArea);
        borderPane.setLeft(searchResults);
        
        Scene searchScene = new Scene(borderPane, 1000, 600);
        updateProviderPanes(mainStage, searchScene); // fills out the providerPanes for the first time

        // by default, the results will show everything, since nothing is searched and the default search method is by name
        resultsBox.getChildren().addAll(providerPanes.values()); // below the scene creation because waiting for "updateProviderPanes"

		mainStage.setScene(searchScene);
    }

    /**
     * Gets the text that will display the inventory for the provider in SearchFrame. Used to keep code DRY.
     * 
     * @param provider The provider to base the inventory off of.
     * @return A string that displays the inventory.
     */
    private static String getProviderSearchInventory(Provider provider) {
        return provider.getDetails()+"\n"
               +(provider.getInventory().isEmpty() ? "" : provider.getInventory().keySet().stream().map(
                   item -> String.format("%s: %d", item.getItemName(),provider.getInventory().get(item))
               ).collect(Collectors.toList()));
    }

    /**
     * Creating the provider panes must be done in a method in case it needs to be updated later on.
     * NOTE: This function is for creating provider panes to be used later.
     * It is NOT made for updating the visiblity of provider panes; see searchUsers()
     * 
     * @param mainStage The main stage of the application. Is a necessary parameter for the "See More" buttons to work.
     * @param scene The scene that the ProviderDetailsFrame's back button returns to.
     */
    private static void updateProviderPanes(Stage mainStage, Scene scene) {
        providerPanes = 
        UserBase.providers.entrySet()
                          .stream() // stream of entries, a great way to give you a headache
                          .collect(Collectors.toMap(
                            Map.Entry::getKey, // use the key of the current mapping of name to provider
                            entry -> {
                                Provider provider = entry.getValue();
                                BorderPane providerPane = new BorderPane();
                                providerPane.setPadding(new Insets(15));
                                providerPane.setMaxSize(940,250);

                                Label header = new Label(String.format("%s (%.6f, %.6f)",provider.getName(),provider.getLatitude(),provider.getLongitude()));
                                Button seeMoreButton = new Button("See More");

                                Text details = new Text(getProviderSearchInventory(provider));// details and inventory, if it's not empty
                                details.setWrappingWidth(925);

                                //sets up the provider's frame and the button for it
                                ProviderDetailsFrame provFrame = new ProviderDetailsFrame(provider);
                                provFrame.updateFrame();
                                seeMoreButton.setOnAction(e -> mainStage.setScene(provFrame.getScene())); // see more goes to provider's frame
                                provFrame.getBackButton().setOnAction(e -> {
                                    details.setText(getProviderSearchInventory(provider));
                                    mainStage.setScene(scene);
                                }); // back button goes to the scene param

                                providerPane.setLeft(header);
                                providerPane.setRight(seeMoreButton);
                                providerPane.setBottom(details);
                                return providerPane; //return a custom pane for each one
                            }
                          ));
    }

    /**
     * Searches for the users and updates the VBox for it
     * @param destination The VBox to update
     * @param filter The name of the default filter, saved in Searcher, that we will use to search with
     * @param keyword The contents of the search bar, regardless of how many words it is
     */
    private static void searchUsers(VBox destination, String filter, String keyword){
        destination.getChildren().clear();
        Searcher.getDefault(filter)
                .searchProviders(new ArrayList<Provider>(UserBase.providers.values()), keyword)
                .stream()
                .forEach(provider -> destination.getChildren()
                                                .add(providerPanes.get(provider.getUsername())));
    }

    /**
     * Gets the right default Searcher filter based on the selected filter. 
     * Is case sensitive to the text of the filterBox.
     * 
     * @param displayed The string in the ComboBox
     * @return The string key of the default Searcher
     */
    private static String getFilter(String displayed) {
        if(displayed.equals("Not in Name")) return "without"; //for people looking for random options
        else if(displayed.equals("Inventory")) return "has";
        else if(displayed.equals("Not in Inventory")) return "lacks";// if people want to donate maybe
        else return "contains"; //regular search by company name
    }

}
