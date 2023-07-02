import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.control.Label;

/**
 * Frame of application for editing a user's details.
 * 
 * @author William Carr
 * @version 6.30.2023
 */
public class EditUserFrame extends Application{
    
    /**
     * Added for testing, see start(Stage)
     * @param args Arguments of the program
     */
    public static void main(String[] args) {
        launch(args);
    }

    private static Button backButton;

    /**
     * Returns the back button
     * @return The back button
     */
    public static Button getBackButton() {
        return backButton;
    }

    /**
     * Tests the program with a test foodpantry
     */
    public void start(Stage mainStage) {
        // createPage(mainStage, new FoodPantry("testing", "pw", 30, 30, "bob's discount furniture", "bob sell furnies")); //TODO testing
        createPage(mainStage, UserBase.users.get("theShop"));
    }

    /**
     * Creates a page that allows for editing important user details.
     * @param mainStage Stage to apply the page onto
     * @param user The user to get the details that will be added.
     */
    public static void createPage(Stage mainStage, User user) {

        // userfields needed later
        ArrayList<Node> userFields = user.getUserFields();

        //sets up the top area
        Label header = new Label("Edit User Information");
        header.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

        backButton = new Button("Back");

        // formatting
        BorderPane headPane = new BorderPane();
        headPane.setLeft(header);

        BorderPane backPane = new BorderPane();
        backPane.setRight(backButton);

        // submit button/area
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            user.updateUserFields(userFields);
            // userFields.forEach(f -> System.out.println((f instanceof TextField) ? ""+Double.parseDouble(((TextField)f).getText()) : "not a textfield")); //TODO added for testing, remove when done
            System.out.println(UserBase.users.get("theShop")); //TODO testing
            backButton.fire();
        });
        BorderPane submittalPane = new BorderPane();
        submittalPane.setRight(submitButton);

        //final setup
        GridPane mainPane = new GridPane();
        mainPane.setPadding(new Insets(20));

        // spaced the gridpane additions by row
        mainPane.add(headPane, 0, 0);
        mainPane.add(backPane, 1, 0);

        mainPane.add(userFields.get(0), 0, 1, 2, 1); // makes input area centered

        mainPane.add(submittalPane, 1, 2);

        Scene finalScene = new Scene(mainPane);
        mainStage.setScene(finalScene);
        mainStage.show();
    }

}
