package GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class GUIboxen extends Application{
    private String value81;
    private String value82;
    private String value83;
    private String value84;


    public void start(Stage stage) {

        //ComboBoxen8 Start

        ComboBox<String> subjectBox8 = new ComboBox<>();
        subjectBox8.getItems().add("IPJ");
        subjectBox8.getItems().add("Wiskunde");
        subjectBox8.getItems().add("Proftaak");

        subjectBox8.setOnAction(e -> {
            value81 = (String) subjectBox8.getValue();
            if(value81 !=null) {
                System.out.println(value81);
            }
        });

        ComboBox<String> teacherBox8 = new ComboBox<>();
        teacherBox8.getItems().add("Joehan");
        teacherBox8.getItems().add("Joelie");
        teacherBox8.getItems().add("Robijn");
        teacherBox8.getItems().add("Potter");
        teacherBox8.getItems().add("EddLose");
        teacherBox8.getItems().add("Shans");


        teacherBox8.setOnAction(e -> {
            value82 = (String) teacherBox8.getValue();
            if(value82 !=null) {
                System.out.println(value82);
            }
        });

        ComboBox<String> classroomBox8 = new ComboBox<>();
        classroomBox8.getItems().add("La134");
        classroomBox8.getItems().add("La136");
        classroomBox8.getItems().add("Ld112");

        classroomBox8.setOnAction(e -> {
            value83 = (String) classroomBox8.getValue();
            if(value83 !=null) {
                System.out.println(value83);
            }
        });

        ComboBox<String> classBox8 = new ComboBox<>();
        classBox8.getItems().add("A1");
        classBox8.getItems().add("A2");
        classBox8.getItems().add("A3");
        classBox8.getItems().add("A4");

        classBox8.setOnAction(e -> {
            value84 = (String) classBox8.getValue();
            if(value84 !=null) {
                System.out.println(value84);
            }
        });

        ComboBox[] comboBoxes8 = {subjectBox8, teacherBox8, classroomBox8, classBox8};

//Comboboxen8 End



        VBox vbox = new VBox(subjectBox8, teacherBox8, classroomBox8, classBox8);
        vbox.setSpacing(5);

        Scene scene = new Scene(vbox, 1000, 1100);
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        Application.launch(args);
    }

}
