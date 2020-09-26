package brick.balls;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public abstract class Ball extends Circle {

    public Ball(double centerX, double centerY, double radius, Paint fill)
    {
        super(centerX, centerY,  radius, fill);

    }

    abstract void move();

    abstract void onHit();
}
