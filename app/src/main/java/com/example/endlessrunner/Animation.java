package com.example.endlessrunner;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Animation {

    private Bitmap[] _frames;
    private int _frameIndex;
    private float _frameTime;
    private long _lastFrame;
    private boolean _isPlaying;

    public Animation(Bitmap[] frames, float animationTime) {
        _frames = frames;
        _frameTime = animationTime / _frames.length;
        _frameIndex = 0;
        _lastFrame = System.currentTimeMillis();
    }

    public boolean isPlaying() {
        return _isPlaying;
    }

    public void play() {
        _isPlaying = true;
        _frameIndex = 0;
        _lastFrame = System.currentTimeMillis();
    }

    public void stop() {
        _isPlaying = false;
    }

    public void draw(Canvas canvas, Rect destination) {
        if (!_isPlaying) {
            return;
        }

        //scaleRect(destination);
        canvas.drawBitmap(_frames[_frameIndex], null, destination, new Paint());
    }

    private void scaleRect(Rect destination) {
        float ratio = (float) _frames[_frameIndex].getWidth() / (float) _frames[_frameIndex].getHeight();

        if (destination.width() > destination.height()) {
            destination.left = destination.right - (int) (destination.width() * ratio);
        } else {
            destination.top = destination.bottom - (int) (destination.height() * (1.0 / ratio));
        }

    }


    public void update() {

        if (!_isPlaying) {
            return;
        }

        if (System.currentTimeMillis() - _lastFrame > _frameTime * 1000) {
            _frameIndex++;
            _frameIndex %= _frames.length;
            _lastFrame = System.currentTimeMillis();
        }
    }

}
