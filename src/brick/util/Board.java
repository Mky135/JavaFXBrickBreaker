package brick.util;

import brick.balls.Ball;
import brick.balls.NormalBall;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.Random;

public class Board {

    private final ArrayList<Brick> zone1;
    private final ArrayList<Brick> zone2;
    private final ArrayList<Brick> zone3;
    private final ArrayList<Brick> zone4;
    private final ArrayList<Brick> bricks;
    private final int amountOfBricks;
    private AnchorPane board;
    private Random r = new Random();
    private ArrayList<NormalBall> normalBalls;
    private Label scoreLabel;
    private int score = 0;

    public Board(int amountOfBricks, AnchorPane board, Label scoreLabel) {
        bricks = new ArrayList<>();
        zone1 = new ArrayList<>();
        zone2 = new ArrayList<>();
        zone3 = new ArrayList<>();
        zone4 = new ArrayList<>();
        normalBalls = new ArrayList<>();

        this.board = board;
        this.scoreLabel = scoreLabel;
        this.amountOfBricks = amountOfBricks;

        for (int i = 0; i < amountOfBricks; i++) {
            Brick brick = new Brick(r.nextInt((int) board.getPrefWidth() - 30), r.nextInt((int) (board.getPrefHeight() - 15)),
                    30, 15, 10, this);
            brick.setFill(Paint.valueOf("Yellow"));
            bricks.add(brick);
        }

        normalBalls.add(new NormalBall(r.nextInt((int) board.getPrefWidth() - 10) + 5, r.nextInt((int) board.getPrefHeight() - 10) + 5, r.nextDouble() * Math.PI + .01, 10, this));

        setBoard();
        updateScore();
        setZones();
    }

    public void setZones() {
        for (Brick brick : bricks) {
            if (brick.getX() <= board.getPrefWidth() / 2 && brick.getY() <= board.getPrefHeight() / 2)
                zone1.add(brick);
            else if (brick.getX() > board.getPrefWidth() / 2 && brick.getY() <= board.getPrefHeight() / 2)
                zone2.add(brick);
            else if (brick.getX() <= board.getPrefWidth() / 2 && brick.getY() > board.getPrefHeight() / 2)
                zone3.add(brick);
            else
                zone4.add(brick);
        }
    }

    public void setBoard() {
        board.getChildren().addAll(bricks);
        board.getChildren().addAll(normalBalls);
        for (Brick brick : bricks) {
            board.getChildren().add(brick.getLife());
        }
    }

    public ArrayList<Brick> getZone(Ball ball) {
        if (ball.getCenterX() <= board.getPrefWidth() / 2 && ball.getCenterY() <= board.getPrefHeight() / 2)
            return zone1;
        else if (ball.getCenterX() > board.getPrefWidth() / 2 && ball.getCenterY() <= board.getPrefHeight() / 2)
            return zone2;
        else if (ball.getCenterX() <= board.getPrefWidth() / 2 && ball.getCenterY() > board.getPrefHeight() / 2)
            return zone3;
        else
            return zone4;
    }

    public void destroyBrick(Brick brick) {
        bricks.remove(brick);

        if (zone1.contains(brick))
            zone1.remove(brick);
        else if (zone2.contains(brick))
            zone2.remove(brick);
        else if (zone3.contains(brick))
            zone3.remove(brick);
        else zone4.remove(brick);

        board.getChildren().remove(brick.getLife());
        board.getChildren().remove(brick);

        if(bricks.size() == 0)
            newBoard();
    }

    private void newBoard(){
        board.getChildren().removeAll(normalBalls);
        bricks.clear();
        zone1.clear();
        zone2.clear();
        zone3.clear();
        zone4.clear();

        for (int i = 0; i < amountOfBricks; i++) {
            Brick brick = new Brick(r.nextInt((int) board.getPrefWidth() - 30), r.nextInt((int) (board.getPrefHeight() - 15)),
                    30, 15, 10, this);
            brick.setFill(Paint.valueOf("Yellow"));
            bricks.add(brick);
        }

        setBoard();
        updateScore();
        setZones();
    }

    public void addToScore(int n) {
        score += n;
        updateScore();
    }

    public double width() {
        return board.getPrefWidth();
    }

    public double height() {
        return board.getPrefHeight();
    }

    public ArrayList<NormalBall> getNormalBalls() {
        return normalBalls;
    }

    public void updateScore() {
        scoreLabel.setText("Score: " + score);
    }
}
