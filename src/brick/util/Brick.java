package brick.util;

import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;

public class Brick extends Rectangle {

    private Label life;
    private int lifeForce;
    private Board board;

    public Brick(int x, int y, int width, int height, int lifeForce, Board board)
    {
        super(x,y,width,height);
        this.board = board;
        this.lifeForce = lifeForce;
        life = new Label(String.valueOf(lifeForce));
        life.setLayoutX(x + (double) (width/2) - 4);
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
            board.addToScore(damage + lifeForce);
            board.destroyBrick(this);
        }
        else
        {
            board.addToScore(damage);
        }
        updateLabel();
    }
}
