import java.awt.*;

public class Movable {
  static final int STOP=-1;
  static final int MOVE_U=0;
  static final int MOVE_D=1;
  static final int MOVE_L=2;
  static final int MOVE_R=3;
  static final int stepX = 5, stepY = 5;

  public int dirX; // direction in which the object is moving
  public int dirY; // direction in which the object is moving
  Dimension size; // dimensions of the icon
  Point p, to; // position, translation offset
  Grid grid; // position of all walls
  int spriteCyc;
  protected boolean moving; // the character is moving
  protected boolean facingRight; // the character is facing right
  protected SpriteManager spriteMan;
  
  public Movable() {
    size = null;
    p = new Point(0, 0);
    grid = null;
    spriteCyc = 0;
    moving = false;
    facingRight = true;
    spriteMan = new SpriteManager();
  }

  // positionFraction = where the center of the character is on the x axis of the sprite frame.
  // e.g.: 0.5 in the middle; 0.25 = between left and middle ; 0.75 = between middle and right.
  public void init (Dimension size, Grid grid, double positionFraction) {
    this.size = size;
    this.grid = grid;
    to = new Point(-(int)(size.getWidth()*positionFraction), -(int)size.getHeight());
  }

  public void paint (Graphics g) {
    Image img = spriteMan.getCurrentImage(spriteCyc);
    spriteCyc++;

    int x1, x2, y1, y2, x3, x4;
    if(facingRight) {
      x1 = (int)(p.getX()+to.getX());
      x2 = (int)(p.getX()+to.getX() + img.getWidth(null));
      x3 = 0;
      x4 = img.getWidth(null);
    } else {
      x1 = (int)(p.getX()-to.getX() - img.getWidth(null));
      x2 = (int)(p.getX()-to.getX());
      x3 = img.getWidth(null);
      x4 = 0;
    }

    y1 = (int)(p.getY()-img.getHeight(null));
    y2 = (int)(p.getY()); 

    //g.drawImage(img, (int)(p.getX()+to.getX()), (int)(p.getY()+to.getY()), null);
    g.drawImage(img, 
                x1, y1, x2, y2, // area where we draw the character
                x3, 0,  x4, img.getHeight(null), // portion of the character sprite to draw
                null);

    if(grid.viewPositions) {
      g.setColor(Color.GREEN);
      g.drawRect((int)(p.getX()+to.getX()), (int)(p.getY()+to.getY()),(int)size.getWidth(),(int)size.getHeight());
      g.fillOval((int)(p.getX()), (int)(p.getY()),3,3);
      g.drawString("["+(int)p.getX()+","+(int)p.getY()+"]",(int)p.getX()-24,(int)(p.getY()+15));
      g.drawString("isMoving = "+isMoving(),(int)p.getX()-24,(int)(p.getY()+30));
      g.drawString("right = "+facingRight,(int)p.getX()-24,(int)(p.getY()+45));
    }
  }

  boolean tryMove (double posX, double posY) {
    if(grid.canMoveHere((int)posX,(int)posY)) {
      p.setLocation(posX,posY);
      return true; // could move
    }
    return false; // could not move
  }

  public boolean move () {
    moving = false;
    switch(dirX) {
      case MOVE_R: moving = tryMove(p.getX()+stepX,p.getY()); facingRight=true; break;
      case MOVE_L: moving = tryMove(p.getX()-stepX,p.getY()); facingRight=false;break;
    }
    switch(dirY) {
      case MOVE_U: moving = tryMove(p.getX(),p.getY()-stepY); break;
      case MOVE_D: moving = tryMove(p.getX(),p.getY()+stepY); break;
    }
    return moving;
  }

  public boolean isMoving () {
    return moving;
  }

  public void act () {
  }; // virtual function
}
