package com.example.endlessrunner;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Bird extends Enemy {

    public Bird(Rect boundingRect, float xVelocity, float yVelocity, Paint paint) {
        super(boundingRect, xVelocity, yVelocity, paint);
    }

    @Override
    protected void initSprite(){
        _hitBox=new Rect();
        _hitBox.top=_boundingRect.top+64;
        _hitBox.bottom=_boundingRect.bottom-64;
        _hitBox.left=_boundingRect.left+64;
        _hitBox.right=_boundingRect.right-64;

        _animation = new Animation(SpriteCollection.getBirdSprites(), 0.5f);
        _animationManager=new AnimationManager(new Animation[]{_animation});
    }

    /*
    @Override
    public void draw(Canvas canvas) {
        //canvas.drawRect(_boundingRect,_paint);
    }
    */

    @Override
    public void update() {
        super.update();
        float sinValue=-(float)Math.cos(_boundingRect.left / 100) * 5;
        _boundingRect.top+=(int)sinValue;
        _boundingRect.bottom+=(int)sinValue;

        _hitBox.top=_boundingRect.top+64;
        _hitBox.bottom=_boundingRect.bottom-64;
        _hitBox.left=_boundingRect.left+64;
        _hitBox.right=_boundingRect.right-64;

    }

}