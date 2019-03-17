package com.example.endlessrunner;

import java.util.ArrayList;


public class CollisionManager {


    void checkPlayerObjectsCollision(Player player, ArrayList<MovingObject> objects){

        boolean checkForPlatforms=true;

        for(MovingObject object:objects){
            if(object instanceof Platform && checkForPlatforms){
                Platform platform=(Platform)object;

                if(platform.isPlayerOnTop(player)){
                    player.setGroundLevel(Settings.SCREEN_HEIGHT-platform.getBoundingRect().top);
                    checkForPlatforms=false;
                }
            }

            else if(object instanceof Coin){
                Coin coin=(Coin)object;
                if(coin.isColliding(player) && !coin.isTaken()){
                    player.addScore(10000);
                    coin.setIsTaken(true);
                }
            }

            else if(object instanceof Heart){
                Heart heart=(Heart)object;
                if(heart.isColliding(player) && !heart.isTaken()){
                    player.addLifePoint();
                    heart.setIsTaken(true);
                }
            }
        }
        if(checkForPlatforms){
            player.setGroundLevel(100);
        }
    }

    void checkPlayerEnemyCollision(Player player, ArrayList<Enemy> objects){
        for(Enemy enemy:objects){
            if(enemy.isColliding(player) && !enemy.getHitPlayer()){
                player.subtractLifePoint();
                enemy.setHitPlayer(true);
            }
        }
    }

}
