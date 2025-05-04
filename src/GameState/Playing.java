package GameState;

import Camera.Camera2D;
import EnemyManager.EnemyManager;
import Levels.LevelManager;
import Player.Player;
import ui.PauseOverlay;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Playing implements StateMethods{
    private LevelManager levelManager;
    private Player player;
    private Camera2D camera;
    private EnemyManager enemyManager;
    private Boolean paused = false;
    private PauseOverlay pauseOverlay;
    public Playing() {
        levelManager = new LevelManager();
        player = new Player(0,0, levelManager);
        enemyManager = new EnemyManager(player,levelManager);
        camera = new Camera2D(player,levelManager,enemyManager);
        pauseOverlay = new PauseOverlay(this);
    }
    public void unpause() {
        paused = false;
    }
    @Override
    public void update(float delta) {
        if (paused) {
            pauseOverlay.update();
        } else {
            player.update(delta);
            enemyManager.update(delta);
            camera.update();
        }
    }

    @Override
    public void render(Graphics g) {
        camera.render(g);
        if (paused) {
            pauseOverlay.render(g);
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
        }
    }

    @Override
    public void MouseReleased(MouseEvent e) {
        if (paused){
            pauseOverlay.mouseReleased(e);
        }
    }

    @Override
    public void MouseMoved(MouseEvent e) {
        if (paused){
            pauseOverlay.mouseMoved(e);
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
