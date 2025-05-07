package Game;
import Audio.AudioPlayer;
import BlueGolem.BlueGolem;
import Camera.Camera2D;
import EnemyManager.EnemyManager;
import GameState.GameState;
import GameState.Playing;
import GameState.Menu;
import Player.Player;
import Levels.LevelManager;
import Player.PlayerStateMachine;

import java.awt.*;


public class GameController {
    private GamePanel gamePanel;
    private GameWindow gameWindow;
    private Menu menu;
    private Playing playing;
    private AudioPlayer audioPlayer;
    public GameController() {
        playing = new Playing();
        menu = new Menu();
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        audioPlayer = new AudioPlayer();
        gamePanel.requestFocus();
    }

    public void draw(Graphics g) {
        switch (GameState.state){
            case MENU:
                menu.render(g);
                break;
            case PLAYING:
                playing.render(g);
                break;
        }
    }

    public void render(){
        gamePanel.repaint();
    }
    public void update(float delta){
        switch (GameState.state){
            case MENU:
                menu.update(delta);
                break;
            case PLAYING:
                playing.update(delta);
                break;
        }
    }

    public Menu getMenu() {
        return menu;
    }

    public Playing getPlaying() {
        return playing;
    }
    public AudioPlayer getAudioPlayer() {
        return audioPlayer;
    }
}
