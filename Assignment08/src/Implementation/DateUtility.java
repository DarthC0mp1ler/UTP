package Implementation;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtility {

    private static final DateFormat DATEFORMAT = new SimpleDateFormat("yyyy-mm-dd");

    public static void serialize(Date date, DataOutputStream outputStream){

        try {
            outputStream.writeUTF(DATEFORMAT.format(date));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Date deserialize(DataInputStream inputStream) {
        try {
            return DATEFORMAT.parse(inputStream.readUTF());
        }catch ( Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
