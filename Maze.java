import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class Maze extends JPanel {
  static final int FLOOR = 0;
  static final int WALL = 1;
  ImageIcon maze, over;
  Dimension size;
  ArrayList<Movable> stuff; // list of stuff on the screen (on top of the maze)
  Grid grid; 
  boolean gameOver;
 
  public Maze() {
    maze = new ImageIcon("res/maze.png");
    over = new ImageIcon("res/over.png");
    size = new Dimension(maze.getIconWidth()+10, maze.getIconHeight()+30);
    setOpaque(false);
    setPreferredSize(size);
    grid = new Grid(maze.getImage()); 
    gameOver = false;
  }

  protected void paintComponent(Graphics g) {
    if(gameOver) {
      g.drawImage(over.getImage(), 0, 0, null);
      g.setColor(Color.RED);
      g.drawString("Press F5 to embarass yourself again!", 
                   (int)(0.25 * size.getWidth()), 
                   (int)(0.90 * size.getHeight()));
    } else {
      Movable t;
      g.drawImage(maze.getImage(), 0, 0, null);

      grid.paint(g);

      Iterator<Movable> it = stuff.iterator();
      while(it.hasNext()) {
        t = it.next();
        t.paint(g);
      }   
    }
    super.paintComponent(g);
  }
}
