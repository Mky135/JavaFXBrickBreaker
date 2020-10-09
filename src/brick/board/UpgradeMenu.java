package brick.board;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class UpgradeMenu extends AnchorPane {

    private boolean retracted;
    public UpgradeMenu(Button button)
    {
        super();
        retracted = true;
        button.setOnAction(event -> {
            retracted = !retracted;
            if(retracted) {
                setLayoutY(550);
                setPrefHeight(0);
            }
            else {
                setLayoutY(275);
                setPrefHeight(275);
            }
            button.setLayoutY(getLayoutY() - button.getPrefHeight());
        });
        setLayoutY(550);
        setPrefWidth(275);

        setStyle("-fx-background-color: white");
    }
}
