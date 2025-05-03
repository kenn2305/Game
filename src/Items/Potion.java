package Items;

import Player.Player;

import java.awt.*;

public class Potion extends Items{
    //Effect
    private float scale_damage = 0.2f; private float timeEffectDamage = 3.0f;
    private float scale_speed = 0.5f; private float timeEffectSpeed = 2.0f;
    public Potion(float x, float y, String type , Player player) {
        super(x, y, type, player);
    }
    protected void playerInteract(){
        if (player.getBound().intersects(bounds)){
            if (type == "BLUE"){
                player.applyEffectFromPotion("BLUE",scale_speed, timeEffectSpeed);
                player.setSpeedApplied(true);
            } else if (type == "RED"){
                player.applyEffectFromPotion("RED",scale_damage, timeEffectDamage);
                player.setDamageApplied(true);
            }
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