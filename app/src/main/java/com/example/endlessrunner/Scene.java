package com.example.endlessrunner;

import android.graphics.Canvas;
import android.view.GestureDetector;
import android.view.MotionEvent;

public interface Scene  {
    void terminate();
    void draw(Canvas canvas) ;
    void update() ;
    void receiveTouch(MotionEvent event);
    ModifyGestureDetector getGestureDetector();
}
