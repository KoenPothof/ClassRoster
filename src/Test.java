import Data.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalTime;

public class Test {
    public static void main(String[] args) {

       Lesson lesson1 = new Lesson(Subject.math, new Teacher("Johan Shortstruik"), new Classroom(102),new Group("A1"), LocalTime.of(8,30),LocalTime.of(9,20));

        System.out.println(lesson1.toString());

    }

}
