import java.awt.*;
import java.util.HashMap;

public class SpriteManager {
  private HashMap<String, SpritePartition> partitions = new HashMap<>();
  private SpritePartition activePartition;

  public void addPartition(String partitionKey, SpritePartition partition) {
    partitions.put(partitionKey, partition);
  }

  public void setActivePartition(String partitionKey) {
    activePartition = partitions.get(partitionKey);
  }

  public Image getCurrentImage(int currCyc) {
    if (activePartition == null) {
      return null;
    }
    return activePartition.imageForCycle(currCyc);
  }

  public Dimension getMaxImageDimension() {
    // TODO: this was used for collision detection. Research a better way.
    return new Dimension(100, 100);
  }
}
