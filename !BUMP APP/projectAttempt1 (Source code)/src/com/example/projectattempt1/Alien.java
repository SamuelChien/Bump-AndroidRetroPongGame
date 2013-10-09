package com.example.projectattempt1;

import android.R.integer;

public class Alien {
  public int color, timer, xPos, yPos;
  public boolean isAlive;
  public int width =  90, height = 90;
  public int enemyrow;
  
  public Alien (int colorpass, boolean isAlivepass, int timerpass, int xPospass, int yPospass){
    color = colorpass;
    isAlive = isAlivepass;
    timer = timerpass;
    xPos = xPospass;
    yPos = yPospass;
  }
}