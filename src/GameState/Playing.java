package GameState;

import Camera.Camera2D;
import EnemyManager.EnemyManager;
import Game.*;
import Levels.LevelManager;
import ObjectManager.ObjecManager;
import Player.Player;
import ui.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Playing extends State implements StateMethods{
    public Boolean gameComplete = false;
    private LevelManager levelManager;
    private ObjecManager objecManager;
    private Player player;
    private Camera2D camera;
    private EnemyManager enemyManager;
    private Boolean paused = false;
    private PauseOverlay pauseOverlay;
    private LevelCompleteOverlay levelCompleteOverlay;
    private GameCompletedOverlay gameCompletedOverlay;
    private GameOverOverlay gameOverOverlay;
    private Boolean levelComplete = false;
    private Boolean gameOver = false;
    private TextDamePool pool;
    public Playing(GameController gameController) {
        super(gameController);
        this.pool = new TextDamePool(20);
        levelManager = new LevelManager(this);
        player = new Player(200,0, levelManager,this);
        objecManager = new ObjecManager(this);
        enemyManager = new EnemyManager(this);
        camera = new Camera2D(player,levelManager,enemyManager,pool);
        pauseOverlay = new PauseOverlay(this);
        levelCompleteOverlay = new LevelCompleteOverlay(this);
        gameCompletedOverlay = new GameCompletedOverlay(this);
        gameOverOverlay = new GameOverOverlay(this);
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
        gameOver = player.getGameOver();
        if(gameOver){
            gameOverOverlay.update();
        } else {
            if (paused) {
                pauseOverlay.update();
            } else if (gameComplete) {
                gameCompletedOverlay.update();
            } else if (levelComplete) {
                levelCompleteOverlay.update();
            } else {
                levelManager.update();
                enemyManager.update(delta);
                camera.update();
                player.update(delta);
                pool.update(delta);
            }
        }
    }
    public void resetAll(){
        enemyManager.removeAll();
        player.resetPlayer(levelManager.getCurrentLevel().getPlayerSpawnPointX(),levelManager.getCurrentLevel().getPlayerSpawnPointY());
        gameOver = false;
    }
    public void replay(){
        enemyManager.removeAll();
        player.resetPlayer(levelManager.getCurrentLevel().getPlayerSpawnPointX(),levelManager.getCurrentLevel().getPlayerSpawnPointY());
        enemyManager.setSpawnPoint(levelManager.getCurrentLevel().getEnemySpawnPoints());
        enemyManager.addBlueGolem();
    }
    public ObjecManager getObjecManager() {
        return objecManager;
    }
    public TextDamePool getPool() {
        return pool;
    }
    @Override
    public void render(Graphics g) {
        camera.render(g);
        if (gameOver){
            gameOverOverlay.render(g);
        } else {
            if (paused) {
                pauseOverlay.render(g);
            } else if (gameComplete) {
                gameCompletedOverlay.render(g);
            } else if (levelComplete) {
                levelCompleteOverlay.render(g);
            }
        }
    }

    @Override
    public void MouseDragged(MouseEvent e) {
        if (paused && !gameOver) {
            pauseOverlay.mouseDragged(e);
        }
    }

    @Override
    public void MousePressed(MouseEvent e) {
        if (gameOver){
            gameOverOverlay.MousePressed(e);
        } else {
            if (paused) {
                pauseOverlay.mousePressed(e);
            } else if (levelComplete) {
                levelCompleteOverlay.MousePressed(e);
            } else if (gameComplete) {
                gameCompletedOverlay.MousePressed(e);
            }
        }
    }

    @Override
    public void MouseReleased(MouseEvent e) {
        if (gameOver){
            gameOverOverlay.MouseReleased(e);
        } else {
            if (paused) {
                pauseOverlay.mouseReleased(e);
            } else if (levelComplete) {
                levelCompleteOverlay.MouseReleased(e);
            } else if (gameComplete) {
                gameCompletedOverlay.MouseReleased(e);
            }
        }
    }

    @Override
    public void MouseMoved(MouseEvent e) {
        if (gameOver){
            gameOverOverlay.MouseMove(e);
        } else {
            if (paused) {
                pauseOverlay.mouseMoved(e);
            } else if (levelComplete) {
                levelCompleteOverlay.MouseMove(e);
            } else if (gameComplete) {
                gameCompletedOverlay.MouseMove(e);
            }
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
