package Data;

import java.util.ArrayList;

public class Classroom {
    private final ArrayList<String> classRooms;

    public Classroom() {
        classRooms = new ArrayList<>();
    }

    public void add(String classRoom) {
        this.classRooms.add(classRoom);
    }

    public ArrayList<String> get() {
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
