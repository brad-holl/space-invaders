package game.core;

import game.App;
import javafx.scene.canvas.GraphicsContext;

public class Bunker extends GameObject implements BunkerPlan{
    private BunkerState healthy;
    private BunkerState half;
    private BunkerState low;
    private BunkerState state;
    public Bunker() {
        super(100, App.HEIGHT-200, 50, 50);
        setHealth((int) (App.gameStats.getProjectileDamage() * 3));
        healthy = new HealthyState(this);
        half = new HalfState(this);
        low = new LowState(this);
        state = healthy;
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

    }

    public void takeDamage(){
        state.changeState();
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillRect(xPos, yPos, width, height);
    }

    @Override
    public void setXPos(int xPos) {
        if (xPos <= 100){
            this.xPos = 100;
            return;
        }
        if (xPos >= App.WIDTH - 100){
            this.xPos = App.WIDTH - 100;
            return;
        }
        this.xPos = xPos;
    }

    @Override
    public void setYPos(int yPos) {
        if (yPos >= App.HEIGHT ){
            this.yPos = App.HEIGHT - 100;
            return;
        }
        if (yPos <= 200 ){
            this.yPos = App.HEIGHT - 100;
            return;
        }
        this.yPos = yPos;
        System.out.println(yPos);
    }

    @Override
    public void setBunkerWidth(int width) {
        if (width < 50) {
            this.width = 50;
            return;
        }
        this.width = width;
    }

    @Override
    public void setBunkerHeight(int height) {
        if (height < 50) {
            this.height = 50;
            return;
        }
        this.height = height;
    }

    public BunkerState getHealthy() {
        return healthy;
    }

    public BunkerState getHalf() {
        return half;
    }

    public BunkerState getLow() {
        return low;
    }

    public BunkerState getState() {
        return state;
    }

    public void setState(BunkerState state) {
        this.state = state;
    }


    @Override
    public String toString() {
        return "Bunker Position: (" + xPos + "," + yPos + "), Height: " + height + ", Width: " + width;
    }

}
