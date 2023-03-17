package Utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileConverter {

    private String filename;

    public FileConverter(String filename) {
        this.filename = filename;
    }

    public void save(ArrayList<String[]> data){  // methode om naar file
        try(PrintWriter pw = new PrintWriter(this.filename)) {
            for (int i = 0; i < data.size(); i++) {
                for (int j = 0; j < data.get(i).length; j++) {
                    pw.print(data.get(i)[j]+"@");
//                    System.out.println("Saved: " + data.get(i)[j]); // debug code
                }
                if (i != data.size()-1) {
                    pw.println();
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<String[]> load(){
        ArrayList<String[]> list = new ArrayList<>();
        File file = new File(this.filename);

        try (Scanner input = new Scanner(file)){
            while (input.hasNext()){
                String data = input.nextLine();
//                System.out.println("Loaded: " + data); // debug code
                list.add(data.split("@", 0));

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}