import javafx.application.Application;
import javafx.application.Platform;
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
public class HomeFrame extends Application {

    /**
     * Starts the program!
     * @param args The arguments used to start the program from the command line
     * @author William Carr
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Called a separate method in order to be modular.
     * 
     * @param mainStage The stage to add the home page onto.
     */
    @Override
    public void start(Stage mainStage) {
        buildHomePage(mainStage);
    }

    /**
     * Allows the driver to access the home page
     * 
     * @param mainStage The stage of the application
     * @author William Carr
     */
    public static void buildHomePage(Stage mainStage) { //TODO split this into smaller helper methods

        Text welcomeHeader = new Text("Welcome to the FoodFinder application!");
        welcomeHeader.setFont(new Font(20));

        // Text welcomeContent = new Text("This is an application developed to help those in need of food. The creators are Mary Moore, Julius Leone, Alexa Gonzales, and William Carr.");
        // welcomeContent.setWrappingWidth(300);

        BorderPane welcome = new BorderPane();
        // welcome.setPadding(new Insets(15));
        welcome.setTop(welcomeHeader);
        // welcome.setBottom(welcomeContent);

        Button loginButton = new Button("Login");
        loginButton.setFont(new Font(17.5));
        
        // listener for login //TODO replace this with some way to swap frames, would be easier if they were in the same class
        loginButton.setOnAction(e -> {
            System.out.println("switch windows");
            Login_Frame.buildLoginPage(mainStage);
        });

        BorderPane loginContainer = new BorderPane();
        loginContainer.setCenter(loginButton);

        Text summaryContent = new Text("This is an application developed to help those in need of food. The creators are Mary Moore, Julius Leone, Alexa Gonzales, and William Carr.\n\n"+
                                    "We hope to support those in need of basic necessities with our application and organization.");
        summaryContent.setWrappingWidth(500); //TODO consistent for now, but change this later

        Text recentDonationsHeader = new Text("Recent Donations");
        recentDonationsHeader.setFont(new Font(17.5));

        VBox recentDonationsFeed = new VBox();
        recentDonationsFeed.setBackground(new Background(new BackgroundFill(Color.DARKTURQUOISE, new CornerRadii(5), new Insets(5))));
        recentDonationsFeed.setMinSize(200, 200);

        BorderPane recentDonationsContainer = new BorderPane();
        recentDonationsContainer.setTop(recentDonationsHeader);
        BorderPane.setAlignment(recentDonationsHeader, Pos.TOP_CENTER);
        recentDonationsContainer.setCenter(recentDonationsFeed);

        GridPane mainPane = new GridPane(); //creates the main pane with its settings
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

        Scene homeScene = new Scene(mainPane);

        mainStage.setTitle("FoodFinder v0.1");
		mainStage.setScene(homeScene);
		mainStage.show();
    }
}
