package Entities;

import BlueGolem.BlueGolem;
import Levels.LevelManager;
import Player.Player;
import Ray.Ray;
import ui.TextDame;
import ui.TextDamePool;
import utilz.Maths;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Enemy extends Entity{
    protected float maxHealth; protected float currentHealth;
    protected float damage;
    public Boolean active = true;  protected Random rand;
    protected BufferedImage sprite_sheet;
    protected BufferedImage[][] animations;
    protected Player player;
    protected Ray ray;
    protected int Direction = 1; protected Boolean lockDirection;
    protected Rectangle Hitbox; public Color hitboxColor; protected Boolean hitbox_active = false;
    protected Boolean hitplayer = false;
    protected float attackCooldown = 1.0f;
    protected float attackTimer = 0.0f;
    protected TextDame tmp;
    protected Boolean isAttack = false; protected Boolean Chase = false; protected Boolean intersectsInChase = false ;protected Boolean hurt = false; protected float hurtCooldown = 0.2f; protected float hurtTimer = 0;
    protected Rectangle rayCastFloor; protected Boolean rayCastFloorActive = false ;public Color rayColor;
    protected Boolean death = false; protected Boolean finisedDeath = false;

    //Healthbar
    protected int maxHealthBar = 72;
    protected int heathWidth = maxHealthBar;
    protected float displayTime = 6.0f;
    protected float displayTimer = 0.0f;
    protected Boolean display = false;
    protected TextDamePool pool;
    public Enemy(float x, float y, LevelManager levelManager) {
        super(x, y, levelManager);
    }
    public void activateEnemy( int x, int y, float maxHealth){
        this.currentHealth = maxHealth;
        this.active = true;
        collisionBox.x = x;
        collisionBox.y = y;
    }
    protected void stateUpdate(float delta){
        int distance = Maths.Distance(player.getBound().x, player.getBound().y, collisionBox.x, collisionBox.y);
        if (distance <= 400 && !ray.rayInterrupt() && canSee()){
            Chase = true;
        }

        if (distance > 400 || ray.rayInterrupt()){
            Chase = false;
        }

        if (Chase && !lockDirection){
            if (player.getBound().x < collisionBox.x){
                Direction = -1;
            } else {
                Direction = 1;
            }
        }
        if (player.getHitbox().intersects(collisionBox)){
            if (player.getHitbox_active() && hurtTimer <= 0 && active){
                player.setGetIntersect(true);
                hurt = true;
                hurtTimer = hurtCooldown;
                currentHealth -= player.getDame();
                display = true;
                displayTimer = 0.0f;
                if (player.getBound().x <= collisionBox.x){
                    Direction = -1;
                } else {
                    Direction = 1;
                }
                pool.spawnTextDame(collisionBox.x,collisionBox.y,"-"+(int)player.getDame(),Color.RED);
            } else {
                hurt = false;
                player.setGetIntersect(false);
            }
        }

        if (Hitbox.intersects(player.getBound()) && canSee()) {
            isAttack = true;
            if (hitbox_active && attackTimer <= 0) {
                player.getDamageFromEnemy(damage, true);
                hitplayer = true;
                attackTimer = attackCooldown; // Reset cooldown
            } else {
                hitplayer = false;
            }
        } else {
            isAttack = false;
            hitplayer = false;
        }

        if (attackTimer > 0) {
            attackTimer -= delta * 2;
        }


        if (hurtTimer > 0){
            hurtTimer -= delta * 2;
        }

        if (currentHealth <= 0){
            death = true;
        } else {
            death = false;
        }

        if (display){
            displayTimer += delta * 2;
        }
        if (displayTimer >= displayTime){
            display = false;
            displayTimer = 0.0f;
        }
    }
    protected void HealthBar(Graphics g , int offsetX , int offsetY){
        if (display && !death) {
            g.setColor(new Color(0, 0, 0));
            g.fillRect(collisionBox.x - 11 - offsetX,
                    collisionBox.y - 19 - offsetY,
                    74, 11);

            g.setColor(new Color(225, 0, 30));
            g.fillRect(collisionBox.x - 10 - offsetX,
                    collisionBox.y - 18 - offsetY,
                    heathWidth, 9);
        }
    }
    protected Boolean canSee(){

        if (Direction == 1){
            if (player.getBound().x >= collisionBox.x){
                return true;
            }
        } else if (Direction == -1){
            if (player.getBound().x <= collisionBox.x){
                return true;
            }
        }
        return false;
    }
    protected void updateHealthBar(){
        heathWidth = (int)((currentHealth / (float)maxHealth) * maxHealthBar);
    }
    public void update(float delta){
        super.update(delta);
        stateUpdate(delta);
        updateHealthBar();
    }
    public void render(Graphics g, int offsetX , int offsetY){

    }
    public int getDirection() {
        return Direction;
    }
    public void setDirection(int direction) {
        Direction = direction;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getLockDirection() {
        return lockDirection;
    }

    public void setLockDirection(Boolean lockDirection) {
        this.lockDirection = lockDirection;
    }

    public Rectangle getHitbox() {
        return Hitbox;
    }

    public void setHitbox(Rectangle hitbox) {
        Hitbox = hitbox;
    }

    public Boolean getHitbox_active() {
        return hitbox_active;
    }

    public void setHitbox_active(Boolean hitbox_active) {
        this.hitbox_active = hitbox_active;
    }

    public Boolean getChase() {
        return Chase;
    }

    public void setChase(Boolean chase) {
        Chase = chase;
    }

    public Boolean getAttack() {
        return isAttack;
    }

    public void setAttack(Boolean attack) {
        isAttack = attack;
    }

    public Rectangle getRayCastFloor() {
        return rayCastFloor;
    }

    public void setRayCastFloor(Rectangle rayCastFloor) {
        this.rayCastFloor = rayCastFloor;
    }

    public Boolean getDeath() {
        return death;
    }

    public void setDeath(Boolean death) {
        this.death = death;
    }

    public Boolean getFinisedDeath() {
        return finisedDeath;
    }

    public void setFinisedDeath(Boolean finisedDeath) {
        this.finisedDeath = finisedDeath;
    }
    public Boolean getHurt(){
        return hurt;
    }
    public void setHurt(Boolean hurt) {
        this.hurt = hurt;
    }
    public Boolean getIntersectsInChase() {
        return intersectsInChase;
    }
    public void setIntersectsInChase(Boolean intersectsInChase) {
        this.intersectsInChase = intersectsInChase;
    }
    public Boolean getRayCastFloorActive() {
        return rayCastFloorActive;
    }
}
