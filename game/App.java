package game;

import game.core.EndGame;
import game.core.GameScreen;
import game.core.ConfigReader;
import game.core.GameStats;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application {

    public static final String TITLE = "Space Invaders";
    public static int WIDTH = 700;
    public static int HEIGHT = 600;
    public static GameStats gameStats;

    private Stage stage;
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setTitle(TITLE);
        Pane pane = new Pane();
        Scene scene = new Scene(pane, WIDTH, HEIGHT);
        GameScreen gameScreen = new GameScreen(this, scene);
        pane.getChildren().add(gameScreen);
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> gameScreen.getGame().stopLoop());
        stage.show();

    }
    public void showEndGame(boolean win){
        Platform.runLater(()->{
           stage.setScene(new Scene(new EndGame(win)));
        });

    }
    public static void main(String[] args) {
        String configPath;
        if (args.length > 0) {
            configPath = args[0];
        } else {
            configPath = "config.json";
        }
        // parse the file:
        ConfigReader reader = new ConfigReader();
        gameStats = reader.parse(configPath);
        WIDTH = (int) gameStats.getWidth();
        HEIGHT = (int) gameStats.getHeight();

        launch(args);

    }
}
