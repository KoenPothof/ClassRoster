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

public class GuiCanvas {

    private boolean simulationOn = false;

    private Map map;
    private Pathfinding pathfinding;
    private ResizableCanvas canvas;
    private FileConverter fileConverter;
    private ArrayList<NPC> npcs = new ArrayList<>();

    private NPCConsole npcConsole;

    Point2D clipPosition = new Point2D.Double(50 * 16, 50 * 16);

    public GuiCanvas(BorderPane borderPanePane, FileConverter fileConverter) throws IOException {
        map = new Map("map/project.json");
        pathfinding = new Pathfinding(50, 50);
        canvas = new ResizableCanvas(g -> draw(g), borderPanePane);
        FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());

        borderPanePane.setCenter(canvas);
        this.fileConverter = fileConverter;

//        NPC npc = new NPC(12, 10);
//        NPC npc2 = new NPC(11, 10);
//        Group group1 = new Group();


        for (int i = 0; i < fileConverter.getGroups().length; i++) {
            for (int j = 0; j < fileConverter.getGroups()[i].getNPCs().length; j++) {
                npcs.add(fileConverter.getGroups()[i].getNPCs()[j]);
            }
        }
//        npcs.add(npc);
//        npcs.add(npc2);

//        canvas.setOnMouseClicked(e -> {
//            clipPosition = new Point2D.Double(e.getX(), e.getY());
//            pathfinding.findPath((int) e.getX() / 16, (int) e.getY() / 16);
//            draw(g2d);
//        });
//        pathfinding.findPath(14, 14);

        npcConsole = new NPCConsole(npcs, fileConverter);
        new AnimationTimer() {
            long last = -1;

            @Override
            public void handle(long now) {
                try {
                    if (simulationOn) {
                        if (last == -1)
                            last = now;
                        update((now - last) / 1.0);
                        last = now;
                        draw(g2d);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }.start();

        draw(g2d);
    }

    public void draw(FXGraphics2D g2d) {
        g2d.setBackground(Color.white);
        g2d.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());
        map.draw(g2d);
        for (NPC npc : npcs)
            npc.draw(g2d);

        pathfinding.draw(g2d);
//        pathfinding.numberDraw(g2d);
    }

    public void update(double deltaTime) {
        npcConsole.update();
    }

    public void setSimulationOn(boolean simulationOn) {
        this.simulationOn = simulationOn;
    }
}