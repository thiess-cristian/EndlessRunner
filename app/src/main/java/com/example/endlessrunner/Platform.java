package com.example.endlessrunner;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Platform extends MovingObject {

    Paint _paint;

    public Platform(Rect boundingRect, float xVelocity, float yVelocity) {
        super(boundingRect, xVelocity, yVelocity);
        _paint = new Paint();
        _paint.setColor(Color.GRAY);
    }

    public void setColor(int color) {
        _paint.setColor(color);
    }

    boolean isPlayerOnTop(Player player) {
        if (player.getBoundingRect().right >= _boundingRect.left && player.getBoundingRect().left <= _boundingRect.right) {
            if (player.getBoundingRect().bottom + 5 <= _boundingRect.top || player.getBoundingRect().bottom - 5 <= _boundingRect.top) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect(_boundingRect, _paint);
    }

    @Override
    public void update() {
        super.move();
    }
}
