import java.awt.*;
import java.awt.image.PixelGrabber;

/*
A Grid is a map of the Maze into coarser squares of size 'gridX'.  We take the
png image of the Maze and project it on the grid, so that whatever is different
than 'transparent' (where the characters can walk)) is considered a wall.
*/

public class Grid {
  static final int gridX = 2; //10;
  int[][] grid;
  int gW;
  int gH;
  boolean visible;
  boolean showWalls;
  boolean viewPositions;

  public Grid(Image image) {
    visible = showWalls = viewPositions = false;
    try {
      PixelGrabber grabber = new PixelGrabber(image, 0, 0, -1, -1, false);
      if(grabber.grabPixels()) {
        int[] data = (int[]) grabber.getPixels();
        int gpW = grabber.getWidth();
        int gpH = grabber.getHeight();

        int i, j;
        gW = (gpW/gridX)+1;
        gH = (gpH/gridX)+1;
        grid = new int[gW][gH];
        for(i=0; i<gW; i++) { // reset grid
          for(j=0; j<gH; j++)
            grid[i][j] = 0;
        }

        System.out.println("PG "+grabber.getWidth()+" x "+grabber.getHeight());
        System.out.println("GRID "+gW+" x "+gH);

        int x,y;
        int transparent = 0xff777777; //0xfffe0000; //data[0]; //0xfffe0000;
        for(i=0; i<data.length; i++) {
          //System.out.print("["+Integer.toHexString(data[i])+"]");
          //System.out.print(data[i]==transparent?" ":"x");
          //if((i+1)%gpW==0) {System.out.println(" "); /*System.exit(0);*/}
                    
          if(data[i] != transparent) {
            x = ((i+1)%gpW)/gridX;
            y = ((i+1)/gpW)/gridX;
            grid[x][y] = 1; // any pixel in the grid square is not transparent, mark as wall
          }
        }
      }
      //debugGrid();
    } catch (InterruptedException e1) { e1.printStackTrace(); }
  }

  public boolean canMoveHere (int posX, int posY) {
    int i = posX/gridX;
    int j = posY/gridX;
    if(i < 0 || j < 0 || i >= gW || j >= gH)
      return true;
    else
      return (grid[posX/gridX][posY/gridX] == 0);
  }

  void debugGrid () {
    int i, j;
    for(i=0; i<gW; i++) {
      for(j=0; j<gH; j++) {
        System.out.print(grid[i][j]==1?"x":".");     
      }
      System.out.print("\n");     
    }
    System.exit(0);
  }

  public void paint (Graphics g) {
      int x,y;
      g.setColor(Color.blue);
      for(int i=0; i<gW; i++) { 
        x = i*gridX;
        for(int j=0; j<gH; j++) {
          y = j*gridX;
          if(showWalls && grid[i][j] > 0) {
            g.fillRect(x,y,gridX,gridX);
          } else if(visible) {
            g.drawRect(x,y,gridX,gridX);
          }
        }
      }
  }
}
