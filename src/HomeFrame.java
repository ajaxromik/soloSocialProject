import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * A class to set up the Home page of our application
 * 
 * @author William Carr
 * @version 3.28.2023
 */
public class HomeFrame extends Application { // TODO probably want to remove "extends Application" and use driver/testing class

    // ----- testing functions -----
    /**
     * Starts the program!
     * @author William Carr
     * @param args The arguments used to start the program from the command line
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Makes it possible to test this class.
     * Keep in mind this is purely for UI appearance testing and it has no functionality.
     * 
     * @author William Carr
     * @param mainStage The stage to add the home page onto.
     */
    @Override
    public void start(Stage mainStage) {
        buildHomePage();
        setStage(mainStage);
        getLoginButton().setOnAction(e -> System.out.println("login button pressed"));
    }
    
    /**
     * Sets the stage for the driver
     * 
     * @author William Carr
     * @param stage
     */
    private void setStage(Stage stage) {
        stage.setTitle("FoodFinder v0.1");
		stage.setScene(homeScene);
		stage.show();
    }

    // ----- for overarching project -----
    //private instance variables allow for easier access to important parts of the Scene
    private Scene homeScene;
    private VBox summaryContainer;
    private VBox recentDonationsFeed;
    private Button loginButton;

    private HBox permittedButtons;
    private Set<ButtonPermission> perms = new HashSet<>(); //by default user has no permissions
    private Button searchButton = new Button("Search");
    private Button viewDonationsButton = new Button("View Donations");
    private Button editButton = new Button("Edit Location Info");

    private final int SUMMARY_WIDTH = 600;
    private final String BULLETPOINT = "\u2022";
    private static final int INTERVALS_UNTIL_FEED_STOP = 8640;  // a full day has 8640 individual 10-second intervals

    /**
     * Creates a HomeFrame Object to store the homeScene and adds it to a stage
     * 
     * @author William Carr
     */
    public HomeFrame(Stage stage) {
        buildHomePage();
        setStage(stage);
    }
    
    /**
     * Creates a HomeFrame Object to store the homeScene that the application will use
     * 
     * @author William Carr
     */
    public HomeFrame() {
        buildHomePage();
    }

    /**
     * Returns the homeScene
     * 
     * @author William Carr
     * @return homeScene field
     */
    public Scene getScene() {
        return homeScene;
    }

    /**
     * Returns the recentDonationsFeed
     * 
     * @author William Carr
     * @return recentDonationsFeed field
     */
    public VBox getDonationsFeed() {
        return recentDonationsFeed;
    }

    /**
     * Returns the loginButton
     * 
     * @author William Carr
     * @return loginButton field
     */
    public Button getLoginButton() {
        return loginButton;
    }

    /**
     * Returns the searchButton
     * 
     * @author William Carr
     * @return searchButton field
     */
    public Button getSearchButton() {
        return searchButton;
    }

    /**
     * Returns the viewDonationsButton
     * 
     * @author William Carr
     * @return viewDonationsButton field
     */
    public Button getViewDonationsButton() {
        return viewDonationsButton;
    }

    /**
     * Returns the editButton
     * 
     * @author William Carr
     * @return editButton field
     */
    public Button getEditButton() {
        return editButton;
    }

    /**
     * Adds the permitted buttons to the Home screen
     * 
     * @author William Carr
     * @param perms The list of ButtonPermissions to use. May not contain any duplicates. Cannot be null.
     */
    public void updatePermittedButtons(Set<ButtonPermission> perms) {
        //if permissions are different, make the buttons correct
        if(!this.perms.equals(perms)){
            this.perms = perms;
            List<Node> children = permittedButtons.getChildren();
            children.clear();
            //add the search button if they are logged in
            if(!perms.isEmpty())
                children.add(searchButton); // everyone gets to search
            for(ButtonPermission perm : perms){
                switch(perm){ //have to have the perms if you want the add or edit buttons
                    case EDIT:
                        children.add(editButton);
                        break;
                    case VIEWDONATIONS:
                        children.add(viewDonationsButton);
                        break;
                }
            }
        }
    }

    /**
     * Creates a home page for the object. 
     * This method should not be called more than once per object, or it will reset every field to their starting values.
     * (it would get rid of any listeners)
     * 
     * @author William Carr
     */
    private void buildHomePage() { //TODO split this into smaller helper methods
        
        //creates welcome section(top left)
        Text welcomeHeader = new Text("Welcome to the FoodFinder application!");
        welcomeHeader.setFont(new Font(20));

        BorderPane welcome = new BorderPane();
        welcome.setTop(welcomeHeader);

        //creates the login section(top right)
        loginButton = new Button("Login");
        loginButton.setFont(new Font(17.5));
        // loginButton.setStyle("-fx-background-color: #BBBBBB; -fx-background-radius: 0;"); // little test of playing with buttons

        BorderPane loginContainer = new BorderPane();
        loginContainer.setRight(loginButton);

        // creates the summary(bottom left)
        Text summaryContent = new Text("We hope to support those in need of basic necessities with our application and organization. "+
            "This app was designed with simplicity and accessibility in mind, helping users locate nearby food and essentials quickly and easily.\n\n"+
            "Key Features:\n"+
            "\t"+BULLETPOINT+" Find food banks in your area with real-time updates to ensure you never miss an opportunity for a nutritious meal\n"+
            "\t"+BULLETPOINT+" Connect with volunteers and organizations to donate food or offer your services\n"+
            "\t"+BULLETPOINT+" Discover the types of food available at food banks and distribution centers in your area\n"+
            "\nFeel free to contact us with any feedback, comments, or questions about the FoodFinder app at customersupport@foodfinder.org. "+
            "This service was brought to you by Mary Moor, Julius Leone, Alexa Gonzalez, and William Carr.");
        summaryContent.setWrappingWidth(SUMMARY_WIDTH);

        //creates the buttons for below the summary
        permittedButtons = new HBox(20);

        summaryContainer = new VBox(10);
        // summaryContainer.setPadding(new Insets(10));
        summaryContainer.getChildren().addAll(summaryContent, permittedButtons);
        summaryContainer.setAlignment(Pos.TOP_LEFT);

        //creates the donations feed section(bottom right)
        Text recentDonationsHeader = new Text("Recent Donations");
        recentDonationsHeader.setFont(new Font(17.5));

        recentDonationsFeed = new VBox(); //TODO implement the additions to the VBox here
        recentDonationsFeed.setBackground(new Background(new BackgroundFill(Color.DARKTURQUOISE, new CornerRadii(5), new Insets(5))));
        recentDonationsFeed.setMinSize(200, 200);
        startFeed();

        BorderPane recentDonationsContainer = new BorderPane();
        recentDonationsContainer.setTop(recentDonationsHeader);
        BorderPane.setAlignment(recentDonationsHeader, Pos.TOP_CENTER);
        recentDonationsContainer.setCenter(recentDonationsFeed);

        //creates the main pane with its settings
        GridPane mainPane = new GridPane();
        mainPane.setPadding(new Insets(20));
        mainPane.setMinSize(400, 200);
        mainPane.setVgap(10);
        mainPane.setHgap(20);
        mainPane.setAlignment(Pos.CENTER);
        // mainPane.setGridLinesVisible(true); //TODO take this out when the application as a whole is finished entirely

        mainPane.add(welcome,0,0);
        mainPane.add(loginContainer, 1, 0);
        mainPane.add(summaryContainer, 0, 1);
        mainPane.add(recentDonationsContainer, 1, 1);

        homeScene = new Scene(mainPane);
    }

    /**
     * Gets the text to use to display a donation in the feed.
     * @param donation The donation to convert to text.
     * @return The String version of the donation.
     */
    private static String getDonationText(Donation donation) {
        return String.format("%i %s(s) to %s", donation.getQuantityOfItems(), donation.getItemName(), donation.getProvider().getName());
    }

    /**
     * Updates the donations VBox feed based on the newly made donations.
     */
    private void updateFeedOnce() {
        recentDonationsFeed.getChildren().clear();
        
        ArrayList<Donation> displayedDonations = DonationsBase.getRecentDonations();

        String foundation = "";
        for(int i = 0; i < displayedDonations.size(); i++) {
            if(i != 0) //separate this line from the one before if it's not the first
                foundation += "\n";
            foundation += getDonationText(displayedDonations.get(i));
        }

        recentDonationsFeed.getChildren().add(new Text(foundation));
    }

    /**
     * Updates the donations VBox feed indefinitely.
     */
    private void updateFeedIndefinitely() {
        try {
            for (int i = 0; i < HomeFrame.INTERVALS_UNTIL_FEED_STOP; i++) {
                updateFeedOnce();
                Thread.sleep(10000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Uses the recentDonationsFeed instance variable to set the feed running.
     */
    private void startFeed() {

        // starts the automatically updating feed
        Runnable updater = new Runnable() {
            public void run() {
                updateFeedIndefinitely();
            }
        };

        Thread backgroundUpdater = new Thread(updater);
        backgroundUpdater.setDaemon(true);
        backgroundUpdater.start(); // tells the application to start the updater
        
    }

}