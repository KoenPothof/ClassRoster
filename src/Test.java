import Data.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.RoundingMode;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {

        Group group = new Group();
        Classroom classroom = new Classroom();

        group.add("1");
        group.add("2");
        group.add("3");
        group.add("4");
        group.add("5");

        classroom.add("CLASS1");
        classroom.add("CLASS2");
        classroom.add("CLASS3");
//        classroom.add("CLASS4");

        FileOutput fileOutput = new FileOutput();
        fileOutput.save("data.txt", group.get(),classroom.get());
        System.out.println(group);
        System.out.println(classroom);
    }

}
