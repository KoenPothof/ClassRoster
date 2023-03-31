package NPC;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class NPC {
    private double positionX;
    private double positionY;
    private double targetX;
    private double targetY;

    private final double speed = .9;
    private final int tileWidth = 16;
    private final int tileOffset = 8;


    private BufferedImage image;
    private NotAFinalName notAFinalName;

    // note ons popetje mag alleen maar 90 graden hoeken draaien dus goed invoegen bij de mapping

    public NPC(int x, int y) {
        this.positionX = x * tileWidth - tileOffset;
        this.positionY = y * tileWidth - tileOffset;
        this.targetX = this.positionX;
        this.targetY = this.positionY;
        this.notAFinalName = new NotAFinalName();
        image = notAFinalName.getCurrentImage();
    }

    public void draw(Graphics2D g) {
        AffineTransform tx = new AffineTransform();
        tx.translate(positionX - image.getWidth() / 2, positionY - image.getHeight() / 2);
        tx.scale(2, 2);
        g.drawImage(image, tx, null);
        g.setColor(Color.ORANGE);
        g.draw(new Ellipse2D.Double(positionX - 5, positionY - 5, 10, 10));

    }

    // zorgt voor lopen naar muis target inplaats dit zou mapping hier moeten
    public void update(ArrayList<NPC> npcs) {
        double angleTo = Math.atan2(targetY - positionY, targetX - positionX);

        double oldPosX = positionX;
        double oldPosY = positionY;

        positionX = (positionX + speed * Math.cos(angleTo));
        positionY = (positionY + speed * Math.sin(angleTo));


        // dit is de collision
        // als we collision met muren willen dan moeten we die toevoegen aan de muren arraylist

        ArrayList muren = new ArrayList<Point2D>();

        boolean hasCollision = false;
        for (NPC npc : npcs) {
            if (npc == this)
                continue;
            Point2D npcPosition = new Point2D.Double(npc.positionX, npc.positionY);
            if (npcPosition.distanceSq(new Point2D.Double(positionX, positionY) {
            }) < 50 * 50)
                hasCollision = true;
            for (int i = 0; i < muren.size(); i++) {
//                if (positionX == muren.get(i).getX() && positionY == muren.get(i).getY()) {
//                    hasCollision = true;
//                }
            }
        }
        if (hasCollision) {
            positionX = oldPosX;
            positionY = oldPosY;
        }
    }

    // plek waar je naar toe moet
    public void setTarget(int x, int y) {
        this.targetX = x * tileWidth + tileOffset;
        this.targetY = y * tileWidth + tileOffset;
//        this.target = new Point2D.Double(x * tileWidth - tileOffset, y * tileWidth - tileOffset);
    }

    // plek van popetje
    public int[] getPosition() {
        int[] position = new int[2];
        position[0] = (int) (positionX / tileWidth);
        position[1] = (int) (positionY / tileWidth);
        return position;
    }

    public int[] getTarget() {
        int[] position = new int[2];
        position[0] = (int) (targetX / tileWidth);
        position[1] = (int) (targetY / tileWidth);
        return position;
    }

    public NotAFinalName getNotAFinalName() {
        return notAFinalName;
    }
}
