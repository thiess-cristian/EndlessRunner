package com.example.endlessrunner;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.Random;

public class GameModel implements GameObject{

    private Player _player;
    private ArrayList<Enemy> _enemies;
    private ArrayList<MovingObject> _movingObjects;
    private long _initTime;
    private long _platformInitTime;
    private CollisionManager _collisionManager;

    public GameModel(){
        int playerX=100;
        int playerY=Settings.SCREEN_HEIGHT-200;
        Paint paint=new Paint();
        paint.setColor(Color.GREEN);
        _player=new Player(new Rect(playerX,playerY,playerX+100,playerY+100),paint);
        _enemies=new ArrayList<>();
        _movingObjects=new ArrayList<>();
        _initTime=System.currentTimeMillis();
        _platformInitTime=_initTime;
        _collisionManager=new CollisionManager();
    }

    @Override
    public void draw(Canvas canvas) {
        for (Enemy enemy:_enemies) {
            enemy.draw(canvas);
        }
        for (MovingObject movingObject:_movingObjects){
            movingObject.draw(canvas);
        }
        _player.draw(canvas);
    }

    @Override
    public void update() {
        long currentTime=System.currentTimeMillis();

        if(currentTime-_initTime>1000){
           _initTime=currentTime;
           addEnemy();
           addBird();
        }

        if(currentTime-_platformInitTime>5000){
            _platformInitTime=_initTime;
            addPlatform();
        }


        if(_enemies.get(0).getBoundingRect().right<0){
            _enemies.remove(0);
        }

        for (Enemy enemy:_enemies) {
            enemy.update();
        }

        if(_movingObjects.size()>0) {
            if (_movingObjects.get(0).getBoundingRect().right < 0) {
                _movingObjects.remove(0);
            }
        }

        for (MovingObject movingObject:_movingObjects){
            movingObject.update();
        }

        _collisionManager.checkPlayerOnPlatform(_player,_movingObjects);
        _player.update();
    }

    public Player getPlayer(){
        return _player;
    }

    private void addEnemy(){
        Random random=new Random();
        int top=random.nextInt(Settings.SCREEN_HEIGHT);
        int bottom=top+100;
        int left=Settings.SCREEN_WIDTH;
        int right=left+100;
        Rect boundingRect=new Rect(left,top,right,bottom);
        Paint paint=new Paint();
        paint.setColor(Color.RED);
        _enemies.add(new Enemy(boundingRect,-10,0,paint));
    }

    private void addBird(){
        Random random=new Random();
        int top=random.nextInt(Settings.SCREEN_HEIGHT);
        int bottom=top+100;
        int left=Settings.SCREEN_WIDTH;
        int right=left+100;
        Rect boundingRect=new Rect(left,top,right,bottom);
        Paint paint=new Paint();
        paint.setColor(Color.YELLOW);
        _enemies.add(new Bird(boundingRect,-5,0,paint));
    }

    private void addPlatform(){
        Random random=new Random();
        int top=Settings.SCREEN_HEIGHT-random.nextInt(300);
        int bottom=top+100;
        int left=Settings.SCREEN_WIDTH;
        int right=left+500;
        Rect boundingRect=new Rect(left,top,right,bottom);
        _movingObjects.add(new Platform(boundingRect,-5,0));
    }

}
