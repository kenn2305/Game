package Levels;

import Player.Player;
import utilz.LoadSave;
import Game.Game;

import java.awt.image.BufferedImage;

public class LevelManager {
    private BufferedImage[] level_sprites;
    private Level test_level;
    private Player player;
    public LevelManager() {
        loadLevelSprite();
        test_level = new Level(LoadSave.TEST_LEVEL_DATA);
    }

    private void loadLevelSprite(){
        BufferedImage tile_map = LoadSave.loadImage(LoadSave.TILE_MAP_DATA);
        int rows = tile_map.getHeight() / Game.TILE_DEFAULT;
        int cols = tile_map.getWidth() / Game.TILE_DEFAULT;
        level_sprites = new BufferedImage[rows*cols];
        for ( int i = 0 ; i < rows ; i++){
            for ( int j = 0 ; j < cols ; j++){
                int index = i * cols + j;
                level_sprites[index] = tile_map.getSubimage(j * Game.TILE_DEFAULT, i * Game.TILE_DEFAULT, Game.TILE_DEFAULT, Game.TILE_DEFAULT);
            }
        }
    }
    public Level getCurrentLevel() {
        return test_level;
    }

    public BufferedImage getTileGrid(int index){
        return level_sprites[index];
    }
    public void setPlayer(Player player){
        this.player = player;
    }
}
