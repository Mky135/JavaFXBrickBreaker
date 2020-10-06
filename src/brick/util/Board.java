package brick.util;

import brick.balls.NormalBall;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.Random;

public class Board {

    private final ArrayList<Brick> bricks;
    private AnchorPane board;
    private Random r = new Random();
    private NormalBall[] normalBalls;
    private Label scoreLabel;
    private int score = 0;

    public Board(int amountOfBricks, int amountOfBalls, AnchorPane board, Label scoreLabel)
    {
        bricks = new ArrayList<>();
        normalBalls = new NormalBall[amountOfBalls];
        this.board = board;
        this.scoreLabel = scoreLabel;

        for (int i = 0; i < amountOfBricks; i++) {
            Brick brick = new Brick(r.nextInt((int) board.getPrefWidth() - 30), r.nextInt((int) (board.getPrefHeight()- 15)),
                    30, 15, 10, this);
            brick.setFill(Paint.valueOf("Yellow"));
            bricks.add(brick);
        }

        for (int i = 0; i < normalBalls.length; i++) {
            normalBalls[i] = new NormalBall(240, 200, r.nextDouble() * Math.PI, 11, this);
        }


        setBoard();
        updateScore();
    }

    public void setBoard()
    {
        board.getChildren().addAll(bricks);
        board.getChildren().addAll(normalBalls);
        for (Brick brick: bricks) {
            board.getChildren().add(brick.getLife());
        }
    }

    public ArrayList<Brick> getBricks() {
        return bricks;
    }

    public void destroyBrick(Brick brick)
    {
        bricks.remove(brick);
        board.getChildren().remove(brick.getLife());
        board.getChildren().remove(brick);
    }

    public void addToScore(int n)
    {
        score += n;
        updateScore();
    }

    public NormalBall[] getNormalBalls() {
        return normalBalls;
    }

    public void updateScore()
    {
        scoreLabel.setText("Score: " + score);
    }
}
