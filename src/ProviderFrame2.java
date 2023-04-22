import java.util.Set;
import java.util.HashSet;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * A class to set up the Home page of our application
 * 
 * @author  Alexa Gonzalez
 * @version 4.21.2023
 */
public class ProviderFrame2 extends Application { 

    // ----- for overarching project -----
    //private instance variables allow for easier access to important parts of the Scene
    private Scene providerScene;
    private VBox summaryContainer;
    private VBox listedHours;
    private Button signOutButton;

    private HBox permittedButtons;
    private Set<ButtonPermission> perms = new HashSet<>(); //by default user has no permissions
    private Button searchButton = new Button("Search");
    private Button donateButton = new Button("Donate");
    private Button editButton = new Button("Edit Location Info");

    private final int SUMMARY_WIDTH = 600;
    private final String BULLETPOINT = "\u2022";

    private static Provider provider = new FoodPantry("TEST", "1234", 4321421342314.3214, 1234432.23423, "jeffs donuts", "A GREAT DONUT SHOP");


    
    // ----- testing functions -----
   
    /**
     * Starts the program!
     * @author Alexa Gonzalez
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * @author Alexa Gonzalez
     * @param mainStage The stage to add the ProviderFrame onto.
     */
    @Override
    public void start(Stage mainStage) {
        buildProviderPage();
        // addPermittedButtons(null);
        setStage(mainStage);
    }
    
    /**
     * Sets the stage for the driver
     * 
     * @author Alexa Gonzalez
     * @param stage
     */
    private void setStage(Stage stage) {
        stage.setTitle("Provider - " + provider.getName());
		stage.setScene(providerScene);
		stage.show();
    }

    /**
     * 
     * 
     * @author Alexa Gonzalez
     */
    public ProviderFrame2(Stage stage) {
        buildProviderPage();
        setStage(stage);
    }
    
    /**
     * 
     *  
     * @author Alexa Gonzalez
     */
    public ProviderFrame2() {
        buildProviderPage();
    }

    public Scene getScene() {
        return providerScene;
    }

    public VBox getListedHours() {
        return listedHours;
    }

    public Button getSearchButton() {
        return searchButton;
    }

    public Button getDonateButton() {
        return donateButton;
    }

    public Button getEditButton() {
        return editButton;
    }

    // /**
    //  * Adds the permitted buttons to the Home screen
    //  * 
    //  * @author Alexa Gonzalez
    //  * @param perms The list of ButtonPermissions to use. May not contain any duplicates. Cannot be null.
    //  */
    // public void updatePermittedButtons(Set<ButtonPermission> perms) {
    //     //if permissions are different, make the buttons correct
    //     if(!this.perms.equals(perms)){
    //         this.perms = perms;
    //         List<Node> children = permittedButtons.getChildren();
    //         children.clear();
    //         //add the search button if they are logged in
    //         if(!perms.isEmpty())
    //             children.add(searchButton); // everyone gets to search
    //         for(ButtonPermission perm : perms){
    //             switch(perm){ //have to have the perms if you want the add or edit buttons
    //                 case EDIT:
    //                     children.add(editButton);
    //                     break;
    //                 case DONATE:
    //                     children.add(donateButton);
    //                     break;
    //             }
    //         }
    //     }
    // }

    /**
     * Creates a home page for the object. 
     * This method should not be called more than once per object, or it will reset every field to their starting values.
     * (it would get rid of any listeners)
     * 
     * @author Alexa Gonzalez
     */
    private void buildProviderPage() { //TODO split this into smaller helper methods
        
        
        Text welcomeHeader = new Text(provider.getName());
        welcomeHeader.setFont(new Font(22));

        BorderPane welcome = new BorderPane();
        welcome.setTop(welcomeHeader);

    //TODO sign-out button functionality 

        //creates the sign-out button section(top right)
        signOutButton = new Button("Sign Out");
        signOutButton.setFont(new Font(15));

        BorderPane loginContainer = new BorderPane();
        loginContainer.setRight(signOutButton);

        // creates the details of the indiviudal provider
        Text detailsContent = new Text("Details: " + provider.getDetails());
        detailsContent.setWrappingWidth(SUMMARY_WIDTH);

        //creates the buttons for below the summary
        permittedButtons = new HBox(20);

        summaryContainer = new VBox(10);
        // summaryContainer.setPadding(new Insets(10));
        summaryContainer.getChildren().addAll(detailsContent, permittedButtons);
        summaryContainer.setAlignment(Pos.TOP_LEFT);

        //creates the donations feed section(bottom right)
        Label hours = new Label("Hours");
        hours.setFont(new Font(17.5));

        listedHours = new VBox();
        listedHours.setMinSize(200, 200);

        BorderPane listedHoursContainer = new BorderPane();
        listedHoursContainer.setTop(hours);
        BorderPane.setAlignment(hours, Pos.TOP_CENTER);
        listedHoursContainer.setCenter(listedHours);

        //creates the main pane with its settings
        GridPane mainPane = new GridPane();
        mainPane.setPadding(new Insets(20));
        mainPane.setMinSize(400, 200);
        mainPane.setVgap(10);
        mainPane.setHgap(20);
        mainPane.setAlignment(Pos.CENTER);
        //mainPane.setGridLinesVisible(true); //TODO take this out when the application as a whole is finished entirely

        mainPane.add(welcome,0,0);
        mainPane.add(loginContainer, 1, 0);
        mainPane.add(summaryContainer, 0, 1);
        mainPane.add(listedHoursContainer, 1, 1);

        providerScene = new Scene(mainPane);
    }

}
