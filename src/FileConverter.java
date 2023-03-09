import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileConverter {

    public void save(String filename, List<String> data, List<String> data2){  // methode om naar file
        try(PrintWriter pw = new PrintWriter(filename)) {
            for (int i = 0; i < data.size(); i++) {
//                System.out.println("Saved: " + s); // debug code
                pw.print(data.get(i)+" ");
            }
            pw.println();
            for (int i = 0; i < data2.size(); i++) {
//                System.out.println("Saved: " + s); // debug code
                pw.print(data2.get(i)+" ");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> load(String filename){
        List<String> list = new ArrayList<>();
        File file = new File(filename);

        try (Scanner input = new Scanner(file)){
            while (input.hasNext()){
                String data = input.nextLine();
                System.out.println("Loaded: " + data);;
                list.add(data);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
