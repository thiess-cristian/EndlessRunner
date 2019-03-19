package com.example.endlessrunner;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

public class SpriteCollection {

    private static ArrayList<Bitmap> _backgroundSprites;

    SpriteCollection(){
        initBackgroundSprites();
    }


    private void initBackgroundSprites(){
        _backgroundSprites=new ArrayList<>();

        Bitmap sprite0=BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_background0);
        Bitmap sprite1=BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_background1);

        sprite0=Bitmap.createScaledBitmap(sprite0, Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT, false);
        sprite1=Bitmap.createScaledBitmap(sprite1, Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT, false);


        _backgroundSprites.add(sprite0);
        _backgroundSprites.add(sprite1);
    }

    static ArrayList<Bitmap> getBackgroundSprites(){
        return _backgroundSprites;
    }
}
