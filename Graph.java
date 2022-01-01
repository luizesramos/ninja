import java.lang.String;
import java.awt.*;
import javax.swing.*;
import java.lang.Math;

public class Graph {
  private int [][]adjmat; // adjacency matrix (with integer weights)
  private int max, n; // number of vertices
  private Vertex []vertex;
  private boolean respectReachability; // upon adding edges

  public Graph (int limit) {
    max = limit+1;
    adjmat = new int [max][max];
    vertex = new Vertex [max];
    n = 0;
    respectReachability = true;
  }

  public boolean addVertex (int x, int y, String s, boolean reachable) {
    if(n+1 >= max) return false;
    vertex[n] = new Vertex(x, y, s, reachable);
    n++;
    return true;
  }

  public boolean addEdge (int v1, int v2, int weight) {
    if(v1 >= n || v2 >= n) return false; 

    if(respectReachability) {
      if(vertex[v1].isUnreachable() || vertex[v2].isUnreachable()) return false;
    }
    adjmat[v1][v2] = adjmat[v2][v1] = weight;
    //System.out.println("v"+v1+" <-> v"+v2);
    return true;
  }

  public int getWeight (int v1, int v2) {
    return adjmat[v1][v2];
  }

  public boolean hasEdge (int v1, int v2) {
    return getWeight(v1,v2) > 0;
  }

  public void selfGenerate (int maxX, int maxY, Grid g) {
    int size = max;
    int cols = (int)Math.ceil(Math.sqrt(size)), rows = (int)Math.ceil(size/cols);
    int padX = (int)Math.ceil(maxX/cols), padY = (int)Math.ceil(maxY/rows);
    int spacX = (int)Math.ceil((maxX-(2*padX))/cols), spacY = (int)Math.ceil((maxY-(2*padY))/rows);
    System.out.println("size "+size+" cols "+cols+" rows "+rows+" sX "+spacX+" sY "+spacY+" pX "+padX+" pY "+padY);
    int x, y, i, j;

    // creating vertices
    for (i=0; i<rows; i++) {
      for(j=0; j<cols; j++) {
        x = padX + (j*spacX);
        y = padY + (i*spacY);
        if(!addVertex (x, y, new String("v"+n), g.canMoveHere(x,y)))
          System.out.print("x");
      }
    }
    // creating edges
    for(i=0; i<(size-cols); i++) {
      if(i%cols != (cols-1)) { // we leave the last column of each row without edges
        addEdge(i,i+1, 1);
        addEdge(i,cols+i+1, 1);
      }
      addEdge(i,cols+i, 2);
    }
    for(i=size-cols; i<size-1; i++) addEdge(i,i+1, 1);
  }

  public void paint (Graphics g) {
    //int i, j; 
    //Vertex a, b;

    //g.setColor(Color.WHITE);
    //for(j=0; j<n; j++)
    //  for(i=0; i<n; i++) {
    //    if(hasEdge(i,j)) {
    //      a = vertex[i];
    //      b = vertex[j];
    //      g.drawLine(a.getX(), a.getY(), b.getX(), b.getY());
    //    }
    //  }

    //for(i=0; i<n; i++) 
    //  vertex[i].paint(g); 
  }
}
