package Utilities;

import Data.*;
import Data.Rooms.Room;
import Data.Rooms.RoomType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class FileConverter {

    private String filename;
    private JsonReader jsonReader;
    private Time[] times;
    private Subject[] subjects;
    private Teacher[] teachers;
    private Room[] classrooms;
    private Group[] groups;
    private ArrayList<Lesson> lessons = new ArrayList<>();
    private HashMap<String, Integer> dataHashMap = new HashMap<>();

    private ArrayList<String[]> list = new ArrayList<>();


    public FileConverter(String filename) {
        this.filename = filename;
        this.jsonReader = new JsonReader();
        loadData();
    }

    public void save() {  // methode om naar file
        sort();
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
            for (Room classroom : classrooms) {
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
                if (i != lessons.size() - 1) {
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

    public void sort() {
        ArrayList<String> howToSort = new ArrayList<>();
        ArrayList<Lesson> beforeSortLessons = new ArrayList<>(lessons);
        for (int i = 0; i < getLessons().size(); i++) {
            howToSort.add(getLessons().get(i).toString() + "@" + i);
        }
        Collections.sort(howToSort);
        for (int i = 0; i < howToSort.size(); i++) {
            lessons.set(i, beforeSortLessons.get(Integer.parseInt(howToSort.get(i).split("@", 0)[1])));
        }
    }

    private void loadData() {
        File file = new File(this.filename);
        String[] data;
        try (Scanner input = new Scanner(file)) {
            data = input.nextLine().split("@", 0);
            times = new Time[data.length];
            list.add(data);
            for (int i = 0; i < data.length; i++) {
                String datum = data[i];
                times[i] = new Time(datum);
                dataHashMap.put(datum, i);
//                System.out.println("Loaded: " + datum); // debug code
            }

            data = input.nextLine().split("@", 0);
            subjects = new Subject[data.length];
            list.add(data);
            for (int i = 0; i < data.length; i++) {
                String datum = data[i];
                subjects[i] = new Subject(datum);
                dataHashMap.put(datum, i);
//                System.out.println("Loaded: " + datum); // debug code
            }

            data = input.nextLine().split("@", 0);
            teachers = new Teacher[data.length];
            list.add(data);
            for (int i = 0; i < data.length; i++) {
                String datum = data[i];
                teachers[i] = new Teacher(datum);
                dataHashMap.put(datum, i);
//                System.out.println("Loaded: " + datum); // debug code
            }

            data = input.nextLine().split("@", 0);
            classrooms = new Room[data.length];
            list.add(data);
            for (int i = 0; i < data.length; i++) {
                String datum = data[i];
                classrooms[i] = new Room(datum, jsonReader.getObjectX()[i], jsonReader.getObjectY()[i], jsonReader.getObjectWidth()[i], jsonReader.getObjectHeight()[i], RoomType.CLASSROOM);
                dataHashMap.put(datum, i);
//                System.out.println("Loaded: " + datum); // debug code
            }

            data = input.nextLine().split("@", 0);
            groups = new Group[data.length];
            list.add(data);
            for (int i = 0; i < data.length; i++) {
                String datum = data[i];
                groups[i] = new Group(datum);
                dataHashMap.put(datum, i);
//                System.out.println("Loaded: " + datum); // debug code
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

    public Room[] getClassrooms() {
        return classrooms;
    }

    public Group[] getGroups() {
        return groups;
    }

    public ArrayList<Lesson> getLessons() {
        return lessons;
    }

    public HashMap<String, Integer> getDataHashMap() {
        return dataHashMap;
    }

    public ArrayList<String[]> getList() {
        return list;
    }

    public JsonReader getJsonReader() {
        return jsonReader;
    }
}