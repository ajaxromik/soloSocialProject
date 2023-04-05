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

    @Override
    public void start(Stage mainStage) {
        HomeFrame.buildHomePage(mainStage);
    }

}
