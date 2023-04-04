package NPC;

import Utilities.FileConverter;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class NPCConsole {

    private ArrayList<NPC> npcs;
    private FileConverter fileConverter;

    private final double speed = 4;


    public NPCConsole(ArrayList<NPC> npcs, FileConverter fileConverter) {
        this.npcs = npcs;
        this.fileConverter = fileConverter;

    }

    public void update() {
        for (NPC npc : npcs) {

            int npcX = npc.getPosition()[0];
            int npcY = npc.getPosition()[1];
            int[] nextTile = npc.getPathfinding().getNextTile(npcX, npcY);
            if (npc.getPosition()[0] == npc.getTarget()[0] && npc.getPosition()[1] == npc.getTarget()[1]) {
                npc.setTarget(nextTile[0], nextTile[1]);
            }


            double angleTo = Math.atan2(npc.targetY - npc.positionY, npc.targetX - npc.positionX);

            double oldPosX = npc.positionX;
            double oldPosY = npc.positionY;

            npc.positionX = (npc.positionX + speed * Math.cos(angleTo));
            npc.positionY = (npc.positionY + speed * Math.sin(angleTo));

            // als de tijd 8:30-9:20 is, foreach NPC in een groep, setTarget LB001 (voorbeeld).


            // dit is de collision
            // als we collision met muren willen dan moeten we die toevoegen aan de muren arraylist

            ArrayList<Point2D> muren = new ArrayList<>();
            muren.add(new Point2D.Double(5, 5));


            boolean hasCollision = false;
            for (NPC otherNPC : npcs) {
                if (otherNPC == npc) {
                    continue;
                }

                Point2D npcPosition = new Point2D.Double(otherNPC.positionX, otherNPC.positionY);
                if (npcPosition.distanceSq(new Point2D.Double(npc.positionX, npc.positionY)) < 10 * 10 /* WTF? */) {
                    hasCollision = true;
                }
                for (int i = 0; i < muren.size(); i++) {
                    if (npc.positionX / 16 == muren.get(i).getX() && npc.positionY / 16 == muren.get(i).getY()) {
                        hasCollision = true;
                        break;
                    }
                }
            }
            if (hasCollision) {
                npc.positionX = oldPosX;
                npc.positionY = oldPosY;
            }
        }
    }

    public void draw(Graphics2D g2d) {
        for (NPC npc : npcs) {
            npc.draw(g2d);
        }
    }

}