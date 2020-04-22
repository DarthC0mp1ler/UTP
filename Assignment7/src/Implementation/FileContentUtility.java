package Implementation;

import java.io.InputStream;
import java.util.Scanner;

public class FileContentUtility {

    public static boolean contains(InputStream input, String content, int size){
        Scanner scanner = new Scanner( input);
        String result = scanner.findWithinHorizon(content,size);
        scanner.close();
        return result != null;
    }

}
