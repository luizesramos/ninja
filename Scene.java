import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class Scene extends JPanel {
  Dimension size;
  ArrayList<Movable> stuff; // list of stuff on the screen (on top of the maze)
  boolean gameOver;

  public Scene() {
    size = new Dimension(1024, 640);
    setOpaque(false);
    setPreferredSize(size);
    gameOver = false;
  }

  protected void paintComponent(Graphics g) {
    // g.drawImage(maze.getImage(), 0, 0, null);

    for (Movable m : stuff) {
      m.paint(g);
    }
    super.paintComponent(g);
  }

  static class Plane {

  }
}
