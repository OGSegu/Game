package Model;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class LevelObject {
    private final ImageView IMG_VIEW;
    private final Rectangle TOP_COLLISION;
    private final Rectangle BOTTOM_COLLISION;
    private Rectangle currentCollision;
    private OBJECT_VIEW view = OBJECT_VIEW.FRONT;
    LevelObject(ImageView imgView) {
        this.IMG_VIEW = imgView;
        TOP_COLLISION = new Rectangle(
                IMG_VIEW.getX(),
                IMG_VIEW.getY(),
                IMG_VIEW.getX() + IMG_VIEW.getImage().getWidth(),
                (IMG_VIEW.getY() + IMG_VIEW.getImage().getHeight()) / 2);
        TOP_COLLISION.setFill(Color.BLUE);
        TOP_COLLISION.setOpacity(0.25);
        BOTTOM_COLLISION = new Rectangle(
                IMG_VIEW.getX(),
                (IMG_VIEW.getY() + IMG_VIEW.getImage().getHeight()) - 10,
                IMG_VIEW.getX() + IMG_VIEW.getImage().getWidth(),
                10);
        BOTTOM_COLLISION.setFill(Color.BLUE);
        BOTTOM_COLLISION.setOpacity(0.25);
        currentCollision = TOP_COLLISION;
    }

    public void setLocation(double x, double y) {
        IMG_VIEW.setX(x);
        IMG_VIEW.setY(y);
        TOP_COLLISION.setX(x);
        TOP_COLLISION.setY(y);
        BOTTOM_COLLISION.setX(x);
        BOTTOM_COLLISION.setY(IMG_VIEW.getY() + IMG_VIEW.getImage().getHeight() - 10);
    }

    public void deleteCollision() {

    }

    public ImageView getIMG_VIEW() {
        return IMG_VIEW;
    }


    public OBJECT_VIEW getView() {
        return view;
    }

    public void setView(OBJECT_VIEW newView) {
        this.view = newView;
        if (newView == OBJECT_VIEW.BACK) {
            getIMG_VIEW().setViewOrder(2);
            currentCollision = TOP_COLLISION;
            TOP_COLLISION.setVisible(true);
            BOTTOM_COLLISION.setVisible(false);
        } else {
            getIMG_VIEW().setViewOrder(0);
            currentCollision = BOTTOM_COLLISION;
            TOP_COLLISION.setVisible(false);
            BOTTOM_COLLISION.setVisible(true);
        }
    }

    public Rectangle getTOP_COLLISION() {
        return TOP_COLLISION;
    }

    public Rectangle getBOTTOM_COLLISION() {
        return BOTTOM_COLLISION;
    }
    public Rectangle getCurrentCollision() {
        return currentCollision;
    }
}

enum OBJECT_VIEW {
    FRONT,
    BACK
}
