package com.freshtactic.ponggame;

import android.R.integer;

public class Alien {
  public int color, timer, xPos, yPos;
  public boolean isAlive;
  public int width =  90;
  public int height = 90;
  public int number = 0;
  
  public Alien (int colorpass, boolean isAlivepass, int timerpass, int xPospass, int yPospass, int number){

    color = colorpass;
    isAlive = isAlivepass;
    timer = timerpass;
    xPos = xPospass;
    yPos = yPospass;

  }
  
}