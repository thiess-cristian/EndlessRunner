package com.example.endlessrunner;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Heart extends MovingObject {

    private Paint _paint;
    private boolean _isTaken;

    private Animation _animation;
    private AnimationManager _animationManager;

    public Heart(Rect boundingRect, float xVelocity, float yVelocity) {
        super(boundingRect, xVelocity, yVelocity);
        _paint=new Paint();
        _paint.setColor(Color.MAGENTA);
        _isTaken=false;

        Bitmap off = BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_heart0);
        Bitmap on = BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_heart1);

        _animation = new Animation(new Bitmap[]{on,off}, 1);
        _animationManager=new AnimationManager(new Animation[]{_animation});
    }

    public boolean isColliding(Player player){
        return Rect.intersects(player.getBoundingRect(),_boundingRect);
    }

    public void setIsTaken(boolean isTaken){

        _isTaken=isTaken;
    }

    public boolean isTaken(){

        return _isTaken;
    }

    @Override
    public void draw(Canvas canvas) {
        if(!_isTaken){
           // canvas.drawRect(_boundingRect,_paint);
            _animationManager.draw(canvas,_boundingRect);
        }
    }

    @Override
    public void update() {
        super.move();
        _animationManager.playAnimation(0);
        _animationManager.update();
    }


}
