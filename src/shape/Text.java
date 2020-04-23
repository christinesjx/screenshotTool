package shape;

public class Text implements Shape {

    private int x, y;

    private String string;

    public Text(int x, int y, String string) {
        this.x = x;
        this.y = y;
        this.string = string;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
