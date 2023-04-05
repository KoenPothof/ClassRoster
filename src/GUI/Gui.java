package GUI;

import NPC.NPCConsole;
import Utilities.FileConverter;
import Data.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Gui extends Application {

    private final FileConverter fileConverter = new FileConverter("data.txt");

    private GuiCanvas guiCanvas;

    private final ArrayList<ComboBox<String>> comboBoxArrayList = new ArrayList<>();
    private final ArrayList<Label> labelArrayList = new ArrayList<>();
    private final ArrayList<Button> deleteButtonsArrayList = new ArrayList<>();
    private final ArrayList<ComboBox<String>> addComboBoxesArrayList = new ArrayList<>();
    private final Integer[] addCheck = {0, 0, 0, 0, 0};

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

    private int index = 0;

    @Override
    public void start(Stage primaryStage) throws IOException {

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
        for (int i = 0; i < fileConverter.getLessons().size(); i++) {
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
        for (int i = 0; i < fileConverter.getLessons().size(); i++) {
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
        guiCanvas = new GuiCanvas(simulationPane, fileConverter);

        // --- ----------- ---


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

        // --- ------- ---

        // --- simulation tab ---
        Tab simulationTab = new Tab("simulatie");

        BufferedImage buttonImages = ImageIO.read(getClass().getResource("/gui_resources/buttons.png"));


        BufferedImage startImage = buttonImages.getSubimage(26, 0, 26, 25);
        Image startImage1 = SwingFXUtils.toFXImage(startImage, null);
        ImageView startView = new ImageView(startImage1);
        Button startButton = new Button();
        startButton.setGraphic(startView);
        startButton.setWrapText(true);
        startButton.setMaxWidth(20);
        startButton.setOnAction(event -> {
            guiCanvas.setSimulationOn(true);
        });

        BufferedImage pauseImage = buttonImages.getSubimage(0, 0, 26, 25);
        Image pauseImage1 = SwingFXUtils.toFXImage(pauseImage, null);
        ImageView pauseView = new ImageView(pauseImage1);
        Button pauseButton = new Button();
        pauseButton.setGraphic(pauseView);
        pauseButton.setWrapText(true);
        pauseButton.setMaxWidth(20);
        pauseButton.setOnAction(event -> {
            guiCanvas.setSimulationOn(false);
        });

        BufferedImage resetImage = buttonImages.getSubimage(52, 0, 26, 25);
        Image resetImage1 = SwingFXUtils.toFXImage(resetImage, null);
        ImageView resetView = new ImageView(resetImage1);
        Button resetButton = new Button();
        resetButton.setGraphic(resetView);
        resetButton.setWrapText(true);
        resetButton.setMaxWidth(20);
        resetButton.setOnAction(event -> {
            guiCanvas.setNpcConsole(new NPCConsole(fileConverter));
        });

        Label speedLabel = new Label("Speed: ");
        Label speedLabel2 = new Label("1.0");

        Button slower = new Button("Slower");
        slower.setOnAction(event -> {
            if (index != -2) {
                index--;
            }
            newSpeed(speedLabel2);
        });

        Button faster = new Button("Faster");
        faster.setOnAction(event -> {
            if (index != 5) {
                index++;
            }
            newSpeed(speedLabel2);
        });

        VBox buttons = new VBox();
        buttons.getChildren().addAll(startButton, pauseButton, resetButton, speedLabel, speedLabel2, faster, slower);
        simulationPane.setLeft(buttons);

        simulationTab.setContent(simulationPane);


        roostermodule.getTabs().addAll(roosterTab, editTab, simulationTab);

        // --- ------- ---


        Image icon = new Image("gui_resources/logo.png");
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

    private void newSpeed(Label speedLabel) {
        double speed = .9999999;
        switch (index) {
            case -2:
                speed = 0.1;
                break;
            case -1:
                speed = 0.5;
                break;
            case 0:
                speed = 1.0;
                break;
            case 1:
                speed = 1.5;
                break;
            case 2:
                speed = 2.0;
                break;
            case 3:
                speed = 5.0;
                break;
            case 4:
                speed = 10.0;
                break;
            case 5:
                speed = 20.0;
                break;
        }
        speedLabel.setText(speed + "");
        guiCanvas.getNpcConsole().setSpeed(speed);
        guiCanvas.setTimerTime(speed);
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

    private void refresh() {
//        System.out.println("refresh"); // debug code
        fileConverter.sort();
        for (int i = 0; i < fileConverter.getLessons().size(); i++) {

            String time = fileConverter.getLessons().get(i).getTime().toString();
            String subject = fileConverter.getLessons().get(i).getSubject().toString();
            String teacher = fileConverter.getLessons().get(i).getTeacher().toString();
            String classroom = fileConverter.getLessons().get(i).getClassroom().toString();
            String group = fileConverter.getLessons().get(i).getGroup().toString();
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
            comboBoxArrayList.add(new ComboBox<>(FXCollections.observableArrayList(fileConverter.getList().get(j))));
        }

        int comboBoxId = comboBoxArrayList.size() - 5;
        vBoxesEdit[0].getChildren().add(comboBoxArrayList.get(comboBoxId));
        vBoxesEdit[1].getChildren().add(comboBoxArrayList.get(comboBoxId + 1));
        vBoxesEdit[2].getChildren().add(comboBoxArrayList.get(comboBoxId + 2));
        vBoxesEdit[3].getChildren().add(comboBoxArrayList.get(comboBoxId + 3));
        vBoxesEdit[4].getChildren().add(comboBoxArrayList.get(comboBoxId + 4));

        int lessonId = comboBoxId / 5;
        comboBoxArrayList.get(comboBoxId).setValue(fileConverter.getLessons().get(lessonId).getTime().toString());
        comboBoxArrayList.get(comboBoxId).setOnAction(e -> fileConverter.getLessons().get(lessonId).setTime(fileConverter.getTimes()[fileConverter.getDataHashMap().get(comboBoxArrayList.get(comboBoxId).getValue())]));

        comboBoxArrayList.get(comboBoxId + 1).setValue(fileConverter.getLessons().get(lessonId).getSubject().toString());
        comboBoxArrayList.get(comboBoxId + 1).setOnAction(e -> fileConverter.getLessons().get(lessonId).setSubject(fileConverter.getSubjects()[fileConverter.getDataHashMap().get(comboBoxArrayList.get(comboBoxId + 1).getValue())]));

        comboBoxArrayList.get(comboBoxId + 2).setValue(fileConverter.getLessons().get(lessonId).getTeacher().toString());
        comboBoxArrayList.get(comboBoxId + 2).setOnAction(e -> fileConverter.getLessons().get(lessonId).setTeacher(fileConverter.getTeachers()[fileConverter.getDataHashMap().get(comboBoxArrayList.get(comboBoxId + 2).getValue())]));

        comboBoxArrayList.get(comboBoxId + 3).setValue(fileConverter.getLessons().get(lessonId).getClassroom().toString());
        comboBoxArrayList.get(comboBoxId + 3).setOnAction(e -> fileConverter.getLessons().get(lessonId).setClassroom(fileConverter.getClassrooms()[fileConverter.getDataHashMap().get(comboBoxArrayList.get(comboBoxId + 3).getValue())]));

        comboBoxArrayList.get(comboBoxId + 4).setValue(fileConverter.getLessons().get(lessonId).getGroup().toString());
        comboBoxArrayList.get(comboBoxId + 4).setOnAction(e -> fileConverter.getLessons().get(lessonId).setGroup(fileConverter.getGroups()[fileConverter.getDataHashMap().get(comboBoxArrayList.get(comboBoxId + 4).getValue())]));

        deleteButtonAdd();
    }

    private void labelsAdd() {
        for (int j = 0; j < 5; j++) {
            labelArrayList.add(new Label());
        }

        int labelId = labelArrayList.size() - 5;
        labelArrayList.get(labelId).setText(fileConverter.getLessons().get(labelId / 5).getTime().toString());
        labelArrayList.get(labelId + 1).setText(fileConverter.getLessons().get(labelId / 5).getSubject().toString());
        labelArrayList.get(labelId + 2).setText(fileConverter.getLessons().get(labelId / 5).getTeacher().toString());
        labelArrayList.get(labelId + 3).setText(fileConverter.getLessons().get(labelId / 5).getClassroom().toString());
        labelArrayList.get(labelId + 4).setText(fileConverter.getLessons().get(labelId / 5).getGroup().toString());
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
            fileConverter.getLessons().remove(buttonId);
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
            addComboBoxesArrayList.add(new ComboBox<>(FXCollections.observableArrayList(fileConverter.getList().get(i))));
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
            String[] beta = {"", "", "", "", ""};
            for (int j = 0; j < addComboBoxesArrayList.size(); j++) {
                beta[j] = addComboBoxesArrayList.get(j).getValue();
            }
            boolean hasDuplicates = false;
            String currentTime = beta[0];
            for (int i = 0; i < fileConverter.getLessons().size(); i++) {
                if (fileConverter.getLessons().get(i).getTime().toString().equals(currentTime)) {
                    if (fileConverter.getLessons().get(i).getGroup().toString().equals(beta[4])) {
                        hasDuplicates = true;
                        break;
                    } else if (fileConverter.getLessons().get(i).getClassroom().toString().equals(beta[3])) {
                        hasDuplicates = true;
                        break;
                    } else if (fileConverter.getLessons().get(i).getTeacher().toString().equals(beta[2])) {
                        hasDuplicates = true;
                        break;
                    } else if (fileConverter.getLessons().get(i).getSubject().toString().equals(beta[1])) {
                        hasDuplicates = true;
                        break;
                    }
                }

            }
            if (hasDuplicates) {
                new Alert(Alert.AlertType.NONE, "Er zijn duplicates!", ButtonType.OK).show();
            } else if (!hasDuplicates && alpha == 5) {
//                    String[] beta = {"", "", "", "", ""};
//                    for (int j = 0; j < addComboBoxesArrayList.size(); j++) {
//                        beta[j] = addComboBoxesArrayList.get(j).getValue();
//                    }
                fileConverter.getLessons().add(new Lesson(
                        fileConverter.getTimes()[fileConverter.getDataHashMap().get(beta[0])],
                        fileConverter.getSubjects()[fileConverter.getDataHashMap().get(beta[1])],
                        fileConverter.getTeachers()[fileConverter.getDataHashMap().get(beta[2])],
                        fileConverter.getClassrooms()[fileConverter.getDataHashMap().get(beta[3])],
                        fileConverter.getGroups()[fileConverter.getDataHashMap().get(beta[4])]
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
        guiCanvas.getTimer().cancel();
        fileConverter.save();
    }

}