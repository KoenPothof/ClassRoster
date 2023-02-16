import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileOutput {

    public void save(String filename, ArrayList<String> data){  // methode om naar file
        try(PrintWriter pw = new PrintWriter(filename)) {
            for (String s: data) {
                System.out.println("Saved: " + s); // debug code
                pw.println(data);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
