import Data.Lesson;
import Data.Schedule;
import Data.Teacher;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Test {
    public static void main(String[] args) {

        Schedule schedule = new Schedule();
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("Data.txt"))){
            output.writeObject(schedule);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
