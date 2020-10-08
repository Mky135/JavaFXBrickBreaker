package brick.board;

import brick.balls.Ball;
import brick.balls.NormalBall;
import brick.util.Brick;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.Random;

public class Board {

    private ArrayList<Brick> zone1;
    private ArrayList<Brick> zone2;
    private ArrayList<Brick> zone3;
    private ArrayList<Brick> zone4;
    private final ArrayList<ArrayList<Brick>> bricks;
    private final AnchorPane board;
    private final Random r = new Random();
    private final ArrayList<NormalBall> normalBalls;
    private final Label scoreLabel;

    private int score = 0;
    private String boardXml;

    public Board(AnchorPane board, Label scoreLabel, String boardXml) {
        bricks = new ArrayList<>();
        zone1 = new ArrayList<>();
        zone2 = new ArrayList<>();
        zone3 = new ArrayList<>();
        zone4 = new ArrayList<>();
        normalBalls = new ArrayList<>();

        this.board = board;
        this.scoreLabel = scoreLabel;
        this.boardXml = boardXml;

        normalBalls.add(new NormalBall(r.nextInt((int) board.getPrefWidth() - 10) + 5, r.nextInt((int) board.getPrefHeight() - 10) + 5, r.nextDouble() * Math.PI + .01, 10, this));

        setBoard(boardXml);
        updateScore();
    }

    public void setBoard() {
        for (ArrayList<Brick> brickArray: bricks) {
            for (Brick brick : brickArray) {
                board.getChildren().add(brick);
                board.getChildren().add(brick.getLife());
            }
        }
        board.getChildren().addAll(normalBalls);
    }

    public void setBoard(String xml)
    {
        BrickPlacer brickPlacer = new BrickPlacer(xml, this);
        zone1 = brickPlacer.getZone1();
        zone2 = brickPlacer.getZone2();
        zone3 = brickPlacer.getZone3();
        zone4 = brickPlacer.getZone4();
        bricks.add(zone1);
        bricks.add(zone2);
        bricks.add(zone3);
        bricks.add(zone4);
        setBoard();
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
        if (zone1.contains(brick))
            zone1.remove(brick);
        else if (zone2.contains(brick))
            zone2.remove(brick);
        else if (zone3.contains(brick))
            zone3.remove(brick);
        else zone4.remove(brick);

        board.getChildren().remove(brick.getLife());
        board.getChildren().remove(brick);

        if(zone1.size() == 0 && zone2.size() == 0 && zone3.size() == 0 && zone4.size() == 0)
            newBoard();
    }

    private void newBoard(){
        board.getChildren().removeAll(normalBalls);
        bricks.clear();
        zone1.clear();
        zone2.clear();
        zone3.clear();
        zone4.clear();

        setBoard(boardXml.substring(0, boardXml.length()-5) + (r.nextInt(1)+1) + ".xml");
        updateScore();
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
