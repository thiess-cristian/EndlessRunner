package com.example.endlessrunner;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class RectPlayer implements GameObject {

    private Rect _rect;
    private Paint _paint;

    private Animation _idle;
    private Animation _walkRight;
    private Animation _walkLeft;
    private AnimationManager _animationManager;

    public RectPlayer(Rect rect, Paint paint) {
        _rect = rect;
        _paint = paint;

        BitmapFactory factory = new BitmapFactory();
        Bitmap idle = factory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.alienblue);
        Bitmap walk1 = factory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.alienblue_walk1);
        Bitmap walk2 = factory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.alienblue_walk2);

        _idle = new Animation(new Bitmap[]{idle}, 2);
        _walkRight = new Animation(new Bitmap[]{walk1, walk2}, 0.5f);

        Matrix m = new Matrix();
        m.preScale(-1, 1);
        Bitmap walk1Left = Bitmap.createBitmap(walk1, 0, 0, walk1.getWidth(), walk1.getHeight(), m, false);
        Bitmap walk2Left = Bitmap.createBitmap(walk2, 0, 0, walk2.getWidth(), walk2.getHeight(), m, false);

        _walkLeft = new Animation(new Bitmap[]{walk1Left, walk2Left}, 0.5f);

        _animationManager=new AnimationManager(new Animation[]{_idle,_walkLeft,_walkRight});

    }

    public Rect getRect() {
        return _rect;
    }

    @Override
    public void draw(Canvas canvas) {
        //canvas.drawRect(_rect, _paint);
        _animationManager.draw(canvas,_rect);
    }

    @Override
    public void update() {
        _animationManager.update();
    }

    public void update(Point point) {

        float olfLeft = _rect.left;

        int left = point.x - _rect.width() / 2;
        int top = point.y - _rect.height() / 2;
        int right = point.x + _rect.width() / 2;
        int bottom = point.y + _rect.height() / 2;
        _rect.set(left, top, right, bottom);

        int state = 0;
        if (_rect.left - olfLeft > 5) {
            state = 1;
        } else if (_rect.left - olfLeft < -5) {
            state = 2;
        }

        _animationManager.playAnimation(state);
        _animationManager.update();
    }
}
