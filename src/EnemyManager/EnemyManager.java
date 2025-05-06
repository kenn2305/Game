package EnemyManager;

import BlueGolem.BlueGolem;
import Game.Game;
import Items.Hamburger;
import Items.Items;
import Items.Potion;
import Levels.LevelManager;
import ObjectManager.ObjecManager;
import Player.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class EnemyManager {
    private Player player;
    private LevelManager levelManager;
    private ObjecManager objManager;
    private ArrayList<BlueGolem> blueGolems;
    private float spawnTimer = 0.0f; private ArrayList<Point> spawnPoint;
    private final ArrayList<BlueGolem> toRemove = new ArrayList<>();
    private Random rand = new Random();
    private Boolean spawned = false;
    public EnemyManager(Player player, LevelManager levelManager) {
        this.player = player;
        this.levelManager = levelManager;
        blueGolems = new ArrayList<>();
        spawnPoint = new ArrayList<>();
        rand = new Random();
        objManager = new ObjecManager(player);
        spawnPoint = levelManager.getCurrentLevel().getEnemySpawnPoints();
        addBlueGolem();
    }
    public void update(float delta) {
        updateBlueGolem(delta);
        for (BlueGolem blueGolem : new ArrayList<>(blueGolems) ) {
            blueGolem.update(delta);
        }
        removeBlueGolem();
        objManager.update(delta);
    }
    public void render(Graphics g , int offsetX , int offsetY) {
        ArrayList<BlueGolem> copy = new ArrayList<>(blueGolems);
        for (BlueGolem blueGolem : copy) {
            blueGolem.render(g, offsetX, offsetY);
        }
        objManager.render(g, offsetX, offsetY);
    }
    public void addBlueGolem() {
        if (spawned) return;
        for (Point point : spawnPoint) {
            int x = (int) (point.x * Game.GAME_SCALE * 2);
            int y = (int) (point.y * Game.GAME_SCALE * 2);
            BlueGolem blueGolem = new BlueGolem(x, y + 25 , levelManager, player);
            blueGolems.add(blueGolem);
        }
        spawned = true;
    }

    private void updateBlueGolem(float delta){
        spawnTimer += delta * 2;
        if (blueGolems.size() < spawnPoint.size()){
            if (spawnTimer > 60.0f) {
                int index = rand.nextInt(spawnPoint.size());
                Point point = spawnPoint.get(index);
                int x = (int) (point.x * Game.GAME_SCALE * 2);
                int y = (int) (point.y * Game.GAME_SCALE * 2);
                BlueGolem blueGolem = new BlueGolem(x, y + 25 , levelManager, player);
                blueGolems.add(blueGolem);
            }
        }
    }
    private void removeBlueGolem() {
        toRemove.clear();
        for (int i = blueGolems.size() - 1; i >= 0; i--) {
            BlueGolem g = blueGolems.get(i);
            if (g.getFinisedDeath()) {
                player.setEnemy_kill_num(player.getEnemy_kill_num() + 1);
                int random = rand.nextInt(4);
                if (random == 0) {
                    objManager.addPotion("BLUE",g.getBounds().x + g.getBounds().width / 2,
                            g.getBounds().y + g.getBounds().height / 2);
                } else if (random == 1) {
                    objManager.addPotion("RED",g.getBounds().x + g.getBounds().width / 2,
                             g.getBounds().y + g.getBounds().height / 2);
                } else if (random == 2) {
                    objManager.addHamburger("HAMBURGER",g.getBounds().x + g.getBounds().width / 2,
                            g.getBounds().y + g.getBounds().height / 2);
                }
                toRemove.add(g);
            }
        }
        blueGolems.removeAll(toRemove);
    }
    public void removeAll() {
        toRemove.clear();
        blueGolems.clear();
        spawnPoint.clear();
        objManager.removeAll();
        spawned = false;
    }
    public void setSpawnPoint(ArrayList<Point> spawnPoint) {
        this.spawnPoint = spawnPoint;
    }
}
