import java.awt.*;
import javax.swing.*;
import java.lang.Thread;
import java.lang.InterruptedException;	
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class Timer extends Thread {
  ArrayList<Movable> stuff;
  Game frame;

  Timer () {
    super("timer");
  }

  public void run() {
    Movable t;
    while(true) {
      try{ //do what you want to do before sleeping

        Iterator<Movable> it = stuff.iterator();
        while(it.hasNext()) {
          t = it.next();
          t.act();
        }

        Thread.currentThread().sleep(100); // ms

        frame.repaint();

        if(frame.dude.dead) 
          frame.gameOver();

      } catch(InterruptedException ie){ //If this thread was intrrupted by nother thread 
      }
    }
  }
}
