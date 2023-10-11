package game.core;

import game.App;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class EndGame extends Group {
    private Label title;
    private final String TITLE = "";
    private VBox container;


    public EndGame(boolean win) {
        init();

        // Display "WIN" or "LOSE" based on game result
        Label resultLabel;
        if (win) {
            resultLabel = new Label(" WIN ");
        } else {
            resultLabel = new Label(" LOSE ");
        }
        resultLabel.setFont(new Font(36));

        // Add the title and the resultLabel to the container
        container.getChildren().addAll(title, resultLabel);
    }

    private void init() {
        title = new Label(TITLE);
        title.setFont(new Font(42));

        container = new VBox(10);
        container.setPrefWidth(700);
        container.setPrefHeight(600);
        container.setLayoutX(App.WIDTH / 2.0 - container.getPrefWidth() / 2);
        container.setLayoutY(App.HEIGHT / 2.0 - container.getPrefHeight() / 2);
        container.setAlignment(Pos.CENTER);

        getChildren().addAll(container);
    }
}
