package com.example.endlessrunner;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class GameOverDisplay {

    private Rect _rect;
    private Paint _paint;

    private Animation _animation;
    private AnimationManager _animationManager;

    private Bitmap _m;

    public GameOverDisplay(){
        _paint=new Paint();
        _paint.setColor(Color.RED);
        _paint.setTextSize(100);

        int canvasW = Settings.SCREEN_WIDTH;
        int canvasH = Settings.SCREEN_HEIGHT;
        Point centerOfCanvas = new Point(canvasW / 2, canvasH / 2);
        int rectW = 800;
        int rectH = 400;
        int left = centerOfCanvas.x - (rectW / 2);
        int top = centerOfCanvas.y - (rectH / 2);
        int right = centerOfCanvas.x + (rectW / 2);
        int bottom = centerOfCanvas.y + (rectH / 2);
        _rect = new Rect(left, top, right, bottom);

        Bitmap off = BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_game_over0);
        Bitmap on = BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_game_over1);
        _animation = new Animation(new Bitmap[]{on,off}, 2);
        _animationManager=new AnimationManager(new Animation[]{_animation});

    }

    public void draw(Canvas canvas){
        _animationManager.draw(canvas,_rect);
    }

    public void update(){
        _animationManager.playAnimation(0);
        _animationManager.update();
    }
}
