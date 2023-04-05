package GUI;

import NPC.NPCConsole;
import Utilities.FileConverter;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import map.Map;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import java.awt.*;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class GuiCanvas {

    private boolean simulationOn = false;

    private Map map;
    private ResizableCanvas canvas;
    private Timer timer;
    private TimerTask task;

    private NPCConsole npcConsole;

    private int counter;
    private final long timerTime = 1000; // 1000 = 1 second

    public GuiCanvas(BorderPane borderPanePane, FileConverter fileConverter) throws IOException {
        map = new Map("map/project.json");
        canvas = new ResizableCanvas(g -> draw(g), borderPanePane);
        FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());

        borderPanePane.setCenter(canvas);

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

        newTimer(1, true);

        draw(g2d);
    }

    String text = "press the start button";

    public void draw(FXGraphics2D g2d) {
        g2d.setBackground(Color.white);
        g2d.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());
        map.draw(g2d);

//        pathfinding.draw(g2d);
        npcConsole.draw(g2d);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setStroke(javafx.scene.paint.Paint.valueOf("white"));
        gc.setFont(new Font("Arial", 28));
        gc.strokeText(text, 1580, 90);

//        pathfinding.numberDraw(g2d);
    }

    public void update(double deltaTime) {
        npcConsole.update();
    }

    private void newTimer(double time, boolean reset) {
        if (reset) {
            counter = 450;
        }
        try {
            timer.cancel();
        } catch (Exception ignored) {
        }
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                if (simulationOn) {
                    counter++;

                    int hours = counter / 60;
                    int minutes = counter % 60;
//                text = hours + ":" + minutes;

                    if (hours < 10) {
                        text = String.format("0%d:%02d", hours, minutes);
                    } else {
                        text = String.format("%d:%02d", hours, minutes);
                    }
//                    for (int i = 0; i < npcConsole.getNpcs().size() * counter/480; i++) {
//                        npcConsole.getNpcs().get(i).setDrawn(true);
//                    }

                    if (text.equals("08:20")) {
                        npcConsole.pathfindingUpdate(0);
                    } else if (text.equals("09:20")) {
                        npcConsole.pathfindingUpdate(1);
                    } else if (text.equals("10:10")) {
                        npcConsole.pathfindingUpdate(2);
                    } else if (text.equals("11:00")) {
                        npcConsole.pathfindingUpdate();
                    } else if (text.equals("11:15")) {
                        npcConsole.pathfindingUpdate(3);
                    } else if (text.equals("12:05")) {
                        npcConsole.pathfindingUpdate(4);
                    } else if (text.equals("12:55")) {
                        npcConsole.pathfindingUpdate();
                    } else if (text.equals("13:30")) {
                        npcConsole.pathfindingUpdate(5);
                    } else if (text.equals("14:20")) {
                        npcConsole.pathfindingUpdate(6);
                    } else if (text.equals("15:10")) {
                        npcConsole.pathfindingUpdate(7);
                    }


//                System.out.println(text);
                }
            }
        };
        timer.schedule(task, 0, (long) (timerTime / time));
    }

    public void setSimulationOn(boolean simulationOn) {
        this.simulationOn = simulationOn;
    }

    public void setTimerTime(double time) {
        counter--;
        newTimer(time, false);
    }

    public void setNpcConsole(NPCConsole npcConsole) {
        this.npcConsole = npcConsole;
        newTimer(1, true);
    }

    public Timer getTimer() {
        return timer;
    }

    public NPCConsole getNpcConsole() {
        return npcConsole;
    }

}