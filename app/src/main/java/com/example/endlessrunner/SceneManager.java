package com.example.endlessrunner;

import android.graphics.Canvas;
import android.view.GestureDetector;
import android.view.MotionEvent;

import java.util.ArrayList;

public class SceneManager {

    private ArrayList<Scene> _sceneList=new ArrayList<>();
    public static int ACTIVE_SCENE;

    public SceneManager(){
        ACTIVE_SCENE=0;
        _sceneList.add(new GameplayScene());
    }

    public ModifyGestureDetector getSceneGestureDetector(){
        return _sceneList.get(ACTIVE_SCENE).getGestureDetector();
    }


    public void receiveTouch(MotionEvent event){
        _sceneList.get(ACTIVE_SCENE).receiveTouch(event);
    }

    public void update(){

        _sceneList.get(ACTIVE_SCENE).update();
    }

    public void draw(Canvas canvas){
        _sceneList.get(ACTIVE_SCENE).draw(canvas);
    }
}
