package com.example.endlessrunner;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.Random;

public class EnemySpawner {
    protected long _initTime;
    protected int _spawnInterval;

    public EnemySpawner(){
        _initTime=System.currentTimeMillis();
        _spawnInterval=2000;
    }

    public void addEnemy(ArrayList<Enemy> enemies){
        if(System.currentTimeMillis()-_initTime>_spawnInterval){
            enemies.add(spawnEnemy());
            _initTime=System.currentTimeMillis();
        }
    }

    protected Enemy spawnEnemy(){
        Random random = new Random();
        int yOffset = random.nextInt(200);
        //int top = Settings.SCREEN_HEIGHT - yOffset-200;
        int top = Settings.SCREEN_HEIGHT - 356;
        int bottom = top + 256;
        int left = Settings.SCREEN_WIDTH;
        int right = left + 256;
        Rect boundingRect = new Rect(left, top, right, bottom);
        Paint paint = new Paint();
        paint.setColor(Color.RED);

        return new Enemy(boundingRect, -10, 0, paint);
    }



}
