package com.example.endlessrunner;


import android.content.Context;
import android.graphics.Canvas;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private GameModel _gameModel;
    private GameThread _mainThread;

    private SceneManager _sceneManager;

    private ModifyGestureDetector _gestureDetector;

    View.OnTouchListener _touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return _gestureDetector.onTouchEvent(event);
        }
    };


    public GameView(Context context) {
        super(context);
        Settings.CURRENT_CONTEXT = context;
        setOnTouchListener(_touchListener);
        _sceneManager = new SceneManager();
        _gestureDetector=_sceneManager.getSceneGestureDetector();
        getHolder().addCallback(this);
        _mainThread = new GameThread(getHolder(), this);
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Settings.INIT_TIME = System.currentTimeMillis();
        _mainThread.setRunning(true);
        _mainThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;

        while (retry) {
            try {
                _mainThread.setRunning(false);
                _mainThread.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        _sceneManager.receiveTouch(event);
        return true;
    }


    public void update() {
        _sceneManager.update();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        _sceneManager.draw(canvas);
    }

}
