package Data;

import Utilities.FileConverter;

public class Subject {
    private String subject;
    public static FileConverter fileConverter = new FileConverter("data.txt");

    public Subject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return subject;
    }
}

