package Model;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Player {

    private final Image[] SKELETON_IDLE_RIGHT;
    private final Image[] SKELETON_IDLE_LEFT;
    private final Image[] SKELETON_WALK_RIGHT;
    private final Image[] SKELETON_WALK_LEFT;

    {
        SKELETON_IDLE_RIGHT = SpriteData.getSprites("Skeleton_Idle_Right");
        SKELETON_IDLE_LEFT = SpriteData.getSprites("Skeleton_Idle_Left");
        SKELETON_WALK_RIGHT = SpriteData.getSprites("Skeleton_Walk_Right");
        SKELETON_WALK_LEFT = SpriteData.getSprites("Skeleton_Walk_Left");
    }

    private Status action = Status.IDLE; // Текущая анимация
    private Status.View view = Status.View.LEFT; // Куда смотрит сейчас
    private ImageView imgView = new ImageView(SKELETON_IDLE_LEFT[0]); // Как выглядит сейчас
    private Image[] imgArray = SKELETON_IDLE_LEFT; // Массив кадров
    private final Rectangle COLLISION;

    private double velY = 0;
    private double velX = 0;
    public final Rectangle rectTest = new Rectangle(490, 388, 525,30);

    {
        rectTest.setFill(Color.YELLOW);
        rectTest.setOpacity(0.25);
    }

    private final AnimationTimer movementAnim = new AnimationTimer() {
        @Override
        public void handle(long l) {
            double posX = getImgView().getX() + getVelX();
            double posY = getImgView().getY() + getVelY();
            // Коллизия проверяет местоположение игрока наперед, чтобы не попасть на стенку.
            getCOLLISION().setX(posX);
            getCOLLISION().setY(posY);
            if (rectTest.intersects(getCOLLISION().getBoundsInLocal())) {
                System.out.println("COLLISION!");
                return;
            }

            getImgView().setX(getImgView().getX() + getVelX());
            getImgView().setY(getImgView().getY() + getVelY());
            setPosition(getImgView().getX(), getImgView().getY());
            getCOLLISION().setX(getImgView().getX());
            getCOLLISION().setY(getImgView().getY());
        }
    };
    public Player(int x, int y) {
        this.imgView.setX(x);
        this.imgView.setY(y);
        this.COLLISION = new Rectangle(x, y, imgView.getImage().getWidth(), imgView.getImage().getHeight());
        this.COLLISION.setFill(Color.BLUE);
        this.COLLISION.setOpacity(0.25);
    }

    public Player() {
        this.COLLISION = new Rectangle(this.imgView.getX(), this.imgView.getY(), imgView.getImage().getWidth(), imgView.getImage().getHeight());
        this.COLLISION.setFill(Color.BLUE);
        this.COLLISION.setOpacity(0.25);
    }

    public Image[] getSKELETON_IDLE(Status.View view) {
        if (view == Status.View.RIGHT) {
            return SKELETON_IDLE_RIGHT;
        } else {
            return SKELETON_IDLE_LEFT;
        }
    }


    public Image[] getSKELETON_WALK(Status.View view) {
        if (view == Status.View.RIGHT) {
            return SKELETON_WALK_RIGHT;
        } else {
            return SKELETON_WALK_LEFT;
        }
    }

    public void setPosition(double x, double y) {
        getImgView().setX(x);
        getImgView().setY(y);
    }

    public void move() {
        getMovementAnim().start();
    }

    public AnimationTimer getMovementAnim() {
        return movementAnim;
    }


    public Status getAction() {
        return this.action;
    }

    public void setAction(Status action, Status.View view) {
        if (action == Status.IDLE) {
            setImgArray(getSKELETON_IDLE(view));
        }
        if (action == Status.WALK) {
            setImgArray(getSKELETON_WALK(view));
        }
        setView(view);
    }

    public Status.View getView() {
        return this.view;
    }

    public void setView(Status.View newView) {
        this.view = newView;
    }


    public ImageView getImgView() {
        return this.imgView;
    }

    public void setImgView(ImageView imgView) {
        this.imgView = imgView;
    }

    public Image[] getImgArray() {
        return this.imgArray;
    }

    public void setImgArray(Image[] imgArray) {
        this.imgArray = imgArray;
        getImgView().setImage(imgArray[0]);
    }

    public Rectangle getCOLLISION() {
        return COLLISION;
    }


    public double getVelY() {
        return velY;
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }

    public double getVelX() {
        return velX;
    }

    public void setVelX(double velX) {
        this.velX = velX;
    }


}