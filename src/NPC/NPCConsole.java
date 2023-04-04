package NPC;

import Utilities.FileConverter;
import Utilities.JsonReader;

import java.awt.*;
import java.awt.geom.Point2D;
import java.time.Clock;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class NPCConsole {

    private ArrayList<NPC> npcs;
    private ArrayList<NPC[]> npcsByGroup;
    private FileConverter fileConverter;
    private ArrayList<Point2D> muren;


    private final double speed = 5;


    public NPCConsole(FileConverter fileConverter) {
        this.fileConverter = fileConverter;
        this.muren = new ArrayList<>();
        JsonReader jsonReader = new JsonReader("Walls");
        for (int i = 0; i < jsonReader.getDataArrayInt().length; i++) {
            if (jsonReader.getDataArrayInt()[i] > 0) {
                this.muren.add(new Point2D.Double(i % jsonReader.getMapWidth() + 1, i / jsonReader.getMapWidth() + 1));
            }
        }
        this.npcs = new ArrayList<>();
        this.npcsByGroup = new ArrayList<>();
        for (int i = 0; i < fileConverter.getGroups().length; i++) {
            npcsByGroup.add(new NPC[16]);
            for (int j = 0; j < 16; j++) {
                NPC npc = new NPC(fileConverter.getGroups()[i].toString());
                this.npcs.add(npc);
                npcsByGroup.get(i)[j] = npc;
            }
        }

    }

    public void update() {
        for (NPC npc : npcs) {

            int npcX = npc.getPosition()[0];
            int npcY = npc.getPosition()[1];
            try {
                int[] nextTile = npc.getPathfinding().getNextTile(npcX, npcY);
                if (npc.getPosition()[0] == npc.getTarget()[0] && npc.getPosition()[1] == npc.getTarget()[1]) {
                    npc.setTarget(nextTile[0], nextTile[1]);
                }
            } catch (Exception ignored) {
            }


            double angleTo = Math.atan2(npc.targetY - npc.positionY, npc.targetX - npc.positionX);

            double oldPosX = npc.positionX;
            double oldPosY = npc.positionY;

            npc.positionX = (npc.positionX + speed * Math.cos(angleTo));
            npc.positionY = (npc.positionY + speed * Math.sin(angleTo));

            // als de tijd 8:30-9:20 is, foreach NPC in een groep, setTarget LB001 (voorbeeld).




            // dit is de collision
            // als we collision met muren willen dan moeten we die toevoegen aan de muren arraylist


            for (NPC otherNPC : npcs) {
                if (otherNPC == npc) {
                    continue;
                }

                Point2D npcPosition = new Point2D.Double(otherNPC.positionX, otherNPC.positionY);
                if (npcPosition.distanceSq(new Point2D.Double(npc.positionX, npc.positionY)) < 100 /* WTF? */) {
                    npc.positionX = npc.positionX + (npc.positionX - otherNPC.positionX) * .7;
                    npc.positionY = npc.positionY + (npc.positionY - otherNPC.positionY) * .7;
                }
                for (int l = 0; l < muren.size(); l++) {
                    if (npc.positionX / 16 == muren.get(l).getX() && npc.positionY / 16 == muren.get(l).getY()) {
                        npc.positionX = oldPosX;
                        npc.positionY = oldPosY;
                        break;
                    }
                }
            }
        }
    }


    public void pathfindingUpdate() {
        System.out.println("pathfinding update");

        for (int i = 0; i < fileConverter.getTimes().length; i++) {
            String currentTime = fileConverter.getTimes()[i].toString();
            for (int j = 0; j < fileConverter.getLessons().size(); j++) {
                if (fileConverter.getLessons().get(j).getTime().toString().equals(currentTime)){
                    for (int k = 0; k < npcsByGroup.size(); k++) {
                        if (npcsByGroup.get(k)[0].getGroup().equals( fileConverter.getLessons().get(j).getGroup().toString())){
                            for (int l = 0; l < npcsByGroup.get(k).length; l++) {
                                npcsByGroup.get(k)[l].setPathfinding(fileConverter.getLessons().get(j).getClassroom().getPathfindings()[l]);
                            }
                        }
                    }
                }
            }
        }
    }


    public void draw(Graphics2D g2d) {
        for (NPC npc : npcs) {
            npc.draw(g2d);
        }
    }

}