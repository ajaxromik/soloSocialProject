import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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
        buildLoginPage(primaryStage);
    }

    // ----- end of testing methods -----
    
    /**
     * moved so login button is instantiated by the time GUI driver adds a listener
     * loginButton should never be modified, do it static if you must
     */
    private static Button loginButton = new Button("LOGIN");

    /**
     * Returns the loginButton
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
     * @author Mary C. Moor
     * @param primaryStage
     */
    public static void buildLoginPage(Stage primaryStage) {
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
        username.getChildren().addAll(new Label("Username:"), new TextField());
        
        //Password
        HBox password = new HBox();
        password.setAlignment(Pos.CENTER);
        password.setSpacing(20);
        password.getChildren().addAll(new Label("Password:"), new PasswordField());

        //Forgot username/password
        HBox reset = new HBox();
        reset.setAlignment(Pos.BOTTOM_LEFT);
        reset.setSpacing(100);
        reset.getChildren().addAll(new Label("Having trouble: "), new Button("Reset"));

        //Register/ new user
        HBox register = new HBox();
        register.setAlignment(Pos.BOTTOM_LEFT);
        register.setSpacing(80);
        register.getChildren().addAll(new Label("New user: "), new Button("Create Account"));


        //login
        HBox login = new HBox();
        login.setAlignment(Pos.BOTTOM_RIGHT);
        login.setSpacing(10);
        login.getChildren().addAll(loginButton);

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

}