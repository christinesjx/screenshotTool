package command;

import shape.Rectangle;
import java.awt.*;
import java.awt.image.BufferedImage;


public class CropCommand implements Command {


    private Rectangle rectangle;
    private BufferedImage subImage;

    public CropCommand(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    @Override
    public void execute(BufferedImage image) {
        Graphics2D g2d = (Graphics2D) image.getGraphics();
        g2d.drawRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());

        if(rectangle.getHeight() > 0 && rectangle.getWidth() > 0){
            this.subImage = image.getSubimage(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
        }
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

    public BufferedImage getImage() {
        return subImage;
    }

    public void setImage(BufferedImage image) {
        this.subImage = image;
    }
}
