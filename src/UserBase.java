import java.io.*;
import java.nio.file.ProviderNotFoundException;
import java.util.HashMap;
import java.util.ArrayList;
/**
 * serializes and deserializes users
 * @author Julius A. Leone
 * @version 4.22.2023
 */
abstract public class UserBase {
    public static HashMap<String, User> users= new HashMap<String, User>();
    public static ArrayList<Provider> providers= new ArrayList<Provider>();
    public static HashMap<String, Donor> donors = new HashMap<String, Donor>();
    public static HashMap<String, Recipient> recipients = new HashMap<String, Recipient>();
    public static HashMap<String, FoodPantry> foodPantrys = new HashMap<String, FoodPantry>();

    static{
        try{
            FileInputStream fs = new FileInputStream("donors.ser");
            ObjectInputStream is = new ObjectInputStream(fs);
            donors = (HashMap<String, Donor>)is.readObject();
            is.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        try{
            FileInputStream fs = new FileInputStream("recipients.ser");
            ObjectInputStream is = new ObjectInputStream(fs);
            recipients = (HashMap<String, Recipient>)is.readObject();
            is.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        try{
            FileInputStream fs = new FileInputStream("foodPantrys.ser");
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
        providers.addAll(foodPantrys.values());

    }


    public static void serializeDonors(){
        try{
            FileOutputStream fs = new FileOutputStream("donors.ser");
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
            FileOutputStream fs = new FileOutputStream("recipients.ser");
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
            FileOutputStream fs = new FileOutputStream("foodPantrys.ser");
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(foodPantrys);
            os.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
}
