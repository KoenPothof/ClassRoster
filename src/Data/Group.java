package Data;

public class Group {
    private String classCode;

    public Group(String classCode) {
        this.classCode = classCode;
    }

    @Override
    public String toString() {
        return classCode;
    }
}
