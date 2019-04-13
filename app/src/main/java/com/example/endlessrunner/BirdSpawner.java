package com.example.endlessrunner;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.Random;

public class BirdSpawner extends EnemySpawner {

    public BirdSpawner(){
        super();
        _spawnInterval=3000;
    }

    protected Enemy spawnEnemy(){
        Random random = new Random();
        int yOffset = random.nextInt(400) + 750;
        int top = Settings.SCREEN_HEIGHT - yOffset;
        int bottom = top + 256;
        int left = Settings.SCREEN_WIDTH;
        int right = left + 256;
        Rect boundingRect = new Rect(left, top, right, bottom);
        Paint paint = new Paint();
        paint.setColor(Color.YELLOW);

        return new Bird(boundingRect, -12, 0, paint);
    }

}
