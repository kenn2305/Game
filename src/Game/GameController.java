package Game;
import BlueGolem.BlueGolem;
import Camera.Camera2D;
import EnemyManager.EnemyManager;
import Player.Player;
import Levels.LevelManager;
import Player.PlayerStateMachine;

import java.awt.*;


public class GameController {
    private LevelManager levelManager;
    private GamePanel gamePanel;
    private GameWindow gameWindow;
    private Player player;
    private Camera2D camera;
    private EnemyManager enemyManager;
    public GameController() {
        levelManager = new LevelManager();
        player = new Player(0,0, levelManager);
        enemyManager = new EnemyManager(player,levelManager);
        camera = new Camera2D(player,levelManager,enemyManager);
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
    }

    public void draw(Graphics g) {
        camera.render(g);
    }

    public void render(){
        gamePanel.repaint();
    }
    public void update(float delta){
        player.update(delta);
        enemyManager.update(delta);
        camera.update();
    }

    public Player getPlayer() {
        return player;
    }
}
