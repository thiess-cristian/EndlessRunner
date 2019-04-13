package com.example.endlessrunner;

import android.graphics.Canvas;
import android.view.MotionEvent;

public class GameplayScene implements Scene {

    private GameModel _gameModel;
    private ModifyGestureDetector _gestureDetector;

    private GameOverDisplay _gameOverDisplay;

    public GameplayScene() {
        _gameModel = new GameModel();
        _gestureDetector = new ModifyGestureDetector(Settings.CURRENT_CONTEXT, new GameGestureDetector(_gameModel));
        _gameOverDisplay=new GameOverDisplay();
    }


    @Override
    public void terminate() {
        SceneManager.ACTIVE_SCENE = 0;
    }

    @Override
    public void draw(Canvas canvas) {
        if (canvas != null) {
            _gameModel.draw(canvas);
            if(_gameModel.isGameOver()){

                _gameOverDisplay.draw(canvas);
            }
        }
    }

    @Override
    public void update() {
        if (!_gameModel.isGameOver()) {
            _gameModel.update();
        }else{
            _gameOverDisplay.update();
            _gameModel.updateHUD();
        }
    }

    @Override
    public void receiveTouch(MotionEvent event) {

    }

    @Override
    public ModifyGestureDetector getGestureDetector() {
        return _gestureDetector;
    }

}
