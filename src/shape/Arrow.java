package shape;

/**
 *
 * Draw an arrow line between two points.
 *  x1 x-position of first point.
 *  y1 y-position of first point.
 *  x2 x-position of second point.
 *  y2 y-position of second point.
 *  d  the width of the arrow.
 *  h  the height of the arrow.
 *
 *  Reference: Logic from https://stackoverflow.com/questions/2027613/how-to-draw-a-directed-arrow-line-in-java
 */
public class Arrow implements Shape {
    private int x1,y1;
    private int x2,y2;
    private int d,h;

    public Arrow(int x1, int y1, int x2, int y2, int d, int h) {
        super();
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.d = d;
        this.h = h;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }
}
