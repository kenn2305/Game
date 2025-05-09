package BlueGolem;

import Levels.LevelManager;
import utilz.Constants;

import java.awt.*;

public class Wander extends BlueGolemState{
    public Wander(BlueGolem blueGolem, BlueGolemStateMachine blueGolemStateMachine, LevelManager levelManager) {
        super(blueGolem, blueGolemStateMachine, levelManager);
    }

    @Override
    protected void render(Graphics g, int offsetX, int offsetY) {
        drawAnimations(g,offsetX,offsetY,state);
    }

    @Override
    protected void update(float delta) {
        updateAniFrames(stateTime);
        updateMovementInState(delta);
        blueGolem.stateTimer(delta);
        updateState(delta);
    }

    @Override
    protected void onEnter() {
        System.out.println("wander");
        blueGolem.setLockDirection(false);
        if (blueGolem.getIntersectsInChase()){
            blueGolem.setChase(false);
            blueGolem.setIntersectsInChase(false);
        }
        blueGolem.stateTimeOut = blueGolem.getStateTime();
        playAnimation(Constants.BlueGolemConstants.WALK, Constants.BlueGolemAniConstants.WALK);
        aniSpeed = 10;
    }

    private void updateState(float delta) {

        if (blueGolem.getDeath()){
            blueGolemStateMachine.Change_to_next_state(blueGolemStateMachine.Death);
            return;
        }

        if (blueGolem.checkCollisionLeftWall() && !blueGolem.checkCollisionRightWall()) {
            blueGolem.setDirection(1);
            blueGolem.stateTimeOut = blueGolem.getStateTime();
        } else if (blueGolem.checkCollisionRightWall() && !blueGolem.checkCollisionLeftWall()) {
            blueGolem.setDirection(-1);
            blueGolem.stateTimeOut = blueGolem.getStateTime();
        }

        if (blueGolem.checkCollisionFloor() && !blueGolem.getRayCastFloorActive()){
            blueGolem.setDirection(blueGolem.getDirection() * -1);
            blueGolem.stateTimeOut = blueGolem.getStateTime();
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
