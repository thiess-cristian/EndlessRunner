package com.example.endlessrunner;

import android.graphics.Canvas;
import android.graphics.Rect;

public abstract class MovingObject implements GameObject {

    protected Rect _boundingRect;
    protected float _xVelocity;
    protected float _yVelocity;

    public MovingObject(Rect boundingRect,float xVelocity,float yVelocity){
        _boundingRect=boundingRect;
        _xVelocity=xVelocity;
        _yVelocity=yVelocity;
    }

    Rect getBoundingRect(){
        return _boundingRect;
    }

    public void move() {
        _boundingRect.offset((int)_xVelocity,(int)_yVelocity);
    }
}
