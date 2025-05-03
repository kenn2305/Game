package BlueGolem;

import Levels.LevelManager;
import StateMachine.StateMachine;
import utilz.Constants;

import java.awt.*;

public class Death extends BlueGolemState{
    public Death(BlueGolem blueGolem, BlueGolemStateMachine blueGolemStateMachine, LevelManager levelManager) {
        super(blueGolem, blueGolemStateMachine, levelManager);
    }

    @Override
    protected void onEnter() {
        System.out.println("Death");
        playAnimation(Constants.BlueGolemConstants.DIE, Constants.BlueGolemAniConstants.DIE);
        aniSpeed = 8;
    }

    @Override
    protected void render(Graphics g, int offsetX, int offsetY) {
        drawAnimations(g,offsetX,offsetY,state);
    }

    @Override
    protected void update(float delta) {
        blueGolem.lockDirection = true;
        updateAniFrames(stateTime);
        blueGolem.Chase = false;
        updateState(delta);
    }
    private void updateState(float delta) {
        blueGolem.setVelX(0);
        if (finished){
            blueGolem.finisedDeath = true;
        }
    }
}
