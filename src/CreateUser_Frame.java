import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.util.HashMap;

/**
 * The CreateUser_Frame will creates a login page with fields for the user's 
 * name, password and re-entered password. Upon clicking the "Create" button,
 * the program checks if the passwords match and if the username is not 
 * already taken. If the input is valid, the program stores the user's 
 * credentials in a HashMap and displays a success message. If the input is 
 * invalid, the program displays an appropriate error message.
 * @author Mary C. Moor
 */
public class CreateUser_Frame extends Application {

    private final HashMap<String, String> userMap = new HashMap<>();

    // ----- testing methods -----
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Builds the login page and displays it.
     * @param primaryStage the primary stage of the JavaFX application
     * @author Mary C. Moor
     */
    @Override
    public void start(Stage primaryStage) {
        buildLoginPage(primaryStage);
    }

    // ----- end of testing methods -----

    /**
     * Creates the login page.
     * @param primaryStage the primary stage of the JavaFX application
     * @author Mary C. Moor
     */
    private void buildLoginPage(Stage primaryStage) {
        // Create the login form
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label sceneTitle = new Label("Create User Account");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(sceneTitle, 0, 0, 2, 1);

        Label userName = new Label("User Name:");
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);

        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);

        Label pw2 = new Label("Re-enter Password:");
        grid.add(pw2, 0, 3);

        PasswordField pwBox2 = new PasswordField();
        grid.add(pwBox2, 1, 3);

        Button btn = new Button("Create");
        grid.add(btn, 1, 4);

        Label statusLbl = new Label();
        grid.add(statusLbl, 1, 5);

        btn.setOnAction(event -> {
            String userNameTxt = userTextField.getText();
            String pwTxt = pwBox.getText();
            String pwTxt2 = pwBox2.getText();

            if (!pwTxt.equals(pwTxt2)) {
                statusLbl.setText("Passwords do not match!");
                return;
            }

            if (userMap.containsKey(userNameTxt)) {
                statusLbl.setText("User already exists!");
                return;
            }
            
            if (userNameTxt.equals("") || pwTxt.equals("") || pwTxt2.equals("")) {
                statusLbl.setText("Fields can not be blank!");
                return;
            }
            userMap.put(userNameTxt, pwTxt);
            statusLbl.setText("User created successfully!");
        });

        // Create the scene and set it on the stage
        Scene scene = new Scene(grid, 400, 275);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
