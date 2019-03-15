package com.example.endlessrunner;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Enemy extends MovingObject {

    protected Paint _paint;

    public Enemy(Rect boundingRect, float xVelocity, float yVelocity, Paint paint) {
        super(boundingRect, xVelocity, yVelocity);

        _paint=paint;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect(_boundingRect,_paint);
    }

    @Override
    public void update() {
        super.move();
    }
}
