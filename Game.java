import javax.swing.*;
import java.util.ArrayList;
import java.awt.event.*;

public class Game extends JFrame implements TimerThread.Engine, KeyListener, MouseListener { // , ActionListener {
  private Dude dude;
  private Scene scene;

  public Game(String s) {
    super(s);

    dude = new Dude();
    scene = new Scene();
    scene.setMovables(new ArrayList<>() {
      {
        add(dude);
      }
    });

    this.addKeyListener(this);
    this.addMouseListener(this);

    getContentPane().add(scene);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setPreferredSize(scene.getPreferredSize());
    setSize(scene.getPreferredSize());
    setResizable(false);
    setUndecorated(true); // removes title bar
    restart();
  }

  public void setup() {
    setLocationRelativeTo(null);
    setVisible(true);
  }

  public void draw() {
    scene.update();
    repaint();
  }

  public void restart() {
    dude.p.setLocation(getWidth() * 0.2, getHeight() * 0.8);
  }

  public void keyPressed(KeyEvent e) { // stop moving when keys are released
    switch (e.getKeyCode()) {
      case KeyEvent.VK_RIGHT:
        dude.change.x = Movable.stepX;
        break;
      case KeyEvent.VK_LEFT:
        dude.change.x = -Movable.stepX;
        break;
      case KeyEvent.VK_UP:
        dude.change.y = Movable.stepY;
        break;
      case KeyEvent.VK_DOWN:
        dude.change.y = -Movable.stepY;
        break;
      case KeyEvent.VK_SPACE:
        dude.change.x = dude.change.y = Movable.STOP;
        dude.celebrate(true);
        break;
      case KeyEvent.VK_CONTROL:
      case KeyEvent.VK_ALT:
        dude.change.x = dude.change.y = Movable.STOP;
        dude.lowhit(true);
        break;
    }
  }

  public void keyReleased(KeyEvent e) {
    switch (e.getKeyCode()) {
      case KeyEvent.VK_RIGHT:
      case KeyEvent.VK_LEFT:
        dude.change.x = Movable.STOP;
        break;
      case KeyEvent.VK_UP:
      case KeyEvent.VK_DOWN:
        dude.change.y = Movable.STOP;
        break;
      case KeyEvent.VK_SPACE:
        dude.celebrate(false);
        break;
      case KeyEvent.VK_CONTROL:
      case KeyEvent.VK_ALT:
        dude.lowhit(false);
        break;
    }
  }

  public void mousePressed(MouseEvent e) {
  }

  public void mouseReleased(MouseEvent e) {
  }

  public void mouseEntered(MouseEvent e) {
  }

  public void mouseExited(MouseEvent e) {
  }

  public void mouseClicked(MouseEvent e) {
    System.out.println("COORD " + e.getX() + " " + e.getY());
  }

  @Override
  public void keyTyped(KeyEvent e) {
  }
}
