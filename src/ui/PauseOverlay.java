package ui;

import Game.Game;
import GameState.GameState;
import utilz.Constants;
import utilz.LoadSave;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import GameState.Playing;
public class PauseOverlay {
    private BufferedImage background;
    private int bgX, bgY, bgW, bgH;
    private SoundButton musicButton , sfxButton;
    private UrmButtom menuB, replayB, unpauseB;
    private VolumeButton volumeB;
    private Playing playing;
    public PauseOverlay(Playing playing) {
        loadBackground();
        creatSoundButton();
        creatUrmButton();
        creatVolumeButton();
        this.playing = playing;
    }

    private void creatVolumeButton() {
        int sliderX = (int)(bgX + 18 * Game.GAME_SCALE * 2.5);
        int sliderY = (int)(bgY + 71 * Game.GAME_SCALE * 2.5);
        volumeB = new VolumeButton(sliderX, sliderY, Constants.VolumeButton.SLIDER_WIDTH, Constants.VolumeButton.VOLUME_SIZE_HEIGHT);
    }

    private void creatUrmButton() {
        int menuX = (int)(bgX + 10 * Game.GAME_SCALE * 2.5);
        int replayX = (int)(bgX + 42 * Game.GAME_SCALE * 2.5);
        int unpauseX = (int)(bgX + 73 * Game.GAME_SCALE * 2.5);
        int bY = (int)(bgY + 92 * Game.GAME_SCALE * 2.5);

        menuB = new UrmButtom(menuX,bY, Constants.URMButton.URM_SIZE_WIDTH, Constants.URMButton.URM_SIZE_HEIGHT,2);
        replayB = new UrmButtom(replayX,bY, Constants.URMButton.URM_SIZE_WIDTH, Constants.URMButton.URM_SIZE_HEIGHT,1);
        unpauseB = new UrmButtom(unpauseX,bY, Constants.URMButton.URM_SIZE_WIDTH, Constants.URMButton.URM_SIZE_HEIGHT,0);
    }

    private void creatSoundButton() {
        int soundX = (int)(bgX + 71 * Game.GAME_SCALE * 2.5);
        int soundY = (int)(bgY + 8 * Game.GAME_SCALE * 2.5);
        int sfxY = (int)(bgY + 30 * Game.GAME_SCALE * 2.5);
        musicButton = new SoundButton(soundX,soundY, Constants.PauseButton.SOUND_SIZE_WIDTH, Constants.PauseButton.SOUND_SIZE_HEIGHT);
        sfxButton = new SoundButton(soundX, sfxY, Constants.PauseButton.SOUND_SIZE_WIDTH, Constants.PauseButton.SOUND_SIZE_HEIGHT);
    }
    private void loadBackground() {
        background = LoadSave.loadImage(LoadSave.PAUSE_BACKGROUND);
        bgW = (int) (background.getWidth() * Game.GAME_SCALE * 2.5);
        bgH = (int) (background.getHeight() * Game.GAME_SCALE * 2.5);
        bgX = Game.GAME_WIDTH / 2 - bgW / 2 ;
        bgY = 100;
    }
    public void update() {
        musicButton.update();
        sfxButton.update();
        menuB.update();
        replayB.update();
        unpauseB.update();
        volumeB.update();
    }
    public void render(Graphics g) {
        g.drawImage(background, bgX, bgY, bgW, bgH, null);
        musicButton.render(g);
        sfxButton.render(g);
        menuB.render(g);
        replayB.render(g);
        unpauseB.render(g);
        volumeB.render(g);
    }

    public void mouseMoved(MouseEvent e) {
        musicButton.setMouseOver(false);
        sfxButton.setMouseOver(false);
        menuB.setMouseOver(false);
        replayB.setMouseOver(false);
        unpauseB.setMouseOver(false);
        volumeB.setMouseOver(false);
        if (isIn(e,musicButton)) {
            musicButton.setMouseOver(true);
        } else if (isIn(e,sfxButton)) {
            sfxButton.setMouseOver(true);
        } else if (isIn(e,menuB)){
            menuB.setMouseOver(true);
        } else if (isIn(e,replayB)){
            replayB.setMouseOver(true);
        } else if (isIn(e,unpauseB)){
            unpauseB.setMouseOver(true);
        } else if (isIn(e,volumeB)){
            volumeB.setMouseOver(true);
        }
    }
    public void mousePressed(MouseEvent e) {
        if (isIn(e,musicButton)) {
            musicButton.setMousePressed(true);
        } else if (isIn(e,sfxButton)) {
            sfxButton.setMousePressed(true);
        } else if (isIn(e,menuB)) {
            menuB.setMousePressed(true);
        } else if (isIn(e,replayB)) {
            replayB.setMousePressed(true);
        } else if (isIn(e,unpauseB)) {
            unpauseB.setMousePressed(true);
        } else if (isIn(e,volumeB)) {
            volumeB.setMousePressed(true);
        }
    }
    public void mouseReleased(MouseEvent e) {
        if (isIn(e,musicButton)) {
            if (musicButton.getMousePressed()) {
                musicButton.setMuted(!musicButton.getMuted());
            }
        } else if (isIn(e,sfxButton)) {
            if (sfxButton.getMousePressed()) {
                sfxButton.setMuted(!sfxButton.getMuted());
            }
        } else if (isIn(e,menuB)) {
            if (menuB.getMousePressed()) {
                menuB.setMousePressed(false);
                GameState.state = GameState.MENU;
                playing.unpause();
            }
        } else if (isIn(e,replayB)) {
            if (replayB.getMousePressed()) {
                replayB.setMousePressed(false);
            }
        } else if (isIn(e,unpauseB)) {
            if (unpauseB.getMousePressed()) {
                unpauseB.setMousePressed(false);
                playing.unpause();
            }
        }
        musicButton.resetBools();
        sfxButton.resetBools();
        menuB.resetBool();
        replayB.resetBool();
        unpauseB.resetBool();
        volumeB.resetBools();
    }
    public void mouseDragged(MouseEvent e) {
        if (volumeB.getMousePressed()) {
            volumeB.changeX(e.getX());
        }
    }
    private Boolean isIn(MouseEvent e, PauseButton b) {
        return b.getBounds().contains(e.getX(), e.getY());
    }
}
