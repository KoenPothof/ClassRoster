package map;

import org.jfree.fx.FXGraphics2D;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class Map {
    private int tileWidth;
    private int tileHeight;
    public Map (String filename){
        JsonReader reader = null;
        reader = Json.createReader(getClass().getResourceAsStream(filename));
        JsonObject root = reader.readObject();


        //load the map below

        try{

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public void draw(FXGraphics2D g2d){

    }
}
