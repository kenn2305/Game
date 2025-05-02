package Player;

import Levels.LevelManager;
import StateMachine.StateMachine;
import utilz.Constants;

import java.awt.*;

public class Attack_1 extends PlayerState{
    public Attack_1(Player player, PlayerStateMachine playerStateMachine, LevelManager levelManager) {
        super(player, playerStateMachine, levelManager);
    }
    @Override
    protected void update(float delta) {
        player.damage = 10;
        player.setLockDirection(true);
        player.setFinishedAttack(false);
        updateAniFrames(stateTime);
        updateState(delta);
    }

    @Override
    protected void render(Graphics g, int offsetX, int offsetY) {
        drawAnimations(g,offsetX,offsetY,state);
    }

    @Override
    protected void onEnter() {
        playAnimation(Constants.PlayerConstants.ATTACK_1, Constants.PlayerAniConstants.ATTACK_1);
        player.setHitbox_active(false);
        player.setFinishedAttack(false);
        player.setVelX(0);
        aniSpeed = 5;
    }

    private void updateState(float delta) {
        if (aniFrame >= 3){
            player.setHitbox_active(true);
        } else {
            player.setHitbox_active(false);
        }

        if (finished){
            player.setFinishedAttack(true);
            if (player.getAttack_stage() == 2){
                playerStateMachine.Change_to_next_state(playerStateMachine.Attack_2);
            } else {
                if (player.getLeft() || player.getRight()){
                    playerStateMachine.Change_to_next_state(playerStateMachine.Running);
                } else {
                    playerStateMachine.Change_to_next_state(playerStateMachine.Idle);
                }
            }
        }
    }

}
