package BlueGolem;

import Levels.LevelManager;
import utilz.Constants;

import java.awt.*;

public class Idle extends BlueGolemState{
    public Idle(BlueGolem blueGolem, BlueGolemStateMachine blueGolemStateMachine, LevelManager levelManager) {
        super(blueGolem, blueGolemStateMachine, levelManager);
    }

    @Override
    protected void render(Graphics g, int offsetX, int offsetY) {
        drawAnimations(g,offsetX,offsetY,state);
    }

    @Override
    protected void update(float delta) {
        updateAniFrames(stateTime);
        blueGolem.stateTimer(delta);
        updateState(delta);
    }

    @Override
    protected void onEnter() {
        System.out.println("IDLE");
        blueGolem.setLockDirection(true);
        blueGolem.stateTimeOut = blueGolem.getStateTime();
        blueGolem.setChase(true);
        playAnimation(Constants.BlueGolemConstants.IDLE, Constants.BlueGolemAniConstants.IDLE);
        aniSpeed = 8;
    }


    private void updateState(float delta) {
        blueGolem.setVelX(0);

        if (blueGolem.getDeath()){
            blueGolemStateMachine.Change_to_next_state(blueGolemStateMachine.Death);
            return;
        }

        if (blueGolem.stateOut == true && finished) {
            blueGolem.stateOut = false;
            blueGolem.stateTimer = 0;
            if (blueGolem.states == BlueGolem.State.IDLE){
                blueGolemStateMachine.Change_to_next_state(blueGolemStateMachine.Idle);
            } else if (blueGolem.states == BlueGolem.State.WANDER) {
                blueGolemStateMachine.Change_to_next_state(blueGolemStateMachine.Wander);
            }
        }

        if (blueGolem.getAttack()){
            blueGolemStateMachine.Change_to_next_state(blueGolemStateMachine.Attack);
        } else if (blueGolem.getChase()){
            blueGolemStateMachine.Change_to_next_state(blueGolemStateMachine.Chase);
        } else if (blueGolem.getHurt()) {
            blueGolemStateMachine.Change_to_next_state(blueGolemStateMachine.Hurt);
        }
    }
}
