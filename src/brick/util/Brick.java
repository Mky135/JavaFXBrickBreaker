package brick.util;

import brick.board.Board;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Brick extends Rectangle {

    private final Label life;
    private double lifeForce;
    private final Board board;

    public Brick(int x, int y, double lifeForce, Board board)
    {
        super(x,y,30,15);
        setFill(Paint.valueOf("Yellow"));
        this.board = board;
        this.lifeForce = lifeForce;
        life = new Label(String.valueOf(lifeForce));
        life.setLayoutX(x + 2);
        life.setLayoutY(y - 1);
    }

    public Label getLife() {
        return life;
    }

    public void updateLabel(){
        life.setText(String.valueOf(lifeForce));
    }

    public void hit(int damage)
    {
        lifeForce -= damage;

        if(lifeForce <= 0)
        {
            board.addToScore((int) (damage + lifeForce));
            board.destroyBrick(this);
        }
        else
        {
            board.addToScore(damage);
        }
        updateLabel();
    }
}
