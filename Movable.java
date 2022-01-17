import java.awt.*;

public class Movable {
  // TODO: add scaling to the images
  // private int scale = 1;

  protected XY p, to; // position, translation offset
  protected XY change; // active change rate in position
  protected XY changeRate; // delta change when movement is active
  private int spriteCyc;
  protected boolean moving; // the character is moving
  protected boolean facingRight; // the character is facing right
  protected SpriteManager spriteMan;

  public Movable(XY changeRate) {
    this.changeRate = changeRate;
    p = new XY(0, 0);
    change = new XY(0, 0);
    spriteCyc = 0;
    moving = false;
    facingRight = true;
    spriteMan = new SpriteManager();
  }

  // positionFraction = where the center of the character is on the x axis of the
  // sprite frame.
  // e.g.: 0.5 in the middle; 0.25 = between left and middle ; 0.75 = between
  // middle and right.
  public void init(XY size, double positionFraction) {
    to = new XY(-(int) (size.x() * positionFraction), -(int) size.y());
  }

  public void paint(Graphics g) {
    Image img = spriteMan.getCurrentImage(spriteCyc);
    spriteCyc++;

    int x1, x2, y1, y2, x3, x4;
    if (facingRight) {
      x1 = (int) (p.x() + to.x());
      x2 = (int) (p.x() + to.x() + img.getWidth(null));
      x3 = 0;
      x4 = img.getWidth(null);
    } else {
      x1 = (int) (p.x() - to.x() - img.getWidth(null));
      x2 = (int) (p.x() - to.x());
      x3 = img.getWidth(null);
      x4 = 0;
    }

    y1 = (int) (p.y() - img.getHeight(null));
    y2 = (int) (p.y());

    // g.drawImage(img, (int)(p.x()+to.x()), (int)(p.y()+to.y()), null);
    g.drawImage(img,
        x1, y1, x2, y2, // area where we draw the character
        x3, 0, x4, img.getHeight(null), // portion of the character sprite to draw
        null);

    // if(grid.viewPositions) {
    // g.setColor(Color.GREEN);
    // g.drawRect((int)(p.x()+to.x()),
    // (int)(p.y()+to.y()),(int)size.getWidth(),(int)size.getHeight());
    // g.fillOval((int)(p.x()), (int)(p.y()),3,3);
    // g.drawString("["+(int)p.x()+","+(int)p.y()+"]",(int)p.x()-24,(int)(p.y()+15));
    // g.drawString("isMoving = "+isMoving(),(int)p.x()-24,(int)(p.y()+30));
    // g.drawString("right = "+facingRight,(int)p.x()-24,(int)(p.y()+45));
    // }
  }

  public boolean move() {
    moving = false;

    if (change.x() != 0) {
      moving = tryMove(p.x() + change.x(), p.y());
      facingRight = change.x() > 0;
    }

    if (change.y() > 0) { 
      // TODO: handle jumping and crouching
    }

    return moving;
  }

  private boolean tryMove(double posX, double posY) {
    p.set(posX, posY);
    return true;
  }

  public void act() {} /* implemented in children */
}
