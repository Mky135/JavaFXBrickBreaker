package brick.board;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

public class UpgradeMenu extends AnchorPane {

    private boolean retracted;
    public UpgradeMenu(Button button)
    {
        super();
        button.setFocusTraversable(false);
        retracted = false;
        button.setOnAction(event -> {
            retracted = !retracted;
            if(retracted) {
                setLayoutY(550);
                setPrefHeight(0);
                button.setStyle("-fx-background-image: url('/brick/images/foldUp.png')");
            }
            else {
                setLayoutY(275);
                setPrefHeight(275);
                button.setStyle("-fx-background-image: url('/brick/images/foldDown.png')");
            }
            button.setLayoutY(getLayoutY() - button.getPrefHeight());
        });
        setLayoutY(550);
        setPrefWidth(275);

        setStyle("-fx-background-color: white");
        button.fire();
    }
}
