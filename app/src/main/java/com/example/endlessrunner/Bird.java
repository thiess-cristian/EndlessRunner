package com.example.endlessrunner;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Bird extends Enemy {

    public Bird(Rect boundingRect, float xVelocity, float yVelocity, Paint paint) {
        super(boundingRect, xVelocity, yVelocity, paint);
    }


    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect(_boundingRect,_paint);
    }

    @Override
    public void update() {
        super.update();
        float sinValue=-(float)Math.cos(_boundingRect.left / 100) * 5;
        _boundingRect.top+=(int)sinValue;
        _boundingRect.bottom+=(int)sinValue;
    }

}