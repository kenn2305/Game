package Camera;

import BlueGolem.BlueGolem;
import EnemyManager.EnemyManager;
import Player.Player;
import Game.Game;
import Levels.LevelManager;
import utilz.Maths;

import java.awt.*;
import java.util.List;
import java.util.Map;

public class Camera2D {
    private int CameraX, CameraY;
    private int width = Game.GAME_WIDTH;
    private int height = Game.GAME_HEIGHT;
    private int xLimit,yLimit;
    private int maxX = Game.MAP_WIDTH - width;
    private int maxY = Game.MAP_HEIGHT - height;
    private Player player;
    private EnemyManager enemyManager;
    private LevelManager level;
    private int offsetX, offsetY;
    public Camera2D(Player player , LevelManager level , EnemyManager enemyManager) {
        System.out.println(maxX + " " + maxY);
        this.player = player;
        this.level = level;
        this.enemyManager = enemyManager;
        this.CameraX = 0;
        this.CameraY = 0;
        offsetX = 0;
        offsetY = 0;
        xLimit = Game.GAME_WIDTH / 2;
        yLimit = Game.GAME_HEIGHT / 2;
    }

    public void update() {
        CameraFollowPlayer();
    }

    public void render(Graphics g) {
        DrawMapInViewPort(g);
        DebugBox(g);
        DebugHitBox(g);
        drawPlayerInViewPort(g);
    }


    private void CameraFollowPlayer() {
        CameraX = player.getBound().x;
        CameraY = player.getBound().y;
        int tmp_x = 0;
        int tmp_y = 0;
        if (CameraX > xLimit) {
            tmp_x = CameraX - xLimit;
        }

        if (CameraY > yLimit) {
            tmp_y = CameraY - yLimit;
        }

        if (tmp_x > maxX) {
            tmp_x = maxX;
        }
        if (tmp_y > maxY) {
            tmp_y = maxY;
        }

        offsetX = (int)Maths.Lerp(offsetX, (float)tmp_x, 0.05f);
        offsetY = (int)Maths.Lerp(offsetY, (float)tmp_y, 0.05f);
    }

    private void DebugBox(Graphics g) {
        g.setColor(player.bound_color);
        g.fillRect((int)(player.getBound().x - offsetX),
                (int)(player.getBound().y - offsetY),
                player.getBound().width,
                player.getBound().height);
    }

     private void DebugHitBox(Graphics g) {
         Graphics2D g2 = (Graphics2D) g;
         g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                 RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
         g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                 RenderingHints.VALUE_ANTIALIAS_OFF);
        g2.setColor(player.hitbox_color);
        g2.fillRect(player.getHitbox().x - offsetX, player.getHitbox().y - offsetY, player.getHitbox().width, player.getHitbox().height);

     }
    private void DrawMapInViewPort(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
        Map<String, int[][]> map = level.getCurrentLevel().getMap_data();
        List<Rectangle> bounds = level.getCurrentLevel().getTileBounds();
        //Draw map
        for (String layer : map.keySet()) {
            int[][] layer_data = map.get(layer);
            for ( int i = 0 ; i < layer_data.length ; i++ ) {
                for ( int j = 0 ; j < layer_data[i].length ; j++) {
                    g2.drawImage(level.getTileGrid(layer_data[i][j]),
                            j * Game.MAP_TILE_SIZE - offsetX,
                            i * Game.MAP_TILE_SIZE - offsetY,
                            Game.MAP_TILE_SIZE,
                            Game.MAP_TILE_SIZE, null);
                }
            }
        }
        //Debug
        for (Rectangle bound : bounds) {
            g2.setColor(new Color(0,0,130,80));
            g2.fillRect(bound.x - offsetX, bound.y - offsetY, bound.width, bound.height);
        }
    }

    private void drawPlayerInViewPort(Graphics g) {
        player.render(g,offsetX,offsetY);
        enemyManager.render(g,offsetX,offsetY);
    }
    public float getOffsetX() {
        return offsetX;
    }
    public float getOffsetY() {
        return offsetY;
    }
}
