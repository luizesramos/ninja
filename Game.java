import javax.swing.*;
import java.util.ArrayList;
import java.awt.event.*;

public class Game extends JFrame implements TimerThread.Engine, KeyListener, MouseListener { // , ActionListener {
  // Midi music;
  Maze maze;
  Dude dude;
  ArrayList<Movable> stuff; // list of stuff on the screen (on top of the maze)
  static final int catCount = 0;
  Cat cat[];

  public Game(String s) {
    super(s);
    stuff = new ArrayList<Movable>();

    maze = new Maze();
    getContentPane().add(maze);
    maze.stuff = stuff;
    this.addKeyListener(this);
    this.addMouseListener(this);

    dude = new Dude(maze.grid);
    stuff.add(dude);

    cat = new Cat[catCount];
    for (int i = 0; i < catCount; i++) {
      cat[i] = new Cat(maze.grid, dude, i);
      stuff.add(cat[i]);
    }

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setPreferredSize(maze.size);
    setSize(maze.size);
    setResizable(false);

    restart();

    // music = new Midi();
  }

  public void setup() {
    setLocationRelativeTo(null);
    setVisible(true);
  }

  public void move() {
    for (Movable m : stuff) {
      m.act();
    }
  }

  public void draw() {
    repaint();
    if (dude.dead) {
      gameOver();
    }
  }

  public void restart() {
    dude.dead = false;
    dude.p.setLocation(getWidth() / 2, 0.4 * getHeight());
    if (catCount > 0)
      cat[0].p.setLocation(getWidth() * 0.1, getHeight() * 0.1);
    if (catCount > 1)
      cat[1].p.setLocation(getWidth() * 0.1, getHeight() * 0.9);
    if (catCount > 2)
      cat[2].p.setLocation(getWidth() * 0.9, getHeight() * 0.9);
    if (catCount > 3)
      cat[3].p.setLocation(getWidth() * 0.9, getHeight() * 0.1);
    maze.gameOver = false;
  }

  public void keyPressed(KeyEvent e) { // stop moving when keys are released
    switch (e.getKeyCode()) {
      case KeyEvent.VK_RIGHT:
        dude.dirX = Movable.MOVE_R;
        break;
      case KeyEvent.VK_LEFT:
        dude.dirX = Movable.MOVE_L;
        break;
      case KeyEvent.VK_UP:
        dude.dirY = Movable.MOVE_U;
        break;
      case KeyEvent.VK_DOWN:
        dude.dirY = Movable.MOVE_D;
        break;
      case KeyEvent.VK_SPACE:
        dude.dirX = dude.dirY = Movable.STOP;
        dude.celebrate(true);
        break;
      case KeyEvent.VK_CONTROL:
        dude.dirX = dude.dirY = Movable.STOP;
        dude.lowhit(true);
        break;

      case KeyEvent.VK_F2:
        maze.grid.visible = !maze.grid.visible;
        break;
      case KeyEvent.VK_F3:
        maze.grid.showWalls = !maze.grid.showWalls;
        break;
      case KeyEvent.VK_F4:
        maze.grid.viewPositions = !maze.grid.viewPositions;
        break;
      case KeyEvent.VK_F5:
        restart();
        break;
    }
  }

  public void keyReleased(KeyEvent e) {
    switch (e.getKeyCode()) {
      case KeyEvent.VK_RIGHT:
        dude.dirX = Movable.STOP;
        break;
      case KeyEvent.VK_LEFT:
        dude.dirX = Movable.STOP;
        break;
      case KeyEvent.VK_UP:
        dude.dirY = Movable.STOP;
        break;
      case KeyEvent.VK_DOWN:
        dude.dirY = Movable.STOP;
        break;
      case KeyEvent.VK_SPACE:
        dude.celebrate(false);
        break;
      case KeyEvent.VK_CONTROL:
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

  public void gameOver() {
    maze.gameOver = true;
  }

  @Override
  public void keyTyped(KeyEvent e) {
  }
}
