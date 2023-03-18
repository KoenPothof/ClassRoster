import Data.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class FileConverter {

    private String filename;
    private Time[] times;
    private Subject[] subjects;
    private Teacher[] teachers;
    private Classroom[] classrooms;
    private Group[] groups;

    private HashMap<String, Integer> dataHashMap = new HashMap<>();

    private ArrayList<String[]> list = new ArrayList<>();


    public FileConverter(String filename) {
        this.filename = filename;
        loadData();
    }

    public void save(ArrayList<Lesson> lessons) {  // methode om naar file
        try (PrintWriter pw = new PrintWriter(this.filename)) {

            for (Time time : times) {
                pw.print(time + "@");
            }
            pw.println();
            for (Subject subject : subjects) {
                pw.print(subject + "@");
            }
            pw.println();
            for (Teacher teacher : teachers) {
                pw.print(teacher + "@");
            }
            pw.println();
            for (Classroom classroom : classrooms) {
                pw.print(classroom + "@");
            }
            pw.println();
            for (Group group : groups) {
                pw.print(group + "@");
            }
            pw.println();
            for (int i = 0; i < lessons.size(); i++) {
                pw.print(lessons.get(i).getTime().toString() + "@");
                pw.print(lessons.get(i).getSubject().toString() + "@");
                pw.print(lessons.get(i).getTeacher().toString() + "@");
                pw.print(lessons.get(i).getClassroom().toString() + "@");
                pw.print(lessons.get(i).getGroup().toString() + "@");
                if (i != lessons.size()-1){
                    pw.println();
                }
            }


//            for (int i = 0; i < lessons.size(); i++) {
//                for (int j = 0; j < lessons.get(i).length; j++) {
//                    pw.print(lessons.get(i)[j] + "@");
////                    System.out.println("Saved: " + lessons.get(i)[j]); // debug code
//                }
//
//            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Lesson> loadLessons() {
        ArrayList<Lesson> lessons = new ArrayList<>();
        File file = new File(this.filename);
        String[] data;
        try (Scanner input = new Scanner(file)) {
            for (int i = 0; i < 5; i++) {
                list.add(input.nextLine().split("@", 0));
            }
            while (input.hasNext()) {
                data = input.nextLine().split("@", 0);
//                System.out.println("Loaded: " + data); // debug code
                lessons.add(new Lesson(
                        times[dataHashMap.get(data[0])],
                        subjects[dataHashMap.get(data[1])],
                        teachers[dataHashMap.get(data[2])],
                        classrooms[dataHashMap.get(data[3])],
                        groups[dataHashMap.get(data[4])]
                ));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lessons;
    }

    private void loadData() {
        File file = new File(this.filename);
        String[] data;
        try (Scanner input = new Scanner(file)) {
            data = input.nextLine().split("@", 0);
            times = new Time[data.length];
            for (int i = 0; i < data.length; i++) {
                String datum = data[i];
                times[i] = new Time(datum);
                dataHashMap.put(datum, i);
//                System.out.println("Loaded: " + datum); // debug code
            }
            data = input.nextLine().split("@", 0);
            subjects = new Subject[data.length];
            for (int i = 0; i < data.length; i++) {
                String datum = data[i];
                subjects[i] = new Subject(datum);
                dataHashMap.put(datum, i);
//                System.out.println("Loaded: " + datum); // debug code
            }
            data = input.nextLine().split("@", 0);
            teachers = new Teacher[data.length];
            for (int i = 0; i < data.length; i++) {
                String datum = data[i];
                teachers[i] = new Teacher(datum);
                dataHashMap.put(datum, i);
//                System.out.println("Loaded: " + datum); // debug code
            }
            data = input.nextLine().split("@", 0);
            classrooms = new Classroom[data.length];
            for (int i = 0; i < data.length; i++) {
                String datum = data[i];
                classrooms[i] = new Classroom(datum);
                dataHashMap.put(datum, i);
//                System.out.println("Loaded: " + datum); // debug code
            }
            data = input.nextLine().split("@", 0);
            groups = new Group[data.length];
            for (int i = 0; i < data.length; i++) {
                String datum = data[i];
                groups[i] = new Group(datum);
                dataHashMap.put(datum, i);
//                System.out.println("Loaded: " + datum); // debug code
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Time[] getTimes() {
        return times;
    }

    public Subject[] getSubjects() {
        return subjects;
    }

    public Teacher[] getTeachers() {
        return teachers;
    }

    public Classroom[] getClassrooms() {
        return classrooms;
    }

    public Group[] getGroups() {
        return groups;
    }

    public HashMap<String, Integer> getDataHashMap() {
        return dataHashMap;
    }

    public ArrayList<String[]> getList() {
        return list;
    }
}