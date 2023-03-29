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
     * Starts the UI for the home frame
     * 
     * @param mainStage The stage of the application
     * @author William Carr
     */
    @Override
    public void start(Stage mainStage) { //TODO split this into smaller helper methods

        Text welcomeHeader = new Text(40, 20, "Welcome!");
        welcomeHeader.setFont(new Font(20));

        Text welcomeContent = new Text("This is an application developed to help those in need of food. The creators are Mary Moore, Julius Leone, Alexa Gonzales, and William Carr.");
        welcomeContent.setWrappingWidth(300);

        BorderPane welcome = new BorderPane();
        // welcome.setPadding(new Insets(15));
        welcome.setTop(welcomeHeader);
        welcome.setBottom(welcomeContent);

        Button loginButton = new Button("Login");
        loginButton.setFont(new Font(17.5));

        Text something = new Text("This is where something else that is also important(i forget what) will go");
        something.setWrappingWidth(300); //TODO consistent for now, but change this later

        Text recentAdditionsHeader = new Text("This is where our recent additions will go...");
        recentAdditionsHeader.setWrappingWidth(150);

        GridPane mainPane = new GridPane(); //creates the main pane with its settings
        mainPane.setPadding(new Insets(20));
        mainPane.setMinSize(400, 200);
        mainPane.setVgap(10);
        mainPane.setHgap(20);
        mainPane.setAlignment(Pos.CENTER);
        // mainPane.setGridLinesVisible(true); //TODO take this out when this is finished entirely

        mainPane.add(welcome,0,0);
        mainPane.add(loginButton, 1, 0);
        mainPane.add(something, 0, 1);
        mainPane.add(recentAdditionsHeader, 1, 1);

        Scene homeScene = new Scene(mainPane);

        mainStage.setTitle("FoodFinder v0.1");
		mainStage.setScene(homeScene);
		mainStage.show();
    }
}
