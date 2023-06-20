import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
     * Keep in mind this is purely for UI appearance testing and it has no functionality.
     * 
     * @param mainStage The stage to add the home page onto.
     */
    @Override
    public void start(Stage mainStage) {
        buildSearchPage(mainStage);
    }

    // ----- end of testing methods -----

    /**
     * Creates a new Search page, so that the old search filters and keywords are reset.
     * 
     * @param mainStage The stage to put the SearchFrame into
     */
    public static void buildSearchPage(Stage mainStage) { //TODO add a back button

        // search keyword
        Label searchLabel = new Label("Search");
        TextField keywordBox = new TextField();

        //filter selection
        Label filterLabel = new Label("Search by:");
        ComboBox<String> filterBox = new ComboBox<String>();
		filterBox.setPromptText("Filters");
		filterBox.getItems().addAll("Name", "Not in Name", "Inventory", "Not in Inventory");

        // search and filter area
        HBox inputArea = new HBox(15);
        inputArea.setPadding(new Insets(0,0,25,0));
        inputArea.getChildren().addAll(searchLabel,keywordBox,filterLabel,filterBox);

        // search results
        ScrollPane searchResults = new ScrollPane(); //TODO figure out how to display a little blurb for every provider here
        

        // stage scene change
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(15));
        borderPane.setTop(inputArea);

        Scene searchScene = new Scene(borderPane, 1000, 600);
		mainStage.setScene(searchScene);
		mainStage.show();
    }

}
