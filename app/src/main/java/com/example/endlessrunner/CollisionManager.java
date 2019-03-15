package com.example.endlessrunner;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.Set;

public class CollisionManager {


    void checkPlayerOnPlatform(Player player, ArrayList<MovingObject> objects){

        for(MovingObject object:objects){
            if(object instanceof Platform){
                Platform platform=(Platform)object;

                if(platform.isPlayerOnTop(player)){
                    player.setGroundLevel(Settings.SCREEN_HEIGHT-platform.getBoundingRect().top);
                    System.out.println("was on top");
                    platform.setColor(Color.BLUE);
                    return;
                }
            }
        }
        player.setGroundLevel(100);
    }
}
