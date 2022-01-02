import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class Scene extends JPanel {
  private final Dimension size = new Dimension(800, 400);
  private Plane parallaxPlane;
  private ArrayList<Movable> movables;

  public Scene() {
    parallaxPlane = new Plane("res/stage1/bg01.png", 2);
    setOpaque(false);
    setPreferredSize(size);
  }

  public void setMovables(ArrayList<Movable> movables) {
    this.movables = movables;
  }

  public void update() {
    for (Movable m : movables) {
      m.act();
    }
  }

  protected void paintComponent(Graphics g) {
    parallaxPlane.drawOn(g);

    for (Movable m : movables) {
      m.paint(g);
    }
    super.paintComponent(g);
  }
}
