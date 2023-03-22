package GUI;

import Utilities.FileConverter;
import javafx.animation.AnimationTimer;
import Data.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import map.Map;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class AlternativeGui extends Application {

    private final FileConverter fileConverter = new FileConverter("data.txt");

    private final ArrayList<ComboBox<String>> comboBoxArrayList = new ArrayList<>();
    private final ArrayList<Label> labelArrayList = new ArrayList<>();
    private final ArrayList<Button> deleteButtonsArrayList = new ArrayList<>();
    private final ArrayList<ComboBox<String>> addComboBoxesArrayList = new ArrayList<>();
    private final Integer[] addCheck = {0, 0, 0, 0, 0};

    private ArrayList<String[]> list;

    private ArrayList<Lesson> lessons = new ArrayList<>();

    private static Time[] times;
    private static Subject[] subjects;
    private static Teacher[] teachers;
    private static Classroom[] classrooms;
    private static Group[] groups;

    private HashMap<String, Integer> dataHashMap;


    private final VBox vBoxTimeEdit = new VBox();
    private final VBox vBoxSubjectEdit = new VBox();
    private final VBox vBoxTeacherEdit = new VBox();
    private final VBox vBoxLocationEdit = new VBox();
    private final VBox vBoxGroupEdit = new VBox();
    private final VBox vBoxDeleteAddEdit = new VBox();

    private final VBox[] vBoxesEdit = {vBoxTimeEdit, vBoxSubjectEdit, vBoxTeacherEdit, vBoxLocationEdit, vBoxGroupEdit};

    private final HBox hBoxEdit = new HBox();


    private final VBox vBoxTimeRooster = new VBox();
    private final VBox vBoxSubjectRooster = new VBox();
    private final VBox vBoxTeacherRooster = new VBox();
    private final VBox vBoxLocationRooster = new VBox();
    private final VBox vBoxGroupRooster = new VBox();

    private final VBox[] vBoxesRooster = {vBoxTimeRooster, vBoxSubjectRooster, vBoxTeacherRooster, vBoxLocationRooster, vBoxGroupRooster};

    private final HBox hBoxRooster = new HBox();

    public static void main(String[] args) {
        launch();
    }

    private Map map;
    private ResizableCanvas canvas;
    private boolean simulationOn = false;

    @Override
    public void start(Stage primaryStage) throws Exception {

        map = new Map("map/project.json");

        lessons = fileConverter.loadLessons();
        times = fileConverter.getTimes();
        subjects = fileConverter.getSubjects();
        teachers = fileConverter.getTeachers();
        classrooms = fileConverter.getClassrooms();
        groups = fileConverter.getGroups();
        dataHashMap = fileConverter.getDataHashMap();
        list = fileConverter.getList();


        // --- Labels ---

        topLabelsCreate(vBoxTimeEdit, vBoxSubjectEdit, vBoxTeacherEdit, vBoxLocationEdit, vBoxGroupEdit);

        Label DeleteAddLabelEdit = new Label("add / delete");
        Label DeleteAddLineEdit = new Label("----------");
        vBoxDeleteAddEdit.getChildren().add(DeleteAddLabelEdit);
        vBoxDeleteAddEdit.getChildren().add(DeleteAddLineEdit);

        topLabelsCreate(vBoxTimeRooster, vBoxSubjectRooster, vBoxTeacherRooster, vBoxLocationRooster, vBoxGroupRooster);

        // --- ------ ---


        // --- edit tab ---

        // gives the vBoxes a border
        for (VBox vBox : Arrays.asList(vBoxTimeEdit, vBoxSubjectEdit, vBoxTeacherEdit, vBoxLocationEdit, vBoxGroupEdit, vBoxDeleteAddEdit)) {
            vBox.setStyle("-fx-border-color: #dcdcdc ");
            vBox.setAlignment(Pos.BASELINE_CENTER);
        }

        addButtonRow();

        // makes all the comboBoxes and buttons with the correct values
        for (int i = 0; i < lessons.size(); i++) {
            comboBoxesAdd();
        }

        hBoxEdit.getChildren().addAll(vBoxTimeEdit, vBoxSubjectEdit, vBoxTeacherEdit, vBoxLocationEdit, vBoxGroupEdit, vBoxDeleteAddEdit);
        hBoxEdit.setSpacing(8);

        // --- ---- --- ---


        // --- rooster tab ---

        // gives the vBoxes a border
        for (VBox vBox : Arrays.asList(vBoxTimeRooster, vBoxSubjectRooster, vBoxTeacherRooster, vBoxLocationRooster, vBoxGroupRooster)) {
            vBox.setStyle("-fx-border-color: #dcdcdc ");
            vBox.setAlignment(Pos.BASELINE_CENTER);
        }

        // makes all the Labes with the correct values
        for (int i = 0; i < lessons.size(); i++) {
            labelsAdd();
        }

        hBoxRooster.getChildren().addAll(vBoxTimeRooster, vBoxSubjectRooster, vBoxTeacherRooster, vBoxLocationRooster, vBoxGroupRooster);
        hBoxRooster.setSpacing(8);

        // --- ------- --- ---


        // --- BorderPanes ---

        BorderPane roosterBorderPane = new BorderPane();
        roosterBorderPane.setCenter(hBoxRooster);

        BorderPane editBorderPane = new BorderPane();
        editBorderPane.setCenter(hBoxEdit);

        BorderPane simulationPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), simulationPane);
        simulationPane.setCenter(canvas);

        // --- ----------- ---


        // --- simulation tab ---

        FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());
        new AnimationTimer() {
            long last = -1;

            @Override
            public void handle(long now) {
                if (last == -1)
                    last = now;
                update((now - last) / 10000.0);
                last = now;
                draw(g2d);
            }
        }.start();

        draw(g2d);

        // --- ---------- --- ---


        // --- TabPane ---

        TabPane roostermodule = new TabPane();
        roostermodule.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Tab roosterTab = new Tab("rooster");
        roosterTab.setContent(roosterBorderPane);

        Tab editTab = new Tab("edit");
        editTab.setContent(editBorderPane);
        editTab.setOnSelectionChanged(e -> {
            refresh();
//            System.out.println("editTab changed"); // debug code
        });


        Tab simulationTab = new Tab("simulatie");
        simulationTab.setContent(simulationPane);
        simulationPane.setOnMouseMoved(e -> {
            simulationOn = true;
        });
        simulationTab.setOnSelectionChanged(e ->{
            simulationOn = false;
        });

        roostermodule.getTabs().addAll(roosterTab, editTab, simulationTab);


        // --- ------- ---


        Image icon = new Image("gui_resources/burger.png");
        primaryStage.getIcons().add(icon);

        Scene scene = new Scene(roostermodule, 700, 500);
        scene.getStylesheets().add("gui_resources/style.css");

        refresh();

        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.setMaximized(true);
        primaryStage.setTitle("roostermodule");
        primaryStage.show();
    }

    public void draw(FXGraphics2D g) {
        g.setBackground(Color.white);
        g.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());
        if (simulationOn) {
            map.draw(g);
        }
    }


    public void update(double deltaTime) {


    }

    private void topLabelsCreate(VBox vBoxTimeEdit, VBox vBoxSubjectEdit, VBox vBoxTeacherEdit, VBox vBoxLocationEdit, VBox vBoxGroupEdit) {
        Label timeLabelEdit = new Label("time");
        Label timeLineEdit = new Label("----------");
        vBoxTimeEdit.getChildren().add(timeLabelEdit);
        vBoxTimeEdit.getChildren().add(timeLineEdit);

        Label subjectLabelEdit = new Label("subject");
        Label subjectLineEdit = new Label("----------");
        vBoxSubjectEdit.getChildren().add(subjectLabelEdit);
        vBoxSubjectEdit.getChildren().add(subjectLineEdit);

        Label teacherLabelEdit = new Label("teacher");
        Label teacherLineEdit = new Label("----------");
        vBoxTeacherEdit.getChildren().add(teacherLabelEdit);
        vBoxTeacherEdit.getChildren().add(teacherLineEdit);

        Label locationLabelEdit = new Label("location");
        Label locationLineEdit = new Label("----------");
        vBoxLocationEdit.getChildren().add(locationLabelEdit);
        vBoxLocationEdit.getChildren().add(locationLineEdit);

        Label groupLabelEdit = new Label("group");
        Label groupLineEdit = new Label("----------");
        vBoxGroupEdit.getChildren().add(groupLabelEdit);
        vBoxGroupEdit.getChildren().add(groupLineEdit);
    }

    private ArrayList<String[]> arraySort(ArrayList<String[]> data) {
        ArrayList<String> howToSort = new ArrayList<>();
        ArrayList<String[]> sortedData = new ArrayList<>();
        for (int i = 5; i < data.size(); i++) {
            howToSort.add(data.get(i)[0] + "@" + i);
//            System.out.println(data.get(i)[0] + "@" + i); // debug code
        }
        Collections.sort(howToSort);
        for (int i = 0; i < 5; i++) {
            sortedData.add(data.get(i));
        }
        for (int i = 0; i < howToSort.size(); i++) {
            sortedData.add(data.get(Integer.parseInt(howToSort.get(i).split("@", 0)[1])));
        }
//        // debug code
//        for (int i = 0; i < sortedData.size(); i++) {
//            for (int j = 0; j < sortedData.get(i).length; j++) {
//                System.out.println(sortedData.get(i)[j]);
//            }
//            System.out.println();
//        }

        return sortedData;
    }

    private void sort() {
        ArrayList<String> howToSort = new ArrayList<>();
        ArrayList<Lesson> beforeSortLessons = new ArrayList<>(lessons);
        for (int i = 0; i < lessons.size(); i++) {
            howToSort.add(lessons.get(i).toString() + "@" + i);
        }
        Collections.sort(howToSort);
        for (int i = 0; i < howToSort.size(); i++) {
            lessons.set(i, beforeSortLessons.get(Integer.parseInt(howToSort.get(i).split("@", 0)[1])));
        }
    }


    private void refresh() {
//        System.out.println("refresh"); // debug code
        sort();
        for (int i = 0; i < lessons.size(); i++) {

            String time = lessons.get(i).getTime().toString();
            String subject = lessons.get(i).getSubject().toString();
            String teacher = lessons.get(i).getTeacher().toString();
            String classroom = lessons.get(i).getClassroom().toString();
            String group = lessons.get(i).getGroup().toString();
            // do something with the data
            int alpha = i * 5;
            labelArrayList.get(alpha).setText(time);
            labelArrayList.get(alpha + 1).setText(subject);
            labelArrayList.get(alpha + 2).setText(teacher);
            labelArrayList.get(alpha + 3).setText(classroom);
            labelArrayList.get(alpha + 4).setText(group);

            comboBoxArrayList.get(alpha).setValue(time);
            comboBoxArrayList.get(alpha + 1).setValue(subject);
            comboBoxArrayList.get(alpha + 2).setValue(teacher);
            comboBoxArrayList.get(alpha + 3).setValue(classroom);
            comboBoxArrayList.get(alpha + 4).setValue(group);
        }

    }


    private void comboBoxesAdd() {
        for (int j = 0; j < 5; j++) {
            comboBoxArrayList.add(new ComboBox<>(FXCollections.observableArrayList(list.get(j))));
        }

        int comboBoxId = comboBoxArrayList.size() - 5;
        vBoxesEdit[0].getChildren().add(comboBoxArrayList.get(comboBoxId));
        vBoxesEdit[1].getChildren().add(comboBoxArrayList.get(comboBoxId + 1));
        vBoxesEdit[2].getChildren().add(comboBoxArrayList.get(comboBoxId + 2));
        vBoxesEdit[3].getChildren().add(comboBoxArrayList.get(comboBoxId + 3));
        vBoxesEdit[4].getChildren().add(comboBoxArrayList.get(comboBoxId + 4));

        int lessonId = comboBoxId / 5;
        comboBoxArrayList.get(comboBoxId).setValue(lessons.get(lessonId).getTime().toString());
        comboBoxArrayList.get(comboBoxId).setOnAction(e -> lessons.get(lessonId).setTime(times[dataHashMap.get(comboBoxArrayList.get(comboBoxId).getValue())]));

        comboBoxArrayList.get(comboBoxId + 1).setValue(lessons.get(lessonId).getSubject().toString());
        comboBoxArrayList.get(comboBoxId + 1).setOnAction(e -> lessons.get(lessonId).setSubject(subjects[dataHashMap.get(comboBoxArrayList.get(comboBoxId + 1).getValue())]));

        comboBoxArrayList.get(comboBoxId + 2).setValue(lessons.get(lessonId).getTeacher().toString());
        comboBoxArrayList.get(comboBoxId + 2).setOnAction(e -> lessons.get(lessonId).setTeacher(teachers[dataHashMap.get(comboBoxArrayList.get(comboBoxId + 2).getValue())]));

        comboBoxArrayList.get(comboBoxId + 3).setValue(lessons.get(lessonId).getClassroom().toString());
        comboBoxArrayList.get(comboBoxId + 3).setOnAction(e -> lessons.get(lessonId).setClassroom(classrooms[dataHashMap.get(comboBoxArrayList.get(comboBoxId + 3).getValue())]));

        comboBoxArrayList.get(comboBoxId + 4).setValue(lessons.get(lessonId).getGroup().toString());
        comboBoxArrayList.get(comboBoxId + 4).setOnAction(e -> lessons.get(lessonId).setGroup(groups[dataHashMap.get(comboBoxArrayList.get(comboBoxId + 4).getValue())]));

        deleteButtonAdd();
    }

    private void labelsAdd() {
        for (int j = 0; j < 5; j++) {
            labelArrayList.add(new Label());
        }

        int labelId = labelArrayList.size() - 5;
        labelArrayList.get(labelId).setText(lessons.get(labelId / 5).getTime().toString());
        labelArrayList.get(labelId + 1).setText(lessons.get(labelId / 5).getSubject().toString());
        labelArrayList.get(labelId + 2).setText(lessons.get(labelId / 5).getTeacher().toString());
        labelArrayList.get(labelId + 3).setText(lessons.get(labelId / 5).getClassroom().toString());
        labelArrayList.get(labelId + 4).setText(lessons.get(labelId / 5).getGroup().toString());
        if (labelId % 2 == 0) {
            for (int i = labelId; i < labelId + 5; i++) {
                labelArrayList.get(i).setStyle("-fx-background-color: #dddddd");
            }
        }
        vBoxesRooster[0].getChildren().add(labelArrayList.get(labelId));
        vBoxesRooster[1].getChildren().add(labelArrayList.get(labelId + 1));
        vBoxesRooster[2].getChildren().add(labelArrayList.get(labelId + 2));
        vBoxesRooster[3].getChildren().add(labelArrayList.get(labelId + 3));
        vBoxesRooster[4].getChildren().add(labelArrayList.get(labelId + 4));
    }

    private void deleteButtonAdd() {
        deleteButtonsArrayList.add(new Button("delete"));
        int buttonId = deleteButtonsArrayList.size() - 1;
        vBoxDeleteAddEdit.getChildren().add(deleteButtonsArrayList.get(buttonId));
        deleteButtonsArrayList.get(buttonId).setOnAction(e -> {
//            data.remove(buttonId + 5);
            lessons.remove(buttonId);
            for (int k = 0; k < vBoxesEdit.length; k++) {
                vBoxesEdit[k].getChildren().remove(vBoxesEdit[k].getChildren().size() - 1);
                vBoxesRooster[k].getChildren().remove(vBoxesRooster[k].getChildren().size() - 1);
            }
            vBoxDeleteAddEdit.getChildren().remove(vBoxDeleteAddEdit.getChildren().size() - 1);
            int size = labelArrayList.size() - 1;
            for (int j = size; j > size - 5; j--) {
                labelArrayList.remove(j);
                comboBoxArrayList.remove(j);
            }
            deleteButtonsArrayList.remove(deleteButtonsArrayList.size() - 1);
            refresh();
        });
    }

    private void addButtonRow() {
        for (int i = 0; i < 5; i++) {
            addComboBoxesArrayList.add(new ComboBox<>(FXCollections.observableArrayList(list.get(i))));
            vBoxesEdit[i].getChildren().add(addComboBoxesArrayList.get(i));
        }
        Button addButton = new Button("add");
        vBoxDeleteAddEdit.getChildren().add(addButton);

        for (int i = 0; i < addComboBoxesArrayList.size(); i++) {
            int finalI = i;
            addComboBoxesArrayList.get(i).setOnAction(e -> {
                addCheck[finalI] = 1;
//                for (int j = 0; j < addCheck.length; j++) {
//                    System.out.println(addCheck[j]); // debug code
//                }
            });
        }

        addButton.setOnAction(e -> {
            int alpha = 0;
            for (int i = 0; i < addCheck.length; i++) {
                alpha = alpha + addCheck[i];
            }
            if (alpha == 5) {
                String[] beta = {"", "", "", "", ""};
                for (int j = 0; j < addComboBoxesArrayList.size(); j++) {
                    beta[j] = addComboBoxesArrayList.get(j).getValue();
                }
                lessons.add(new Lesson(
                        times[dataHashMap.get(beta[0])],
                        subjects[dataHashMap.get(beta[1])],
                        teachers[dataHashMap.get(beta[2])],
                        classrooms[dataHashMap.get(beta[3])],
                        groups[dataHashMap.get(beta[4])]
                ));
                comboBoxesAdd();
                labelsAdd();
                refresh();
                for (int j = 0; j < addCheck.length; j++) {
                    addCheck[j] = 0;
                    addComboBoxesArrayList.get(j).setValue("");
                }
            }
        });
    }

    @Override
    public void stop() {
//        System.out.println("\n closing application"); // debug code
        sort();
        fileConverter.save(lessons);
    }
}