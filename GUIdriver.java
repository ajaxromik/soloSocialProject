import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Acts as the driver for the application as a whole.
 * 
 * @author William Carr
 */
public class GUIdriver extends Application{
    
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * A start method that allows the pages to swap from one screen to another
     * 
     * @param mainStage The stage of the application that we will change.
     */
    @Override
    public void start(Stage mainStage) {
        HomeFrame home = new HomeFrame(mainStage);
        
        // listener for login //TODO ask myers if this is bad design
        home.getLoginButton().setOnAction(e -> Login_Frame.buildLoginPage(mainStage));
        Login_Frame.getLoginButton().setOnAction(e -> mainStage.setScene(home.getScene())); //TODO change this because i just used the login button since there was no back button
    }

}
