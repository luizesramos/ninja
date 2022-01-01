import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.lang.Math;

public class SpriteManager {
  private ArrayList<ImageIcon> sprites; 
  private ArrayList<SpritePartition> partitions; 
  private SpritePartition activePartition;

  public SpriteManager () { 
    sprites = new ArrayList<ImageIcon>();
    partitions = new ArrayList<SpritePartition>();
    setActivePartition(0);
  }

  public ImageIcon addSprite (String s) {
    ImageIcon icon = new ImageIcon(s);
    sprites.add(icon);
    return icon;
  }

  public Image getImage (int index) {
    if(index > sprites.size()) return null;
    return sprites.get(index).getImage();
  }

  public Image getCurrentImage (int currCyc) {
    if(partitions.isEmpty())
      return getImage(currCyc%sprites.size());

    return sprites.get(activePartition.beg + (currCyc % activePartition.size())).getImage();
  }

  public void addPartition (int beg, int end) {
    partitions.add(new SpritePartition(beg, end));
  }

  public void setActivePartition (int index) {
    if(!partitions.isEmpty())
      activePartition = partitions.get(index);
  }

  public Dimension getImageDimension (int index) {
    if(sprites.size() == 0) return new Dimension (0, 0);
    Image img = getCurrentImage(0);
    return new Dimension (img.getWidth(null), img.getHeight(null));
  }

  public Dimension getMaxImageDimension () {
    if(sprites.size() == 0) return new Dimension (0, 0);
    int maxX = 0, maxY = 0;
    for(int i=0; i<sprites.size(); i++) {
      Image img = getImage(i);
      maxX = Math.max(maxX, img.getWidth(null));
      maxY = Math.max(maxY, img.getHeight(null));
    }
    return new Dimension (maxX, maxY);
  }
}
