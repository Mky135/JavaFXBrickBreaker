package brick.balls;

import brick.util.Brick;
import brick.util.Point;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public abstract class Ball extends Circle {

    public static final int NUM_OF_BALL_TYPES = 1;
    double dx, dy;
    private final double theta;
    double damage;

    public Ball(double centerX, double centerY, double radius, Paint fill, double theta) {
        super(centerX, centerY, radius, fill);
        this.theta = theta;
    }

    private Point rightOfBall() {
        return new Point(getCenterX() + getRadius(), getCenterY());
    }

    private Point topOfBall() {
        return new Point(getCenterX(), getCenterY() - getRadius());
    }

    private Point leftOfBall() {
        return new Point(getCenterX() - getRadius(), getCenterY());
    }

    private Point bottomOfBall() {
        return new Point(getCenterX(), getCenterY() + getRadius());
    }

    public boolean collided(Rectangle brick, Point posOfBall) {

        return posOfBall.getX() >= brick.getX() &&
                posOfBall.getY() >= brick.getY() &&
                posOfBall.getX() <= brick.getX() + brick.getWidth() &&
                posOfBall.getY() <= brick.getY() + brick.getHeight();
    }

    public boolean collidedBottom(Rectangle brick) {
        return collided(brick, topOfBall());
    }

    public boolean collidedTop(Rectangle brick) {
        return collided(brick, bottomOfBall());
    }

    public boolean collidedLeft(Rectangle brick) {
        return collided(brick, rightOfBall());
    }

    public boolean collidedRight(Rectangle brick) {
        return collided(brick, leftOfBall());
    }

    public double getTheta() {
        return theta;
    }

    public void increaseSpeed(double val)
    {
        dx += val;
        dy += val;
    }

    public void increaseDamage(double num)
    {
        damage += num;
    }

    abstract void move();

    abstract void onHit(Brick brick);
}
