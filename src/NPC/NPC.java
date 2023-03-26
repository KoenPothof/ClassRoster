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
    private Point2D position;
    private double angle;
    private final double speed = .9;

    private final int tileWidth = 16;
    private final int tileOffset = 8;
    private Point2D target;

    private BufferedImage image;
    private NotAFinalName notAFinalName;

    // note ons popetje mag alleen maar 90 graden hoeken draaien dus goed invoegen bij de mapping

    public NPC(int x, int y) {
        this.position = new Point2D.Double(x * tileWidth + tileOffset, y * tileWidth + tileOffset);
        this.target = position;
        this.notAFinalName = new NotAFinalName();
        this.angle = Math.random() * 2 * Math.PI;
        image = notAFinalName.getCurrentImage();
    }

    public void draw(Graphics2D g) {
        AffineTransform tx = new AffineTransform();
        tx.translate(position.getX() - image.getWidth() / 2, position.getY() - image.getHeight() / 2);
//        tx.rotate(angle + Math.toRadians(90), image.getWidth() / 2, image.getHeight() / 2);
        g.drawImage(image, tx, null);
        g.setColor(Color.ORANGE);
        g.draw(new Ellipse2D.Double(position.getX() - 5, position.getY() - 5, 10, 10));

//        g.setColor(Color.red);
//        g.fill(new Ellipse2D.Double(target.getX()-25, target.getY()-25,50,50));
    }

    // zorgt voor lopen naar muis target inplaats dit zou mapping hier moeten
    public void update(ArrayList<NPC> npcs) {
        double angleTo = Math.atan2(target.getY() - position.getY(), target.getX() - position.getX());

        double diff = angleTo - angle;
        if (diff < -Math.PI)
            diff += 2 * Math.PI;
        if (diff > Math.PI)
            diff -= 2 * Math.PI;

        if (Math.abs(diff) < 0.1)
            angle = angleTo;
        else if (diff > 0)
            angle += 0.1;
        else
            angle -= 0.1;

        int degrees = (int) Math.toDegrees(angle);
        if (degrees > 240 && degrees < 300) {
            notAFinalName.setDirection(WalkingDirection.UP);
        } else if (degrees >= 300 || degrees <= 60){
            notAFinalName.setDirection(WalkingDirection.RIGHT);
        } else if (degrees > 60 && degrees < 120){
            notAFinalName.setDirection(WalkingDirection.DOWN);
        } else if (degrees > 120 && degrees < 240) {
            notAFinalName.setDirection(WalkingDirection.LEFT);
        }
        image = notAFinalName.getCurrentImage();


        Point2D oldPos = position;

        position = new Point2D.Double(
                position.getX() + speed * Math.cos(angle),
                position.getY() + speed * Math.sin(angle));


        // dit is de collision
        // als we collision met muren willen dan moeten we die toevoegen aan de muren arraylist

        ArrayList muren = new ArrayList<Point2D>();

        boolean hasCollision = false;
        for (NPC npc : npcs) {
            if (npc == this)
                continue;
            if (npc.position.distanceSq(this.position) < 50 * 50)
                hasCollision = true;
            for (int i = 0; i < muren.size(); i++) {
                if (this.position == muren.get(i)) {
                    hasCollision = true;
                }
            }
        }
        if (hasCollision) {
            position = oldPos;
            angle += 0.9;

        }
    }

    // plek waar je naar toe moet
    public void setTarget(int x, int y) {
        this.target = new Point2D.Double(x * tileWidth - tileOffset, y * tileWidth - tileOffset);
    }

    // plek van popetje
    public int[] getPosition() {
        int[] position = new int[2];
        position[0] = (int) (this.position.getX() / tileWidth);
        position[1] = (int) (this.position.getY() / tileWidth);
        return position;
    }

    public NotAFinalName getNotAFinalName() {
        return notAFinalName;
    }
}
