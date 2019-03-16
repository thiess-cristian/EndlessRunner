package com.example.endlessrunner;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;


class GameGestureDetector extends GestureDetector.SimpleOnGestureListener implements ModifyGestureDetector.MyGestureListener {

    private GameModel _gameModel;

    public GameGestureDetector(GameModel gameModel) {
        _gameModel=gameModel;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        _gameModel.getPlayer().startJump();

        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        if(_gameModel.isGameOver()){
            _gameModel.reset();
        }
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public void onUp(MotionEvent ev) {
        _gameModel.getPlayer().endJump();

    }
}
