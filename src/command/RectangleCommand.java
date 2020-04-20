package command;

import java.awt.*;
import java.awt.image.BufferedImage;
import shape.Rectangle;

public class RectangleCommand implements Command{

    private Rectangle rectangle;

    public RectangleCommand(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    @Override
    public void execute(BufferedImage image) {
        Graphics2D g2d = (Graphics2D) image.getGraphics();

        g2d.setColor(Color.BLACK);
        g2d.drawRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
    }


    public void setPoint(int x, int y) {
        rectangle.setX(x);
        rectangle.setY(y);
    }

    public void setWidth(int width) {
        rectangle.setWidth(width);
    }

    public void setHeight(int height) {
        rectangle.setHeight(height);
    }
}
