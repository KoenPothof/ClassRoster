package GUI;

import PathFinding.Pathfinding;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.BorderPane;
import map.Map;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.IOException;

public class GuiCanvas {

    private boolean simulationOn = false;

    private Map map;
    private Pathfinding pathfinding;
    private ResizableCanvas canvas;

    Point2D clipPosition = new Point2D.Double(50 * 16, 50 * 16);

    public GuiCanvas(BorderPane borderPanePane) throws IOException {
        map = new Map("map/project.json");
        pathfinding = new Pathfinding();
        canvas = new ResizableCanvas(g -> draw(g), borderPanePane);
        FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());

        borderPanePane.setCenter(canvas);

        canvas.setOnMouseClicked(e -> {
            clipPosition = new Point2D.Double(e.getX(), e.getY());
            draw(g2d);
        });

        new AnimationTimer() {
            long last = -1;

            @Override
            public void handle(long now) {
                if (last == -1)
                    last = now;
                update((now - last) / 10000.0);
                last = now;
                try {
                    pathfinding.findPath((int) clipPosition.getX() / 16, (int) clipPosition.getY() / 16);
                    if (simulationOn) {
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
        pathfinding.draw(g2d);
//        pathfinding.numberDraw(g2d);
    }

    public void update(double deltaTime) {


    }

    public void setSimulationOn(boolean simulationOn) {
        this.simulationOn = simulationOn;
    }
}
