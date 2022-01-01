import java.lang.String;
import java.awt.*;
import javax.swing.*;

public class Vertex {
  private int x, y;
  private String label;
  private boolean reachable;

  public Vertex () {
    x = y = 0;
    label = null;
    reachable = true;
  }

  public Vertex (int x, int y, String s, boolean reachable) {
    create(x,y,s,reachable);
  }

  public void create (int x, int y, String s, boolean r) {
    this.x = x;
    this.y = y;
    this.label = s;
    this.reachable = r;
    //System.out.println(s+" "+x+" "+y);
  }

  public void paint (Graphics g) {
    g.setColor(reachable ? Color.GREEN : Color.RED);
    //g.fillRect( x, y, 1, 1 );
    g.drawString(label, x, y);
  }

  public int getX () { return x; }
  public int getY () { return y; }
  public boolean isUnreachable() { return !reachable; }
}
