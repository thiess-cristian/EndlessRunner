package com.example.endlessrunner;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.Random;

public class Platform extends MovingObject {

    private Paint _paint;
    private Bitmap _sprite;

    public Platform(Rect boundingRect, float xVelocity, float yVelocity) {
        super(boundingRect, xVelocity, yVelocity);
        _paint = new Paint();
        _paint.setColor(Color.GRAY);

        Random random=new Random();
        int randomSpriteIndex=random.nextInt(3);

        _sprite=SpriteCollection.getPlatformSprites()[randomSpriteIndex];
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
        canvas.drawBitmap(_sprite, null, _boundingRect, new Paint());
    }

    @Override
    public void update() {
        super.move();
    }
}
