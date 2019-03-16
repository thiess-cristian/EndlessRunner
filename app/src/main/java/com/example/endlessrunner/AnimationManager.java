package com.example.endlessrunner;

import android.graphics.Canvas;
import android.graphics.Rect;

public class AnimationManager {

    private Animation[] _animations;
    private int _animationIndex = 0;

    public AnimationManager(Animation[] animations) {
        _animations = animations;
    }

    public void playAnimation(int index) {
        for (int i = 0; i < _animations.length; i++) {
            if (i == index) {
                if (!_animations[i].isPlaying()) {
                    _animations[i].play();
                }
            } else {
                _animations[i].stop();
            }
        }
        _animationIndex = index;
    }

    public void draw(Canvas canvas, Rect rect) {
        _animations[_animationIndex].draw(canvas, rect);
    }

    public void update() {
        _animations[_animationIndex].update();
    }

}
