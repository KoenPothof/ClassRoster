package GUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GUIboxrooster extends Application {
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
    public Scene scene;
    public GUIrooster set = new GUIrooster();
    private String value1;
    private String value2;
    private String value3;
    private String value4;
    private String value11;
    private String value12;
    private String value13;
    private String value14;
    private String value21;
    private String value22;
    private String value23;
    private String value24;
    private String value31;
    private String value32;
    private String value33;
    private String value34;
    private String value41;
    private String value42;
    private String value43;
    private String value44;
    private String value51;
    private String value52;
    private String value53;
    private String value54;
    private String value61;
    private String value62;
    private String value63;
    private String value64;
    private String value71;
    private String value72;
    private String value73;
    private String value74;
    private String value81;
    private String value82;
    private String value83;
    private String value84;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Hello world!");
        VBox vBoxrooster = new VBox();
        Label labelrooster = new Label("Roosterview");
        Button editButton = new Button("EDIT");

        editButton.setOnAction(e -> {
            stage.setScene(scene);
        });


        HBox hBoxrooster = new HBox();
        hBoxrooster.getChildren().addAll(labelrooster, editButton);
        GridPane lessons = new GridPane();
        lessons.setVgap(35);

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

        int n = 0;
        int j = 1;
        boolean k = true;
        for (Label label: labels
        ) {
            lessons.add(label, n, j);
            n++;
            if(k){
                label.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            }
            if(n == 5){
                j++;
                if(k){
                    k = false;
                }
                else if(!k){
                    k = true;
                }
                n = 0;
            }

        }

        lessons.setHgap(150);
        vBoxrooster.getChildren().addAll(hBoxrooster, hBox1rooster, lessons);

        Label time = new Label("8.30-9.20    ");
        Label time1 = new Label("9.20-10.10  ");
        Label time2 = new Label("10.10-11.00");
        Label time3 = new Label("11.00-11.20");
        Label pauze1 = new Label("Pauze");
        Label streep1 = new Label("-      ");
        Label streep12 = new Label("-     ");
        Label streep13 = new Label("-");
        Label time4 = new Label("11.20-12.10");
        Label time5 = new Label("12.10-13.00");
        Label time6 = new Label("13.00-13.20");
        Label pauze2 = new Label("Pauze");
        Label streep2 = new Label("-      ");
        Label streep21 = new Label("-     ");
        Label streep22 = new Label("-");
        Label time7 = new Label("13.20-14.10");
        Label time8 = new Label("14.10-15.00");
        ArrayList<Label> labels = new ArrayList<>();
        labels.add(time);
        labels.add(time1);
        labels.add(time2);
        labels.add(time3);
        labels.add(pauze1);
        labels.add(time4);
        labels.add(time5);
        labels.add(time6);
        labels.add(pauze2);
        labels.add(time7);
        labels.add(time8);

//ComboBoxen Start

        ComboBox<String> subjectBox = new ComboBox<>();
        subjectBox.getItems().add("IPJ");
        subjectBox.getItems().add("Wiskunde");
        subjectBox.getItems().add("Proftaak");

        subjectBox.setOnAction(e -> {
            value1 = (String) subjectBox.getValue();
                subject.setText(value1);
        });

        ComboBox<String> teacherBox = new ComboBox<>();
        teacherBox.getItems().add("Joehan");
        teacherBox.getItems().add("Joelie");
        teacherBox.getItems().add("Robijn");
        teacherBox.getItems().add("Potter");
        teacherBox.getItems().add("EddLose");
        teacherBox.getItems().add("Shans");


        teacherBox.setOnAction(e -> {
            value2 = (String) teacherBox.getValue();
                teacher.setText(value2);
        });

        ComboBox<String> classroomBox = new ComboBox<>();
        classroomBox.getItems().add("La134");
        classroomBox.getItems().add("La136");
        classroomBox.getItems().add("Ld112");

        classroomBox.setOnAction(e -> {
            value3 = (String) classroomBox.getValue();
                location.setText(value3);
        });

        ComboBox<String> classBox = new ComboBox<>();
        classBox.getItems().add("A1");
        classBox.getItems().add("A2");
        classBox.getItems().add("A3");
        classBox.getItems().add("A4");

        classBox.setOnAction(e -> {
            value4 = (String) classBox.getValue();
                group.setText(value4);
        });

        ComboBox[] comboBoxes = {subjectBox, teacherBox, classroomBox, classBox};

//Comboboxen End
        //ComboBoxen1 Start

        ComboBox<String> subjectBox1 = new ComboBox<>();
        subjectBox1.getItems().add("IPJ");
        subjectBox1.getItems().add("Wiskunde");
        subjectBox1.getItems().add("Proftaak");

        subjectBox1.setOnAction(e -> {
            value11 = (String) subjectBox1.getValue();
                subject1.setText(value11);
        });

        ComboBox<String> teacherBox1 = new ComboBox<>();
        teacherBox1.getItems().add("Joehan");
        teacherBox1.getItems().add("Joelie");
        teacherBox1.getItems().add("Robijn");
        teacherBox1.getItems().add("Potter");
        teacherBox1.getItems().add("EddLose");
        teacherBox1.getItems().add("Shans");


        teacherBox1.setOnAction(e -> {
            value12 = (String) teacherBox1.getValue();
                teacher1.setText(value12);
        });

        ComboBox<String> classroomBox1 = new ComboBox<>();
        classroomBox1.getItems().add("La134");
        classroomBox1.getItems().add("La136");
        classroomBox1.getItems().add("Ld112");

        classroomBox1.setOnAction(e -> {
            value13 = (String) classroomBox1.getValue();
                location1.setText(value13);
        });

        ComboBox<String> classBox1 = new ComboBox<>();
        classBox1.getItems().add("A1");
        classBox1.getItems().add("A2");
        classBox1.getItems().add("A3");
        classBox1.getItems().add("A4");

        classBox1.setOnAction(e -> {
            value14 = (String) classBox1.getValue();
                group1.setText(value14);
        });

        ComboBox[] comboBoxes1 = {subjectBox1, teacherBox1, classroomBox1, classBox1};

//Comboboxen1 End
        //ComboBoxen2 Start

        ComboBox<String> subjectBox2 = new ComboBox<>();
        subjectBox2.getItems().add("IPJ");
        subjectBox2.getItems().add("Wiskunde");
        subjectBox2.getItems().add("Proftaak");

        subjectBox2.setOnAction(e -> {
            value21 = (String) subjectBox2.getValue();
                subject2.setText(value21);
        });

        ComboBox<String> teacherBox2 = new ComboBox<>();
        teacherBox2.getItems().add("Joehan");
        teacherBox2.getItems().add("Joelie");
        teacherBox2.getItems().add("Robijn");
        teacherBox2.getItems().add("Potter");
        teacherBox2.getItems().add("EddLose");
        teacherBox2.getItems().add("Shans");


        teacherBox2.setOnAction(e -> {
            value22 = (String) teacherBox2.getValue();
                teacher2.setText(value22);
        });

        ComboBox<String> classroomBox2 = new ComboBox<>();
        classroomBox2.getItems().add("La134");
        classroomBox2.getItems().add("La136");
        classroomBox2.getItems().add("Ld112");

        classroomBox2.setOnAction(e -> {
            value23 = (String) classroomBox2.getValue();
                location2.setText(value23);
        });

        ComboBox<String> classBox2 = new ComboBox<>();
        classBox2.getItems().add("A1");
        classBox2.getItems().add("A2");
        classBox2.getItems().add("A3");
        classBox2.getItems().add("A4");

        classBox2.setOnAction(e -> {
            value24 = (String) classBox2.getValue();
                group2.setText(value24);
        });

        ComboBox[] comboBoxes2 = {subjectBox2, teacherBox2, classroomBox2, classBox2};

//Comboboxen2 End

    //Combobox3 --> Pauze

        //ComboBoxen4 Start

        ComboBox<String> subjectBox4 = new ComboBox<>();
        subjectBox4.getItems().add("IPJ");
        subjectBox4.getItems().add("Wiskunde");
        subjectBox4.getItems().add("Proftaak");

        subjectBox4.setOnAction(e -> {
            value41 = (String) subjectBox4.getValue();
                subject4.setText(value41);
        });

        ComboBox<String> teacherBox4 = new ComboBox<>();
        teacherBox4.getItems().add("Joehan");
        teacherBox4.getItems().add("Joelie");
        teacherBox4.getItems().add("Robijn");
        teacherBox4.getItems().add("Potter");
        teacherBox4.getItems().add("EddLose");
        teacherBox4.getItems().add("Shans");


        teacherBox4.setOnAction(e -> {
            value42 = (String) teacherBox4.getValue();
                teacher4.setText(value42);
        });

        ComboBox<String> classroomBox4 = new ComboBox<>();
        classroomBox4.getItems().add("La134");
        classroomBox4.getItems().add("La136");
        classroomBox4.getItems().add("Ld112");

        classroomBox4.setOnAction(e -> {
            value43 = (String) classroomBox4.getValue();
                location4.setText(value43);
        });

        ComboBox<String> classBox4 = new ComboBox<>();
        classBox4.getItems().add("A1");
        classBox4.getItems().add("A2");
        classBox4.getItems().add("A3");
        classBox4.getItems().add("A4");

        classBox4.setOnAction(e -> {
            value44 = (String) classBox4.getValue();
                group4.setText(value44);
        });

        ComboBox[] comboBoxes4 = {subjectBox4, teacherBox4, classroomBox4, classBox4};

//Comboboxen4 End
        //ComboBoxen5 Start

        ComboBox<String> subjectBox5 = new ComboBox<>();
        subjectBox5.getItems().add("IPJ");
        subjectBox5.getItems().add("Wiskunde");
        subjectBox5.getItems().add("Proftaak");

        subjectBox5.setOnAction(e -> {
            value51 = (String) subjectBox5.getValue();
                subject5.setText(value51);
        });

        ComboBox<String> teacherBox5 = new ComboBox<>();
        teacherBox5.getItems().add("Joehan");
        teacherBox5.getItems().add("Joelie");
        teacherBox5.getItems().add("Robijn");
        teacherBox5.getItems().add("Potter");
        teacherBox5.getItems().add("EddLose");
        teacherBox5.getItems().add("Shans");


        teacherBox5.setOnAction(e -> {
            value52 = (String) teacherBox5.getValue();
                teacher5.setText(value52);
        });

        ComboBox<String> classroomBox5 = new ComboBox<>();
        classroomBox5.getItems().add("La134");
        classroomBox5.getItems().add("La136");
        classroomBox5.getItems().add("Ld112");

        classroomBox5.setOnAction(e -> {
            value53 = (String) classroomBox5.getValue();
                location5.setText(value53);
        });

        ComboBox<String> classBox5 = new ComboBox<>();
        classBox5.getItems().add("A1");
        classBox5.getItems().add("A2");
        classBox5.getItems().add("A3");
        classBox5.getItems().add("A4");

        classBox5.setOnAction(e -> {
            value54 = (String) classBox5.getValue();
                group5.setText(value54);
        });

        ComboBox[] comboBoxes5 = {subjectBox5, teacherBox5, classroomBox5, classBox5};

//Comboboxen5 End
    // ComboBox6 --> Pauze
        //ComboBoxen7 Start

        ComboBox<String> subjectBox7 = new ComboBox<>();
        subjectBox7.getItems().add("IPJ");
        subjectBox7.getItems().add("Wiskunde");
        subjectBox7.getItems().add("Proftaak");

        subjectBox7.setOnAction(e -> {
            value71 = (String) subjectBox7.getValue();
                subject7.setText(value71);
        });

        ComboBox<String> teacherBox7 = new ComboBox<>();
        teacherBox7.getItems().add("Joehan");
        teacherBox7.getItems().add("Joelie");
        teacherBox7.getItems().add("Robijn");
        teacherBox7.getItems().add("Potter");
        teacherBox7.getItems().add("EddLose");
        teacherBox7.getItems().add("Shans");


        teacherBox7.setOnAction(e -> {
            value72 = (String) teacherBox7.getValue();
                teacher7.setText(value72);
        });

        ComboBox<String> classroomBox7 = new ComboBox<>();
        classroomBox7.getItems().add("La134");
        classroomBox7.getItems().add("La136");
        classroomBox7.getItems().add("Ld112");

        classroomBox7.setOnAction(e -> {
            value73 = (String) classroomBox7.getValue();
                location7.setText(value73);
        });

        ComboBox<String> classBox7 = new ComboBox<>();
        classBox7.getItems().add("A1");
        classBox7.getItems().add("A2");
        classBox7.getItems().add("A3");
        classBox7.getItems().add("A4");

        classBox7.setOnAction(e -> {
            value74 = (String) classBox7.getValue();
                group7.setText(value74);
        });

        ComboBox[] comboBoxes7 = {subjectBox7, teacherBox7, classroomBox7, classBox7};

//Comboboxen7 End
        //ComboBoxen8 Start

        ComboBox<String> subjectBox8 = new ComboBox<>();
        subjectBox8.getItems().add("IPJ");
        subjectBox8.getItems().add("Wiskunde");
        subjectBox8.getItems().add("Proftaak");

        subjectBox8.setOnAction(e -> {
            value81 = (String) subjectBox8.getValue();
                subject8.setText(value81);
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
                teacher8.setText(value82);
        });

        ComboBox<String> classroomBox8 = new ComboBox<>();
        classroomBox8.getItems().add("La134");
        classroomBox8.getItems().add("La136");
        classroomBox8.getItems().add("Ld112");

        classroomBox8.setOnAction(e -> {
            value83 = (String) classroomBox8.getValue();
                location8.setText(value83);
        });

        ComboBox<String> classBox8 = new ComboBox<>();
        classBox8.getItems().add("A1");
        classBox8.getItems().add("A2");
        classBox8.getItems().add("A3");
        classBox8.getItems().add("A4");

        classBox8.setOnAction(e -> {
            value84 = (String) classBox8.getValue();
                group8.setText(value84);
        });

        ComboBox[] comboBoxes8 = {subjectBox8, teacherBox8, classroomBox8, classBox8};

//Comboboxen8 End

        VBox vbox1 = new VBox();
        vbox1.setSpacing(25);

        Button addbutton = new Button("ADD");
        Button deletebutton = new Button("DELETE");

        addbutton.setOnAction(e -> {
            stage.setScene(scene2);
        });

        deletebutton.setOnAction(e -> {
            subjectBox.setValue(null);
            subjectBox1.setValue(null);
            subjectBox2.setValue(null);
            subjectBox4.setValue(null);
            subjectBox5.setValue(null);
            subjectBox7.setValue(null);
            subjectBox8.setValue(null);
            teacherBox.setValue(null);
            teacherBox1.setValue(null);
            teacherBox2.setValue(null);
            teacherBox4.setValue(null);
            teacherBox5.setValue(null);
            teacherBox7.setValue(null);
            teacherBox8.setValue(null);
            classroomBox.setValue(null);
            classroomBox1.setValue(null);
            classroomBox2.setValue(null);
            classroomBox4.setValue(null);
            classroomBox5.setValue(null);
            classroomBox7.setValue(null);
            classroomBox8.setValue(null);
            classBox.setValue(null);
            classBox1.setValue(null);
            classBox2.setValue(null);
            classBox4.setValue(null);
            classBox5.setValue(null);
            classBox7.setValue(null);
            classBox8.setValue(null);
        });

        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(addbutton, deletebutton);


        Label timer = new Label("Time");
        Label subject = new Label("Subject    ");
        Label teacher = new Label("Teacher");
        Label location = new Label("Location");
        Label group = new Label("Group");

        HBox titleHbox = new HBox();
        titleHbox.setSpacing(160);
        titleHbox.getChildren().addAll(timer, subject, teacher, location, group);

        HBox hbox0 = new HBox();
        hbox0.setSpacing(110);
        hbox0.getChildren().addAll(time);
        for (int i = 0; i < 4; i++) {
            hbox0.getChildren().addAll(comboBoxes[i]);
        }
        HBox hbox1 = new HBox();
        hbox1.setSpacing(110);
        hbox1.getChildren().addAll(time1);
        for (int i = 0; i < 4; i++) {
            hbox1.getChildren().addAll(comboBoxes1[i]);
        }
        HBox hbox2 = new HBox();
        hbox2.setSpacing(110);
        hbox2.getChildren().addAll(time2);
        for (int i = 0; i < 4; i++) {
            hbox2.getChildren().addAll(comboBoxes2[i]);
        }
        HBox hbox3 = new HBox();
        hbox3.setSpacing(170);
        hbox3.getChildren().addAll(time3, pauze1, streep1, streep12, streep13);

        HBox hbox4 = new HBox();
        hbox4.setSpacing(110);
        hbox4.getChildren().addAll(time4);
        for (int i = 0; i < 4; i++) {
            hbox4.getChildren().addAll(comboBoxes4[i]);
        }
        HBox hbox5 = new HBox();
        hbox5.setSpacing(110);
        hbox5.getChildren().addAll(time5);
        for (int i = 0; i < 4; i++) {
            hbox5.getChildren().addAll(comboBoxes5[i]);
        }
        HBox hbox6 = new HBox();
        hbox6.setSpacing(170);
        hbox6.getChildren().addAll(time6, pauze2, streep2, streep21, streep22);

        HBox hbox7 = new HBox();
        hbox7.setSpacing(110);
        hbox7.getChildren().addAll(time7);
        for (int i = 0; i < 4; i++) {
            hbox7.getChildren().addAll(comboBoxes7[i]);
        }
        HBox hbox8 = new HBox();
        hbox8.setSpacing(110);
        hbox8.getChildren().addAll(time8);
        for (int i = 0; i < 4; i++) {
            hbox8.getChildren().addAll(comboBoxes8[i]);
        }
        vbox1.getChildren().addAll(buttonBox, titleHbox, hbox0, hbox1, hbox2, hbox3, hbox4, hbox5, hbox6, hbox7, hbox8);



        scene = new Scene(vbox1, 950, 600);
        scene2 = new Scene(vBoxrooster, 950, 600);
        stage.setScene(scene2);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}