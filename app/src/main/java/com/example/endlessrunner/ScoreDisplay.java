package com.example.endlessrunner;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class ScoreDisplay {

    private int _score;

    public void setScore(int score){
        _score=score/60;
    }

    void draw(Canvas canvas){
        Paint paint =new Paint();
        paint.setColor(Color.BLUE);
        paint.setTextSize(50);
        canvas.drawText(""+_score,50,50,paint);
    }

}
