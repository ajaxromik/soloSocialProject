import java.util.stream.Collectors;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * A class that shows some details about a provider that the user would see after hitting "See More" in the SearchFrame.
 * Could possibly be used to also display a preview for Providers that are editing details. //TODO consider implementing this
 * 
 * @author William Carr
 * @version 6.25.2023
 */
public class ProviderDetailsFrame extends Application{
    
    //TODO remove this
    private final Provider TESTING = UserBase.providers.values().iterator().next();//gets one of the providers from userbase in a disgusting way

    /**
     * Tests the class
     * 
     * @param args Arguments if you so desire
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Allows for testing.
     * 
     * @param mainStage The stage to test the application on
     */
    @Override
    public void start(Stage mainStage){
        mainStage.setScene(providerScene);
        mainStage.show();
    }

    /**
     * Creates a frame for the provider; meant for testing
     */
    public ProviderDetailsFrame() {
        this.provider = TESTING;
        setupControls();
    }

    // ----- end of testing -----
    private Provider provider; //TODO determine if this needs to actually be saved here
    private Scene providerScene;
    private Button backButton = new Button("Back");

    /**
     * Creates a frame for a provider. Must call setupControls when provider is changed.
     * @param provider The provider to base the frame on
     */
    public ProviderDetailsFrame(Provider provider) {
        this.provider = provider;
        setupControls();
    }

    /**
     * Returns the homeScene
     * 
     * @author William Carr
     * @return providerScene field
     */
    public Scene getScene() {
        return providerScene;
    }

    /**
     * Returns the back button to link it to other parts of the program.
     * @return the back button
     */
    public Button getBackButton() {
        return backButton;
    }

    /**
     * Creates the setup for the pane.
     * Does not need to update with changes to the provider, 
     * because a new instance of this class is created when being used.
     */
    public void setupControls() {

        // creates the header just like in the search frame
        Label header = new Label(String.format("%s (%.6f, %.6f)",provider.getName(),provider.getLongitude(),provider.getLatitude()));
        header.setStyle("-fx-font-size: 24px;");
        
        //inventory items + details
        Text description = new Text(
            "Inventory: "+
            (provider.getInventory().isEmpty() ? "Inventory items not listed" : // shows either the previous string of the items of the inventory
            provider.getInventory().keySet().stream().map(
                item -> String.format("%s: %d", item.getItemName(),provider.getInventory().get(item))
            ).collect(Collectors.toList()))+
            "\n\n"+
            provider.getDetails()
        );
        description.setWrappingWidth(600);

        // webview of the map, 
        // TODO unfortunately is hard to control without an api key, but I'm not sure how the billing works so i steered clear of it
        WebView mapView = new WebView();
        WebEngine mapEngine = mapView.getEngine();
        mapEngine.load(String.format("https://maps.google.com/?ll=%f,%f&z=5",provider.getLatitude(),provider.getLongitude()));//somewhere in mexico
        mapView.setMaxSize(450, 500);

        // Header and back button
        BorderPane topArea = new BorderPane();
        topArea.setPadding(new Insets(0, 0, 10, 0));
        topArea.setRight(backButton);
        topArea.setLeft(header);

        // putting it all together
        BorderPane providerPane = new BorderPane();
        providerPane.setPadding(new Insets(15));
        providerPane.setTop(topArea);
        providerPane.setLeft(description);
        providerPane.setRight(mapView);

        providerScene = new Scene(providerPane, 1080, 550);

    }
}
