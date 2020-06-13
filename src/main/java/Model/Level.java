package Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public abstract class Level {
    static private ImageView LEVEL_IMG = new ImageView();
    private final String location;
    private final int[] pCoord;
    private LinkedList<Rectangle> COLLISION;
    private List<Trigger> TRIGGERS;
    private LevelObject[] OBJECTS;

    public Level(String location, Image image, LinkedList<Rectangle> COLLISION, List<Trigger> TRIGGERS, LevelObject[] OBJECTS, int[] pCoord) {
        LEVEL_IMG.setImage(image);
        this.location = location;
        this.COLLISION = createCollisionList(COLLISION);
        this.TRIGGERS = createTriggerList(TRIGGERS);
        this.OBJECTS = OBJECTS;
        this.pCoord = pCoord;
    }

    abstract void interact(Trigger trigger);


    void checkObjectView(Player player) {
        double playerCol = player.getBOTTOM_COLLISION();
        for (LevelObject colShape : getOBJECTS()) {
            if (playerCol > colShape.getIMG_VIEW().getY() + colShape.getIMG_VIEW().getImage().getHeight()) {
                colShape.setView(OBJECT_VIEW.BACK);
            } else {
                colShape.setView(OBJECT_VIEW.FRONT);
            }
        }
    }

    public LevelObject[] getOBJECTS() {
        return OBJECTS;
    }

    public List<Trigger> getTRIGGERS() {
        return TRIGGERS;
    }
    public LinkedList<Rectangle> getCOLLISION() {
        return COLLISION;
    }

    public ImageView getLEVEL_IMG() {
        return LEVEL_IMG;
    }

    public int[] getpCoord() {
        return pCoord;
    }

    String getLocation() {
        return location;
    }

    public void setCOLLISION(LinkedList<Rectangle> COLLISION) {
        this.COLLISION = COLLISION;
    }

    public void setTRIGGERS(List<Trigger> TRIGGERS) {
        this.TRIGGERS = TRIGGERS;
    }

    List<Trigger> createTriggerList(List<Trigger> list) {
        List resultList = new ArrayList();
        for (Trigger trigger : list) {
            resultList.add(new Trigger(trigger));
        }
        return resultList;
    }

    LinkedList<Rectangle> createCollisionList(LinkedList<Rectangle> list) {
        LinkedList resultList = new LinkedList();
        for (Rectangle col : list) {
            resultList.add(new Rectangle(col.getX(), col.getY(), col.getWidth(), col.getHeight()));
        }
        return resultList;
    }

    abstract void reload();

}
