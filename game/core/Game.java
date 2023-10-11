package game.core;

import javafx.scene.Scene;


/**
 * Main game loop a thread to keep ui and game processing separate
 * so that it does not freezes UI.
 */
public class Game extends Thread{

    private static final boolean RENDER_TIME = true; // should display frames and updates
    private boolean running = true; // is game running.
    private static final double UPS = 90; // number of updates per second
    private static final double FPS = 120; // number of frames per second.
    private static final int FRAME_RENDER_TIME = 16; // in ms
    private GameScreen gameScreen; // used to draw stuff on canvas.
    private Scene scene;

    /**
     * Constructor of the Game loop class
     * @param scene scene that this screen is in to add key listeners to.
     */
    public Game(GameScreen gameScreen, Scene scene) {
        this.gameScreen = gameScreen;
        this.scene = scene;
    }

    @Override
    public void run() { // Main game loop
        long startTime = System.nanoTime();
        final double updateTime = 1000000000 / UPS;
        final double renderTime = 1000000000 / FPS;
        double updateD = 0, renderD = 0;
        int frames = 0, updates = 0;
        long currentTimeMillis = System.currentTimeMillis();

        while (running) {

            long timeNow = System.nanoTime();
            updateD += (timeNow - startTime) / updateTime;
            renderD += (timeNow - startTime) / renderTime;
            startTime = timeNow;

            if (updateD >= 1) {

                gameScreen.update();

                updates++;
                updateD--;
            }

            if (renderD >= 1) {

                gameScreen.render();

                frames++;
                renderD--;
            }

            if (System.currentTimeMillis() - currentTimeMillis > 1000) {
                if (RENDER_TIME) {
                    System.out.println(String.format("UPS: %s, FPS: %s", updates, frames));
                }
                frames = 0;
                updates = 0;
                currentTimeMillis += 1000;
            }
        }

    }


    public void stopLoop(){
        running = false; // stop game which in turn stops the game.
    }


}
