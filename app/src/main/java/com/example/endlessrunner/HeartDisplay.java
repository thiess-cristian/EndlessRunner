package com.example.endlessrunner;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class HeartDisplay {

    int _heartCount;
    private Bitmap _sprite;

    public HeartDisplay(){
        _sprite = BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_heart0);
    }

    void setHeartCount(int count){
        _heartCount=count;
    }

    void draw(Canvas canvas){
        Paint paint =new Paint();
        //paint.setColor(Color.RED);
        //paint.setTextSize(50);
        //canvas.drawText(""+_heartCount,Settings.SCREEN_WIDTH-100,50,paint);

        int left=Settings.SCREEN_WIDTH-200;
        int top=20;
        int right=left+50;
        int bottom=top+50;

        Rect rect=new Rect(left,top,right,bottom);

        for(int i=0;i<=_heartCount;i++){
            canvas.drawBitmap(_sprite, null, rect, new Paint());
            rect.offset(50,0);
        }
    }

}
