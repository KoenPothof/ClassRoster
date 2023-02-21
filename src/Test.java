import Data.*;

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

        FileConverter fileOutput = new FileConverter();
        fileOutput.save("data.txt", group.get(),classroom.get());
        System.out.println(group);
        System.out.println(classroom);
    }

}
