package GameState;

import ui.MenuButton;

import java.awt.event.MouseEvent;

public class State {
    public Boolean isIn(MouseEvent e , MenuButton mb){
        return mb.getBounds().contains(e.getX(), e.getY());
    }
}
