package ui;

import utilz.LoadSave;

import java.awt.*;

public class TextDame {
    private Font font;
    private int x, y, size, dame;
    public float opacity = 225;
    public TextDame(int x , int y , int size, int dame) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.dame = dame;
        font = LoadSave.LoadFont(LoadSave.FONT);
    }
    public void update(float delta) {
        y -= 300 * delta;
        opacity -= 600 * delta;
        if (opacity <= 0) {
            opacity = 0;
        }
    }
    public void render(Graphics g, int offsetX, int offsetY) {
        g.setFont(font.deriveFont((float) size));
        int alpha = (int) Math.min(225, Math.max(0, (int)opacity));
        g.setColor(new Color(225,0,0,alpha));
        g.drawString(String.valueOf(-dame),x - offsetX,y - offsetY);
    }
}
