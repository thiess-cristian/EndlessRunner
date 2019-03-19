package com.example.endlessrunner;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class SpriteCollection {

    private static Bitmap[] _backgroundSprites;
    private static Bitmap[] _numberSprites;
    private static Bitmap[] _playerSprites;
    private static Bitmap[] _enemySprites;
    private static Bitmap[] _birdSprites;
    private static Bitmap[] _heartSprites;
    private static Bitmap[] _coinSprites;
    private static Bitmap[] _platformSprites;

    SpriteCollection(){
        initBackgroundSprites();
        initNumberSprites();
        initPlayerSprites();
        initEnemySprites();
        initBirdSprites();
        initCoinSprites();
        initHeartSprites();
        initPlatformSprites();
    }


    static Bitmap[] getBackgroundSprites(){
        return _backgroundSprites;
    }

    static Bitmap[] getNumberSprites(){
        return _numberSprites;
    }

    static Bitmap[] getPlayerSprites(){
        return _playerSprites;
    }

    static Bitmap[] getEnemySprites(){
        return _enemySprites;
    }

    static Bitmap[] getBirdSprites(){
        return _birdSprites;
    }

    static Bitmap[] getHeartSprites(){
        return _heartSprites;
    }

    static Bitmap[] getCoinSprites(){
        return _coinSprites;
    }

    static Bitmap[] getPlatformSprites(){
        return _platformSprites;
    }

    private void initBackgroundSprites(){
        _backgroundSprites=new Bitmap[2];

        Bitmap sprite0=BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_background0);
        Bitmap sprite1=BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_background1);

        sprite0=Bitmap.createScaledBitmap(sprite0, Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT, false);
        sprite1=Bitmap.createScaledBitmap(sprite1, Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT, false);

        _backgroundSprites[0]=sprite0;
        _backgroundSprites[1]=sprite1;
    }

    private void initPlayerSprites(){
        _playerSprites= new Bitmap[12];

        _playerSprites[0]=BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_player00);
        _playerSprites[1]=BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_player01);
        _playerSprites[2]=BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_player02);
        _playerSprites[3]=BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_player03);
        _playerSprites[4]=BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_player04);
        _playerSprites[5]=BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_player05);
        _playerSprites[6]=BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_player06);
        _playerSprites[7]=BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_player07);
        _playerSprites[8]=BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_player08);
        _playerSprites[9]=BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_player09);
        _playerSprites[10]=BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_player10);
        _playerSprites[11]=BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_player11);
    }

    private void initNumberSprites(){
        _numberSprites=new Bitmap[10];

        _numberSprites[0]=BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_number00);
        _numberSprites[1]=BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_number01);
        _numberSprites[2]=BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_number02);
        _numberSprites[3]=BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_number03);
        _numberSprites[4]=BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_number04);
        _numberSprites[5]=BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_number05);
        _numberSprites[6]=BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_number06);
        _numberSprites[7]=BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_number07);
        _numberSprites[8]=BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_number08);
        _numberSprites[9]=BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_number09);
    }

    private void initEnemySprites(){
        _enemySprites=new Bitmap[1];
    }

    private void initBirdSprites(){
        _birdSprites=new Bitmap[1];
    }

    private void initHeartSprites(){
        _heartSprites=new Bitmap[2];

        _heartSprites[0]=BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_heart0);
        _heartSprites[1]=BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_heart1);
    }

    private void initCoinSprites(){
        _coinSprites=new Bitmap[2];

        _coinSprites[0]=BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_coin0);
        _coinSprites[1]=BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_coin1);
    }

    private void initPlatformSprites(){
        _platformSprites=new Bitmap[3];

        _platformSprites[0]=BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_platform0);
        _platformSprites[1]=BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_platform1);
        _platformSprites[2]=BitmapFactory.decodeResource(Settings.CURRENT_CONTEXT.getResources(), R.drawable.sprite_platform2);
    }
}
