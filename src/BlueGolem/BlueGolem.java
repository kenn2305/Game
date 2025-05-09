    package BlueGolem;

    import Entities.Enemy;
    import Entities.Entity;
    import Game.Game;
    import Levels.LevelManager;
    import Player.Player;
    import Ray.Ray;
    import org.w3c.dom.Text;
    import ui.TextDame;
    import ui.TextDamePool;
    import utilz.LoadSave;
    import utilz.Maths;

    import java.awt.*;
    import java.awt.image.BufferedImage;
    import java.util.ArrayList;
    import java.util.Random;

    public class BlueGolem extends Enemy {
        protected BlueGolemStateMachine blueGolemStateMachine;
        enum State {
            IDLE,
            WANDER;
        }
        protected float stateTimeOut = 0.0f; protected float[] stateTime;
        protected float stateTimer; protected BlueGolem.State states;
        protected Boolean stateOut = false;
        public BlueGolem(float x, float y, LevelManager levelManager , Player player, TextDamePool pool) {
            super(x, y, levelManager);
            this.player = player;
            loadSpriteSheet();
            loadAnimations();
            InitBound();
            InitHitBox();
            InitRayCastFloor();
            maxHealth = 250; currentHealth = maxHealth;
            damage = 30;
            rand = new Random();
            stateTime = new float[] {3,4};
            states = State.WANDER;
            blueGolemStateMachine = new BlueGolemStateMachine(this, levelManager);
            ray = new Ray(levelManager);
            this.pool = pool;
        }
        protected void loadSpriteSheet() {
            sprite_sheet = LoadSave.loadImage(LoadSave.BLUEGOLEM);
        }
        protected void loadAnimations() {
            int rows = sprite_sheet.getHeight() / 64;
            int cols = sprite_sheet.getWidth() / 90;
            animations = new BufferedImage[rows][cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    animations[i][j] = sprite_sheet.getSubimage(j * 90, i * 64, 90, 64);
                }
            }
        }
        protected void updateRayCastFloor(){
            if (Direction == 1){
                rayCastFloor.setLocation((int)(x + collisionBox.width) , (int)(y + collisionBox.getHeight() / 2));
            } else {
                rayCastFloor.setLocation((int)(x - rayCastFloor.width) , (int)(y + collisionBox.getHeight() / 2));
            }
            rayColor = new Color(0, 0, 225, 130);
            rayCastFloorActive = false;
            for (Rectangle tile : levelManager.getCurrentLevel().getTileBounds()){
                if (rayCastFloor.intersects(tile)){
                    rayColor = new Color(225, 0, 0, 130);
                    rayCastFloorActive = true;
                    break;
                }
            }
        }
        protected void InitBound(){
            collisionBox = new Rectangle((int)(22 * Game.GAME_SCALE * 1.5) , (int) (32 * Game.GAME_SCALE * 1.5));
        }
        protected void InitHitBox(){
            Hitbox = new Rectangle((int)(35 * Game.GAME_SCALE * 1.5), (int) (17 * Game.GAME_SCALE * 1.5));
        }

        private void InitRayCastFloor(){
            rayCastFloor = new Rectangle((int)(3 * Game.GAME_SCALE * 1.5), (int) (35 * Game.GAME_SCALE * 1.5));
        }

        private void updateHitbox(){
            if (Direction == 1) {
                Hitbox.setLocation((int) (x - 7 * Game.GAME_SCALE * 1.5), (int) (y + 15.5 * Game.GAME_SCALE * 1.5));
            } else {
                Hitbox.setLocation((int) (x - 10 * Game.GAME_SCALE * 1.5), (int) (y + 15.5 * Game.GAME_SCALE * 1.5));
            }
            if (hitbox_active) {
                hitboxColor = new Color(225, 0, 0, 130);
            } else {
                hitboxColor = new Color(0, 0, 225, 130);
            }
        }
        protected State getRandomState(){
            State[] states = State.values();
            return states[rand.nextInt(states.length)];
        }

        protected float getStateTime(){
            return stateTime[rand.nextInt(stateTime.length)];
        }
        public void update(float delta) {
            super.update(delta);
            blueGolemStateMachine.update(delta);
            ray.update(delta,player.getBound().x , player.getBound().y, collisionBox.x , collisionBox.y);
            updateHitbox();
            updateRayCastFloor();
        }
        protected void stateTimer(float delta) {
            stateTimer += delta * 2;
            if (stateTimer >= stateTimeOut){
                states = getRandomState();
                stateTimer = 0;
                stateOut = true;
            }
        }
        public void render(Graphics g, int offsetX, int offsetY) {
            HealthBar(g , offsetX , offsetY);
            //Debug(g,offsetX,offsetY);
            blueGolemStateMachine.render(g, offsetX, offsetY);
        }
        public void Debug(Graphics g, int offsetX, int offsetY) {
            g.setColor(hitboxColor);
            g.fillRect(Hitbox.x - offsetX, Hitbox.y - offsetY, Hitbox.width, Hitbox.height);
            g.setColor(new Color(0, 0, 225, 130));
            g.fillRect(collisionBox.x - offsetX , collisionBox.y - offsetY , collisionBox.width, collisionBox.height);

            g.setColor(rayColor);
            g.fillRect(rayCastFloor.x - offsetX, rayCastFloor.y - offsetY, rayCastFloor.width, rayCastFloor.height);
            ray.render(g, offsetX, offsetY);
        }
        public BufferedImage getSprite(int x , int y) {
            return animations[x][y];
        }
        public Rectangle getBounds() {
            return collisionBox;
        }
        public void activateEnemy( int x, int y, float maxHealth){
            super.activateEnemy(x, y, maxHealth);
            blueGolemStateMachine.activateBlueGolem();
        }
    }
