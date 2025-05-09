package GoldGolem;

import BlueGolem.BlueGolem;
import Levels.LevelManager;
import Player.Player;
import ui.TextDamePool;
import utilz.LoadSave;
import utilz.Maths;

import java.awt.*;

public class GoldGolem extends BlueGolem {
    public GoldGolem(float x, float y, LevelManager levelManager, Player player, TextDamePool pool) {
        super(x, y, levelManager, player, pool);
        maxHealth = 350; currentHealth = maxHealth;
        damage = 60;
    }
    @Override
    protected void loadSpriteSheet() {
        sprite_sheet = LoadSave.loadImage(LoadSave.GOLDGOLEM);
    }
}
