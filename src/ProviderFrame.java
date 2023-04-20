import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The ProviderFrame will provide the basic functionality for the GUI of 
 * an indiviudal organization.
 * 
 * @author   Alexa Gonzalez 
 * @version  4.20.2023
 */
public class ProviderFrame extends Application
{
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage mainStage){ 
        setStage(mainStage);
        
    }

    private void setStage(Stage stage) {
        stage.setTitle("Provider Information");
        stage.show();
    }



}

