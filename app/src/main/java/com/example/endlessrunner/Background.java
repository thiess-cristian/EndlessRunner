package com.example.endlessrunner;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.Random;

public class Background extends MovingObject {

    Paint _paint;

    private Bitmap _sprite;

    public Background(Rect boundingRect, float xVelocity, float yVelocity) {
        super(boundingRect, xVelocity, yVelocity);

        _paint=new Paint();
        _paint.setColor(Color.WHITE);

        Random random=new Random();
        int randomSpriteIndex=random.nextInt(2);

        _sprite=SpriteCollection.getBackgroundSprites()[randomSpriteIndex];
    }

    @Override
    public void draw(Canvas canvas) {
        //canvas.drawRect(_boundingRect,_paint);
        canvas.drawBitmap(_sprite, null, _boundingRect, new Paint());
    }

    @Override
    public void update() {
        super.move();
    }

    public void reset() {
        int top = 0;
        int bottom = Settings.SCREEN_HEIGHT;
        int left = Settings.SCREEN_WIDTH;
        int right = left+Settings.SCREEN_WIDTH;
        _boundingRect.top=top;
        _boundingRect.bottom=bottom;
        _boundingRect.left=left;
        _boundingRect.right=right;

        Random random=new Random();
        int randomSpriteIndex=random.nextInt(2);

        _sprite=SpriteCollection.getBackgroundSprites()[randomSpriteIndex];
    }
}
