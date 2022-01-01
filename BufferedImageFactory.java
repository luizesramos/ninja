import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

public final class BufferedImageFactory {
    static public BufferedImage scaledImage(String imageName, int scale) throws Exception {
        BufferedImage img = ImageIO.read(new File(imageName));

        BufferedImage scaled = new BufferedImage(img.getWidth() * scale, img.getHeight() * scale, BufferedImage.TYPE_INT_ARGB);
        
        AffineTransform at = new AffineTransform();
        at.scale(scale * 1.0, scale * 1.0);
        AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
    
        return scaleOp.filter(img, scaled);
    }
}
