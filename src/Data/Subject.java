package Data;

import java.util.ArrayList;

public class Subject {
    private final ArrayList<String> subjects;

    public Subject() {
        subjects = new ArrayList<>();
    }

    public void add(String subject) {
        this.subjects.add(subject);
    }

    public ArrayList<String> get() {
        return subjects;
    }

    @Override
    public String toString() {
        String returnString = "";
        for (String subject : subjects) {
            returnString = returnString + subject + " ";
        }
        return returnString;
    }}

