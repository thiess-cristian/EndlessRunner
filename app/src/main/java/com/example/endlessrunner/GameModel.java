package com.example.endlessrunner;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.Random;

public class GameModel implements GameObject {

    private Player _player;
    private ArrayList<Enemy> _enemies;
    private ArrayList<MovingObject> _movingObjects;
    private ArrayList<Background> _backgrounds;

    private CollisionManager _collisionManager;
    private ScoreDisplay _scoreDisplay;
    private HeartDisplay _heartDisplay;

    private EnemySpawner _enemySpawner;
    private MovingObjectSpawner _movingObjectSpawner;
    private BirdSpawner _birdSpawner;

    private boolean _gameOver=true;
    private SpriteCollection _spriteCollection;

    public GameModel() {
        _spriteCollection=new SpriteCollection();
        _enemySpawner=new EnemySpawner();
        _movingObjectSpawner=new MovingObjectSpawner();
        _birdSpawner=new BirdSpawner();

        _enemies = new ArrayList<>();
        _movingObjects = new ArrayList<>();
        _collisionManager = new CollisionManager();
        _scoreDisplay=new ScoreDisplay();
        _heartDisplay=new HeartDisplay();
        _gameOver=false;
        _backgrounds=new ArrayList<>();

        addPlayer();
        addInitialBackground();
        addBackGround();

    }

    @Override
    public void draw(Canvas canvas) {

        for(Background background:_backgrounds){
            background.draw(canvas);
        }

        for (MovingObject movingObject : _movingObjects) {
            movingObject.draw(canvas);
        }

        for (Enemy enemy : _enemies) {
            enemy.draw(canvas);
        }
        _player.draw(canvas);
        _scoreDisplay.draw(canvas);
        _heartDisplay.draw(canvas);
    }

    @Override
    public void update() {
        _enemySpawner.addEnemy(_enemies);
        _movingObjectSpawner.addMovingObject(_movingObjects);
        _birdSpawner.addEnemy(_enemies);

        if (_enemies.size() > 0) {
            if (_enemies.get(0).getBoundingRect().right < 0) {
                _enemies.remove(0);
            }
        }

        for (Enemy enemy : _enemies) {
            enemy.update();
        }

        if (_movingObjects.size() > 0) {
            if (_movingObjects.get(0).getBoundingRect().right < 0) {
                _movingObjects.remove(0);
            }
        }

        for (MovingObject movingObject : _movingObjects) {
            movingObject.update();
        }

        for(Background background:_backgrounds){
            background.update();
            if(background.getBoundingRect().right<=0){
                background.reset();
            }
        }


        _collisionManager.checkPlayerObjectsCollision(_player, _movingObjects);
        _collisionManager.checkPlayerEnemyCollision(_player,_enemies);
        _player.update();
        updateHUD();


        if(!_player.isAlive()){
            _gameOver=true;
        }
    }

    public void updateHUD(){
        _scoreDisplay.setScore(_player.getScore());
        _heartDisplay.setHeartCount(_player.getLifePoints());
    }

    public Player getPlayer() {
        return _player;
    }

    public boolean isGameOver(){
        return _gameOver;
    }
    public void reset() {
        addPlayer();
        _enemies = new ArrayList<>();
        _movingObjects = new ArrayList<>();
        _collisionManager = new CollisionManager();
        _scoreDisplay=new ScoreDisplay();
        _heartDisplay=new HeartDisplay();
        _gameOver=false;
        _backgrounds=new ArrayList<>();
        addInitialBackground();
        addBackGround();
    }

    public void addBackGround(){
        int top = 0;
        int bottom = Settings.SCREEN_HEIGHT;
        int left = Settings.SCREEN_WIDTH;
        int right = left+Settings.SCREEN_WIDTH;
        Rect boundingRect=new Rect(left, top, right, bottom);
        _backgrounds.add(new Background(boundingRect,-5,0));
    }
    public void addInitialBackground(){
        int top = 0;
        int bottom = Settings.SCREEN_HEIGHT;
        int left = 0;
        int right = Settings.SCREEN_WIDTH;
        Rect boundingRect=new Rect(left, top, right, bottom);
        _backgrounds.add(new Background(boundingRect,-5,0));
    }
    public  void addPlayer(){
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);

        int top = Settings.SCREEN_HEIGHT -500;
        int bottom = top + 256;
        int left = 200;
        int right = left + 256;
        Rect boundingRect=new Rect(left, top, right, bottom);

        _player = new Player(boundingRect, paint);
    }
}
