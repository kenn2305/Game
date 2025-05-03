package EnemyManager;

import BlueGolem.BlueGolem;
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
    private LevelManager level;
    private ObjecManager objManager;
    private ArrayList<BlueGolem> blueGolems;
    private float spawnTimer = 0.0f; private ArrayList<int[]> spawnPoint;
    private Random rand = new Random();
    public EnemyManager(Player player, LevelManager level) {
        this.player = player;
        this.level = level;
        blueGolems = new ArrayList<>();
        spawnPoint = new ArrayList<>();
        rand = new Random();
        objManager = new ObjecManager(player);
        spawnPoint.add(new int[]{469,569});
        spawnPoint.add(new int[]{77,804});
        spawnPoint.add(new int[]{2072,1044});
    }
    public void update(float delta) {
        addBlueGolem(delta);
        for (BlueGolem blueGolem : blueGolems) {
            blueGolem.update(delta);
        }
        objManager.update(delta);
        removeBlueGolem();
    }
    public void render(Graphics g , int offsetX , int offsetY) {
        for (BlueGolem blueGolem : blueGolems) {
            blueGolem.render(g, offsetX, offsetY);
        }
        objManager.render(g, offsetX, offsetY);
    }
    private void addBlueGolem(float delta) {
        spawnTimer += delta * 2;
        if (spawnTimer > 0.0f && blueGolems.size() < 1) {
            int index = rand.nextInt(spawnPoint.size());
            int[] spawn = spawnPoint.get(index);
            BlueGolem blueGolem = new BlueGolem(spawn[0], spawn[1], level, player);
            blueGolems.add(blueGolem);
            spawnTimer = 0.0f;
        }
    }

    private void removeBlueGolem() {
        for (int i = blueGolems.size() - 1; i >= 0; i--) {
            BlueGolem g = blueGolems.get(i);
            if (g.getFinisedDeath()) {
                int random = rand.nextInt(3);
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
                blueGolems.remove(i);
            }
        }
    }

}
