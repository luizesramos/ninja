import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class Scene extends JPanel {
  private final Dimension size = new Dimension(800, 400);
  private Plane parallaxPlane;

  ArrayList<Movable> stuff; // list of stuff on the screen (on top of the maze)
  boolean gameOver;

  public Scene() {
    parallaxPlane = new Plane("res/stage1/bg01.png", 2);
    setOpaque(false);
    setPreferredSize(size);
    gameOver = false;
  }

  protected void paintComponent(Graphics g) {
    parallaxPlane.drawOn(g);

    for (Movable m : stuff) {
      m.paint(g);
    }
    super.paintComponent(g);
  }
}
