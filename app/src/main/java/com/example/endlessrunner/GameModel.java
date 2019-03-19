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
    private long _initTime;
    private long _platformInitTime;
    private CollisionManager _collisionManager;
    private ScoreDisplay _scoreDisplay;
    private HeartDisplay _heartDisplay;

    private SpriteCollection _spriteCollection;

    private boolean _gameOver=true;

    public GameModel() {
        _spriteCollection=new SpriteCollection();

        _enemies = new ArrayList<>();
        _movingObjects = new ArrayList<>();
        _initTime = System.currentTimeMillis();
        _platformInitTime = _initTime;
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

        for (Enemy enemy : _enemies) {
            enemy.draw(canvas);
        }
        for (MovingObject movingObject : _movingObjects) {
            movingObject.draw(canvas);
        }
        _player.draw(canvas);
        _scoreDisplay.draw(canvas);
        _heartDisplay.draw(canvas);
    }

    @Override
    public void update() {
        long currentTime = System.currentTimeMillis();

        Random random = new Random();
        if (currentTime - _initTime > 2000) {
            _initTime = currentTime;

            if (random.nextBoolean()) {
                addEnemy();
            }
            if (random.nextBoolean()) {
                addBird();
            }
        }

        if (currentTime - _platformInitTime > 10000) {
            _platformInitTime = _initTime;
            addPlatform();
            if (random.nextBoolean()) {
            }

            if (random.nextBoolean()) {
                addHeart();
            }else{
                addCoin();
            }
        }

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

        if(_backgrounds.get(0).getBoundingRect().right<=0){
            //_backgrounds.remove(0);
            //_backgrounds.get(0).reset();
            //addBackGround();
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
        _scoreDisplay.setScore(_player.getScore());
        _heartDisplay.setHeartCount(_player.getLifePoints());


        if(!_player.isAlive()){
            _gameOver=true;
        }
    }

    public Player getPlayer() {
        return _player;
    }

    public boolean isGameOver(){
        return _gameOver;
    }

    private void addEnemy() {
        Random random = new Random();
        int yOffset = random.nextInt(200);
        int top = Settings.SCREEN_HEIGHT - yOffset-100;
        int bottom = top + 100;
        int left = Settings.SCREEN_WIDTH;
        int right = left + 100;
        Rect boundingRect = new Rect(left, top, right, bottom);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        _enemies.add(new Enemy(boundingRect, -10, 0, paint));
    }

    private void addBird() {
        Random random = new Random();
        int yOffset = random.nextInt(400) + 600;
        int top = Settings.SCREEN_HEIGHT - yOffset;
        int bottom = top + 100;
        int left = Settings.SCREEN_WIDTH;
        int right = left + 100;
        Rect boundingRect = new Rect(left, top, right, bottom);
        Paint paint = new Paint();
        paint.setColor(Color.YELLOW);
        _enemies.add(new Bird(boundingRect, -10, 0, paint));
    }

    private void addPlatform() {
        Random random = new Random();
        int top = Settings.SCREEN_HEIGHT - random.nextInt(150) - 200;
        int bottom = top + 200;
        int left = Settings.SCREEN_WIDTH;
        int right = left + 1000;
        Rect boundingRect = new Rect(left, top, right, bottom);
        _movingObjects.add(new Platform(boundingRect, -5, 0));
    }

    private void addHeart() {
        int top = Settings.SCREEN_HEIGHT - 200;
        int bottom = top + 100;
        int left = Settings.SCREEN_WIDTH;
        int right = left + 100;
        Rect boundingRect = new Rect(left, top, right, bottom);
        _movingObjects.add(new Heart(boundingRect, -5, 0));
    }

    private void addCoin() {
        int top = Settings.SCREEN_HEIGHT - 200;
        int bottom = top + 100;
        int left = Settings.SCREEN_WIDTH;
        int right = left + 100;
        Rect boundingRect = new Rect(left, top, right, bottom);
        _movingObjects.add(new Coin(boundingRect, -5, 0));
    }

    public void reset() {
        addPlayer();
        _enemies = new ArrayList<>();
        _movingObjects = new ArrayList<>();
        _initTime = System.currentTimeMillis();
        _platformInitTime = _initTime;
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
        int playerX = 200;
        int playerY = Settings.SCREEN_HEIGHT - 200;
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
