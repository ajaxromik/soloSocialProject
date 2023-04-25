import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

/**
 * This Class is the login frame of our appliaction. This is where the user will enter in their login and password 
 * @author Mary C. Moor, William Carr
 */
public class Login_Frame extends Application{

    // ----- testing methods -----
    public static void main(String[] args) {
              launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        buildLoginPage(primaryStage, new ArrayList<User>());
    }

    // ----- end of testing methods -----
    
    /**
     * moved so back button is instantiated by the time GUI driver adds a listener
     * backButton should never be modified besides this and the driver's listener, do it static if you must
     */
    private static Button backButton = new Button("Back");
    private static Button createAccButton = new Button("Create Account");
    private static Button loginButton;
    private static TextField userField; // avoids passing parameters to the loginPress method
    private static PasswordField passField; // ditto of comment above; should never have more than one Login_Frame at a time
    private static User loggedInUser;


    /**
     * Returns the backButton
     * 
     * @author William Carr
     * @return backButton field
     */
    public static Button getBackButton() {
        return backButton;
    }

    /**
     * Returns the createAccButton
     * 
     * @author William Carr
     * @return createAccButton field
     */
    public static Button getCreateAccButton() {
        return createAccButton;
    }

    /**
     * Returns the loggedInUser. If nobody is logged in, will be null. Must do a 
     * 
     * @author William Carr
     * @return loggedInUser field
     */
    public static User getLoggedInUser() {
        return loggedInUser;
    }

    /**
     * If loggedInUser is not null, we are currently logged in
     * 
     * @author William Carr
     * @return True if logged in, otherwise false
     */
    public static boolean isLoggedIn() {
        return loggedInUser != null;
    }

    /**
     * Logs out of the current application's user if there is one
     * 
     * @author William Carr
     */
    public static void logout() {
        loggedInUser = null;
    }

    /**
     * Returns the loginButton //TODO probably get rid of this; login handles its own button in the loginPress() method
     * 
     * @author William Carr
     * @return loginButton field
     */
    public static Button getLoginButton() {
        return loginButton;
    }

    /**
     * Allows the driver to access the login page. 
     * Creates a new login page every time, so listeners should be added to the buttons after this method is called
     * 
     * @author Mary C. Moor, William Carr
     * @param primaryStage
     */
    public static void buildLoginPage(Stage primaryStage, List<User> users) {
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10));
        //Welcome message
        HBox welcome = new HBox();
        welcome.setAlignment(Pos.CENTER);
        welcome.setSpacing(10);
        welcome.getChildren().addAll(new Label("Welcome to the Social Service Application!\n Please login below to get started:"));

        //Username
        HBox username = new HBox();
        username.setAlignment(Pos.CENTER);
        username.setSpacing(20);
        userField = new TextField();
        username.getChildren().addAll(new Label("Username:"), userField);
        
        //Password
        HBox password = new HBox();
        password.setAlignment(Pos.CENTER);
        password.setSpacing(20);
        passField = new PasswordField();
        password.getChildren().addAll(new Label("Password:"), passField);

        //Forgot username/password          //keep in mind the users have no email
        HBox reset = new HBox();
        reset.setAlignment(Pos.BOTTOM_LEFT);
        reset.setSpacing(100);
        reset.getChildren().addAll(new Label("Having trouble: "), new Button("Reset")); 

        //Register/ new user
        HBox register = new HBox();
        register.setAlignment(Pos.BOTTOM_LEFT);
        register.setSpacing(80);
        register.getChildren().addAll(new Label("New user: "), createAccButton);


        //login and back button
        BorderPane login = new BorderPane();
        loginButton = new Button("LOGIN");
        loginButton.setOnAction(e -> loginPress(users));
        login.setLeft(backButton);
        login.setRight(loginButton);

        VBox centerBox = new VBox();
        centerBox.setAlignment(Pos.CENTER);
        centerBox.setSpacing(10);
        centerBox.setPadding(new Insets(10));
        centerBox.getChildren().addAll(welcome, username, password, reset, register, login);
        
        // Create a container to hold the login box with border
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(10));
        gridPane.setBorder(new Border(new BorderStroke(Color.BLACK,
        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        // Add the login box to the container
        gridPane.add(centerBox, 0, 0);

        borderPane.setCenter(gridPane);

        
        Scene scene = new Scene(borderPane, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Uses streaming with lambda expressions to find the user when logging in
     * 
     * @author William Carr
     * @editedBy Mary C. Moor
     * @param users The list of users from the database
     */
    private static void loginPress(List<User> users) {
        Optional<User> user = users.parallelStream()
                .filter(u -> u.checkLoginInfo(userField.getText(), passField.getText()))
                .findFirst();
    
        if (user.isPresent()) {
            loggedInUser = user.get();
            backButton.fire(); // uses the backButton's onEvent
        } else {
            Alert alert = new Alert(AlertType.ERROR, "Please try again, there is no user with those login credentials", ButtonType.OK);
            alert.showAndWait();
        }
    }

}