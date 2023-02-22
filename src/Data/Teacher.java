package Data;

import java.util.ArrayList;

public class Teacher {
    private final ArrayList<String> teachers;

    public Teacher() {
        teachers = new ArrayList<>();
    }

    public void add(String teacher) {
        this.teachers.add(teacher);
    }

    public ArrayList<String> get() {
        return teachers;
    }

    @Override
    public String toString() {
        String returnString = "";
        for (String teacher : teachers) {
            returnString = returnString + teacher + " ";
        }
        return returnString;
    }
}
