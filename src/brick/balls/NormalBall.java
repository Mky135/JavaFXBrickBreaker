package brick.balls;

import brick.board.Board;
import brick.util.Brick;
import javafx.scene.paint.Paint;

import java.util.ArrayList;

public class NormalBall extends Ball{

    private final Board board;
    private final int damage;

    public NormalBall(int centerX, int centerY, double theta, int damage, Board board) {
        super(centerX, centerY, 5, Paint.valueOf("White"), theta);
        dx = .5;
        dy = .5;
        dx *= Math.cos(getTheta());
        dy *= -Math.sin(getTheta());
        this.board = board;
        this.damage = damage;
        move();
    }

    @Override
    public void move() {
        if(getCenterY() - getRadius() <= 0 || getCenterY() + getRadius() >= board.height())
            dy = -dy;


        if(getCenterX() - getRadius() <= 0 || getCenterX() + getRadius() >= board.width())
            dx = -dx;

        ArrayList<Brick> bricks = board.getZone(this);
        for (int i = 0; i < bricks.size(); i++) {
                Brick brick = bricks.get(i);

                if(collidedBottom(brick) || collidedTop(brick)) {
                    dy = -dy;
                    onHit(brick);
                }
                else if(collidedLeft(brick) || collidedRight(brick)) {
                    dx = -dx;
                    onHit(brick);
                }
        }

        setCenterX(getCenterX() + dx);
        setCenterY(getCenterY() + dy);
    }

    @Override
    void onHit(Brick brick) {
        brick.hit(damage);
    }
}
