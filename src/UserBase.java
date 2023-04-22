import java.io.*;
import java.util.HashMap;
abstract public class UserBase {
    public static HashMap<String, User> users= new HashMap<String, User>();
    public static HashMap<String, Provider> providers= new HashMap<String, Provider>();

    static{
        try{

        }
        catch(Exception ex){

        }
    }


    public static void serializeDonor(Donor donor){
        try{
            FileOutputStream fs = new FileOutputStream("./csv/donor.ser");
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(donor);
            os.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static void serializeRecipient(Recipient recipient){
        try{
            FileOutputStream fs = new FileOutputStream("./csv/recipient.ser");
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(recipient);
            os.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static void serializeFoodPantry(Provider foodPantry){
        try{
            FileOutputStream fs = new FileOutputStream("./csv/foodPantry.ser");
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(foodPantry);
            os.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }




}
