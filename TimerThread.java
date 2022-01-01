import java.lang.Thread;
import java.lang.InterruptedException;

public class TimerThread extends Thread {
  public interface Engine {
    void setup();

    void move();

    void draw();
  }

  private final static long WAIT_TIME_MILLISECONDS = 100;
  private Engine engine;

  TimerThread(Engine engine) {
    super("TimerThread");
    this.engine = engine;
  }

  public void run() {
    engine.setup();

    while (true) {
      try {        
        engine.move();

        sleep(WAIT_TIME_MILLISECONDS);

        engine.draw();
      } catch (InterruptedException e) {
        System.out.println("Timer was intrrupted by another thread");
      }
    }
  }
}
