import javafx.application.Application;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Acts as the driver for the application as a whole.
 * Does a lot of setting the listeners that connect frames to each other
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

    private static ArrayList<User> users = new ArrayList<User>(UserBase.users.values());

    /**
     * A start method that allows the pages to swap from one screen to another
     * 
     * @author William Carr
     * @param mainStage The stage of the application that we will change.
     */
    @Override
    public void start(Stage mainStage) {

        // a lot of the beauty of the app is useless if you resize it
        mainStage.setResizable(false); 

        HomeFrame home = new HomeFrame(mainStage);
        
        // listener for login 
        home.getLoginButton().setOnAction(e -> Login_Frame.buildLoginPage(mainStage, users));

        //permission-based home button listeners
        home.getSearchButton().setOnAction(e -> SearchFrame.buildSearchPage(mainStage));
        
        // various back buttons
        Login_Frame.getBackButton().setOnAction(e -> { // the code in this button is how the screen returns to the home screen, and is called with a fire() method in Login_Frame
            if(Login_Frame.isLoggedIn()){ //how to change the home frame if we are logged in
                User loggedInUser = Login_Frame.getLoggedInUser();
                home.updatePermittedButtons(loggedInUser.getButtonPermissions()); // no matter what type of user, get the button permissions for them

                //edit button is able to not be reset because it would only matter if user is logged in again, in which case it would be reset
                home.getLoginButton().setText("Log out of: "+loggedInUser.getUsername());
                home.getEditButton().setOnAction(event -> EditUserFrame.createPage(mainStage, loggedInUser)); // updates the home buttons
                home.getLoginButton().setOnAction(event -> {Login_Frame.logout(); Login_Frame.buildLoginPage(mainStage, users);}); 

                //similar to edit button, the search button does not show unless logged in; therefore, we don't need to close it on logout
                ProviderDetailsFrame.setLoggedInUser(loggedInUser);

                if(loggedInUser.canDonate())
                    home.getViewDonationsButton().setOnAction(click -> DonationView.buildDonationView(mainStage, (Donor)loggedInUser));
                
            } else { // how we change the home frame if we are not logged in
                home.updatePermittedButtons(new HashSet<>());//sets HomeFrame's permissions to a new empty set
                home.getLoginButton().setText("Login");//resets the text since it was modified
                home.getLoginButton().setOnAction(event -> Login_Frame.buildLoginPage(mainStage, users));
            }
            // set the scene at the end because if we do it before, it'll change the sizing after swapping scenes
            mainStage.setScene(home.getScene());
        });

        EventHandler<ActionEvent> backToHome = e -> mainStage.setScene(home.getScene());

        SearchFrame.getBackButton().setOnAction(backToHome);
        EditUserFrame.getBackButton().setOnAction(backToHome);
        DonationView.getBackButton().setOnAction(backToHome);

    }

    /**
     * Updates the users
     * 
     * @author William Carr
     * @return The list of users on file for our organization
     */
    public static void addUser(User user) {
        users.add(user);
    }

}