import java.io.*;
import java.util.HashMap;
/**
 * serializes and deserializes users
 * @author Julius A. Leone
 * @version 4.22.2023
 */
abstract public class UserBase {
    public static HashMap<String, User> users= new HashMap<String, User>();
    public static HashMap<String, Provider> providers= new HashMap<String, Provider>();
    public static HashMap<String, Donor> donors = new HashMap<String, Donor>();
    public static HashMap<String, Recipient> recipients = new HashMap<String, Recipient>();
    public static HashMap<String, FoodPantry> foodPantrys = new HashMap<String, FoodPantry>();

    static{
        try{
            FileInputStream fs = new FileInputStream("ser/donors.ser");
            ObjectInputStream is = new ObjectInputStream(fs);
            donors = (HashMap<String, Donor>)is.readObject();
            is.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        try{
            FileInputStream fs = new FileInputStream("ser/recipients.ser");
            ObjectInputStream is = new ObjectInputStream(fs);
            recipients = (HashMap<String, Recipient>)is.readObject();
            is.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        try{
            FileInputStream fs = new FileInputStream("ser/foodPantrys.ser");
            ObjectInputStream is = new ObjectInputStream(fs);
            foodPantrys = (HashMap<String, FoodPantry>)is.readObject();
            is.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        
        users.putAll(donors);
        users.putAll(recipients);
        users.putAll(foodPantrys);
        providers.putAll(foodPantrys);

    }


    public static void serializeDonors(){
        try{
            FileOutputStream fs = new FileOutputStream("ser/donors.ser");
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(donors);
            os.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static void serializeRecipients(){
        try{
            FileOutputStream fs = new FileOutputStream("ser/recipients.ser");
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(recipients);
            os.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static void serializeFoodPantrys(){
        try{
            FileOutputStream fs = new FileOutputStream("ser/foodPantrys.ser");
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(foodPantrys);
            os.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * Serializes all users in the event that the type is unknown.
     * 
     * @author William Carr
     */
    public static void serializeUsers() {
        serializeDonors();
        serializeRecipients();
        serializeFoodPantrys();
    }
    
}
