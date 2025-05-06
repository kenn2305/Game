package GameState;

import Camera.Camera2D;
import EnemyManager.EnemyManager;
import Levels.LevelManager;
import Player.Player;
import ui.LevelCompleteOverlay;
import ui.PauseOverlay;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Playing implements StateMethods{
    private LevelManager levelManager;
    private Player player;
    private Camera2D camera;
    private EnemyManager enemyManager;
    private Boolean paused = false;
    private PauseOverlay pauseOverlay;
    private LevelCompleteOverlay levelCompleteOverlay;
    private Boolean levelComplete = false;
    public Boolean gameComplete = false;
    public Playing() {
        levelManager = new LevelManager(this);
        player = new Player(200,0, levelManager);
        enemyManager = new EnemyManager(player,levelManager);
        camera = new Camera2D(player,levelManager,enemyManager);
        pauseOverlay = new PauseOverlay(this);
        levelCompleteOverlay = new LevelCompleteOverlay(this);
    }
    public void unpause() {
        paused = false;
    }
    public LevelManager getLevelManager() {
        return levelManager;
    }
    public EnemyManager getEnemyManager(){
        return enemyManager;
    }
    public void setLevelComplete(Boolean levelComplete){
        this.levelComplete = levelComplete;
    }
    public Player getPlayer() {
        return player;
    }
    @Override
    public void update(float delta) {
        if (paused) {
            pauseOverlay.update();
        } else if (levelComplete) {
            levelCompleteOverlay.update();
        } else {
            levelManager.update();
            enemyManager.update(delta);
            camera.update();
            player.update(delta);
        }

    }
    public void resetAll(){
        enemyManager.removeAll();
        player.resetPlayer(levelManager.getCurrentLevel().getPlayerSpawnPointX(),levelManager.getCurrentLevel().getPlayerSpawnPointY());
    }
    public void replay(){
        enemyManager.removeAll();
        player.resetPlayer(levelManager.getCurrentLevel().getPlayerSpawnPointX(),levelManager.getCurrentLevel().getPlayerSpawnPointY());
        enemyManager.setSpawnPoint(levelManager.getCurrentLevel().getEnemySpawnPoints());
        enemyManager.addBlueGolem();
    }
    @Override
    public void render(Graphics g) {
        camera.render(g);
        if (paused) {
            pauseOverlay.render(g);
        } else if (levelComplete) {
            levelCompleteOverlay.render(g);
        }
    }

    @Override
    public void MouseDragged(MouseEvent e) {
        if (paused) {
            pauseOverlay.mouseDragged(e);
        }
    }

    @Override
    public void MousePressed(MouseEvent e) {
        if (paused){
            pauseOverlay.mousePressed(e);
        } else if (levelComplete) {
            levelCompleteOverlay.MousePressed(e);
        }
    }

    @Override
    public void MouseReleased(MouseEvent e) {
        if (paused){
            pauseOverlay.mouseReleased(e);
        } else if (levelComplete) {
            levelCompleteOverlay.MouseReleased(e);
        }
    }

    @Override
    public void MouseMoved(MouseEvent e) {
        if (paused){
            pauseOverlay.mouseMoved(e);
        } else if (levelComplete) {
            levelCompleteOverlay.MouseMove(e);
        }
    }

    @Override
    public void KeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.setRight(true);
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.setLeft(true);
        }

        if (e.getKeyCode() == KeyEvent.VK_X) {
            if (!player.getPressedJump()) {
                player.setPressedJump(true);
                player.setConsumed(false);
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_Z) {
            if (!player.getPressedDash()) {
                player.setPressedDash(true);
                player.setConsumedDash(false);
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_C) {
            if (!player.getPressedAttack()) {
                player.setPressedAttack(true);
                player.setConsumedAttack(false);
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            paused = !paused;
        }
    }

    @Override
    public void KeyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.setRight(false);
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.setLeft(false);
        }

        if (e.getKeyCode() == KeyEvent.VK_X) {
            player.setPressedJump(false);
            player.setConsumed(false);
        }

        if (e.getKeyCode() == KeyEvent.VK_Z) {
            player.setPressedDash(false);
            player.setConsumedDash(false);
        }

        if (e.getKeyCode() == KeyEvent.VK_C) {
            player.setPressedAttack(false);
            player.setConsumedAttack(false);
        }
    }
}
