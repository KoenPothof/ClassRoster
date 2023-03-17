package map;

import Data.Classroom;

import javax.imageio.ImageIO;
import javax.json.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CodeTest {

    public static void main(String[] args) throws FileNotFoundException {
        JsonReader reader = null;
        reader = Json.createReader(new FileInputStream("resources/SchoolBuilding1.json"));
        JsonObject root = reader.readObject();

        //data arrays uitlezen
        JsonArray layers = root.getJsonArray("layers");
        JsonArray chunksArray = null;
        JsonArray tile;


        //mapwidth
        int width = root.getJsonArray("layers").getJsonObject(0).getInt("width");
//        System.out.println(width);

        //mapheight
        int height = root.getJsonArray("layers").getJsonObject(0).getInt("height");
//        System.out.println(height);

        ArrayList<Integer> data = new ArrayList<>();
        ArrayList<ArrayList<Integer>> testLayer = new ArrayList<>();
        ArrayList<Integer> split = new ArrayList<>();
        split.add(20);
        int[][] test = new int[height][width];
        chunksArray = layers.getJsonObject(0).getJsonArray("chunks");

        for (int i = 0; i < chunksArray.size(); i++) {
            tile = chunksArray.getJsonObject(i).getJsonArray("data");
            for (int j = 0; j < tile.size(); j++) {
                int dataElement = tile.getInt(j);
                data.add(dataElement);
            }
            testLayer.add(data);
        }

        System.out.println(testLayer.get(1));

//        int number = tile.getInt(100);
//       test =  t.saveTile(chunksArray);
//        System.out.println(test);
//        System.out.println(number);
//        int[] data = new int[tile.size()];


//        for (int y = 0; y < 4; y++) {
//            for (int x = 0; x < 7; x++) {
//                test[y][x] = chunksArray.getJsonObject(y).getJsonArray("data").getInt(x);
//            }
//        }
//        for (int i = 0; i < tile.size(); i++) {
//            data[i] = tile.getInt(i);
//        }
//        System.out.println(Arrays.toString(test));
//        System.out.println(tile);
//        System.out.println(Arrays.stream(layer.getLayer()).toArray());
        //tilesets uitlezen
        JsonArray tilesets = root.getJsonArray("tilesets");
        for (int i = 1; i < 4; i++) {
//            System.out.println(tilesets.getJsonObject(i).getString("image"));
        }

//        System.out.println(Arrays.toString(layer.getLayer().get(1)));


        ArrayList<BufferedImage> tiles = new ArrayList<>();
        try {
            BufferedImage tilemap = ImageIO.read(new FileInputStream("resources/" + root.getJsonArray("tilesets").getJsonObject(1).getString("image")));

            int tileHeight = root.getInt("tileheight");
            int tileWidth = root.getInt("tilewidth");

            for (int y = 0; y < tilemap.getHeight(); y += tileHeight) {
                for (int x = 0; x < tilemap.getWidth(); x += tileWidth) {
                    tiles.add(tilemap.getSubimage(x, y, tileWidth, tileHeight));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
