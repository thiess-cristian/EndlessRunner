package com.example.endlessrunner;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Enemy extends MovingObject {

    protected Paint _paint;
    protected boolean _hitPlayer;
    protected Animation _animation;
    protected AnimationManager _animationManager;
    protected Rect _hitBox;

    public Enemy(Rect boundingRect, float xVelocity, float yVelocity, Paint paint) {
        super(boundingRect, xVelocity, yVelocity);

        _paint = paint;

        initSprite();
    }

    protected void initSprite(){
        _hitBox=new Rect();
        _hitBox.top=_boundingRect.top;
        _hitBox.bottom=_boundingRect.bottom;
        _hitBox.left=_boundingRect.left+64;
        _hitBox.right=_boundingRect.right-64;

        _animation = new Animation(SpriteCollection.getEnemySprites(), 1f);
        _animationManager=new AnimationManager(new Animation[]{_animation});
    }

    public boolean getHitPlayer(){
        return _hitPlayer;
    }

    public void setHitPlayer(boolean hitPlayer){
        _hitPlayer=hitPlayer;
    }

    boolean isColliding(Player player){
        return Rect.intersects(player.getHitBox(),_hitBox);
    }

    @Override
    public void draw(Canvas canvas) {
        //canvas.drawRect(_hitBox, _paint);
        _animationManager.draw(canvas,_boundingRect);
    }

    @Override
    public void update() {
        super.move();
        _hitBox.top=_boundingRect.top;
        _hitBox.bottom=_boundingRect.bottom;
        _hitBox.left=_boundingRect.left+64;
        _hitBox.right=_boundingRect.right-64;

        _animationManager.playAnimation(0);
        _animationManager.update();

    }
}
