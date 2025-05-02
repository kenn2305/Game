package Inputs;
import Game.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInput implements KeyListener {
    private GamePanel gamePanel;
    public KeyboardInput(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            gamePanel.getGameController().getPlayer().setRight(true);
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            gamePanel.getGameController().getPlayer().setLeft(true);
        }

        if (e.getKeyCode() == KeyEvent.VK_X) {
            if (!gamePanel.getGameController().getPlayer().getPressedJump()) {
                gamePanel.getGameController().getPlayer().setPressedJump(true);
                gamePanel.getGameController().getPlayer().setConsumed(false);
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_Z) {
            if (!gamePanel.getGameController().getPlayer().getPressedDash()) {
                gamePanel.getGameController().getPlayer().setPressedDash(true);
                gamePanel.getGameController().getPlayer().setConsumedDash(false);
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_C) {
            if (!gamePanel.getGameController().getPlayer().getPressedAttack()) {
                gamePanel.getGameController().getPlayer().setPressedAttack(true);
                gamePanel.getGameController().getPlayer().setConsumedAttack(false);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            gamePanel.getGameController().getPlayer().setRight(false);
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            gamePanel.getGameController().getPlayer().setLeft(false);
        }

        if (e.getKeyCode() == KeyEvent.VK_X) {
            gamePanel.getGameController().getPlayer().setPressedJump(false);
            gamePanel.getGameController().getPlayer().setConsumed(false);
        }

        if (e.getKeyCode() == KeyEvent.VK_Z) {
            gamePanel.getGameController().getPlayer().setPressedDash(false);
            gamePanel.getGameController().getPlayer().setConsumedDash(false);
        }

        if (e.getKeyCode() == KeyEvent.VK_C) {
            gamePanel.getGameController().getPlayer().setPressedAttack(false);
            gamePanel.getGameController().getPlayer().setConsumedAttack(false);
        }
    }
}
