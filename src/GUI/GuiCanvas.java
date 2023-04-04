package GUI;

import Data.Group;
import NPC.NPC;
import NPC.NPCConsole;
import PathFinding.Pathfinding;
import Utilities.FileConverter;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.BorderPane;
import map.Map;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GuiCanvas {

    private boolean simulationOn = false;

    private Map map;
    private Pathfinding pathfinding;
    private ResizableCanvas canvas;
    private Timer timer;
    private TimerTask task;

    private FileConverter fileConverter;

    private NPCConsole npcConsole;

    private int counter = 0;
    private final long timerTime = 10000; // 1000 = 1 second

    public GuiCanvas(BorderPane borderPanePane, FileConverter fileConverter) throws IOException {
        map = new Map("map/project.json");
        pathfinding = new Pathfinding(64,47);
        canvas = new ResizableCanvas(g -> draw(g), borderPanePane);
        FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());

        borderPanePane.setCenter(canvas);
        this.fileConverter = fileConverter;

        npcConsole = new NPCConsole(fileConverter);
        new AnimationTimer() {
            long last = -1;

            @Override
            public void handle(long now) {
                try {
                    if (simulationOn) {
                        if (last == -1)
                            last = now;
                        update((now - last) / 1000.0);
                        last = now;
                        draw(g2d);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }.start();

        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                npcConsole.pathfindingUpdate();
                counter++;
            }
        };
        timer.schedule(task, 0, timerTime);

        draw(g2d);
    }

    public void draw(FXGraphics2D g2d) {
        g2d.setBackground(Color.white);
        g2d.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());
        map.draw(g2d);
        npcConsole.draw(g2d);

//        pathfinding.draw(g2d);
//        pathfinding.numberDraw(g2d);
    }

    public void update(double deltaTime) {
        npcConsole.update();
    }

    public void setSimulationOn(boolean simulationOn) {
        this.simulationOn = simulationOn;
    }

    public void setTimerTime(double time) {
        counter--;
        timer.schedule(task, 0, (long) (timerTime * time));
    }

    public Timer getTimer() {
        return timer;
    }
}