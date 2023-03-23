package GUI;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.BorderPane;
import map.Map;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import java.awt.*;
import java.io.IOException;

public class GuiCanvas {

    private boolean simulationOn = false;

    private Map map;
    private ResizableCanvas canvas;

    public GuiCanvas(BorderPane simulationPane) throws IOException {
        map = new Map("map/project.json");
        canvas = new ResizableCanvas(g -> draw(g), simulationPane);
        FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());
        new AnimationTimer() {
            long last = -1;

            @Override
            public void handle(long now) {
                if (last == -1)
                    last = now;
                update((now - last) / 10000.0);
                last = now;
                if (simulationOn) {
                    draw(g2d);
                }
            }
        }.start();

        draw(g2d);


    }

    public void draw(FXGraphics2D g) {
        g.setBackground(Color.white);
        g.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());
        map.draw(g);
    }


    public void update(double deltaTime) {


    }


    public ResizableCanvas getCanvas() {
        return canvas;
    }

    public void setSimulationOn(boolean simulationOn) {
        this.simulationOn = simulationOn;
    }
}
