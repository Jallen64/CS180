import java.io.Serializable;

/**
 * Created by jaall on 3/31/2017.
 */
public class ParseException extends Exception implements Serializable {

    public ParseException(String msg){
        super(msg);
    }

}
