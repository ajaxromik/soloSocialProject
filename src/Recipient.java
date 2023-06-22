/**
 * A user seeking to recieve donations from providers.
 * @author Julius A. Leone
 */
public class Recipient extends User{
    private static final long serialVersionUID = -4343524323425321L;

    public Recipient(String username, String password, double longitude, double latitude){
        super(username, password, longitude, latitude);
    }

}
