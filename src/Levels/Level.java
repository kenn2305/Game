package Levels;

import BlueGolem.BlueGolem;
import Game.Game;
import Player.Player;
import utilz.LoadSave;

import java.awt.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Level {
    private LevelManager levelManager;
    private Map<String,int[][]> map_data = new LinkedHashMap<String,int[][]>();
    private List<Rectangle> bounds;
    public Level(String map_name) {
        this.map_data = LoadSave.getLevelData(map_name);
        this.bounds = LoadSave.loadTileBound(map_name);
        setBoundsPosition();
    }

    private void setBoundsPosition(){
        for (Rectangle bound : bounds){
            bound.setLocation((int)(bound.x * Game.GAME_SCALE * 2),(int)(bound.y * Game.GAME_SCALE * 2));
            bound.setSize((int)(bound.width * Game.GAME_SCALE * 2),(int)(bound.height * Game.GAME_SCALE * 2));
        }
    }
    public Map<String,int[][]> getMap_data() {
        return map_data;
    }
    public List<Rectangle> getTileBounds() {
        return bounds;
    }
}
