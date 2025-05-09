package BlueGolem;

import Levels.LevelManager;
import utilz.Constants;

import java.awt.*;

public class Attack extends BlueGolemState{
    public Attack(BlueGolem blueGolem, BlueGolemStateMachine blueGolemStateMachine, LevelManager levelManager) {
        super(blueGolem, blueGolemStateMachine, levelManager);
    }
    @Override
    protected void render(Graphics g, int offsetX, int offsetY) {
        drawAnimations(g,offsetX,offsetY,state);
    }

    @Override
    protected void update(float delta) {
        blueGolem.setLockDirection(true);
        updateAniFrames(stateTime);
        updateState(delta);
    }

    @Override
    protected void onEnter() {
        System.out.println("Attack");
        playAnimation(Constants.BlueGolemConstants.ATTACK, Constants.BlueGolemAniConstants.ATTACK);
        aniSpeed = 8;
    }


    private void updateState(float delta) {
        blueGolem.setVelX(0);
        if (aniFrame == 6 || aniFrame == 7) {
            blueGolem.setHitbox_active(true);
        } else {
            blueGolem.setHitbox_active(false);
        }
        if (blueGolem.getDeath()) {
            blueGolemStateMachine.Change_to_next_state(blueGolemStateMachine.Death);
            return;
        }

        if (!blueGolem.getAttack()){
            if (finished) {
                blueGolem.stateOut = false;
                blueGolem.stateTimer = 0;
                if (blueGolem.states == BlueGolem.State.IDLE) {
                    blueGolemStateMachine.Change_to_next_state(blueGolemStateMachine.Idle);
                } else if (blueGolem.states == BlueGolem.State.WANDER) {
                    blueGolemStateMachine.Change_to_next_state(blueGolemStateMachine.Wander);
                } else if (blueGolem.getChase()) {
                    blueGolemStateMachine.Change_to_next_state(blueGolemStateMachine.Chase);
                }
            }
        }
    }
}
