

public class Ball {
  private Boolean isAlive;
  private int xPos, yPos, maxX, maxY ;
  private int [] vector ;
  
  public Ball (Boolean isAlive, int xPos, int yPos, int maxX, int maxY ){
    this.isAlive = isAlive;
    this.xPos = xPos;
    this.yPos = yPos;
    this.maxX = maxX;
    this.maxY = maxY;
    this.vector = new int [2]; 
  }
  
  public Boolean getIsAlive() {
    return this.isAlive;
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
  
  public void setXPos (int newXPos){
    this.xPos = newXPos;
  }
  
  public void setYPos (int newYPos){
    this.yPos = newYPos;
  }
  
  public void launched (int mode){
    if ( mode == 0 ) {
      this.vector[0] = -1;
      this.vector[1] = 1;
    }else if ( mode == 1 ){
      this.vector[0] = 0;
      this.vector[1] = 1;
    }else if ( mode == 2 ){
      this.vector[0] = 1;
      this.vector[1] = 1;    }
  }
  
  public void update () {
    if ( (xPos + 55 ) >= maxX ) {
      vector[0] *= -1;
    }else if ( xPos <= 0 ) {
      vector[0] *= -1;
    }
    if ( (yPos + 52 ) >= maxY ) {
      vector[1] *= -1;
    }else if ( yPos <= 0 ) {
      vector[1] *= -1;
    }
    xPos += vector[0];
    yPos += vector[1];
  }
  
  
}