import java.util.stream.Collectors;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * A class that shows some details about a provider that the user would see after hitting "See More" in the SearchFrame.
 * Could possibly be used to also display a preview for Providers that are editing details. //TODO consider implementing this
 * 
 * @author William Carr
 * @version 6.25.2023
 */
public class ProviderDetailsFrame extends Application{
    
    //TODO remove this
    private final Provider TESTING = UserBase.providers.values().iterator().next();//gets one of the providers from userbase in a disgusting way

    /**
     * Tests the class
     * 
     * @param args Arguments if you so desire
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Allows for testing.
     * 
     * @param mainStage The stage to test the application on
     */
    @Override
    public void start(Stage mainStage){
        mainStage.setScene(providerScene);
        mainStage.show();
    }

    /**
     * Creates a frame for the provider; meant for testing
     */
    public ProviderDetailsFrame() {
        this.provider = TESTING;
        setupControls();
    }

    // ----- end of testing -----

    private static User loggedInUser;
    private Provider provider;
    private Scene providerScene;
    private Button backButton = new Button("Back");
    private GridPane donorVisiblePane = new GridPane();

    /**
     * Sets the logged in user
     * @param user The new logged in user
     */
    public static void setLoggedInUser(User user) {
        loggedInUser = user;
    }

    /**
     * Creates a frame for a provider. Must call setupControls when provider is changed.
     * @param provider The provider to base the frame on
     */
    public ProviderDetailsFrame(Provider provider) {
        this.provider = provider;
        setupControls();
    }

    /**
     * Returns the homeScene
     * 
     * @author William Carr
     * @return providerScene field
     */
    public Scene getScene() {
        return providerScene;
    }

    /**
     * Returns the back button to link it to other parts of the program.
     * @return the back button
     */
    public Button getBackButton() {
        return backButton;
    }

    /**
     * Creates the setup for the pane.
     * Does not need to update with changes to the provider, 
     * because a new instance of this class is created when being used.
     */
    public void setupControls() {

        // creates the header just like in the search frame
        Label header = new Label(String.format("%s (%.6f, %.6f)",provider.getName(),provider.getLatitude(),provider.getLongitude()));
        header.setStyle("-fx-font-size: 24px;");
        
        //inventory items + details
        Text description = new Text(
            "Inventory: "+
            (provider.getInventory().isEmpty() ? "Inventory items not listed" : // shows either the previous string of the items of the inventory
            provider.getInventory().keySet().stream().map(
                item -> String.format("%s: %d", item.getItemName(),provider.getInventory().get(item))
            ).collect(Collectors.toList()))+
            "\n\n"+
            provider.getDetails()
        );
        description.setWrappingWidth(600);

        // webview of the map, 
        // TODO unfortunately is hard to control without an api key, but I'm not sure how the billing works so i steered clear of it
        WebView mapView = new WebView();
        WebEngine mapEngine = mapView.getEngine();
        mapEngine.load(String.format("https://maps.google.com/?ll=%f,%f&z=5",provider.getLatitude(),provider.getLongitude()));//somewhere in mexico
        mapView.setMaxSize(450, 500);
        BorderPane borderedMap = new BorderPane(mapView);
        borderedMap.setStyle("-fx-border-color: dimgray; -fx-border-width: 2px; -fx-border-radius: 3px;");

        // Header and buttons area
        BorderPane topArea = new BorderPane();
        topArea.setPadding(new Insets(0, 0, 10, 0));

        //setting up the donation area
        ChoiceBox<String> itemTypes = new ChoiceBox<String>();
		itemTypes.getItems().addAll(ItemType.getItemTypes().keySet()); //calling the account type recipient feels weird
		itemTypes.setValue(ItemType.DEFAULT_ITEM_TYPE);
        itemTypes.setPrefWidth(160);

        TextField donateItemField = new TextField();
        donateItemField.setPromptText("Name of Item");

        TextField quantityField = new TextField();
        quantityField.setPromptText("Count");
        quantityField.setPrefWidth(60);
        quantityField.textProperty().addListener((observable, oldValue, newValue) -> {// keeps 6 digits
            if(!newValue.matches("^[\\d]{1,6}$"))
                quantityField.setText(newValue.replaceAll("[^\\d.]",""));
        });

        Button donateButton = new Button("Donate");
        donateButton.setOnAction(e -> {
            System.out.printf("I donated %d of %s that is type %s%n", quantityField.getText().isEmpty() ? 0 : Integer.parseInt(quantityField.getText()), donateItemField.getText(), ItemType.getItemTypes().get(itemTypes.getValue()).toString()); //TODO finish later
            if(quantityField.getText().isEmpty() || Integer.parseInt(quantityField.getText()) == 0) { // do nothing case
                System.out.println("Invalid");
            }
        });

        GridPane interactiveGrid = new GridPane();
        interactiveGrid.setHgap(5);

        donorVisiblePane.setHgap(5); //sets up the donor's gridpane
        donorVisiblePane.add(itemTypes, 0, 0);
        donorVisiblePane.add(donateItemField, 1, 0);
        donorVisiblePane.add(quantityField, 2, 0);
        donorVisiblePane.add(donateButton, 3, 0);
        donorVisiblePane.visibleProperty().set(true); //TODO leave false in final product

        interactiveGrid.add(donorVisiblePane, 0, 0);
        interactiveGrid.add(backButton, 1, 0);

        topArea.setRight(interactiveGrid);
        topArea.setLeft(header);

        // putting it all together
        BorderPane providerPane = new BorderPane();
        providerPane.setPadding(new Insets(15));
        providerPane.setTop(topArea);
        providerPane.setLeft(description);
        providerPane.setRight(borderedMap);

        providerScene = new Scene(providerPane, 1080, 550);

    }

    /**
     * Updates the frame based on the user that is logged in.
     * User must be set beforehand.
     */
    public void updateFrame() {
        if(loggedInUser.canDonate()) // if users can donate, let them see the donation fields
            donorVisiblePane.visibleProperty().set(true);
        else // otherwise don't
            donorVisiblePane.visibleProperty().set(false);
    }
}
