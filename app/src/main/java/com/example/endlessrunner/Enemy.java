package com.example.endlessrunner;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Enemy extends MovingObject {

    protected Paint _paint;
    protected boolean _hitPlayer;

    public Enemy(Rect boundingRect, float xVelocity, float yVelocity, Paint paint) {
        super(boundingRect, xVelocity, yVelocity);

        _paint = paint;
    }

    public boolean getHitPlayer(){
        return _hitPlayer;
    }

    public void setHitPlayer(boolean hitPlayer){
        _hitPlayer=hitPlayer;
    }

    boolean isColliding(Player player){
        return Rect.intersects(player.getHitBox(),_boundingRect);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect(_boundingRect, _paint);
    }

    @Override
    public void update() {
        super.move();
    }
}
