package map;

import org.jfree.fx.FXGraphics2D;

import java.awt.image.BufferedImage;

public class Layer {
    private int[] data;


    public Layer(int[] data) {
        this.data = data;
    }

    public void draw(FXGraphics2D g2d, Map map) {
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                int index = x + map.getWidth() * y;
                int tileId = data[index];
                if (tileId == 0)
                    continue;
                BufferedImage tileImage = map.getSlicedImage(tileId - 1);
                g2d.drawImage(tileImage, 16 * x, 16 * y, null);
            }
        }
    }
}
