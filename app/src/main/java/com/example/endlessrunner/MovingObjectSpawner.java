package com.example.endlessrunner;

import android.graphics.Rect;

import java.util.ArrayList;
import java.util.Random;

public class MovingObjectSpawner {

    protected long _initTime;
    protected int _spawnInterval;

    public MovingObjectSpawner(){
        _initTime=System.currentTimeMillis();
        _spawnInterval=10000;
    }

    public void addMovingObject(ArrayList<MovingObject> movingObjects){
        if(System.currentTimeMillis()-_initTime>_spawnInterval){
            movingObjects.add(spawnMovingObject());
            _initTime=System.currentTimeMillis();
        }
    }

    private MovingObject spawnMovingObject() {
        Random random=new Random();

        int index=random.nextInt(3);

        MovingObject ob=null;
        switch (index){
            case 0:{
                ob=spawnPlatform();
                break;
            }
            case 1:{
                ob=spawnHeart();
                break;
            }
            case 2:{
                ob=spawnCoin();
                break;
            }
        }
        return ob;
    }

    private Platform spawnPlatform() {
        Random random = new Random();
        int top = Settings.SCREEN_HEIGHT - 356;
        int bottom = top + 200;
        int left = Settings.SCREEN_WIDTH;
        int right = left + 1000;
        Rect boundingRect = new Rect(left, top, right, bottom);

        return new Platform(boundingRect, -5, 0);
    }

    private Heart spawnHeart() {
        int top = Settings.SCREEN_HEIGHT - 200;
        int bottom = top + 100;
        int left = Settings.SCREEN_WIDTH;
        int right = left + 100;
        Rect boundingRect = new Rect(left, top, right, bottom);

        return new Heart(boundingRect, -5, 0);
    }

    private Coin spawnCoin() {
        int top = Settings.SCREEN_HEIGHT - 200;
        int bottom = top + 100;
        int left = Settings.SCREEN_WIDTH;
        int right = left + 100;
        Rect boundingRect = new Rect(left, top, right, bottom);

        return new Coin(boundingRect, -5, 0);
    }

}
