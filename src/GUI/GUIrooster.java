package GUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class GUIrooster extends Application {
    public Scene scene2;
    public GUIboxrooster guIboxrooster;
    public Label time = new Label("8.30-9.20");
    public Label subject = new Label("");
    public Label teacher = new Label("");
    public Label location = new Label("");
    public Label group = new Label("");
    public Label time1 = new Label("9.20-10.10");
    public Label subject1 = new Label("");
    public Label teacher1 = new Label("");
    public Label location1 = new Label("");
    public Label group1 = new Label("");
    public Label time2 = new Label("10.10-11.00");
    public Label subject2 = new Label("");
    public Label teacher2 = new Label("");
    public Label location2 = new Label("");
    public Label group2 = new Label("");
    public Label time3 = new Label("11.00-11.20");
    public Label subject3 = new Label("Pauze");
    public Label teacher3 = new Label("-");
    public Label location3 = new Label("-");
    public Label group3 = new Label("-");
    public Label time4 = new Label("11.20-12.10");
    public Label subject4 = new Label("");
    public Label teacher4 = new Label("");
    public Label location4 = new Label("");
    public Label group4 = new Label("");
    public Label time5 = new Label("12.10-13.00");
    public Label subject5 = new Label("");
    public Label teacher5 = new Label("");
    public Label location5 = new Label("");
    public Label group5 = new Label("");
    public Label time6 = new Label("13.00-13.20");
    public Label subject6 = new Label("Pauze");
    public Label teacher6 = new Label("-");
    public Label location6 = new Label("-");
    public Label group6 = new Label("-");
    public Label time7 = new Label("13.20-14.10");
    public Label subject7 = new Label("");
    public Label teacher7 = new Label("");
    public Label location7 = new Label("");
    public Label group7 = new Label("");
    public Label time8 = new Label("14.10-15.00");
    public Label subject8 = new Label("");
    public Label teacher8 = new Label("");
    public Label location8 = new Label("");
    public Label group8 = new Label("");
    public ArrayList<Label> labels = new ArrayList<>();
    @Override
    public void start(Stage stage) {
        stage.setTitle("Hello world!");
        VBox vBoxrooster = new VBox();
        Label labelrooster = new Label("Roosterview");
        Button editButton = new Button("EDIT");

        editButton.setOnAction(e -> {

        });

        HBox hBoxrooster = new HBox();
        hBoxrooster.getChildren().addAll(labelrooster, editButton);
        GridPane lessons = new GridPane();

        Label timeHead = new Label("Time");
        Label subjectHead = new Label("Subject");
        Label teacherHead = new Label("Teacher");
        Label locationHead = new Label("Location");
        Label groupHead = new Label("group");

        ArrayList<Label> heads = new ArrayList<>();
        heads.add(timeHead);
        heads.add(subjectHead);
        heads.add(teacherHead);
        heads.add(locationHead);
        heads.add(groupHead);
        for (Label label:heads
        ) {
            label.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        }
        HBox hBox1rooster = new HBox();
        hBox1rooster.getChildren().addAll(timeHead, subjectHead, teacherHead, locationHead, groupHead);
        hBox1rooster.setSpacing(30);


        labels.add(time);
        labels.add(subject);
        labels.add(teacher);
        labels.add(location);
        labels.add(group);
        labels.add(time1);
        labels.add(subject1);
        labels.add(teacher1);
        labels.add(location1);
        labels.add(group1);
        labels.add(time2);
        labels.add(subject2);
        labels.add(teacher2);
        labels.add(location2);
        labels.add(group2);
        labels.add(time3);
        labels.add(subject3);
        labels.add(teacher3);
        labels.add(location3);
        labels.add(group3);
        labels.add(time4);
        labels.add(subject4);
        labels.add(teacher4);
        labels.add(location4);
        labels.add(group4);
        labels.add(time5);
        labels.add(subject5);
        labels.add(teacher5);
        labels.add(location5);
        labels.add(group5);
        labels.add(time6);
        labels.add(subject6);
        labels.add(teacher6);
        labels.add(location6);
        labels.add(group6);
        labels.add(time7);
        labels.add(subject7);
        labels.add(teacher7);
        labels.add(location7);
        labels.add(group7);
        labels.add(time8);
        labels.add(subject8);
        labels.add(teacher8);
        labels.add(location8);
        labels.add(group8);

        lessons.add(timeHead, 0, 0);
        lessons.add(subjectHead, 1, 0);
        lessons.add(teacherHead, 2, 0);
        lessons.add(locationHead, 3, 0);
        lessons.add(groupHead, 4, 0);

        int i = 0;
        int j = 1;
        boolean k = true;
        for (Label label: labels
        ) {
            lessons.add(label, i, j);
            i++;
            if(k){
                label.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            }
            if(i == 5){
                j++;
                if(k){
                    k = false;
                }
                else if(!k){
                    k = true;
                }
                i = 0;
            }

        }

        lessons.setHgap(50);
        vBoxrooster.getChildren().addAll(hBoxrooster, hBox1rooster, lessons);
        scene2 = new Scene(vBoxrooster);
        stage.setScene(scene2);
        stage.show();
    }

    public static void main(String[] args) {
        launch(GUIrooster.class);
    }
}
