package com.example.endlessrunner;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Background extends MovingObject {

    Paint _paint;

    public Background(Rect boundingRect, float xVelocity, float yVelocity) {
        super(boundingRect, xVelocity, yVelocity);

        _paint=new Paint();
        _paint.setColor(Color.WHITE);
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
