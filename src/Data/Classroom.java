package Data;

import java.util.ArrayList;
import java.util.List;

public class Classroom {
    private final List<String> classRooms;

    public Classroom() {
        classRooms = new ArrayList<>();
    }

    public void add(String classRoom) {
        this.classRooms.add(classRoom);
    }

    public List<String> get() {
        return classRooms;
    }

    @Override
    public String toString() {
        String returnString = "";
        for (String classRoom : classRooms) {
            returnString = returnString + classRoom + " ";
        }
        return returnString;
    }
}
