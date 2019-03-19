package com.example.endlessrunner;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

class GameThread extends Thread {

    public static final int MAX_FPS = 60;
    private SurfaceHolder _surfaceHolder;
    private GameView _gameView;
    private boolean _running;
    private static Canvas _canvas;

    public GameThread(SurfaceHolder holder, GameView gameView) {
        super();

        _surfaceHolder = holder;
        _gameView = gameView;
    }

    public void setRunning(boolean running) {
        _running=running;
    }

    @Override
    public void run() {
        long startTime;
        long timeMillis;
        long waitTime;
        long totalTime = 0;
        int frameCount = 0;
        long targetTime = 1000 / MAX_FPS;
        long averageFPS = 0;

        while (_running) {
            startTime = System.nanoTime();
            _canvas = null;

            try {
                _canvas = _surfaceHolder.lockCanvas();
                synchronized (_surfaceHolder) {
                    _gameView.update();
                    _gameView.draw(_canvas);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (_canvas != null) {
                    try {
                        _surfaceHolder.unlockCanvasAndPost(_canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            timeMillis = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime - timeMillis;

            try {
                if(waitTime>0){
                    sleep(waitTime);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            totalTime += System.nanoTime() - startTime;
            frameCount++;
            if (frameCount == MAX_FPS) {
                averageFPS = 1000 / ((totalTime / frameCount) / 1000000);
                frameCount = 0;
                totalTime = 0;
                //System.out.println(averageFPS);
            }

        }
    }

}
