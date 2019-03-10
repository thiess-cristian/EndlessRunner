package com.example.endlessrunner;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

public class GameplayScene implements Scene {
    private Rect _r;

    private RectPlayer _player;
    private Point _playerPoint;
    private ObstacleManager _obstacleManager;

    private boolean _movingPlayer;
    private boolean _gameOver=false;
    private long _gameOverTime;

    public GameplayScene(){

        _r=new Rect();
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        _player = new RectPlayer(new Rect(100, 100, 200, 200), paint);
        _playerPoint = new Point(Settings.SCREEN_WIDTH / 2, 3 * Settings.SCREEN_HEIGHT / 4);
        _player.update(_playerPoint);
        _obstacleManager = new ObstacleManager(200, 500, 200);
    }


    @Override
    public void terminate() {
        SceneManager.ACTIVE_SCENE=0;
    }

    @Override
    public void draw(Canvas canvas) {
        if (canvas != null) {
            canvas.drawColor(Color.WHITE);
            _player.draw(canvas);
            _obstacleManager.draw(canvas);

            if(_gameOver){
                Paint paint=new Paint();
                paint.setTextSize(100);
                paint.setColor(Color.RED);
                drawCenterText(canvas,paint,"game over");
            }
        }
    }

    @Override
    public void update() {
        if (!_gameOver) {
            _player.update(_playerPoint);
            _obstacleManager.update();
            if (_obstacleManager.playerCollide(_player)) {
                _gameOver = true;
                _gameOverTime = System.currentTimeMillis();
            }
        }
    }

    @Override
    public void receiveTouch(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                if (!_gameOver && _player.getRect().contains((int) event.getX(), (int) event.getY())) {
                    _movingPlayer = true;
                }
                if (_gameOver && System.currentTimeMillis() - _gameOverTime >= 2000) {
                    reset();
                    _gameOver = false;
                }
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                if (!_gameOver && _movingPlayer) {

                    _playerPoint.set((int) event.getX(), (int) event.getY());
                }
                break;
            }
            case MotionEvent.ACTION_UP: {
                _movingPlayer = false;
                break;
            }
        }
    }


    private void reset() {
        _playerPoint = new Point(Settings.SCREEN_WIDTH / 2, 3 * Settings.SCREEN_HEIGHT / 4);
        _player.update(_playerPoint);
        _obstacleManager = new ObstacleManager(200, 500, 200);
        _movingPlayer = false;

    }


    private void drawCenterText(Canvas canvas, Paint paint, String text) {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(_r);
        int cHeight = _r.height();
        int cWidth = _r.width();
        paint.getTextBounds(text, 0, text.length(), _r);
        float x = cWidth / 2f - _r.width() / 2f - _r.left;
        float y = cHeight / 2f + _r.height() / 2f - _r.bottom;
        canvas.drawText(text, x, y, paint);
    }
}
