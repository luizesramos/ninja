import java.awt.*;

public class XY {
    private int xValue, yValue;

    public XY(int x, int y) {
        this.xValue = x;
        this.yValue = y;
    }

    public void setX(int x) {
        this.xValue = x;
    }

    public void setY(int y) {
        this.yValue = y;
    }

    public void set(int x, int y) {
        this.xValue = x;
        this.yValue = y;
    }

    public void set(double x, double y) {
        this.xValue = (int) x;
        this.yValue = (int) y;
    }

    public void clampX(int delta, int min, int max) {
        xValue = clamp(xValue, delta, min, max);
    }

    public void clampY(int delta, int min, int max) {
        yValue = clamp(yValue, delta, min, max);
    }

    private int clamp(int value, int delta, int min, int max) {
        return (value + delta < min || value + delta >= max) ? value : value + delta;
    }

    public int x() {
        return xValue;
    }

    public int y() {
        return yValue;
    }

    Point asPoint() {
        return new Point(xValue, yValue);
    }

    Dimension asDimension() {
        return new Dimension(xValue, yValue);
    }
}
