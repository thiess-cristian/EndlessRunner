package com.example.endlessrunner;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class ModifyGestureDetector extends GestureDetector {

    MyGestureListener myGestureListener;

    public ModifyGestureDetector(Context context, OnGestureListener listener) {
        super(context, listener);
        init(listener);
    }

    void init(OnGestureListener listener) {
        if (listener instanceof MyGestureListener) {
            myGestureListener = (MyGestureListener) listener;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_UP && myGestureListener != null) {
            myGestureListener.onUp(ev);
        }
        return super.onTouchEvent(ev);
    }

    public interface MyGestureListener {
        void onUp(MotionEvent ev);
    }
}
