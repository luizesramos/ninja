import java.awt.*;
import java.util.ArrayList;
import javax.swing.ImageIcon;

// we may partition a list of sprites into subsets corresponding to specific states of the characters
public class SpritePartition {
  public enum LoopType {
    CIRCULAR,
    BACK_AND_FORTH
  }

  private ArrayList<ImageIcon> frames = new ArrayList<>();

  public SpritePartition(String resourcePath, int frameCount, SpritePartition.LoopType loopType) {
    switch (loopType) {
      case BACK_AND_FORTH:
        addFrames(resourcePath, 1, frameCount);
        addFrames(resourcePath, frameCount - 1, 1);
        break;
      case CIRCULAR:
        addFrames(resourcePath, 1, frameCount);
        break;
    }
  }

  public SpritePartition(String resourcePath, int frameCount) {
    this(resourcePath, frameCount, SpritePartition.LoopType.CIRCULAR);
  }

  public Image imageForCycle(int cycle) {
    return frames.get((cycle % frames.size())).getImage();
  }

  private void addFrames(String resourcePath, int start, int end) {
    if (start <= end) {
      for (int i = start; i <= end; i++) {
        frames.add(new ImageIcon(resourcePath + "/" + i + ".gif"));
      }
    } else {
      for (int i = start; i >= end; i--) {
        frames.add(new ImageIcon(resourcePath + "/" + i + ".gif"));
      }
    }
  }
}
