package com.example.endlessrunner;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Player extends MovingObject {

    private Paint _paint;
    private float _gravity;
    private boolean _onGround;
    private float _jumpSpeed;
    private int _groundLevel;
    private int _lifePoints;
    private boolean _isAlive;
    private int _score;

    public Player(Rect boundingRect, Paint paint) {
        super(boundingRect, 0, 0);
        _paint = paint;

        _gravity = 0.5f;
        _jumpSpeed = -20.0f;
        _onGround = false;
        _groundLevel = 100;
        _lifePoints = 3;
        _isAlive = true;
    }

    public void addScore(int value) {
        _score += value;
    }

    public int getScore() {
        return _score;
    }

    public void addLifePoint() {
        if (_lifePoints < 3) {
            _lifePoints++;
        }
    }

    public void subtractLifePoint() {
        _lifePoints--;
        if (_lifePoints < 0) {
            _isAlive = false;
        }
    }

    public int getLifePoints(){
        return _lifePoints;
    }

    public void setAlive(boolean isAlive) {
        _isAlive = isAlive;
    }

    public boolean isAlive() {
        return _isAlive;
    }

    public void setOnGround(boolean onGround) {
        _onGround = onGround;
    }

    public void setGroundLevel(int groundLevel) {
        _groundLevel = groundLevel;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect(_boundingRect, _paint);
    }

    @Override
    public void update() {
        _yVelocity += _gravity;
        super.move();

        if (_boundingRect.bottom > Settings.SCREEN_HEIGHT - _groundLevel) {
            _boundingRect.bottom = Settings.SCREEN_HEIGHT - _groundLevel;
            _boundingRect.top = Settings.SCREEN_HEIGHT - _groundLevel - 100;
            _yVelocity = 0.0f;
            _onGround = true;
        }
    }

    public void startJump() {
        if (_onGround) {
            _yVelocity = _jumpSpeed;
            _onGround = false;
        }
    }

    public void endJump() {
        if (_yVelocity < -10.0f) {
            _yVelocity = -10.0f;
        }
    }
}
