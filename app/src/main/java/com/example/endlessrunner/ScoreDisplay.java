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
    private ArrayList<Bitmap> _numbers;

    public ScoreDisplay(){
        _numbers=new ArrayList<>();
        _numbers.add(0,BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_number00));
        _numbers.add(1,BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_number01));
        _numbers.add(2,BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_number02));
        _numbers.add(3,BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_number03));
        _numbers.add(4,BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_number04));
        _numbers.add(5,BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_number05));
        _numbers.add(6,BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_number06));
        _numbers.add(7,BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_number07));
        _numbers.add(8,BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_number08));
        _numbers.add(9,BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_number09));
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
            canvas.drawBitmap(_numbers.get(stack.pop()), null, rect, new Paint());
            rect.offset(100,0);
        }


        //canvas.drawText(""+_score,50,50,paint);
    }

}
