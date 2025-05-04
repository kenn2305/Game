package Inputs;

import Game.GamePanel;
import GameState.GameState;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInput implements MouseListener , MouseMotionListener {
    private GamePanel gamePanel;
    public MouseInput(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (GameState.state) {
            case MENU:
                gamePanel.getGameController().getMenu().MousePressed(e);
                break;
            case PLAYING:
                gamePanel.getGameController().getPlaying().MousePressed(e);
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (GameState.state) {
            case MENU:
                gamePanel.getGameController().getMenu().MouseReleased(e);
                break;
            case PLAYING:
                gamePanel.getGameController().getPlaying().MouseReleased(e);
                break;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        switch (GameState.state) {
            case PLAYING:
                gamePanel.getGameController().getPlaying().MouseDragged(e);
                break;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        switch (GameState.state) {
            case MENU:
                gamePanel.getGameController().getMenu().MouseMoved(e);
                break;
            case PLAYING:
                gamePanel.getGameController().getPlaying().MouseMoved(e);
                break;
        }
    }
}
