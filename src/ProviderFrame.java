import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.Map;

/**
 * The ProviderFrame will provide the basic functionality for the GUI of 
 * an indiviudal organization.
 * 
 * @author   Alexa Gonzalez 
 * @version  4.20.2023
 */
public class ProviderFrame extends Application
{
    //TODO: NEEDS TO BE LOGGED IN PROVIDER
    private static Provider provider = new FoodPantry("TEST", "TEST", 4321421342314.3214, 1234432.23423, "jeffs donuts", "A GREAT DONUT SHOP");
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage mainStage){ 

        BorderPane mainPane = new BorderPane();

        setupControls(mainPane);

        setStage(mainStage);

    }

    private void setupControls(BorderPane mainPane){
        VBox topBox = new VBox();
        //sets up name field
        HBox nameBox = new HBox();
        TextField nameField = new TextField(provider.getName());
        Button nameSubmit = new Button("change name");
        nameSubmit.setOnAction(e -> provider.setName(nameField.getText()));
        nameBox.getChildren().addAll(nameField, nameSubmit);

        //Sets up details field
        HBox detailsBox = new HBox();
        TextArea detailsArea = new TextArea(provider.getDetails());
        detailsArea.setPrefColumnCount(15);
        detailsArea.setPrefHeight(80);
        detailsArea.setPrefWidth(250);
        Button detailsSubmit = new Button("change name");
        detailsSubmit.setOnAction(e -> provider.setDetails(detailsArea.getText()));
        detailsBox.getChildren().addAll(detailsArea, detailsSubmit);

        topBox.getChildren().addAll(nameBox, detailsBox);
        
        VBox inventoryBox = new VBox();
        setUpInventoryControls(inventoryBox);
        
        
        mainPane.setTop(topBox);


    }

    private void setUpInventoryControls(VBox inventoryBox){
        HashMap<Item, Integer> inventory = provider.getInventory();
        for(Map.Entry<Item,Integer> item : inventory.entrySet()){
            HBox nameBox = new HBox();
            Label itemName = new Label(item.getKey().getItemName());
            TextField quantityField = new TextField("" + item.getValue());
            Button quantitySubmit = new Button("change quantity");
            quantitySubmit.setOnAction(e -> provider.setItemQuantity();
            nameBox.getChildren().addAll(nameField, nameSubmit);
        }

    }

    private void setStage(Stage stage) {
        stage.setTitle("Provider Information");


        stage.show();
    }



}