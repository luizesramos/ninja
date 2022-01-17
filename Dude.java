
public class Dude extends Movable {
  private enum State {
    IDLE("idle"), 
    WALKING("walk"), 
    CELEBRATING("celeb"), 
    LOW_PUNCH("lopunch"), 
    DEAD("dead"), 
    JUMPING("jump"), 
    JUMP_KICK("jumpkick"), 
    JUMP_PUNCH("jumppunch");

    final String rawValue;

    State(String rawValue) {
      this.rawValue = rawValue;
    }
  }

  private State state = State.IDLE;

  public Dude(XY changeRate) {
    super(changeRate);
    spriteMan.addPartition(State.IDLE.rawValue, new SpritePartition("res/rain/idle", 7));
    spriteMan.addPartition(State.WALKING.rawValue, new SpritePartition("res/rain/walk", 9));
    spriteMan.addPartition(State.LOW_PUNCH.rawValue, new SpritePartition("res/rain/lopunch", 5));
    spriteMan.addPartition(State.CELEBRATING.rawValue, new SpritePartition("res/rain/celeb", 4, SpritePartition.LoopType.BACK_AND_FORTH));
    spriteMan.setActivePartition(state.rawValue);

    init(spriteMan.getMaxImageDimension(), 0.25);
  }

  public void act() {
    boolean isMoving = move();
    
    if (isMoving) {
      if (state == State.IDLE) {
        state = State.WALKING;
      }
    } else {
      if (state == State.WALKING) {
        state = State.IDLE;
      }
    }

    spriteMan.setActivePartition(state.rawValue);
  }

  public void moveRight() {
    change.setX(changeRate.x());
  }

  public void moveLeft() {
    change.setX(-changeRate.x());
  }

  public void duck() {
    change.setY(0);
  }

  public void jump() {
    state = State.JUMPING;
  }

  public void stopX() {
    change.setX(0);
  }

  public void celebrate(boolean isActive) {
    state = isActive ? State.CELEBRATING : State.IDLE;
  }

  public void lowhit(boolean isActive) {
    if (state == State.JUMPING) {
      state = State.JUMP_PUNCH;
    } else {
      state = isActive ? State.LOW_PUNCH : State.IDLE;
    }
  }
}
