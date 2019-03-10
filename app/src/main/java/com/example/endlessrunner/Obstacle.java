package com.example.endlessrunner;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Obstacle implements GameObject {

    private Rect _rect;
    private Rect _rect2;
    private Paint _paint;


    public Obstacle(int rectHeight, Paint paint, int startX, int startY, int playerGap) {
        _paint = paint;

        _rect = new Rect(0, startY, startX, startY + rectHeight);
        _rect2 = new Rect(startX + playerGap, startY, Settings.SCREEN_WIDTH, startY + rectHeight);
    }

    public void incrementY(float value){
        _rect.top+=value;
        _rect.bottom+=value;
        _rect2.top+=value;
        _rect2.bottom+=value;
    }

    public Rect getRect() {
        return _rect;
    }

    public boolean playerCollide(RectPlayer player) {
        return _rect.intersect(player.getRect()) || _rect2.intersect(player.getRect());
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect(_rect, _paint);
        canvas.drawRect(_rect2, _paint);
    }

    @Override
    public void update() {

    }
}
