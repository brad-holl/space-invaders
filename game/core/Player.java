package game.core;

import game.App;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.net.URISyntaxException;
import java.util.Objects;

public class Player extends GameObject implements Move, Shoot{
    private static final double SPEED_MULTIPLIER = 1;
    private int lives;
    public Player(int xPos, int yPos, int width, int height) {
        super(xPos, yPos, width, height);
        try {
            Image ship = new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("ship.png")).toURI().toString());

            setSprite(ship);
            setSpeed((int) App.gameStats.getSpeed());
            lives = (int) App.gameStats.getLives();
            Color color = Color.GREEN;
            String playerColor = App.gameStats.getPlayerColor();
            if (playerColor.equalsIgnoreCase("red")){
                color = Color.RED;
            }else if (playerColor.equalsIgnoreCase("blue")){
                color = Color.BLUE;
            }else if (playerColor.equalsIgnoreCase("green")){
                color = Color.GREEN;
            }
            setColor(color);
            setHealth(100);

            Image tintedShip = tintImage(ship, color);
            setSprite(tintedShip);

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean collisionDetected(GameObject other) {
        boolean colliding = true;
        if (this.getxPos() + this.getWidth() < other.getxPos()) colliding = false;
        if (this.getxPos() > other.getxPos() + other.getWidth()) colliding = false;
        if (this.getyPos() + this.getHeight() < other.getyPos()) colliding = false;
        if (this.getyPos() > other.getyPos() + other.getHeight()) colliding = false;
        return colliding;
    }

    @Override
    public void update() {
        if (lives <= 0) setAlive(false);
    }

   @Override
    public void render(GraphicsContext gc) {
       if (getSprite() != null) {
           gc.drawImage(getSprite(), xPos, yPos, width, height);
       } else {
           gc.setFill(getColor());
           gc.fillRect(xPos, yPos, width, height);
       }

       gc.setFill(Color.GREEN);
       gc.setFont(new Font(26));
//        gc.fillText("Lives " + lives, LIFE_POS_X, LIFE_POS_y);
    }

    @Override
    public void move(String key) {
        if (key.equalsIgnoreCase("d")){
            if (xPos <= App.WIDTH - width) // Updated boundary check
                xPos += getSpeed() * SPEED_MULTIPLIER;
        }
        else if (key.equalsIgnoreCase("a")){
            if (xPos >= 0) // Updated boundary check
                xPos -= getSpeed() * SPEED_MULTIPLIER;
        }

    }




    public void takeLife(){
        lives--;
    }

    @Override
    public void shoot(GameScreen gameScreen) {
        if (!gameScreen.isPlayerProjectilePresent()){
            gameScreen.spawnPlayerProjectile();

        }
    }

    private Image tintImage(Image inputImage, Color tintColor) {
        int width = (int) inputImage.getWidth();
        int height = (int) inputImage.getHeight();

        WritableImage outputImage = new WritableImage(width, height);
        PixelReader reader = inputImage.getPixelReader();
        PixelWriter writer = outputImage.getPixelWriter();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color originalColor = reader.getColor(x, y);
                if (originalColor.getOpacity() > 0) {
                    writer.setColor(x, y, tintColor.interpolate(originalColor, 0.5));
                } else {
                    writer.setColor(x, y, originalColor);
                }
            }
        }
        return outputImage;
    }



}
