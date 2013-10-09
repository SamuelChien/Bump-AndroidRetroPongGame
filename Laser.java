

public class Laser {
  private int xPos, yPos;
  
  public Laser (int xPos, int yPos ){
    this.xPos = xPos;
    this.yPos = yPos;
  }
  
  public int getXPos() {
    return this.xPos;
  }
  
  public int getYPos(){
    return this.yPos;
  }
  
  public void setXPos (int newXPos){
    this.xPos = newXPos;
  }
  
  public void setYPos (int newYPos){
    this.yPos = newYPos;
  }
  
  public void incYPos(int inc){
    this.yPos += inc;
  }
  
}