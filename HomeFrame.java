import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
    private VBox recentDonationsFeed;
    private Button loginButton;

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
     * Creates a home page for the object
     * 
     * @author William Carr
     * @param mainStage The stage of the application
     */
    private void buildHomePage() { //TODO split this into smaller helper methods
        
        //creates welcome section(top left)
        Text welcomeHeader = new Text("Welcome to the FoodFinder application!");
        welcomeHeader.setFont(new Font(20));

        // Text welcomeContent = new Text("This is an application developed to help those in need of food. The creators are Mary Moore, Julius Leone, Alexa Gonzales, and William Carr.");
        // welcomeContent.setWrappingWidth(300);

        BorderPane welcome = new BorderPane();
        // welcome.setPadding(new Insets(15));
        welcome.setTop(welcomeHeader);
        // welcome.setBottom(welcomeContent);

        //creates the login section(top right)
        loginButton = new Button("Login");
        loginButton.setFont(new Font(17.5));

        BorderPane loginContainer = new BorderPane();
        loginContainer.setCenter(loginButton);

        // creates the summary(bottom left) //TODO this might need a Vbox container later to allow new buttons to appear below the summary
        Text summaryContent = new Text("This is an application developed to help those in need of food. The creators are Mary Moore, Julius Leone, Alexa Gonzales, and William Carr.\n\n"+
                                    "We hope to support those in need of basic necessities with our application and organization.");
        summaryContent.setWrappingWidth(500); //TODO consistent for now, but change this later

        //creates the donations feed section(bottom right)
        Text recentDonationsHeader = new Text("Recent Donations");
        recentDonationsHeader.setFont(new Font(17.5));

        recentDonationsFeed = new VBox();
        recentDonationsFeed.setBackground(new Background(new BackgroundFill(Color.DARKTURQUOISE, new CornerRadii(5), new Insets(5))));
        recentDonationsFeed.setMinSize(200, 200);

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
        // mainPane.setGridLinesVisible(true); //TODO take this out when this is finished entirely

        mainPane.add(welcome,0,0);
        mainPane.add(loginContainer, 1, 0);
        mainPane.add(summaryContent, 0, 1);
        mainPane.add(recentDonationsContainer, 1, 1);

        homeScene = new Scene(mainPane);
    }

}
