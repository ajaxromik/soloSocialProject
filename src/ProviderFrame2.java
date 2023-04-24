import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.scene.chart.*;
import javafx.scene.layout.VBox;


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
    private VBox mapContent;
    private Button signOutButton;

    private HBox permittedButtons;
    private Set<ButtonPermission> perms = new HashSet<>(); //by default user has no permissions
    private Button searchButton = new Button("Search");
    private Button donateButton = new Button("Donate");
    private Button editButton = new Button("Edit Location Info");

    private final int DETAILS_WIDTH = 600;
    private final String BULLETPOINT = "\u2022";

    private static Provider provider = new FoodPantry("TEST", "1234", 39.7100, -75.1192, "jeffs donuts", "A GREAT DONUT SHOP");


    
    // ----- testing functions -----
   
    /**
     * Starts the program
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
        addOpenStreetMap();
        // addInventoryChart();
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
        return mapContent;
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
        detailsContent.setWrappingWidth(DETAILS_WIDTH);    

        summaryContainer = new VBox(10);
        // summaryContainer.setPadding(new Insets(10));
       
        VBox tableContainer = new VBox();
        tableContainer.getChildren().add(new Label("Inventory Chart"));

        summaryContainer.getChildren().addAll(detailsContent, tableContainer);
        summaryContainer.setAlignment(Pos.TOP_LEFT);



        //creates the map section
        Label map = new Label("Map");
        map.setFont(new Font(17.5));

        Label distanceFrom = new Label("Distance From: " ); //+ User.distanceFrom(User.getlatitude(), User.getlatitude())
        // distanceFrom

        mapContent = new VBox();
        mapContent.setMinSize(200, 200);

        BorderPane mapContentContainer = new BorderPane();
        mapContentContainer.setTop(map);
        BorderPane.setAlignment(map, Pos.TOP_CENTER);
        mapContentContainer.setCenter(mapContent);

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
        mainPane.add(mapContentContainer, 1, 1);

        providerScene = new Scene(mainPane);
    }

    //TODO this method will eventually make it into the item class
    private void loadItems(ArrayList<Item> items) {
        Item item1 = new Item("Apple", "Smith");

        items.addAll(Arrays.asList(item1));
    }

    /**
     * Display the map of the FoodPantry with the coordinates of the given FoodPantry
     */
    private void addOpenStreetMap() {
        WebView webView = new WebView();

        //The actual map program: String url = "https://www.openstreetmap.org/#map=16/" + FoodPantry.getLatitude() + "/" + FoodPantry.getLongitude();
        
        //Embeded Map
        //String url = "https://www.openstreetmap.org/export/embed.html?bbox=" + User.getLongitude() + "," + FoodPantry.getLatitude() + "," + FoodPantry.getLongitude() + "," + FoodPantry.getLatitude() + "&layer=mapnik";

        //Testing Purposes: System.out.println(url);
        
        
        
        
        // webView.getEngine().load(url); 

       
        
        webView.setPrefWidth(400);
        webView.setPrefHeight(300);
        mapContent.getChildren().add(webView);
    }


    private void addInventoryChart(){
        TableView<Item> inventoryChart= new TableView<Item>();
        ArrayList<Item> items = new ArrayList<Item>();
        loadItems(items);

        // Setup the table columns
		TableColumn<Item, String> ItemName = 
				new TableColumn<Item, String>("Item Name");
		ItemName.setMinWidth(140);
		ItemName.setCellValueFactory(
				new PropertyValueFactory<Item, String>("Name")); 
				
		TableColumn<Item, String> ItemQuant = 
				new TableColumn<Item, String>("Item Quantity");
		ItemQuant.setMinWidth(100);
		ItemQuant.setCellValueFactory(
				new PropertyValueFactory<Item, String>("Quantity")); 

		TableColumn<Item, Integer> itemType =
				new TableColumn<Item, Integer>("Item Type");
		itemType.setMinWidth(60);
		itemType.setCellValueFactory(
				new PropertyValueFactory<Item, Integer>("Type")); 
		
		
		inventoryChart.getColumns().addAll(Arrays.asList(ItemName, ItemQuant, itemType));
		// using Arrays.asList avoids type safety issue
		items.forEach(item -> inventoryChart.getItems().add(item));

        summaryContainer.getChildren().add(inventoryChart);

    }
}