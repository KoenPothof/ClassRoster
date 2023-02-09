package GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GUIboxrooster extends Application {
    public GUIrooster guirooster = new GUIrooster();
    private String value1;
    private String value2;
    private String value3;
    private String value4;

    @Override
    public void start(Stage stage) {
        Label time = new Label("8.30-9.20");
        Label time1 = new Label("9.20-10.10");
        Label time2 = new Label("10.10-11.00");
        Label time3 = new Label("11.00-11.20");
        Label subject3 = new Label("Pauze");
        Label time4 = new Label("11.20-12.10");
        Label time5 = new Label("12.10-13.00");
        Label time6 = new Label("13.00-13.20");
        Label subject6 = new Label("Pauze");
        Label time7 = new Label("13.20-14.10");
        Label time8 = new Label("14.10-15.00");
        ArrayList<Label> labels = new ArrayList<>();
        labels.add(time);
        labels.add(time1);
        labels.add(time2);
        labels.add(time3);
        labels.add(subject3);
        labels.add(time4);
        labels.add(time5);
        labels.add(time6);
        labels.add(subject6);
        labels.add(time7);
        labels.add(time8);

        Button button = new Button("sex");

        button.setOnAction(e -> {
              guirooster.start(stage);
        });



        ComboBox subjectBox = new ComboBox();
        subjectBox.getItems().add("IPJ");
        subjectBox.getItems().add("Wiskunde");
        subjectBox.getItems().add("Proftaak");

        subjectBox.setOnAction(e -> {
            value1 = (String) subjectBox.getValue();
            if(value1 !=null) {
                if(value1.equals("Wiskunde")){
                    Label Wiskunde = new Label("Wiskunde");
                    guirooster.newLabel(guirooster.subject, Wiskunde);
                }else if(value1.equals("IPJ")){
                    Label IPJ = new Label("IPJ");
                    guirooster.newLabel(guirooster.subject, IPJ);
                }else if(value1.equals("Proftaak")){
                    Label Proftaak = new Label("Proftaak");
                    guirooster.newLabel(guirooster.subject, Proftaak);
                }
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

        HBox hbox = new HBox();
        hbox.setSpacing(100);
        VBox vbox1 = new VBox();
        vbox1.setSpacing(25);
        for (Label labell:
                labels) {
            vbox1.getChildren().add(labell);
        }

        VBox vbox2 = new VBox();
        vbox2.getChildren().addAll(subjectBox);
        VBox vbox3 = new VBox();
        vbox3.getChildren().add(teacherBox);
        VBox vbox4 = new VBox();
        vbox4.getChildren().add(classBox);
        hbox.getChildren().addAll(vbox1, vbox2, vbox3, vbox4, button);

        Scene scene = new Scene(hbox);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}