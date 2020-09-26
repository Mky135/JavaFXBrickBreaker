package brick.controllers;

import brick.balls.NormalBall;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    AnchorPane score;

    @FXML
    AnchorPane board;

    AnimationTimer animation_timer;
    public static Rectangle brick;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Random r = new Random();
        brick = new Rectangle(250, 100, 50, 50);
        brick.setFill(Paint.valueOf("Yellow"));
        NormalBall[] nbs = new NormalBall[10];
        for (int i = 0; i < nbs.length; i++) {
            nbs[i] = new NormalBall(r.nextInt(400), r.nextInt(400), r.nextDouble() * (Math.PI));
        }

        board.getChildren().addAll(nbs);
        board.getChildren().add(brick);

        animation_timer = new AnimationTimer()
        {
            public void handle(long timestamp_of_current_frame)
            {
                for (NormalBall nb : nbs) {
                    nb.move();
                }
            }
        };

        animation_timer.start() ;
    }
}
