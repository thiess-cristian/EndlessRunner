package com.example.endlessrunner;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.io.Console;
import java.util.ArrayList;

public class ObstacleManager implements GameObject {

    private ArrayList<Obstacle> _obstacles;
    private int _playerGap;
    private int _obstacleGap;
    private int _obstacleHeight;
    private long _startTime;
    private long _initTime;

    private int _score=0;

    public ObstacleManager(int playerGap, int obstacleGap, int obstacleHeight) {
        _playerGap = playerGap;
        _obstacleGap = obstacleGap;
        _obstacleHeight = obstacleHeight;
        _obstacles = new ArrayList<>();

        _startTime = System.currentTimeMillis();
        _initTime = _startTime;

        populateObstacles();
    }

    public boolean playerCollide(RectPlayer player) {
        for (Obstacle obstacle : _obstacles) {
            if (obstacle.playerCollide(player)) {
                return true;
            }
        }
        return false;
    }

    private void populateObstacles() {

        int currentY = -5 * Settings.SCREEN_HEIGHT / 4;

        while (currentY < 0) {
            int xStart = (int) (Math.random() * (Settings.SCREEN_WIDTH - _playerGap));
            Paint paint = new Paint();
            paint.setColor(Color.GREEN);
            _obstacles.add(new Obstacle(_obstacleHeight, paint, xStart, currentY, _playerGap));
            currentY += _obstacleHeight + _obstacleGap;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        for (Obstacle obstacle : _obstacles) {
            obstacle.draw(canvas);
        }
        Paint paint =new Paint();
        paint.setColor(Color.BLUE);
        paint.setTextSize(50);
        canvas.drawText(""+_score,50,50,paint);
    }

    @Override
    public void update() {

        if(_startTime<Settings.INIT_TIME){
            _startTime=Settings.INIT_TIME;
        }

        int elapsedTime = (int) (System.currentTimeMillis() - _startTime);
        _startTime = System.currentTimeMillis();
        float speed = (float) (1 + Math.sqrt(_startTime - _initTime) / 1000) * Settings.SCREEN_HEIGHT * 2.0f / 10000.f;

        for (Obstacle obstacle : _obstacles) {
            obstacle.incrementY(elapsedTime * speed);


        }

        if (_obstacles.get(_obstacles.size() - 1).getRect().top >= Settings.SCREEN_HEIGHT) {
            Paint paint = new Paint();
            paint.setColor(Color.GREEN);
            int xStart = (int) (Math.random() * (Settings.SCREEN_WIDTH - _playerGap));
            _obstacles.add(0,
                    new Obstacle(
                            _obstacleHeight,
                            paint,
                            xStart,
                            _obstacles.get(0).getRect().top - _obstacleHeight - _obstacleGap,
                            _playerGap));

            _obstacles.remove(_obstacles.size() - 1);
            _score++;
        }
    }
}
