import javafx.application.Application;
import javafx.stage.Stage;

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
        HomeFrame home = new HomeFrame(mainStage);
        
        // listener for login 
        home.getLoginButton().setOnAction(e -> Login_Frame.buildLoginPage(mainStage));
        Login_Frame.getLoginButton().setOnAction(e -> mainStage.setScene(home.getScene())); //TODO change this to back button when it's added
    }

}
