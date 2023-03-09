package map;

import javax.json.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class CodeTest {

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<JsonArray> jsonArrays = new ArrayList<>();
        JsonReader reader = null;
        reader = Json.createReader(new FileInputStream("SchoolBuilding1.json"));
        JsonObject root = reader.readObject();


        //data arrays uitlezen
        JsonArray layers = root.getJsonArray("layers");
        JsonArray chunksArray = null;
        JsonArray dataArray;




        //mapwidth
        int width = root.getInt("width");
        //mapheight
        int height = root.getInt("height");


        for (int i = 0; i < layers.size(); i++) {
            chunksArray = layers.getJsonObject(i).getJsonArray("chunks");
        }

        for (int i = 0; i < chunksArray.size(); i++) {
            dataArray = chunksArray.getJsonObject(i).getJsonArray("data");
            System.out.println(dataArray);
            jsonArrays.add(dataArray);
        }
      

        //tilesets uitlezen
        JsonArray tilesets = root.getJsonArray("tilesets");
        for (int i = 1; i < 4; i++) {
            System.out.println(tilesets.getJsonObject(i).getString("image"));
        }


//        try {
//            BufferedImage weirdTileset = ImageIO.read(CodeTest.class.getResourceAsStream(root.getJsonArray("tilesets").getString(2)));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }



    }
}
