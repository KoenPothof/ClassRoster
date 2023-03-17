package GUI;

import Utilities.FileConverter;
import javafx.animation.AnimationTimer;
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

public class AlternativeGui extends Application {

    private final FileConverter fileConverter = new FileConverter("data.txt");

    private final ArrayList<ComboBox<String>> comboBoxArrayList = new ArrayList<>();
    private final ArrayList<Label> labelArrayList = new ArrayList<>();
    private final ArrayList<Button> deleteButtonsArrayList = new ArrayList<>();
    private final ArrayList<ComboBox<String>> addComboBoxesArrayList = new ArrayList<>();

    private ArrayList<String[]> data = new ArrayList<>();

    private final Integer[] addCheck = {0, 0, 0, 0, 0};


//    private static ArrayList<String> timeArrayList = new ArrayList<>();
//    private static ArrayList<String> subjectArrayList = new ArrayList<>();
//    private static ArrayList<String> teacherArrayList = new ArrayList<>();
//    private static ArrayList<String> locationArrayList = new ArrayList<>();
//    private static ArrayList<String> groupArrayList = new ArrayList<>();


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

    @Override
    public void start(Stage primaryStage) throws Exception {
        data = fileConverter.load();
        map = new Map("map/project.json");

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
        for (int i = 5; i < data.size(); i++) {
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
        for (int i = 5; i < data.size(); i++) {
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
                update((now - last) / 1000000000.0);
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
        editTab.setOnSelectionChanged(e->{
            refresh();
//            System.out.println("editTab clicked"); // debug code
        });

        Tab simulationTab = new Tab("simulatie");
        simulationTab.setContent(simulationPane);

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
        map.draw(g);
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


    private void refresh() {
//        System.out.println("refresh"); // debug code
        data = arraySort(data);
        for (int i = 0; i < labelArrayList.size(); i++) {
            labelArrayList.get(i).setText(data.get(i / 5 + 5)[i % 5]);
//            labelArrayList.get(i).setText((String) comboBoxArrayList.get(i).getValue());
        }
        for (int i = 0; i < comboBoxArrayList.size(); i++) {
            int alpha = i % 5;
            comboBoxArrayList.get(i).setValue(data.get((5 + (i / 5)))[alpha]);
        }
    }


    private void comboBoxesAdd() {
        for (int j = 0; j < 5; j++) {
            comboBoxArrayList.add(new ComboBox<>(FXCollections.observableArrayList(data.get(j))));

        }
        for (int i = comboBoxArrayList.size() - 5; i < comboBoxArrayList.size(); i++) {
            int comboBoxId = i;
            int alpha = (comboBoxId / 5) + 5;
            int beta = comboBoxId % 5;
            comboBoxArrayList.get(i).setValue(data.get(alpha)[beta]);
            comboBoxArrayList.get(i).setOnAction(e -> {
//                System.out.println(finalI + " happend |" + comboBoxArrayList.get(finalI).getValue() + "|"); // debug code
                data.get(alpha)[beta] = (String) comboBoxArrayList.get(comboBoxId).getValue();
//                System.out.println("changed to: " + data.get(alpha)[beta]); // debug code
            });
            vBoxesEdit[i % 5].getChildren().add(comboBoxArrayList.get(i));
        }
        deleteButtonAdd();
    }

    private void labelsAdd() {
        for (int j = 0; j < 5; j++) {
            labelArrayList.add(new Label());
        }
        for (int j = labelArrayList.size() - 5; j < labelArrayList.size(); j++) {
            if (j / 5 % 2 == 1) {
                labelArrayList.get(j).setStyle("-fx-background-color: #adadad");
            }
            labelArrayList.get(j).setText(data.get(j / 5 + 5)[j % 5]);
            vBoxesRooster[j % 5].getChildren().add(labelArrayList.get(j));
        }
    }

    private void deleteButtonAdd() {
        deleteButtonsArrayList.add(new Button("delete"));
        int buttonId = deleteButtonsArrayList.size() - 1;
        vBoxDeleteAddEdit.getChildren().add(deleteButtonsArrayList.get(buttonId));
        deleteButtonsArrayList.get(buttonId).setOnAction(e -> {
            data.remove(buttonId + 5);
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
            addComboBoxesArrayList.add(new ComboBox<>(FXCollections.observableArrayList(data.get(i))));
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
                    beta[j] = (String) addComboBoxesArrayList.get(j).getValue();
                }
                data.add(beta);
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
//        System.out.println("\t closing application"); // debug code
        fileConverter.save(arraySort(data));
    }
}