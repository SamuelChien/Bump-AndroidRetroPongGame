

public class Alien {
  private int color ;
  private Boolean isAlive, isShoot;
  private int timer, xPos, yPos;
  
  public Alien (int color, Boolean isAlive, Boolean isShoot, int timer, int xPos, int yPos ){
    this.color = color ;
    this.isAlive = isAlive;
    this.isShoot = isShoot;
    this.timer = timer;
    this.xPos = xPos;
    this.yPos = yPos;
  }
  
  public Boolean getIsAlive() {
    return this.isAlive;
  }
  
  public Boolean getIsShoot() {
    return this.isShoot;
  }
  
  public int getColor() {
    return this.color;
  }
  
  public int getTimer() {
    return this.timer;
  }
  
  public int getXPos() {
    return this.xPos;
  }
  
  public int getYPos(){
    return this.yPos;
  }
  
  public void setIsAlive(Boolean state){
    this.isAlive = state;
  }
  
  public void setIsShoot(Boolean state){
    this.isShoot = state;
  }
  
  public void setTimer(int newTimer){
    this.timer = newTimer;
  }
  
  public void setXPos (int newXPos){
    this.xPos = newXPos;
  }
  
  public void setYPos (int newYPos){
    this.yPos = newYPos;
  }
    
}