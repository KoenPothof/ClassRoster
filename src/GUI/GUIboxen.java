package GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class GUIboxen extends Application{
    private String value1;
    private String value2;
    private String value3;
    private String value4;

    public void start(Stage stage) {

        ComboBox subjectBox = new ComboBox();
        subjectBox.getItems().add("IPJ");
        subjectBox.getItems().add("Wiskunde");
        subjectBox.getItems().add("Proftaak");

        subjectBox.setOnAction(e -> {
            value1 = (String) subjectBox.getValue();
            if(value1 !=null) {
                System.out.println(value1);
            }
        });

        ComboBox teacherBox = new ComboBox();
        teacherBox.getItems().add("Joehan");
        teacherBox.getItems().add("Joelie");
        teacherBox.getItems().add("Robijn");
        teacherBox.getItems().add("Potter");
        teacherBox.getItems().add("EddLose");
        teacherBox.getItems().add("Shans");


        teacherBox.setOnAction(e -> {
            value2 = (String) teacherBox.getValue();
            if(value2 !=null) {
                System.out.println(value2);
            }
        });

        ComboBox classroomBox = new ComboBox();
        classroomBox.getItems().add("La134");
        classroomBox.getItems().add("La136");
        classroomBox.getItems().add("Ld112");

        classroomBox.setOnAction(e -> {
            value3 = (String) classroomBox.getValue();
            if(value3 !=null) {
                System.out.println(value3);
            }
        });

        ComboBox classBox = new ComboBox();
        classBox.getItems().add("A1");
        classBox.getItems().add("A2");
        classBox.getItems().add("A3");
        classBox.getItems().add("A4");

        classBox.setOnAction(e -> {
            value4 = (String) classBox.getValue();
            if(value4 !=null) {
                System.out.println(value4);
            }
        });

        VBox vbox = new VBox(subjectBox, teacherBox, classroomBox, classBox);

        Scene scene = new Scene(vbox, 200, 120);
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        Application.launch(args);
    }

}
