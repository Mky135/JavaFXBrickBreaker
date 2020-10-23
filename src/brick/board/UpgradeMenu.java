package brick.board;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

public class UpgradeMenu extends AnchorPane {

    private boolean retracted;
    public UpgradeMenu(Button button)
    {
        //Todo: Find good way to retract menu
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
        setUpUpgrades();
    }

    private void setUpUpgrades()
    {
        Label whiteSpeedUpgrade = new Label("Upgrade WhiteBall Speed (1)");
        whiteSpeedUpgrade.setLayoutX(10);
        whiteSpeedUpgrade.setLayoutY(10);
        Button whiteSpeedUpgradeButton = new Button("1");
        whiteSpeedUpgradeButton.setLayoutX(200);
        whiteSpeedUpgradeButton.setLayoutY(10);
        this.getChildren().add(whiteSpeedUpgrade);
        this.getChildren().add(whiteSpeedUpgradeButton);
    }
}
