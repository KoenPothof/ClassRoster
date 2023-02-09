package Data;

public class Teacher {
    private String name;

    public Teacher(String name) {
        this.name = name;
    }

    public Teacher createTeacher(String name){
        Teacher teacher = new Teacher(name);

        return teacher;
    }

    @Override
    public String toString() {
        return name;
    }
}
