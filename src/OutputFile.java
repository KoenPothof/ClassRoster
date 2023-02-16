import Data.Group;
import Data.Lesson;
import Data.SchoolData;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class OutputFile {

    public void save(String filename, ArrayList<Lesson> lessons){
        try(PrintWriter pw = new PrintWriter(filename)){
            for (Lesson data: lessons) {
                System.out.println("Saved: " + data.toString());
                pw.print(data);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
