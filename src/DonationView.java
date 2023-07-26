import java.util.Arrays;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * Makes a frame to view a donor's donations.
 * 
 * @author William Carr
 * @version 7.9.2023
 */
public class DonationView extends Application {

    /**
     * Tests the program 
     * @param args Arguments if you would(they do nothing)
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Tests this part of the application through javafx
     */
    public void start(Stage mainStage) {
        buildDonationView(mainStage, UserBase.donors.values().iterator().next());
        mainStage.show();
    }

    // ----- end of testing -----

    private static Button backButton = new Button("Back");

    /**
     * Returns the back button
     * @return The back button
     */
    public static Button getBackButton() {
        return backButton;
    }

    /**
     * Staticly builds a new donation view to keep up to date with current info.
     * @param mainStage The stage to apply the view to.
     * @param donor The donor whose information we are viewing.
     */
    public static void buildDonationView(Stage mainStage, Donor donor) {

        BorderPane topArea = new BorderPane();

        Label header = new Label("View Donations");
        topArea.setLeft(header);
        BorderPane.setAlignment(header, Pos.CENTER_LEFT);
        header.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

        topArea.setRight(backButton);
        BorderPane.setAlignment(backButton, Pos.CENTER_RIGHT);
        
        //sets up table
        TableView<Donation> donationsBox = new TableView<>();

        // item name column
        TableColumn<Donation, String> itemColumn = new TableColumn<Donation, String>("Item");
        itemColumn.setCellValueFactory(new PropertyValueFactory<Donation, String>("ItemName"));

        // quantity of donation column
        TableColumn<Donation, String> quantityColumn = new TableColumn<Donation, String>("Quantity");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<Donation, String>("QuantityOfItems"));

        // provider column
        TableColumn<Donation, String> providerColumn = new TableColumn<Donation, String>("Provider");
        providerColumn.setCellValueFactory(cellData -> { //fills the column with the provider's name
            Provider provider = cellData.getValue().getProvider();
            return new SimpleStringProperty(provider.getName());
        });

        // date column
        TableColumn<Donation, String> dateColumn = new TableColumn<Donation, String>("Date(YYYY-MM-DD)");
        dateColumn.setMinWidth(80);
        dateColumn.setCellValueFactory(new PropertyValueFactory<Donation, String>("Date"));

        //adds the columns
        donationsBox.getColumns().addAll(Arrays.asList(itemColumn,quantityColumn,providerColumn,dateColumn));

        //populates the table
        donor.getDonations().forEach(donation -> donationsBox.getItems().add(donation));

        // sizing
        donationsBox.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        donationsBox.setPrefSize(500, 400);

        GridPane mainPane = new GridPane();
        mainPane.setVgap(10);
        mainPane.setPadding(new Insets(20));
        mainPane.add(topArea, 0, 0);
        mainPane.add(donationsBox, 0, 1);

        Scene scene = new Scene(mainPane);
        mainStage.setScene(scene);
    }
    
}
