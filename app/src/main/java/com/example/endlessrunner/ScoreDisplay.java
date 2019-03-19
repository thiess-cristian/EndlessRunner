package com.example.endlessrunner;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.LinkedList;

public class ScoreDisplay {

    private int _score;
    private Bitmap[] _numbers;

    public ScoreDisplay(){
        _numbers=SpriteCollection.getNumberSprites();
    }

    public void setScore(int score){
        _score=score/60;
    }

    void draw(Canvas canvas){
        Paint paint =new Paint();
        paint.setColor(Color.BLUE);
        paint.setTextSize(50);

        LinkedList<Integer> stack = new LinkedList<Integer>();
        while (_score > 0) {
            stack.push( _score % 10 );
            _score = _score / 10;
        }

        if(stack.isEmpty()){
            stack.push(0);
        }

        int left=20;
        int top=20;
        int right=left+100;
        int bottom=top+100;

        Rect rect=new Rect(left,top,right,bottom);

        while (!stack.isEmpty()) {
            canvas.drawBitmap(_numbers[stack.pop()], null, rect, new Paint());
            rect.offset(100,0);
        }


        //canvas.drawText(""+_score,50,50,paint);
    }

}
