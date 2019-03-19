package com.example.endlessrunner;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
    private Animation _animation;
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

        _hitBox=new Rect();
        _hitBox.top=_boundingRect.top;
        _hitBox.bottom=_boundingRect.bottom;
        _hitBox.left=_boundingRect.left+64;
        _hitBox.right=_boundingRect.right-64;

        Bitmap player0 = BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_player00);
        Bitmap player1 = BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_player01);
        Bitmap player2 = BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_player02);
        Bitmap player3 = BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_player03);
        Bitmap player4 = BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_player04);
        Bitmap player5 = BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_player05);
        Bitmap player6 = BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_player06);
        Bitmap player7 = BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_player07);
        Bitmap player8 = BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_player08);
        Bitmap player9 = BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_player09);
        Bitmap player10 = BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_player10);
        Bitmap player11 = BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_player11);

        _animation = new Animation(new Bitmap[]{player0,player1,player2,player3,player4,player5,player6,player7,player8,player9,player10,player11}, 0.8f);
        _animationManager=new AnimationManager(new Animation[]{_animation});


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
        canvas.drawRect(_hitBox, _paint);
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
        }
        _animationManager.playAnimation(0);
        _animationManager.update();
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
