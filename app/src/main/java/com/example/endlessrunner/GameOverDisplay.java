package com.example.endlessrunner;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class GameOverDisplay {

    private Rect _r;
    private Paint _paint;

    public GameOverDisplay(){
        _paint=new Paint();
        _paint.setColor(Color.RED);
        _paint.setTextSize(100);
        _r=new Rect();
    }

    public void draw(Canvas canvas){
        drawCenterText(canvas,_paint,"game over");
    }

    private void drawCenterText(Canvas canvas, Paint paint, String text) {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(_r);
        int cHeight = _r.height();
        int cWidth = _r.width();
        paint.getTextBounds(text, 0, text.length(), _r);
        float x = cWidth / 2f - _r.width() / 2f - _r.left;
        float y = cHeight / 2f + _r.height() / 2f - _r.bottom;

        canvas.drawText(text, x, y, paint);
    }

}
