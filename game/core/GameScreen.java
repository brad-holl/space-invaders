package game.core;

import game.App;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameScreen extends Pane {
    private Canvas canvas;
    private GraphicsContext gc;
    private Game game;
    private Player player;
    private List<Projectile> projectiles;
    private List<Bunker> bunkers;
    private List<Enemy> enemies;

    private final Random random = new Random();
    private String key = "";
    private App app;

    public GameScreen(App app, Scene scene) {

        this.app = app;
        init();
        scene.addEventHandler(KeyEvent.KEY_PRESSED, this::pressed);
        scene.addEventHandler(KeyEvent.KEY_RELEASED, this::released);
        game = new Game(this, getScene());
        game.start();


    }



    private void init() {
        canvas = new Canvas(App.WIDTH, App.HEIGHT);
        getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();
        GameStats stats = App.gameStats;
        player = new Player((int) stats.getStartX(), (int) stats.getStartY(), 50, 50);
        projectiles = new ArrayList<>();
        bunkers = new ArrayList<>();
        spawnBunkers();
        enemies = new ArrayList<>();
        spawnEnemies();
    }
    String direction = "d";
    public void update() {
        player.move(key);
        if (!player.isAlive()){
            endGame(false);
        }
        if (enemies.isEmpty()){
            endGame(true);
        }

        player.update();
        for (int i = 0; i < projectiles.size(); i++) {
            GameObject cast = (GameObject) projectiles.get(i);
            if (!cast.isAlive()) {
                projectiles.remove(projectiles.get(i));
                continue;
            }
            GameObject project = (GameObject) projectiles.get(i);
            // projectile collision detection against bunkers
            for (int j = 0; j < bunkers.size(); j++) {
                Bunker bunker = bunkers.get(j);
                if (project.collisionDetected(bunker)) {
                    bunker.takeDamage();
                    projectiles.remove(projectiles.get(i));
                    break;
                }
            }
            project.update();
        }
        for (int i = 0; i < projectiles.size(); i++) {
            if (projectiles.get(i) instanceof PlayerProjectile) {
                for (int j = 0; j < enemies.size(); j++) {
                    Enemy enemy = enemies.get(j);
                    if (((PlayerProjectile) projectiles.get(i)).collisionDetected(enemy)){
                        projectiles.remove(projectiles.get(i));
                        enemy.takeDamage((int) App.gameStats.getProjectileDamage());
                        break;
                    }
                }
            }
            else if (projectiles.get(i) instanceof EnemyProjectile) {
                if (((EnemyProjectile) projectiles.get(i)).collisionDetected(player)){
                    projectiles.remove(projectiles.get(i));
                    player.takeLife();
                    player.setxPos((int) App.gameStats.getStartX());
                    break;
                }
            }
        }
        handleProjectileCollisions();
        for (int i = 0; i < bunkers.size(); i++) {
            Bunker bunker = bunkers.get(i);
            if (!bunker.isAlive()) {
                bunkers.remove(bunker);
                continue;
            }
            for (Enemy enemy:enemies) {
                if (bunker.collisionDetected(enemy)){
                    bunkers.remove(bunker);
                    break;
                }

            }

            bunker.update();
        }


        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            if (enemy.getyPos() > App.HEIGHT - 100){
                endGame(false);
            }

            enemy.update();
            if (!enemy.isAlive()) {
                enemies.remove(enemy);
                continue;
            }

            int chance = random.nextInt(500);
            if (chance == 16) {
                enemy.shoot(this);
            }
        }

        for (Enemy enemy : enemies) {
            if (player.collisionDetected(enemy)) {
                endGame(false);
                return;
            }
        }
        int lastEnemy = enemies.size() - 1;
        if (direction.equalsIgnoreCase("a")) {
            lastEnemy = 0;
        }

        if (!enemies.isEmpty()) {
            if (enemies.get(lastEnemy).canMove(direction, enemies)) {
                for (Enemy enemy : enemies) {
                    enemy.move(direction);
                }
            } else {
                // Move enemies down when reaching the border
                for (Enemy enemy : enemies) {
                    enemy.setyPos((int) (enemy.getyPos() + enemy.getHeight()));  // Move down by one enemy height
                }
                direction = switchDirections(direction);
            }
        }

    }

    private void handleProjectileCollisions() {
        for (int i = 0; i < projectiles.size(); i++) {
            GameObject proj1 = (GameObject) projectiles.get(i);

            if (proj1 instanceof PlayerProjectile) {
                for (int j = 0; j < projectiles.size(); j++) {
                    if (i != j) {
                        GameObject proj2 = (GameObject) projectiles.get(j);
                        if (proj2 instanceof EnemyProjectile) {
                            if (proj1.collisionDetected(proj2)) {
                                projectiles.remove(proj1);
                                projectiles.remove(proj2);
                                i = Math.max(0, i-2);
                                break;
                            }
                        }
                    }
                }
            }
        }

    }


    private String switchDirections(String direction){
        if (direction.equalsIgnoreCase("d")){
            return "a";
        }else{
            return  "d";
        }
    }

    private void endGame(boolean win){
        game.stopLoop();
        app.showEndGame(win);
    }

    public Game getGame() {
        return game;
    }

    public void render() {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, App.WIDTH, App.HEIGHT);
        // render players
        player.render(gc);
        // render projectiles
        for (int i = 0; i < projectiles.size(); i++) {
            ((GameObject) projectiles.get(i)).render(gc);
        }
        // render bunkers
        for (int i = 0; i < bunkers.size(); i++) {
            bunkers.get(i).render(gc);
        }

        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            enemy.render(gc);
        }
    }

    public boolean isPlayerProjectilePresent() {
        for (Projectile p : projectiles) {
            if (p instanceof PlayerProjectile) return true;
        }
        return false;
    }

    public void spawnPlayerProjectile() {
        ProjectileFactory factory = new PlayerProjectileFactory();
        Projectile playerProjectile = factory.createProjectile(
                this.player.getxPos() + (this.player.getWidth() / 2.0),
                this.player.getyPos(),
                "slow"
        );
        projectiles.add(playerProjectile);
    }

    public void spawnEnemyProjectile(Enemy enemy) {
        ProjectileFactory factory = new EnemyProjectileFactory();
        Projectile enemyProjectile = factory.createProjectile(
                enemy.getxPos() + (enemy.getWidth() / 2.0),
                enemy.getyPos(),
                enemy.getStrategy()
        );
        if(enemy.getStrategy().equals("slow")){
            enemyProjectile.setStrategy(new SlowProjectile());
        } else if (enemy.getStrategy().equals("fast")) {
            enemyProjectile.setStrategy(new FastProjectile());
        }
        projectiles.add(enemyProjectile);
    }

    private void spawnBunkers() {
        List<BunkerStats> bunkerStats = App.gameStats.getBunkerStats();
        for (BunkerStats bs : bunkerStats) {
            ConcreteBunkerBuilder builder = new ConcreteBunkerBuilder();
            BunkerDirector director = new BunkerDirector(builder);

            Bunker bunker = director.createBunker(bs.getPosx(),bs.getPosy(),bs.getHeight(),bs.getWidth());

            bunkers.add(bunker);


        }

        for (Bunker bunker : bunkers) {
            System.out.println(bunker.toString()); // Assuming Bunker has a well-defined toString method
        }


    }

    public boolean areEnemyProjectilePresent() {
        int count = 0;
        for (Projectile p : projectiles) {
            if (p instanceof EnemyProjectile) count++;
        }
        return count > 2;
    }



    private void spawnEnemies(){

        List<EnemyStats> enemyStats = App.gameStats.getEnemyStats();
        int enemyWidth = 30;
        int enemyHeight = 30;
        for (EnemyStats es: enemyStats){
            ConcreteEnemyBuilder builder = new ConcreteEnemyBuilder();
            EnemyDirector director = new EnemyDirector(builder);
            Enemy enemy = director.constructEnemy((int) es.getX(), (int) es.getY(), enemyWidth, enemyHeight, 100, 1, es.getStrategy());

            enemies.add(enemy);
        }

    }




    /**
     * Event handler for key board keys
     *
     * @param event key event when key is pressed.
     */
    public void pressed(KeyEvent event) {

        if (event.getCode() == KeyCode.SPACE) {
            player.shoot(this);
        }
        //player.move(event.getCode().toString());
        if (event.getCode() == KeyCode.RIGHT){
            key = "d";
        }
        if (event.getCode() == KeyCode.LEFT){
            key = "a";
        }
    }

    /**
     * Event handler for key board keys
     *
     * @param event key event when key is released.
     */
    public void released(KeyEvent event) {

        if (event.getCode() == KeyCode.SPACE) {
            player.shoot(this);
        }
        if (event.getCode() == KeyCode.RIGHT){
            key = "";
        }
        if (event.getCode() == KeyCode.LEFT){
            key = "";
        }
        //player.move(event.getCode().toString());

    }




}
