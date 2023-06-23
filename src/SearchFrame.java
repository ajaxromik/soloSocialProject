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
        System.out.println(UserBase.providers); //TODO remove this in end product; makes it easier to see what should display
        launch(args);
    }

    /**
     * Makes it possible to test this class.
     * Keep in mind this is purely for UI appearance testing and it has no functionality.
     * 
     * @param mainStage The stage to add the home page onto.
     */
    @Override
    public void start(Stage mainStage) {
        buildSearchPage(mainStage);
    }

    // ----- end of testing methods -----

    private static Map<String,BorderPane> providerPanes;
    static {
        updateProviderPanes();
    }

    /**
     * Creating the provider panes must be done in a method in case it needs to be updated later on.
     */
    public static void updateProviderPanes() {
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

                                Label header = new Label(String.format("%s (%.6f, %.6f)",provider.getName(),provider.getLongitude(),provider.getLatitude()));
                                Button seeMoreButton = new Button("See More"); // figure out how to make this link to a new provider frame for each provider
                                Text details = new Text(provider.getDetails()+"\n"
                                +(provider.getInventory().isEmpty() ? "" : provider.getInventory().keySet()));// details and inventory, if it's not empty

                                providerPane.setLeft(header);
                                providerPane.setRight(seeMoreButton);
                                providerPane.setBottom(details);
                                return providerPane; //return a custom pane for each one
                            }
                          ));
    }

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
     * Creates a new Search page, so that the old search filters and keywords are reset.
     * 
     * @param mainStage The stage to put the SearchFrame into
     */
    public static void buildSearchPage(Stage mainStage) {

        // search results
        VBox resultsBox = new VBox();
        resultsBox.setMaxWidth(970);

        //TODO this is testing, adding all the providers; make it only search results; use .clear() to empty the list
        resultsBox.getChildren().addAll(providerPanes.values());
        
        ScrollPane searchResults = new ScrollPane(resultsBox); //TODO figure out how to display a little blurb for every provider here
        searchResults.setHbarPolicy(ScrollBarPolicy.NEVER);
        searchResults.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        searchResults.setPannable(true);
        searchResults.setMinWidth(970);

        // search keyword
        Label searchLabel = new Label("Search:");
        TextField keywordBox = new TextField(); //TODO add the searching eventlistener here

        //filter selection
        Label filterLabel = new Label("Search by:");
        ComboBox<String> filterBox = new ComboBox<String>(); // TODO add filtering eventlistener here
		filterBox.setPromptText("Filters");
		filterBox.getItems().addAll("Name", "Not in Name", "Inventory", "Not in Inventory");

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
		mainStage.setScene(searchScene);
		mainStage.show();
    }

}
