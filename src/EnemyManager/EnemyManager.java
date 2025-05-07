package EnemyManager;

import BlueGolem.BlueGolem;
import Game.Game;
import GameState.Playing;
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
    private ArrayList<BlueGolem> blueGolems;
    private float spawnTimer = 0.0f; private ArrayList<Point> spawnPoint;
    private final ArrayList<BlueGolem> toRemove = new ArrayList<>();
    private Random rand = new Random();
    private Boolean spawned = false;
    private Playing playing;
    public EnemyManager(Playing playing) {
        this.playing = playing;
        blueGolems = new ArrayList<>();
        spawnPoint = new ArrayList<>();
        rand = new Random();
        spawnPoint = playing.getLevelManager().getCurrentLevel().getEnemySpawnPoints();
        addBlueGolem();
    }
    public void update(float delta) {
        updateBlueGolem(delta);
        for (BlueGolem blueGolem : new ArrayList<>(blueGolems) ) {
            blueGolem.update(delta);
        }
        removeBlueGolem();
        playing.getObjecManager().update(delta);
    }
    public void render(Graphics g , int offsetX , int offsetY) {
        ArrayList<BlueGolem> copy = new ArrayList<>(blueGolems);
        for (BlueGolem blueGolem : copy) {
            blueGolem.render(g, offsetX, offsetY);
        }
        playing.getObjecManager().render(g, offsetX, offsetY);
    }
    public void addBlueGolem() {
        if (spawned) return;
        for (Point point : spawnPoint) {
            int x = (int) (point.x * Game.GAME_SCALE * 2);
            int y = (int) (point.y * Game.GAME_SCALE * 2);
            BlueGolem blueGolem = new BlueGolem(x, y + 25 , playing.getLevelManager(), playing.getPlayer(),playing.getPool());
            blueGolems.add(blueGolem);
        }
        spawned = true;
    }

    private void updateBlueGolem(float delta){
        spawnTimer += delta * 2;
        if (blueGolems.size() < spawnPoint.size()){
            if (spawnTimer > 20.0f) {
                int index = rand.nextInt(spawnPoint.size());
                Point point = spawnPoint.get(index);
                int x = (int) (point.x * Game.GAME_SCALE * 2);
                int y = (int) (point.y * Game.GAME_SCALE * 2);
                BlueGolem blueGolem = new BlueGolem(x, y + 25 , playing.getLevelManager(), playing.getPlayer(),playing.getPool());
                blueGolems.add(blueGolem);
                spawnTimer = 0.0f;
            }
        }
    }
    private void removeBlueGolem() {
        toRemove.clear();
        for (int i = blueGolems.size() - 1; i >= 0; i--) {
            BlueGolem g = blueGolems.get(i);
            if (g.getFinisedDeath()) {
                playing.getPlayer().setEnemy_kill_num(playing.getPlayer().getEnemy_kill_num() + 1);
                int random = rand.nextInt(4);
                if (random == 0) {
                    playing.getObjecManager().addPotion("BLUE",g.getBounds().x + g.getBounds().width / 2,
                            g.getBounds().y + g.getBounds().height / 2);
                } else if (random == 1) {
                    playing.getObjecManager().addPotion("RED",g.getBounds().x + g.getBounds().width / 2,
                             g.getBounds().y + g.getBounds().height / 2);
                } else if (random == 2) {
                    playing.getObjecManager().addHamburger("HAMBURGER",g.getBounds().x + g.getBounds().width / 2,
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
        playing.getObjecManager().removeAll();
        spawned = false;
    }
    public void setSpawnPoint(ArrayList<Point> spawnPoint) {
        this.spawnPoint = spawnPoint;
    }
}
