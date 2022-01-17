import java.awt.*;
import java.awt.image.BufferedImage;

class Plane {
  private BufferedImage img;
  private XY size;
  private XY offset;

  Plane(String imageName, int scale) {
    try {
      img = BufferedImageFactory.scaledImage(imageName, scale);
      size = new XY(img.getWidth(null), img.getHeight(null));
      offset = new XY(0, 0);
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Failed to read " + imageName);
    }
  }

  void xMoveByOffset(int dx) {
    offset.clampX(dx, 0, size.x());
  }

  void yMoveByOffset(int dy) {
    offset.clampY(dy, 0, size.y());
  }

  void drawOn(Graphics g) {
    g.drawImage(img, offset.x(), offset.y(), null);
  }
}
