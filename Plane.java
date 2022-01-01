import java.awt.*;
import java.awt.image.BufferedImage;

class Plane {
  private BufferedImage img;
  private Point size;
  private Point offset;

  Plane(String imageName, int scale) {
    try {
      img = BufferedImageFactory.scaledImage(imageName, scale);
      size = new Point(img.getWidth(null), img.getHeight(null));
      offset = new Point(0, 0);
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Failed to read " + imageName);
    }
  }

  void xMoveByOffset(int dx) {
    offset.x = clamp(offset.x, dx, 0, size.x);
  }

  void yMoveByOffset(int dy) {
    offset.y = clamp(offset.y, dy, 0, size.y);
  }

  private int clamp(int value, int delta, int min, int max) {
    return (value + delta < min || value + delta >= max) ? value : value + delta;
  }

  void drawOn(Graphics g) {
    g.drawImage(img, offset.x, offset.y, null);
  }
}
