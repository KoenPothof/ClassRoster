package map;

import Utilities.JsonReader;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import org.jfree.fx.FXGraphics2D;

import javax.imageio.ImageIO;
import javax.json.JsonArray;
import javax.json.JsonObject;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;


public class Map {
    private Utilities.JsonReader jsonReader;
    private int tileWidth;
    private int tileHeight;
    private int mapWidth;
    private int mapHeight;
    private ArrayList<BufferedImage> slicedTiles = new ArrayList<>();
    private BufferedImage screenshot;

    public Map() throws IOException {

        this.jsonReader = new JsonReader();
        JsonArray layerArray = this.jsonReader.getLayerArray();

        this.mapWidth = jsonReader.getMapWidth();
        this.mapHeight = jsonReader.getMapHeight();


// uitlezen images en het snijden daarvan
        try {
            JsonArray tileSets = jsonReader.getTileSets();
            for (int i = 0; i < tileSets.size(); i++) {
                JsonObject tileset = tileSets.getJsonObject(i);
//                System.out.println(tileset.getString("image"));
                BufferedImage image = ImageIO.read(new FileInputStream("resources/tilesets/" + tileset.getString("image")));
                int gid = tileset.getInt("firstgid");
                int tileHeight = tileset.getInt("tileheight");
                int tileWidth = tileset.getInt("tilewidth");
                for (int y = 0; y < image.getHeight(); y += tileHeight) {
                    for (int x = 0; x < image.getWidth(); x += tileWidth) {
                        slicedTiles.add(image.getSubimage(x, y, tileWidth, tileHeight)); // zorg ervoor dat image.getSubImgae op positie gid komt te staan in de slicedTiles array
                        gid++;
                    }
                }
            }
            tileHeight = jsonReader.getTileHeight();
            tileWidth = jsonReader.getTileWidth();
        } catch (Exception e) {
            e.printStackTrace();
        }


        // create a new canvas with the same size as the screenshot
        JsonObject layer = layerArray.getJsonObject(2);
        int[] data = new int[layer.getJsonArray("data").size()];
        for (int j = 0; j < data.length; j++) {
            data[j] = layer.getJsonArray("data").getInt(j);
        }

        javafx.scene.canvas.Canvas canvas = new javafx.scene.canvas.Canvas(1920, 1080);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        for (int x = 0; x < mapWidth; x++) {
            for (int y = 0; y < mapHeight; y++) {
                int index = x + mapWidth * y;
                int tileId = data[index];
                if (tileId <= 0) {
                    continue;
                }
                gc.drawImage(SwingFXUtils.toFXImage(getSlicedImage(tileId - 1), null), 16 * x, 16 * y);
            }
        }

        layer = layerArray.getJsonObject(3);
        data = new int[layer.getJsonArray("data").size()];
        for (int j = 0; j < data.length; j++) {
            data[j] = layer.getJsonArray("data").getInt(j);
        }
        for (int x = 0; x < mapWidth; x++) {
            for (int y = 0; y < mapHeight; y++) {
                int index = x + mapWidth * y;
                int tileId = data[index];
                if (tileId <= 0) {
                    continue;
                }
                gc.drawImage(SwingFXUtils.toFXImage(getSlicedImage(tileId - 1), null), 16 * x, 16 * y);
            }
        }

        // take a snapshot of the canvas and draw it onto the graphics object
        SnapshotParameters parameters = new SnapshotParameters();
        WritableImage snapshot = canvas.snapshot(parameters, null);

        File output = new File("TiledBackgroundScreenshot.png");
        ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", output);
        screenshot = ImageIO.read(new FileInputStream("TiledBackgroundScreenshot.png"));

    }

    public void draw(FXGraphics2D g2d) {
        g2d.drawImage(screenshot, 0, 0, null);
    }

    public BufferedImage getSlicedImage(int tileId) {
        return slicedTiles.get(tileId);
    }
}
