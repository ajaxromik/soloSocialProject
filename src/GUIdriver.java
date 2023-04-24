import javafx.application.Application;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Acts as the driver for the application as a whole.
 * 
 * @author William Carr
 */
public class GUIdriver extends Application{
    
    /**
     * Runs our GUI, with the ability to swap from one page to another.
     * 
     * @author William Carr
     * @param args Command line input (ignore)
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * A start method that allows the pages to swap from one screen to another
     * 
     * @author William Carr
     * @param mainStage The stage of the application that we will change.
     */
    @Override
    public void start(Stage mainStage) {
        ArrayList<User> users = getUsers(); // TODO temporary testing replacement for the users we will use in the end; change when serialization is done
        HomeFrame home = new HomeFrame(mainStage);
        
        // listener for login 
        home.getLoginButton().setOnAction(e -> Login_Frame.buildLoginPage(mainStage, users));
        
        Login_Frame.getBackButton().setOnAction(e -> {
            if(Login_Frame.isLoggedIn()){ //how to change the home frame if we are logged in
                home.updatePermittedButtons(Login_Frame.getLoggedInUser().getButtonPermissions());

                //TODO make this its own change login method and logout method
                home.getLoginButton().setText("Log out of: "+Login_Frame.getLoggedInUser().getUsername());
                home.getLoginButton().setOnAction(event -> {Login_Frame.logout(); Login_Frame.buildLoginPage(mainStage, users);}); 
            } else { // how we change the home frame if we are not logged in
                home.updatePermittedButtons(new HashSet<>());//sets HomeFrame's permissions to a new empty set
                home.getLoginButton().setText("Login");//resets the text since it was modified
                home.getLoginButton().setOnAction(event -> Login_Frame.buildLoginPage(mainStage, users));
            }
            // set the scene at the end because if we do it before, it'll change the sizing after swapping scenes
            mainStage.setScene(home.getScene());
        });
    }

    /**
     * Temporary testing replacement for the list of users. //TODO fill this eventually
     * There should never be users with the same username, let alone same user and password.
     * 
     * @author William Carr
     * @return The list of users on file for our organization
     */
    private ArrayList<User> getUsers() {
        return new ArrayList<User>(Arrays.asList(
            new FoodPantry("admin", "minad", 0.0, 0.0, "Testing FoodPantry", "test"), 
            new FoodPantry("regularUser", "imAUser", 0.0, 0.0, "Testing FoodPantry", "test")
        ));
    }

}