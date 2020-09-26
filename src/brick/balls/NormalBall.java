package brick.balls;

import brick.Main;
import brick.controllers.MainController;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class NormalBall extends Ball{

    private double dx = .5, dy = .5;
    private double theta;

    public NormalBall(int centerX, int centerY, double theta) {
        super(centerX, centerY, 5, Paint.valueOf("White"));
        this.theta = theta;
        dx *= Math.cos(theta);
        dy *= Math.sin(theta);
        move();
    }

    @Override
    public void move() {
        if(getCenterX() - getRadius() <= 0 || getCenterX() + getRadius() >= 400)
            dx = -dx;

        if(getCenterY() - getRadius() <= 0 || getCenterY() + getRadius() >= 550)
            dy = -dy;

        setCenterX(getCenterX() + dx);
        setCenterY(getCenterY() + dy);
    }

    @Override
    void onHit() {

    }
}
