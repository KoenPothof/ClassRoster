package GUI;

import NPC.NPC;
import PathFinding.Pathfinding;
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

    private ArrayList<NPC> npcs = new ArrayList<>();

    Point2D clipPosition = new Point2D.Double(50 * 16, 50 * 16);

    public GuiCanvas(BorderPane borderPanePane) throws IOException {
        map = new Map("map/project.json");
        pathfinding = new Pathfinding(50, 50);
        canvas = new ResizableCanvas(g -> draw(g), borderPanePane);
        FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());

        borderPanePane.setCenter(canvas);

        NPC npc = new NPC(12, 10);
        npcs.add(npc);

//        canvas.setOnMouseClicked(e -> {
//            clipPosition = new Point2D.Double(e.getX(), e.getY());
//            pathfinding.findPath((int) e.getX() / 16, (int) e.getY() / 16);
//            draw(g2d);
//        });
//        pathfinding.findPath(14, 14);

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

//        pathfinding.draw(g2d);
        pathfinding.numberDraw(g2d);
    }

    public void update(double deltaTime) {
        for (NPC npc : npcs) {
            int npcX = npc.getPosition()[0];
            int npcY = npc.getPosition()[1];
            int[] nextTile = pathfinding.getNextTile(npcX, npcY);
            if (npc.getPosition()[0] == npc.getTarget()[0] && npc.getPosition()[1] == npc.getTarget()[1] && deltaTime % 100 < 1) {
                npc.setTarget(nextTile[0], nextTile[1]);
            }


            npc.update(npcs);

        }
    }

    public void setSimulationOn(boolean simulationOn) {
        this.simulationOn = simulationOn;
    }
}
