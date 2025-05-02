package StateMachine;

import java.awt.*;

public abstract class State {
    protected int aniFrame = 0;
    protected int aniSpeed;
    protected int aniTick = 0;
    protected int state = 0;
    protected int stateTime = 0;
    protected Boolean finished = false;

    protected void drawAnimations(Graphics g, float offsetX, float offsetY, int state) {};
    protected void updateAniFrames(int stateFrames){};
    protected void playAnimation(int state , int stateTime){};

    protected abstract void update(float delta);
    protected abstract void render(Graphics g, int offsetX, int offsetY);
    protected abstract void onEnter();
    protected abstract void onExit();
}
