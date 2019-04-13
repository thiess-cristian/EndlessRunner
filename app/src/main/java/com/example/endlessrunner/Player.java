package com.example.endlessrunner;

import android.graphics.Bitmap;
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
    private int _state;
    private Animation _runningAnimation;
    private Animation _jumpingAnimation;
    private Animation _fallingAnimation;
    private AnimationManager _animationManager;
    private Rect _hitBox;

    public Player(Rect boundingRect, Paint paint) {
        super(boundingRect, 0, 0);
        _paint = paint;

        _gravity = 0.5f;
        _jumpSpeed = -20.0f;
        _onGround = false;
        _groundLevel = 100;
        _lifePoints = 3;
        _isAlive = true;
        _state=0;

        _hitBox=new Rect();
        _hitBox.top=_boundingRect.top;
        _hitBox.bottom=_boundingRect.bottom;
        _hitBox.left=_boundingRect.left+64;
        _hitBox.right=_boundingRect.right-64;

        _runningAnimation = new Animation(SpriteCollection.getPlayerSprites(), 0.8f);
        Bitmap[] jumping=new Bitmap[1];
        jumping[0]=SpriteCollection.getPlayerSprites()[0];
        _jumpingAnimation = new Animation(jumping, 0.8f);
        Bitmap[] falling=new Bitmap[3];

        falling[0]=SpriteCollection.getPlayerSprites()[4];
        falling[1]=SpriteCollection.getPlayerSprites()[5];
        falling[2]=SpriteCollection.getPlayerSprites()[6];
        _fallingAnimation=new Animation(falling,0.3f);
        _animationManager=new AnimationManager(new Animation[]{_runningAnimation,_fallingAnimation});
    }

    public Rect getHitBox(){
        return _hitBox;
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
        //canvas.drawRect(_hitBox, _paint);
        _animationManager.draw(canvas,_boundingRect);
    }

    @Override
    public void update() {
        _yVelocity += _gravity;
        super.move();

        _hitBox.offset((int)_xVelocity,(int)_yVelocity);

        if (_boundingRect.bottom > Settings.SCREEN_HEIGHT - _groundLevel) {
            _boundingRect.bottom = Settings.SCREEN_HEIGHT - _groundLevel;
            _boundingRect.top = Settings.SCREEN_HEIGHT - _groundLevel - 256;

            _hitBox.top=_boundingRect.top;
            _hitBox.bottom=_boundingRect.bottom;

            _yVelocity = 0.0f;
            _onGround = true;
            _state=0;
        }
        _animationManager.playAnimation(_state);
        _animationManager.update();
    }

    public void startJump() {
        if (_onGround) {
            _yVelocity = _jumpSpeed;
            _onGround = false;
            _state=1;
        }
    }

    public void endJump() {
        if (_yVelocity < -10.0f) {
            _yVelocity = -10.0f;
            _state=1;
        }
    }
}
