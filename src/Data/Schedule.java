package Data;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class Schedule implements Serializable {
    private ArrayList<Lesson> lessons;



    public Schedule(ArrayList<Lesson> lessons) {
        this.lessons = lessons;
    }


}
