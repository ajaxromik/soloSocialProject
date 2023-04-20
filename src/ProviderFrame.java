import java.util.ArrayList;
import java.util.Arrays;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


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
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage mainStage){ 
        BorderPane mainPane = new BorderPane(); 
        setupControls(mainPane);
        Scene scene = new Scene(mainPane);
        setStage(mainStage, scene);
        
    }

    private void setupControls(BorderPane mainPain) {

        TableView<ProductType> viewProduct = new TableView<ProductType>();
        ArrayList<ProductType> products = new ArrayList<ProductType>();
        

        TableColumn<ProductType, String> colTitle = 
				new TableColumn<ProductType, String>("ProductType Title");
		colTitle.setMinWidth(140);
		colTitle.setCellValueFactory(
				new PropertyValueFactory<ProductType, String>("Title")); 
				
		TableColumn<ProductType, String> colArtist = 
				new TableColumn<ProductType, String>("Artist");
		colArtist.setMinWidth(100);
		colArtist.setCellValueFactory(
				new PropertyValueFactory<ProductType, String>("Artist")); 

		TableColumn<ProductType, Integer> colReleaseYear =
				new TableColumn<ProductType, Integer>("Released");
		colReleaseYear.setMinWidth(60);
		colReleaseYear.setCellValueFactory(
				new PropertyValueFactory<ProductType, Integer>("ReleaseYear")); 
		
		TableColumn<ProductType, String> colMovie = 
				new TableColumn<ProductType, String>("Featured Movie");
		colMovie.setMinWidth(120);
		colMovie.setCellValueFactory(
				new PropertyValueFactory<ProductType, String>("FeaturedMovieTitle")); 
		
		viewProduct.getColumns().addAll(Arrays.asList(colTitle, colArtist, colReleaseYear, colMovie));
		// using Arrays.asList avoids type safety issue
		products.forEach(product -> viewProduct.getItems().add(product));
	
        mainPain.setBottom(viewProduct);

    }
    private void setStage(Stage stage, Scene scene) {
        stage.setTitle("Provider Information");
        stage.setScene(providerScene);
        stage.show();
    }



}

