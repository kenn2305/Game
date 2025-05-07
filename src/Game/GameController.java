package Game;
import Audio.AudioPlayer;
import BlueGolem.BlueGolem;
import Camera.Camera2D;
import EnemyManager.EnemyManager;
import GameState.GameState;
import GameState.Playing;
import GameState.Menu;
import GameState.MenuOption;
import Player.Player;
import Levels.LevelManager;
import Player.PlayerStateMachine;
import ui.AudioOptions;
import ui.TextDamePool;

import java.awt.*;


public class GameController {
    private GamePanel gamePanel;
    private GameWindow gameWindow;
    private Menu menu;
    private Playing playing;
    private MenuOption menuOption;
    private AudioPlayer audioPlayer;
    private AudioOptions audioOptions;
    public GameController() {
        audioOptions = new AudioOptions(this);
        playing = new Playing(this);
        menu = new Menu(this);
        menuOption = new MenuOption(this);
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        audioPlayer = new AudioPlayer();
        gamePanel.requestFocus();
    }

    public void draw(Graphics g) {
        switch (GameState.state){
            case MENU -> menu.render(g);
            case PLAYING -> playing.render(g);
            case OPTIONS -> menuOption.render(g);
            case QUIT -> System.exit(0);
        }
    }

    public void render(){
        gamePanel.repaint();
    }
    public void update(float delta){
        switch (GameState.state){
            case MENU -> menu.update(delta);
            case PLAYING -> playing.update(delta);
            case OPTIONS -> menuOption.update(delta);
            case QUIT -> System.exit(0);
        }
    }

    public Menu getMenu() {
        return menu;
    }
    public MenuOption getMenuOption() {return menuOption;}
    public Playing getPlaying() {
        return playing;
    }
    public AudioPlayer getAudioPlayer() {
        return audioPlayer;
    }
    public AudioOptions getAudioOptions() {
        return audioOptions;
    }
}
