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

    public Player(Rect boundingRect, Paint paint) {
        super(boundingRect, 0, 0);
        _paint = paint;

        _gravity = 0.5f;
        _jumpSpeed = -20.0f;
        _onGround = false;
        _groundLevel = 100;
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

        if (_boundingRect.left < 10 || _boundingRect.left > 190)
            _xVelocity *= -1;
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
