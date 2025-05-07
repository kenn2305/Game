package ui;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TextDamePool {
    private List<TextDame> textDames;
    private int poolSize;
    private final float maxExtralLifeTime = 7.0f;
    private List<Float> extralLifeTimes;
    public TextDamePool(int size) {
        textDames = new ArrayList<TextDame>();
        extralLifeTimes = new ArrayList<>();
        poolSize = size;
        InitPool();
    }
    private void InitPool(){
        for ( int i = 0; i < poolSize; i++ ){
            textDames.add(new TextDame());
        }
    }

    public void spawnTextDame(int x , int y , String text, Color color){
        for (TextDame textDame : textDames){
            if (!textDame.active){
                textDame.activate(x,y,text,color);
                return;
            }
        }
        TextDame textDame = new TextDame();
        textDame.activate(x,y,text,color);
        textDames.add(textDame);
        extralLifeTimes.add(0.0f);
    }
    public void update(float delta){
        int index = poolSize;

        for (TextDame textDame : textDames){
            if (textDame.active){
                textDame.update(delta);
            }
        }

        Iterator<Float> timerIter = extralLifeTimes.iterator();
        while (timerIter.hasNext()){
            int idx = extralLifeTimes.indexOf(timerIter.next()) + poolSize;
            if (idx >= poolSize) return;

            if (!textDames.get(idx).active){
                float newTimer = extralLifeTimes.get(idx - poolSize) + delta * 2;
                if (newTimer > maxExtralLifeTime){
                    textDames.remove(idx);
                    timerIter.remove();
                    continue;
                } else {
                    extralLifeTimes.set(idx - poolSize, newTimer);
                }
            }
        }
    }
    public void render(Graphics g, int offsetX, int offsetY){
        for (TextDame textDame : textDames){
            if (textDame.active){
                textDame.render(g, offsetX, offsetY);
            }
        }
    }
}
