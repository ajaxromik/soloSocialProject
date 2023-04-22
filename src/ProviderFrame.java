import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
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
    private Scene providerScene;

    private static Item testItem = new Item("Apple", "Green");
    //TODO: NEEDS TO BE LOGGED IN PROVIDER
    private static Provider provider = new FoodPantry("TEST", "TEST", 4321421342314.3214, 1234432.23423, "jeffs donuts", "A GREAT DONUT SHOP");
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage mainStage){ 
        //BorderPane mainPane = new BorderPane();
        setStage(mainStage);

        setupControls(mainStage);



    }

    private void setupControls(Stage mainStage){

        BorderPane mainPane = new BorderPane();

        Text welcomeHeader = new Text(provider.getName());
        welcomeHeader.setFont(new Font(20));
        mainPane.setTop(welcomeHeader);

        // VBox topBox = new VBox();
        // //sets up name field
        // HBox nameBox = new HBox();
        // TextField nameField = new TextField(provider.getName());
        // Button nameSubmit = new Button("change name");
        // nameSubmit.setOnAction(e -> provider.setName(nameField.getText()));
        // nameBox.getChildren().addAll(nameField, nameSubmit);

        // //Sets up details field
        // HBox detailsBox = new HBox();
        // TextArea detailsArea = new TextArea(provider.getDetails());
        // detailsArea.setPrefColumnCount(15);
        // detailsArea.setPrefHeight(80);
        // detailsArea.setPrefWidth(250);
        // Button detailsSubmit = new Button("change details");
        // detailsSubmit.setOnAction(e -> provider.setDetails(detailsArea.getText()));
        // detailsBox.getChildren().addAll(detailsArea, detailsSubmit);

        // topBox.getChildren().addAll(nameBox, detailsBox);
        
        // VBox inventoryBox = new VBox();
        // setUpInventoryControls(inventoryBox);
        
        
        //mainStage.setTop(topBox);


    }

    private void setUpInventoryControls(VBox inventoryBox){
        HashMap<Item, Integer> inventory = provider.getInventory();
        for(Map.Entry<Item,Integer> item : inventory.entrySet()){
            HBox nameBox = new HBox();
            Label itemName = new Label(item.getKey().getItemName());
            TextField quantityField = new TextField("" + item.getValue());
            Button quantitySubmit = new Button("change quantity");
            quantitySubmit.setOnAction(e -> provider.setItemQuantity(testItem, 5));
            nameBox.getChildren().addAll(nameBox, itemName, quantityField); // org. params: nameField, nameSubmit
        }

    }

    private void setStage(Stage stage) {
        stage.setTitle("Provider Information");
        stage.setScene(providerScene);
        stage.show();
    }



}

// import javafx.application.Application;
// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
// import javafx.scene.chart.Chart;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.control.TextField;
// import javafx.scene.control.TextArea;
// import javafx.scene.layout.BorderPane;
// import javafx.scene.layout.GridPane;
// import javafx.scene.layout.HBox;
// import javafx.scene.layout.VBox;
// import javafx.scene.text.Font;
// import javafx.scene.text.Text;
// import javafx.stage.Stage;
// import java.util.HashMap;
// import java.util.Map;

// /**
//  * The ProviderFrame will provide the basic functionality for the GUI of 
//  * an indiviudal organization.
//  * 
//  * @author   Alexa Gonzalez 
//  * @version  4.20.2023
//  */
// public class ProviderFrame extends Application
// {

//     private static Item testItem = new Item("Apple", "Green");
//     //TODO: NEEDS TO BE LOGGED IN PROVIDER
//     private static Provider provider = new FoodPantry("TEST", "TEST", 4321421342314.3214, 1234432.23423, "jeffs donuts", "A GREAT DONUT SHOP");
//     public static void main(String[] args){
//         launch(args);
//     }

//     @Override
//     public void start(Stage mainStage){ 

//         BorderPane mainPane = new BorderPane();

//         setupControls(mainPane);

//         Chart chartView = new ChartView(provider.getInventory());
//         mainPane.setBottom(chartView.getView());

//         setStage(mainStage, mainPane);

//     }

//     private void setupControls(BorderPane mainPane){
//         VBox topBox = new VBox();
//         //sets up name field
//         HBox nameBox = new HBox();
//         TextField nameField = new TextField(provider.getName());
//         Button nameSubmit = new Button("change name");
//         nameSubmit.setOnAction(e -> provider.setName(nameField.getText()));
//         nameBox.getChildren().addAll(nameField, nameSubmit);

//         //Sets up details field
//         HBox detailsBox = new HBox();
//         TextArea detailsArea = new TextArea(provider.getDetails());
//         detailsArea.setPrefColumnCount(15);
//         detailsArea.setPrefHeight(80);
//         detailsArea.setPrefWidth(250);
//         Button detailsSubmit = new Button("change details");
//         detailsSubmit.setOnAction(e -> provider.setDetails(detailsArea.getText()));
//         detailsBox.getChildren().addAll(detailsArea, detailsSubmit);

//         topBox.getChildren().addAll(nameBox, detailsBox);
        
//         VBox inventoryBox = new VBox();
//         setUpInventoryControls(inventoryBox);
        
       
//     }}