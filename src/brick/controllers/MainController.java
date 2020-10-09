package brick.controllers;

import brick.balls.NormalBall;
import brick.board.Board;
import brick.board.UpgradeMenu;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    AnchorPane score;

    @FXML
    Label scoreLabel;

    @FXML
    AnchorPane board;

    @FXML
    Button upgradeButton;

    AnimationTimer animation_timer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Board gameBoard = new Board(board, scoreLabel, "brick/board/board1.xml");
        UpgradeMenu menu = new UpgradeMenu(upgradeButton);
        board.getChildren().add(menu);

        animation_timer = new AnimationTimer()
        {
            public void handle(long timestamp_of_current_frame)
            {
                for (NormalBall nb : gameBoard.getNormalBalls()) {
                    nb.move();
                }
            }
        };

        animation_timer.start() ;
    }
}
