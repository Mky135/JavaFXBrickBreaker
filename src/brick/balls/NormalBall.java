package brick.balls;

import brick.util.Board;
import brick.util.Brick;
import javafx.scene.paint.Paint;

import java.util.ArrayList;

public class NormalBall extends Ball{

    private double dx = .5, dy = .5;
    private Board board;
    private int damage;

    public NormalBall(int centerX, int centerY, double theta, int damage, Board board) {
        super(centerX, centerY, 5, Paint.valueOf("White"), theta);
        dx *= Math.cos(getTheta());
        dy *= -Math.sin(getTheta());
        this.board = board;
        this.damage = damage;
        move();
    }

    @Override
    public void move() {
        if(getCenterY() - getRadius() <= 0 || getCenterY() + getRadius() >= 550)
            dy = -dy;


        if(getCenterX() - getRadius() <= 0 || getCenterX() + getRadius() >= 400)
            dx = -dx;

        ArrayList<Brick> bricks = board.getZone(this);
        for (int i = 0; i < bricks.size(); i++) {
                Brick brick = bricks.get(i);

                if(collidedBottom(brick) || collidedTop(brick)) {
                    dy = -dy;
                    brick.hit(damage);
                }
                else if(collidedLeft(brick) || collidedRight(brick)) {
                    dx = -dx;
                    brick.hit(damage);
                }
        }

        setCenterX(getCenterX() + dx);
        setCenterY(getCenterY() + dy);
    }


    @Override
    void onHit() {

    }
}
