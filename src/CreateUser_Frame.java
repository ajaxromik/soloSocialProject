import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.util.HashMap;

/**
 * The CreateUser_Frame will creates a create page with fields for the user's 
 * name, password and re-entered password. Upon clicking the "Create" button,
 * the program checks if the passwords match and if the username is not 
 * already taken. If the input is valid, the program stores the user's 
 * credentials in a HashMap and displays a success message. If the input is 
 * invalid, the program displays an appropriate error message.
 * @author Mary C. Moor, William Carr
 */
public class CreateUser_Frame extends Application {

    // ----- testing methods -----
    public static void main(String[] args) {
        System.out.println(USER_MAP);
        launch(args);
    }

    /**
     * Builds the create page and displays it.
     * @param primaryStage the primary stage of the JavaFX application
     * @author Mary C. Moor
     */
    @Override
    public void start(Stage primaryStage) {
        buildCreatePage(primaryStage);
    }

    // ----- end of testing methods -----

    private static final HashMap<String, User> USER_MAP = UserBase.users;

    private static String userNameTxt;
    private static String pwTxt;
    private static double longitudeValue;
    private static double latitudeValue;
    private static Button backButton = new Button("Back");
    static {
        BorderPane.setAlignment(backButton, Pos.CENTER_RIGHT);
    }

    /**
     * Returns the back button
     * @return The back button
     */
    public static Button getBackButton() {
        return backButton;
    }

    /**
     * Creates the Creation page.
     * @param primaryStage the primary stage of the JavaFX application
     * @author Mary C. Moor, William Carr
     */
    public static void buildCreatePage(Stage primaryStage) {
        // Create the Create form
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label sceneTitle = new Label("Create User Account");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        BorderPane titlePane = new BorderPane();
        titlePane.setLeft(sceneTitle);
        titlePane.setRight(backButton);
        BorderPane.setAlignment(sceneTitle, Pos.CENTER_LEFT);
        
        grid.add(titlePane, 0, 0, 2, 1);

        Label userName = new Label("User Name:");
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);

        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);

        Label pw2 = new Label("Re-enter Password:");
        grid.add(pw2, 0, 3);

        PasswordField pwBox2 = new PasswordField();
        grid.add(pwBox2, 1, 3);

        Label latitude = new Label("Latitude:");
        grid.add(latitude, 0, 4);

        TextField latitudeField = new TextField();
        grid.add(latitudeField, 1, 4);

        Label longitude = new Label("Longitude:");
        grid.add(longitude, 0, 5);

        TextField longitudeField = new TextField();
        grid.add(longitudeField, 1, 5);

        //Only allows numbers into the longitude and latitude, and sets the static variables
        latitudeField.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches("[\\d.-]")) //TODO leave setting the variable for later
                latitudeField.setText(newValue.replaceAll("[^\\d.-]|(?<!^)[-]",""));
            
            if(newValue.length() > 12)// shorten if the string is too long. No number will need more accuracy than the 8th digit after the decimal, we do not deal with numbers in the hundreds for latitude, plus room for a decimal place and minus
                latitudeField.setText(latitudeField.getText().substring(0, 12));
        });
        longitudeField.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches("[\\d.-]")) 
                longitudeField.setText(newValue.replaceAll("[^\\d.-]|(?<!^)[-]",""));
            
            if(newValue.length() > 13)// shorten if the string is too long. No number will need more accuracy than the 8th digit after the decimal, and we do not deal with numbers in the thousands, plus room for a decimal place and minus
                longitudeField.setText(longitudeField.getText().substring(0, 13));
        });

        Button createButton = new Button("Create");
        grid.add(createButton, 1, 6);

        Label statusLbl = new Label("\n ");//gets the first two lines so no resizing
        grid.add(statusLbl, 0, 7, 2, 1);

        createButton.setOnAction(event -> {
            try {
                userNameTxt = userTextField.getText();
                pwTxt = pwBox.getText();
                String pwTxt2 = pwBox2.getText();
                
                boolean longitudeEmpty = longitudeField.textProperty().getValue().isEmpty();
                boolean latitudeEmpty = latitudeField.textProperty().getValue().isEmpty();
                
                if (userNameTxt.equals("") || pwTxt.equals("") || pwTxt2.equals("")
                    || longitudeEmpty || latitudeEmpty) {
                    statusLbl.setText("Fields can not be blank!");
                    return;
                }

                longitudeValue = Double.parseDouble(longitudeField.getText());
                latitudeValue = Double.parseDouble(latitudeField.getText());

                if (!pwTxt.equals(pwTxt2)) {
                    statusLbl.setText("Passwords do not match!");
                    return;
                }

                if (USER_MAP.containsKey(userNameTxt)) {
                    throw new UserAlreadyExistsException(userNameTxt);
                }

                if(!User.checkLatLon(latitudeValue, longitudeValue)) { //the previous if statement would have been called if either the latitude or longitude were empty, so we call this method to make sure they are within the valid range
                    statusLbl.setText("Latitude must be between -90 and 90.\nLongitude must be between -180 and 180.");
                    return;
                }

            } catch(UserAlreadyExistsException ex) {
                statusLbl.setText("User already exists!\nYou could try '"+ex.getSuggestedUsername()+"' for a username.");
                return;
            } catch(NumberFormatException ex) {
                statusLbl.setText("Latitude or longitude input is invalid.\nMake sure that they are valid numbers.");
                return;
            } catch(Exception ex) {
                ex.printStackTrace();
            }
            
            statusLbl.setText("User created successfully!");
            buildSpecialtiesPage(primaryStage);
        });

        // Create the scene and set it on the stage
        Scene scene = new Scene(grid);
        primaryStage.setScene(scene);//TODO these lines exist in a lot of files but could be removed
        primaryStage.show();
    }

    /**
     * Creates the page where you add the special details for each type of user.
     * @param primaryStage the primary stage of the app
     * @author William Carr
     */
    public static void buildSpecialtiesPage(Stage primaryStage) {
        // Create the create form
        GridPane mainPane = new GridPane();
        mainPane.setAlignment(Pos.CENTER);
        mainPane.setHgap(10);
        mainPane.setVgap(10);
        mainPane.setPadding(new Insets(25, 25, 25, 25));

        Label sceneTitle = new Label("Account Specialties");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        mainPane.add(sceneTitle, 0, 0, 2, 1);

        Label accTypeLabel = new Label("Type of Account:");
        mainPane.add(accTypeLabel, 0, 1);

        ChoiceBox<String> accountTypes = new ChoiceBox<String>();
		accountTypes.getItems().addAll("Standard User", "Donor", "Food Pantry"); //calling the account type recipient feels weird
		accountTypes.setValue("Standard User");
        mainPane.add(accountTypes, 1, 1);

        //where to add any special additions
        GridPane specialtyGrid = new GridPane();
        specialtyGrid.setHgap(10);
        specialtyGrid.setVgap(10);
        mainPane.add(specialtyGrid, 0, 2, 2, 1);

        Button submit = new Button("Create User");
        mainPane.add(submit,1,3);
        
        //creates the fields outside of the listener so that we can access them
        TextField nameField = new TextField();
        TextArea detailsField = new TextArea();

        // listeners
        submit.setOnAction(e -> submitRecipient()); // default because of standard user being default

        accountTypes.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            if(newValue.equals("Food Pantry")){
                specialtyGrid.add(new Label("Organization: "), 0, 0);
                specialtyGrid.add(new Label("Details for users: "), 0, 1);

                specialtyGrid.add(nameField, 1, 0);
                specialtyGrid.add(detailsField, 1, 1);

                submit.setOnAction(e -> submitFoodPantry(nameField.getText(), detailsField.getText()));
            } else {
                specialtyGrid.getChildren().clear();
                
                if(newValue.equals("Donor")){
                    submit.setOnAction(e -> submitDonor());
                } else { // standard user/recipient case
                    submit.setOnAction(e -> submitRecipient());
                }
            }
            // update window height
            primaryStage.sizeToScene();
        });

        // Create the scene and set it on the stage
        Scene scene = new Scene(mainPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Creates a Food Pantry and has the UserBase handle it
     * DO NOT PLAY WITH THIS.
     * 
     * @author William Carr
     * @param name Name of the organization
     * @param details Details for the users to see
     */
    private static void submitFoodPantry(String name, String details) {
        FoodPantry newFoodPantry = new FoodPantry(userNameTxt, pwTxt, longitudeValue, latitudeValue, name, details);
        UserBase.foodPantrys.put(newFoodPantry.getUsername(), newFoodPantry);
        UserBase.serializeFoodPantrys();
        //makes sure that the app is updated in all necessary areas
        UserBase.providers.put(newFoodPantry.getUsername(), newFoodPantry);
        UserBase.users.put(newFoodPantry.getUsername(), newFoodPantry);
        GUIdriver.addUser(newFoodPantry);

        Login_Frame.getBackButton().fire();
    }

    /**
     * Creates a Donor and has the UserBase handle it
     * DO NOT PLAY WITH THIS.
     * 
     * @author William Carr
     */
    private static void submitDonor(){
        Donor newDonor = new Donor(userNameTxt, pwTxt, longitudeValue, latitudeValue);
        UserBase.donors.put(newDonor.getUsername(), newDonor);
        UserBase.serializeDonors();
        UserBase.users.put(newDonor.getUsername(), newDonor); // needed so that the app can use the new user without closing the app and starting it up again
        GUIdriver.addUser(newDonor);
        Login_Frame.getBackButton().fire();
    }
    
    /**
     * Creates a Recipient and has the UserBase handle it
     * DO NOT PLAY WITH THIS.
     * 
     * @author William Carr
     */
    private static void submitRecipient(){
        Recipient newRecipient = new Recipient(userNameTxt, pwTxt, longitudeValue, latitudeValue);
        UserBase.recipients.put(newRecipient.getUsername(), newRecipient);
        UserBase.serializeRecipients();
        UserBase.users.put(newRecipient.getUsername(), newRecipient); // needed so that the app can use the new user without closing the app and starting it up again
        GUIdriver.addUser(newRecipient); 
        Login_Frame.getBackButton().fire();
    }

    /**
     * A custom exception to show our message, must be static as we have no instance of CreateUserFrame
     * 
     * @author William Carr
     */
    private static class UserAlreadyExistsException extends Exception {

        private String newUsername;

        /**
         * Creates a UserAlreadyExistsException
         * @param username The username that already exists
         */
        public UserAlreadyExistsException(String username) {
            newUsername = getNewUsername(username);
        }

        /**
         * Gets the username suggested by the system.
         * 
         * @return The new suggested username.
         */
        public String getSuggestedUsername() {
            return newUsername;
        }

        /**
         * Creates a new username based on the old, and makes sure the database does not have it
         * @param username The pre-existing username
         * @return A new username that is in the system
         */
        private String getNewUsername(String username) {
            boolean duplicate = true;
            int num = 0;
            while(duplicate && num <= Integer.MAX_VALUE) {
                if(USER_MAP.containsKey(username+num))
                    num++;
                else //userbase does not have a user with this username and number
                    duplicate = false;
            }
            return username+num;
        }
    }
}
