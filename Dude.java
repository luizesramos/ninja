public class Dude extends Movable {
  final static int STILL_FRAMES = 0; 
  final static int WALK_FRAMES  = 1;
  final static int CELEB_FRAMES  = 2;
  final static int LOHIT_FRAMES  = 3;
  boolean dead, celebrating, hitting;

  public Dude() {
    int i;
    for(i=1; i<=7; i++) spriteMan.addSprite("res/rain/rain-stop"+i+".gif");
    for(i=1; i<=9; i++) spriteMan.addSprite("res/rain/rain-walk"+i+".gif");
    for(i=1; i<=4; i++) spriteMan.addSprite("res/rain/rain-celeb"+i+".gif");
    for(i=3; i>=1; i--) spriteMan.addSprite("res/rain/rain-celeb"+i+".gif");
    for(i=1; i<=5; i++) spriteMan.addSprite("res/rain/rain-lohit"+i+".gif");

    spriteMan.addPartition(0,   6); // STILL frames
    spriteMan.addPartition(7,  15); // WALK frames
    spriteMan.addPartition(16, 22); // CELEBRATE frames
    spriteMan.addPartition(23, 27); // LOW HIT frames
    spriteMan.setActivePartition(STILL_FRAMES);

    init(spriteMan.getMaxImageDimension(), 0.25);

    dirX = Movable.STOP;
    dirY = Movable.STOP;
    dead = celebrating = hitting = false;
  }

  public void act () {
    move();
    if(moving)
      spriteMan.setActivePartition (WALK_FRAMES);
    else if (celebrating)
      spriteMan.setActivePartition (CELEB_FRAMES);
    else if (hitting)
      spriteMan.setActivePartition (LOHIT_FRAMES);
    else
      spriteMan.setActivePartition (STILL_FRAMES);
  }

  public void celebrate (boolean status) {
    celebrating = status;
  }

  public void lowhit (boolean status) {
    hitting = status;
  }
}
