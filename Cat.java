import java.util.Random;
import java.lang.Math;

public class Cat extends Movable {
  Dude dude;
  int cyc, skip;
  Random generator;
  int randCyc; // if stuck, do random movements for these many cycles

  public Cat(Grid g, Dude d, int id) {
    //for(int i=1; i<=6; i++) spriteMan.addSprite("res/cat/cat"+i+".gif");
    //init(spriteMan.getMaxImageDimension(), g, 0.5);

    if(id == 0) {
      spriteMan.addSprite("res/sky1.gif");
    } else if(id==1) {
      spriteMan.addSprite("res/jacko1.gif");
    } else if(id==2) {
      spriteMan.addSprite("res/fifi1.gif");
    } else {
      spriteMan.addSprite("res/dude.gif");
      spriteMan.addSprite("res/dude2.gif");
    }
    init(spriteMan.getMaxImageDimension(), g, 0.5);
    dirX = Movable.MOVE_R;
    dirY = Movable.MOVE_D;
    dude = d;
    generator = new Random();
    cyc = 0;
    skip = 2;
  }

  boolean approachX () {
    if(dude.p.getX() < p.getX()) {
      dirX = Movable.MOVE_L;
      return move();
    } else if(dude.p.getX() > p.getX()) {
      dirX = Movable.MOVE_R;
      return move();
    } else {
      dirX = Movable.STOP;
      return false; // don't change horizontal direction, it is the desired one
    }
  }

  boolean approachY () {
    if(dude.p.getY() < p.getY()) {
      dirY = Movable.MOVE_U;
      return move();
    } else if(dude.p.getY() > p.getY()) {
      dirY = Movable.MOVE_D;
      return move();
    } else {
      dirY = Movable.STOP;
      return false; // don't change vertical direction, it is the desired one
    }
  }

  boolean hitDude () {
    return (Math.abs(dude.p.getX()-p.getX()) < 8 && Math.abs(dude.p.getY()-p.getY()) < 8);
  }

  public void act() {
    if(hitDude()) {
      dude.dead = true;
      //System.out.println("GOTCHA!");
    }

    if(++cyc % skip != 0)
      return;

    if(randCyc > 0) {
      while(!move()) {
        randomMove();
      }
      randCyc--;
    }
    
    if(!approachY() && !approachX()) {
      while(!randomMove())
        randCyc = 100;
    }
  }

  boolean randomMove() {
    dirX = (generator.nextInt()) % 2 == 0 ? Movable.MOVE_R : Movable.MOVE_L;
    dirY = (generator.nextInt()) % 2 == 0 ? Movable.MOVE_D : Movable.MOVE_U;
    return move();
  }
}
