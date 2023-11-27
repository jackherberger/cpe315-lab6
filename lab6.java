import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class lab6 {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Usage java Main <filename>");
            System.exit(1);
        }
        String filename = args[0];
        String scriptname;
        ArrayList<String> scriptLines = new ArrayList<>();
        if (args.length > 1) {
            scriptname = args[1];   
            Scanner scriptReader = new Scanner(new FileReader(scriptname)); 
        }
       
    }
}
