package Items;

import Player.Player;

import java.awt.*;

public class Hamburger extends Items{
    private float heal = 20;
    public Hamburger(float x, float y, String type, Player player) {
        super(x, y, type, player);
    }
    protected void playerInteract(){
        if (player.getBound().intersects(bounds) && active){
            player.setHealApplied(true);
            player.applyEffectFromHamburger(heal);
            active = false;
        }
    }
    public void update(float delta){
        updateAnimation(aniTime);
        bounce(delta);
        hoover(delta);
        playerInteract();
    }
    public void render(Graphics g , int offsetX , int offsetY){
        g.setColor(Color.BLUE);
        g.fillRect(bounds.x - offsetX, bounds.y-offsetY, bounds.width, bounds.height);
        drawAnimation(g , offsetX , offsetY);
    }
}
